package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//DataProvider 1
	
	@DataProvider(name="formData")
	public String [][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"/testData/EnterpriseFormFillingData.xlsx";
		
		ExcelUtils xlutils=new ExcelUtils(path);
		
		int rowsCount=xlutils.getRowCount("FormData");	
		int colsCount=xlutils.getCellCount("FormData",1);
				
		String logindata[][]=new String[rowsCount][colsCount];
		
		for(int i=1;i<=rowsCount;i++)  
		{		
			for(int j=0;j<colsCount;j++)  
			{
				logindata[i-1][j]= xlutils.getCellData("FormData",i, j);  
			}
		}
	return logindata;
				
	}
	
	
}
