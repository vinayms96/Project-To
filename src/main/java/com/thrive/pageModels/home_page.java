package com.thrive.pageModels;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.thrive.modules.LinkStatusCode;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.thrive.browserSetup.ProjectSetup;
import com.thrive.modules.Action;
import com.thrive.reportSetup.ExtentReports;

public class home_page extends ProjectSetup {

    @FindBy(xpath = "//div[@class='header content']/div[2]/a")
    private WebElement menu;
    @FindBy(xpath = "//ul[@class=\"header links\"]/li[2]/a")
    private WebElement login;
    @FindBy(xpath = "//div[@id='store.menu']/nav/div/div/div")
    private List<WebElement> menuLinks;
    @FindBy(xpath = "//div[@class='breadcrumbs']/ul/li[2]/strong")
    private WebElement breadcrumbs;
    @FindBy(xpath = "//div[@class='page-title-wrapper']/h1/span")
    private WebElement pageTitle;

    public home_page(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /*
     * Click on Login link in the Header
     */
    public void clickLoginLink() throws Exception {
        ExtentReports.setChildTest("Click LoginLink");

//		actions.moveClick(menu);
        System.out.println("menu=" + menu);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", menu);

        ExtentReports.getChildTest().info("Login link is clicked in Homepage");
    }

    /*
     * Click on Menu links and check if they are redirecting to correct pages
     */
    public void checkMenuLinks() {

        // Created a child node
        ExtentReports.setChildTest("Check All the Menu Links");

        // Open all Menu links in different Tabs
        Iterator<WebElement> links = menuLinks.iterator();
        while (links.hasNext()) {
            WebElement element = links.next().findElement(By.tagName("a"));
            System.out.println("Check-> " + element.getAttribute("href"));
            String url = element.getAttribute("href");
//            if (LinkStatusCode.connection(url) == 200) {
                Action.clickOpenTab(element);
//            } else {
//                System.out.println("Link is Corrupted");
//                ExtentReports.getChildTest().fail("Link: " + element.getAttribute("href") + " -> is Corrupted");
//            }
        }
        // Switch to all the tabs and verify the Pages
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> wind = windows.iterator();
        String parent = wind.next();
        while (wind.hasNext()) {
            driver.switchTo().window(wind.next());
            String breadName = breadcrumbs.getText();
            Assert.assertEquals(pageTitle.getText(), breadName);
            ExtentReports.getChildTest().pass("Menu link is redirected to " + breadName + " page");

            driver.close();
        }
        driver.switchTo().window(parent);

    }

}