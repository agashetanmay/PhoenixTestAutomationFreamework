package com.api.services;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.Utils.SpecUtil;
import com.api.constant.Role;

import io.restassured.response.Response;

public class MasterService {
	
	private static final String MASTER_ENDPOINT ="/master";
	 private static final Logger LOGGER = LogManager.getLogger(MasterService.class);
	
	public Response master(Role role) {
	LOGGER.info("making the request for {} for {}",MASTER_ENDPOINT, role );
	return given().spec(SpecUtil.requestSpecificationWithAuth(role))
	.contentType("")   //whenever we make post request RA added content type application/url-formecoded
	.when().post(MASTER_ENDPOINT);
	
	}	
}
