package MyProject.Utlities;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

public class Demo {

	public static void main(String[] args) throws Exception {
		
		String a ="HELLO WORLD";
		char c1 = 0;
		int count=1,highestCount=1;
		
		for(int i=0;i<a.length();i++) {
			for(int j=i+1;j<a.length();j++) {
				if(a.charAt(i) == a.charAt(j)) {
					count+=1;
				}
			}
			if(count>highestCount) {
				highestCount=count;
				c1 = a.charAt(i);
			}
			count=1;
		}
		System.out.println(c1);
		/*DesiredCapabilities dc = new DesiredCapabilities();
		dc.setBrowserName("chrome");
		WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);
		Alert a = driver.switchTo().alert();
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);*/
		
		
		
		
	/*	int a = 1;
		int b=2;
		int c;
		for(int i=1;i<=10;i++) {
			c= a+b;
			a = b;
			b=c;
			System.out.println(c);
		}*/
		
		/*System.out.println("Priate Method");
		List<Integer> ls = new ArrayList<Integer>();
		ls.add(2);ls.add(22);ls.add(42);ls.add(22);ls.add(12);
		ListIterator it = ls.listIterator();
		while(it.hasNext()) {
			
		}
		Set<Integer> ss = new TreeSet<Integer>(ls);
		System.out.println(ss);*/
		
		/*
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int arr[]= {1,2,2,2,1,1,2,2,1};
		int updateArr[] = new int[arr.length];
		int arrIndex = arr.length-1;
		for(int i=0;i<arr.length;i++) {
			if(arr[i] == 1) {
				updateArr[arrIndex] = arr[i];
				arrIndex--;
			}
		}
		arrIndex = 0;
		for(int i=0;i<arr.length;i++) {
			if(arr[i] != 1) {
				updateArr[arrIndex] = arr[i];
				arrIndex++;
			}
		}
		System.out.println(Arrays.toString(updateArr));
	*/}

}
