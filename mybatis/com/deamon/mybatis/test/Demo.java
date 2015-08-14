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

import com.deamon.mybatis.dao.UserDao;
import com.deamon.mybatis.entity.User;

public class Demo {
	public static void main(String[] args) {
		try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void init() throws IOException {
		PropertyConfigurator.configure("conf/mybatis_log.properties");
		String resource = "com/deamon/mybatis/test/config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = sqlSessionFactory.openSession();

		/* 得到一个结果 */
		UserDao mapper = session.getMapper(UserDao.class);
		System.out.println(mapper.getUserById("admin").getGender());
		
		/* 得到结果集合 */
		List<User> userList = mapper.getAllUser();
		for(User temp : userList){
			System.out.println(temp.getId()+" "+temp.getName()+"  "+temp.getGender());
		}
	}
}