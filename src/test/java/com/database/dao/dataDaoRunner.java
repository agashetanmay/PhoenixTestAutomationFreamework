package com.database.dao;

import java.util.ArrayList;
import java.util.List;

import com.api.Utils.createJobBeanMapper;
import com.api.request.model.createJobPayload;
import com.dataProviders.api.bean.createJobBean;

public class dataDaoRunner {

	public static void main(String[] args) {


	   List<createJobBean> beanList =	createJobPayloadDataDao.getCreateJobPayloadData();
	   List<createJobPayload> payloadList = new ArrayList<createJobPayload>();
	   
	   for(createJobBean createJobBean:beanList) { 
		createJobPayload payload = createJobBeanMapper.mapper(createJobBean);
		payloadList.add(payload); 
	   }
	   
	   for(createJobPayload payload :payloadList) {
		   System.out.println(payload);
	   }
	   

	}

}
