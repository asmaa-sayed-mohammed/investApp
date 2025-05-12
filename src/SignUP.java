import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * The {@code SignUP} class handles user registration for the application.
 * It collects user input, validates data using {@link verifyUser}, checks for existing users,
 * and saves new user records to a file.
 *
 * <p>This class implements the {@link FilesFunction} interface for reading and saving user data from/to a file.</p>
 */
public class SignUP implements FilesFunction{
    verifyUser verify = new verifyUser();
    String name;
    String userName;
    String Password;
    String email;
    Scanner scanner = new Scanner(System.in);
    /**
     * Collects the user's sign-up information via standard input.
     */
    public void getDetails(){
        System.out.println("Insert your name: ");
        name = scanner.nextLine();
        System.out.println("Insert your userName: ");
        userName = scanner.nextLine();
        System.out.println("Insert your password: ");
        Password = scanner.nextLine();
        System.out.println("Insert your email: ");
        email = scanner.nextLine();
    }
    /**
     * Returns the entered username.
     *
     * @return The user's username.
     */
    public String getUserName(){
        return userName;
    }
    /**
     * Reads all registered users from the "User.txt" file.
     *
     * @return A list of string arrays, where each array contains the details of a single user.
     */
    public List<String[]> readFromFile(){
        String filePath = "User.txt";
        List<String[]> stockAccounts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] stockData = line.split(",");
                if (stockData.length == 4) {
                    for (int i = 0; i < stockData.length; i++) {
                        stockData[i] = stockData[i].trim();
                    }
                    stockAccounts.add(stockData);
                }
            }
        } catch (IOException e) {
            System.out.println("Can't read this file.");
        }
        return stockAccounts;
    }

    List<String[]> userData = new ArrayList<>();
    /**
     * Checks if the user (by email) is already signed up.
     *
     * @return {@code true} if the email already exists in the file, otherwise {@code false}.
     */
    public boolean isSigned() {
        userData = readFromFile();  // Get all user data from file
        for (String[] userRecord : userData) {
            // Check if a user with the same username or email already exists
            if ( userRecord[0].equals(email)) {
                return true;  // UserDetails already exists
            }
        }
        return false;  // UserDetails not found, proceed with sign-up
    }
    /**
     * Saves the user's sign-up information to the "User.txt" file if valid and unique.
     * Validates data using {@link verifyUser} class.
     */
    public void saveToFile(){
        File file = new File("User.txt");
        if (file.exists() && file.canWrite()){
            try (FileWriter writer = new FileWriter(file,true)) {
                if ((verify.verifyUsername(userName))
                        && (verify.verifyEmail(email))
                        && (verify.verifyName(name))
                        && (verify.verifyPassword(Password))
                        && (verify.uniqueUserName(userName))
                ){
                    writer.write(email  + ", ");
                    writer.write(Password  + ", ");
                    writer.write(userName + ", ");
                    writer.write(name + "\n");
                    System.out.println("-------------------------------------------------------------------\n");
                    System.out.println("SignedUP successfully so now lets login to start new adventure! ");
                }else {
                    System.out.println("Insert a valid data...");
                }
            } catch (IOException e) {
                System.out.println("Failed to save data...");
            }
        }else {
            System.out.println("File doesn't exist");
        }
    }

    public void implementSignUp(){
        getDetails();
        readFromFile();
        if (isSigned()){
            System.out.println("this user was signed before please choose log in..");
        }else {
            saveToFile();
        }
    }

}
