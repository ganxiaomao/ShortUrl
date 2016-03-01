package com.youshusoft.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class ClientInitializer extends ChannelInitializer<SocketChannel>{
	@Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //解码和编码
        pipeline.addLast("decoder", new ObjectDecoder(ClassResolvers.weakCachingResolver(null)));
        pipeline.addLast("encoder", new ObjectEncoder());

        // 客户端的逻辑
        pipeline.addLast("handler", new ClientHandler());
    }
}
