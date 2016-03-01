package com.youshusoft.netty;

import org.apache.log4j.Logger;

import com.youshusoft.queue.DefaultMessageQueueManager;
import com.youshusoft.transport.callback.RemoteReqMessageCallback;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
public class ServerHandler extends SimpleChannelInboundHandler<Object>{
	private static Logger logger = Logger.getLogger(ServerHandler.class);
	@Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg)
            throws Exception {
		Request req = (Request) msg;
		logger.info("请求到达：" + ctx.channel().remoteAddress() + " [msg]" + req);
		RemoteReqMessageCallback callback = new RemoteReqMessageCallback();
		req.setCallback(callback);
		DefaultMessageQueueManager.getInstance().handleMessage(req);
		Response res = callback.getResponse();
        ctx.writeAndFlush(res);
        logger.info("数据响应 ：" + res);
    }

    /*
     * 覆盖 channelActive 方法 在channel被启用的时候触发 (在建立连接的时候)
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	logger.info("建立连接 : " + ctx.channel().remoteAddress());
        super.channelActive(ctx);
    }
}
