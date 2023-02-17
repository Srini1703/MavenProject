package my.MavenProject_AndroidAppium;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import MyProject.Utlities.ReportStatus_Mobile;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Message_App {
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='Start new conversation']")
	MobileElement we;
	
	@BeforeSuite
	public void configuration() throws Exception{
		ReportStatus_Mobile.startJSreport();
	}
	
	ReportStatus_Mobile report = null;
	HashMap<String,String> data = new HashMap<String,String>();
	DesiredCapabilities dc = new DesiredCapabilities();
	AppiumDriver<MobileElement> driver=null;
	@Test
	public void myTest() throws Exception {
		try {
			data.put("testId", "Demo Run_1");
			data.put("S_No", "1");
			data.put("platform", "ANDROID");
			dc.setCapability("appPackage","com.google.android.apps.messaging" );
			dc.setCapability("appActivity","com.google.android.apps.messaging.ui.ConversationListActivity" );
			dc.setCapability("platformName", "Android");
			dc.setCapability("deviceName", "Android Simulator");
			driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"),dc);
			PageFactory.initElements(new AppiumFieldDecorator(driver), this);
			report = new ReportStatus_Mobile(driver);
			report.report(data);
			if(we.isDisplayed()) {
				report.reportSteps("Message App Launch Validation", "Passed");
				we.click();
			}else
				report.reportSteps("Message App Launch Validation", "Failed");
			
			/*Dimension d = driver.manage().window().getSize();
			int width = d.getWidth();
			int height = d.getHeight();
			
			int middle = height/2;
			int startPoint = (int) (width*0.7);
			int endPoint = (int) (width*0.3);
			
			new TouchAction(driver)
			.press(PointOption.point(startPoint, middle))
			.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
			.moveTo(PointOption.point(endPoint, middle))
			.release().perform();*/
			
			if(!report.getReportValue(data.get("testId"), "status").equals("Failed"))
				report.updateMainReport(data.get("testId"), "status", "Passed");
		}catch(Exception e) {
			e.printStackTrace();
			report.getReportValue(data.get("testId"), "status").equals("Failed");
		}
		finally {
			driver.closeApp();
		}

	}
	
	@AfterSuite
	public void endReport() throws Exception {
		ReportStatus_Mobile.executionTime();
		ReportStatus_Mobile.getTotalCount();
	}

}
