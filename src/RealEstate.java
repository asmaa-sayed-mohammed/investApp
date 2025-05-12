import java.util.Date;
import java.util.Scanner;
/**
 * The {@code RealEstate} class represents a real estate asset, extending the {@link Asset} class.
 * It distinguishes between investment and owned properties and formats their data accordingly.
 */
public class RealEstate extends Asset {
    /**
     * Constructs a new {@code RealEstate} object with the provided property details.
     *
     * @param name          The name or description of the real estate asset.
     * @param quantity      The quantity of the asset (e.g., number of units or area).
     * @param purchasePrice The purchase price of the asset.
     * @param purchaseDate  The date the asset was purchased.
     * @param choice        A string indicating the ownership type: "1" for Investment, any other value for Owned.
     */
    public RealEstate(String name, float quantity, float purchasePrice, Date purchaseDate, String choice) {
        super(name, "", quantity, purchasePrice, purchaseDate);
        this.investType = chooseType(choice);
        super.setAssetType(this.investType+ " RealEstate");
    }
    /**
     * Determines the ownership type of the real estate asset based on the user's input.
     *
     * @param choice User input: "1" for Investment, any other value for Owned.
     * @return A string representing the ownership type.
     */

    private String chooseType(String choice) {
        if (choice.equals("1")) {
            return "Investment";
        } else {
            return "Owned";
        }
    }
    /**
     * Allows the user to update the ownership type interactively,
     * and updates the asset type accordingly.
     */
    public void setType() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("is this RealEstate: 1)Investment          2)Owned ");
        String choice3 = scanner.nextLine();

        this.investType=chooseType(choice3) ;
        super.setAssetType(this.investType+ " RealEstate");
    }
    /**
     * Returns a string representation of the real estate asset,
     * including ownership type and inherited asset details.
     *
     * @return A descriptive string of the real estate asset.
     */
    @Override
    public String toString() {
                return "[RealEstate - " + investType+ "] " + super.toString();
    }


}
