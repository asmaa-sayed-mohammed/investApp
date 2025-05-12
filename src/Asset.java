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

    /** @return The name of the asset. */
    public String getAssetName() {
        return this.assetName;
    }

    /** @return The quantity of the asset. */
    public float getQuantity() {
        return this.quantity;
    }

    /** @return The date the asset was purchased. */
    public Date getPurchaseDate() {
        return this.purchaseDate;
    }

    /** @return The price at which the asset was purchased. */
    public float getPurchasePrice() {
        return this.purchasePrice;
    }

    /**
     * Sets the quantity of the asset.
     *
     * @param quantity The new quantity.
     */
    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    /**
     * Sets the purchase price of the asset.
     *
     * @param purchasePrice The new purchase price.
     */
    public void setPurchasePrice(float purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    /**
     * Sets the name of the asset.
     *
     * @param assetName The new asset name.
     */
    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    /**
     * Sets the purchase date of the asset.
     *
     * @param purchaseDate The new purchase date.
     */
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    /**
     * Sets the type of the asset.
     *
     * @param assetType The new asset type (e.g., stock, gold).
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    /** @return The type of the asset. */
    public String getAssetType() {
        return this.assetType;
    }

    /** @return The total amount/value of the asset. */
    public double getAssetamount() {
        return this.amount;
    }

    /**
     * Sets the total amount/value of the asset.
     *
     * @param amount The total calculated asset value.
     */
    public void setAssetamount(double amount) {
        this.amount = amount;
    }

    /** @return The investment type label of the asset. */
    public String getInvestType() {
        return this.investType;
    }

    /**
     * Returns a string representation of the asset including its key details.
     *
     * @return A string containing asset details.
     */
    @Override
    public String toString() {
        return "Asset Name: " + this.assetName +
                ", Type: " + this.assetType +
                ", Quantity: " + this.quantity +
                ", Purchase Price: " + this.purchasePrice +
                ", Purchase Date: " + new SimpleDateFormat("yyyy-MM-dd").format(this.purchaseDate) +
                ", Asset Amount: " + this.amount +
                ", Asset Investment type: " + this.investType;
    }
}
