package com.thrive.modules;

import java.text.SimpleDateFormat;
import java.util.Date;

public class dateFunc {
	
	public static String getReportDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("E dd-MM hh:mm:ssa");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public static String getShotDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("E dd-MM hh:mm a");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
}
