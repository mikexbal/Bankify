import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try {
            //Connection String
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankifyDB", "root", "");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user_accounts");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("username"));
                System.out.println(resultSet.getString("password"));

            }
        } catch (Exception e){
           System.out.println(e);
        }

    }
}
