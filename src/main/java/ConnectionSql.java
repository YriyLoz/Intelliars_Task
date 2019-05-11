import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSql {
    private static final String URL = "jdbc:mysql://localhost:3306/database?useSSL=false&serverTimezone=UTC";
    private static final String NAME = "root";
    private static final String PASSWORD = "admin";
    private Connection connection;

    ConnectionSql() {
        try {
            connection = DriverManager.getConnection(URL, NAME, PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() throws SQLException {

        this.connection.close();


    }
}
