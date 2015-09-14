package com.deamon.mybatis.test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
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

		/* �õ�һ����� */
		UserDao mapper = session.getMapper(UserDao.class);
		System.out.println(mapper.getUserById("admin").getGender());
		
		/* �õ����� */
		List<User> userList = mapper.getAllUser();
		for(User temp : userList){
			System.out.println(temp.getId()+" "+temp.getName()+"  "+temp.getGender());
		}
	}
}