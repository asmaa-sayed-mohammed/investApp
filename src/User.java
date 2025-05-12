/**
 * The {@code User} class represents a user with authentication-related information
 * such as email, password, unique username, and name.
 * <p>
 * This class is used as a base for login and signup operations.
 */
public class User   {
    //neww
    String email;
    String password;
    String uniqueusername;
    String name;
    /**
     * Default no-argument constructor.
     */
    public User() {}
    /**
     * Constructs a {@code User} with the specified email, password, and unique username.
     *
     * @param email the user's email address
     * @param password the user's password
     * @param uniqueusername the user's unique username
     * @param type an unused parameter (likely a remnant or placeholder)
     */
    public User(String email, String password, String uniqueusername, String type) {
        this.email = email;
        this.password = password;
        this.uniqueusername = uniqueusername;
    }
}
