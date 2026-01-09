package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.database.DatabaseManager;
import com.database.model.CustomerDBModel;

public class CustomerDao {
	
	
	private static final String CUSTOMER_DETAIL_QUERRY= """
			select * from tr_customer where id= ?
			""";
	
	private CustomerDao() {}
	
	public static CustomerDBModel getCustomerInfo(int customerId) {
		CustomerDBModel customerDBModel = null;
	try {
		Connection connection = DatabaseManager.getConnection();
		PreparedStatement preparedStatement =  connection.prepareStatement(CUSTOMER_DETAIL_QUERRY);
		preparedStatement.setInt(1, customerId);
		ResultSet resultset = preparedStatement.executeQuery();
		
		while(resultset.next()) {
			
		customerDBModel = new CustomerDBModel(resultset.getInt("id"),
				resultset.getString("first_name"),
				resultset.getString("last_name"),
				resultset.getString("mobile_number"),
				resultset.getString("mobile_number_alt"),
				resultset.getString("email_id"),
				resultset.getString("email_id_alt"),
				resultset.getInt("tr_customer_address_id"));		
		}
		}catch(SQLException e) {
		System.err.print(e.getMessage());
		}
		return customerDBModel;
		
		
	}
	
	

}
