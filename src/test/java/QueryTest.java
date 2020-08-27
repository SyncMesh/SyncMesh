import Syncmesh.MSSQLConnection;
import com.apollographql.apollo.ApolloClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.syncmesh.AllTalksQuery;
import org.syncmesh.SpeakersByNameQuery;
import org.syncmesh.TalkByIDQuery;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QueryTest {
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
    //ToDO: AllTalks, SpeakerByName
    @Test
    void executeAllTalks() throws InterruptedException, SQLException {

        List<AllTalksQuery.AllTalk> expected = new ArrayList<>();
        expected.add(new AllTalksQuery.AllTalk("Talk", "1", "this is desc for talk 1", "talk 1"));
        expected.add(new AllTalksQuery.AllTalk("Talk", "2", "this is desc for talk 2", "talk 2"));
        expected.add(new AllTalksQuery.AllTalk("Talk", "3", "this is desc for talk 3", "talk 3"));

        Syncmesh.query.AllTalksQuery atq = new Syncmesh.query.AllTalksQuery(sql);
        List<AllTalksQuery.AllTalk> data = atq.execute();
        System.out.println(data);
        assertEquals(expected, data);
    }

    @Test
    void queryTalkByID() throws InterruptedException, SQLException {
        TalkByIDQuery.TalkById expected = new TalkByIDQuery.TalkById("Talk", "1" , "this is desc for talk 1", "talk 1");
        Syncmesh.query.TalkByIDQuery query = new Syncmesh.query.TalkByIDQuery(sql, "1");
        TalkByIDQuery.TalkById data = query.execute();
        assertEquals(data, expected);
    }

    @Test
    void querySpeakerByName() throws InterruptedException, SQLException {
        SpeakersByNameQuery.SpeakersByName speaker = new SpeakersByNameQuery.SpeakersByName("Speaker", "60", "Arik 389", "Arik@Twitter");
        Syncmesh.query.SpeakersByNameQuery query = new Syncmesh.query.SpeakersByNameQuery(sql, "Arik 389");

        assertEquals(speaker,query.execute().get(0));
    }

}