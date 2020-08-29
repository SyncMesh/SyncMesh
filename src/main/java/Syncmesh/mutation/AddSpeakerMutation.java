package Syncmesh.mutation;

import Syncmesh.Future;
import Syncmesh.MSSQLConnection;
import com.apollographql.apollo.ApolloClient;
import org.syncmesh.AddSpeakerMutation.AddSpeaker;

import java.sql.SQLException;

public class AddSpeakerMutation implements Mutation<AddSpeaker> {
    /**
     * A mutation action for adding a speaker to the server through the GraphQL and then to the local DB.
     *
     * @param sql - holds the database connection.
     * @param ap - holds the connection to the Apollo Client.
     * @param name,twitter - fields of information for mutation.
     */
    private MSSQLConnection sql;
    private ApolloClient ap;
    private String name;
    private String twitter;

    public AddSpeakerMutation(MSSQLConnection sql, ApolloClient ap, String name, String twitter) {
        this.sql = sql;
        this.name = name;
        this.twitter = twitter;
        this.ap = ap;
    }

    /**
     * The function execute will send a mutate to the server,
     * The function will wait for a result thanks to the custom Future class,
     * and only then will write it to the local db.
     * (a True or false for operation success can be written)
     *
     * In a production environment we can immediately call a subscription to get notified on
     * changes in server side
     * @throws InterruptedException
     * @throws SQLException
     */
    @Override
    public AddSpeaker execute() throws InterruptedException, SQLException {

        org.syncmesh.AddSpeakerMutation asm = org.syncmesh.AddSpeakerMutation.builder().name(name).twitter(twitter).build();
        org.syncmesh.AddSpeakerMutation.Data data = new Future<>(ap.mutate(asm)).get();

        //SQL insert if mutation was successfull
        sql.writeToDB(data.addSpeaker().__typename(), "'" + data.addSpeaker().id() + "','" + name + "','" + twitter + "'");

        return data.addSpeaker();
    }
}
