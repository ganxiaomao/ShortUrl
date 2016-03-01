package com.youshusoft.netty;

import java.io.Serializable;

/**
 * ÏìÓ¦ÏûÏ¢
 * @author ÓÆÊ÷
 *
 */
public class Response extends NettyMessage<ResData> implements Serializable {
	
	public Response(long ident){
		super(ident,MessageType.OUT);
	}
	public Response(Request request){
		super(request.getIdent(),MessageType.OUT);
	}
}
