package com.api.test;

import static io.restassured.RestAssured.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.Utils.SpecUtil;
import com.api.Utils.authTokenProvider;
import com.api.Utils.configManager;
import com.api.constant.Role;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class userDetailsAPITest {
	
	@Test(description = "verify the user detail API response is showing correctely", groups = { "api", "regression", "smoke"})
	public void userDetailsAPITest() throws IOException {
		given().spec(SpecUtil.requestSpecificationWithAuth(Role.FD))
		.when()
		.get("userdetails")
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Response-schema/userDetailsUserSchema.json"));
		
	}

}
