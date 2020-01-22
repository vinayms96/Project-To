package com.thrive.regression;

import com.thrive.utils.Auto_constant;
import com.thrive.utils.ExcelUtils;
import com.thrive.utils.Property;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class test1 implements Auto_constant {

    public static void main(String[] args) {

//		System.out.println(System.getProperty("user.dir"));
//		Database db = new Database();
//		db.connectDB();
//		DatabaseSetup.get_test_data("validLogin");
//		for(int i=0; i<DatabaseSetup.data.size(); i++) {
//			System.out.println(DatabaseSetup.data.get(i));
//		}
//		db.closeDB();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://codilar:test@thrive-test.codilar.in");
        System.out.println(driver.getCurrentUrl());

    }

}
