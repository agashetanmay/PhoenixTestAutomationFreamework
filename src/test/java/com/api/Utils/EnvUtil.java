package com.api.Utils;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvUtil {

	public static Dotenv dotenv;
	
	static {
		dotenv = Dotenv.load();  // this block will execute first and initialize the dotenv variable
	}
	
	private EnvUtil() {}
	
	
	public static String getValue(String varName) {
		return dotenv.get(varName);
	}
	
	
}
