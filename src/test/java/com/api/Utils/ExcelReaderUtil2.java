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

public class ExcelReaderUtil2 {

	private ExcelReaderUtil2() {

	}

	public static Iterator<userCredentials> loadTestData() {

		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("testData/phoenix_test_data.xlsx");

		XSSFWorkbook workbook;
		XSSFSheet sheet=null;
		XSSFRow headerRow = null;
		try {
			workbook = new XSSFWorkbook(is);
			sheet = workbook.getSheetAt(0);
			headerRow = sheet.getRow(0);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		

		int usernameIndex = -1;
		int passwordIndex = -1;

		for (Cell cell : headerRow) {

			if (cell.getStringCellValue().trim().equalsIgnoreCase("username")) {
				usernameIndex = cell.getColumnIndex(); // column index at 0
			}
			if (cell.getStringCellValue().trim().equalsIgnoreCase("password")) {
			passwordIndex = cell.getColumnIndex(); // column index at 1
		    }
		}
			
		int lastRowIndex = sheet.getLastRowNum();
		XSSFRow rowData;
		userCredentials usercredentials = null;

		List<userCredentials> userList = new ArrayList<userCredentials>();
		for (int rowIndex = 1; rowIndex <= lastRowIndex; rowIndex++) {
			rowData = sheet.getRow(rowIndex);
			usercredentials = new userCredentials(rowData.getCell(usernameIndex).toString(),
					rowData.getCell(passwordIndex).toString());
			userList.add(usercredentials);
		}
		
		return userList.iterator();
	
		}
	}

	



