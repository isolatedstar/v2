package com.zllh.payment.server.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.zllh.payment.front.dao.ReceiveMsgRecordMapper;
import com.zllh.payment.front.service.AcctMgtService;
import com.zllh.payment.front.service.UtilService;
import com.zllh.payment.model.AcctMgt;
import com.zllh.payment.model.BankBean;
import com.zllh.payment.model.ReceiveMsgRecord;
import com.zllh.payment.model.ResultBackBean;
import com.zllh.payment.model.TaskBean;
import com.zllh.payment.server.queue.ResultBackQueue;
import com.zllh.payment.server.service.BankServerFactory;
import com.zllh.payment.server.service.ReceiveMsgService;
import com.zllh.payment.server.service.SendBankMsgService;
import com.zllh.payment.server.thread.ResultBackThread;
import com.zllh.payment.utils.CalculateLimitAmount;
import com.zllh.payment.utils.Util;


@Service
public class ReceiveMsgServiceImpl implements ReceiveMsgService{
	public Logger logger = Logger.getLogger(ReceiveMsgServiceImpl.class);
	
	@Autowired
	private  ReceiveMsgRecordMapper receiveMsgRecordMapper;
	
	@Autowired
	private BankServerFactory  bankServerFactory;
	
	@Autowired
	private UtilService utilService;
	
	@Autowired
	private  ResultBackQueue resultBackQueue;
	
	@Autowired
	private AcctMgtService acctMgtService;
	
	@Autowired
	private SendBankMsgService sendBankMsgService;

	public boolean checkMsgRepeat(String serialID) {
		ReceiveMsgRecord receiveMsgRecord = receiveMsgRecordMapper.selectByPrimaryKey(serialID);
		if (null == receiveMsgRecord){
			return false;
		}
		return true;
	}

	
	public void saveReceiveMsgRecords(List<ReceiveMsgRecord> receiveMsgRecords){
		Assert.notNull(receiveMsgRecords);
		
		for (ReceiveMsgRecord receiveMsgRecord : receiveMsgRecords) {
			receiveMsgRecordMapper.insert(receiveMsgRecord);
		}
	}
	
	public void saveReceiveMsgRecord(ReceiveMsgRecord receiveMsgRecord){
		Assert.notNull(receiveMsgRecord);
		receiveMsgRecordMapper.insert(receiveMsgRecord);
	}
	
	public void updateStatus(Byte status, String serialID){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("serialID", serialID);
		receiveMsgRecordMapper.updateStatusByPrimaryKey(map);
	}
	
	public ResultBackBean  buildResultBackBean(String serialID, String factoringUrl, String mallUrl, String flowId, String resultCode, String data){
		//创建返回队列 
		ResultBackBean resultBackBean = new ResultBackBean();
		resultBackBean.setFactoringUrl(factoringUrl);
		resultBackBean.setMallUrl(mallUrl);
		resultBackBean.setSerialID(serialID);
		resultBackBean.setFlowId(flowId);
		resultBackBean.setStatus(resultCode);
		resultBackBean.setResultContent(data);
		return resultBackBean;
	}
	
	public ReceiveMsgRecord buildMallReceiveMsgRecord(String serialID, String data, Map<String, Object> action){
		return buildReceiveMsgRecord(serialID, "2", data, action);
	}
	
	public ReceiveMsgRecord buildFactoringReceiveMsgRecord(String serialID, String data, Map<String, Object> action){
		return buildReceiveMsgRecord(serialID, "1", data, action);
	}
	
