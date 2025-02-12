import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Main {
    //Main class will serve as the view
    //This class will control function calls and user inputs

    public static void main(String[] args) throws SQLException {
        //Create prop to access our config file
        Properties prop = new Properties();
        String propFileName = "resources/config.properties";
        String databaseURL;
        String databasePassword;
        String databaseUsername;
        String accountUsername;
        String accountPassword;


        try {
            //InputStream to read the contents of our config files
            FileInputStream inputStream = new FileInputStream(propFileName);
            prop.load(inputStream);

            //Grab config values and pass them into variables
            databaseURL = prop.getProperty("database.url");
            databasePassword = prop.getProperty("database.password");
            databaseUsername = prop.getProperty("database.user");

            //Establish connection with database
            DatabaseManager database = new DatabaseManager(DriverManager.getConnection(databaseURL, databaseUsername, databasePassword));

            System.out.println("Connected to the MySQL database successfully!");

            database.printTest();




        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//        System.out.println("Welcome to Bankify! \"Banking made easy!\"");
//        System.out.println("Please select one of the following options: 1 or 2\n" +
//                "1. Login \n2. Create Account \nEnter Choice: ");
//        Scanner scanner = new Scanner(System.in);
//        int choice = scanner.nextInt();
//
//        switch (choice) {
//            case 1: //Login to account
//                System.out.println("Please enter your username: ");
//
//                break;
//
//            case 2: //Create Account
//                System.out.println("Please enter your password: ");
//        }

