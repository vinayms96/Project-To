package com.thrive.pageModels;

import com.thrive.browserSetup.ProjectSetup;
import com.thrive.logger.LoggerConfig;
import com.thrive.modules.Action;
import com.thrive.reportSetup.ExtentReports;
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
import java.util.Set;

public class home_page extends ProjectSetup {
    private static ArrayList<String> menus;

    @FindBy(xpath = "//div[@class='header content']/div[2]/a")
    private WebElement menu;
    @FindBy(xpath = "//ul[@class=\"header links\"]/li[2]/a")
    private WebElement login;
    @FindBy(xpath = "//div[@id='store.menu']/nav/div/div/div/div/div")
    private List<WebElement> menuLinks;
    @FindBy(xpath = "//div[@class='breadcrumbs']/ul/li[2]/strong")
    private WebElement breadcrumbs;
    @FindBy(xpath = "//meta[@name='title']")
    private WebElement pageTitle;

    /*
     * Page model Constructor
     */
    public home_page(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public static ArrayList<String> getMenus() {
        return menus;
    }

    /*
     * Click on Login link in the Header
     */
    public void click_login_link() {
        // Setting the extent child
        ExtentReports.setChildTest("Click LoginLink");

        // Clicking on the sign in link
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", menu);

        // Logger and Extent report
        LoggerConfig.getLogger().info("Login link is clicked in Homepage");
        ExtentReports.getChildTest().info("Login link is clicked in Homepage");
    }

    /*
     * Click on Menu links and check if they are redirecting to correct pages
     */
    public void check_menu_links() {

        // Declaring ArrayList
        menus = new ArrayList<>();

        // Setting the extent child
        ExtentReports.setChildTest("Check All the Menu Links");

        // Open all Menu links in different Tabs
        Iterator<WebElement> links = menuLinks.iterator();
        while (links.hasNext()) {
            WebElement element = links.next();
            System.out.println("Check-> " + element.findElement(By.tagName("a")).getAttribute("href"));
//            String url = element.getAttribute("href");
            menus.add(element.findElement(By.tagName("span")).getText());
//            if (LinkStatusCode.connection(url) == 200) {
            Action.clickOpenTab(element);
            LoggerConfig.getLogger().info("Link: " + element.findElement(By.tagName("a")).getAttribute("href") + " is opened");
//            } else {
//                System.out.println("Link is Corrupted");
//                ExtentReports.getChildTest().fail("Link: " + element.getAttribute("href") + " -> is Corrupted");
//            }
        }
        // Switch to all the tabs and verify the Pages
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> wind = windows.iterator();
        String parent = wind.next();
        int count = menus.size();
        while (wind.hasNext()) {
            try {
                // Switches to next window
                driver.switchTo().window(wind.next());
                Assert.assertEquals(pageTitle.getAttribute("content"), menus.get(count - 1));

                // Result printed to Extent reports and logged
                ExtentReports.getChildTest().pass("Menu link is redirected to " + menus.get(count - 1) + " page");
                LoggerConfig.getLogger().info("Menu link is redirected to " + menus.get(count - 1) + " page");

                // Closes each of the open tabs
                driver.close();
                count--;
            }catch (Exception e){
                ExtentReports.getChildTest().error(e.getLocalizedMessage());
                LoggerConfig.getLogger().error(e.getLocalizedMessage());
                count--;
            }
        }
        // Switches back to the Parent tab
        driver.switchTo().window(parent);
        LoggerConfig.getLogger().info("Control switched back to Parent tab");

    }

}