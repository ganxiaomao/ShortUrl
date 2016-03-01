package com.youshusoft.transport.messagehandler;

import com.youshusoft.netty.NettyMessage;
import com.youshusoft.transport.callback.MessageCallback;
/**
 * 中转服务器(WEB/DNS)响应消息处理器
 * @author 悠树
 *
 */
public class HubMessageOutHandler extends MessageHandler {
	public void handle(NettyMessage<?> message) {
		Object obj = resMap.remove(message.getIdent());
		if(obj != null){
			((MessageCallback)obj).callback(message);
		}
	}

}
