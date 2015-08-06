package com.deamon.mybatis.test;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.PropertyConfigurator;

import com.deamon.mybatis.pojo.User;

public class Demo {
	public static void main(String[] args) {
		try {
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void init() throws IOException {
		PropertyConfigurator.configure("conf/mybatis_log.properties");
		String resource = "com/deamon/mybatis/test/config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = sqlSessionFactory.openSession();
		
		session.getConfiguration().addMapper(UserMapper.class);
		UserMapper mapper = session.getMapper(UserMapper.class);
		System.out.println(mapper.findById("admin"));
	}
}