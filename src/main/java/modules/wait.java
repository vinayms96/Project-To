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

public class wait extends projectSetup{
	static projectSetup set = new projectSetup();
	
	static Wait<WebDriver> w;
	
	public static void waitVisible(WebDriver driver, WebElement element) {
		w = new WebDriverWait(driver, 10);
		w.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void waitVisibleAll(WebDriver driver, List<WebElement> element) {
		w.until(ExpectedConditions.visibilityOfAllElements(element));
	}
	
	public static void waitRefresh(WebDriver driver, WebElement element) {
		w.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
	}
	
	@SuppressWarnings("deprecation")
	public static void fluentVisible(WebElement ele) {
		w = new FluentWait<WebDriver>(set.driver)
				.withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(250, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);
	}

}
