// Source code is decompiled from a .class file using FernFlower decompiler.
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class assetFactory {
    public assetFactory() {
    }

    public static Asset createAsset(int type, Scanner scanner) {
        System.out.print("Enter Asset Name : ");
        String name = scanner.nextLine();
        System.out.print("Enter Quantity : ");
        float quantity = scanner.nextFloat();
        System.out.print("Enter Purchase Price : ");
        float price = scanner.nextFloat();
        scanner.nextLine();
        System.out.print("Enter Purchase Date  (d-MM-yyyy): ");
        String dateStr = scanner.nextLine();

        Date date;
        try {
            date = (new SimpleDateFormat("d-MM-yyyy")).parse(dateStr);
        } catch (ParseException var8) {
            System.out.println("Invalid date format .");
            return null;
        }

        Stock var10000;
        switch (type) {
            case 1:
                var10000 = new Stock(name, quantity, price, date);
                break;
            default:
                System.out.println("Invalid asset type .");
                var10000 = null;
        }

        return var10000;
    }
}
