import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SignUP {
    verifyUser verify = new verifyUser();
    String name;
    String userName;
    String Password;
    String email;
    Scanner scanner = new Scanner(System.in);

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
    public String getUserName(){
        return userName;
    }
    public void saveToFile(){
        File file = new File("C:\\Users\\Mohamed.S\\IdeaProjects\\untitled6\\user.txt");
        if (file.exists() && file.canWrite()){
            try (FileWriter writer = new FileWriter(file,true)) {
                if ((verify.verifyUsername(userName))
                        && (verify.verifyEmail(email))
                        && (verify.verifyName(name))
                        && (verify.verifyPassword(Password))
                        && (verify.uniqueUserName(userName))
                ){
                    writer.write(name + ", ");
                    writer.write(userName + ", ");
                    writer.write(Password + ", ");
                    writer.write(email + "\n");
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
}
