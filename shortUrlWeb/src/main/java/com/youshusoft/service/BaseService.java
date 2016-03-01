package com.youshusoft.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService {
	@Autowired
	private SqlSessionTemplate session;

	public SqlSessionTemplate getSession() {
		return session;
	}

	public void setSession(SqlSessionTemplate session) {
		this.session = session;
	}
	public <T> T getMapper(Class<T> clz){
		return session.getMapper(clz);
	}
	
	
}
