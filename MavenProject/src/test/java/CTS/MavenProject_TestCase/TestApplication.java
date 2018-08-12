package CTS.MavenProject_TestCase;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.functionalLibrary.FunctionalLibrary;

import CTS.Utlities.OR_Reader;

public class TestApplication {
	FunctionalLibrary driver;
	
	@BeforeSuite
	void initialize() throws Exception
	{
		OR_Reader.readOR();
	}
	

	@Test
	void MyTest() throws Exception
	{
		try {
			driver = new FunctionalLibrary("Chrome");
			driver.launchBrowser("C:\\Users\\srini\\git\\MavenProject\\MavenProject\\TTS and STT\\SpeechToText.html");
			if(driver.isDisplayed("TTSFrame")) {
				driver.switchToFrame("TTSFrame");
				driver.clearText("TTSsetText");
				Thread.sleep(1000);
				driver.sendkeys("TTSsetText", "Alexa,");
			}
			if(driver.isDisplayed("TTSbutton"))
				driver.clickElement("TTSbutton");
			driver.switchToParentWindow();
			if(driver.isDisplayed("TTSmicBtn"))
				driver.clickElement("TTSmicBtn");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		driver.killSession();
	}
	
}
