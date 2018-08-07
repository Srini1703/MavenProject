package CTS.MavenProject_AndroidAppium;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

/**
 * Hello world!
 *
 */
public class App 
{
	WebDriver driver;
	@Test
    public void lauchBrowser(String browserName)
    {
		/*browserName = browserName.toLowerCase();
		switch(browserName) {
		
		case"chrome":
	        System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case"firefox":
			System.setProperty("webdriver.gecko.driver", "./Drivers/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case"ie":
			System.setProperty("webdriver.ie.driver", "./Drivers/internetexplorerdriver.exe");
			driver = new InternetExplorerDriver();
			break;	
		}*/
		
		//selectByVisibleText
		
		WebElement element = driver.findElement(By.id("Country"));
		Select oSelect = new Select(element);
		oSelect.selectByVisibleText("2010");
		//By index
		oSelect.selectByIndex(4);
		//By Value
		oSelect.selectByValue("India");
		Alert a = driver.switchTo().alert();
    }
}
