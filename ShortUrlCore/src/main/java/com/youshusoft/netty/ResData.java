package com.youshusoft.netty;

import java.io.Serializable;

import com.youshusoft.conts.CodeConts;

/**
 * Netty请求响应数据
 * @author 悠树
 *
 */
public class ResData implements Serializable {
	private static final long serialVersionUID = 1L;
	private int status;
	private Object data;
	
	public static ResData createException(){
		ResData res = new ResData();
		res.status = CodeConts.EXCEPTION;
		return res;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public <T> T getData() {
		if(data == null){
			return null;
		}
		return (T) data;
	}
	public void setData(Object data) {
		this.data = data;
	}

}
