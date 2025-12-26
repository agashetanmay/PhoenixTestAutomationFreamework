package com.api.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.request.model.createJobPayload;
import com.github.javafaker.Country;
import com.github.javafaker.Faker;

public class FakerDemo2 {

	public static void main(String[] args) {
	Faker faker = new Faker(new Locale("en-IND"));
	
	String firstName =	faker.name().firstName();
	String lastName =	faker.name().lastName();
	String mobileNumber = faker.numerify("77########");
	String altMobileNumber = faker.numerify("75########");
	String emailAddress = faker.internet().emailAddress();
	String altEmailAddress = faker.internet().emailAddress();
	
	Customer customer =new Customer(firstName, lastName, mobileNumber, altMobileNumber, emailAddress , altEmailAddress);
	
	System.out.println(customer);
	String flatNumber = faker.numerify("A-###");
	String appartmentName = faker.address().streetName();
	String streetName = faker.address().streetName();
	String landMark = faker.address().streetName();
	String area = faker.address().fullAddress();
	String pincode= faker.numerify("#####");
	String country = faker.address().country();
	String state = faker.address().state();
	
	CustomerAddress customerAddress = new CustomerAddress(flatNumber, appartmentName, streetName, landMark,  area, pincode, country, state); 
	
	System.out.println(customerAddress);
	
	String dop = DateTimeUtil.getTimeWithDaysAgo(10);
	String imeiSerialNumber = faker.numerify("###############");
	String popurl = faker.internet().url();
	CustomerProduct customerProduct = new CustomerProduct(dop, imeiSerialNumber, imeiSerialNumber, imeiSerialNumber, popurl, 1, 1);
	
	System.out.println(customerProduct);
	
	Random random = new Random();
	int id = random.nextInt(26)+(1);
	String Remark= faker.lorem().sentence(5);
	
	Problems problem = new Problems(id, Remark);
	
	List<Problems> problemList = new ArrayList<Problems>();
	problemList.add(problem);
	System.out.println(problem);
	
	createJobPayload payload = new createJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemList);
	
	System.out.println(payload);
	}

	

}
