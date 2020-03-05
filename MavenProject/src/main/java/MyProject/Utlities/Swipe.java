package MyProject.Utlities;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Swipe {
	
	public static void main(String args[]) throws Exception{
		try {// com.android.settings/com.android.settings.Settings
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setCapability("platformName", "Android");
			dc.setCapability("deviceName", "Android Simulator");
			dc.setCapability("appPackage", "com.android.settings");
			dc.setCapability("appActivity", "com.android.settings.Settings");
			AppiumDriver<MobileElement> driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"),dc);
			Dimension d = driver.manage().window().getSize();
			int width = d.getWidth();
			int height = d.getHeight();
			
			int middle = width/2;
			int startPoint = (int) (height*0.3);
			int endPoint = (int) (height*0.7);
			
			new TouchAction(driver)
			.press(PointOption.point(middle, startPoint))
			.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
			.moveTo(PointOption.point(middle, endPoint))
			.release().perform();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
