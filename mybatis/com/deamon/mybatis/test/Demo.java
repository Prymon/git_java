package com.deamon.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

import com.deamon.mybatis.dao.UserDao;
import com.deamon.mybatis.entity.User;

public class Demo {
	private static final String resource_Url = "com/deamon/mybatis/test/config.xml";

	static {
		// 初始化log4j
		PropertyConfigurator.configure("conf/mybatis_log.properties");
	}

	/**
	 * 通过ID查找记录并输出
	 * 这种实现需要DAO，也可以改成不用dao的
	 * @throws IOException
	 */
	public static void test0() throws IOException {
		// 加载mybatis的配置文件
		Reader reader = Resources.getResourceAsReader(resource_Url);
		// 通过工厂创建sql会话，得到SqlSession
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = sqlSessionFactory.openSession();
		// 通过SqlSession操作数据库
		UserDao mapper = session.getMapper(UserDao.class);
		System.out.println(mapper.getUserById("123").getGender());
		List<User> userList = mapper.getAllUser();
		session.close();
		// 遍历结果
		for (User temp : userList) {
			System.out.println(temp.getId() + " " + temp.getName() + "  " + temp.getGender());
		}
	}

	/**
	 * 测试selectOne方法
	 * 这种实现不需要DAO
	 */
	@Test
	public void test() {
		try {
			InputStream resourceStream = Resources.getResourceAsStream(resource_Url);
			SqlSessionFactory session_Factory = new SqlSessionFactoryBuilder().build(resourceStream);
			SqlSession session = session_Factory.openSession();
			User user = session.selectOne("com.deamon.mybatis.dao.UserDao.getUserById", 123);
			System.out.println(user);
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 测试insert pojo
	 * 这种实现不需要DAO , 除了查询外，事物操作都需要执行commit
	 */
	@Test
	public void test2() {
		User user = new User();
		user.setId("123");
		user.setName("Deamon");
		user.setGender("female");
		try {
			InputStream resourceStream = Resources.getResourceAsStream(resource_Url);
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceStream);
			SqlSession session = factory.openSession();
			int result = session.insert("com.deamon.mybatis.dao.UserDao.insertUser", user);
			session.commit();
			System.out.println(result);
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 插入一个自增类型并获得主键的值
	 * 除了查询外，事物操作都需要执行commit
	 */
	@Test
	public void test3() {
		User user = new User();
		user.setName("Deamon");
		user.setGender("female");
		try {
			InputStream resourceStream = Resources.getResourceAsStream(resource_Url);
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceStream);
			SqlSession session = factory.openSession();
			session.insert("com.deamon.mybatis.dao.UserDao.insertUserReturnId", user);
			session.commit();
			System.out.println("just insert id :"+user.getId());
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 插入前使用mysql生成uuid,插入时，uuid会先填充到实体中
	 * 除了查询外，事物操作都需要执行commit
	 */
	@Test
	public void test4() {
		User user = new User();
		user.setName("UUID");
		user.setGender("female");
		try {
			InputStream resourceStream = Resources.getResourceAsStream(resource_Url);
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceStream);
			SqlSession session = factory.openSession();
			session.insert("com.deamon.mybatis.dao.UserDao.insertUserByUuid", user);
			session.commit();
			System.out.println("just insert id :"+user.getId());
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据ID删除记录
	 * 除了查询外，事物操作都需要执行commit
	 */
	@Test
	public void test5(){
		User user = new User();
		user.setId("123");
		try {
			InputStream resourceStream = Resources.getResourceAsStream(resource_Url);
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceStream);
			SqlSession session = factory.openSession();
			session.insert("com.deamon.mybatis.dao.UserDao.deleteUserById", user);
			session.commit();
			System.out.println("just delete id :"+user.getId());
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * 
	 */
	
	
	
	
	
}
