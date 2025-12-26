package com.api.Utils;

import com.github.javafaker.Faker;

public class FakerDemo {

	public static void main(String[] args) {
		Faker faker = new Faker();
		
	String firstName =	faker.name().firstName();
	
	System.out.println(firstName);
	
	String lastName =	faker.name().lastName();
	
	System.out.println(lastName);
	
	
	String buildingNumber = faker.address().buildingNumber();
	 String streetAddress = faker.address().streetAddress();
	 
	String digit = faker.number().digits(11);
	
	System.out.println(digit);
	 
	 System.out.println(faker.numerify("123########"));
	}

}
