package modules;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class openBrowser implements auto_constant {
	public static WebDriver driver;
	public static String browser;
	public static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	static String screenShotPath = screenPath + "/screenshot " + dateFunc.getShotDate();

	@BeforeSuite
	public void setupExtent() {
		if (Property.getProperty("extent").equalsIgnoreCase("on")) {
			extentReports.attRepo();
		}
	}

	@BeforeClass
	public synchronized void setParentTests() {
		if (Property.getProperty("extent").equalsIgnoreCase("on")) {
			ExtentTest parent = extentReports.extent.createTest(getClass().getName());
			parentTest.set(parent);
		}
	}

	@BeforeMethod
	public void setChildTests() {
		if (Property.getProperty("extent").equalsIgnoreCase("on")) {
			ExtentTest child = parentTest.get().createNode(browser);
			test.set(child);
		}
	}

	@BeforeTest(description = "Checking the browser and launching it")
	@Parameters({ "browser" })
	public void beforeTest(String browser) {
		openBrowser.browser = browser;

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
	}

	@AfterTest(description = "Terminating the browser instance and reports")
	public void afterTest() {
		if (Property.getProperty("extent").equalsIgnoreCase("on")) {
			extentReports.extent.flush();
		}
		driver.close();
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		if (Property.getProperty("extent").equalsIgnoreCase("on")) {
			if (result.getStatus() == ITestResult.FAILURE)
				test.get().fail(result.getThrowable());
			else if (result.getStatus() == ITestResult.SKIP)
				test.get().skip(result.getThrowable());
			else
				test.get().pass("Test passed");
		}
	}

	public void setDriver(WebDriver driver) {
		openBrowser.driver = driver;
//		driver = drive;
	}

}
