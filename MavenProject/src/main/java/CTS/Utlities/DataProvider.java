package CTS.Utlities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class DataProvider {
	
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
}
