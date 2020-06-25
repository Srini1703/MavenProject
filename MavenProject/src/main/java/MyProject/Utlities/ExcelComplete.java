package MyProject.Utlities;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelComplete {
	
	@SuppressWarnings({ "resource", "deprecation" })
	public ArrayList<HashMap<String,String>> readExcel(String excelFile, String sheetName) throws Exception{
		String heading="",key="";
		ArrayList<HashMap<String,String>> arrMap = new ArrayList<HashMap<String,String>>();
		FileInputStream fis = new FileInputStream(excelFile);
		//XSSFWorkbook wb1 = new XSSFWorkbook(fis);
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		HSSFSheet sh = wb.getSheet(sheetName);
		int rowCount = sh.getLastRowNum() - sh.getFirstRowNum();
		for(int i=0;i<=rowCount;i++) {
			HashMap<String,String> hm = new HashMap<String,String>();
			Row row = sh.getRow(i);
			int colCount = row.getLastCellNum() - row.getFirstCellNum();
			for(int j=0;j<=colCount;j++) {
				heading = sh.getRow(0).getCell(j).getStringCellValue();
				Cell cel = row.getCell(j);
				switch(cel.getCellType()) {
					case Cell.CELL_TYPE_STRING :
						key = cel.getStringCellValue();
						break;
				}
				hm.put(heading, key);
			}
			arrMap.add(hm);
		}
		
		return arrMap;
	}
	
	
	
}
