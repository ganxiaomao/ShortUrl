package com.youshusoft.transport.callback;

import com.youshusoft.netty.NettyMessage;
import com.youshusoft.netty.Response;
/**
 * 远程请求时的回调器
 * @author 悠树
 *
 */
public class RemoteReqMessageCallback implements MessageCallback {
	private Response response;
	@Override
	public void callback(NettyMessage<?> message) {
		response = (Response) message;
		synchronized (this) {
			this.notify();//唤醒
		}
	}
	public Response getResponse() {
		synchronized (this) {
			try {
				wait(30000); //等待响应信息
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return response;
	}
	

}
