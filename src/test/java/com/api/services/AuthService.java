package com.api.services;

import static io.restassured.RestAssured.given;

import com.api.Utils.SpecUtil;
import com.api.request.model.userCredentials;

import io.restassured.response.Response;

public class AuthService {

	private static final String LOGIN_ENDPOINT = "login";

	public Response login(userCredentials user) {

		Response response = given().spec(SpecUtil.requestSpec(user)).when().post(LOGIN_ENDPOINT);
		return response;
	}

}
