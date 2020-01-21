package com.thrive.modules;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import com.thrive.browserSetup.ProjectSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import com.thrive.reportSetup.ExtentReports;

public class WaitUntil extends ProjectSetup {

	static Wait w;

	public static void waitVisible(int time, WebElement element) {
		w = new WebDriverWait(driver, time);
		w.until(ExpectedConditions.visibilityOf(element));
	}

	public static  void waitClick(int time, WebElement element){
		w = new WebDriverWait(driver, time);
		w.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waitVisibleAll(int time, List<WebElement> element) {
		w = new WebDriverWait(driver, time);
		w.until(ExpectedConditions.visibilityOfAllElements(element));
	}

	public static void waitRefresh(int time, WebElement element) {
		w = new WebDriverWait(driver, time);
		w.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
	}

	public static void waitTextToBe(int time, By element, String expectedText) {
		w = new WebDriverWait(driver, time);
		w.until(ExpectedConditions.textToBe(element, expectedText));
	}

	public static void fluentVisible(int time, WebElement ele, String fullName) {
		try {
			w = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
					.pollingEvery(Duration.ofMillis(250)).ignoring(NoSuchElementException.class);
			w.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					if (ele.getText().contains(fullName)) {
						return ele;
					}
					return null;
				}
			});
		} catch (Exception e) {
			System.out.println("Could Not find the specified Element");
			ExtentReports.getChildTest().fail("Could Not find the specified Element");
		}
	}

}
