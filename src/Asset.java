// Source code is decompiled from a .class file using FernFlower decompiler.
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Asset implements Serializable {
    private String assetName;
    private String assetType;
    private float quantity;
    private Date purchaseDate;
    private float purchasePrice;

    public Asset(String assetName, String assetType, float quantity, float purchasePrice, Date purchaseDate) {
        this.assetName = assetName;
        this.assetType = assetType;
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
        this.purchasePrice = purchasePrice;
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

    public String toString() {
        String var10000 = this.assetName;
        return "Asset Name: " + var10000 + ", Type: " + this.assetType + ", Quantity: " + this.quantity + ", Purchase Price: " + this.purchasePrice + ", Purchase Date: " + (new SimpleDateFormat("yyyy-MM-dd")).format(this.purchaseDate);
    }
}
