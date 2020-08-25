package Syncmesh.query;
import Syncmesh.MSSQLConnection;

import org.syncmesh.TalkByIDQuery.TalkById;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class TalkByIDQuery implements Query<TalkById> {
    private String id;
    private MSSQLConnection sql;
    public TalkByIDQuery(MSSQLConnection sql, String id){
        this.sql = sql;
        this.id = id;
    }

    @Override
    public TalkById execute() throws SQLException {
        List<Map<String,String>> results = sql.getQueryBuilder("Talk").setWhereClause("id",id).buildAndExecute();
        Map<String, String> talk = results.get(0);
        //TODO: no object?

        return new TalkById("Talk", id,talk.get("title"), talk.get("description"));
    }
}
