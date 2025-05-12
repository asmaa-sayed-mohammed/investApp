import java.util.Date;
/**
 * The {@code Stock} class represents a stock asset and extends the {@link Asset} class.
 * It sets the asset type to "Stock" and reuses the base asset structure.
 *
 * <p>This class is used to model investment assets specifically categorized as stocks.</p>
 */
class Stock extends Asset {
    /**
     * Constructs a {@code Stock} object with the specified attributes.
     *
     * @param name          The name of the stock.
     * @param quantity      The number of stock units purchased.
     * @param purchasePrice The price per unit at the time of purchase.
     * @param purchaseDate  The date the stock was purchased.
     */
    public Stock(String name, float quantity, float purchasePrice, Date purchaseDate) {
        super(name, "Stock", quantity, purchasePrice, purchaseDate);
    }
    /**
     * Returns a string representation of the stock asset,
     * including its base attributes.
     *
     * @return A formatted string describing the stock asset.
     */
    public String toString() {
        return "[Stock] " + super.toString();
    }
}
