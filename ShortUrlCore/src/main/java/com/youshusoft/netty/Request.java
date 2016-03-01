package com.youshusoft.netty;

import java.util.ArrayList;
import java.util.List;

import com.youshusoft.transport.callback.MessageCallback;
/**
 * ������Ϣ
 * @author ����
 *
 */
public class Request extends NettyMessage<List<Object>>{
	protected MessageCallback callback; //��Ӧ�ص�
	private String key; //Ŀ�귽��
	public  Request(String key){
		this(generateId(),key);
	}
	public Request(long ident,String key){
		super(ident,MessageType.IN);
		this.key = key;
		data = new ArrayList<Object>();
	}
	public void callback(NettyMessage<?> message){
		if(callback != null){
			callback.callback(message);
		}
	}
	public Request addParameter(Object val){
		data.add(val);
		return this;
	}
	public Request addParameter(Object val,Object...vals ){
		data.add(val);
		for (Object v : vals) {
			data.add(v);
		}
		return this;
	}
	public String getCode() {
		return key;
	}
	public void setCode(String code) {
		this.key = code;
	}
	public MessageCallback getCallback() {
		return callback;
	}
	public void setCallback(MessageCallback callback) {
		this.callback = callback;
	}
	
}
