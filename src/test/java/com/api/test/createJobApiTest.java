package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.Utils.SpecUtil;
import com.api.Utils.authTokenProvider;
import com.api.Utils.configManager;
import com.api.constant.Role;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.pojo.createJobPayload;

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
		CustomerProduct customerproduct = new CustomerProduct("2025-04-23T18:30:00.000Z", "82346567890346", "82346567890346", "82346567890346", "2025-04-23T18:30:00.000Z", 1, 1);
		
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
