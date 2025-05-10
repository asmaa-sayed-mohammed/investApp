import java.util.Date;
class Stock extends Asset {
    public Stock(String name, float quantity, float purchasePrice, Date purchaseDate) {
        super(name, "Stock", quantity, purchasePrice, purchaseDate);
    }

    public String toString() {
        return "[Stock] " + super.toString();
    }
}
