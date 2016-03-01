package com.youshusoft.queue;

import com.youshusoft.netty.NettyMessage;
/**
 * 消息队列管理器
 * @author 悠树
 *
 */
public class MessageQueueManager {
	protected MessageQueue in;
	protected MessageQueue out;
	public void handleMessage(NettyMessage<?> message) {
		if(message.getType() == NettyMessage.MessageType.IN){
			in.getBsWorker().execute(message);
		}else{
			out.getBsWorker().execute(message);
		}
		
	}
}
