package com.zllh.payment.server.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.payment.front.dao.FreezeMapper;
import com.zllh.payment.front.dao.ReceiveMsgRecordMapper;
import com.zllh.payment.model.Freeze;
import com.zllh.payment.model.FundTransferDetail;
import com.zllh.payment.model.ReceiveMsgRecord;
import com.zllh.payment.model.ResultBackBean;
import com.zllh.payment.model.TaskBean;
import com.zllh.payment.server.pool.ThreadPool;
import com.zllh.payment.server.queue.ResultBackQueue;
import com.zllh.payment.server.queue.TaskQueue;
import com.zllh.payment.server.service.BankServerFactory;
import com.zllh.payment.server.service.FactoringMsgService;
import com.zllh.payment.server.service.FreezeService;
import com.zllh.payment.server.service.FundTransferDetailService;
import com.zllh.payment.server.service.ReceiveMsgService;
import com.zllh.payment.server.service.SendBankMsgService;
import com.zllh.payment.server.thread.ResultBackThread;
import com.zllh.payment.utils.MsgSynchronizedLockUtil;
import com.zllh.payment.utils.SpringBeanFactoryUtils;
import com.zllh.payment.utils.Util;

@Service
public class FactoringMsgServiceImpl implements FactoringMsgService {
	
	public Logger logger = Logger.getLogger(FactoringMsgServiceImpl.class);
	
	@Autowired
	private  ResultBackQueue resultBackQueue;
	
	@Autowired
	private ReceiveMsgService receiveMsgService;
	
	@Autowired
	private BankServerFactory bankServerFactory;
	
	@Autowired
	private TaskQueue taskQueue;
	
	@Autowired
	private SendBankMsgService sendBankMsgService;
	
	@Autowired
	private FreezeService freezeService;
	
	@Autowired
	private FundTransferDetailService fundTransferDetailService;

	SpringBeanFactoryUtils sBeanFactoryUtils;

