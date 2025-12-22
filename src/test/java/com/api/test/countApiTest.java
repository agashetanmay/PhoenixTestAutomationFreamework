package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.Utils.SpecUtil;
import com.api.Utils.authTokenProvider;
import com.api.Utils.configManager;
import com.api.constant.Role;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class countApiTest {
	
	@Test(description = "verify the count API giving correct response", groups = { "api", "regression", "smoke"})
	public void verfiyCountAPI() {
		
		given().spec(SpecUtil.requestSpecificationWithAuth(Role.FD))
		.when().get("/dashboard/count")
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.body("message", Matchers.equalTo("Success"))
		.time(Matchers.lessThan(15000L))
		.body("data",Matchers.notNullValue())
		.body("data.size()",Matchers.equalTo(3))
		.body("data.count", Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
		.body("data.label",Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
		.body("data.key", Matchers.containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Response-schema/countResponseSchema.json"));
		
		
	}
	@Test(description = "verify the count API giving correct response for missing auth token", groups = { "api", "negative","regression", "smoke"})
	public void countAPITestMissingAuthToken() {
		given().spec(SpecUtil.requestSpec()).when().get("/dashboard/count")
		.then().spec(SpecUtil.responseSpec_TEXT(401));
	}
	

}
