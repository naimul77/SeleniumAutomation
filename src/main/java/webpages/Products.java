package webpages;

import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.By;

import automation.Product;

public class Products extends Base {
    /* Private Data Fields */
    private ArrayList<Product> productList;
    private int totalProducts;

    /* Overloaded Constructors */
    public Products(Base base, Properties prop) { super(base, prop); }
    public Products() { this(0, null); }
    public Products(int totalProducts) { this(totalProducts,null); }
    public Products(int totalProducts, ArrayList<Product> productList) {
        this.totalProducts = totalProducts;
        this.productList = productList;
    }

    /* Getter-Setter Methods */
    public ArrayList<Product> getProductList() { return productList; }
    public void setProductList(ArrayList<Product> productList) { this.productList = productList; }
    public int getTotalProducts() { return totalProducts; }
    public void setTotalProducts(int totalProducts) { this.totalProducts = totalProducts; }

    /* Non-Inherited methods */
    public void allListings() { this.driver.findElement(By.xpath(prop.getProperty("allListings"))).click(); }
    public void acceptOffers() { this.driver.findElement(By.xpath(prop.getProperty("acceptOffers"))).click(); }
    public void auction() { this.driver.findElement(By.xpath(prop.getProperty("auction"))).click(); }
    public void buyItNow() { this.driver.findElement(By.xpath(prop.getProperty("buyItNow"))).click(); }
    public void checkProductDescription() { this.driver.findElement(By.xpath(prop.getProperty("productDescriptionCheck"))).click(); }
    public void checkProductPrice() { this.driver.findElement(By.xpath(prop.getProperty("productPriceCheck"))).click(); }

    /* Add product information to product list according to search results in specific website */
    public void retrieveProduct(String productName) {

        productList.add(
                new Product(
                    productName,
                    this.driver.findElement(By.xpath(prop.getProperty("productDescription"))).getText(),
                    Double.parseDouble(this.driver.findElement(By.xpath(prop.getProperty("productPrice"))).getText()),
                    this.driver.findElement(By.xpath(prop.getProperty("productCondition"))).getText()
                )
        );
    }
}
