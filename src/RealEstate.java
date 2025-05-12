import java.util.Date;
import java.util.Scanner;

public class RealEstate extends Asset {

    public RealEstate(String name, float quantity, float purchasePrice, Date purchaseDate, String choice) {
        super(name, "", quantity, purchasePrice, purchaseDate);
        this.investType = chooseType(choice);
        super.setAssetType(this.investType+ " RealEstate");
    }

    private String chooseType(String choice) {
        if (choice.equals("1")) {
            return "Investment";
        } else {
            return "Owned";
        }
    }
    public void setType() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("is this RealEstate: 1)Investment          2)Owned ");
        String choice3 = scanner.nextLine();

        this.investType=chooseType(choice3) ;
        super.setAssetType(this.investType+ " RealEstate");
    }
    @Override
    public String toString() {
                return "[RealEstate - " + investType+ "] " + super.toString();
    }


}
