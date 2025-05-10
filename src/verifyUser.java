import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public  class verifyUser {
    // verify the name length
    public  boolean verifyName(String name){
        if (name.length() < 100 ){
            System.out.println("true name\n");
            return true;
        }else {
            System.out.println("false name\n");
            return false;
        }
    }
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
    public boolean uniqueUserName(String userName) {
        try (BufferedReader reader = new BufferedReader(new FileReader("UserDetails.txt"))) {
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
