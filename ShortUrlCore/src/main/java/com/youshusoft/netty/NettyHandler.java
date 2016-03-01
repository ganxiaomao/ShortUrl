package com.youshusoft.netty;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import com.youshusoft.handler.ResHandler;

/**
 * Netty��������
 * @author ����
 *
 */
public abstract class NettyHandler extends ResHandler{
	
	/**���౻��ʼ����ʱ���ҳ���Ӧ��Handler���� */
	public List<Method> init(){
		Method[] ms = this.getClass().getDeclaredMethods();
		List<Method> methodList = new ArrayList<Method>();
		for(Method m : ms){
			if(m.getModifiers() != Modifier.PUBLIC){
				continue;
			}
			methodList.add(m);
		}
		return methodList;
	}
}
