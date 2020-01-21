package com.thrive.browserSetup;

import com.thrive.logger.LoggerConfig;
import com.thrive.utils.Property;

public class ServerAuth extends ProjectSetup {
    public static void auth() {
        driver.get(Property.getProperty("authurl"));
        LoggerConfig.getLogger().info("The requested url is hit -> " + Property.getProperty("url"));
    }
}
