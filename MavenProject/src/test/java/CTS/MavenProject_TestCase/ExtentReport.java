package CTS.MavenProject_TestCase;

import java.io.File;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;

import CTS.Utlities.Reporting;

public class ExtentReport 
{
	WebDriver driver;
	ExtentTest logger= null;
	Reporting r1 = new Reporting();
	
	@BeforeSuite
	void startProject(){
		Reporting.startReporting();
	}
	
	@Test
	void MyTest() throws Exception
	{
		logger = r1.startTest("TC started");
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.google.co.in/");
		driver.manage().window().maximize();
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File imagePath = new File("./Screenshots"+"/"+"Screenshot1.png");
		FileUtils.copyFile(screenshot, imagePath);
		r1.reportPass("Icon Google", imagePath, logger);
	}
	
	@AfterSuite
	void endProject(){
		Reporting.endReporting();
		driver.close();
	}
}
