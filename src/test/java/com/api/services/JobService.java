package com.api.services;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.Utils.SpecUtil;
import com.api.constant.Role;

import io.restassured.response.Response;

public class JobService {

	private static final String CREATE_JOB_ENDPOINT = "/job/create";

	private static final String SEARCH_JOB_ENDPOINT = "/job/search";
	 private static final Logger LOGGER = LogManager.getLogger(JobService.class);
	 
	public Response Create(Role role, Object createjobpayload) {
		LOGGER.info("making the request for {} for {} and palyload is {}",CREATE_JOB_ENDPOINT, role,createjobpayload );
		Response response = given().spec(SpecUtil.requestSpecificationWithAuthAndPayload(role, createjobpayload)).when()
				.post(CREATE_JOB_ENDPOINT);
		return response;
	}

	public Response Search(Role role, Object searchjobpayload) {
		LOGGER.info("making the request for {} for {} and palyload is {}",SEARCH_JOB_ENDPOINT, role,searchjobpayload );
		Response response = given().spec(SpecUtil.requestSpecificationWithAuthAndPayload(role, searchjobpayload)).when()
				.post(SEARCH_JOB_ENDPOINT);
		return response;
	}

}
