package com.youshusoft.transport.callback;

import com.youshusoft.netty.NettyMessage;
import com.youshusoft.netty.Response;
/**
 * Զ������ʱ�Ļص���
 * @author ����
 *
 */
public class RemoteReqMessageCallback implements MessageCallback {
	private Response response;
	@Override
	public void callback(NettyMessage<?> message) {
		response = (Response) message;
		synchronized (this) {
			this.notify();//����
		}
	}
	public Response getResponse() {
		synchronized (this) {
			try {
				wait(30000); //�ȴ���Ӧ��Ϣ
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return response;
	}
	

}
