package com.api.test;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.Utils.DateTimeUtil;
import com.api.Utils.FakerDataGenerator;
import com.api.Utils.SpecUtil;
import com.api.Utils.authTokenProvider;
import com.api.Utils.configManager;
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
import com.database.dao.CustomerDao;
import com.database.model.CustomerDBModel;
import com.github.javafaker.Faker;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class createJobApiTestWithFaker {

	createJobPayload createJobPayload;
	JobService jobService;
	
	@BeforeMethod(description = "create job api payload")
	public void Setup() {
		createJobPayload = FakerDataGenerator.generateFakecreateJobdata();
		jobService = new JobService();
	}
	
    @Test(description = "verify create JOB api is able to create Inwarrenty job", groups = { "api", "regression", "smoke" })
	public void verifyCreateJobApiTest() {
		int customerId = jobService.Create(Role.FD, createJobPayload)
		.then().log().all().spec(SpecUtil.responseSpec_OK())
		.body("message",Matchers.equalTo("Job created successfully. "))
		.body("data",Matchers.notNullValue())
		.body("data.job_number", Matchers.containsString("JOB_"))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Response-schema/createJobResponseSchema.json"))
		.extract().body().jsonPath().getInt("data.tr_customer_id");
		Customer expectedCustomerData = createJobPayload.customer();  // this will give us faker expected customer data
		
		CustomerDBModel actualCustomerDataInDB = CustomerDao.getCustomerInfo(customerId);
		
		Assert.assertEquals(actualCustomerDataInDB.getFirst_name(),expectedCustomerData.first_name());
		Assert.assertEquals(actualCustomerDataInDB.getLast_name(),expectedCustomerData.last_name());
		Assert.assertEquals(actualCustomerDataInDB.getMobile_number(),expectedCustomerData.mobile_number());
		Assert.assertEquals(actualCustomerDataInDB.getMobile_number_alt(),expectedCustomerData.mobile_number_alt());
		Assert.assertEquals(actualCustomerDataInDB.getEmail_id(),expectedCustomerData.email_id());
		Assert.assertEquals(actualCustomerDataInDB.getEmail_id_alt(),expectedCustomerData.email_id_alt());
		 
	}
	
	
}
