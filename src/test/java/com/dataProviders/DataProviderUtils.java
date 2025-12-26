package com.dataProviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.Utils.CSVReaderUtility;
import com.api.Utils.FakerDataGenerator;
import com.api.Utils.createJobBeanMapper;
import com.api.request.model.createJobPayload;
import com.dataProviders.api.bean.createJobBean;
import com.dataProviders.api.bean.userBean;

public class DataProviderUtils {
  
	
	@DataProvider(name="LoginAPIdataProvider", parallel=true)
	public static Iterator<userBean> loginAPIDataProvider() {
		
		return CSVReaderUtility.loadCSV("testData/LoginCredentials.csv",userBean.class);
		
	}
	
	@DataProvider(name="CreateJobAPIdataProvider", parallel=true)
	public static Iterator<createJobPayload> createJobDataProvider() {
	Iterator<createJobBean>	createJobBeanIterator = CSVReaderUtility
			.loadCSV("testData/createJobData.csv", createJobBean.class);
	
	List<createJobPayload> payloadList = new ArrayList<createJobPayload>();
	
	    createJobBean tempBean;
	    createJobPayload tempPayload;
	    
	    while(createJobBeanIterator.hasNext()) {
	    	tempBean = createJobBeanIterator.next();
	    	tempPayload = createJobBeanMapper.mapper(tempBean);
	    	payloadList.add(tempPayload);	
	    }
	    return payloadList.iterator();
	}
	

	@DataProvider(name="CreateJobAPIFakerDataProvider", parallel=true)
	public static Iterator<createJobPayload> createJobFakerDataProvider() {
		
		return FakerDataGenerator.generateFakecreateJobdata(10);
		
	}
}