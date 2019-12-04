package modules;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class actions extends openBrowser{

	static Actions a = new Actions(driver);
	
	public static void moveOver(WebElement element) {
		a.moveToElement(element).build().perform();
	}
	
	public static void moveClick(WebElement element) {
		a.moveToElement(element).click().build().perform();
	}

}
