package com.youshusoft.queue;

import com.youshusoft.transport.messagehandler.MessageInHandler;
import com.youshusoft.transport.messagehandler.MessageOutHandler;
/**
 * 默认消息队列管理器
 * @author 悠树
 *
 */
public class DefaultMessageQueueManager extends MessageQueueManager {
	private static DefaultMessageQueueManager qm = new DefaultMessageQueueManager();
	public static DefaultMessageQueueManager getInstance(){return qm;}
	protected DefaultMessageQueueManager(){
		start();
	}
	protected void start(){
		in = new MessageQueue(new MessageInHandler());
		out = new MessageQueue(new MessageOutHandler());
	}

}
