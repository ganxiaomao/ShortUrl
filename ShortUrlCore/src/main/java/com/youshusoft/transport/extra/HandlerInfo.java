package com.youshusoft.transport.extra;

import java.lang.reflect.Method;
/**
 * handle方法信息
 * @author 悠树
 *
 */
public class HandlerInfo {
	
	public HandlerInfo(Object object, Method method) {
		super();
		this.object = object;
		this.method = method;
	}
	private Object object; //类实例
	private Method method; //类方法

	public Object getObject() {
		return object;
	}

	public Method getMethod() {
		return method;
	}
	
}
