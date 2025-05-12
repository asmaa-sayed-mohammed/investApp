// Source code is decompiled from a .class file using FernFlower decompiler.
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Represents a financial asset such as stocks, real estate, or gold.
 * This class is responsible for storing and managing the asset's details
 * including name, type, quantity, purchase price and date, and total amount.
 * It implements {@link Serializable} to allow object persistence.
 */
public class Asset implements Serializable {

    private String assetName;
    private String assetType;
    private float quantity;
    private Date purchaseDate;
    private float purchasePrice;
    private double amount ;
    public String investType;
    /**
     * Constructs an {@code Asset} object with specified details.
     *
     * @param assetName      The name of the asset.
     * @param assetType      The type of the asset (e.g., stock, real estate, gold).
     * @param quantity       The quantity of the asset.
     * @param purchasePrice  The purchase price per unit of the asset.
     * @param purchaseDate   The date the asset was purchased.
     */
    public Asset(String assetName, String assetType, float quantity, float purchasePrice, Date purchaseDate) {
        this.assetName = assetName;
        this.assetType = assetType;
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
        this.purchasePrice = purchasePrice;
        this.amount = 0;
        this.investType="Investment";
    }

    public String getAssetName() {
        return this.assetName;
    }

    public float getQuantity() {
        return this.quantity;
    }

    public Date getPurchaseDate() {
        return this.purchaseDate;
    }

    public float getPurchasePrice() {
        return this.purchasePrice;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public void setPurchasePrice(float purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getAssetType() {
        return this.assetType;
    }
    public double getAssetamount() {

        return this.amount;
    }

    public void setAssetamount(double amount) {
        this.amount = amount;
    }

    public String getInvestType() {
        return this.investType;
    }
    public String toString() {
        String var10000 = this.assetName;
        return "Asset Name: " + var10000 + ", Type: " + this.assetType + ", Quantity: " + this.quantity + ", Purchase Price: " + this.purchasePrice + ", Purchase Date: " + (new SimpleDateFormat("yyyy-MM-dd")).format(this.purchaseDate) + ", Asset Amount: " + this.amount +", Asset Investment type: "+this.investType ;
    }
}