    @Autowired
    private FreezeMapper freezeMapper;

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
	public void saveFactoringSendMsg(String msgXml) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveTransferStatus() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void directBankProcess() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void queryBalance(String acctNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void queryBalanceFromBank(String acctNo) {
		// TODO Auto-generated method stub
		
	}
	
	//释放锁定金额到账户余额
	public String rollBackBalanceLockAmt(Map<String, Object> map){
		String bankAcct = map.get("bankAcct").toString();
		BigDecimal amt = new BigDecimal(map.get("amt").toString());
		Freeze freeze = freezeService.findByBankAcct(bankAcct);
		if (freeze.getLockAmt().compareTo(amt) < 0){
			return "error";
		}
		freeze.setBalance(freeze.getBalance().add(amt));
		freeze.setLockAmt(freeze.getLockAmt().subtract(amt));
		freezeService.updateFreeze(freeze);
		return "success";
	}

	//彻底释放锁定金额（执行成功）
	public String releaseBalanceLockAmt(Map<String, Object> map) {
		String bankAcct = map.get("bankAcct").toString();
		String  bankAcctIn = map.get("bankAcctIn").toString();
		BigDecimal amt = new BigDecimal(map.get("amt").toString());
		Freeze freezeOut = freezeService.findByBankAcct(bankAcct);
		Freeze freezeIn = freezeService.findByBankAcct(bankAcctIn);
		if (freezeOut.getLockAmt().compareTo(amt) < 0){
			return "error";
		}
		freezeOut.setLockAmt(freezeOut.getLockAmt().subtract(amt));
		freezeIn.setBalance(freezeIn.getBalance().add(amt));
		freezeService.updateFreeze(freezeOut);
		freezeService.updateFreeze(freezeIn);
		return "success";
	}
	
	//锁定金额
	public String updateBalanceLockAmt(Map<String, Object> map) {

		String bankAcct = map.get("bankAcct").toString();
		BigDecimal amt = new BigDecimal(map.get("amt").toString());
		Freeze freeze = freezeService.findByBankAcct(bankAcct);
		if (amt.compareTo(BigDecimal.ZERO) != 1 || freeze.getBalance().compareTo(amt) == -1){
			return "error";
		}
		freeze.setBalance(freeze.getBalance().subtract(amt));
		freeze.setLockAmt(freeze.getLockAmt().add(amt));
		freezeService.updateFreeze(freeze);
		return "success";
	}
	
	

	@Override
	public BigDecimal setBalanceLockAmt(String acctNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeMsgFromList() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveTransferDetail() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean updateTransferStatus(String bankFlowId, int transferStatus) {
		if (StringUtils.isBlank(bankFlowId)){
			return false;
		}
		FundTransferDetail fundTransferDetail = fundTransferDetailService.findByPrimaryKey(bankFlowId);
		if (null == fundTransferDetail || fundTransferDetail.getTransferStatus() == transferStatus){
			return false;
		}
		fundTransferDetailService.updateTransferStatusByPrimaryKey(bankFlowId, transferStatus);
		return true;
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

	public boolean handleFactoringMsg(String data){
		logger.error("---------------------------------------接收报文！");
		logger.error(data);
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
			ResultBackBean resultBackBean = receiveMsgService.buildResultBackBean(serialID, factoringUrl, mallUrl, "", "434", "报文流水号重复！");
			resultBackQueue.addResultBackInQ(resultBackBean);
			ResultBackThread resultBackThread = new ResultBackThread();
			Thread thread = new Thread(resultBackThread);
			thread.start();
		}else{
			//仅入库第一条msglist
			ReceiveMsgRecord receiveMsgRecord = receiveMsgService.buildFactoringReceiveMsgRecord(serialID, data, action);
			receiveMsgService.saveReceiveMsgRecord(receiveMsgRecord);
			
			List<Map<String, Object>> successMsgList = receiveMsgService.checkedMsgList(serialID, msgList, integrity, factoringUrl, mallUrl);
			if (successMsgList.size() > 0 ){
				//根据业务流水，计算流水内所有action内帐号至少存在的金额
				List<TaskBean> taskBeans = receiveMsgService.buildFactoringTaskBeans(serialID, integrity, mallUrl, factoringUrl, successMsgList, operator);
				for (TaskBean taskBean : taskBeans) {
					taskBean = bankServerFactory.findBankInfoByAcct(taskBean);
					if( null != taskBean){
						taskQueue.addTaskInQ(taskBean);
					}
				}
				TaskQueue tQueue = (TaskQueue) SpringBeanFactoryUtils.getBean("taskQueue");
				if (tQueue.getQueueLength() > 0) {
					try {
						new ThreadPool().getInstanceConsumerThread();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		MsgSynchronizedLockUtil.removeLock(serialID);
		return true;
	}

	public double queryBalanceFromDB(String acctNo){
		Freeze freezeResutlt = freezeMapper.selectByPrimaryKey(acctNo);
		return freezeResutlt.getBalance().doubleValue();
	}

	public void querySendToBankMsgStatus(String acctNo){
		
	}

	@Override
	public String updateBalanceAndStatus(Map<String, Object> map) {
		//锁定余额
		updateBalanceLockAmt(map);
		//修改查询报文成功状态
		updateMsgStatus(map.get("bankFlowId").toString(),(byte)1);
		return null;
	}


	@Override
	public String updateFactoringSendMsgStatusAndUnLockAmt(Map<String, Object> maps) {

		//根据账户和状态修改
		updFactoringSendMsgStatus(maps);
		updBalanceUnLockAmt(maps);
		
		return null;
	}
	public void updFactoringSendMsgStatus(Map<String, Object> maps){
		
	}
	//解冻金额
	public void updBalanceUnLockAmt(Map<String, Object> maps){
		
	}

	@Override
	public void updateSendToBankMsgStatus(Map<String, Object> map) {

		sendBankMsgService.updateStatusByPrimaryKey(map);
	}

	@Override
	public void updateUnlockAllAccountBalance(List<Map<String, String>> accountBalance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMsgStatus(String flowId, Byte status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("serialID", flowId);
		receiveMsgRecordMapper.updateStatusByPrimaryKey(map); 
		
	}
}
