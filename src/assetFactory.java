// Source code is decompiled from a .class file using FernFlower decompiler.
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class assetFactory {
    public assetFactory() {
    }

    public static Asset createAsset(int type, Scanner scanner) {
        System.out.print("Enter Asset Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Quantity: ");
        float quantity = scanner.nextFloat();
        System.out.print("Enter Purchase Price: ");
        float price = scanner.nextFloat();
        scanner.nextLine();
        System.out.print("Enter Purchase Date (d-MM-yyyy): ");
        String dateStr = scanner.nextLine();

        Date date;
        try {
            date = new SimpleDateFormat("d-MM-yyyy").parse(dateStr);

            Date today = new Date();
            if (date.after(today)) {
                System.out.println("Invalid date: " + dateStr + " is in the future. Please enter a past or today's date.");
                return null;
            }

        } catch (ParseException var8) {
            System.out.println("Invalid date format.");
            return null;
        }


        Asset var1;
        switch (type) {
            case 1:
                var1 = new Stock(name, quantity, price, date);
                break;
                case 2:
                    System.out.print("is this Real Estate: 1)Investment          2)Owned ");
                    String choice2 = scanner.nextLine();
                    var1 = new RealEstate(name, quantity, price, date ,choice2);
                    break;
                    case 3:
                        System.out.print("is this Real Estate: 1)Investment          2)Owned ");
                        String choice3 = scanner.nextLine();
                        System.out.print("how many grams do you have?: ");
                        String choice4 = scanner.nextLine();
                        var1 = new Gold(name, quantity, price, date , choice3, Integer.parseInt(choice4));
                        break;
            default:
                System.out.println("Invalid asset type.");
                var1 = null;
        }

        return var1;
    }
}
