package com.thrive.modules;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFunc {

    public static String getReportDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("E dd-MM hh:mm:ssa");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static void getLogDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("E dd-MM hh:mm a");
        System.setProperty("log_date", dateFormat.format(new Date()));
    }

}
