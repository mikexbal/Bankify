import java.sql.*;


//This class will allow us to establish database connection
//Manage database verification

public class DatabaseManager {
    private Connection connection;



    public DatabaseManager(Connection connection) {
        this.connection = connection;
    }

    public double checkBalance(String username) throws SQLException {
        String query = "SELECT account_balance FROM user_accounts WHERE username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getDouble("account_balance");
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
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

    }

    public boolean validatePassword(String username, String password) throws SQLException {
        //Return true if password matches what's in database
        String query = "SELECT password FROM user_accounts WHERE username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        if (resultSet.getString("password").equals(password)) {
            return true;
        }
        return false;
    }


    public boolean validateUsername(String username) throws SQLException {
        //Return true if username is available
        String query = "Select username FROM user_accounts WHERE username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()){

            return false;
        } else {

            return true;
        }
    }


    public boolean createAccount(String username, String password, String verifyPassword) throws SQLException {
        if (validateUsername(username)) {
            System.out.println("Username exists already!");
            return false;
        }

        if (password.equals(verifyPassword)) {
            String query = "INSERT INTO user_accounts (username, password, account_balance) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setDouble(3, 0);
            preparedStatement.executeUpdate();
            System.out.println("Account successfully created!");
            return true;
        }
        System.out.println("Passwords do no match!");
        return false;
    }


}
