package com.bhk.app;

import java.io.IOException;


import com.bhk.util.HttpEventLogger;

public class LoggerClass {

	public static void main(String[] args) {		
		
		LoggerClass obj = new LoggerClass();
		obj.doLog();
		System.out.println("The work is completed...2");
		//System.exit(0);
	}
	
	public void doLog() {
		try {		
			
			HttpEventLogger log = HttpEventLogger.getInstance();
			log.info("This is a debug message from MyApplication");
			log.info("This is a info message from MyApplication");
			log.trace("This is a trace message from MyApplication");
			log.error("This is a error message from MyApplication");
			log.warn("This is a warn message from MyApplication");
			
			//TestUtil.deleteHttpEventCollectorToken("Log4j2Test");
			//System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e);
		}
		System.out.println("The work is completed...");
	}

}