	public ReceiveMsgRecord buildReceiveMsgRecord(String serialID, String type, String data, Map<String, Object> action){
		Assert.notNull(action);
		String recvAccNo = action.get("recvAccNo").toString();
		//加入入库数组
		ReceiveMsgRecord receiveMsgRecord = new ReceiveMsgRecord();
		receiveMsgRecord.setSerialID(serialID);
		receiveMsgRecord.setMsgContext(data);
		//sender 商城 或  保理      //receiver根据收款账号查询所属银行
		BankBean bankBean = bankServerFactory.findBankByAcctRule(recvAccNo);
		String recieverId = "";
		if(null != bankBean && StringUtils.isBlank(bankBean.getBankZhiId())){
			recieverId = bankBean.getBankZhiId();
		}else if (null != bankBean && null == bankBean.getBankZhiId() && StringUtils.isBlank(bankBean.getBankFenId())){
			recieverId = bankBean.getBankFenId();
		}else if (null != bankBean && null == bankBean.getBankFenId() && StringUtils.isBlank(bankBean.getBankZongId())){
			recieverId = bankBean.getBankZongId();
		}
		receiveMsgRecord.setMsgRecevier(recieverId);
		receiveMsgRecord.setMsgSender(type);//1保理，2商城
		receiveMsgRecord.setStatus(Byte.parseByte("1"));//1、已生产  1、已发送  3、已驳回
		receiveMsgRecord.setMsgSenderTime(new Date());
		return receiveMsgRecord;
	}
	
