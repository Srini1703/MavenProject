package CTS.Utlities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Reporting {
	
	public static ExtentReports report;
	public static ExtentTest logger;
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd_MMM_yyy_h_mm_s");
	public static Date date = new Date();
	public static File path;
	
	public static void startReporting()
	{
		path = new File("./Report/"+sdf.format(date));
		path.mkdirs();
		report = new ExtentReports(path+"/OverallReport.html");
	}
	
	public ExtentTest startTest(String name)
	{
		return logger = report.startTest(name);
	}
	
	public void endTest()
	{
		report.endTest(logger);
	}
	
	public void reportPass(String desc, File imagePath, ExtentTest logger){
		String desc1 = "<font color ='blue'><b>Description - </font></b>"+"Validation of"+desc+" Icon"+"\n"+"<br>"+
				"<font color ='blue'><b>Expected - </font></b>"+desc+" Should be available"+"\n"+"<br>"+
				"<font color ='blue'><b>Actual - </font></b>"+desc+" Icon is available"+"\n"+"<br>";
		desc = desc1;
		String temp = logger.addScreenCapture(imagePath.getAbsolutePath());
		logger.log(LogStatus.PASS, desc, temp);
	}
	
	public void reportFail(String desc, File imagePath, ExtentTest logger){
		String desc1 = "<font color ='blue'><b>Description - </font></b>"+"Validation of"+desc+" Icon"+"\n"+"<br>"+
				"<font color ='blue'><b>Expected - </font></b>"+desc+" Should be available"+"\n"+"<br>"+
				"<font color ='red'><b>Failed - </font></b>"+desc+" Icon is NOT available"+"\n"+"<br>";
		desc = desc1;
		String temp = imagePath.getPath();
		logger.log(LogStatus.FAIL, desc, temp);
	}
	
	public static void endReporting(){
		report.endTest(logger);
		report.flush();
	}
}
