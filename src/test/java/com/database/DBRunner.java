package com.database;

import java.sql.Connection;
import java.sql.SQLException;

public class DBRunner {

	public static void main(String[] args) throws SQLException {
		
		long statrttime = System.currentTimeMillis();
		
		Connection conn = DatabaseManager.getConnection();
		System.out.println(conn);
	
		long endtime = System.currentTimeMillis();
		
		System.out.println("duration "+(endtime-statrttime));

	}

}
