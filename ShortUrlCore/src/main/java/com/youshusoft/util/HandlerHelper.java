package com.youshusoft.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.youshusoft.netty.NettyHandler;
import com.youshusoft.transport.extra.HandlerInfo;
/**
 * NettyHandler����������
 * @author ����
 *
 */
public class HandlerHelper {
	private static Logger logger = Logger.getLogger(HandlerHelper.class);
	private static Map<String, HandlerInfo> handlerMap = new HashMap<String, HandlerInfo>();
	/**
	 * ��ָ���������м̳���NettyHandler������public�ķ�����ӵ�handlerMap��
	 * @param packName ��·��
	 */
	public static void initSocketHandler(String packName){
		
		Set<Class<?>> cs = ClassUtil.getClasses(packName);
		for (Class<?> c : cs) {
			if(!NettyHandler.class.isAssignableFrom(c)){
				continue;
			}
			try {
				NettyHandler nh = (NettyHandler) c.newInstance();
				String className = c.getSimpleName().replaceAll("Impl", "");;
				List<Method> list = nh.init();
				for (Method method : list) {
					String key = className + "." + method.getName();
					HandlerInfo info = new HandlerInfo(nh, method);
					logger.info("handlerMap put " + key);
					handlerMap.put(key,info);
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(-1);
			}
				
		}
	}
	
	public static HandlerInfo getSocketHandler(String key){
		HandlerInfo hi = handlerMap.get(key);
		if(hi != null){
			return hi;
		}
		return null;
	}

}
