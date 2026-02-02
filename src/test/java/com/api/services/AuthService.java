package com.api.services;

import static io.restassured.RestAssured.given;
import com.api.Utils.SpecUtil;
import io.restassured.response.Response;
public class AuthService {

	private static final String LOGIN_ENDPOINT = "login";

	public Response login(Object user) {

		Response response = given().spec(SpecUtil.requestSpec(user)).when().post(LOGIN_ENDPOINT);
		return response;
	}

}
