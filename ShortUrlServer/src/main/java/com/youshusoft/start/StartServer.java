package com.youshusoft.start;


import org.apache.log4j.Logger;

import com.youshusoft.netty.NettyServer;
import com.youshusoft.netty.ServerInitializer;
import com.youshusoft.redis.RedisAPI;
import com.youshusoft.util.Config;
import com.youshusoft.util.Config.RedisConfig;
import com.youshusoft.util.Config.ServerConfig;
import com.youshusoft.util.HandlerHelper;
/**
 * 启动入口
 * @author 悠树
 *
 */
public class StartServer {
	public static Logger logger = Logger.getLogger(StartServer.class);
	private static RedisConfig redisConfig; 
	public static void main(String[] args) throws Exception {
		String serverId = "1";
		if(args.length >= 1){
			serverId = args[0];
		}
		startSocketServer(Config.getServerConfig(serverId));
	}
	/**
	 * 启动Socket服务器
	 */
	public static void startSocketServer(ServerConfig sc){
		if(sc == null){
			throw new NullPointerException();
		}
		HandlerHelper.initSocketHandler("com.youshusoft.handler");
		redisConfig = Config.getRedisConfig(sc.getRedisConfigId());
		RedisAPI.init(redisConfig);
		NettyServer server = new NettyServer(sc.getPort(), new ServerInitializer());
		server.start();
		logger.info("========================================");
		logger.info("            Server启动成功                                            ");
		logger.info("========================================");
	}
	public static RedisConfig getRedisConfig() {
		return redisConfig;
	}
	
	

}
