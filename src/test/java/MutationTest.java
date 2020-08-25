import Syncmesh.MSSQLConnection;
import Syncmesh.mutation.AddSpeakerMutation;
import com.apollographql.apollo.ApolloClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MutationTest {

    final String URL = "http://82.166.179.129:8080/graphql";
    MSSQLConnection sql;
    ApolloClient ap;

    @BeforeEach
    void setUp() throws SQLException {
        ap = ApolloClient.builder().serverUrl(URL).build();
        sql = new MSSQLConnection();
    }

    @AfterEach
    void tearDown() throws SQLException {
        sql.closeConnection();
    }

    @Test
    void executeAddSpeaker() throws InterruptedException, SQLException {
        int rand = (int)(Math.random() * 10000);
        // Extract String to var
        AddSpeakerMutation adm = new AddSpeakerMutation(sql,ap, "Arik " + rand, "Arik@Twitter");
        org.syncmesh.AddSpeakerMutation.AddSpeaker data = adm.execute();

        assertEquals("Arik " + rand, data.name());
        assertEquals("Arik@Twitter", data.twitter());
    }
}