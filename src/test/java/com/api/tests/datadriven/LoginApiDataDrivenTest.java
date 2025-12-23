package com.api.tests.datadriven;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.Utils.SpecUtil;
import com.api.request.model.userCredentials;
import com.dataProviders.api.bean.userBean;

import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginApiDataDrivenTest {

	userCredentials user;

	@BeforeMethod(description = "create the payload for kogin API")
	public void setup() {
		user = new userCredentials("iamfd", "password");
	}

	@Test(description = "verify if login api working for FD user", groups = { "api", "regression", "smoke" },
			dataProviderClass =com.dataProviders.DataProviderUtils.class, dataProvider = "LoginAPIdataProvider")
	
	public void loginApiTest(userBean userbean) throws IOException {
		given().spec(SpecUtil.requestSpec(userbean))
		.when().post("login").then().spec(SpecUtil.responseSpec_OK())
		.body("message", Matchers.equalTo("Success")).and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Response-schema/loginResponseSchema.json"));

	}

}
