package modules;

import java.util.concurrent.TimeUnit;

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
	  	  
	  extentReports.attRepo(browser);
	  
		if(browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			if(Property.getProperty("head").equalsIgnoreCase("false")) {
				driver = new ChromeDriver();
			}else {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				driver = new ChromeDriver(options);
			}
		}else if(browser.equalsIgnoreCase("Firefox")) {
			driver = null;
			WebDriverManager.firefoxdriver().setup();
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
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  }

  @AfterTest(description = "Terminating the browser instance and reports")
  public void afterTest() {
	  extentReports.extent.flush();
	  driver.close();
  }

}
