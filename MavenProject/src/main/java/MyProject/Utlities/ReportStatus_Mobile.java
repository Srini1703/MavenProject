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


public class ReportStatus_Mobile {
	
	public static JSONArray jsJsonArray,jsonArray,AndroidjsonArray,IosjsonArray,Jsontotalcountarray=new JSONArray();
	private static File jsonFilePath;
	private static String startTime,fileName;
	private static String jsReport = System.getProperty("user.dir")+"/JavaScriptReport_Mobile";
	private static File executionPath,overallMainReport,imagePath,myScriptPath,myCssPath;
	public static File currentExecutionPath;
	public File screenshotFilePath;
	
	WebDriver driver;
	public String browserName;

	int passCount=0,failCount=0;
	static SimpleDateFormat sdf1 = new SimpleDateFormat("h:mm:ss a");
	HashMap<String,String> data = new HashMap<String,String>();
	
	public static void startJSreport() throws Exception{
		try {
			jsonArray = new JSONArray();
			AndroidjsonArray = new JSONArray();
			IosjsonArray = new JSONArray();
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyy h:mm:ss a");
			startTime = sdf.format(date);
			executionPath = new File(jsReport+"/Execution_Report");
			if(!executionPath.exists())
				executionPath.mkdir();
			if(System.getenv("BUILD_ID")==null)
				fileName = startTime.replace("/", "_").replace(":", "_").replace(",", "_");
			else
				fileName = System.getenv("BUILD_ID");
			currentExecutionPath = new File(jsReport+"/Execution_Report/"+fileName);
			currentExecutionPath.mkdirs();
			jsonFilePath = new File(currentExecutionPath.toString()+"/JSON");
			jsonFilePath.mkdir();
			myScriptPath = new File(currentExecutionPath.toString()+"/javaScript.js");
			myCssPath = new File(currentExecutionPath.toString()+"/myCss.css");
			overallMainReport = new File(currentExecutionPath.toString()+"/Report.html");

			try {
				copyFileUsingStream(new File(System.getProperty("user.dir")+"/JavaScriptReport_Mobile/javaScript.js"), myScriptPath);
				copyFileUsingStream(new File(System.getProperty("user.dir")+"/JavaScriptReport_Mobile/myCss.css"), myCssPath);
				copyFileUsingStream(new File(System.getProperty("user.dir")+"/JavaScriptReport_Mobile/Report.html"), overallMainReport);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	private static void copyFileUsingStream(File source, File dest) throws Exception{
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;
			while((length = is.read(buffer))>0){
				os.write(buffer,0,length);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			is.close();
			os.close();
		}
	}
	
	public ReportStatus_Mobile(WebDriver driver) throws Exception{
		this.driver = driver;
	}
	
	public synchronized void report(HashMap<String,String> data) throws Exception{
		this.data = data;
		JSONObject JSAndroidJsonObject = new JSONObject();
		JSONObject JSiOSjsonObject = new JSONObject();
		this.browserName = data.get("platform");
		if(data.get("platform").contains("ANDROID")) {
			JSONArray AndroidstepJsonArray = new JSONArray();
			JSAndroidJsonObject.put("TestCaseID", data.get("S_No"));
			JSAndroidJsonObject.put("testcasename", data.get("testId"));
			JSAndroidJsonObject.put("status", "In-Progress");
			JSAndroidJsonObject.put("totalSteps", 0);
			JSAndroidJsonObject.put("platform", browserName);
			updateAndroidStaticArray(JSAndroidJsonObject);
			writeAndroidReport();
			writeReportTestCase(AndroidstepJsonArray, data.get("testId"));
			File testFile = new File(currentExecutionPath.toString()+"/"+JSAndroidJsonObject.get("testcasename"));
			testFile.mkdir();
		}
		else if(data.get("platform").contains("IOS")) {
			JSONArray iOSstepJsonArray = new JSONArray();
			JSiOSjsonObject.put("TestCaseID", data.get("S_No"));
			JSiOSjsonObject.put("testcasename", data.get("testId"));
			JSiOSjsonObject.put("status", "In-Progress");
			JSiOSjsonObject.put("totalSteps", 0);
			JSiOSjsonObject.put("platform", browserName);
			updateIosStaticArray(JSiOSjsonObject);
			writeIosReport();
			writeReportTestCase(iOSstepJsonArray, data.get("testId"));
			File testFile = new File(currentExecutionPath.toString()+"/"+JSiOSjsonObject.get("testcasename"));
			testFile.mkdir();
		}
	}
	
	synchronized static void updateAndroidStaticArray(JSONObject jsonObject) {
		AndroidjsonArray.put(jsonObject);
	}
	synchronized static void updateIosStaticArray(JSONObject jsonObject) {
		IosjsonArray.put(jsonObject);
	}
	
	public synchronized void writeAndroidReport() throws Exception{
		FileWriter write = new FileWriter(jsonFilePath+"/ANDROID.json");
		try {
			AndroidjsonArray.write(write);
		}
		finally {
			write.flush();
			write.close();
		}
	}
	public synchronized void writeIosReport() throws Exception{
		FileWriter write = new FileWriter(jsonFilePath+"/IOS.json");
		try {
			IosjsonArray.write(write);
		}
		finally {
			write.flush();
			write.close();
		}
	}
	
	public void writeReportTestCase(JSONArray testArray,String testCaseName) throws Exception{
		FileWriter write = new FileWriter(jsonFilePath+"/"+testCaseName+".json");
		try {
			testArray.write(write);
		}
		finally {
			write.flush();
			write.close();
		}
	}
	
	public synchronized void reportSteps(String elementName,String strStatus) throws Exception{
		try {
			if(data.get("platform").equals("ANDROID")) {
				try {
					for(int i=0; i<AndroidjsonArray.length();i++) {
						if(AndroidjsonArray.getJSONObject(i).get("testcasename").equals(data.get("testId"))) {
							if(strStatus.equals("Passed"))
								passCount = passCount+1;
							else if(strStatus.equals("Failed"))
								failCount++;
							InputStream is = new FileInputStream(jsonFilePath+"/"+data.get("testId")+".json");
							String jsonTxt = IOUtils.toString(is,"UTF-8");
							JSONArray jsonTestArray = new JSONArray(jsonTxt);
							JSONObject stepJsonArray = new JSONObject();
							int currentStep = Integer.valueOf(AndroidjsonArray.getJSONObject(i).get("totalSteps").toString())+1;
							stepJsonArray.put("sno", currentStep);
							stepJsonArray.put("Description", "Validation of icon "+"<font color='blue'><b>"+elementName+"</b></font>");
							stepJsonArray.put("Expected", "<font color='blue'><b>"+elementName+"</b></font>"+" should be available");
							if(strStatus.equals("Passed"))
								stepJsonArray.put("Actual", "<font color='blue'><b>"+elementName+"</b></font>"+" Icon is available");
							else if(strStatus.equals("Failed"))
								stepJsonArray.put("Actual", "<font color='red'><b>"+elementName+"</b></font>"+" Icon is NOT available");
							stepJsonArray.put("stepStatus", strStatus);
							File screenshot = getFileScreenShot();
							imagePath= new File(currentExecutionPath.toString()+"/"+AndroidjsonArray.getJSONObject(i).get("testcasename")+"/"+stepJsonArray.get("sno")+".png");
							FileUtils.copyFile(screenshot, imagePath);
							stepJsonArray.put("Screenshot", AndroidjsonArray.getJSONObject(i).get("testcasename")+"/"+stepJsonArray.get("sno")+".png");
							jsonTestArray.put(stepJsonArray);
							stepJsonArray.put("testcasename", data.get("testId"));
							
							AndroidjsonArray.getJSONObject(i).put("totalSteps", currentStep);
							AndroidjsonArray.getJSONObject(i).put("passCount", passCount);
							AndroidjsonArray.getJSONObject(i).put("failCount", failCount);
							
							if(strStatus.equals("Failed"))
								updateMainReport(data.get("testId"), "status", "Failed");
							writeReportTestCase(jsonTestArray, data.get("testId"));
						}
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			else if(data.get("platform").equals("IOS")) {
				try {
					for(int i=0; i<IosjsonArray.length();i++) {
						if(IosjsonArray.getJSONObject(i).get("testcasename").equals(data.get("testId"))) {
							if(strStatus.equals("Passed"))
								passCount = passCount+1;
							else if(strStatus.equals("Failed"))
								failCount++;
							InputStream is = new FileInputStream(jsonFilePath+"/"+data.get("testId")+".json");
							String jsonTxt = IOUtils.toString(is);
							JSONArray jsonTestArray = new JSONArray(jsonTxt);
							JSONObject stepJsonArray = new JSONObject();
							int currentStep = Integer.valueOf(IosjsonArray.getJSONObject(i).get("totalSteps").toString())+1;
							stepJsonArray.put("sno", currentStep);
							stepJsonArray.put("Description", "Validation of icon "+"<font color='blue'><b>"+elementName+"</b></font>");
							stepJsonArray.put("Expected", "<font color='blue'><b>"+elementName+"</b></font>"+" should be available");
							if(strStatus.equals("Passed"))
								stepJsonArray.put("Actual", "<font color='blue'><b>"+elementName+"</b></font>"+" Icon is available");
							else if(strStatus.equals("Failed"))
								stepJsonArray.put("Actual", "<font color='red'><b>"+elementName+"</b></font>"+" Icon is NOT available");
							stepJsonArray.put("stepStatus", strStatus);
							File screenshot = getFileScreenShot();
							imagePath= new File(currentExecutionPath.toString()+"/"+IosjsonArray.getJSONObject(i).get("testcasename")+"/"+stepJsonArray.get("sno")+".png");
							FileUtils.copyFile(screenshot, imagePath);
							stepJsonArray.put("Screenshot", IosjsonArray.getJSONObject(i).get("testcasename")+"/"+stepJsonArray.get("sno")+".png");
							jsonTestArray.put(stepJsonArray);
							stepJsonArray.put("testcasename", data.get("testId"));
							
							IosjsonArray.getJSONObject(i).put("totalSteps", currentStep);
							IosjsonArray.getJSONObject(i).put("passCount", passCount);
							IosjsonArray.getJSONObject(i).put("failCount", failCount);
							
							if(strStatus.equals("Failed"))
								updateMainReport(data.get("testId"), "status", "Failed");
							writeReportTestCase(jsonTestArray, data.get("testId"));
						}
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized String getReportValue(String testId,String key) throws Exception{
		String value="";
		try {
			if(data.get("platform").equals("ANDROID")) {
				for(int i=0;i<AndroidjsonArray.length();i++) {
					JSONObject obj = AndroidjsonArray.getJSONObject(i);
					if(obj.get("testcasename").equals(testId)) {
						value = obj.get(key).toString();
						break;
					}
				}
				writeAndroidReport();
			}else if(testId.equals("IOS")) {
				for(int i=0;i<IosjsonArray.length();i++) {
					JSONObject obj = IosjsonArray.getJSONObject(i);
					if(obj.get("testcasename").equals(testId)) {
						value = obj.get(key).toString();
						break;
					}
				}
				writeIosReport();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public synchronized void updateMainReport(String testId,String key, String value) throws Exception{
		if(data.get("platform").equals("ANDROID")) {
			for(int i=0;i<AndroidjsonArray.length();i++) {
				JSONObject obj = AndroidjsonArray.getJSONObject(i);
				if(obj.get("testcasename").equals(testId)) {
					obj.put(key, value);
					break;
				}
			}
			writeAndroidReport();
		}else if(data.get("platform").equals("IOS")) {
			for(int i=0;i<IosjsonArray.length();i++) {
				JSONObject obj = IosjsonArray.getJSONObject(i);
				if(obj.get("testcasename").equals(testId)) {
					obj.put(key, value);
					break;
				}
			}
			writeIosReport();
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
			FileWriter writer = new FileWriter(jsonFilePath+"/executionTime.json");
			jArr.write(writer);
			writer.flush();
			writer.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getTotalCount() throws Exception{
		JSONArray jArr = new JSONArray();
		JSONObject obj = new JSONObject();
		int AndroidPass=0,AndroidFail=0,AndroidTotal=0,AndroidPassedcase=0,AndroidFailedcase=0,AndroidTotalcase=0;
		int iOSpass=0,iOSfail=0,iOStotal=0,iOSpassedcase=0,iOSfailedcase=0,iOStotalcase=0;
		
		try {
			for(int i=0;i<AndroidjsonArray.length();i++) {
				AndroidTotal = AndroidTotal+Integer.parseInt(AndroidjsonArray.getJSONObject(i).get("totalSteps").toString());
				AndroidPass = AndroidPass+Integer.parseInt(AndroidjsonArray.getJSONObject(i).get("passCount").toString());
				AndroidFail = AndroidFail+Integer.parseInt(AndroidjsonArray.getJSONObject(i).get("failCount").toString());
				if(AndroidjsonArray.getJSONObject(i).get("status").equals("Passed"))
					AndroidPassedcase = AndroidPassedcase+1;
				else if(AndroidjsonArray.getJSONObject(i).get("status").equals("Failed"))
					AndroidFailedcase = AndroidFailedcase+1;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			for(int i=0;i<IosjsonArray.length();i++) {
				iOStotal = iOStotal+Integer.parseInt(IosjsonArray.getJSONObject(i).get("totalSteps").toString());
				iOSpass = iOSpass+Integer.parseInt(IosjsonArray.getJSONObject(i).get("passCount").toString());
				iOSfail = iOSfail+Integer.parseInt(IosjsonArray.getJSONObject(i).get("failCount").toString());
				if(IosjsonArray.getJSONObject(i).get("status").equals("Passed"))
					iOSpassedcase = iOSpassedcase+1;
				else if(IosjsonArray.getJSONObject(i).get("status").equals("Failed"))
					iOSfailedcase = iOSfailedcase+1;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			AndroidTotalcase = AndroidTotalcase+AndroidjsonArray.length();
		}catch(NullPointerException ne) {
			
		}
		
		try {
			iOStotalcase = iOStotalcase+IosjsonArray.length();
		}catch(NullPointerException ne) {
			
		}
		
		obj.put("OverallTotal",(AndroidTotal+iOStotal));
		obj.put("total",AndroidTotal);
		obj.put("iOStotal",iOStotal);
		
		obj.put("OverallPass",(AndroidPass+iOSpass));
		obj.put("pass",AndroidPass);
		obj.put("iOSpass",iOSpass);
		
		obj.put("OverallFail",(AndroidFail+iOSfail));
		obj.put("fail",AndroidFail);
		obj.put("iOSfail",iOSfail);
		
		obj.put("OverallTotalCase",(AndroidTotalcase+iOStotalcase));
		obj.put("totalcase",AndroidTotalcase);
		obj.put("iOStotalcase",iOStotalcase);
		
		obj.put("OverallPassedCase",(AndroidPassedcase+iOSpassedcase));
		obj.put("passedcase",AndroidPassedcase);
		obj.put("iOSpassedcase",iOSpassedcase);
		
		obj.put("OverallFailedCase",(AndroidFailedcase+iOSfailedcase));
		obj.put("failedcase",AndroidFailedcase);
		obj.put("iOSfailedcase",iOSfailedcase);
		
		jArr.put(obj);
		FileWriter writer = new FileWriter(jsonFilePath+"/TotalDetails.json");
		jArr.write(writer);
		writer.flush();
		writer.close();
		
	}
	
	public File getFileScreenShot() {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	}
	
}
