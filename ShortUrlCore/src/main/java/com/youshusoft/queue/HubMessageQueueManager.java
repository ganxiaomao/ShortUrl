package com.youshusoft.queue;

import com.youshusoft.transport.messagehandler.HubMessageOutHandler;
import com.youshusoft.transport.messagehandler.MessageInHandler;
/**
 * ��ת������(WEB/DNS)��Ӧ��Ϣ���й�����
 * @author ����
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
