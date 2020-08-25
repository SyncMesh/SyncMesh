import Syncmesh.MSSQLConnection;
import Syncmesh.query.AllTalksQuery;
import Syncmesh.query.TalkByIDQuery;
import Syncmesh.subscription.TalkScoresSubscription;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.ApolloSubscriptionCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.subscription.WebSocketSubscriptionTransport;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.syncmesh.AllTalksQuery.AllTalk;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //Build Main
        //Build Update / Insert INTO Builder
        //Exception Handling - V
        //ToDO: Pause with imput scanner for explenation time
        final String URL = "http://82.166.179.129:8080/graphql";
        final String SubURL = "http://82.166.179.129:8080/subscriptions";
        OkHttpClient okHttpClient;
        MSSQLConnection sql = null;
        try {
            sql = new MSSQLConnection();
            okHttpClient = new OkHttpClient();
            ApolloClient ap = ApolloClient.builder().serverUrl(URL).okHttpClient(okHttpClient)
                    .subscriptionTransportFactory(new WebSocketSubscriptionTransport.Factory(SubURL, okHttpClient))
                    .build();
            List<AllTalk> allTalkList = new AllTalksQuery(sql).execute();
            for (AllTalk talk : allTalkList) {
                System.out.println(talk.toString());
            }
            //ToDO: Nice Prints
            System.out.println(new TalkByIDQuery(sql, allTalkList.get(0).id()).execute().toString());

            //ToDO: Mutation and query on mutation

            //ToDo: Subscription
            TalkScoresSubscription subscription = new TalkScoresSubscription(sql, ap, new ApolloSubscriptionCall.Callback<org.syncmesh.TalkScoresSubscription.Data>() {
                @Override
                public void onResponse(@NotNull Response<org.syncmesh.TalkScoresSubscription.Data> response) {
                    System.out.println(response.getData());
                }

                @Override
                public void onFailure(@NotNull ApolloException e) {
                    System.out.println(e.getMessage());
                }

                @Override
                public void onCompleted() {
                    System.out.println();
                }

                @Override
                public void onTerminated() {
                    System.out.println();
                }

                @Override
                public void onConnected() {
                    System.out.println();
                }
            });
            subscription.execute();
        } catch (SQLException e) {
            if (sql != null) {
                try {
                    sql.closeConnection();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        } finally {

        }
    }
}
