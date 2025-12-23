package com.demo.csv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class readCSVFile {

	public static void main(String[] args) throws IOException, CsvException {

		// code to read CSV file in java

//		File csvFile = new File(
//				"C:\\Users\\tanagash\\eclipse-workspace\\PhoenixApiTestAutomation\\src\\main\\resources\\testData\\Logincred.csv");
//		FileReader FileReader = new FileReader(csvFile);

		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/Logincred.csv");

		InputStreamReader isr = new InputStreamReader(is);
		
		CSVReader csvReader = new CSVReader(isr);

		List<String[]> dataList = csvReader.readAll();

		for (String[] dataArray : dataList) {
			for (String data : dataArray) {

				System.out.println(data.replace(",", " "));

			}
			System.out.println();
		}

	}

}
