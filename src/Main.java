import java.util.List;

public class Main {
    public static void main(String[] args) {
        SignUP s1 = new SignUP();
        s1.implementSignUp();
        String name = s1.getUserName();
        StockAccount accc1 = new StockAccount(name);
        accc1.implementStockAccount();
    }
}