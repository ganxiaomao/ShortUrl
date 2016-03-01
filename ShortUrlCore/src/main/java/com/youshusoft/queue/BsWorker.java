package com.youshusoft.queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import com.youshusoft.netty.NettyMessage;
import com.youshusoft.transport.messagehandler.MessageHandler;
/**
 * ��������
 * @author ����
 *
 */
public class BsWorker {

	public MessageHandler messgeaHandler; //��Ϣ������
	public int threadCount = 50; //�����̳߳��߳���

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
		final ExecutorService es = Executors.newFixedThreadPool(threadCount); //��ʼ�������̳߳�
		new Thread(new Runnable(){

			@Override
			public void run() {
				try{
					for(;;){
						final NettyMessage<?> message = queue.take(); //ѭ����ȡ��Ϣ
						es.submit(new Thread(){
							@Override
							public void run() {
								try{
									messgeaHandler.handle(message); //��Ϣ����
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
	 * �����Ϣ���������
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
