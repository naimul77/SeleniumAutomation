package automation;

import java.util.Properties;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import webpages.Base;
import webpages.Homepage;
import webpages.AdvancedSearch;
import webpages.Products;

public class Automation {

    protected static Properties prop = new Properties();
    protected static Base base;

    public static void main(String[] args) {

        String filename = "/Users/naimul7/JavaProjects/SeleniumAutomation/src/main/java/automation/config.properties";
        try{
            InputStream input = new FileInputStream(filename);
            prop.load(input);
        } catch(FileNotFoundException fnfex) { fnfex.printStackTrace();
        } catch(IOException ioex) { ioex.printStackTrace(); }

        base = new Base("https://" + prop.getProperty("website"), prop);
    }

    public static void WritePropertiesFile() {
        /* Configure this method to create or update properties inside the config.properties file */

        //
    }

    public static void ReadPropertiesFile() {
       /* Configure this method to read values of various properties by their key */
    }

    public static void automateHomepage() {
        Homepage homepage = new Homepage(base);

        /* Test each Tab for the Homepage Navbar */
        homepage.navigateHometabs();

        /* Test Search Function in webpage with / without category */
        
    }

    public static void navigateHometabs(Homepage homepage) {
        /* If tabIndex is less than 0 or more than the number of tabs on the homepage of the website*/
        for(int tabIndex = 0; tabIndex < Integer.parseInt(prop.getProperty("ebayHometabFunctioning")); tabIndex++)
            homepage.navigateHometab(tabIndex).click();
    }

    public static void search(Homepage homepage, String keyword) {
        /* Run Search test for Keyword without Category Selection */
        homepage.search("Pokemon Brilliant Diamond");
        /* Return to homepage after completion of successful Serach */
        homepage.getDriver().navigate().back();

        /* Run Search test for Keyword with Category Selection */

        homepage.search("iPhone", "random");
    }
}
