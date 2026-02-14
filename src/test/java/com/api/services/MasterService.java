package com.api.services;

import static io.restassured.RestAssured.given;

import com.api.Utils.SpecUtil;
import com.api.constant.Role;

import io.restassured.response.Response;

public class MasterService {
	
	private static final String MASTER_ENDPOINT ="/master";
	
	public Response master(Role role) {
	return given().spec(SpecUtil.requestSpecificationWithAuth(role))
	.contentType("")   //whenever we make post request RA added content type application/url-formecoded
	.when().post(MASTER_ENDPOINT);
	
	}	
}