	public List<Map<String, Object>> checkedMsgList(String serialID,List<Map<String, Object>> msgList, String integrity, String factoringUrl, String mallUrl){
		boolean isFirst = true;
		boolean isAllSuccess = true;
		List<Map<String, Object>> successMsgList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> msg : msgList) {
			String flowId = msg.get("flowID").toString();
			List<Map<String, Object>> actions = Util.jsonToListMap(msg.get("action").toString());
			if (msgList.indexOf(msg) > 0){
				isFirst = false;
			}
			//验证报文是否有效
			Map<String, Object> message = checkedMsg(flowId,  actions, integrity, isFirst);
			boolean isEffective = Boolean.parseBoolean(message.get("isEffective").toString());
			String content = message.get("content").toString();
			String resultCode = message.get("resultCode").toString();
			if (!isEffective){
				//校验失败
				ResultBackBean resultBackBean = buildResultBackBean(serialID, factoringUrl, mallUrl, flowId, resultCode, content);
				resultBackQueue.addResultBackInQ(resultBackBean);
				isAllSuccess = false;
				if ("all".equals(integrity)){
					break;
				}
			}else{
				//校验成功
				successMsgList.add(msg);
			}
		}
		if (!isAllSuccess){
			ResultBackThread resultBackThread = new ResultBackThread();
			Thread thread = new Thread(resultBackThread);
			thread.start();
		}
		return successMsgList;
	}
	
	public String formatString(Object obj){
		return obj == null ? "" : obj.toString();
	}

	public Map<String, Object> checkedMsg(String flowId, List<Map<String, Object>> actions, String integrity, boolean isFirst){
		boolean isEffective = true;
		String content = "";
		String resultCode = "";
		if (StringUtils.isBlank(flowId)){
			isEffective = false;
			content = "流水号不存在或为空！";
		}else{
			List<String> clientIDs = new ArrayList<String>();
			for (Map<String, Object> action : actions) {
				logger.error("------------------------action >>>> "+ action.toString());
				String code = formatString(action.get("code"));
				String userName = formatString(action.get("userName"));
				String accountNo = formatString(action.get("accountNo"));
				String recvAccNo = formatString(action.get("recvAccNo"));
				String recvAccNm = formatString(action.get("recvAccNm"));
				String tranAmt = formatString(action.get("tranAmt"));
				String clientID = formatString(action.get("clientID"));
				String tranType = formatString(action.get("tranType"));
				String payAccNo = formatString(action.get("payAccNo"));
				
				if (StringUtils.isBlank(code)){
					isEffective  = false;
					content = "操作命令错误";
					resultCode = "420";
					break;
				}else if (StringUtils.isBlank(userName) ){
					isEffective  = false;
					content = "登录名为空或不存在！";
					resultCode = "421";
					break;
				}else if(StringUtils.isBlank(accountNo)){
					isEffective  = false;
					content = "主账号为空或不存在！";
					resultCode = "422";
					break;
				}else if(code.equals("ZLMDTFER") && StringUtils.isBlank(payAccNo)){
					//出金操作不存在付款帐号 ZLMDTFER
					isEffective  = false;
					content = "付款附属账号为空或不存在！";
					resultCode = "423";
					break;
				}else if(StringUtils.isBlank(recvAccNo)){
					isEffective  = false;
					content = "收款附属账号为空或不存在！";
					resultCode = "424";
					break;
				}else if(StringUtils.isBlank(recvAccNm)){
					isEffective  = false;
					content = "收款附属账户名称为空或不存在！";
					resultCode = "425";
					break;
				}else if (StringUtils.isBlank(tranAmt) || !matchAmt(tranAmt)){
					isEffective  = false;
					content = "交易金额格式错误！";
					resultCode = "426";
					logger.info("交易金额格式错误！交易金额＝"+tranAmt);
					break;
				}else if (payAccNo.equals(recvAccNo)){
					isEffective  = false;
					content = "付款账号与收款账号相同！";
					resultCode = "427";
					break;
				}else if (code.equals("ZLMDTFER") && StringUtils.isBlank(tranType)){
					isEffective  = false;
					content = "转账类型为空或不存在！";
					resultCode = "428";
					break;
				}else if (StringUtils.isBlank(clientID) || clientID.length() > 20){
					isEffective  = false;
					content = "clientID格式错误（长度不能超过20）！";
					logger.info("clientID格式错误（长度不能超过20）！clientID＝"+clientID);
					resultCode = "429";
					break;
				}else if (clientIDs.contains(clientID)){
					isEffective  = false;
					content = "报文内clientID重复！";
					resultCode = "430";
					break;
				}else{
					if (code.equals("ZLMDTFER")){
						AcctMgt payAcctMgt = new AcctMgt();
						payAcctMgt.setMasterAcct(accountNo);
						payAcctMgt.setBankAcct(payAccNo);
						payAcctMgt.setAcctName("");
						payAcctMgt.setStatus(Byte.valueOf("1"));
						int payAcctCount = acctMgtService.getAcctMsgCount(payAcctMgt);
						if (payAcctCount == 0){
							isEffective  = false;
							content = "不存在主账号"+accountNo+",付款账号为"+payAccNo+",账户名为"+recvAccNm+"的账号记录！";
							logger.info(content);
							resultCode = "431";
							break;
						}
						
						AcctMgt subAcctMgt = new AcctMgt();
						subAcctMgt.setMasterAcct(accountNo);
						subAcctMgt.setBankAcct(recvAccNo);
						subAcctMgt.setAcctName(recvAccNm);
						subAcctMgt.setStatus(Byte.valueOf("1"));
						int subAcctCount = acctMgtService.getAcctMsgCount(subAcctMgt);
						if (subAcctCount == 0){
							isEffective  = false;
							content = "不存在主账号"+accountNo+"收款账号为"+recvAccNo+"的账号记录！";
							resultCode = "432";
							logger.info(content);
							break;
						}	
					}else if ("ZLFNDOUT".equals(code)){
						AcctMgt payAcctMgt = new AcctMgt();
						payAcctMgt.setBankAcct(accountNo);
						payAcctMgt.setAcctName("");
						payAcctMgt.setStatus(Byte.valueOf("1"));
						int payAcctCount = acctMgtService.getAcctMsgCount(payAcctMgt);
						if (payAcctCount == 0){
							isEffective  = false;
							content = "不存在付款账号为"+accountNo+"的账号记录！";
							logger.info(content);
							resultCode = "431";
							break;
						}
						
						AcctMgt subAcctMgt = new AcctMgt();
						subAcctMgt.setBankAcct(recvAccNo);
						subAcctMgt.setAcctName(recvAccNm);
						subAcctMgt.setStatus(Byte.valueOf("1"));
						int subAcctCount = acctMgtService.getAcctMsgCount(subAcctMgt);
						if (subAcctCount == 0){
							isEffective  = false;
							content = "不存在收款账号为"+recvAccNo+",账户名为"+recvAccNm+"的账号记录！";
							resultCode = "432";
							logger.info(content);
							break;
						}	
					}
				}
				clientIDs.add(clientID);
			}
			if (isEffective){
				//验证clientIDs是否已经存在
				if (sendBankMsgService.isBankFlowIdExist(clientIDs)){
					isEffective  = false;
					content = "clientId已存在！";
					resultCode = "433";
					logger.info(content);
				}
			}
		}
		Map<String, Object> messageMap = new HashMap<String, Object>();
		messageMap.put("isEffective", isEffective);
		messageMap.put("content", content);
		messageMap.put("resultCode", resultCode);
		return messageMap;
	}
	
	//构建商城taskbeans
	public List<TaskBean> buildFactoringTaskBeans(String serialID, String integrity, String mallUrl, String factoringUrl, List<Map<String, Object>> msgList, String operator){
		return buildTaskBeans(serialID, integrity,"1", mallUrl, factoringUrl, msgList, operator);
	}
	
	//构建保理taskbeans
	public List<TaskBean> buildMallTaskBeans(String serialID, String integrity, String mallUrl, String factoringUrl, List<Map<String, Object>> msgList, String operator){
		Assert.notNull(msgList);
		return buildTaskBeans(serialID, integrity, "2", mallUrl, factoringUrl,  msgList, operator);
	}
	
	public List<TaskBean> buildTaskBeans(String serialID, String integrity,String type, String mallUrl, String factoringUrl, List<Map<String, Object>> msgList, String operator){
		Assert.notNull(msgList);
		
		List<TaskBean> taskBeans = new ArrayList<TaskBean>();
		if ("all".equals(integrity)){
			//合并msg的action
			List<Map<String, String>> parseMsgList = new ArrayList<Map<String, String>>();
			List<Map<String, String>> calculatedMsgs = null;
			String flowId = "";
			for (Map<String, Object> msg : msgList) {
				if (StringUtils.isBlank(flowId)){
					flowId =  msg.get("flowID").toString();
				}
				List<Map<String, Object>> actions = Util.jsonToListMap(msg.get("action").toString());
				for (Map<String, Object> action : actions) {
					Map<String, String> actionMap = buildTransferActionParse(action, flowId);
					parseMsgList.add(actionMap);
				}
			}
			//合并msg的极值计算
			calculatedMsgs = calculateLimitAmountByAllMsg(msgList);
			logger.error("#####################################################封装查询报文数据："+calculatedMsgs.toString());
			TaskBean taskBean =buildTaskBean(serialID, flowId, mallUrl, factoringUrl, type, calculatedMsgs, parseMsgList, operator);
			taskBeans.add(taskBean);
		}else if ("part".equals(integrity)){
			for (Map<String, Object> msg : msgList) {
				String flowId = msg.get("flowID").toString();
				List<Map<String, String>> parseMsgList = new ArrayList<Map<String, String>>();
				List<Map<String, String>> calculatedMsgs = null;
				List<Map<String, Object>> actions = Util.jsonToListMap(msg.get("action").toString());
				for (Map<String, Object> action : actions) {
					Map<String, String> actionMap = buildTransferActionParse(action, flowId);
					parseMsgList.add(actionMap);
				}
				//合并msg的极值计算
				calculatedMsgs = calculateLimitAmountByMsg(msg);
				logger.error("#####################################################封装查询报文数据："+calculatedMsgs.toString());
				TaskBean taskBean =buildTaskBean(serialID, flowId, mallUrl, factoringUrl, type, calculatedMsgs, parseMsgList, operator);
				taskBeans.add(taskBean);
			}	
		}
		return  taskBeans;
	}

	public TaskBean buildTaskBean (String serialID, String flowId, String mallUrl, String factoringUrl, String type, List<Map<String, String>> calculatedMsgs, List<Map<String, String>> parsedMsgs, String operator){
		Assert.notNull(calculatedMsgs);
		TaskBean taskBean = new TaskBean();
		taskBean.setFlowId(flowId);
		taskBean.setSerialID(serialID);
		taskBean.setParsedMsgs(parsedMsgs);
		taskBean.setCalculatedMsgs(calculatedMsgs);
		taskBean.setMallUrl(mallUrl);
		taskBean.setFactoringUrl(factoringUrl);
		taskBean.setMsgFrom(type);
		taskBean.setOperator(operator);
		return taskBean;
	}
	
	/**
	 * 构建报文
	 * @param action
	 * @return
	 */
	private Map<String, String> buildTransferActionParse(Map<String, Object> action, String flowId){
		Map<String, String> actionMap = new HashMap<String, String>();
		String userName =  formatString(action.get("userName"));
		String accountNo = formatString(action.get("accountNo"));
		String payAccNo = formatString(action.get("payAccNo"));
		String recvAccNo = formatString(action.get("recvAccNo"));
		String tranAmt = formatString(action.get("tranAmt"));
		String clientID = formatString(action.get("clientID"));
		String tranType = formatString(action.get("tranType"));
		String recvAccNm = formatString(action.get("recvAccNm"));
		String freezeNo = formatString(action.get("freezeNo"));
		String code = formatString(action.get("code"));
		String tranFlag = formatString(action.get("tranFlag"));
	
		actionMap.put("action", code);
		actionMap.put("userName", userName);
		actionMap.put("clientID", clientID);
		actionMap.put("accountNo", accountNo);
		actionMap.put("payAccNo", payAccNo);
		actionMap.put("tranType", tranType);
		actionMap.put("recvAccNo", recvAccNo);
		actionMap.put("recvAccNm", recvAccNm);
		actionMap.put("tranAmt", tranAmt);
		actionMap.put("freezeNo", freezeNo);
		actionMap.put("account", code);
		actionMap.put("tranFlag", tranFlag);
		actionMap.put("flowId", flowId);

		return actionMap;
	}
	
	
	
	//integrity=ALL
	public List<Map<String, String>> calculateLimitAmountByAllMsg(List<Map<String, Object>> msgList){
		List<Map<String, String>> calculatedMsgs = new ArrayList<Map<String, String>>();
		List<Map<String, String>> queryMsgs = new ArrayList<Map<String, String>>();
		//获取每个账号及与账号相关的转账业务
		List<Map<String, String>> transfers = new ArrayList<Map<String, String>>();
		List<String> totaLNos =  new ArrayList<String>();
		for (Map<String, Object> msg : msgList) {
			List<Map<String, Object>> actions = Util.jsonToListMap(msg.get("action").toString());
			for (Map<String, Object> action : actions) {
				String userName = formatString(action.get("userName"));
				String payAccNo = formatString(action.get("payAccNo"));
				String recvAccNo = formatString(action.get("recvAccNo"));
				String recvAccNm = formatString(action.get("recvAccNm"));
				String tranAmt = formatString(action.get("tranAmt"));
				String accountNo = formatString(action.get("accountNo"));
				if (0 == totaLNos.size()){
					totaLNos.add(payAccNo);
					totaLNos.add(recvAccNo);
				}else{
					totaLNos = checkAccountExist(payAccNo, totaLNos);
					totaLNos = checkAccountExist(recvAccNo, totaLNos);
				}
				Map<String, String> transferMap = new HashMap<String, String>();
				transferMap.put("payAccNo", payAccNo);
				transferMap.put("recvAccNo", recvAccNo);
				transferMap.put("tranAmt", tranAmt);
				transferMap.put("recvAccNm", recvAccNm);
				transferMap.put("userName", userName);
				transferMap.put("accountNo", accountNo);
				transfers.add(transferMap);
			}
		}
		if (transfers.size() > 0){
			//获取每个帐号的极值
			calculatedMsgs = CalculateLimitAmount.calculatelLimitAmt(totaLNos, transfers);
			queryMsgs = getQueryMsgs(calculatedMsgs, transfers);
		}
		return queryMsgs;
	}
	
	public List<Map<String, String>> getQueryMsgs(List<Map<String, String>> calculatedMsgs,  List<Map<String, String>> transfers){
		List<Map<String, String>> queryMsgs = new ArrayList<Map<String, String>>();
		for (Map<String, String> calculateMsg : calculatedMsgs) {
			String account = calculateMsg.get("account");
			String limitAmt = calculateMsg.get("limitAmt");
			for (Map<String, String> map : transfers) {
				String payAccNo = formatString(map.get("payAccNo"));
				String recvAccNo = formatString(map.get("recvAccNo"));
				if (account.equals(payAccNo) || account.equals(recvAccNo)){
					Map<String, String> queryMsg = new HashMap<String, String>();
					String userName = formatString(map.get("userName"));
					String accountNo = formatString(map.get("accountNo"));
					queryMsg.put("accountNo", accountNo);
					queryMsg.put("payAccNo", account);
					queryMsg.put("userName", userName);
					queryMsg.put("tranAmt", limitAmt);
					queryMsgs.add(queryMsg);
					break;
				}
			}
		}
		return queryMsgs;
	}
	
	
	public List<Map<String, String>> calculateLimitAmountByMsg(Map<String, Object> msg){
		Assert.notNull(msg);
		List<Map<String, String>> calculatedMsgs = new ArrayList<Map<String, String>>();
		List<Map<String, String>> queryMsgs = new ArrayList<Map<String, String>>();
		String flowId = msg.get("flowID").toString();
		List<Map<String, Object>> actions = Util.jsonToListMap(msg.get("action").toString());
		Map<String, String> msgMap = new HashMap<String, String>();
		msgMap.put("flowId", flowId);
		//获取每个账号及与账号相关的转账业务
		List<Map<String, String>> transfers = new ArrayList<Map<String, String>>();
		List<String> totaLNos =  new ArrayList<String>();
		for (Map<String, Object> action : actions) {
			String userName = formatString(action.get("userName"));
			String payAccNo = formatString(action.get("payAccNo"));
			String recvAccNo = formatString(action.get("recvAccNo"));
			String recvAccNm = formatString(action.get("recvAccNm"));
			String tranAmt = formatString(action.get("tranAmt"));
			String accountNo = formatString(action.get("accountNo"));
			if (0 == totaLNos.size()){
				totaLNos.add(payAccNo);
				totaLNos.add(recvAccNo);
			}else{
				totaLNos = checkAccountExist(payAccNo, totaLNos);
				totaLNos = checkAccountExist(recvAccNo, totaLNos);
			}
			Map<String, String> transferMap = new HashMap<String, String>();
			transferMap.put("payAccNo", payAccNo);
			transferMap.put("recvAccNo", recvAccNo);
			transferMap.put("tranAmt", tranAmt);
			transferMap.put("recvAccNm", recvAccNm);
			transferMap.put("userName", userName);
			transferMap.put("accountNo", accountNo);
			transfers.add(transferMap);
		}
		//获取每个帐号的极值
		if (transfers.size() > 0){
			calculatedMsgs = CalculateLimitAmount.calculatelLimitAmt(totaLNos, transfers);
			queryMsgs = getQueryMsgs(calculatedMsgs, transfers);
		}
		return queryMsgs;
	}
	
	public List<String> checkAccountExist(String accountNo, List<String> accounts){
		boolean isExist = false;
		for (String aNo : accounts) {
			if (aNo.equals(accountNo)){
				isExist = true;
				break;
			}
		}
		if (!isExist){
			accounts.add(accountNo);
		}
		return accounts;
	}
	
	public boolean matchAmt(String amt){
		// 若金额为科学计数法，择转换为数字
		if(!StringUtils.isBlank(amt) && amt.toUpperCase().contains("E")){
			amt = new BigDecimal(amt).toPlainString();
		}
		// 判断小数点后2位的数字的正则表达式  
		Pattern pattern=Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); 
	    Matcher match=pattern.matcher(amt);   
		return match.matches();
	}

}
