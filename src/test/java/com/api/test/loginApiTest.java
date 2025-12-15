package com.api.test;

import com.api.pojo.userCredentials;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class loginApiTest {
	@Test
	public void loginApiTest() {
		
		userCredentials user = new userCredentials("iamfd","password");
		
	    given().baseUri("http://64.227.160.186:9000/v1").and().contentType(ContentType.JSON)
	     .accept(ContentType.ANY)
	     .body(user).when().post("login").then().statusCode(200).and()
	     .log().body()
	     .log().all()
	     .body("message",Matchers.equalTo("Success")).and()
	     .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Response-schema/loginResponseSchema.json"));
	     
	   
	     
	}

}
