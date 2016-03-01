package com.youshusoft.netty;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import com.youshusoft.handler.ResHandler;

/**
 * Netty方法超类
 * @author 悠树
 *
 */
public abstract class NettyHandler extends ResHandler{
	
	/**在类被初始化的时候，找出对应的Handler方法 */
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
