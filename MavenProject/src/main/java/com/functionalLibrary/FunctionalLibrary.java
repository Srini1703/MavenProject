package com.functionalLibrary;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

import MyProject.Utlities.OR_Reader;

public class FunctionalLibrary {
	
	String browser;
	public FunctionalLibrary(String browser){
		this.browser = browser;
	}
	WebDriver driver;
	
	public void launchBrowser(String url){
		browser = browser.toLowerCase();
		switch(browser)
		{
			case"chrome":
			System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(url);
		}
	}
	
	WebElement findWebElement(String methodname,String ORkey) throws Exception
	{
		System.out.println("");
		WebElement element=null;
		String value = OR_Reader.getOR(ORkey);
		try
		{	
			System.out.println("Get Element Started for the Method "+methodname+ "-- "+value);
			FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver)
				       .withTimeout(10,TimeUnit.SECONDS)
				       .pollingEvery(2, TimeUnit.SECONDS)
				       .ignoring(NoSuchElementException.class)
					   .ignoring(TimeoutException.class);

			element =  fWait.until(new Function<WebDriver, WebElement>() {
			     public WebElement apply(WebDriver driver) {
				       return driver.findElement(By.xpath(value));
				     }
				   });
			if(element!=null){
				System.out.println("Get Element Completed for the Method "+methodname+ " -- "+value);
				return element;
			}
			else
				System.out.println("Get Element Failed for the Method "+methodname+ " -- "+value);
		}
		catch(Exception e){
			System.out.println("Exception occured while evaluating "+value);
		}
		return element;
	}
	
	/*@SuppressWarnings("unchecked")
	List<WebElement> findWebElements(String ORkey) throws Exception
	{
		List<WebElement> elements=null;
		String value = OR_Reader.getOR(ORkey);
		try
		{	
			System.out.println("Get Element Started for the Value -- "+value);
			FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver)
				       .withTimeout(30,TimeUnit.SECONDS)
				       .pollingEvery(5, TimeUnit.SECONDS)
				       .ignoring(NoSuchElementException.class)
					   .ignoring(TimeoutException.class);
			elements =  (List<WebElement>) fWait.until(new Function<WebDriver, WebElement>() {
			     public WebElement apply(WebDriver driver) {
				       return (WebElement) driver.findElements(By.xpath(value));
				     }
				   });
			if(elements.size()>0){
				System.out.println("Get Element Completed for the Value -- "+value);
				return elements;
			}
			else
				System.out.println("Get Element Failed for the Value -- "+value);
		}
		catch(Exception e){
			System.out.println("Get Element Failed for the Value -- "+value);
		}
		return elements;
	}
	*/
	
	public void clearText(String ORkey) throws Exception {
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		WebElement ele = findWebElement(name,ORkey);
		ele.clear();
	}
	
	public void killSession() throws Exception {
		driver.close();
	}
	
	public boolean isDisplayed(String ORkey) throws Exception
	{
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		boolean flag=false;
		try
		{
			WebElement ele = findWebElement(name,ORkey);
			if(ele!=null)
				return flag=true;
		}
		catch(Exception e){
			
		}
		return flag;
	}
	
	public void clickElement(String ORkey) throws Exception 
	{
		try
		{
			String name = new Object(){}.getClass().getEnclosingMethod().getName();
			WebElement ele = findWebElement(name,ORkey);
			ele.click();
		}
		catch(Exception e){
			System.out.println("ELEMENT IS NOT CLICKABLE");
		}
	}
	
	public void sendkeys(String ORkey,String data) throws Exception{
		
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		WebElement ele = findWebElement(name,ORkey);
		ele.sendKeys(data);
	}
	
	public void switchToFrame(String ORkey) throws Exception {
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		WebElement ele = findWebElement(name,ORkey);
		driver.switchTo().frame(ele);
	}
	
	public void switchToParentWindow() throws Exception {
		driver.switchTo().defaultContent();
	}
}
