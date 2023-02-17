package my.MavenProject_TestCase;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class Pagefactory {
	WebDriver driver = null;
	@FindBy(xpath="//input[@name='q']")
	protected WebElement we;
	
	@Test
	public void myTest() throws Exception{
		try {
			System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
			ChromeOptions opts = new ChromeOptions();
			opts.addArguments("--start-maximized");
			driver = new ChromeDriver();
			driver.get("https://www.amazon.in/");
			//Pagefactory pf = Pagefactory.initialize(driver);
			WebElement dropDown = driver.findElement(By.xpath("//div[@id='nav-search-dropdown-card']/descendant::select"));
			Select sel = new Select(dropDown);
			List<WebElement> list = sel.getOptions();
			for(WebElement we:list) {
				System.out.println(we.getAttribute("textContent"));
			}
			//pf.click();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static Pagefactory initialize(WebDriver driver) throws Exception{
		return org.openqa.selenium.support.PageFactory.initElements(driver, Pagefactory.class);
	}
	
	void click() throws Exception{
		try {
			System.out.println(we);
			we.click();
			we.sendKeys("Srini");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
