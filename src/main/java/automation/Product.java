package automation;

public class Product {

    private String productName;
    private String productDescription;
    private String condition;
    private double purchasePrice;
    private double openingBid;
    private double currentBid;
    private double closingBid;

    public Product() {
        this("", "", 0.0, "");
    }

    public Product(String productName, String productDescription, double purchasePrice, String condition) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.purchasePrice = purchasePrice;
        this.condition = condition;
    }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public String getProductDescription() { return productDescription; }
    public void setProductDescription(String productDescription) { this.productDescription = productDescription; }
    public double getPurchasePrice() { return purchasePrice; }
    public void setPurchasePrice(double purchasePrice) { this.purchasePrice = purchasePrice; }
    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }
    public double getOpeningBid() { return openingBid; }
    public void setOpeningBid(double openingBid) { this.openingBid = openingBid; }
    public double getCurrentBid() { return currentBid; }
    public void setCurrentBid(double currentBid) { this.currentBid = currentBid; }
    public double getClosingBid() { return closingBid; }
    public void setClosingBid(double closingBid) { this.closingBid = closingBid; }

    @Override
    public String toString() {
        return "\nProduct Name: " + this.productName
                + "\nProduct Description: " + this.productDescription
                + "\nCondition: " + this.condition
                + "\nPurchase Price: " + this.purchasePrice
                + "\nOpening Bid: " + this.openingBid
                + "\nCurrent Bid: " + this.currentBid;
    }

    public boolean bid(double bid) {
        if(bid < this.openingBid)
            return false;

        if(bid <= 0.0)
            return false;

        return (this.currentBid = bid) != 0;
    }
}
