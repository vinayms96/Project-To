package com.thrive.database;

import com.thrive.logger.LoggerConfig;
import com.thrive.utils.Property;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DatabaseSetup extends Database {

    public static ArrayList<String> data;
    private static ResultSet result;

    public static void createDB() {
        // Setting up Logger
        LoggerConfig.setLogger(DatabaseSetup.class.getName());

        // Query to be executed
        String query = "CREATE DATABASE thrive;";

        // Create a Database
        try {
            connect = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            stmt = connect.createStatement();
            if (!stmt.execute(query)) {
                LoggerConfig.getLogger().info("Database Created Successfully!");
            }
        } catch (Exception e) {
            LoggerConfig.getLogger().error(e.toString());
        }
    }

    public static void get_test_data(String feature) {
        // Setting up Logger
        LoggerConfig.setLogger(DatabaseSetup.class.getName());
        int cellCount = 0;

        String query = "SELECT * FROM " + Property.getProperty("tableName") + " WHERE features=" + "'" + feature + "'";

        try {
            result = stmt.executeQuery(query);
            for(int i=1; i<result.getFetchSize(); i++){
                data.add(result.getString(i));
            }
        }catch (Exception e){
            LoggerConfig.getLogger().error(e.getMessage());
        }

    }
}
