package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.api.Utils.configManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCPDemo {
	
	public static final String DB_URL = configManager.getProperty("DB_URL");
	public static final String DB_USERNAME = configManager.getProperty("DB_USERNAME");
	public static final String DB_PASSWORD = configManager.getProperty("DB_PASSWORD");

	public static void main(String[] args) throws SQLException {
		HikariConfig hikariConfig = new HikariConfig();
		
		hikariConfig.setJdbcUrl(DB_URL);
		hikariConfig.setUsername(DB_USERNAME);
		hikariConfig.setPassword(DB_PASSWORD);
		hikariConfig.setMaximumPoolSize(10);
		hikariConfig.setMinimumIdle(2);
		hikariConfig.setConnectionTimeout(10000);
		hikariConfig.setIdleTimeout(10000);
		hikariConfig.setMaxLifetime(1800000);
		hikariConfig.setPoolName("phoenix test automation pool");
		HikariDataSource ds = new HikariDataSource(hikariConfig);
		Connection conn = ds.getConnection();
//		Statement statement = conn.createStatement();
//		ResultSet rs = statement.execute Query("select first_name,last_name,mobile_number from tr_customer tc");
//		   
//		while(rs.next()) {
//	    System.out.println(rs.getString("first_name")+" | "+rs.getString("last_name")+" | "+rs.getString("mobile_number"));
//		   }   
		ds.close();
	}

}
