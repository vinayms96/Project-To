package com.thrive.pageModels;

import com.thrive.logger.LoggerConfig;
import com.thrive.modules.Action;
import com.thrive.modules.RandomPicker;
import com.thrive.modules.WaitUntil;
import com.thrive.reportSetup.ExtentReports;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Iterator;
import java.util.List;

public class listing_page {

    // Page Elements
    @FindBy(xpath = "//div[@id='ninjamenus4']/div/div")
    private List<WebElement> menus2;
    @FindBy(xpath = "//div[@id='store.menu']/nav/div/div/div/div/div")
    private List<WebElement> menus1;
    @FindBy(xpath = "//div[@id='amasty-shopby-product-list'] //ol/li")
    private List<WebElement> products_list;

    // Page Model Constructor
    public listing_page(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void landing_page(String landing) {
        Iterator<WebElement> main_menu = menus1.iterator();
        while (main_menu.hasNext()) {
            WebElement element = main_menu.next();
            System.out.println(landing);
            if (element.findElement(By.tagName("span")).getText().equalsIgnoreCase(landing)) {
                Action.moveClick(element.findElement(By.tagName("a")));
                break;
            }
        }
    }

    public void listing_page(String listing) {
        Iterator<WebElement> sec_menu = menus2.iterator();
        while (sec_menu.hasNext()) {
            WebElement element = sec_menu.next();
            if (element.findElement(By.tagName("span")).getText().contains(listing)) {
                Action.moveClick(element.findElement(By.tagName("a")));
                break;
            }
        }
    }

    public void choose_product(String prod_name) {
        Iterator<WebElement> products = products_list.iterator();
        while (products.hasNext()) {
            WebElement element = products.next();
            if (element.findElement(By.xpath("//strong/a")).getText().contains(prod_name)) {
                Action.moveClick(element.findElement(By.xpath("//strong/a")));
                break;
            }
        }
    }

    /*
     * Click on random product
     * Returns Product Name from Listing page
     */
    public String rand_prod() {

        // Setting Extent Child
        ExtentReports.setChildTest("Selecting a Random Product");

        WaitUntil.waitVisibleAll(5, products_list);

        // Getting the Product name and Click on random product
        WebElement ele = products_list.get(RandomPicker.random_prod(products_list.size()));
        String prod_name = ele.findElement(By.xpath("//strong/a")).getText();
        Action.moveClick(ele.findElement(By.xpath("//strong/a")));
        LoggerConfig.getLogger().info("Clicked on '" + prod_name + "'");

        ExtentReports.getChildTest().pass("Product name is returned");
        LoggerConfig.getLogger().info("Product name is returned");
        return prod_name;
    }

}
