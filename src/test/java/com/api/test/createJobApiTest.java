package com.api.test;

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
import io.restassured.http.ContentType;

public class createJobApiTest {

	@Test
	public void verifyCreateJobApiTest() {
		
		Customer customer = new Customer("tanmay", "agashe", "6757898909", "", "tanmay@gmail.com", "");
		CustomerAddress customeraddress = new CustomerAddress("123 DP ROAD", "ASD APT", "zxs", "ZXC", "qwe", "334356", "India", "Chhattisgarh");
		CustomerProduct customerproduct = new CustomerProduct("2025-04-23T18:30:00.000Z", "22346567890346", "22346567890346", "22346567890346", "2025-04-23T18:30:00.000Z", 1, 1);
		
		Problems problems = new Problems(3, "display issue");
		Problems[] problemsArray = new Problems[1];
		problemsArray[0]= problems;
		
		createJobPayload createjobpayload = new createJobPayload(0, 2, 1, 1,customer,customeraddress,customerproduct,problemsArray);
		
		given()
		.spec(SpecUtil.requestSpecificationWithAuthAndPayload(Role.FD, createjobpayload))
		 .when().post("/job/create")
		 .then().log().all().spec(SpecUtil.responseSpec_OK());

	}
}
