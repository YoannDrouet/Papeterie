package dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTools {
    private static final String URL = Settings.getPropriete("url");

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL);
        return connection;
    }
}
