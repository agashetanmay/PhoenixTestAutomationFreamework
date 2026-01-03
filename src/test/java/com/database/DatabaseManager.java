package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.api.Utils.configManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseManager {
//double locking design pattern 
	public static final String DB_URL = configManager.getProperty("DB_URL");
	public static final String DB_USERNAME = configManager.getProperty("DB_USERNAME");
	public static final String DB_PASSWORD = configManager.getProperty("DB_PASSWORD");
	public static final int MAX_POOL_SIZE = Integer.parseInt(configManager.getProperty("MAX_POOL_SIZE"));
	public static final int MIN_IDLE_COUNT = Integer.parseInt(configManager.getProperty("MINIMUM_IDLE_COUNT"));
	public static final int CONNECTION_TIMEOUT_IN_SEC = Integer
			.parseInt(configManager.getProperty("CONNECTION_TIMEOUT_IN_SEC"));
	public static final int IDLE_TIMEOUT_IN_SEC = Integer.parseInt(configManager.getProperty("IDLE_TIMEOUT_SEC"));
	public static final int MAX_LIFETIME_IN_MIN = Integer.parseInt(configManager.getProperty("MAX_LIFETIME_IN_MIN"));
	public static final String HIKARI_CP_POOL_NAME = configManager.getProperty("HIKARI_CP_POOL_NAME");

	private static HikariConfig hikariConfig;
	private volatile static HikariDataSource hikariDataSource;
	public static Connection connection; // any update happen to connection variable
	// all the thread will aware of it//

	private DatabaseManager() {
		/// make this class as singleton by introducing private constructor
	}

	// to make this method thread safe we have introduce synchronized keyword
	public static void initializePool() {

		if (hikariDataSource == null) { // first check which all parallel thread entered and hikaridatasource value is created or not 
			synchronized (DatabaseManager.class) {  // synchronized is applied for all the block
				if (hikariDataSource == null) { // to create the connection object once
					hikariConfig = new HikariConfig();
					hikariConfig.setJdbcUrl(DB_URL);
					hikariConfig.setUsername(DB_USERNAME);
					hikariConfig.setPassword(DB_PASSWORD);
					hikariConfig.setMaximumPoolSize(MAX_POOL_SIZE);
					hikariConfig.setMinimumIdle(MIN_IDLE_COUNT);
					hikariConfig.setConnectionTimeout(CONNECTION_TIMEOUT_IN_SEC * 1000);
					hikariConfig.setIdleTimeout(IDLE_TIMEOUT_IN_SEC * 1000);
					hikariConfig.setMaxLifetime(MAX_LIFETIME_IN_MIN * 60 * 1000); // 30 min
					hikariConfig.setPoolName(HIKARI_CP_POOL_NAME);

					hikariDataSource = new HikariDataSource(hikariConfig);
				}
			}
		}

	}

	public static Connection getConnection() throws SQLException {
		
		if(hikariDataSource==null) {
			initializePool();
		}
		else if(hikariDataSource.isClosed()) {
			throw new SQLException("Hikari data source is closed");
		}
		try {
			connection = hikariDataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}

// things to learn from this class volatile,synchronized and double locking design patterns and hicari cp 
