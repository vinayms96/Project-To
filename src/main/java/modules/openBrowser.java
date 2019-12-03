package modules;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class openBrowser implements auto_constant{
	public static WebDriver driver = null;
	
  @BeforeTest(description = "Checking the browser and launching it")
  @Parameters({"browser"})
  public void beforeTest(String browser) {
	  WebDriverManager.chromedriver().setup();
	  WebDriverManager.firefoxdriver().setup();
	  
	  extentReports.attRepo(browser);
	  
		if(browser.equalsIgnoreCase("Chrome")) {
			if(Property.getProperty("head").equalsIgnoreCase("false")) {
				driver = new ChromeDriver();
			}else {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("headless");
				driver = new ChromeDriver(options);
			}
		}else if(browser.equalsIgnoreCase("Firefox")) {
			if(Property.getProperty("head").equalsIgnoreCase("false")) {
				driver = new FirefoxDriver();
			}else {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("headless");
				driver = new FirefoxDriver(options);
			}
		}
		driver.manage().window().maximize();
		driver.get(url);
  }

  @AfterTest(description = "Terminating the browser instance and reports")
  public void afterTest() {
	  driver.close();
  }

}
