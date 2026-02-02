package com.api.services;
import static io.restassured.RestAssured.given;
import com.api.Utils.SpecUtil;
import com.api.constant.Role;
import io.restassured.response.Response;

public class DashboardService {

	private static final String COUNT_ENDPOINT = "/dashboard/count";

	public Response count(Role role) {
	Response response = given().spec(SpecUtil.requestSpecificationWithAuth(role)).when().get(COUNT_ENDPOINT);
		return response;  
}
	
	public Response countWithNoAuth() {
		Response response = given().spec(SpecUtil.requestSpec()).when().get(COUNT_ENDPOINT);
		return response;  
	}
}
