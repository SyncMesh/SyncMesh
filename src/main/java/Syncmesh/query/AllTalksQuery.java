package Syncmesh.query;

import Syncmesh.MSSQLConnection;
import org.syncmesh.AllTalksQuery.AllTalk;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AllTalksQuery implements Query<List<AllTalk>>{
    /**
     * Query all talkers from local DB.
     *
     * @param sql - holds the database connection.
     */
    private MSSQLConnection sql;
    public AllTalksQuery(MSSQLConnection sql){ this.sql = sql; }

    /**

     * @return List of maps, each map represents a row (entity) in the database.
     * @throws SQLException
     */
    @Override
    public List<AllTalk> execute() throws SQLException{
        List<Map<String,String>> results = sql.getQueryBuilder("Talk").buildAndExecute();
        List<AllTalk> allTalks = new ArrayList<>();
        for (Map<String,String> m: results) {
            allTalks.add(new AllTalk("Talk", m.get("id"),m.get("title"), m.get("description")));
        }
        return allTalks;
    }
}
