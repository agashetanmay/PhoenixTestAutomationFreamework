package com.api.tests.datadriven;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.Utils.SpecUtil;
import com.api.constant.Role;
import com.api.request.model.createJobPayload;
import com.dataProviders.api.bean.createJobBean;

import io.restassured.module.jsv.JsonSchemaValidator;

public class createJobApiDBDrivenTest {
	
	
    @Test(description = "verify create JOB api is able to create Inwarrenty job", groups = { "api", "regression", "smoke","csv","DB" },
    dataProviderClass = com.dataProviders.DataProviderUtils.class, dataProvider = "CreateJobAPIDBdataProvider"
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
