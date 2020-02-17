package com.thrive.pageModels;

import com.thrive.logger.LoggerConfig;
import com.thrive.modules.Action;
import com.thrive.reportSetup.ExtentReports;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class mini_cart {
    private ArrayList<String> prod_names;
    private ArrayList<String> mini_ops_selected;

    @FindBy(xpath = "//div[@data-block='minicart']/a")
    private WebElement mini_cart;
    @FindBy(xpath = "//div[@class='minicart-items-wrapper']/ol/li")
    private List<WebElement> mini_prods;
    @FindBy(xpath = "//div[@class='minicart-items-wrapper']/ol/li //strong/a")
    private WebElement mini_prod_name;

    public mini_cart(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /*
     * Clicks on Mini cart icon
     */
    public void clk_mini_cart() {
        Action.moveClick(mini_cart);
    }

    /*
     * Verify's the Product details added to cart
     */
    public void verify_prods() {
        // Setting up Extent Child
        ExtentReports.setChildTest("Product details in Cart");

        // Verify the Product name
        Assert.assertEquals(product_page.getProduct_name(), mini_prod_name.getText());
        ExtentReports.getChildTest().pass("Product Name matched");

        // Iterate through each options and verify
        Iterator<WebElement> prods = mini_prods.iterator();
        while (prods.hasNext()) {
            WebElement element = prods.next();
            // Click on See Details dropdown
            Action.moveClick(prods.next().findElement(By.xpath("//div/span")));
            for (WebElement webElement : element.findElements(By.xpath("//dl/dd"))) {
                mini_ops_selected.add(webElement.getText());
            }
            // Verify the Product price
            Assert.assertEquals(product_page.getProd_price(), prods.next().findElement(By.xpath("//span[@class='price" +
                    "']")).getText());
            // Verify all the options selected, Seller Name and Product Condition
            Assert.assertEquals(product_page.getOps_select(), mini_ops_selected);
            ExtentReports.getChildTest().pass("The Product details added to Cart are correct");
            LoggerConfig.getLogger().info("The Product details added to Cart are correct");
        }
    }

}