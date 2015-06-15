package com.deamon.log4j;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogTest {
	private static Logger logger = Logger.getLogger(LogTest.class);

	public static void main(String[] args) throws MalformedURLException {
		PropertyConfigurator.configure("conf/log4j.properties");
		System.out.println("This is println message.");

		// 记录debug级别的信息
		logger.debug("This is debug message.");
		// 记录info级别的信息
		logger.info("This is info message.");
		// 记录error级别的信息
		logger.error("This is error message.");
	}
}