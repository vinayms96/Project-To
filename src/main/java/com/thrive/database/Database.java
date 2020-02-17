package com.thrive.database;

import com.thrive.logger.LoggerConfig;
import com.thrive.utils.Property;

import java.sql.*;

public class Database extends Property {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String JDBC_DB_URL =
            "jdbc:mysql://" + getProperty("hostName") + ":" + getProperty("port") + "/" + getProperty(
                    "dbName");
    static final String JDBC_URL = "jdbc:mysql://" + getProperty("hostName") + ":" + getProperty("port") + "/";

    // JDBC Database credentials
    static final String USER = getProperty("dbUser");
    static final String PASSWORD = getProperty("dbPass");

    // Available variables for the package
    static Connection connect;
    static Statement stmt;
    static boolean result;

    public void connectDB() {
        // Setting up Logger
        LoggerConfig.setLogger(getClass().getName());
        try {
            // Create a Connection with Database
            connect = DriverManager.getConnection(JDBC_DB_URL, USER, PASSWORD);
            if (!connect.isClosed()) {
                LoggerConfig.getLogger().info("Connected to Database Successfully!");
            }
        } catch (Exception e) {
            LoggerConfig.getLogger().error("Database not present -> Creating the Database...");
            DatabaseSetup.createDB();
        }
    }

    public void closeDB() {
        // Setting up Logger
        LoggerConfig.setLogger(getClass().getName());
        try {
            connect.close();
            if (connect.isClosed()) {
                LoggerConfig.getLogger().info("Database connection closed.");
            }
        } catch (Exception e) {
            LoggerConfig.getLogger().error(e.toString());
        }
    }

}
