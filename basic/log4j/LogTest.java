package log4j;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogTest {
	private static Logger logger = Logger.getLogger(LogTest.class);

	public static void main(String[] args) throws MalformedURLException {
		//加载配置文件
		PropertyConfigurator.configure("conf/log4j.properties");

		//开始logging
		System.out.println("starting log");
		new Logs2().logging("wel logs 2");
		
		// 记录debug级别的信息
		logger.debug("This is debug message.");
		// 记录info级别的信息
		logger.info("This is info message.");
		// 记录error级别的信息
		logger.error("This is error message.");
	}
}

class Logs2{
	private static Logger logger = Logger.getLogger(Logs2.class);
	
	public void logging(String msg){
		logger.info("Logs2 info test,get info: "+msg);
	}
}