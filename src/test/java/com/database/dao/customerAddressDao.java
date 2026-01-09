package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.database.DatabaseManager;
import com.database.model.CustomerAddressDBModel;

public class customerAddressDao {
	
	private static String CUSTOMER_ADDRESS_QUERRY = """
			select id,
			flat_number,
			apartment_name,
			street_name,
			landmark,
			area,
			pincode,
			country,
			state 
            from tr_customer_address 
            where id = ?
			"""; // ?= id will replace with placeholder 
	
	private customerAddressDao() {}

	public static CustomerAddressDBModel getCustomerAddressData(int customerAddressId) {
		CustomerAddressDBModel customerAddressDBModel=null;
		try {
			Connection conn = DatabaseManager.getConnection();
			
		PreparedStatement preparedStatement =conn.prepareStatement(CUSTOMER_ADDRESS_QUERRY);
		
		preparedStatement.setInt(1, customerAddressId);  // this help to asssign value to ? i.e Id
		 
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			
		customerAddressDBModel= new CustomerAddressDBModel(
					resultSet.getInt("id"),resultSet.getString("flat_number"),
					resultSet.getString("apartment_name"),resultSet.getString("street_name"),
					resultSet.getString("landmark"),
					resultSet.getString("area"),resultSet.getString("pincode"),resultSet.getString("country"),
					resultSet.getString("state"));
		}
		}
		catch(SQLException e) {
			e.printStackTrace();	
		}
		return customerAddressDBModel;
	}
}
