package com.zllh.payment.server.thread;

import org.springframework.beans.factory.annotation.Autowired;

import com.zllh.payment.model.MsgBean;
import com.zllh.payment.server.queue.AskBankQueue;
import com.zllh.payment.utils.SpringBeanFactoryUtils;

public class HandleTransferMsgThread implements Runnable{

    private String serverUrl;
    
    private MsgBean msgBean;

    @Autowired
	public SpringBeanFactoryUtils sBeanFactoryUtils;

    public AskBankQueue askBankQueue = (AskBankQueue) sBeanFactoryUtils.getBean("askBankQueue");;
    
    public HandleTransferMsgThread(String serverUrl, MsgBean msgBean){
    	this.serverUrl = serverUrl;
    	this.msgBean = msgBean;
    }
 
	public void run() {
		System.out.println("进入发送报文线程！");
		try {
//			String sendStatus = PostBankMessage.post(serverUrl, msgBean.getMsgContent());
			//资金划转列表添加一条记录 (1 已发送，2已成功，3无需处理，4异常)  FundTransferDetail
			//test data
			String sendStatus="";
			if ("".equals(sendStatus)){
				//添加到任务队列
				askBankQueue.addTaskInQ(msgBean);
			}
		} catch (Exception e) {
		}
		System.out.println("执行发送报文线程！");
	}

}
