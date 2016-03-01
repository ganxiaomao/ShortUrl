package com.youshusoft.controller;


import com.youshusoft.handler.ResHandler;
import com.youshusoft.transport.extra.EasyCaller;


public abstract class BaseController extends ResHandler{
	
	public <T> T createRemote(String serverGroup,Class<T> c){
		return EasyCaller.getInstance(serverGroup).createRemote(c);
	}

	
}
