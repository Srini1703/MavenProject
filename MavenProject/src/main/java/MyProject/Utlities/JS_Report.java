package MyProject.Utlities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class JS_Report {
	
	public static JSONArray jsJsonArray,jsonArray,jsontotalcountarray=new JSONArray();
	public static File jsonFilePath;
	public static JSONObject timeobject = new JSONObject();
	public static String startTime,fileName;
	public static String JSreport = "./JavaScriptReport";
	public static File overAllMainReport,imagePath,myScriptPath,myCssPath;
	public static File currentExeutionPath;
	public File screenshotFilePath;
	WebDriver driver;
	int passCount=0,failCount=0;
	static SimpleDateFormat sdf = new SimpleDateFormat("h:mm:ss a");
	
	public static void startJSreport() throws Exception{
		try {
			jsonArray = new JSONArray();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyy h:mm:ss a");
			startTime = sdf.format(date);
			fileName = startTime.replace("/", "_").replace(":", "_").replace(",", "_");
			currentExeutionPath = new File(JSreport+"/Execution_Report/"+fileName);
			currentExeutionPath.mkdirs();
			jsonFilePath = new File(currentExeutionPath.toString()+"/JSONfolder");
			myScriptPath = new File(currentExeutionPath.toString()+"/reportingScript.js");
			myCssPath = new File(currentExeutionPath.toString()+"/ColorCss.css");
			overAllMainReport = new File(currentExeutionPath.toString()+"/Report.html");
			jsonFilePath.mkdir();
			copyOfReportStructure(new File(JSreport+"/ColorCss.css"),myCssPath);
			copyOfReportStructure(new File(JSreport+"/reportingScript.js"),myScriptPath);
			copyOfReportStructure(new File(JSreport+"/Report.html"),overAllMainReport);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static void copyOfReportStructure(File src, File dest) throws Exception{
		InputStream is=null;
		OutputStream os = null;
		try {
			is = new FileInputStream(src);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int lngt;
			while((lngt = is.read(buffer))>0) {
				os.write(buffer, 0, lngt);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			is.close();
			os.close();
		}
	}
	
	public JS_Report(WebDriver driver) throws Exception{
		this.driver = driver;
	}
	
	public synchronized void report(String testId,HashMap<String,String> data) throws Exception{
		try {
			JSONObject JSjsonObject = new JSONObject();
			JSONArray stepJsonArray = new JSONArray();
			JSjsonObject.put("TestCaseID", data.get("S_NO"));
			JSjsonObject.put("testcasename", testId);
			JSjsonObject.put("status", "In-Progress");
			JSjsonObject.put("totalSteps", 0);
			JSjsonObject.put("browser", data.get("Browser"));
			updateStaticArray(JSjsonObject);
			writeReport();
			writeReportTestCase(stepJsonArray,testId);
			File testCaseFile = new File(currentExeutionPath.toString()+"/"+JSjsonObject.get("testcasename"));
			testCaseFile.mkdir();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	synchronized static void updateStaticArray(JSONObject JSjsonObject) {
		jsonArray.put(JSjsonObject);
	}
	
	public synchronized void reportSteps(String testId,String description, String expected, String actual,String status)throws Exception{
		try {
			for(int i=0;i<jsonArray.length();i++) {
				if(jsonArray.getJSONObject(i).get("testcasename").equals(testId)) {
					if(status.equalsIgnoreCase("passed"))
						passCount = passCount+1;
					else if(status.equalsIgnoreCase("failed"))
						failCount = failCount+1;
					InputStream is = new FileInputStream(jsonFilePath+"/"+testId+".json");
					String jsonTxt = IOUtils.toString(is);
					JSONArray jsonTestArray = new JSONArray();
					JSONObject stepJsonArray = new JSONObject();
					int currentStep = Integer.valueOf(jsonArray.getJSONObject(i).get("totalSteps").toString())+1;
					stepJsonArray.put("sno", currentStep);
					stepJsonArray.put("Description", description);
					stepJsonArray.put("Expected", expected);
					stepJsonArray.put("Actual", actual);
					stepJsonArray.put("stepStatus", status);
					if(status.equalsIgnoreCase("passed")) {
						File screenShot = getFileScreenShot();
						imagePath = new File(currentExeutionPath.toString()+"/"+jsonArray.getJSONObject(i).get("testcasename")+"/"+stepJsonArray.getDouble("sno")+".png");
						FileUtils.copyFile(screenShot, imagePath);
						stepJsonArray.put("screenshot", jsonArray.getJSONObject(i).get("testcasename")+"/"+stepJsonArray.getDouble("sno")+".png");
					}
					else if(status.equalsIgnoreCase("failed")) {
						File screenShot = getFileScreenShot();
						imagePath = new File(currentExeutionPath.toString()+"/"+jsonArray.getJSONObject(i).get("testcasename")+"/"+stepJsonArray.getDouble("sno")+".png");
						FileUtils.copyFile(screenShot, imagePath);
						stepJsonArray.put("screenshot", jsonArray.getJSONObject(i).get("testcasename")+"/"+stepJsonArray.getDouble("sno")+".png");
					}
					jsonTestArray.put(stepJsonArray);
					stepJsonArray.put("testcasename", testId);
					jsonArray.getJSONObject(i).put("totalSteps", currentStep);
					jsonArray.getJSONObject(i).put("passCount", passCount);
					jsonArray.getJSONObject(i).put("failCount", failCount);
					if(status.equalsIgnoreCase("failed"))
						updateMainReport(testId,"status","Failed");
					writeReport();
					writeReportTestCase(jsonTestArray,testId);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void executionTime() throws Exception{
		try {
			JSONArray jArr = new JSONArray();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyy h:mm:ss a");
			String endTime = sdf.format(date);
			JSONObject time = new JSONObject();
			time.put("startTime", startTime);
			time.put("endTime", endTime);
			jArr.put(time);
			FileWriter file = new FileWriter(jsonFilePath+"/executionTime.json");
			jArr.write(file);file.flush();file.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	synchronized void updateMainReport(String testId,String key, String value) {
		try {
			for(int i=0;i<jsonArray.length();i++) {
				JSONObject obj = jsonArray.getJSONObject(i);
				if(obj.get("testcasename").equals(testId)) {
					obj.put(key, value);
					writeReport();
					break;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	synchronized void writeReport() throws Exception{
		FileWriter file = new FileWriter(jsonFilePath+"/Module.json");
		try {
			jsonArray.write(file);
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			file.flush();file.close();
		}
	}
	
	synchronized void writeReportTestCase(JSONArray testArray, String testCaseName) throws Exception {
		FileWriter file = new FileWriter(jsonFilePath+"/"+testCaseName+".json");
		try {
			testArray.write(file);
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			file.flush();file.close();
		}
	}
	
	File getFileScreenShot() {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	}
}
