package com.api.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class configManager_OLD {
	
	private configManager_OLD() {
		// private constructor so that we can not create object of configManager
	}
	
	private static Properties prop = new Properties();
	
	static {
       File configFile = new File(System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"cofig"+File.separator+"config.properties");
	    FileReader reader = null;
		try {
			reader = new FileReader(configFile);
			prop.load(reader);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	      catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) {
	    return prop.getProperty(key);

	}
	
	
	

}
