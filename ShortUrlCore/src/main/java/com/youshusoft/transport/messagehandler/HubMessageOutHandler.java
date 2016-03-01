package com.youshusoft.transport.messagehandler;

import com.youshusoft.netty.NettyMessage;
import com.youshusoft.transport.callback.MessageCallback;
/**
 * ��ת������(WEB/DNS)��Ӧ��Ϣ������
 * @author ����
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
