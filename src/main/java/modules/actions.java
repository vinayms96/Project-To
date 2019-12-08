package modules;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class actions {

	Actions a;
	
	public actions(WebDriver driver) {
		a = new Actions(driver);
	}
	
	public void moveOver(WebElement element) {
		a.moveToElement(element).build().perform();
	}
	
	public void moveClick(WebElement element) {
		a.moveToElement(element).click().build().perform();
	}

}
