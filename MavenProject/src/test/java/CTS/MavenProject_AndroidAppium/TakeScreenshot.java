package CTS.MavenProject_AndroidAppium;

import java.io.File;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;

import CTS.Utlities.Reporting;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class TakeScreenshot {
	
	ExtentTest logger= null;
	Reporting r1 = new Reporting();
	AppiumDriver<?> driver = null;File path;
	
	@BeforeSuite
	void startProject(){
		Reporting.startReporting();
	}
	
	@Test
	void MyTest() throws Exception
	{
		logger = r1.startTest("TC started");
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("deviceName", "Android Simulator");
		dc.setCapability("platformName", "Android");
		dc.setCapability("newCommandTimeout", "30");
		//Important for Android
		dc.setCapability("appPackage", "com.android.vending");
		dc.setCapability("appActivity", "com.android.vending.AssetBrowserActivity");
		driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"),dc);
		path = screenshot("PlayStore");
		r1.reportPass("PlayStore HOME SCREEN", path, logger);
		if(driver.findElementByXPath("//*[@resource-id='com.android.vending:id/li_title' and contains(@text,'TOP CHARTS')]").isDisplayed()){
			path = screenshot("Top Chart");
			r1.reportPass("TOP CHART", path, logger);
		}
		else
		{
			path = screenshot("Top Chart");
			r1.reportFail("TOP CHART", path, logger);
		}
	}
	
	File screenshot(String Screenshotname) throws Exception{
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File imagePath = new File("./Screenshots"+"/"+Screenshotname+".png");
		FileUtils.copyFile(screenshot, imagePath);
		return imagePath;
		//r1.reportPass(validation, imagePath, logger);
	}
	
	@AfterSuite
	void endProject(){
		Reporting.endReporting();
		
	}

}
