package com.zllh.payment.server.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.payment.front.dao.AcctMgtMapper;
import com.zllh.payment.front.dao.ReceiveMsgRecordMapper;
import com.zllh.payment.model.MsgBean;
import com.zllh.payment.model.ReceiveMsgRecord;
import com.zllh.payment.model.ResultBackBean;
import com.zllh.payment.model.TaskBean;
import com.zllh.payment.server.pool.ThreadPool;
import com.zllh.payment.server.queue.ResultBackQueue;
import com.zllh.payment.server.queue.TaskQueue;
import com.zllh.payment.server.service.BankServerFactory;
import com.zllh.payment.server.service.MallMsgService;
import com.zllh.payment.server.service.ReceiveMsgService;
import com.zllh.payment.server.thread.ResultBackThread;
import com.zllh.payment.utils.MsgSynchronizedLockUtil;
import com.zllh.payment.utils.Util;

@Service
public class MallMsgServiceImpl implements MallMsgService {

	@Autowired
	private AcctMgtMapper acctMgtMapper;
	@Autowired
	private TaskQueue taskQueue;
	
	@Autowired
	private BankServerFactory  bankServerFactory;
	
	@Autowired
	private  ResultBackQueue resultBackQueue;
	
	@Autowired
	private ReceiveMsgService receiveMsgService;

	@Autowired
	private ReceiveMsgRecordMapper receiveMsgRecordMapper;

	@Override
	public boolean checkMsgRepeat(String msgFlowNo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkMessage() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void saveMallSendMsg(String msgXml) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMsgStatus() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internetbankProcess() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List getResultBackByStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveMsgStatus() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean sendResultToMall() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveBankBackMsg() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateMsgStatusSaveBuildMsg() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String findBankByAccountNum(String acct) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveCallBackMsg() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMsgStatusSaveBackMsg() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTaskInQ(String json) {
		TaskBean tb = new TaskBean();
		tb.setFlowId("111111");
		MsgBean mb = new MsgBean();
		mb.setMsgContent(json);
		List lst = new ArrayList();
		lst.add(mb);
		tb.setActMsgLst(lst);
		
		taskQueue.addTaskInQ(tb);
		
	}
	
	public boolean handleMallMsg(String data){
		Map<String, Object> msgMap = null;
		//报文
		List<Map<String, Object>> msgList = null;
		//回调
		Map<String, Object> callBackMap  = null;
		//all part
		String integrity = "";
		//回调地址
		String mallUrl = "";
		String factoringUrl = "";
		try {
			msgMap = Util.jsonToMap(data); 
			msgList = Util.jsonToListMap(msgMap.get("list").toString());
			callBackMap = Util.jsonToMap(msgMap.get("callBack").toString());
			integrity = msgMap.get("integrity").toString();
			mallUrl = callBackMap.get("mallUrl").toString();
			factoringUrl = callBackMap.get("factoringUrl").toString();
		} catch (Exception e) {
			return false;
		}
		//操作员
		String operator = msgMap.get("operator").toString();
		String serialID = msgMap.get("serialID") == null ? "" : msgMap.get("serialID").toString(); 
		if(msgList == null || msgList.size()<1 || StringUtils.isBlank(serialID)){
			return false;
		}
		Map<String, Object> msg = msgList.get(0);
		List<Map<String, Object>> actions = Util.jsonToListMap(msg.get("action").toString());
		Map<String, Object> action = actions.get(0);
		
		MsgSynchronizedLockUtil.lock(serialID);
		
		if (receiveMsgService.checkMsgRepeat(serialID)){
			//验证流水号是否存在
			ResultBackBean resultBackBean = receiveMsgService.buildResultBackBean(serialID, factoringUrl, mallUrl, serialID, "434", "报文流水号重复！");
			resultBackQueue.addResultBackInQ(resultBackBean);
		}else{
			//仅入库第一条msglist
			ReceiveMsgRecord receiveMsgRecord =  receiveMsgService.buildFactoringReceiveMsgRecord(serialID, data, action);
			receiveMsgService.saveReceiveMsgRecord(receiveMsgRecord);
			
			List<Map<String, Object>> successMsgList = receiveMsgService.checkedMsgList(serialID, msgList, integrity, factoringUrl, mallUrl);
			if (successMsgList.size() > 0 ){
				//根据业务流水，计算流水内所有action内帐号至少存在的金额
				List<TaskBean> taskBeans = receiveMsgService.buildMallTaskBeans(serialID, integrity, mallUrl, factoringUrl, successMsgList, operator);
				for (TaskBean taskBean : taskBeans) {
					taskBean = bankServerFactory.findBankInfoByAcct(taskBean);
					taskQueue.addTaskInQ(taskBean);
				}
				try {
					new ThreadPool().getInstanceConsumerThread();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		MsgSynchronizedLockUtil.removeLock(serialID);
		return true;
	}
	

}
