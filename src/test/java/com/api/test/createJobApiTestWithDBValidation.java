package com.api.test;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.Utils.DateTimeUtil;
import com.api.Utils.SpecUtil;
import com.api.constant.Model;
import com.api.constant.OEM;
import com.api.constant.Platform;
import com.api.constant.Problem;
import com.api.constant.Product;
import com.api.constant.Role;
import com.api.constant.ServiceLocation;
import com.api.constant.Warrenty_status;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.request.model.createJobPayload;
import com.database.dao.CustomerDao;
import com.database.dao.customerAddressDao;
import com.database.dao.mapJobProblemDao;
import com.database.model.CustomerAddressDBModel;
import com.database.model.CustomerDBModel;
import com.database.model.CustomerProductDBModel;
import com.database.model.MapJobProblemDBModel;
import com.database.dao.CustomerProductDao;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class createJobApiTestWithDBValidation {
	
	Customer customer;
	CustomerAddress customeraddress;
	CustomerProduct customerproduct;
	Problems problems;
	createJobPayload createjobpayload;
	int customerId;
	int customerProductId;
	int tr_job_head_Id;
	
	@BeforeMethod(description = "create job api payload")
	public void Setup() {
		customer = new Customer("tanmay", "agashe", "6757898909", "", "tanmay@gmail.com", "");
		customeraddress = new CustomerAddress("123 DP ROAD", "ASD APT", "zxs", "ZXC", "qwe", "334356", "India", "Chhattisgarh");
		customerproduct = new CustomerProduct(DateTimeUtil.getTimeWithDaysAgo(10), "69346567891000", "69346567891000", "69346567891000", DateTimeUtil.getTimeWithDaysAgo(10), 
		Product.NEXUS_2.getCode(), Model.Nexus2_Blue.getCode());
		
		problems = new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(), "smartphone is running slow");
		
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problems);
		
		createjobpayload = new createJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(), Platform.FRONT_DESK.getCode(), Warrenty_status.IN_WARRENTY.getCode(), OEM.GOOGLE.getCode(),
		customer,customeraddress,customerproduct,problemList);
	}

	
    @Test(description = "verify create JOB api is able to create Inwarrenty job", groups = { "api", "regression", "smoke" })
	public void verifyCreateJobApiTest() {
		Response response= given()
		.spec(SpecUtil.requestSpecificationWithAuthAndPayload(Role.FD, createjobpayload))
		.when().post("/job/create")
		.then().log().all().spec(SpecUtil.responseSpec_OK())
		.body("message",Matchers.equalTo("Job created successfully. "))
		.body("data",Matchers.notNullValue())
		.body("data.job_number", Matchers.containsString("JOB_"))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Response-schema/createJobResponseSchema.json"))
//		.extract().body().jsonPath().getInt("data.tr_customer_id");
		.extract().response();
		
		customerId = response.then().extract().body().jsonPath().getInt("data.tr_customer_id");
		customerProductId = response.then().extract().body().jsonPath().getInt("data.tr_customer_product_id");
		
		System.out.println(customerId);
				
		CustomerDBModel customerDataFromDB = CustomerDao.getCustomerInfo(customerId);
		System.out.println("--------------------------------------------");
		System.out.println(customerDataFromDB);
		
		Assert.assertEquals(customer.first_name(),customerDataFromDB.getFirst_name());
		Assert.assertEquals(customer.last_name(),customerDataFromDB.getLast_name());
		Assert.assertEquals(customer.mobile_number(),customerDataFromDB.getMobile_number());
		Assert.assertEquals(customer.mobile_number_alt(),customerDataFromDB.getMobile_number_alt());
		Assert.assertEquals(customer.email_id(),customerDataFromDB.getEmail_id());
		Assert.assertEquals(customer.email_id_alt(),customerDataFromDB.getEmail_id_alt());
		System.out.println("--------------------------------------------");
		System.out.println(customerDataFromDB.getTr_customer_address_id());
		
		CustomerAddressDBModel customerAddressDBModel = customerAddressDao.getCustomerAddressData(customerDataFromDB.getTr_customer_address_id());
		System.out.println(customerAddressDBModel);
		
		Assert.assertEquals(customerAddressDBModel.getFlat_number(),customeraddress.flat_number());
		Assert.assertEquals(customerAddressDBModel.getApartment_name(),customeraddress.apartment_name());
		Assert.assertEquals(customerAddressDBModel.getStreet_name(),customeraddress.street_name());
		Assert.assertEquals(customerAddressDBModel.getLandmark(),customeraddress.landmark());
		Assert.assertEquals(customerAddressDBModel.getArea(),customeraddress.area());
		Assert.assertEquals(customerAddressDBModel.getPincode(),customeraddress.pincode());
		Assert.assertEquals(customerAddressDBModel.getCountry(),customeraddress.country());
		Assert.assertEquals(customerAddressDBModel.getState(),customeraddress.state());
		
		CustomerProductDBModel customerProductDBModel = CustomerProductDao.getProductInfo(customerProductId);
		System.out.println(customerProductDBModel);
		//this will intentionally fail
		Assert.assertEquals(customerProductDBModel.getImei1(),customerproduct.imei1());
		Assert.assertEquals(customerProductDBModel.getImei2(),customerproduct.imei2());
		Assert.assertEquals(customerProductDBModel.getSerial_number(),customerproduct.serial_number());
		Assert.assertEquals(customerProductDBModel.getDop(),customerproduct.dop());
		Assert.assertEquals(customerProductDBModel.getMst_model_id(),customerproduct.mst_model_id());
		Assert.assertEquals(customerProductDBModel.getPopurl(),customerproduct.popurl());
		
		tr_job_head_Id = response.then().extract().body().jsonPath().getInt("data.id");
		
		MapJobProblemDBModel jobDataFrmDB = mapJobProblemDao.getProblemDetails(tr_job_head_Id);
		Assert.assertEquals(jobDataFrmDB.getMst_problem_id(),createjobpayload.problems().get(0).id());
		Assert.assertEquals(jobDataFrmDB.getRemark(),createjobpayload.problems().get(0).remark());
		
	}
	
	
}
