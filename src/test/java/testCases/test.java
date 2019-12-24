package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class test {
	static WebDriver driver;
	
	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		driver.get("http://www.itgeared.com/demo/1506-ajax-loading.html");
		driver.findElement(By.xpath("//a[text()='Click to load get data via Ajax!']")).click();
		WebElement ele = driver.findElement(By.xpath("//div[@id='results']"));
		wait.until(ExpectedConditions.visibilityOf(ele));
		System.out.println(ele.getText());
		driver.close();
		
	}

}
