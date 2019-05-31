package CTS.Utlities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

public class DataProvider {
	WebDriver driver;
	
	@SuppressWarnings("rawtypes")
	@org.testng.annotations.DataProvider(name="myTC")
	public Iterator<Object[]> getData(){
		ArrayList<HashMap> arrHashMap = new ArrayList<HashMap>();
		//ExcelData Object
		List<Object[]> dataArr = new ArrayList<Object[]>();
		for(HashMap data:arrHashMap) {
			dataArr.add(new Object[] {
					data
			});
		}
		return dataArr.iterator();
	}
	
	FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver)
			.withTimeout(Duration.ofSeconds(30))
			.pollingEvery(Duration.ofMillis(500))
			.ignoring(TimeoutException.class)
			.ignoring(NoSuchElementException.class);
}
