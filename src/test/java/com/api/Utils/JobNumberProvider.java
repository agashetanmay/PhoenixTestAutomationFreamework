package com.api.Utils;

import java.util.ArrayList;
import java.util.List;

import com.api.constant.Model;
import com.api.constant.OEM;
import com.api.constant.Platform;
import com.api.constant.Problem;
import com.api.constant.Product;
import com.api.constant.Role;
import com.api.constant.ServiceLocation;
import com.api.constant.Warrenty_status;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.request.model.createJobPayload;
import com.api.services.JobService;

public class JobNumberProvider {
	public static createJobPayload createjobpayload;
	public static JobService jobService;
	
	public static String getJobNumber() {
		
		Customer customer = new Customer("tanmay", "agashe", "6757898909", "", "tanmay@gmail.com", "");
		CustomerAddress customeraddress = new CustomerAddress("123 DP ROAD", "ASD APT", "zxs", "ZXC", "qwe", "334356", "India", "Chhattisgarh");
		CustomerProduct customerproduct = new CustomerProduct(DateTimeUtil.getTimeWithDaysAgo(10), "55346567890444", "55346567890444", "55346567890444", DateTimeUtil.getTimeWithDaysAgo(10), 
		Product.NEXUS_2.getCode(), Model.Nexus2_Blue.getCode());
		
		Problems problems = new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(), "smartphone is running slow");
		
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problems);
		
		createjobpayload = new createJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(), Platform.FRONT_DESK.getCode(), Warrenty_status.IN_WARRENTY.getCode(), OEM.GOOGLE.getCode(),
		customer,customeraddress,customerproduct,problemList);
		
		jobService = new JobService();  // from service class
		
		String JobNumber = jobService.Create(Role.FD, createjobpayload)
		.then().log().all().spec(SpecUtil.responseSpec_OK())
		.extract().body().jsonPath().getString("data.job_number");
		
		return JobNumber;
		
	}

}
