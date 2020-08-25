package Syncmesh.mutation;

import java.sql.SQLException;

public interface Mutation<T> {
    public T execute() throws InterruptedException, SQLException;
}
