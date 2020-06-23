package MyProject.MavenProject;


import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	DesiredCapabilities dc = new DesiredCapabilities();
    	ChromeOptions op = new ChromeOptions();
    	
      try {
    	  op.addArguments("--whitelisted-ips");
    	  dc.merge(op);
    	  RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),dc);
    	  /*Connection c = DriverManager.getConnection("db_url", "username", "pwd");
    	  Class.forName("jdbc driver");
    	  Statement s = c.createStatement();
    	  ResultSet rs = s.executeQuery("");
    	  while(rs.next()) {
    		  
    	  }
    	  
    	  RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),dc);*/
    	  
      }catch(Exception e) {
    	  e.printStackTrace();
      }
    }
}
