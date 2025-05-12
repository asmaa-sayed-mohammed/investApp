import java.util.Date;
import java.util.Scanner;
public class Gold  extends Asset {


        public Gold(String name, float quantity, float purchasePrice, Date purchaseDate, String choice , double amount) {
            super(name, "", quantity, purchasePrice, purchaseDate);
            this.investType = chooseType(choice);
            super.setAssetType(this.investType + " Gold");
            super.setAssetamount(amount);
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
            System.out.print("is this Gold: 1)Investment          2)Owned ");
            String choice3 = scanner.nextLine();
            this.investType = chooseType(choice3) ;
            super.setAssetType(this.investType + " Gold");
        }
        @Override
        public String toString() {
            return "[Gold - " + investType + "] " + super.toString();
        }




}
