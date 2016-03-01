package com.youshusoft.queue;

import com.youshusoft.transport.messagehandler.HubMessageOutHandler;
import com.youshusoft.transport.messagehandler.MessageInHandler;
/**
 * 中转服务器(WEB/DNS)响应消息队列管理器
 * @author 悠树
 *
 */
public class HubMessageQueueManager extends MessageQueueManager {
	private static HubMessageQueueManager qm = new HubMessageQueueManager();
	public static HubMessageQueueManager getInstance(){return qm;}
	protected HubMessageQueueManager(){
		start();
	}
	protected void start(){
		in = new MessageQueue(new MessageInHandler());
		out = new MessageQueue(new HubMessageOutHandler());
	}

}
