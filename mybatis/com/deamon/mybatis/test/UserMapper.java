package com.deamon.mybatis.test;

import org.apache.ibatis.annotations.Select;

import com.deamon.mybatis.pojo.User;

public interface UserMapper {
	User findById(String id);
}
