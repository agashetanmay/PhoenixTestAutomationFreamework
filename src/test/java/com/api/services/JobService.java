package com.api.services;

import static io.restassured.RestAssured.given;

import com.api.Utils.SpecUtil;
import com.api.constant.Role;

import io.restassured.response.Response;

public class JobService {

	private static final String CREATE_JOB_ENDPOINT = "/job/create";

	private static final String SEARCH_JOB_ENDPOINT = "/job/search";

	public Response Create(Role role, Object createjobpayload) {

		Response response = given().spec(SpecUtil.requestSpecificationWithAuthAndPayload(role, createjobpayload)).when()
				.post(CREATE_JOB_ENDPOINT);
		return response;
	}

	public Response Search(Role role, Object searchjobpayload) {

		Response response = given().spec(SpecUtil.requestSpecificationWithAuthAndPayload(role, searchjobpayload)).when()
				.post(SEARCH_JOB_ENDPOINT);
		return response;
	}

}
