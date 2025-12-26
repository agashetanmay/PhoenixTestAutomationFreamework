package com.api.Utils;

import java.util.Iterator;

import com.dataProviders.api.bean.createJobBean;

public class Demo {

	public static void main(String[] args) {
	
		
	Iterator<createJobBean>	iterator = CSVReaderUtility.loadCSV("testData/createJobData.csv",createJobBean.class );
		
    while(iterator.hasNext()) {
    	
    	System.out.print(iterator.next());
    }
	}

}
