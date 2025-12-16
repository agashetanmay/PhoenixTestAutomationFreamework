package com.api.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class configManager {

	private static String path;
	private static String env;

	private configManager() {
		// private constructor so that we can not create object of configManager
	}

	private static Properties prop = new Properties();

	static {
		env = System.getProperty("env", "qa"); // default env is qa id user not pass value from terminal
												// for example: mvn test -Denv=qa
		System.out.println("executing test from " + env + " env");

		switch (env.toLowerCase().trim()) {

		case "dev"-> path = "cofig/config.dev.properties";

		case "qa"-> path = "cofig/config.qa.properties";

		case "uat"->path = "cofig/config.uat.properties";
		
		default-> path = "config/config.qa.properties";

		}

		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

		if (input == null) {
			throw new RuntimeException("can not find file at path " + path);
		}
		try {
			prop.load(input);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		return prop.getProperty(key);

	}
}
