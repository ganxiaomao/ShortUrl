package com.youshusoft.transport.extra;

import java.lang.reflect.Method;
/**
 * handle������Ϣ
 * @author ����
 *
 */
public class HandlerInfo {
	
	public HandlerInfo(Object object, Method method) {
		super();
		this.object = object;
		this.method = method;
	}
	private Object object; //��ʵ��
	private Method method; //�෽��

	public Object getObject() {
		return object;
	}

	public Method getMethod() {
		return method;
	}
	
}
