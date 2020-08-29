package Syncmesh.subscription;

import Syncmesh.MSSQLConnection;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.ApolloSubscriptionCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public class TalkScoresSubscription implements Subscription{
    /**
     * Subscribe to Talk Scores, when a change is made to a title's score in the server side,
     * the client will be updated automatically.
     *
     * @param sql - holds the database connection.
     * @param ap - holds the Apollo client connection to the remote GraphQL Server.
     * @param userCallbak - holds the configuration of what actions the user whats to take due to a score change
     *                    besides the action already configured in the client itself.
     */
    private MSSQLConnection sql;
    private ApolloClient ap;
    ApolloSubscriptionCall.Callback<org.syncmesh.TalkScoresSubscription.Data> userCallback;

    public TalkScoresSubscription(MSSQLConnection sql, ApolloClient ap, ApolloSubscriptionCall.Callback<org.syncmesh.TalkScoresSubscription.Data> userCallback){
        this.ap = ap;
        this.sql = sql;
        this.userCallback = userCallback;
    }
    /**
     *
     * After executing this command whenever a change to the score value will occur on the server side,
     * a set of commands predefined by the client and the user will occur.
     */
    @Override
    public void execute() {
        final org.syncmesh.TalkScoresSubscription subscription = org.syncmesh.TalkScoresSubscription.builder().build();
        ap.subscribe(subscription).execute(new ApolloSubscriptionCall.Callback<org.syncmesh.TalkScoresSubscription.Data>() {
            @Override
            public void onResponse(@NotNull Response<org.syncmesh.TalkScoresSubscription.Data> response) {
                try {
                    sql.updateInDB(response.getData().talkScores().__typename(),"title", response.getData().talkScores().title(), "score", response.getData().talkScores().score().toString());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                userCallback.onResponse(response);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                userCallback.onFailure(e);
            }

            @Override
            public void onCompleted() {
                userCallback.onCompleted();
            }

            @Override
            public void onTerminated() {
                userCallback.onTerminated();
            }

            @Override
            public void onConnected() {
                userCallback.onCompleted();
            }
        });
    }
}
