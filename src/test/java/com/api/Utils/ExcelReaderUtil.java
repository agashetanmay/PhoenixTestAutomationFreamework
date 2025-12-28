package com.api.Utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderUtil {
	
	public static void main(String[] args) throws IOException {
		
		
	InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/phoenix_test_data.xlsx");
		
		
	XSSFWorkbook workbook = new XSSFWorkbook(is);
	
	XSSFSheet sheet = workbook.getSheetAt(0);
	
	XSSFRow myRow = sheet.getRow(0);
	
	XSSFCell username = myRow.getCell(0);
	XSSFCell password = myRow.getCell(1);
	System.out.println(username);
	System.out.println(password);
	
	int lastRowIndex =sheet.getLastRowNum();
	System.out.println(lastRowIndex);
	
	XSSFRow Rowheader = sheet.getRow(0);
	int lastIndexofColumn = Rowheader.getLastCellNum()-1;  //return total number of column
	
	System.out.println(lastIndexofColumn);
	
	for(int rowIndex=0;rowIndex<=lastRowIndex;rowIndex++) {
		
		for(int colIndex=0;colIndex<=lastIndexofColumn;colIndex++) {
			
			myRow = sheet.getRow(rowIndex);
			XSSFCell myCell =myRow.getCell(colIndex);
			
			System.out.println(myCell);
			
			
		}
	}
	
	}

}
