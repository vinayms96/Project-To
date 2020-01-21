package com.thrive.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Property implements Auto_constant {
	static Properties prop;
	public static String getProperty(String key) {
		prop = new Properties();
		try {
			prop.load(new FileInputStream(new File(propPath)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}
}
