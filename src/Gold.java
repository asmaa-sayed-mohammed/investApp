import java.util.Date;
import java.util.Scanner;
/**
 * The {@code Gold} class represents a gold asset, which is a specific type of {@link Asset}.
 * It distinguishes between investment and owned gold, and sets the appropriate type and amount.
 */
public class Gold  extends Asset {

    /**
     * Constructs a new {@code Gold} object with the specified details.
     *
     * @param name          The name of the gold asset.
     * @param quantity      The quantity of gold.
     * @param purchasePrice The purchase price per unit of gold.
     * @param purchaseDate  The date the gold was purchased.
     * @param choice        The type of gold: "1" for Investment, otherwise Owned.
     * @param amount        The total monetary amount of the gold.
     */
        public Gold(String name, float quantity, float purchasePrice, Date purchaseDate, String choice , double amount) {
            super(name, "", quantity, purchasePrice, purchaseDate);
            this.investType = chooseType(choice);
            super.setAssetType(this.investType + " Gold");
            super.setAssetamount(amount);
        }

    /**
     * Determines the investment type of the gold based on the user's choice.
     *
     * @param choice A string input ("1" for Investment, any other value for Owned).
     * @return A string representing the investment type ("Investment" or "Owned").
     */

        private String chooseType(String choice) {
            if (choice.equals("1")) {
                return "Investment";
            } else {
                return "Owned";
            }
        }
    /**
     * Prompts the user to update the investment type of the gold asset,
     * and sets the asset type accordingly.
     */
        public void setType() {
             Scanner scanner = new Scanner(System.in);
            System.out.print("is this Gold: 1)Investment          2)Owned ");
            String choice3 = scanner.nextLine();
            this.investType = chooseType(choice3) ;
            super.setAssetType(this.investType + " Gold");
        }
    /**
     * Returns a string representation of the gold asset, including its investment type
     * and inherited asset details.
     *
     * @return A string describing the gold asset.
     */
        @Override
        public String toString() {
            return "[Gold - " + investType + "] " + super.toString();
        }




}
