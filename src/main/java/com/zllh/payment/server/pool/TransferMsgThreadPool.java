package com.zllh.payment.server.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.zllh.payment.model.MsgBean;
import com.zllh.payment.server.thread.HandleTransferMsgThread;


public class TransferMsgThreadPool {
	
	//单例池
	public static ExecutorService listPool;

	/**
	 * 将已创建的加入线程池执行
	 * @param handleTransferMsgThread
	 * @throws InterruptedException
	 */
	public void getInstanceThread(HandleTransferMsgThread handleTransferMsgThread) throws InterruptedException{
		if (null == listPool){
			listPool = Executors.newSingleThreadExecutor();
		}
		
		if (null != handleTransferMsgThread){
			listPool.execute(handleTransferMsgThread);
		}
	}
	
	/**
	 * 创建线程并加入线程池执行
	 * @param serverUrl
	 * @param msgBean
	 * @throws InterruptedException
	 */
	public void  getInstanceThreadWithParam(String serverUrl, MsgBean  msgBean) throws InterruptedException{
		if (null == listPool){
			listPool = Executors.newSingleThreadExecutor();
		}
		listPool.execute(getOne(serverUrl, msgBean));
	}
	
	//创建执行线程
	private HandleTransferMsgThread getOne(String serverUrl, MsgBean  msgBean){
		return  new HandleTransferMsgThread(serverUrl, msgBean);
	}
}
