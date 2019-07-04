package com.bhk.util;
/*
 * Copyright 2013-2014 Splunk, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"): you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

import java.io.*;
import java.util.*;

import org.apache.logging.log4j.core.LoggerContext;
import org.json.simple.JSONObject;


import com.bhk.HttpEventCollectorErrorHandler;
import com.bhk.HttpEventCollectorEventInfo;

import org.apache.logging.log4j.Logger;

public final class HttpEventLogger {
    private String httpEventCollectorName = "Log4j2Test";
    List<List<HttpEventCollectorEventInfo>> errors = new ArrayList<List<HttpEventCollectorEventInfo>>();
    List<HttpEventCollectorErrorHandler.ServerErrorException> logEx = new ArrayList<HttpEventCollectorErrorHandler.ServerErrorException>();
    private Logger logger;
    private static HttpEventLogger log;
    List<String> msgs = new ArrayList<String>();
    
    private HttpEventLogger() {
    	
    }
    
    public static HttpEventLogger getInstance() throws IOException, InterruptedException, Exception {
    	if(log == null) {
    		log = new HttpEventLogger();
    		log.canSendEventUsingLog4j2();
    	}
    	
    	return log;
    }
    
    /**
     * sending a message via httplogging using log4j2 to splunk
     */       
    public void canSendEventUsingLog4j2() throws Exception, IOException, InterruptedException {
        TestUtil.enableHttpEventCollector();
        String token = TestUtil.createHttpEventCollectorToken(httpEventCollectorName);
        //String token = "0B72F06C-3A01-407B-9F3C-C57236727054";
        String loggerName = "MyApplicationLogger";
        HashMap<String, String> userInputs = new HashMap<String, String>();
        userInputs.put("user_logger_name", loggerName);
        userInputs.put("user_httpEventCollector_token", token);
        userInputs.put("user_host", "MyApplication");
        org.apache.logging.log4j.core.LoggerContext context = TestUtil.resetLog4j2Configuration("log4j2_template.xml", "log4j2.xml", userInputs);
        //use httplogger
       System.out.println("The Current Token ::: "+token);

        Date date = new Date();
        String jsonMsg = String.format("{EventDate:%s, EventMsg:'MyApplication Application started.}", date.toString());

        logger = context.getLogger(loggerName);
        logger.info(jsonMsg);
        msgs.add(jsonMsg);

        jsonMsg = String.format("{EventDate:%s, EventMsg:'MyApplication Application started.}", date.toString());
        logger.error(jsonMsg);
        msgs.add(jsonMsg);

        
        //TestUtil.verifyEventsSentToSplunk(msgs);
        //TestUtil.deleteHttpEventCollectorToken(httpEventCollectorName);
        //System.out.println("====================== Test pass=========================");
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
		
		Date date = new Date();
		String jsonMsg = String.format("{EventDate:%s, EventMsg:"+strMessage+"}", date.toString());
		 msgs.add(jsonMsg);		 
		try {
			// logger.trace("Entering Log4j Example.");

			switch (level) {			
			case 200:
				// FOR ERROR
				logger.error(jsonMsg);
				break;
			case 300:
				// FOR WARN
				logger.warn(jsonMsg);
				break;
			case 400:
				// FOR INFO
				logger.info(jsonMsg);
				logger.info(jsonMsg);
				break;
			case 500:
				// FOR DEBUG
				logger.debug(jsonMsg);
				break;
			case 600:
				// FOR TRACE
				logger.trace(jsonMsg);
				break;
			default:
				logger.info(jsonMsg);
				break;
			}
			//TestUtil.verifyEventsSentToSplunk(msgs);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

 }
