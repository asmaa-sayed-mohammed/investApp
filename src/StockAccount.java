import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * The {@code StockAccount} class manages a user's stock trading account information
 * such as email, password, and associated platform. It supports storing and retrieving
 * this data from a file.
 *
 * <p>This class implements the {@link FilesFunction} interface for file-based operations.</p>
 */
public class StockAccount implements FilesFunction{
    verifyUser verify = new verifyUser();
    String userName;
    String account;
    String password;
    String choice;
    String platformName;
    Scanner scanner = new Scanner(System.in);
    /**
     * Constructs a {@code StockAccount} for the specified user.
     *
     * @param userName The username of the account holder.
     */
    public StockAccount(String userName)
    {
        this.userName = userName;
    }
    /**
     * Displays a list of supported stock trading platforms.
     */
    public void stockPlatforms(){
        System.out.println("1 - EFG Hermes");
        System.out.println("2 - Arqaam Securities");
        System.out.println("3 - Al Ahly Pharos");
        System.out.println("4 - Thndr");
        System.out.println("5 - Mubasher Trade");
    }
    /**
     * Prompts the user to input details for a selected trading platform.
     *
     * @return The name of the chosen platform.
     */
    public String getDetails(){
        stockPlatforms();
        System.out.println("please choose one of these stocks");
        Scanner scanner = new Scanner(System.in);
        choice = scanner.nextLine();
        if (choice.equals("1")){
            System.out.println("Insert your email");
            account = scanner.nextLine();
            System.out.println("Insert your password");
            password = scanner.nextLine();
            platformName = "EFG Hermes";
        }else if (choice.equals("2")){
            System.out.println("Insert your email");
            account = scanner.nextLine();
            System.out.println("Insert your password");
            password = scanner.nextLine();
            platformName = "Arqaam Securities";
        }else if (choice.equals("3")){
            System.out.println("Insert your email");
            account = scanner.nextLine();
            System.out.println("Insert your password");
            password = scanner.nextLine();
            platformName = "Al Ahly Pharos";
        }else if (choice.equals("4")){
            System.out.println("Insert your email");
            account = scanner.nextLine();
            System.out.println("Insert your password");
            password = scanner.nextLine();
            platformName = "Thndr";
        }else if (choice.equals("5")) {
            System.out.println("Insert your email");
            account = scanner.nextLine();
            System.out.println("Insert your password");
            password = scanner.nextLine();
            platformName = "Mubasher Trade";
        }
        return platformName;
    }
    /**
     * Saves the stock account details to a file if all data is valid.
     */
    public void saveToFile(){
        String platForm = getDetails();
        File file = new File("C:\\Users\\Lenovo\\IdeaProjects\\investApp\\StockDetails");
        if (file.exists() && file.canWrite()){
            try (FileWriter writer = new FileWriter(file, true)){
                if (verify.verifyEmail(account) && verify.verifyPassword(password)){
                    writer.write(userName + ", ");
                    writer.write(platForm + ", ");
                    writer.write(account + ", ");
                    writer.write(password + "\n");
                    System.out.println("-------------------------------------------------------------------\n");
                    System.out.println("your stock has added successfully");
                }else {
                    System.out.println("Insert a valid Data");
                }
            } catch (IOException e) {
                System.out.println("Failed to save data...");
            }
        }else {
            System.out.println("File doesn't exist...");
        }
    }
    /**
     * Reads the stock account data associated with the current user from the file.
     *
     * @return A list of string arrays representing stock account entries.
     */
    public List<String[]> readFromFile(){
        String filePath = "C:\\Users\\Lenovo\\IdeaProjects\\investApp\\StockDetails";
        List<String[]> stockAccounts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] stockData = line.split(",");
                if (stockData.length > 1 && stockData[0].trim().equals(userName)) {
                    stockAccounts.add(stockData);
                }
            }
        } catch (IOException e) {
            System.out.println("Can't read this file.");
        }
        return stockAccounts;
    }
    /**
     * Displays the stored stock account details of the user.
     *
     * @param stock List of stock account entries to display.
     */
    public void getStocks(List<String[]> stock){
        for (String[] stockData : stock) {
            System.out.println("userName: " + userName +
                    ", Platform name: " + stockData[1] +
                    ", Account: " + stockData[2] +
                    ", Password: " + stockData[3]);
        }
    }
    /**
     * Provides an interface to the user to add a new stock account or view existing ones.
     */
    public void implementStockAccount(){
        System.out.println("choose what do you want to do: \n");
        System.out.println("1 - Add stock account \n");
        System.out.println("2 - See your accounts \n");
        String choice = scanner.nextLine();
        if (choice.equals("1")){
            saveToFile();
        }else if (choice.equals("2")){
            getStocks(readFromFile());
            if (readFromFile().isEmpty()){
                System.out.println("there is no Stock account ");
            }
        }
    }
}
