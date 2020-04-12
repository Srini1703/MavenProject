package Mobile_Pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import MyProject.Utlities.ReportStatus_Mobile;

public class Demo_Mobile {
	HashMap<String,String> data = new HashMap<String,String>();
	ReportStatus_Mobile report = null;
	WebDriver driver = null;
	WebElement we=null;
	@BeforeSuite
	public void configuration() throws Exception{
		ReportStatus_Mobile.startJSreport();
	}
	
	@Test
	public void test() throws Exception{
		try {
			for(int i=1;i<=2;i++) {
				System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
				driver = new ChromeDriver();
				report = new ReportStatus_Mobile(driver);
				if(i==1) {
					data.put("testId", "Demo Run_1");
					data.put("S_No", "1");
					data.put("platform", "ANDROID");
				}
				else if(i==2) {
					data.put("testId", "Demo Run_2");
					data.put("S_No", "2");
					data.put("platform", "IOS");
				}
				report.report(data);
				driver.manage().window().maximize();
				driver.get("https://www.google.com/");
				try {
					we = driver.findElement(By.xpath("//input[@nme='q']"));
				}catch(Exception e) {
					report.reportSteps("Search Box - Google", "Failed");
				}
				if(we.isDisplayed()) {
					we.sendKeys("Hi Srini");
					report.reportSteps("Search Box - Google", "Passed");
				}
				else
					report.reportSteps("Search Box - Google", "Failed");
				if(!report.getReportValue(data.get("testId"), "status").equals("Failed"))
					report.updateMainReport(data.get("testId"), "status", "Passed");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			driver.close();
		}
	}
	
	@AfterSuite
	public void endReport() throws Exception {
		ReportStatus_Mobile.executionTime();
		ReportStatus_Mobile.getTotalCount();
	}
}
