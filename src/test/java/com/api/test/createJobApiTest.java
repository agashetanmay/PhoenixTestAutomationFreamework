package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.Utils.DateTimeUtil;
import com.api.Utils.SpecUtil;
import com.api.Utils.authTokenProvider;
import com.api.Utils.configManager;
import com.api.constant.Role;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.request.model.createJobPayload;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class createJobApiTest {

	@Test
	public void verifyCreateJobApiTest() {
		
		Customer customer = new Customer("tanmay", "agashe", "6757898909", "", "tanmay@gmail.com", "");
		CustomerAddress customeraddress = new CustomerAddress("123 DP ROAD", "ASD APT", "zxs", "ZXC", "qwe", "334356", "India", "Chhattisgarh");
		CustomerProduct customerproduct = new CustomerProduct(DateTimeUtil.getTimeWithDaysAgo(10), "92346567890346", "92346567890346", "92346567890346", DateTimeUtil.getTimeWithDaysAgo(10), 1, 1);
		
		Problems problems = new Problems(3, "display issue");
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problems);
		
		createJobPayload createjobpayload = new createJobPayload(0, 2, 1, 1,customer,customeraddress,customerproduct,problemList);
		
		given()
		.spec(SpecUtil.requestSpecificationWithAuthAndPayload(Role.FD, createjobpayload))
		.when().post("/job/create")
		.then().log().all().spec(SpecUtil.responseSpec_OK())
		.body("message",Matchers.equalTo("Job created successfully. "))
		.body("data",Matchers.notNullValue())
		.body("data.job_number", Matchers.containsString("JOB_"))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Response-schema/createJobResponseSchema.json"));
		
	}
	
	
}
