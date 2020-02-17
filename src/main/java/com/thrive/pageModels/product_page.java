package com.thrive.pageModels;

import com.thrive.browserSetup.ProjectSetup;
import com.thrive.logger.LoggerConfig;
import com.thrive.modules.Action;
import com.thrive.modules.RandomPicker;
import com.thrive.modules.WaitUntil;
import com.thrive.reportSetup.ExtentReports;
import com.thrive.utils.ExcelUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class product_page extends ProjectSetup {
    private static String product_name;
    private static ArrayList<String> ops_select;
    private static ArrayList<String> option_label;
    private static String prod_price;
    private JavascriptExecutor js = (JavascriptExecutor) driver;

    @FindBy(xpath = "//h1/span")
    private WebElement prod_name;
    @FindBy(xpath = "//div[@role='listbox']")
    private List<WebElement> swatches;
    @FindBy(xpath = "//div[@data-role='swatch-options']/div")
    private List<WebElement> selected_ops;
    @FindBy(xpath = "//div[@class='actions'] //button[@id='product-addtocart-button']")
    private WebElement cart_but;
    @FindBy(xpath = "//div[@class='offer-price price-container']")
    private WebElement check_offer;
    @FindBy(xpath = "//div[@class='product-info-price'] //span[@class='price']")
    private WebElement no_offer_price;
    @FindBy(xpath = "//div[@class='product-info-price']/div/div/span/span")
    private WebElement offer_price;
    @FindBy(css = "//div[@class='product info detailed']")
    private WebElement sold_head;
    @FindBy(xpath = "//div[@id='product-offers']")
    private WebElement offers_block;
    @FindBy(xpath = "//table[@id='product-offers-list'] //tbody/tr")
    private List<WebElement> offer_rows;
    @FindBy(xpath = "//div[@class='page messages']")
    private WebElement page_msgs;
    @FindBy(id = "minicart-content-wrapper")
    private WebElement mini_wrapper;

    public product_page(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String get_prod_name() {
        WaitUntil.waitVisibleAll(5, swatches);
        product_name = prod_name.getText();
        return product_name;
    }

    /*
     * Selects the Config Product options
     */
    public void clk_swatches() {
        option_label = new ArrayList<>(4);

        // Set the Extent Child
        ExtentReports.setChildTest("Select the Product Swatches");

        try {
            // Loops through the Swatches and Clicks on random option if present
            WaitUntil.waitVisibleAll(5, swatches);
            System.out.println(swatches.size());
            if (!swatches.isEmpty()) {
                // Iterate through the swatches and click
//            for(int ops=0;ops<swatches.size();ops++) {
                for (WebElement element : swatches) {
                    List<WebElement> config = element.findElements(By.xpath("//div[@role='option']"));
                    int rand_num = RandomPicker.random_num(config.size());
//                    WebElement rand_ops = config.get(rand_num);
//                    WebElement rand_ops = config.get(config.get(0));
                    js.executeScript("arguments[0].scrollIntoView();", config.get(0));
//                    option_label.add(rand_ops.getAttribute("option-label"));
                    option_label.add(config.get(0).getAttribute("option-label"));
//                    Action.moveClick(rand_ops);
                    Action.moveClick(config.get(0));
                }
                ExtentReports.getChildTest().pass("Swatch(es) Selected");
                LoggerConfig.getLogger().info("Swatch(es) Selected");
//            }
                // Verifying the selected options are displayed
                Iterator<WebElement> element = swatches.iterator();

                // Fetching the Config option values selected
                find_select_ops();
                int count = 0;
                while (element.hasNext()) {
                    Assert.assertTrue(getOps_select().get(count++).contains(element.next().getText()));
                }
                ExtentReports.getChildTest().pass("The Selected options are displayed above are Correct");
                LoggerConfig.getLogger().info("The Selected options are displayed above are Correct");
            }
            fetch_prod_details();
//            set_prod_price();
//            clk_add_cart();
            System.out.println("Clicked");
        } catch (Exception e) {
            LoggerConfig.getLogger().error(e.getMessage());
            e.printStackTrace();
        }
    }

    /*
     * Fetch the Seller name and Product Condition and adds it to same
     * ArrayList<String> ops_select;
     */
    private void fetch_prod_details() throws InterruptedException {

        ExtentReports.setChildTest("Fetch Seller Shop and Product Condition");

        // Enters only if Offers Block is displayed
        js.executeScript("arguments[0].scrollIntoView();", offers_block);
        if (offers_block.isDisplayed()) {
            System.out.println(offer_rows.size());
            int value = 1;
            Thread.sleep(1000);
            List<WebElement> rows = offer_rows;
//            while(rows.hasNext()){
            for (int row = 0; row < rows.size(); row++) {
//                WebElement ele = rows.next();
                System.out.println("Rows -> " + value++);
//                System.out.println(rows.get(row).getText());
                ArrayList<String> rowString = new ArrayList<>();
//            for (WebElement ele : offer_rows) {
                //Iterate through the columns of the Seller Offers and fetch the data if its displayed
                System.out.println("Status -> " + rows.get(row).getAttribute("style").equals("display: table-row;"));
                if (rows.get(row).getAttribute("style").equals("display: table-row;")) {
                    System.out.println("SKU -> " + rows.get(row).getAttribute("class"));
                    // Iterate through the columns and fetch the Seller Shop name and Condition

//                    for (int value = prod_data.size(); value > 0; value--) {
                    System.out.println("Condition -> "+rows.get(row).findElement(By.xpath("//td[@data-th='Condition'] //div[@class='offer-state']")).getText());
                    System.out.println("Seller -> "+rows.get(row).findElement(By.xpath("//td[@data-th='Seller'] //a")).getText());
//                    rowString.add(rows.get(row).findElement(By.xpath("//td[@data-th='Condition'] //div[@class='offer-state']")).getText());
//                    rowString.add(rows.get(row).findElement(By.xpath("//td[@data-th='Seller'] //a")).getText());
//                    System.out.println("Ops_select->"+getOps_select());
                    System.out.println("Ops_select->" + rowString);
                } else {
                    System.out.println("Else Part -> " + rows.get(row).getAttribute("style"));
                }
                ops_select.addAll(rowString);
                rowString.clear();
            }
        }
        System.out.println("fetch_prod_details -> " + ops_select);
        LoggerConfig.getLogger().info(ops_select);
        // Update in the Extent Reports and Log
        ExtentReports.getChildTest().pass("Seller name and Product Condition fetched");
        LoggerConfig.getLogger().info("Seller name and Product Condition fetched");
    }

    /*
     * Clicks on Add to cart button
     */
    public void clk_add_cart() {

        ExtentReports.setChildTest("Click Add to Cart");
        // Clicks on Add to Cart button
        LoggerConfig.getLogger().info("Clicked on Add to Cart Button");
        if (offers_block.isDisplayed()) {
            for (WebElement ele : offer_rows) {
                if (!ele.getAttribute("style").contains("none")) {
//                    Action.moveClick(ele.findElement(By.xpath("//td[@data-th='Action']/button")));
                    js.executeScript("arguments[0].click();", ele.findElement(By.xpath("//td[@data-th='Action']/button")));
                }
            }
        } else {
            Action.moveClick(cart_but);
        }
        ExtentReports.getChildTest().pass("Clicked on Add to Cart Button");
        LoggerConfig.getLogger().info("Clicked on Add to Cart Button");
    }

    /*
     * Confirms if the Product added to Cart message is displayed or not
     */
    public void cart_confirmation() {
        // Wait till Confirmation message is displayed and Verify
        WaitUntil.waitVisible(5, page_msgs);
        Assert.assertEquals("You added " + getProduct_name() + " to your shopping cart.",
                page_msgs.findElement(By.xpath("//div[@role='alert']/div/div")).getText());
        ExtentReports.getChildTest().pass("The Product added to Cart Successfully");
        LoggerConfig.getLogger().info("The Product added to Cart Successfully");
    }

    /*
     * Fetch the Config Options selected and add to ArrayList<String>
     */
    private void find_select_ops() {
        // Set ArrayList<String> size to Number of options
        ops_select = new ArrayList<>(selected_ops.size());

        // Iterate and fetches the Options/Swatches selected
        // add to ArrayList<String>
        for (WebElement selected_op : selected_ops) {
            ops_select.add(selected_op.findElement(By.xpath("//span[@class='swatch-attribute-selected-option']")).getText());
        }
        LoggerConfig.getLogger().info("Fetched the Config Options");
    }

    /*
     * Fetch the Product Price and sets it
     */
    private void set_prod_price() {
        if (check_offer.isDisplayed()) {
            prod_price = offer_price.getText();
        } else {
            prod_price = no_offer_price.getText();
        }
    }

    /*
     * Checks if the Cart empty text is displayed or not
     */
    public void cart_isEmpty() {
        String empty_text = mini_wrapper.findElement(By.xpath("//strong[@class='subtitle empty']")).getText();
        Assert.assertEquals(ExcelUtils.getData("product").get("empty_cart"), empty_text);
        ExtentReports.getChildTest().pass("The cart empty text is displayed");
        LoggerConfig.getLogger().info("The cart empty text is displayed");
    }

    /*
     * Returns the Product options selected
     */
    static ArrayList<String> getOps_select() {
        return ops_select;
    }

    /*
     * Returns the option labels when clicking on them
     */
    static ArrayList<String> getOption_label() {
        return option_label;
    }

    /*
     * Returns Product Price fetched
     */
    static String getProd_price() {
        return prod_price;
    }

    /*
     * Returns the Product name fetched
     */
    static String getProduct_name() {
        return product_name;
    }

}
