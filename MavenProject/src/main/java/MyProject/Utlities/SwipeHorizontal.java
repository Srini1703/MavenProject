package MyProject.Utlities;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class SwipeHorizontal {
	
	public static void main(String args[]) throws Exception{
		try {//com.google.android.apps.maps/com.google.android.maps.MapsActivity
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setCapability("platformName", "Android");
			dc.setCapability("deviceName", "Android Simulator");
			dc.setCapability("appPackage", "com.google.android.apps.maps");
			dc.setCapability("appActivity", "com.google.android.maps.MapsActivity");
			AppiumDriver<MobileElement> driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"),dc);
			MobileElement me = driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.google.android.apps.maps:id/search_omnibox_text_box']"));
			me.click();
			me.sendKeys("Chelsea Football Club London");
			Thread.sleep(2000);
			((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
			((IOSDriver<MobileElement>) driver).getKeyboard().pressKey(Keys.ENTER);
			Dimension d = driver.manage().window().getSize();
			int width = d.getWidth();
			int height = d.getHeight();
			
			int middle = height/2;
			int startPoint = (int) (width*0.7);
			int endPoint = (int) (width*0.3);
			
			new TouchAction(driver)
			.press(PointOption.point(startPoint, middle))
			.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
			.moveTo(PointOption.point(endPoint, middle))
			.release().perform();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
