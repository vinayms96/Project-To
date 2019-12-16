package modules;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class wait extends openBrowser{
	
	static Wait<WebDriver> w = new WebDriverWait(driver, 20);
	
	public static void waitVisible(WebElement element) {
		w.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void waitVisibleAll(List<WebElement> element) {
		w.until(ExpectedConditions.visibilityOfAllElements(element));
	}
	
	public static void waitRefresh(WebElement element) {
		w.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
	}
	
	@SuppressWarnings("deprecation")
	public static void fluentVisible(WebElement ele) {
		w = new FluentWait<WebDriver>(driver)
				.withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(250, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);
	}

}
