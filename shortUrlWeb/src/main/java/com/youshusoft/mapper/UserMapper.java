package com.youshusoft.mapper;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;

import com.github.abel533.mapper.Mapper;
import com.youshusoft.data.User;
public interface UserMapper extends Mapper<User> {
	List<User> list();
}
