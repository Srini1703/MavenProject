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
		driver = new FunctionalLibrary("Chrome");
		driver.launchBrowser("https://www.google.co.in");
		if(driver.isDisplayed("textToSpeech")){
			driver.clickElement("textToSpeech");
		}
	}
}
