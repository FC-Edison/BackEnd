package sql;

import java.sql.*;

/**
 * @author Edison
 * @create 2021-04-06 16:05
 */
public class DatabaseHelper {

    private static DatabaseHelper instance = new DatabaseHelper();

    private DatabaseHelper() {
    }

    public static DatabaseHelper getInstance() {
        return instance;
    }

    private Connection connection = null;
    private Statement statement = null;

    public void connect(String host, int port, String database, String username, String password) {
        try {
            String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", username, password);
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet exeSqlQuery(String sql) {
        ResultSet result = null;
        try {
            result = statement.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public int exeSqlUpdate(String sql) {
        int result = 0;

        try {
            result = statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public static String sqlString(String str) {
        return "\"" + str + "\"";
    }
}
