package com.api.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.Utils.SpecUtil;
import com.api.constant.Role;
import com.api.services.userService;

import io.restassured.module.jsv.JsonSchemaValidator;

public class userDetailsAPITest {
	private userService userservices;
	
	@BeforeMethod
	public void setup() {
	userservices = new userService();
	}
	
	@Test(description = "verify the user detail API response is showing correctely", groups = { "api", "regression", "smoke"})
	public void userDetailsAPITest() {
		userservices.userDetails(Role.FD).then()
		.spec(SpecUtil.responseSpec_OK())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Response-schema/userDetailsUserSchema.json"));
		
	}

}
