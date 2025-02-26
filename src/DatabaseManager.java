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

    public boolean validatePassword(String username, String password) throws SQLException {
        String query = "SELECT password FROM user_accounts WHERE username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            if (resultSet.getString("password").equals(password)) {
                return true;
            }
        }

        System.out.println("Passwords do no match!");
        return false;
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




}
