package com.api.test;

import com.api.Utils.configManager;
import com.api.pojo.userCredentials;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class loginApiTest {
	@Test
	public void loginApiTest() throws IOException {
		
		userCredentials user = new userCredentials("iamfd","password");
		
	    given().baseUri(configManager.getProperty("BASE_URI")).and().contentType(ContentType.JSON)
	     .accept(ContentType.ANY)
	     .body(user).when().post("login").then().statusCode(200).and()
	     .log().body()
	     .log().all()
	     .body("message",Matchers.equalTo("Success")).and()
	     .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Response-schema/loginResponseSchema.json"));
	     
	   
	     
	}

}
