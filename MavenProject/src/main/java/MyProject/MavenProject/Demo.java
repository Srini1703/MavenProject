package MyProject.MavenProject;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public  class Demo {
	String s="CTSABC";
	StringBuilder sb = new StringBuilder(s);
	
	@Test
	void test() {
		System.out.println(sb.reverse());
		String ss = sb.substring(0, 2);
		System.out.println(ss);
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");

		ChromeOptions opts = new ChromeOptions();
		opts.addArguments("--disable-notifications");
		opts.addArguments("--start-maximized");
		WebDriver driver = new ChromeDriver(opts);
		driver.get("https://www.magicbricks.com/");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			/*Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			System.out.println(alert.toString());*/
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
}


