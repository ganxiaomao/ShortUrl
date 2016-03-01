package com.youshusoft.start;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;

import com.youshusoft.handler.DnsHandler;
import com.youshusoft.util.Config;
import com.youshusoft.util.Config.DnsConfig;
import com.youshusoft.util.ServerHelper;

public class StartDns {
	private static Logger logger = Logger.getLogger(StartDns.class);
	public static void main(String[] args) throws Exception {
		List<DnsConfig> dnses = Config.getAllDnsConfig();
		for (DnsConfig dns : dnses) {
			ServerHelper.init(Config.getServerConfigByGroup(dns.getServerGroup()));
		}
		Server server = new Server(80); 
		ContextHandler c = new ContextHandler(); 
		c.setContextPath("/*"); 
		c.setResourceBase("."); 
		c.setClassLoader(Thread.currentThread().getContextClassLoader());
		c.setHandler(new DnsHandler());
		server.setHandler(c); 
		server.start(); 
		logger.info("Dns服务启动完成!");
		server.join();
	}
	
}
