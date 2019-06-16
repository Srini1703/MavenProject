package MyProject.MavenProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.co.in/");
	}
}


