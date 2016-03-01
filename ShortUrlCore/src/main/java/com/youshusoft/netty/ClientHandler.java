package com.youshusoft.netty;

import org.apache.log4j.Logger;



import com.youshusoft.queue.HubMessageQueueManager;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
/**
 * Netty����ͻ��˴�����
 * @author ����
 *
 */
public class ClientHandler extends SimpleChannelInboundHandler<Object>{
	private static Logger logger = Logger.getLogger(ClientHandler.class);
	@Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		logger.info("���󵽴" + ctx.channel().remoteAddress() + " [msg]" + msg);
		HubMessageQueueManager.getInstance().handleMessage((NettyMessage<?>) msg);
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	logger.info("��������" + ctx.channel().remoteAddress());
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    	logger.info("�Ͽ�����" + ctx.channel().remoteAddress());
        super.channelInactive(ctx);
    }
}
