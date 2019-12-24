package modules;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class screenshot extends projectSetup implements auto_constant{

	/*
	 * Take the Screenshot of failed cases and stores in Destination 
	 * then returns the path to Assertion class
	 */
	public static String shot(String failMsg) {
		
		TakesScreenshot pic = (TakesScreenshot)driver;
		File src = pic.getScreenshotAs(OutputType.FILE);
		String dest = screenPath+"Shot "+dateFunc.getShotDate()+"/"+failMsg+".png";
		try {
			FileUtils.copyFile(src, new File(dest));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dest;
	}
	
}
