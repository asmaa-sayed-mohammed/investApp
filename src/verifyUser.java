import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 * The {@code verifyUser} class provides utility methods for validating user input during
 * registration or login, including name, email, password, and username format checks.
 * It also checks for uniqueness of usernames in a stored file.
 */
public  class verifyUser {
    // verify the name length
    /**
     * Verifies that the provided name is less than 100 characters.
     *
     * @param name the user's name
     * @return {@code true} if the name is valid, otherwise {@code false}
     */
    public  boolean verifyName(String name){
        if (name.length() < 100 ){
            System.out.println("true name\n");
            return true;
        }else {
            System.out.println("false name\n");
            return false;
        }
    }
    /**
     * Validates the structure and length of an email address.
     * Ensures it matches a general email pattern and is under 100 characters.
     *
     * @param email the user's email address
     * @return {@code true} if the email is valid, otherwise {@code false}
     */
    // verify the email structure and length
    public  boolean verifyEmail(String email){
        // verify that email = user@domain.com
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matchedEmail = pattern.matcher(email);
        if ((email.length() < 100) && matchedEmail.matches()){
            System.out.println("true email\n");
            return true;
        }else {
            System.out.println("false email\n");
            return false;
        }
    }
    // verify password structure and length
    /**
     * Validates the structure and strength of a password.
     * Requires at least one uppercase letter and one number or special character.
     *
     * @param password the user's password
     * @return {@code true} if the password is strong and valid, otherwise {@code false}
     */
    public  boolean verifyPassword(String password){
        // should contain at least one capital letter
        boolean hasUppercase = password.matches(".*[A-Z].*");
        // should contain at least number and may contain special characters
        boolean hasNumberOrSpecial = password.matches(".*[0-9\\W].*");
        if ((password.length() < 100) && hasUppercase && hasNumberOrSpecial ){
            System.out.println("true password\n");
            return true;
        }
        else {
            System.out.println("false password\n");
            return false;
        }
    }
    /**
     * Verifies that the provided username is less than 50 characters.
     *
     * @param userName the user's username
     * @return {@code true} if the username is valid, otherwise {@code false}
     */
    // verify userName length
    public  boolean verifyUsername(String userName){
        if (userName.length() < 50){
            System.out.println("true username\n");
            return true;
        }else {
            System.out.println("false username\n");
            return false;
        }
    }
    // verify uniqness of username
    /**
     * Checks whether the given username is unique by scanning the "User.txt" file.
     *
     * @param userName the username to check
     * @return {@code true} if the username is not taken, otherwise {@code false}
     */
    public boolean uniqueUserName(String userName) {
        try (BufferedReader reader = new BufferedReader(new FileReader("User.txt"))) {
            String line;
            //.trim for removing space at end and at beginning
            while ((line = reader.readLine()) != null) {
                // make the lines about list to check it easier
                String[] data = line.split(",");
                // data.length > 1 to confirm that there is a data and it's not an empty data
                if (data.length > 1 && data[2].trim().equals(userName)) {
                    System.out.println("This username is used by another user, please choose another one.");
                    return false;
                }
            }
            return true;
        } catch (IOException e) {
            System.out.println("can't read this file");
            return false;
        }
    }

}
