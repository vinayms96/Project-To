package modules;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class wait extends openBrowser{
	
	WebDriverWait w;
	
	public wait(WebDriver driver) {
		w = new WebDriverWait(driver, 20);
	} 
	
	public void waitVisible(WebElement element) {
		w.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitVisibleAll(List<WebElement> element) {
		w.until(ExpectedConditions.visibilityOfAllElements(element));
	}
	
	public void waitRefresh(WebElement element) {
		w.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
	}

}
