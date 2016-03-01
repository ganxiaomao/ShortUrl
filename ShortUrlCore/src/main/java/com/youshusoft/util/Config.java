package com.youshusoft.util;



import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 全局配置
 * @author 悠树
 *
 */
public class Config {
	private static Map<String,List<ServerConfig>> serverGroupMap = new HashMap<String, List<ServerConfig>>();
	private static List<ServerConfig> serverConfigs = new ArrayList<Config.ServerConfig>();
	private static List<WebConfig> webConfigs = new ArrayList<Config.WebConfig>();
	private static List<DnsConfig> dnsConfigs = new ArrayList<Config.DnsConfig>();
	private static List<RedisConfig> redisConfigs = new ArrayList<Config.RedisConfig>();
	
	static{
		initAllConfig();
	}
	public static ServerConfig getServerConfig(String id){
		for (ServerConfig server : serverConfigs) {
			if(server.getId().equals(id)){
				return server;
			}	
		}
		return null;
	}
	public static WebConfig getWebConfig(String id){
		for (WebConfig web : webConfigs) {
			if(web.getId().equals(id)){
				return web;
			}	
		}
		return null;
	}
	public static DnsConfig getDnsConfig(String domain){
		for (DnsConfig dns : dnsConfigs) {
			if(dns.getDomain().equals(domain)){
				return dns;
			}	
		}
		return null;
	}
	
	public static RedisConfig getRedisConfig(String id){
		for (RedisConfig rc : redisConfigs) {
			if(rc.getId().equals(id)){
				return rc;
			}
		}
		return null;
	}
	public static List<ServerConfig> getServerConfigByGroup(String group){
		return serverGroupMap.get(group);
	}
	public static List<ServerConfig> getAllServerConfig(){
		return serverConfigs;
	}
	public static List<WebConfig> getAllWebConfig() {
		return webConfigs;
	}
	public static List<DnsConfig> getAllDnsConfig() {
		return dnsConfigs;
	}
	public static class ServerConfig{
		private String id;
		private String host;
		private int port;
		private String group;
		public String redisConfigId;
		public String getId() {
			return id;
		}
		public String getHost() {
			return host;
		}
		public int getPort() {
			return port;
		}
		public String getGroup() {
			return group;
		}
		public String getRedisConfigId() {
			return redisConfigId;
		}
		
		
	}
	public static class WebConfig{
		private String id;
		private int port;
		private String name;
		private String domain;
		private String serverGroup;
		
		public String getId() {
			return id;
		}
		public int getPort() {
			return port;
		}
		public String getName() {
			return name;
		}
		
		public String getDomain() {
			return domain;
		}
		public String getServerGroup() {
			return serverGroup;
		}
	}
	public static class DnsConfig{
		private String domain;
		private String name;
		private String serverGroup;
		
		public String getDomain() {
			return domain;
		}
		public String getName() {
			return name;
		}
		public String getServerGroup() {
			return serverGroup;
		}
	}
	
	public static class RedisConfig{
		private String id;
		private String host;
		private int port;
		private int maxIdle;
		public String getId() {
			return id;
		}
		public String getHost() {
			return host;
		}
		public int getPort() {
			return port;
		}
		public int getMaxIdle() {
			return maxIdle;
		}
		
	}
	public static void initAllConfig(){
		initAllConfig(Config.class.getResourceAsStream("/config.xml"));
	}
	public static void initAllConfig(InputStream stream){
		
		SAXReader reader = new SAXReader();  
		Document dom = null;
        try {
			dom = reader.read(stream);
			
		} catch (DocumentException e) {
			e.printStackTrace();
			return;
		} 
        Element config = dom.getRootElement(); //全部配置
        List<Element> els = config.element("servers").elements("server");
        for (Element el : els) {
        	String state = el.elementText("state");
        	if(state != null && state.equals("1")){
        		continue;
        	}
			ServerConfig server = new ServerConfig();
			server.id = el.elementText("id"); 
			server.group = el.elementText("group");
			server.host = el.elementText("host");
			server.port = Integer.valueOf(el.elementText("port"));
			server.redisConfigId = el.elementText("redisConfigId");
			putServer(server);
		}
        
 	   
       List<Element> webc = config.element("webs").elements("web");
       for (Element e : webc) {
    	   WebConfig web = new WebConfig();
    	   web.id = e.elementText("id");
    	   web.name = e.elementText("name");
    	   web.domain = e.elementText("domain");
    	   web.serverGroup = e.elementText("serverGroup");
    	   webConfigs.add(web);
       }
       
       List<Element> dnsesc = config.element("dnses").elements("dns");
       for (Element e : dnsesc) {
	       	DnsConfig dns = new DnsConfig();
	       	dns.domain = e.elementText("domain");
	       	dns.name = e.elementText("name");
	       	dns.serverGroup = e.elementText("serverGroup");
	       	dnsConfigs.add(dns);
	   }
       List<Element> redises = config.element("redises").elements("redis");
       for (Element e : redises) {
    	   RedisConfig rc = new RedisConfig();
    	   rc.id = e.elementText("id");
    	   rc.host = e.elementText("host");
    	   rc.port = Integer.valueOf(e.elementText("port"));
    	   rc.maxIdle = Integer.valueOf(e.elementText("maxIdle"));
    	   redisConfigs.add(rc);
       }
	}
	private static void putServer(ServerConfig server){
		serverConfigs.add(server);
		List<ServerConfig> list = serverGroupMap.get(server.group);
		if(list == null){
			list = new ArrayList<Config.ServerConfig>();
			serverGroupMap.put(server.group, list);
		}
		list.add(server);
		
	}
}
