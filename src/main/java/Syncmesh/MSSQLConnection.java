package Syncmesh;

import javafx.util.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MSSQLConnection {
    /**
     *
     * @param dbURL - holds the connection string that includes the URL to the DB Server, the instance,
     *              database's name, user and password.
     * @param conn - holds the connection to the database after a connection was created by the constructor of the class.
     */
    private String dbURL = "jdbc:sqlserver://localhost\\MSSQLSERVER;database=Syncmesh;user=gql;password=Aa@123456";
    private Connection conn = null;

    /**
     * Class Constructor - responsible for connection creation.
     * @throws SQLException
     */
    public MSSQLConnection() throws SQLException {
        conn = DriverManager.getConnection(dbURL);
    }

    /**
     * Responsible for closing the connection to the SQL.
     * @throws SQLException
     */
    public void closeConnection() throws SQLException {
        if (conn != null)
            conn.close();
    }

    /**
     * Writes a new entity into the database.
     *
     * @param tableName
     * @param values
     * @throws SQLException
     */
    public void writeToDB(String tableName, String values) throws SQLException{
        String sql = "INSERT INTO " + tableName + " VALUES (" + values + ");";
        conn.createStatement().execute(sql);
    }

    /**
     * Update a specific entity into the database.
     * @param tableName - name of the table we want to update/
     * @param idKey - by what column we want to filter the rows we want to update.
     * @param idValue - what value needed to be searched in the filter.
     * @param valueKey - what column we want to set for the relevant row.
     * @param valueValue - what value we want to set.
     * @throws SQLException
     */
    public void updateInDB(String tableName, String idKey, String idValue, String valueKey, String valueValue) throws SQLException{
        String sql = "UPDATE " + tableName + " SET " + valueKey + " = '" + valueValue + "' WHERE " + idKey + " = '" + idValue + "';";
        this.conn.createStatement().execute(sql);
    }

    /**
     *
     * @param tableName - specifies for what table we are building a query.
     * @return - returns a QueryBuilder.
     */
    public QueryBuilder getQueryBuilder(String tableName) {
        return new QueryBuilder(tableName);
    }

    public class QueryBuilder {
        /**
         * a class designated for building query strings to the local DB.
         * @param tableName - specifies for what table we are building a query.
         * @param columns - specifies inside the query what columns we want to query, when null all columns will be queried.
         * @param whereClause - specifies a filter to which rows should we query, if null, there will be no where statement inside the query string.
         */
        final private String tableName;
        private List<String> columns;
        private Pair<String, String> whereClause = null;

        public QueryBuilder(String tableName) {
            this.tableName = tableName;
            this.columns = new ArrayList<>();
        }

        public QueryBuilder addColumn(String column) {
            columns.add(column);
            return this;
        }

        public QueryBuilder setWhereClause(String key, String value) {
            whereClause = new Pair<>(key, value);
            return this;
        }

        /**
         * After setting all the relevant values, this function builds the query string and executes the query.
         * @return a List of maps, each map represents a row (entity) in the db.
         * @throws SQLException
         */
        public List<Map<String, String>> buildAndExecute() throws SQLException{
            return execute(build());
        }

        private String build() {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT ");
            if (columns.size() == 0) {
                sb.append("* ");
            } else {
                for (String column : columns) {
                    sb.append(column + ",");
                }
                sb.deleteCharAt(sb.length() - 1); // delete last ','
            }
            sb.append(" FROM ");
            sb.append(tableName);
            if (whereClause != null) {
                sb.append(" WHERE ");
                sb.append(whereClause.getKey());
                sb.append(" = '");
                sb.append(whereClause.getValue());
                sb.append("'");
            }
            sb.append(";");
            return sb.toString();
        }

        private List<Map<String, String>> execute(String query) throws SQLException{
            List<Map<String, String>> result = new ArrayList<>();
            ResultSet rs = null;
            ResultSetMetaData rsmd;

            rs = conn.createStatement().executeQuery(query);
            rsmd = rs.getMetaData();
            while (rs.next()) {
                Map<String, String> map = new HashMap<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    String column = rsmd.getColumnLabel(i);
                    map.put(column, rs.getString(column));
                }
                result.add(map);
            }
            return result;
        }

    }
}
