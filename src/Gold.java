import java.util.Date;

public class Gold  extends Asset {
    private String type; // Owned or Investment

    public Gold(String name, float quantity, float purchasePrice, Date purchaseDate, String choice , double amount) {
        super(name, "", quantity, purchasePrice, purchaseDate);
        this.type = chooseType(choice);
        super.setAssetType(this.type + " Gold");
        super.setAssetamount(amount);
    }

    private String chooseType(String choice) {
        if (choice.equals("1")) {
            return "Investment";
        } else {
            return "Owned";
        }
    }

    @Override
    public String toString() {
        return "[Gold - " + type + "] " + super.toString();
    }




}
