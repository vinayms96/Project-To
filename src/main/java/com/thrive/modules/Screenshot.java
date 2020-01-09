package com.thrive.modules;

import java.io.File;
import java.io.IOException;

import com.thrive.browserSetup.ProjectSetup;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.thrive.utils.Auto_constant;

public class Screenshot extends ProjectSetup implements Auto_constant {

	/*
	 * Take the Screenshot of failed cases and stores in Destination then returns
	 * the path to Assertion class
	 */
	public static String shot(String failMsg) {

		TakesScreenshot pic = (TakesScreenshot) driver;
		File src = pic.getScreenshotAs(OutputType.FILE);
		String dest = "./src/test/resources/Screenshots/Shot " + reportDate + "/" + failMsg + ".png";
		try {
			FileUtils.copyFile(src, new File(dest));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dest;
	}
	
	public static String shot() {

		TakesScreenshot pic = (TakesScreenshot) driver;
		String shot = pic.getScreenshotAs(OutputType.BASE64);
		String image = "data:image/png;base64,"+shot;
		return image;
		
	}

}
