package Syncmesh.mutation;

import Syncmesh.Future;
import Syncmesh.MSSQLConnection;
import com.apollographql.apollo.ApolloClient;
import org.syncmesh.AddSpeakerMutation.AddSpeaker;

import java.sql.SQLException;

public class AddSpeakerMutation implements Mutation<AddSpeaker> {

    /**
     * @auther Arik Regev
     *
     * */
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

    @Override
    public AddSpeaker execute() throws InterruptedException, SQLException {

        org.syncmesh.AddSpeakerMutation asm = org.syncmesh.AddSpeakerMutation.builder().name(name).twitter(twitter).build();
        org.syncmesh.AddSpeakerMutation.Data data = new Future<>(ap.mutate(asm)).get();

        //SQL insert if mutation was successfull
        sql.writeToDB(data.addSpeaker().__typename(), "'" + data.addSpeaker().id() + "','" + name + "','" + twitter + "'");

        return data.addSpeaker();
    }
}
