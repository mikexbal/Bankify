import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

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
            boolean menu = true;

            Scanner scanner = new Scanner(System.in);

            //Using while loop to keep looping until a valid option is selected
            while (menu) {
                try {
                    System.out.println("\nWelcome to Bankify, banking made simple!");
                    System.out.println("Please select an option from the list below:\n" +
                            "1. Login\n" +
                            "2. Create Account\n" +
                            "3. Exit");
                    System.out.println("Enter option: ");


                    int option = scanner.nextInt(); // Get user input

                    switch (option) {

                        //Log in, allow user to input details and validate
                        case 1:
                            menu = false;
                            System.out.println("Login");

                            break;

                        //Allow user to create an account
                        case 2:
                            menu = false;
                            System.out.println("Create Account");
                            break;

                        //User opted to exit program, thank user for using!
                        case 3:
                            menu = false;
                            System.out.println("Thanks for using Bankify!\nHave a great day!");
                            System.exit(0);

                        default:
                            System.out.println("Invalid option! Please enter 1, 2, or 3.");
                    }

                } catch (Exception e) {
                    System.out.println("Invalid input! Please enter a number.");
                    scanner.nextLine();
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


