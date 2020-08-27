package Syncmesh.query;

import Syncmesh.MSSQLConnection;
import org.syncmesh.SpeakersByNameQuery.SpeakersByName;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SpeakersByNameQuery implements Query<List<SpeakersByName>> {
    private MSSQLConnection sql;
    private final String name;

    public SpeakersByNameQuery(MSSQLConnection sql, String name) {
        this.sql = sql;
        this.name = name;
    }

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
