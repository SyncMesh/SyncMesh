package Syncmesh;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Future<TData extends Operation.Data> {

    private ApolloCall<TData> call;
    private TData data = null;

    public Future(ApolloCall<TData> call){
        this.call = call;
    }

    public TData get() throws ApolloException, InterruptedException{
        if (data != null) return data;

        CompletableFuture<TData> future = new CompletableFuture<>();

        call.enqueue(new ApolloCall.Callback<TData>() {
            @Override
            public void onResponse(@NotNull Response<TData> response) {
                future.complete(response.getData());
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                future.completeExceptionally(e);
            }
        });

        try {
            return future.get();
        } catch (ExecutionException e) {
            throw (ApolloException) e.getCause();
        }
    }
}
