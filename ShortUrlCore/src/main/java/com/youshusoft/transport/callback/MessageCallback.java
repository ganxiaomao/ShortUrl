package com.youshusoft.transport.callback;

import com.youshusoft.netty.NettyMessage;
/**
 * ��Ϣ�ص�
 * @author ����
 *
 */
public interface MessageCallback {
	void callback(NettyMessage<?> message);
}
