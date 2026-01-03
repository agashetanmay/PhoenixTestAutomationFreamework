package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.api.Utils.configManager;
import com.database.dao.createJobPayloadDataDao;


public class JDBCdemo {
	
	public static void main(String[] args) throws SQLException  {
		
	Connection  connection =DriverManager.getConnection(configManager.getProperty("DB_URL"),configManager.getProperty("DB_USERNAME"),configManager.getProperty("DB_PASSWORD"));
	
	
	Statement statement = connection.createStatement();
	
	ResultSet resultset = statement.executeQuery("select first_name,last_name,mobile_number from tr_customer tc");
	
	while(resultset.next()) {
		
	String fn =	resultset.getString("first_name");
	String ln =	resultset.getString("last_name");
	String mobnum =	resultset.getString("mobile_number");
		
	    System.out.println(fn+" | "+ln+ " | " +mobnum);
	    
	    
	}
	
	}
	
}	
	
	


