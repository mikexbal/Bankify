import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


//This class will allow us to establish database connection
//Manage database verification

public class DatabaseManager {
    private Connection connection;



    public DatabaseManager(Connection connection) {
        this.connection = connection;
    }

    public void printTest() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM user_accounts");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("username"));
            System.out.println(resultSet.getString("password"));
        }

    }

}
