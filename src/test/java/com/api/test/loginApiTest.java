package com.api.test;

import com.api.Utils.SpecUtil;
import com.api.Utils.configManager;
import com.api.request.model.userCredentials;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class loginApiTest {
	userCredentials user;

	@BeforeMethod(description = "create the payload for kogin API")
	public void setup() {
		user = new userCredentials("iamfd", "password");
	}

	@Test(description = "verify if login api working for FD user", groups = { "api", "regression", "smoke" })
	public void loginApiTest() throws IOException {
		given().spec(SpecUtil.requestSpec(user))
		.when().post("login")
		.then().spec(SpecUtil.responseSpec_OK())
		.body("message", Matchers.equalTo("Success")).and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Response-schema/loginResponseSchema.json"));

	}

}
