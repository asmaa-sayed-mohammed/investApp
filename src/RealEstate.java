import java.util.Date;

public class RealEstate extends Asset {
    private String type; // Owned or Investment

    public RealEstate(String name, float quantity, float purchasePrice, Date purchaseDate, String choice) {
        super(name, "", quantity, purchasePrice, purchaseDate);
        this.type = chooseType(choice);
        super.setAssetType(this.type + " RealEstate");
    }

    private String chooseType(String choice) {
        if (choice.equals("1")) {
            return "Investment";
        } else {
            return " Owned ";
        }
    }

    @Override
    public String toString() {
        return "[RealEstate - " + type + "] " + super.toString();
    }

    public String getType() {
        return this.type;
    }
}
