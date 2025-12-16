package com.api.test;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.Utils.authTokenProvider;
import com.api.Utils.configManager;
import com.api.constant.Role;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class masterApiTest {
	
	@Test
	public void VerifyMasterApiTest() {
		
		RestAssured.given().baseUri(configManager.getProperty("BASE_URI"))
		.header("Authorization",authTokenProvider.getToken(Role.FD))
		.accept(ContentType.ANY)
		.contentType("")   //whenever we make post request RA added content type application/url-formecoded
		.when().post("/master").then().log().all().statusCode(200)
		.and().time(Matchers.lessThan(1500L)).and()
		.body("message",Matchers.equalTo("Success"))
		.body("data",Matchers.notNullValue())
		.body("data", Matchers.hasKey("mst_oem"))
		.body("$",Matchers.hasKey("message"))   //$ we are using to check larger json
		.body("$",Matchers.hasKey("data"))
		.body("data.mst_oem.size()",Matchers.equalTo(2))
		.body("data.mst_model.size()",Matchers.greaterThan(0))
		.body("data.mst_oem.id",Matchers.everyItem(Matchers.notNullValue()))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Response-schema/masterResponseSchema.json"));
	}
	@Test
	public void MasterAPITestInvalidAuthToken() {
		given().baseUri(configManager.getProperty("BASE_URI")).and().header("Authorization","").and()
		.contentType("").
		when().post("/master")
		.then().log().all().statusCode(401);
	}
	
	

}
