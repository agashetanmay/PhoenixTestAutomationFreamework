package com.api.Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.request.model.createJobPayload;
import com.github.javafaker.Faker;

public class FakerDataGenerator {
  private static Faker faker = new Faker(new Locale("en-IND"));
  private final static Random RANDOM = new Random();
  private final static int MST_SERVICE_LOCATION_ID = 0;
  private final static int MST_PLATFORM_ID = 2;
  private final static int MST_WARRENTY_STATUS_ID = 1;
  private final static int MST_OEM_ID = 1;
  private final static int PRODUCT_ID =1;
  private final static int MODEL_ID =1;
  private final static int validProblemId [] = {1,2,3,4,5,6,7,8,9,10,11,12,15,16,17,19,20,22,24,26,27,28,29};
	
  private  FakerDataGenerator() {}

	public static createJobPayload generateFakecreateJobdata() {
		
		Customer customer = generateFakeCustomerdata();
		CustomerAddress customerAddress = generateFakecustomerAddressdata();
		CustomerProduct customerProduct = generateFakeCustomerProductData();
		List<Problems> problemList =generateFakeProblemListData();
		
		createJobPayload payload = new createJobPayload(MST_SERVICE_LOCATION_ID,MST_PLATFORM_ID, MST_WARRENTY_STATUS_ID, MST_OEM_ID, customer, customerAddress, customerProduct, problemList);
		return payload;
		
	}
	
      public static Iterator<createJobPayload> generateFakecreateJobdata(int count) {
		List<createJobPayload> payloadList = new ArrayList<createJobPayload>();
		
    	  for(int i=0;i<=count;i++) {
		Customer customer = generateFakeCustomerdata();
		CustomerAddress customerAddress = generateFakecustomerAddressdata();
		CustomerProduct customerProduct = generateFakeCustomerProductData();
		List<Problems> problemList =generateFakeProblemListData();
		createJobPayload payload = new createJobPayload(MST_SERVICE_LOCATION_ID,MST_PLATFORM_ID, MST_WARRENTY_STATUS_ID, MST_OEM_ID, customer, customerAddress, customerProduct, problemList);
		payloadList.add(payload);
		}
    	return payloadList.iterator();
		
	}
		
		public static Customer generateFakeCustomerdata() {
			String firstName =	faker.name().firstName();
			String lastName =	faker.name().lastName();
			String mobileNumber = faker.numerify("77########");
			String altMobileNumber = faker.numerify("75########");
			String emailAddress = faker.internet().emailAddress();
			String altEmailAddress = faker.internet().emailAddress();
			
			Customer customer =new Customer(firstName, lastName, mobileNumber, altMobileNumber, emailAddress , altEmailAddress);
			 return customer;
			
		}
		
		public static CustomerAddress generateFakecustomerAddressdata() {
			String flatNumber = faker.numerify("A-###");
			String appartmentName = faker.address().streetName();
			String streetName = faker.address().streetName();
			String landMark = faker.address().streetName();
			String area = faker.address().streetAddress();
			String pincode= faker.numerify("#####");
			String country = faker.address().country();
			String state = faker.address().state();
			
			CustomerAddress customerAddress = new CustomerAddress(flatNumber, appartmentName, streetName, landMark,  area, pincode, country, state); 
			return customerAddress;
		}
		
		public static CustomerProduct generateFakeCustomerProductData() {
			String dop = DateTimeUtil.getTimeWithDaysAgo(10);
			String imeiSerialNumber = faker.numerify("###############");
			String popurl = faker.internet().url();
			CustomerProduct customerProduct = new CustomerProduct(dop, imeiSerialNumber, imeiSerialNumber, imeiSerialNumber, popurl, PRODUCT_ID, MODEL_ID );
			return customerProduct; 
		}
		
		
		public static List<Problems> generateFakeProblemListData() {
		int count = RANDOM.nextInt(3);
		List<Problems> problemList = new ArrayList<Problems>();
		int randomIndex;
		String Remark;
		Problems problem;
		
		for(int i=0;i<=count;i++){
	     randomIndex = RANDOM.nextInt(validProblemId.length);
		 Remark = faker.lorem().sentence(4);
		 problem = new Problems(validProblemId[randomIndex], Remark);
		 problemList.add(problem);
		}
		return problemList;
		
	}
}
