package com.youshusoft.netty;

import org.apache.log4j.Logger;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
	private static Logger logger = Logger.getLogger(NettyServer.class);
	private int port;
	private ChannelInitializer<SocketChannel> initializer;
	private ServerBootstrap b;
	private EventLoopGroup bossGroup = new NioEventLoopGroup();
	EventLoopGroup workerGroup = new NioEventLoopGroup();
	public NettyServer(int port, ChannelInitializer<SocketChannel> initializer) {
		super();
		if(initializer == null){
			throw new NullPointerException();
		}
		this.port = port;
		this.initializer = initializer;
	}

	public void start(){
        b = new ServerBootstrap();
        b.group(bossGroup, workerGroup);
        b.channel(NioServerSocketChannel.class);
        b.childHandler(initializer);

        // 服务器绑定端口监听
        ChannelFuture f = null;
		try {
			f = b.bind(port).sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return;
		}
        f.channel();
	} 
	public void end(){
		bossGroup.shutdownGracefully();
		workerGroup.shutdownGracefully();
	}
}
