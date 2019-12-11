package modules;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class wait extends openBrowser{
	
	static WebDriverWait w = new WebDriverWait(driver, 20); 
	
	public static void waitVisible(WebElement element) {
		w.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void waitVisibleAll(List<WebElement> element) {
		w.until(ExpectedConditions.visibilityOfAllElements(element));
	}
	
	public static void waitRefresh(WebElement element) {
		w.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
	}

}
