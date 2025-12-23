package com.api.Utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import com.dataProviders.api.bean.userBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtility {
	
	private CSVReaderUtility() {
		
	}
	
	public static Iterator<userBean> loadCSV(String pathOfCSVfile){
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCSVfile);

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
		
		return userList.iterator();
	}

}
