package Syncmesh.query;

import Syncmesh.MSSQLConnection;
import org.syncmesh.SpeakersByNameQuery.SpeakersByName;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SpeakersByNameQuery implements Query<List<SpeakersByName>> {
    /**
     * Query talkers with specific name from local DB.
     *
     * @param sql - holds the database connection.
     * @param name - holds the required field for query.
     */
    private MSSQLConnection sql;
    private final String name;

    public SpeakersByNameQuery(MSSQLConnection sql, String name) {
        this.sql = sql;
        this.name = name;
    }

    /**
     *
     * @return a list of maps, each map represents a row (entity)
     * of speakers in the database, filtered by the name of the speaker.
     * @throws SQLException
     */
    @Override
    public List<SpeakersByName> execute() throws SQLException {
        List<Map<String, String>> results = sql.getQueryBuilder("Speaker").setWhereClause("name", name).buildAndExecute();
        List<SpeakersByName> speakers = new ArrayList<>();
        for (Map<String, String> m : results) {
            speakers.add(new SpeakersByName("Speaker", m.get("id"), m.get("name"), m.get("twitter")));
        }
        return speakers;
    }
}
