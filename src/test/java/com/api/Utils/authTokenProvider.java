package com.api.Utils;

import com.api.constant.Role;
import com.api.request.model.userCredentials;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;

public class authTokenProvider {
	
	private authTokenProvider() {
		
	}
	
	public static String getToken(Role role) {
		userCredentials usercredentials = null;
		
		  if(role == Role.FD) {
		 usercredentials = new userCredentials("iamfd","password");
		}
		else if(role == Role.SUP){
			usercredentials = new userCredentials("iamsup","password");
		}
		else if(role == Role.ENG){
			usercredentials = new userCredentials("iameng","password");
		}
		else if(role == Role.QC){
			usercredentials = new userCredentials("iamqc","password");
		}
		
	String token = given().baseUri(configManager.getProperty("BASE_URI"))
		.contentType(ContentType.JSON).accept(ContentType.ANY)
		.body(usercredentials).when().post("login")
		.then().log()
		.ifValidationFails()
		.statusCode(200)
		.extract()
		.body()
		.jsonPath()
		.getString("data.token");
       System.out.println("extracted the token");
	  
	  return token;
	}

}
