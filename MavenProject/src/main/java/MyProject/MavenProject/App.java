package MyProject.MavenProject;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
      try {
    	  DesiredCapabilities dc = new DesiredCapabilities();
          dc.setCapability("platformName", "Android");
          dc.setCapability("appPackage", "com.hidglobal.pacs.readermanager");
          dc.setCapability("appActivity", "crc64904c2766c7b43084.SplashScreen");
          dc.setCapability("deviceName", "871bcb56");
          AndroidDriver<MobileElement> driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), dc);
          MobileElement me = driver.findElement(By.xpath("//android.widget.EditText[contains(@text,'Email address')]"));
          if(me.isDisplayed()) {
        	  me.click();
        	  me.sendKeys("Srini");
          }
      }catch(Exception e) {
    	  e.printStackTrace();
      }
    }
}
