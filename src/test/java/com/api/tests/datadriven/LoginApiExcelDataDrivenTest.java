package com.api.tests.datadriven;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.Utils.SpecUtil;
import com.api.services.AuthService;
import com.dataProviders.api.bean.userBean;

import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginApiExcelDataDrivenTest {

	private AuthService authService;

	@BeforeMethod(description = "Initializing the Auth service")
	public void setup() {
		authService = new AuthService(); // created the object of Auth service class
	}

	@Test(description = "verify if login api working for FD user", groups = { "api", "regression", "smoke",
			"login" }, dataProviderClass = com.dataProviders.DataProviderUtils.class, dataProvider = "loginAPIExceldataProvider")
	public void loginApiTest(userBean userbean) throws IOException {
		authService.login(userbean).then().spec(SpecUtil.responseSpec_OK())
		.body("message", Matchers.equalTo("Success")).and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Response-schema/loginResponseSchema.json"));

	}

}
