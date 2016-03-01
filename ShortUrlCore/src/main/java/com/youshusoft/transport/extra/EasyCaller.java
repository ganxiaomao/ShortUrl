package com.youshusoft.transport.extra;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.youshusoft.netty.NettyClient;
import com.youshusoft.netty.Request;
import com.youshusoft.netty.ResData;
import com.youshusoft.netty.Response;
import com.youshusoft.transport.callback.RemoteReqMessageCallback;
import com.youshusoft.transport.messagehandler.MessageHandler;
import com.youshusoft.util.Config;
import com.youshusoft.util.Config.ServerConfig;
import com.youshusoft.util.ServerHelper;


/**
 * Server方法调用代理类
 * @author 悠树
 *
 */
public class EasyCaller {
	Map<String, Object> cacheMap = new ConcurrentHashMap<String, Object>();
	private static Logger logger = Logger.getLogger(EasyCaller.class);
	private String serverGroup;
	private Selector<NettyClient> selector = RanSelector.getInstance(); //默认使用随机选择
	protected EasyCaller(String serverGroup){
		this.serverGroup = serverGroup;
	}
	public static EasyCaller getInstance(String clusterName){
		return new EasyCaller(clusterName);
	}
	public <T> T createRemote(Class<T> clz){
		String key = clz.getName();
		Object val = cacheMap.get(key);
		if(val == null){
			val = Proxy.newProxyInstance(clz.getClassLoader(), new Class[]{clz}, remote);
			cacheMap.put(key, val);
		}
		return (T) val;
	}

	protected final RemoteHandler remote = new RemoteHandler();

	private class RemoteHandler implements InvocationHandler{
		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			String name = method.getDeclaringClass().getSimpleName() + "." + method.getName();
			Request req = new Request(name);
			if(args!=null){
				req.setData(Arrays.asList(args));
			}
			RemoteReqMessageCallback call = new RemoteReqMessageCallback();
			MessageHandler.resMap.put(req.getIdent(), call);
			NettyClient client = selector.select(ServerHelper.getClientListByGroup(serverGroup));
			if(client == null){
				return null;
			}
			client.writeAndFlush(req);
			Response response = call.getResponse();
			if(response == null){
				return null;
			}
			ResData res = response.getData();
			return res;
		}
	}
	
}
