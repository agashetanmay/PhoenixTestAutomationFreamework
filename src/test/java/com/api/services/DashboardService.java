package com.api.services;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.Utils.SpecUtil;
import com.api.constant.Role;
import io.restassured.response.Response;

public class DashboardService {

	private static final String COUNT_ENDPOINT = "/dashboard/count";
	private static final String DETAILS_ENDPOINT = "/dashboard/details";
	 private static final Logger LOGGER = LogManager.getLogger(DashboardService.class);
	 
	public Response count(Role role) {
	LOGGER.info("Making request to the {} for role {}",COUNT_ENDPOINT,role);
	Response response = given().spec(SpecUtil.requestSpecificationWithAuth(role)).when().get(COUNT_ENDPOINT);
		return response;  
}
	
	public Response countWithNoAuth() {
	LOGGER.info("Making request for no Auth to the {} ",COUNT_ENDPOINT);
		Response response = given().spec(SpecUtil.requestSpec()).when().get(COUNT_ENDPOINT);
		return response;  
	}
	public Response details(Role role,Object Payload) {
	LOGGER.info("Making request to the {} for role {}",DETAILS_ENDPOINT,role);
		Response response = given().spec(SpecUtil.requestSpecificationWithAuthAndPayload(role,Payload)).when().post(DETAILS_ENDPOINT);
			return response;  
	}
}
