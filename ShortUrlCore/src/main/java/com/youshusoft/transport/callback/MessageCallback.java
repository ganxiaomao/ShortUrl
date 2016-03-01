package com.youshusoft.transport.callback;

import com.youshusoft.netty.NettyMessage;
/**
 * 消息回调
 * @author 悠树
 *
 */
public interface MessageCallback {
	void callback(NettyMessage<?> message);
}
