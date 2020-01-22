package com.thrive.browserSetup;

import com.thrive.logger.LoggerConfig;
import com.thrive.reportSetup.ExtentReports;
import com.thrive.utils.Property;
import org.testng.Assert;

public class ServerAuth extends ProjectSetup {
    public static void auth() {
        // Setting Logger
        LoggerConfig.setLogger(ServerAuth.class.getName());
        // Opening the website url
        driver.get(Property.getProperty("authurl"));
        try {
            // Comparing the Url of the Launched site
            Assert.assertEquals(Property.getProperty("authurl"), driver.getCurrentUrl());
            LoggerConfig.getLogger().info("The requested url is hit -> " + Property.getProperty("authurl"));
            // Logging the Result
            LoggerConfig.getLogger().info("Website Launched");
        }catch (AssertionError e){
            LoggerConfig.getLogger().error(e.toString());
            Assert.assertTrue(false);
        }
    }
}
