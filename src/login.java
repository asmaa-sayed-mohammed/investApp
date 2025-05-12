import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * The {@code Login} class handles user authentication by verifying credentials against a stored user file.
 * It extends the {@link User} class and uses a local text file ("User.txt") for login validation.
 * <p>
 * This class is responsible for checking if the entered email and password match any entry in the user file,
 * and if so, it authenticates the user and stores the unique username for further operations.
 * </p>
 */
class login extends User{
    /**
     * Constructs a new {@code Login} instance with the provided email and password.
     * This constructor initializes the email and password of the user.
     *
     * @param e The user's email address.
     * @param p The user's password.
     */
    public login(String e, String p) {
        this.email = e;
        this.password = p;

    }
    /**
     * Authenticates the user by checking if the email and password match any entry in "User.txt".
     * If a match is found, it sets the {@code uniqueusername} field and prints a welcome message.
     *
     * @return {@code true} if authentication succeeds; {@code false} otherwise.
     */
    public boolean Authenticate() {
        try {
            File f = new File("User.txt");
            Scanner fileScanner = new Scanner(f);

            while(fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(", ");
                if (parts.length >= 2) {
                    String storedEmail = parts[2];
                    String storedPassword = parts[1];
                    String storedUniqueUsername = parts[2];
                    this.uniqueusername = storedUniqueUsername;
                    if (storedEmail.equals(this.email) && storedPassword.equals(this.password)) {
                        System.out.println("Login successful. Welcome," + this.uniqueusername  + "!");
                        System.out.println("_________________________________________________________");
                        fileScanner.close();
                        return true;
                    }
                }
            }

            fileScanner.close();
            System.out.println("Login failed. Invalid email or password, please try again later.");
            System.out.println("_________________________________________________________________");
            System.out.println();
        } catch (FileNotFoundException var7) {
            System.out.println("error retrieving the users, please try again later.");
        }
        return false;

    }
    /**
     * Gets the username (email) of the authenticated user.
     *
     * @return The {@code uniqueusername} of the logged-in user.
     */
    public String getUsername() {
        return this.uniqueusername;
    }

}