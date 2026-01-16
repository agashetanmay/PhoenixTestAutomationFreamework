package com.database;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvRunner {

	public static void main(String[] args) {
		Dotenv data = Dotenv.load();  //this will help to load the data from .env file 
		String Url = data.get("DB_URL");
		System.out.println(Url);

	}

}
