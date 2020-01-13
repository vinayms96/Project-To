package com.thrive.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerConfig {
    private static Logger log;

    public static void setLogger(String className){
        log = LogManager.getLogger(className);
    }

    public static Logger getLogger(){
        return log;
    }
}
