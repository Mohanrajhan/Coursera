package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataReader {
	public static HashMap<String, String> rowData;
	
	public static List<HashMap<String, String>> data(String filePath, String sheetname)
	{
		List<HashMap<String,String>> excelData = new ArrayList<>();
		try
		{
			FileInputStream fis = new FileInputStream(filePath);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(sheetname);
			XSSFRow headerRow = sheet.getRow(0);
//			System.out.println(sheet.getPhysicalNumberOfRows());
			for(int i=1;i<sheet.getPhysicalNumberOfRows();i++)
			{
				XSSFRow currentRow = sheet.getRow(i);
				rowData = new HashMap<String, String>();
				for(int j=0;j<currentRow.getPhysicalNumberOfCells();j++)
				{
					XSSFCell cell = currentRow.getCell(j);
					DataFormatter formatter = new DataFormatter();
					String data="";
					try{
					data = formatter.formatCellValue(cell); //Returns the formatted value of a cell as a String regardless of the cell type.
					}
					catch(Exception e)
					{
					}
					rowData.put(headerRow.getCell(j).toString(), data);
				}
				excelData.add(rowData);
			}
			workbook.close();
			fis.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return excelData;
	}
	
	public static int TotalRows(String filePath, String sheetname) 
	{
		int rowCount=0;
		try {
		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(sheetname);
		workbook.close();
		fis.close();
		rowCount = sheet.getPhysicalNumberOfRows();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return rowCount;
	}
//	public static void main(String[] args)
//	{
//		List<HashMap<String,String>> dataMap = DataReader.data(System.getProperty("user.dir")+"/testData/EnterpriseFormFillingData.xlsx","FormData");
//		for(Map.Entry<String,String> entry: dataMap.get(0).entrySet())
//		{
//			System.out.println(entry.getKey());
//			System.out.println(entry.getValue()); 
//		}
//	}
}
