package com.thrive.browserSetup;

import com.aventstack.extentreports.Status;
import com.thrive.logger.LoggerConfig;
import com.thrive.modules.DateFunc;
import com.thrive.reportSetup.ExtentReports;
import com.thrive.utils.Auto_constant;
import com.thrive.utils.Property;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class ProjectSetup implements Auto_constant {
    public static WebDriver driver;
    public static String extBrowser;
    public static String reportDate = DateFunc.getReportDate();

    @BeforeSuite(description = "Setting up the Extent Reports", alwaysRun = true)
    public void setEnviron() {
        // Setting the logger
        LoggerConfig.setLogger(getClass().getName());

        //Setting up Extent Reports
        ExtentReports.attachReport();
        LoggerConfig.getLogger().info("Extent Report setup completed");

    }
    
    @BeforeMethod(description = "Checking the browser and launching it", alwaysRun = true)
    @Parameters({"browser"})
    public void openBrowser(String browser) {
        ProjectSetup.extBrowser = browser;

        // Setting the logger
        LoggerConfig.setLogger(getClass().getName());

        // Accepting the Expired SSL Certificates or Insecure Certificates
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

        // Merging the Capabilities with Browser Options
        // Chrome Options
        ChromeOptions chOptions = new ChromeOptions();
        chOptions.merge(capabilities);

        // Firefox Options
        FirefoxOptions fiOptions = new FirefoxOptions();
        fiOptions.merge(capabilities);

        // Adds the Headless mode to Driver Options if enabled
        if (Property.getProperty("head").equalsIgnoreCase("true")) {
            chOptions.addArguments("--headless");
            fiOptions.addArguments("--headless");
            LoggerConfig.getLogger().info("Browser Started in Headless Mode");
        }

        /*
         * This assigns the browser driver to use for the extent reports for setting
         * child node
         */
        if (browser.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().arch64().setup();
            driver = new ChromeDriver(chOptions);
            LoggerConfig.getLogger().info("Chrome Browser is invoked");
        } else if (browser.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().arch64().setup();
            driver = new FirefoxDriver(fiOptions);
            LoggerConfig.getLogger().info("Firefox Browser is invoked");
        }
        driver.manage().window().maximize();

        // Invoking the URL to test
        driver.get(Property.getProperty("url"));
        LoggerConfig.getLogger().info("The requested url is hit -> " + Property.getProperty("url"));

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterMethod(description = "Results to append in Reports at the end of test", alwaysRun = true)
    public void tearDown(ITestResult result) {
        // Setting the logger
        LoggerConfig.setLogger(getClass().getName());

        // Logs status of test in Extent Reports after the execution of Test (After each
        // Test ends)
        if (result.getStatus() == ITestResult.FAILURE) {
            ExtentReports.getChildTest().log(Status.FAIL, result.getThrowable());
            LoggerConfig.getLogger().info("The Test Case '" + result.getName() + "' has Failed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            ExtentReports.getChildTest().log(Status.SKIP, "Test Case Skipped is " + result.getName());
            LoggerConfig.getLogger().info(result.getThrowable());
        }

        // Extent reports will be flushed only if extent reports are On
        // So Reports will be generated only if extent is flushed
        if (Property.getProperty("extent").equalsIgnoreCase("On")) {
            ExtentReports.getExtent().flush();
            LoggerConfig.getLogger().info("Extent Report is generated");
        }
        // Quits/Closes all the browser instances
        driver.quit();
        LoggerConfig.getLogger().info("Browser instance is Closed");
    }

    @AfterSuite
    public void sendMails() {
        if (Property.getProperty("extent").equalsIgnoreCase("on")) {
//            StatusMailing.report_mail();
//			statusMailing.fail_msg_mail();
        }
    }

}
