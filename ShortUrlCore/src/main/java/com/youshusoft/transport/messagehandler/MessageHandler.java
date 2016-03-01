package com.youshusoft.transport.messagehandler;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.youshusoft.netty.NettyMessage;
/**
 * Netty��Ϣ������
 * @author ����
 *
 */
public abstract class MessageHandler {
	private static Logger logger = Logger.getLogger(MessageHandler.class);
	/**���յ��������MAP*/
	public static ConcurrentHashMap<Long, Object> reqMap = new ConcurrentHashMap<Long, Object>();
	/**�����������MAP*/
	public static ConcurrentHashMap<Long, Object> resMap = new ConcurrentHashMap<Long, Object>();
	public void doHandle(NettyMessage<?> message){
		logger.info("doHandle " + message);
		handle(message);
	}
	public abstract void handle(NettyMessage<?> message);
}
