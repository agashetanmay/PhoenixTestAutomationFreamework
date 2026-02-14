package com.api.Utils;

import static io.restassured.RestAssured.given;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.api.constant.Role;
import com.api.request.model.userCredentials;
import com.api.services.JobService;

import io.restassured.http.ContentType;

public class authTokenProvider {
	
	private static Map<Role,String> tokenCatch = new ConcurrentHashMap<Role,String>();  //ConcurrentHashMap is thread safe
	
	private authTokenProvider() {}   //private constructor
	
	public static String getToken(Role role) {
		userCredentials usercredentials = null;
		
		if(tokenCatch.containsKey(role)) {
			return tokenCatch.get(role);   // return the value(token) for specified role
		}
		
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
      
       tokenCatch.put(role, token);
	  return token;
	}
	
	
}
