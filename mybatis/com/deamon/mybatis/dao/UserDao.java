package com.deamon.mybatis.dao;

import java.util.List;

import com.deamon.mybatis.entity.User;

/**
 * �ṩ��user��sql����
 * 
 * @author dimen
 */
public interface UserDao {

	public User getUserById(String id);

	public List<User> getAllUser();
}
