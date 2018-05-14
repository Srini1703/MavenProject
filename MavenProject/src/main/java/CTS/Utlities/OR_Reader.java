package CTS.Utlities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Properties;

public class OR_Reader{
	static Properties properties = new Properties();

	public static void readOR()  throws Exception 
	{
		try
		{
			File fil = new File("./ObjectRepositories/login.properties");
			FileInputStream reader=new FileInputStream(fil); 
			properties.load(reader);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static String getOR(String keyValue) throws Exception
	{
		String key="";
		try
		{
			Enumeration arr = properties.keys();
			while(arr.hasMoreElements()){
				if(arr.nextElement().toString().equalsIgnoreCase(keyValue)){
					key = properties.getProperty(keyValue);
					break;
				}
			}
		}
		catch(Exception e){
			throw new Exception("The given Key "+keyValue+" is Not found in the OR");
		}
		return key;
	}
	
	/*public static void main(String[] args) throws Exception{
		
		OR_Reader or = new OR_Reader();
		or.readOR();
		//OR_Reader.getOR("Username");
		System.out.println(OR_Reader.getOR("color"));
	}*/
}
