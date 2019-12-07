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
	private static WebDriver driver;
	
  @BeforeTest(description = "Checking the browser and launching it")
  @Parameters({"browser"})
  public void beforeTest(String browser) {
	  	  
	  extentReports.attRepo(browser);
	  
		if(browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			if(Property.getProperty("head").equalsIgnoreCase("false")) {
				setDriver(new ChromeDriver());
			}else {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				setDriver(new ChromeDriver(options));
			}
		}else if(browser.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			if(Property.getProperty("head").equalsIgnoreCase("false")) {
				setDriver(new FirefoxDriver());
			}else {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless");
				setDriver(new FirefoxDriver(options));
			}
		}
		getDriver().manage().window().maximize();
		getDriver().get(url);
		getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  }

  @AfterTest(description = "Terminating the browser instance and reports")
  public void afterTest() {
	  extentReports.extent.flush();
	  getDriver().close();
  }

public static WebDriver getDriver() {
	return openBrowser.driver;
}

public static void setDriver(WebDriver driver) {
	openBrowser.driver = driver;
}

}
