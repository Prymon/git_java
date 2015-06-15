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

		// ��¼debug�������Ϣ
		logger.debug("This is debug message.");
		// ��¼info�������Ϣ
		logger.info("This is info message.");
		// ��¼error�������Ϣ
		logger.error("This is error message.");
	}
}