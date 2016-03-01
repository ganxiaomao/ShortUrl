package com.youshusoft.netty;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.log4j.Logger;
/**
 * Netty��Ϣ
 * @author ����
 *
 * @param <T>
 */
public class NettyMessage<T> implements Serializable{
	private static Logger logger = Logger.getLogger(NettyMessage.class);
	private static final long serialVersionUID = 1L;
	public enum MessageType{ //��Ϣ���� REQ,RES
		IN,OUT
	}
	private static final AtomicLong atomicIdent = new AtomicLong(1L); //������ʶ
	protected long ident; //����ID��ʶ
	protected MessageType type; //����
	protected T data; //����
	public NettyMessage(long ident,MessageType type){
		this.ident = ident;
		this.type = type;
	}
	
	public static long generateId(){
		return atomicIdent.getAndIncrement();
	}
	
	public long getIdent() {
		return ident;
	}
	public void setIdent(long ident) {
		this.ident = ident;
	}
	
	
	public MessageType getType() {
		return type;
	}
	public void setType(MessageType type) {
		this.type = type;
	}
	
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ident:"+ ident + " type:" + type + " data:" + data;
	}
	
}
