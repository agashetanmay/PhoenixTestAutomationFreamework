package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.api.Utils.configManager;

public class DatabaseManagerOLD {
//double locking design pattern 
	public static final String DB_URL = configManager.getProperty("DB_URL");
	public static final String DB_USERNAME = configManager.getProperty("DB_USERNAME");
	public static final String DB_PASSWORD = configManager.getProperty("DB_PASSWORD");
	public volatile static Connection connection; //any update happen to connection variable 
	// all the thread will aware of it//

	private DatabaseManagerOLD() {
		/// make this class as singleton by introducing private constructor
	}

	// to make this method thread safe we have introduce synchronized keyword
	public static void connection() throws SQLException {

		if (connection == null) {  // first check which all parallel thread entered
			synchronized (DatabaseManagerOLD.class) {
				if (connection == null) { // to create the connection object once
					connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
					System.out.println(connection);
				}
			}
		}

	}

}

// things to learn from this class volatile,synchronized and double locking design patterns
