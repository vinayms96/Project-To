package modules;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.Status;

import io.github.bonigarcia.wdm.WebDriverManager;

public class projectSetup implements auto_constant {
	public WebDriver driver;
	public static String extBrowser;
	@BeforeSuite
	public void setEnviron() {
		extentReports.attachReport();
	}
	
	@BeforeClass(description = "Checking the browser and launching it")
	@Parameters({ "browser" })
	public void openBrowser(String browser) {
		projectSetup.extBrowser = browser;

		/*
		 * This assigns the browser driver to use for the extent reports for setting
		 * child node
		 */

		if (browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().arch64().setup();
			if (Property.getProperty("head").equalsIgnoreCase("false")) {
				setDriver(new ChromeDriver());
			} else {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				setDriver(new ChromeDriver(options));
			}
		} else if (browser.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().arch64().setup();
			if (Property.getProperty("head").equalsIgnoreCase("false")) {
				setDriver(new FirefoxDriver());
			} else {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless");
				setDriver(new FirefoxDriver(options));
			}
		}
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println(driver);
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			extentReports.extTest.log(Status.FAIL,
					"Test Case Failed is " + result.getName() + "\n" + result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentReports.extTest.log(Status.SKIP, "Test Case Skipped is " + result.getName());
		}
		extentReports.extent.flush();
	}
	
	@AfterClass
	public void closeBrowser() {
//		extentReports.reporter.stop();
//		extentReports.extent.flush();
		driver.close();
	}
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

}
