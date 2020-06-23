package my.MavenProject_AndroidAppium;

import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import MyProject.Utlities.JS_Report;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Settings_TC {
	AppiumDriver<?> driver = null;
	JS_Report report = null;
	@BeforeSuite
	void startReporting() throws Exception{
		try {
			JS_Report.startJSreport();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	void myTest() throws Exception{
		
		HashMap<String,String> data = new HashMap();
		try {
			data.put("S_NO", "1");
			data.put("Browser", "Android");
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setCapability("platformName", "Android");
			dc.setCapability("deviceName", "emulator");
			dc.setCapability("appPackage", "com.android.settings");
			dc.setCapability("appActivity", "com.android.settings.Settings");
			driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"),dc);
			report = new JS_Report(driver);
			report.report("SettingPage", data);
			report.reportSteps("SettingPage", "Settings APP Should get launched", "Settings app is launched", "Settings app is NOT launched", "Passed");
			if(report.getStatus("SettingPage").equals("Failed"))
				report.updateMainReport("SettingPage", "status", "Failed");
			else
				report.updateMainReport("SettingPage", "status", "Passed");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			driver.closeApp();
		}
	}
	
	@AfterSuite
	void endReporting() throws Exception{
		try {
			JS_Report.executionTime();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
