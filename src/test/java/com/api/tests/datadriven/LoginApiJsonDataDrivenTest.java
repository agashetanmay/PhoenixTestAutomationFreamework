package com.api.tests.datadriven;

import java.io.IOException;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.api.Utils.SpecUtil;
import com.api.request.model.userCredentials;
import com.api.services.AuthService;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginApiJsonDataDrivenTest {

	private AuthService authService;

	@BeforeMethod(description = "Initializing the Auth service")
	public void setup() {
		authService = new AuthService(); // created the object of Auth service class
	}

	@Test(description = "verify if login api working for FD user", groups = { "api", "regression", "smoke","login","json" },
			dataProviderClass =com.dataProviders.DataProviderUtils.class, dataProvider = "loginAPIJsondataProvider")
	
	public void loginApiTest(userCredentials usercredentials) throws IOException {
		authService.login(usercredentials).then().spec(SpecUtil.responseSpec_OK())
		.body("message", Matchers.equalTo("Success")).and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Response-schema/loginResponseSchema.json"));
	}

}
