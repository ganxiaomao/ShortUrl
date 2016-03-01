package com.youshusoft.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.youshusoft.netty.ClientInitializer;
import com.youshusoft.netty.NettyClient;
import com.youshusoft.util.Config.ServerConfig;

public class ServerHelper {
	private static Map<String,NettyClient> clientMap = new HashMap<String,NettyClient>();
	
	public static NettyClient getClient(String id){
		return clientMap.get(id);
	}
	
	public static void init(List<ServerConfig> servers){
		for (ServerConfig sc : servers) {
			if(clientMap.get(sc.getId()) != null){
				continue;
			}
			clientMap.put(sc.getId(),new NettyClient(sc.getHost(),sc.getPort(), new ClientInitializer()));
		}
		
	}
	
	public static List<NettyClient> getClientListByGroup(String group){
		List<NettyClient> list = new ArrayList<NettyClient>();
		List<ServerConfig> configs = Config.getServerConfigByGroup(group);
		for (ServerConfig sc : configs) {
			NettyClient nc = clientMap.get(sc.getId());
			if(nc == null){
				continue;
			}
			list.add(nc);
		}
		return list;
	}
}
