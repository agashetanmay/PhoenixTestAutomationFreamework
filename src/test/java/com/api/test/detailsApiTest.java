package com.api.test;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.Utils.SpecUtil;
import com.api.constant.Role;
import com.api.request.model.Details;
import com.api.services.DashboardService;

public class detailsApiTest {

	private DashboardService dashboardService;
	private Details detailsPayload;

	@BeforeMethod(description = "Instantiating the dashboard service and create the details payload")
	public void setup() {
		dashboardService = new DashboardService();
		detailsPayload = new Details("created_today");
	}
    
	@Test(description="verify if details API working properly", groups= {"api","smote","e2e"})
	public void detailAPITest() {
         dashboardService.details(Role.FD, detailsPayload)
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.body("message", Matchers.equalToIgnoringCase("success"));
        
		
		
	}
}