package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.database.DatabaseManager;
import com.database.model.CustomerProductDBModel;

public class CustomerProductDao {
	
	private static final String PRODUCT_QUERRY = """
			
			select * from tr_customer_product where id =?;
			
			""";
	
	CustomerProductDao(){}
	
	public static CustomerProductDBModel getProductInfo(int customer_product_id) {
		CustomerProductDBModel customerProductDBmodel = null;
		try {
		Connection conn = DatabaseManager.getConnection();
		
		 PreparedStatement preparedStatement = conn.prepareStatement(PRODUCT_QUERRY);
		 preparedStatement.setInt(1, customer_product_id);
		 ResultSet  resultset = preparedStatement.executeQuery();
		 while(resultset.next()) {
			 
			customerProductDBmodel = new CustomerProductDBModel(
					 resultset.getInt("id"),
					 resultset.getInt("tr_customer_id"), 
					 resultset.getInt("mst_model_id"), 
					 resultset.getString("dop"), 
					 resultset.getString("popurl"),  
					 resultset.getString("imei2"),  
					 resultset.getString("imei1"),
					 resultset.getString("serial_number"));	
		 }
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return customerProductDBmodel;
		
	}
}
