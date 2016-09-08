package com.zllh.payment.server.thread;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.zllh.factoring.repayment.service.RefundMgService;
import com.zllh.mall.settle.service.ISettleService;
import com.zllh.payment.model.ResultBackBean;
import com.zllh.payment.server.queue.ResultBackQueue;
import com.zllh.payment.utils.MsgSynchronizedLockUtil;
import com.zllh.payment.utils.SpringBeanFactoryUtils;

public class ResultBackThread implements Runnable{
	
	 public Logger logger = Logger.getLogger(ResultBackThread.class);

	@Autowired
	public SpringBeanFactoryUtils sBeanFactoryUtils;
	
	private RefundMgService refundMgService = (RefundMgService) SpringBeanFactoryUtils.getBean("refundMgServiceImpl");

	private ISettleService settleService = (ISettleService)SpringBeanFactoryUtils.getBean("SettleServiceImpl");

	public String sendResultToMall(){
		return null;
	}
	public String sendResultToFactoring(){
		return null;
	}
	public void run(){
		//回调
		logger.error("#####################################################进入回调返回结果线程！");
		ResultBackQueue resultBackQueue = (ResultBackQueue) sBeanFactoryUtils.getBean("resultBackQueue");
		Long queueLength = resultBackQueue.getQueueLength();
		List<ResultBackBean> resultBeans = resultBackQueue.getQueueList();
		for (int i = 0; i < queueLength; i++) {
			ResultBackBean resultBackBean = resultBeans.get(i);
			String flowId = resultBackBean.getFlowId();
			//如果返回结果1路未完成，回调不能执行
			if (MsgSynchronizedLockUtil.checkExist(flowId)){
				continue;
			}
			JSONObject jsonObject =  JSONObject.fromObject(resultBackBean);
			//1.回调保理和商城（如果回调地址不为空）
			String resultFac = "200";
			String resultMal = "200";
			if(resultBackBean.getFactoringUrl() != null && !"".equals(resultBackBean.getFactoringUrl())) {
				resultFac = refundMgService.disposeFacRefundCallback(jsonObject);
			}
			if(resultBackBean.getMallUrl() != null && !"".equals(resultBackBean.getMallUrl())) {
				//resultMal = settleService.disposeMallRefundCallback(jsonObject);
			}
			//回调结果都成功的情况，删除队列中的回调内容。
			if ("200".equals(resultFac) && "200".equals(resultMal)) {
				logger.error("-----------------回调的流水号 = "+resultBackBean.getSerialID() +"-----------------回调的状态码 = "+resultBackBean.getStatus() + "-------------回调的内容=" +resultBackBean.getResultContent());

				resultBackQueue.removeTask(resultBackBean);
			}
			logger.error("#####################################################回调返回结果线程处理结束，已移除回调任务");
		}
	}
}
