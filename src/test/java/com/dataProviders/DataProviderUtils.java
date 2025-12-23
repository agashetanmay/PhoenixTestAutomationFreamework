package com.dataProviders;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.api.Utils.CSVReaderUtility;
import com.dataProviders.api.bean.userBean;

public class DataProviderUtils {
  
	
	@DataProvider(name="LoginAPIdataProvider", parallel=true)
	public static Iterator<userBean> loginAPIDataProvider() {
		
		return CSVReaderUtility.loadCSV("testData/LoginCredentials.csv");
		
	}
}
