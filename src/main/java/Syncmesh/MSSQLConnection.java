package Syncmesh;

import javafx.util.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MSSQLConnection {
    private String dbURL = "jdbc:sqlserver://localhost\\MSSQLSERVER;database=Syncmesh;user=gql;password=Aa@123456";
    private Connection conn = null;

    public MSSQLConnection() throws SQLException {
        conn = DriverManager.getConnection(dbURL);
    }

    public void closeConnection() throws SQLException {
        if (conn != null)
            conn.close();
    }

    public void writeToDB(String tableName, String values) throws SQLException{
        String sql = "INSERT INTO " + tableName + " VALUES (" + values + ");";
        conn.createStatement().execute(sql);
    }

    public void updateInDB(String tableName, String idKey, String idValue, String valueKey, String valueValue) throws SQLException{
        String sql = "UPDATE " + tableName + " SET " + valueKey + " = '" + valueValue + "' WHERE " + idKey + " = '" + idValue + "';";
        this.conn.createStatement().execute(sql);
    }

    public QueryBuilder getQueryBuilder(String tableName) {
        return new QueryBuilder(tableName);
    }

    public class QueryBuilder {
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
