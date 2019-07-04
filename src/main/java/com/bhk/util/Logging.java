package com.bhk.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;



public class Logging {

	private static Logging log = null;
	private Logger LOGGER = LogManager.getLogger("splunk.logger");
	final LoggerContext ctx = (LoggerContext) LogManager.getContext(true);
	public Logging() {		
		
		//LOGGER = ctx.getLogger("splunk.logger");
		 /*ServiceArgs loginArgs = new ServiceArgs();
		 loginArgs.setUsername("admin");
		 loginArgs.setPassword("p@ssw0rd");
		 loginArgs.setHost("pc258180");
		 loginArgs.setPort(8089);*/
	}

	

	public void debug(String message) {
		logMessage(message, 500);
	}

	public void error(String message) {
		logMessage(message, 200);
	}

	public void info(String message) {
		logMessage(message, 400);
	}

	public void warn(String message) {
		logMessage(message, 300);
	}

	public void fatal(String message) {
		logMessage(message, 100);
	}

	public void trace(String message) {
		logMessage(message, 600);
	}

	public void logMessage(String strMessage, int level) {
		try {
			// LOGGER.trace("Entering Log4j Example.");

			switch (level) {			
			case 200:
				// FOR ERROR
				LOGGER.error(strMessage);
				break;
			case 300:
				// FOR WARN
				LOGGER.warn(strMessage);
				break;
			case 400:
				// FOR INFO
				LOGGER.info(strMessage);
				break;
			case 500:
				// FOR DEBUG
				LOGGER.debug(strMessage);
				break;
			case 600:
				// FOR TRACE
				LOGGER.trace(strMessage);
				break;
			default:
				LOGGER.info(strMessage);
				break;
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
