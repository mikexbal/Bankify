import java.sql.*;


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
            System.out.println(resultSet.getString("account_balance"));
        }

    }

    public void depositFunds(double amount, String username) throws SQLException {
        String query = "UPDATE user_accounts SET account_balance = account_balance + ? WHERE username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setDouble(1, amount);
        preparedStatement.setString(2, username);
        preparedStatement.executeUpdate();
    }

    public void withdrawFunds(double amount, String username) throws SQLException {
        String query = "UPDATE user_accounts SET account_balance = account_balance - ? WHERE username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setDouble(1, amount);
        preparedStatement.setString(2, username);
        preparedStatement.executeUpdate();
    }


    public void validateUser(String username, String password) throws SQLException {
        //Validate the username and then validate the password
        //if username not found prompt user to create account
        //if username is found validate the password to log them into account
    }

    public void createAccount(String username, String password) throws SQLException {

    }


    public void withdrawFunds(String username, double amount) throws SQLException {
        //
    }

    public void transferFunds(String fromUsername, String toUsername, double amount) throws SQLException {
        //
    }





}
