package aqa.course.databaseConnection;

import java.sql.*;
import java.util.ResourceBundle;

public class JDBCConnection {
    private static Connection connection;

    public static void connectToDB() {
        ResourceBundle resource = ResourceBundle.getBundle("database");

        String url = resource.getString("url");
        String user = resource.getString("user");
        String pass = resource.getString("password");
        String dbName = resource.getString("name");

        try {
            connection = DriverManager.getConnection(
                    url + ";database=" +
                            dbName + ";user=" +
                            user + ";password=" +
                            pass + ";encrypt=false;"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet executeSQLQuery(String query) {
        ResultSet resultSet;

        try {
            Statement statement = connection.createStatement();

            resultSet = statement.executeQuery(query);
            resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultSet;
    }

    public static void executeSQLUpdate(String query) {
        try {
            Statement statement = connection.createStatement();

            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
