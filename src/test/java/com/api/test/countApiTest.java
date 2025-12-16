package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.Utils.authTokenProvider;
import com.api.Utils.configManager;
import com.api.constant.Role;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class countApiTest {
	
	@Test
	public void verfiyCountAPI() {
		
		given().baseUri(configManager.getProperty("BASE_URI")).and()
		.header("Authorization",authTokenProvider.getToken(Role.FD))
		.accept(ContentType.ANY).and()
		.when().get("/dashboard/count")
		.then()
		.statusCode(200)
		.and().log().all()
		.body("message", Matchers.equalTo("Success"))
		.time(Matchers.lessThan(15000L))
		.body("data",Matchers.notNullValue())
		.body("data.size()",Matchers.equalTo(3))
		.body("data.count", Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
		.body("data.label",Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
		.body("data.key", Matchers.containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Response-schema/countResponseSchema.json"));
		
		
	}
	@Test
	public void countAPITestMissingAuthToken() {
		given().baseUri(configManager.getProperty("BASE_URI")).and().when().get("/dashboard/count")
		.then().log().all().statusCode(401);
	}
	

}
