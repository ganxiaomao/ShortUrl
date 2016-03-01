package com.youshusoft.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.youshusoft.data.User;
import com.youshusoft.mapper.UserMapper;

@Service
public class UserService extends BaseService{
	
	public List<User> list(){
		return getMapper().select(null);
	}
    public int insert(User u) {
        return getMapper().insert(u);
    }
    public User selectOne(User user){
    	return getMapper().selectOne(user);
    }
    public int update(User user){
    	return getMapper().updateByPrimaryKey(user);
    	
    }
    private UserMapper getMapper(){
    	return getMapper(UserMapper.class);
    }
}
