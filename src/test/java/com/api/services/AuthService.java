package com.api.services;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.Utils.SpecUtil;
import io.restassured.response.Response;
public class AuthService {

	private static final String LOGIN_ENDPOINT = "login";
    private static final Logger LOGGER = LogManager.getLogger(AuthService.class);
	public Response login(Object user) {
     
		LOGGER.info("making login request for the payload {}",user);
		Response response = given()
				.spec(SpecUtil.requestSpec(user))
				.when()
				.post(LOGIN_ENDPOINT);
		return response;
	}

}
