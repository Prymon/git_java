package log4j;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogTest {
	private static Logger logger = Logger.getLogger(LogTest.class);

	public static void main(String[] args) throws MalformedURLException {
		//���������ļ�
		PropertyConfigurator.configure("conf/log4j.properties");

		//��ʼlogging
		System.out.println("starting log");
		new Logs2().logging("wel logs 2");
		
		// ��¼debug�������Ϣ
		logger.debug("This is debug message.");
		// ��¼info�������Ϣ
		logger.info("This is info message.");
		// ��¼error�������Ϣ
		logger.error("This is error message.");
	}
}

class Logs2{
	private static Logger logger = Logger.getLogger(Logs2.class);
	
	public void logging(String msg){
		logger.info("Logs2 info test,get info: "+msg);
	}
}