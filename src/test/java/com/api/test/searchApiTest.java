package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.Utils.JobNumberProvider;
import com.api.Utils.SpecUtil;
import com.api.constant.Role;
import com.api.request.model.search;
import com.api.services.JobService;

import io.restassured.response.Response;

public class searchApiTest {
	
	private JobService jobService;
	private String JOB_NUMBER = JobNumberProvider.getJobNumber();
	private search searchJobPayload;
	
	@BeforeMethod(description="Instantiating the job service and and creating search payload")
	public void setup() {
		jobService = new JobService();
		searchJobPayload = new search(JOB_NUMBER);
		
	}
     @Test(description="verify search job api test", groups = {"api","e2e","sanity"})
	 public void validateSearchApiTest() {
    	 jobService.Search(Role.FD, searchJobPayload)
    	 .then()
    	 .spec(SpecUtil.responseSpec_OK())
    	 .body("message", Matchers.equalToIgnoringCase("success"));
	 
}
    
}
