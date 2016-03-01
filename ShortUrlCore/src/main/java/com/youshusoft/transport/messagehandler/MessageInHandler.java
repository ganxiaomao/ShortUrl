package com.youshusoft.transport.messagehandler;

import org.apache.log4j.Logger;

import com.youshusoft.netty.NettyMessage;
import com.youshusoft.netty.Request;
import com.youshusoft.netty.ResData;
import com.youshusoft.netty.Response;
import com.youshusoft.transport.extra.HandlerInfo;
import com.youshusoft.util.HandlerHelper;
/**
 * 请求消息处理器
 * @author 悠树
 *
 */
public class MessageInHandler extends MessageHandler {
	private static Logger logger = Logger.getLogger(MessageInHandler.class);
	@Override
	public void handle(NettyMessage<?> message) {
		long startTime = System.currentTimeMillis();
		Request req = (Request) message;
		HandlerInfo hi = HandlerHelper.getSocketHandler(req.getCode());
		ResData resData = null;
		logger.info("invoke " + req.getCode() + "\n\rargs:" + req.getData().toArray());
		try {
			resData = ((ResData) hi.getMethod().invoke(hi.getObject(), req.getData().toArray()));
		} catch (Exception e) {
			e.printStackTrace();
			//调用异常,返回错误信息
			resData = ResData.createException();
		}
		Response res = new Response(req);
		res.setData(resData);
		//回调
		req.callback(res);
		logger.info("耗时 " + (System.currentTimeMillis() - startTime) + " ms");
	}

}
