package com.youshusoft.queue;

import com.youshusoft.transport.messagehandler.MessageInHandler;
import com.youshusoft.transport.messagehandler.MessageOutHandler;
/**
 * Ĭ����Ϣ���й�����
 * @author ����
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
