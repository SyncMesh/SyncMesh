package Syncmesh.query;

import java.sql.SQLException;

public interface Query<T> {
    /**
     * a common interface that forces all queries to be activated by the execute method
     **/
    public T execute() throws SQLException;
}
