package com.api.test;

import static io.restassured.RestAssured.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.Utils.authTokenProvider;
import com.api.Utils.configManager;
import com.api.constant.Role;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class userDetailsAPITest {
	@Test
	public void userDetailsAPITest() throws IOException {
		
		Header header = new Header("Authorization",authTokenProvider.getToken(Role.FD));
		
		given().baseUri(configManager.getProperty("BASE_URI")).and()
		.header(header).and()
		.accept(ContentType.JSON)
		.when().get("userdetails")
		.then().statusCode(200).and().log().all().and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Response-schema/userDetailsUserSchema.json"));
		
	}

}
