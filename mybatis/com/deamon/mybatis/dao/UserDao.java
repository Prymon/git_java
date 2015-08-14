package com.deamon.mybatis.dao;

import java.util.List;

import com.deamon.mybatis.entity.User;

/**
 * 提供对user的sql操作
 * 
 * @author dimen
 */
public interface UserDao {

	public User getUserById(String id);

	public List<User> getAllUser();
}
