import Syncmesh.MSSQLConnection;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.ApolloSubscriptionCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.subscription.WebSocketSubscriptionTransport;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.syncmesh.TalkScoresSubscription;

import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubscriptionTest {
    final String URL = "http://82.166.179.129:8080/graphql";
    final String SubURL = "http://82.166.179.129:8080/subscriptions";
    OkHttpClient okHttpClient;
    MSSQLConnection sql;
    ApolloClient ap;

    @BeforeEach
    void setUp() throws SQLException {
        okHttpClient = new OkHttpClient();
        ap = ApolloClient.builder().serverUrl(URL).okHttpClient(okHttpClient)
                .subscriptionTransportFactory(new WebSocketSubscriptionTransport.Factory(SubURL, okHttpClient))
                .build();
        sql = new MSSQLConnection();
    }

    @AfterEach
    void tearDown() throws SQLException { sql.closeConnection(); }

    @Test
    public void executeSubscription() throws InterruptedException, ExecutionException {

        CompletableFuture<TalkScoresSubscription.Data> future = new CompletableFuture<>();

        Syncmesh.subscription.TalkScoresSubscription subscription = new Syncmesh.subscription.TalkScoresSubscription(sql,ap,new ApolloSubscriptionCall.Callback<TalkScoresSubscription.Data>() {
            @Override
            public void onResponse(@NotNull Response<TalkScoresSubscription.Data> response) {
                System.out.println("#####" + response.getData() + "#####");
                future.complete(response.getData());
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                System.out.println("##### Error!: " + e.getMessage() + "#####");
                future.completeExceptionally(e);
            }

            @Override
            public void onCompleted() {}

            @Override
            public void onTerminated() {}

            @Override
            public void onConnected() {}
        });

        subscription.execute();

        TalkScoresSubscription.Data data = future.get();
        assertEquals(data.talkScores().title(), "great talk");
    }
}
