package com.intedio.test.service.impl;

import java.io.IOException;
import java.util.Map;

import com.intedio.test.pojo.BusinessModel;
import com.intedio.test.service.Tester;
import com.intedio.test.util.OPERATION;
import com.intedio.test.util.SocketClient;

/**
 * 遮阳板测试工具
 * @author dimen
 * 测试用例：
 * 【1】遮阳板数量：0	可信度：10	快照时间：（暂时关闭）	结果：不报警
 * 【2】遮阳板数量：1	可信度：10	快照时间：（暂时关闭） 结果：报警
 * 【3】遮阳板数量：-1	可信度：10	快照时间：（暂时关闭） 结果：不报警
 * 
 */
public class SunSheldWarnTester implements Tester {
	

	@Override
	public boolean initDatabase(){
		return true;
	}
	
	/**
	 * 遮阳板
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void test1() throws IOException, InterruptedException{
		SocketClient client = new SocketClient("192.168.1.192", 9888);
		BusinessModel.getInstance(OPERATION.intesv).
				setSunNum("2").
				setSunScore("100").
				setSnapShotTime(System.currentTimeMillis()+"");
		Map<String, Object> map = BusinessModel.getMap();
		client.sendSocketData(map);
		client.start();
//		client.receiveSocket();
	}
	
	/**
	 * 一牌多车
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void test2() throws IOException, InterruptedException{
		SocketClient client = new SocketClient("127.0.0.1", 9888);
		BusinessModel.getInstance(OPERATION.intesv).
				setCarPlateNumber("鄂s011123").setCarPlateNumber2("鄂s011123").
				setSF_CarFamily("-13").
				setSnapShotTime(System.currentTimeMillis()+"");
		Map<String, Object> map = BusinessModel.getMap();
		client.sendSocketData(map);
		client.start();
//		client.receiveSocket();
	}
	
	/**
	 * 假套牌
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void test3() throws IOException, InterruptedException{
		SocketClient client = new SocketClient("127.0.0.1", 9888);
		BusinessModel.getInstance(OPERATION.intesv).
				setSF_CarFamily("-90").setSF_CarBrand("10").
				setCarPlateNumber("蒙G14984556").setCarPlateNumber2("蒙G14984556").
				setSnapShotTime(System.currentTimeMillis()+"");
		Map<String, Object> map = BusinessModel.getMap();
		client.sendSocketData(map);
		client.start();
//		client.receiveSocket();
	}
	
	/**
	 * 误检类
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void test4() throws IOException, InterruptedException{
		SocketClient client = new SocketClient("127.0.0.1", 9888);
		BusinessModel.getInstance(OPERATION.intesv)
				.setSF_CarStyleCode("4682ECD6FF02460aB1DC0A5CA8D85BEE");
		Map<String, Object> map = BusinessModel
				.getMap();
		client.sendSocketData(map);
		client.start();
//		client.receiveSocket();
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		test1();
//		test2();
//		test3();
//		test4();
	}

}
