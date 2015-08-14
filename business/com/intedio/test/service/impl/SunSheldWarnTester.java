package com.intedio.test.service.impl;

import com.intedio.test.service.Tester;

/**
 * 遮阳板测试工具
 * @author dimen
 * 测试用例：
 * 遮阳板数量：0	可信度：10	快照时间：（暂时关闭）	结果：不报警
 * 遮阳板数量：1	可信度：10	快照时间：（暂时关闭） 结果：报警
 * 遮阳板数量：-1	可信度：10	快照时间：（暂时关闭） 结果：不报警
 * 
 */
public class SunSheldWarnTester implements Tester {
	

	@Override
	public boolean initDatabase(){
		return true;
	}
	
	public boolean test1(){
		
		
		return false;
	}
	
	
	
	public static void main(String[] args) {

	}

}
