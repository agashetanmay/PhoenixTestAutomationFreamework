package com.demo.csv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;


public class readCSVFile_MapToPOJO {
	
	public static void main(String[] args) throws CsvException, IOException {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/LoginCredentials.csv");

          if (is == null) {
           throw new RuntimeException("CSV file not found");
          }

		InputStreamReader isr = new InputStreamReader(is);
		CSVReader csvReader = new CSVReader(isr);
		
		//MAP CSV TO POJO
		CsvToBean<userBean> csvBean = new CsvToBeanBuilder<userBean>(csvReader)
				.withType(userBean.class)
				.withIgnoreEmptyLine(true)
				.build();
		
		List<userBean> userList = csvBean.parse();
		
		for (userBean user : userList) {
		    System.out.println(user.getUsername() + " : " + user.getPassword());
		}
		System.out.println(userList.get(1).getUsername());
		
		
		
	}
}
