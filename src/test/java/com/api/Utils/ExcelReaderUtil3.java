package com.api.Utils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.poiji.bind.Poiji;

public class ExcelReaderUtil3 {

	private ExcelReaderUtil3() {

	}

	public static <T> Iterator<T> loadTestData(String sheetName,Class<T> clazz) {

		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("testData/phoenix_test_data.xlsx");

			XSSFWorkbook workbook=null;
			try {
				workbook = new XSSFWorkbook(is);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			XSSFSheet sheet = workbook.getSheet(sheetName);
		
	      List<T> dataList = Poiji.fromExcel(sheet,clazz);   //clazz name will be createJobBean or UserBean
	                                                         // it will always read the bean classes.
                   return dataList.iterator();
	}
}

