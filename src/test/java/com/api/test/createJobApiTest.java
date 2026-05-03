package com.api.test;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.api.Utils.DateTimeUtil;
import com.api.Utils.SpecUtil;
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

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class createJobApiTest {
	createJobPayload createjobpayload;
	JobService jobService;
	@BeforeMethod(description = "creating create job api payload and instansiating create job service")
	public void Setup() {
		Customer customer = new Customer("tanmay", "agashe", "6757898909", "", "tanmay@gmail.com", "");
		CustomerAddress customeraddress = new CustomerAddress("123 DP ROAD", "ASD APT", "zxs", "ZXC", "qwe", "334356", "India", "Chhattisgarh");
		CustomerProduct customerproduct = new CustomerProduct(DateTimeUtil.getTimeWithDaysAgo(10), "89246567890366", "89246567890366", "89246567890366", DateTimeUtil.getTimeWithDaysAgo(10), 
		Product.NEXUS_2.getCode(), Model.Nexus2_Blue.getCode());
		
		Problems problems = new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(), "smartphone is running slow");
		Problems problems1 = new Problems(Problem.OVERHEATING.getCode(), "smartphone is overheating");
		
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problems);
		problemList.add(problems1);
	
		createjobpayload = new createJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(), Platform.FRONT_DESK.getCode(), Warrenty_status.IN_WARRENTY.getCode(), OEM.GOOGLE.getCode(),
		customer,customeraddress,customerproduct,problemList);
		
		jobService = new JobService();  // from service class
	}

	
    @Test(description = "verify create JOB api is able to create Inwarrenty job", groups = { "api", "regression", "smoke" })
	public void verifyCreateJobApiTest() {
    	   jobService.Create(Role.FD, createjobpayload)
		.then().log().all().spec(SpecUtil.responseSpec_OK())
		.body("message",Matchers.equalTo("Job created successfully. "))
		.body("data",Matchers.notNullValue())
		.body("data.job_number", Matchers.containsString("JOB_"))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Response-schema/createJobResponseSchema.json"));
		
	}   
}
