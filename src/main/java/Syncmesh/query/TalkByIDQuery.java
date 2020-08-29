package Syncmesh.query;
import Syncmesh.MSSQLConnection;

import org.syncmesh.TalkByIDQuery.TalkById;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class TalkByIDQuery implements Query<TalkById> {
    /**
     * Query talkers with specific id from local DB.
     *
     * @param sql - holds the database connection.
     * @param id - holds the required field for query.
     */
    private String id;
    private MSSQLConnection sql;
    public TalkByIDQuery(MSSQLConnection sql, String id){
        this.sql = sql;
        this.id = id;
    }
    /**
     *
     * @return TalkById entity queried from the DB, filtered by the id of the speaker.
     * Since id is a Primary key, only one object can be returned for this specific key.
     * @throws SQLException
     */
    @Override
    public TalkById execute() throws SQLException {
        List<Map<String,String>> results = sql.getQueryBuilder("Talk").setWhereClause("id",id).buildAndExecute();
        Map<String, String> talk = results.get(0);
        //TODO: no object?

        return new TalkById("Talk", id,talk.get("title"), talk.get("description"));
    }
}
