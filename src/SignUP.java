import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SignUP implements FilesFunction{
    verifyUser verify = new verifyUser();
    String name;
    String userName;
    String Password;
    String email;
    Scanner scanner = new Scanner(System.in);

    public void getDetails(){
        System.out.println("Insert your name : ");
        name = scanner.nextLine();
        System.out.println("Insert your userName : ");
        userName = scanner.nextLine();
        System.out.println("Insert your password : ");
        Password = scanner.nextLine();
        System.out.println("Insert your email : ");
        email = scanner.nextLine();
    }

    public String getUserName(){
        return userName;
    }

    public List<String[]> readFromFile(){
        String filePath = "UserDetails.txt";
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
    public boolean isSigned() {
        userData = readFromFile();  // Get all user data from file
        for (String[] userRecord : userData) {
            // Check if a user with the same username or email already exists
            if (userRecord[2].equals(userName) || userRecord[0].equals(email)) {
                return true;  // UserDetails already exists
            }
        }
        return false;  // UserDetails not found, proceed with sign-up
    }

    public void saveToFile(){
        File file = new File("UserDetails.txt");
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
                    System.out.println("SignedUP successfully");
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
        if (isSigned()){
            System.out.println("this user was signed before please choose log in..");
        }else {
            saveToFile();
        }
    }

}
