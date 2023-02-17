package com.functionalLibrary;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import com.google.common.base.Function;

import MyProject.Utlities.OR_Reader;

public class FunctionalLibrary {

	String browser;

	public FunctionalLibrary(String browser) {
		this.browser = browser;
	}

	WebDriver driver;

	public void launchBrowser(String url) {
		browser = browser.toLowerCase();
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(url);
		}
	}

	public void clickUsingJS(WebElement we) throws Exception {
		try {
			if (we != null) {
				((JavascriptExecutor) driver).executeScript("aruguments[0].click();", we);
			}
		} catch (Exception e) {

		}
	}

	WebElement findWebElement(String methodname, String ORkey) throws Exception {
		System.out.println("");
		WebElement element = null;
		String value = OR_Reader.getOR(ORkey);
		try {
			System.out.println("Get Element Started for the Method " + methodname + "-- " + value);
			FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
					.pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class)
					.ignoring(TimeoutException.class);

			element = fWait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return driver.findElement(By.xpath(value));
				}
			});
			if (element != null) {
				System.out.println("Get Element Completed for the Method " + methodname + " -- " + value);
				return element;
			} else
				System.out.println("Get Element Failed for the Method " + methodname + " -- " + value);
		} catch (Exception e) {
			System.out.println("Exception occured while evaluating " + value);
		}
		return element;
	}

	public void selectDropDown(WebElement we) throws Exception {
		try {
			Select sel = new Select(we);
			sel.selectByVisibleText("");
		} catch (Exception e) {

		}
	}

	/*
	 * @SuppressWarnings("unchecked") List<WebElement> findWebElements(String ORkey)
	 * throws Exception { List<WebElement> elements=null; String value =
	 * OR_Reader.getOR(ORkey); try {
	 * System.out.println("Get Element Started for the Value -- "+value);
	 * FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver)
	 * .withTimeout(30,TimeUnit.SECONDS) .pollingEvery(5, TimeUnit.SECONDS)
	 * .ignoring(NoSuchElementException.class) .ignoring(TimeoutException.class);
	 * elements = (List<WebElement>) fWait.until(new Function<WebDriver,
	 * WebElement>() { public WebElement apply(WebDriver driver) { return
	 * (WebElement) driver.findElements(By.xpath(value)); } });
	 * if(elements.size()>0){
	 * System.out.println("Get Element Completed for the Value -- "+value); return
	 * elements; } else
	 * System.out.println("Get Element Failed for the Value -- "+value); }
	 * catch(Exception e){
	 * System.out.println("Get Element Failed for the Value -- "+value); } return
	 * elements; }
	 */

	public void clearText(String ORkey) throws Exception {
		String name = new Object() {
		}.getClass().getEnclosingMethod().getName();
		WebElement ele = findWebElement(name, ORkey);
		ele.clear();
	}

	public void killSession() throws Exception {
		driver.close();
	}

	public boolean isDisplayed(String ORkey) throws Exception {
		String name = new Object() {
		}.getClass().getEnclosingMethod().getName();
		boolean flag = false;
		try {
			WebElement ele = findWebElement(name, ORkey);
			if (ele != null)
				return flag = true;
		} catch (Exception e) {

		}
		return flag;
	}

	public void clickElement(String ORkey) throws Exception {
		try {
			String name = new Object() {
			}.getClass().getEnclosingMethod().getName();
			WebElement ele = findWebElement(name, ORkey);
			ele.click();
		} catch (Exception e) {
			System.out.println("ELEMENT IS NOT CLICKABLE");
		}
	}

	public void sendkeys(String ORkey, String data) throws Exception {

		String name = new Object() {
		}.getClass().getEnclosingMethod().getName();
		WebElement ele = findWebElement(name, ORkey);
		ele.sendKeys(data);
	}

	public void switchToFrame(String ORkey) throws Exception {
		String name = new Object() {
		}.getClass().getEnclosingMethod().getName();
		WebElement ele = findWebElement(name, ORkey);
		driver.switchTo().frame(ele);
	}

	public void switchToParentWindow() throws Exception {
		driver.switchTo().defaultContent();
	}

	public void switchToWindow(String windowTitle) throws Exception {
		Set<String> handles = driver.getWindowHandles();
		for (String window : handles) {
			String title = driver.switchTo().window(window).getTitle();
			if (title.equals(windowTitle)) {
				driver.switchTo().window(window);
				break;
			}
		}
	}

	public void highlightElement(WebDriver driver, WebElement element) {
		((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", element);
	}

	public void mouseOver(WebElement we) throws Exception {
		Actions a = new Actions(driver);
		a.moveToElement(we).click().build().perform();
	}
}
