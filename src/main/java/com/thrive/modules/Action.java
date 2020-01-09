package com.thrive.modules;

import com.thrive.browserSetup.ProjectSetup;
import com.thrive.logger.LoggerConfig;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Action extends ProjectSetup {
	static Actions a;
	
	public static void moveOver(WebElement element) {
		a = new Actions(driver);
		a.moveToElement(element).build().perform();
	}
	
	public static void moveClick(WebElement element) {
		a = new Actions(driver);
		a.moveToElement(element).click().build().perform();
	}
	
	public static void clickOpenTab(WebElement element) {
		a = new Actions(driver);
		a.moveToElement(element).keyDown(Keys.CONTROL).click().keyUp(Keys.CONTROL).build().perform();
	}

}
