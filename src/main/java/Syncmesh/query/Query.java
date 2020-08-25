package Syncmesh.query;

import java.sql.SQLException;

public interface Query<T> {
    public T execute() throws SQLException;
}
