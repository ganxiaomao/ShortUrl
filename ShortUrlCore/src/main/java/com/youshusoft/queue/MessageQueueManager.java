package com.youshusoft.queue;

import com.youshusoft.netty.NettyMessage;
/**
 * ��Ϣ���й�����
 * @author ����
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
