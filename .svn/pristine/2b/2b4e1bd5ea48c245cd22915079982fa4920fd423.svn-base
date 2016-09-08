package com.zllh.payment.server.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;

import com.zllh.payment.server.thread.MsgQConsumerThread;

public class ThreadPool {

	//单例池
	@Autowired
	public static ExecutorService singlePool;

	//单例线程（发送报文线程）
	@Autowired
	private MsgQConsumerThread singleConsumer=null;

	//获得单线程(向银行发送报文线程)
    public void getInstanceConsumerThread() throws InterruptedException {
    	 if (singlePool == null) {
    		 singlePool = Executors.newSingleThreadExecutor();
    	 }
         if (singleConsumer == null) {
        	 singleConsumer = new MsgQConsumerThread(); 
        	 singlePool.execute(singleConsumer);
         }
         else {
        	 singlePool.execute(singleConsumer);
         }
    }

	
}
