package com.api.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.api.request.model.userCredentials;
import com.dataProviders.api.bean.userBean;
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
		
	      List<T> dataList = Poiji.fromExcel(sheet,clazz);
                   return dataList.iterator();
	}
}

