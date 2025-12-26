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
	
	public static <T> Iterator<T> loadCSV(String pathOfCSVfile,Class<T> bean){
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCSVfile);

          if (is == null) {
           throw new RuntimeException("CSV file not found");
          }

		InputStreamReader isr = new InputStreamReader(is);
		CSVReader csvReader = new CSVReader(isr);
		
		//MAP CSV TO POJO
		CsvToBean<T> csvBean = new CsvToBeanBuilder<T>(csvReader)
				.withType(bean)
				.withIgnoreEmptyLine(true)
				.build();
		
		List<T> List = csvBean.parse();
		
		
		
		return List.iterator();
	}

}
