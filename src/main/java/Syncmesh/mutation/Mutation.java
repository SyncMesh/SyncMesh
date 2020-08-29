package Syncmesh.mutation;

import java.sql.SQLException;

public interface Mutation<T> {
    /**
     * a common interface that forces all mutations to be activated by the execute method
     **/
    public T execute() throws InterruptedException, SQLException;
}
