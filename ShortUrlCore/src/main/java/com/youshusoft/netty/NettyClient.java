package com.youshusoft.netty;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
/**
 * Netty�ͻ���
 * @author ����
 *
 */
public class NettyClient {
	private static Logger logger = Logger.getLogger(NettyClient.class);
	public String host = "127.0.0.1";
    public int port = 6868;
    private Bootstrap b;
    private ChannelFuture cf;
    private volatile Channel channel;
    private ChannelInitializer<SocketChannel> initializer;
    private EventLoopGroup group = new NioEventLoopGroup();
    private final byte[] connectLock = new byte[]{}; 
    private final byte[] openLock = new byte[]{}; 
    
	public NettyClient(String host, int port,ChannelInitializer<SocketChannel> initializer) {
		super();
		if(initializer == null){
			throw new NullPointerException();
		}
		this.host = host;
		this.port = port;
		this.initializer = initializer;
		initClient();
	}
	private void initClient(){
        b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class)
                .handler(initializer);
     // ���ӷ����
        connect(true);
        
	}
	/**
	 * �������� ͬ��
	 * @param retry
	 */
	private void connectSync(boolean retry){
		Thread thread = connect(retry);
		if(thread != null){
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * �������� �첽
	 * @param retry �Ƿ�����
	 */
	private Thread connect(final boolean retry){
		Thread thread;
		if(channel != null && channel.isOpen()){
			return null;
		}else{
			thread = new Thread(new Runnable() {
				@Override
				public void run() {
					synchronized (connectLock) {
						if(channel != null && channel.isOpen()){
							return;
						}
						channel = null;
						do {
							try {
								logger.info("��ʼ�������� " + host + ":" + port + "��");
								cf = b.connect(host, port).sync();
								logger.info(host + ":" + port + " ���ӳɹ���");
								break;
							} catch (Exception e) {
								e.printStackTrace();
								if(retry){
									logger.error(host + ":" + port + " ����ʧ�ܣ�10s�����ԡ�");
									try {
										TimeUnit.SECONDS.sleep(10);
									} catch (InterruptedException e1) {
										e1.printStackTrace();
									}
								}
							}
						} while (retry);
				        channel = cf.channel();
					}
					
				}
			});
		}
		thread.start();
		return thread;
		
	}
	
	public boolean isOpen(){
		if(channel == null || !channel.isOpen()){
			synchronized (openLock) {
				if (channel == null || !channel.isOpen()) {
					connectSync(false);
					return channel != null && channel.isOpen();
				}
			}
		}
		return true;
	}
	public void writeAndFlush(NettyMessage<?> msg){
		if(isOpen()){
			logger.info("writeAndFlush " + msg.toString() + " ["+ host + ":" + port +"]");
			channel.writeAndFlush(msg);
		}
	}
	public void write(Object msg){
		if(isOpen());{
			logger.info("write " + msg.toString() + " ["+ host + ":" + port +"]");
			channel.write(msg);
		}
			
	}
	public void flush(){
		channel.flush();
	}
	public void close(){
		group.shutdownGracefully();
		logger.info("���ӹر�  " + host + ":" + port);
	}
	@Override
	public String toString() {
		return "NettyClient["+ host + ":" + port +"]";
	}
	
    
}
