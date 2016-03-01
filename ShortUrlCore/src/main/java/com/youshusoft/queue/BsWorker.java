package com.youshusoft.queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import com.youshusoft.netty.NettyMessage;
import com.youshusoft.transport.messagehandler.MessageHandler;
/**
 * 请求处理工人
 * @author 悠树
 *
 */
public class BsWorker {

	public MessageHandler messgeaHandler; //消息处理器
	public int threadCount = 50; //处理线程池线程数

	public LinkedBlockingQueue<NettyMessage<?>> queue = new LinkedBlockingQueue<NettyMessage<?>>(100000);

	public BsWorker(final MessageHandler messageHandler, int threadCount){
		if(threadCount > 0){
			this.threadCount = threadCount;
		}
		this.messgeaHandler = messageHandler;
		start();
	}
	public BsWorker(final MessageHandler messageHandler){
		this.messgeaHandler = messageHandler;
		start();
	}
	
	public void start(){
		final ExecutorService es = Executors.newFixedThreadPool(threadCount); //初始化处理线程池
		new Thread(new Runnable(){

			@Override
			public void run() {
				try{
					for(;;){
						final NettyMessage<?> message = queue.take(); //循环获取消息
						es.submit(new Thread(){
							@Override
							public void run() {
								try{
									messgeaHandler.handle(message); //消息处理
								}catch(Exception e){
									e.printStackTrace();
								}
							}
						});
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
		}).start();
	}
	/**
	 * 添加消息到处理队列
	 * @param message
	 */
	public void execute(NettyMessage<?> message){
		try {
			queue.put(message);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
