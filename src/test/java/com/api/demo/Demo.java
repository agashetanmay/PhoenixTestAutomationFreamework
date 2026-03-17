package com.api.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Demo {

	public static void main(String[] args) {

    Logger logger = LogManager.getLogger(Demo.class);
    
    logger.info("starting the info message");
    
    logger.error("error message ");
	}

}
