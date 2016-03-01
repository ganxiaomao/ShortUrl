package com.youshusoft.extra;

import io.netty.channel.ChannelHandlerContext;

import com.youshusoft.netty.NettyMessage;
import com.youshusoft.transport.callback.MessageCallback;
/**
 * Server��������󽫽��д��
 * @author ����
 *
 */
public class ServerCallback implements MessageCallback {
	private ChannelHandlerContext context;
	public ServerCallback(ChannelHandlerContext context){
		this.context = context;
	}
	public void callback(NettyMessage<?> message) {
		context.writeAndFlush(message);
	}

}
