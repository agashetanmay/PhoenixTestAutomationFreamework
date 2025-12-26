package com.api.tests.datadriven;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.Utils.DateTimeUtil;
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

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class createJobApiDataDrivenTest {
	
	
    @Test(description = "verify create JOB api is able to create Inwarrenty job", groups = { "api", "regression", "smoke" },
    dataProviderClass = com.dataProviders.DataProviderUtils.class, dataProvider = "CreateJobAPIdataProvider"
    )
	public void verifyCreateJobApiTest(createJobPayload createjobpayload) {
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
