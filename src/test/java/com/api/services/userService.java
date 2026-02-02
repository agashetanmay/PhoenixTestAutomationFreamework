package com.api.services;

import static io.restassured.RestAssured.given;

import com.api.Utils.SpecUtil;
import com.api.constant.Role;
import io.restassured.response.Response;

public class userService {

	private static final String USER_DETAILS_ENDPOINT = "/userdetails";

	public Response userDetails(Role role) {
    Response response = given().spec(SpecUtil.requestSpecificationWithAuth(role))
    		            .when().get(USER_DETAILS_ENDPOINT);
		return response;
	}

}
