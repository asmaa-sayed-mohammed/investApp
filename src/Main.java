import java.util.List;

public class Main {
    public static void main(String[] args) {
        SignUP s1 = new SignUP();
        s1.getDetails();
        String userName = s1.getUserName();
        System.out.println(userName);
    }
}