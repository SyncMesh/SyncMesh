package Syncmesh.subscription;

import Syncmesh.MSSQLConnection;
import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.ApolloSubscriptionCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TalkScoresSubscription implements Subscription{
    private MSSQLConnection sql;
    private ApolloClient ap;
    ApolloSubscriptionCall.Callback<org.syncmesh.TalkScoresSubscription.Data> userCallback;

    public TalkScoresSubscription(MSSQLConnection sql, ApolloClient ap, ApolloSubscriptionCall.Callback<org.syncmesh.TalkScoresSubscription.Data> userCallback){
        this.ap = ap;
        this.sql = sql;
        this.userCallback = userCallback;
    }
    @Override
    public void execute() {
        final org.syncmesh.TalkScoresSubscription subscription = org.syncmesh.TalkScoresSubscription.builder().build();
        ap.subscribe(subscription).execute(new ApolloSubscriptionCall.Callback<org.syncmesh.TalkScoresSubscription.Data>() {
            @Override
            public void onResponse(@NotNull Response<org.syncmesh.TalkScoresSubscription.Data> response) {
                // TODO: update in local db
                try {
                    sql.updateInDB(response.getData().talkScores().__typename(),"title", response.getData().talkScores().title(), "score", response.getData().talkScores().score().toString());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                userCallback.onResponse(response);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                // TODO: do something?
                userCallback.onFailure(e);
            }

            @Override
            public void onCompleted() {
                // TODO: do something?
                userCallback.onCompleted();
            }

            @Override
            public void onTerminated() {
                // TODO: do something?
                userCallback.onTerminated();
            }

            @Override
            public void onConnected() {
                // TODO: do something?
                userCallback.onCompleted();
            }
        });
    }
}
