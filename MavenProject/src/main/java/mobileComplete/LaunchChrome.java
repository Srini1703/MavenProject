package mobileComplete;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class LaunchChrome {
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws Exception{
		try {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("browserName", "chrome");
			//capabilities.setCapability("VERSION", "4.4.2"); 
			capabilities.setCapability("deviceName","Emulator");
			capabilities.setCapability("platformName","Android");
			//capabilities.setCapability("appPackage", "com.android.chrome");
			//capabilities.setCapability("appActivity","com.google.android.apps.chrome.Main");
			AppiumDriver driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
			driver.get("https://www.google.co.in/");
			driver.findElement(By.xpath("//input[@name='q']")).sendKeys("ESPN");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
