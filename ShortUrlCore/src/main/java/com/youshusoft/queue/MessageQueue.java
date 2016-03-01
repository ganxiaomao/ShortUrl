package com.youshusoft.queue;

import com.youshusoft.transport.messagehandler.MessageHandler;
/**
 * ��Ϣ����
 * @author ����
 *
 */
public class MessageQueue {
	private BsWorker bsWorker;
	private MessageHandler messageHandler;
	
	public MessageQueue(MessageHandler messageHandler){
		this.messageHandler = messageHandler;
		BsWorker bsWorker = new BsWorker(messageHandler);
		this.bsWorker  = bsWorker;
	}
	public MessageQueue(MessageHandler messageHandler,int threadCount){
		this.messageHandler = messageHandler;
		BsWorker bsWorker = new BsWorker(messageHandler,threadCount);
		this.bsWorker  = bsWorker;
	}
	public BsWorker getBsWorker() {
		return bsWorker;
	}
	public MessageHandler getMessageHandler() {
		return messageHandler;
	}
	
	
}
