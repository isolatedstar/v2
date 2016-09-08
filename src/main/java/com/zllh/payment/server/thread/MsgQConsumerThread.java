package com.zllh.payment.server.thread;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import com.zllh.payment.front.dao.FreezeMapper;
import com.zllh.payment.front.service.ManulAjustService;
import com.zllh.payment.model.Freeze;
import com.zllh.payment.model.FundTransferDetail;
import com.zllh.payment.model.MsgBean;
import com.zllh.payment.model.ResultBackBean;
import com.zllh.payment.model.TaskBean;
import com.zllh.payment.server.queue.AskBankQueue;
import com.zllh.payment.server.queue.ManulQueue;
import com.zllh.payment.server.queue.ResultBackQueue;
import com.zllh.payment.server.queue.TaskQueue;
import com.zllh.payment.server.service.FactoringMsgService;
import com.zllh.payment.server.service.FundTransferDetailService;
import com.zllh.payment.utils.PostBankMessage;
import com.zllh.payment.utils.SpringBeanFactoryUtils;
import com.zllh.payment.utils.Util;
import com.zllh.utils.common.StringUtil;

@Service
public class MsgQConsumerThread implements Runnable {

	// 索引位置
	private int indexPosition = 0;
	private long queneLen = 0;
	public Logger logger = Logger.getLogger(MsgQConsumerThread.class);
	public FreezeMapper freezeMapper = (FreezeMapper) SpringBeanFactoryUtils.getBean("freezeMapper");
	public Map<String, Object> mapServerTime = new HashMap<String, Object>();
	private TaskQueue tQueue = (TaskQueue) SpringBeanFactoryUtils.getBean("taskQueue");
	private AskBankQueue askBankQueue = (AskBankQueue) SpringBeanFactoryUtils.getBean("askBankQueue");
	private ManulQueue manulQueue = (ManulQueue) SpringBeanFactoryUtils.getBean("manulQueue");
	private ResultBackQueue resultBackQueue = (ResultBackQueue) SpringBeanFactoryUtils.getBean("resultBackQueue");
	private ManulAjustService manulAjustService = (ManulAjustService) SpringBeanFactoryUtils.getBean("manulAjustServiceImpl");
	private FundTransferDetailService fundTransferDetailService = (FundTransferDetailService) SpringBeanFactoryUtils.getBean("fundTransferDetailServiceImpl");
	private FactoringMsgService factoringMsgService = (FactoringMsgService) SpringBeanFactoryUtils.getBean("factoringMsgServiceImpl");

	@Override
	public void run() {
		System.out.println("进入消费线程！");
		logger.error("---------------------------------------------进入消费线程！");
		// try {
		// 1.取出报文list，逐条循环,向银行服务器逐条发送报文
		// 调用递归方法向银行发送报文
		queneLen = tQueue.getQueueLength();
		// 队列中含有任务的情况
		if (queneLen > 0) {
			sendTaskBean(tQueue.getNextTask());
		} else {
			// 队列为空.询问模块
			queryBankResult();
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("------------------------------------------执行完消费线程！");
	}

	public void sendTaskBean(TaskBean taskBean) {
		List<MsgBean> queryMsgList = taskBean.getQueryMsgLst();
		// 查询队列为空 说明已经发送过此报文。
		if (queryMsgList != null && queryMsgList.size() != 0) {
			List<String> listPostList = queryBalanceFromDB(queryMsgList);
			// DB中余额足够
			if (listPostList.size() == 0) {
				if (isIntervalEnough(taskBean.getServerUrl(), taskBean.getQueryMsgLst().get(0).getIntervalTime())) {
					logger.error("查询------------流水号为：" + taskBean.getFlowId() + "报文DB查询余额足够。");
					setBalanceStatus(queryMsgList, taskBean.getSerialID());
					// 从查询List中删除此报文
					manulAjustService.getTaskBeanMapRemovedQueryMsgList(taskBean.getFlowId());
					// 询问队列中的查询结果
					queryBankResult();
					// 发送转账报文
					handlerSend(taskBean);
				} else {
					goToNext();

				}
			} else {
				List<String> queryBankOk = queryBalanceFromBank(taskBean);
				// 余额足够
				if (queryBankOk.size() == 0) {
					if (isIntervalEnough(taskBean.getServerUrl(), taskBean.getQueryMsgLst().get(0).getIntervalTime())) {
						setBalanceStatus(queryMsgList, taskBean.getSerialID());
						// 从查询List中删除此报文
						manulAjustService.getTaskBeanMapRemovedQueryMsgList(taskBean.getFlowId());
						logger.error("-----------------------------经查询银行余额，报文中有账户余额足够。");
						// 询问队列中的查询结果
						queryBankResult();
						// 发送转账报文
						handlerSend(taskBean);
					} else {
						// 取下个报文组。
						goToNext();
					}

				} else if (!"queryException".equals(queryBankOk.get(0))) {
					// 余额不足
					// 通过总flowid将此任务放入异常队列
					manulAjustService.updateTaskBankStatus(taskBean.getFlowId(), "445", queryBankOk.get(0)+"账户余额不足！");
					manulAjustService.addToManulQueue(taskBean.getFlowId(), "445");
					ResultBackThread resultBackThread = new ResultBackThread();
					Thread thread = new Thread(resultBackThread);
					thread.start();
					logger.error("-----------------------------经查询银行余额，报文中有账户余额不足。");
					// 根据flowId从消费队列中移除该任务
					manulAjustService.removeTaskBean(taskBean.getFlowId());
				} else {
					// 查询异常
					// 通过总flowid将此任务放入异常队列
					logger.error("-----------------------------查询银行卡余额时发生异常，本次不处理，下次继续处理。");
					// 修改出错报文，状态及备注。
					// manulAjustService.updateActMsgBankStatus(taskBean.getFlowId(), "","queryException","查询余额异常");
					// manulAjustService.addToManulQueue(taskBean.getFlowId(),"445");
					// ResultBackThread resultBackThread = new ResultBackThread();
					// Thread thread=new Thread(resultBackThread);
					// thread.start();
				}

			}
		} else {
			handlerSend(taskBean);
		}
		// 队列不为空,取下个报文组。
		if (tQueue.getQueueLength() > 0) {
			// 取下个报文组。
			goToNext();
		}
	}

	private void goToNext() {
		// 取下个报文组。
		if (indexPosition < tQueue.getQueueLength() - 1 && indexPosition >= 0) {
			indexPosition = indexPosition + 1;
			sendTaskBean(tQueue.getTaskByIndex(indexPosition));
		} else {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			indexPosition = 0;
			sendTaskBean(tQueue.getTaskByIndex(indexPosition));
		}
	}

	private void setBalanceStatus(List<MsgBean> queryMsgList, String serialId) {
		// 余额足够1.更新余额冻结表2.更新报文成功状态
		Map<String, Object> map = new HashMap<>();
		for (int enoughAllAccount = 0; enoughAllAccount < queryMsgList.size(); enoughAllAccount++) {
			map.put("bankAcct", queryMsgList.get(enoughAllAccount).getAccountOut());
			map.put("amt", queryMsgList.get(enoughAllAccount).getAmt());
			map.put("status", "1");
			map.put("bankFlowId", serialId);
			factoringMsgService.updateBalanceAndStatus(map);
		}
	}

	// 判断队列是否为空
	public void checkTaskBeanIsEmpty(TaskBean taskBean) {
		// 询问队列中该队列为空时
		if (taskBean.getActMsgLst().size() == 0) {
			// 解锁全部账户结余金额
			factoringMsgService.updateUnlockAllAccountBalance(taskBean.getCalculatedMsgs());
			//修改原始报文状态9：已处理成功。
			factoringMsgService.updateMsgStatus(taskBean.getSerialID(), new Byte((byte) 9));
			// 根据flowId从消费队列中移除该任务
			manulAjustService.removeTaskBean(taskBean.getFlowId());
			indexPosition = indexPosition - 1;
			logger.error("队列移除taskBean------------从队列中移除流水号为：" + taskBean.getFlowId() + "的报文");
			// 回调.将taskBean中的属性赋值给回调对象，并放入回调队列。
			resultBackQueue.addResultBackInQ(setResultBackQueue(taskBean, "200", "成功！"));
			ResultBackThread resultBackThread = new ResultBackThread();
			Thread thread = new Thread(resultBackThread);
			thread.start();
			// 判断队列是否为空
			if (tQueue.getQueueLength() == 0) {
				// 队列为空，继续判断询问列表是否为空
				if (askBankQueue.getQueueLength() == 0) {
					// 询问列表为空，任务结束。
				} else {
					queryBankResult();
				}
			} else {
				// 队列不为空,取首个报文组。
				indexPosition = 0;
				sendTaskBean(tQueue.getNextTask());
			}
		}
	}

	// 发送转账报文
	private void handlerSend(TaskBean taskBean) {
		for (int j = 0; j < taskBean.getActMsgLst().size(); j++) {
			if (isIntervalEnough(taskBean.getServerUrl(), taskBean.getActMsgLst().get(j).getIntervalTime())) {
				// 发送报文
				// 没有删除的转账报文[未发送：0]，向银行发送
				if (taskBean.getActMsgLst().get(j).getStatus() == 0) {
					// 记录本服务器地址发送报文时间
					mapServerTime.put(taskBean.getServerUrl(), new Date().getTime());
					// 设置银行询问的serverUrl
					taskBean.getActMsgLst().get(j).setServerUrl(taskBean.getServerUrl());
					// 调用发送报文线程
					// new TransferMsgThreadPool().getInstanceThreadWithParam(taskBean.getServerUrl(), taskBean.getActMsgLst().get(j));
					String returnXmlString;
					returnXmlString = PostBankMessage.post(taskBean.getServerUrl(), taskBean.getActMsgLst().get(j).getMsgContent());
					if(StringUtil.isNull(returnXmlString)){
						logger.error("###############向银行发送转账报文的时候，前置机传回来一个空串！！！");
						return;
					}
					String status = Util.getXmlElementByName(returnXmlString.replace('"', '\"'), "status");
					String statusText = Util.getXmlElementByName(returnXmlString.replace('"', '\"'), "statusText");
					if ("AAAAAA".equals(status.substring(0, 6))) {
						// 发送成功加入询问队列
						askBankQueue.addTaskInQ(taskBean.getActMsgLst().get(j));
						saveTofundTransferDdtail(taskBean, j);
						// 设置报文发送状态[已发送：1]
						manulAjustService.updateActMsgStatus(taskBean.getFlowId(), taskBean.getActMsgLst().get(j).getMsgId(), 1);
						logger.error("############已发送组流水号为：" + taskBean.getFlowId() + "&&银行流水号为：" + taskBean.getActMsgLst().get(j).getMsgId() + "的报文");
					} else {
						logger.error("############流水号为：" + taskBean.getFlowId() + "&&银行流水号为：" + taskBean.getActMsgLst().get(j).getMsgId() + "的报文发送失败，并已移除该任务。放入人工调整队列。");
						// 发送失败
						manulAjustService.updateActMsgStatus(taskBean.getFlowId(), taskBean.getActMsgLst().get(j).getMsgId(), 2);
						// 修改出错报文，状态及备注。
						manulAjustService.updateActMsgBankStatus(taskBean.getFlowId(), taskBean.getActMsgLst().get(j).getMsgId(), status, statusText);
						// 通过总flowid将此任务放入异常队列
						manulAjustService.addToManulQueue(taskBean.getFlowId(), "446");
						ResultBackThread resultBackThread = new ResultBackThread();
						Thread thread = new Thread(resultBackThread);
						thread.start();
						// 从原任务队列移除。
						indexPosition = indexPosition - 1;
						manulAjustService.removeTaskBean(taskBean.getFlowId());
						break;
					}
				}
				// 询问模块
				queryBankResult();
			}
		}
	}

	private void saveTofundTransferDdtail(TaskBean taskBean, int j) {
		// 插入已发送的报文到转账报文列表。
		FundTransferDetail fundTransferDetail = createFundTransferDetail(taskBean.getActMsgLst().get(j));
		fundTransferDetail.setOperator(taskBean.getOperator());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("transDetail", fundTransferDetail);
		// 记录信息到资金划转列表
		fundTransferDetailService.save(map);
		logger.error("############转账报文入库,流水号为：" + taskBean.getFlowId() + "银行流水号为:" + taskBean.getActMsgLst().get(j).getMsgId() + "的报文已发送，将初始状态计入转账报文表。");
	}

	// 查询间隔够不够
	private boolean isIntervalEnough(String serverUrl, long intervalTime) {
		// 首次服务器访问，时间间隔足够
		if (mapServerTime.get(serverUrl) == null) {
			mapServerTime.put(serverUrl, new Date().getTime());
			return true;
		} else {
			// 非首次访问，查询报文检查一次，转账报文的时间间隔逐条检查。
			long putTime = (long) mapServerTime.get(serverUrl);
			long nowTime = new Date().getTime();
			// 判断查询报文第一条时间间隔
			if (nowTime - putTime >= intervalTime) {
				// 时间间隔达到，或从未发送过该服务器。
				return true;
			} else {
				return false;
			}
		}
	}

	// 查询数据库中余额足够
	private List<String> queryBalanceFromDB(List<MsgBean> queryMsg) {
		List<String> listNullAccount = new ArrayList<String>();
		for (int i = 0; i < queryMsg.size(); i++) {
			double dbBalance = factoringMsgService.queryBalanceFromDB(queryMsg.get(i).getAccountOut());
			if (dbBalance < queryMsg.get(i).getAmt()) {
				listNullAccount.add(queryMsg.get(i).getAccountOut());
			}
		}
		return listNullAccount;
	}

	// 询问队列模块
	public void queryBankResult() {
		for (int i = 0; i < askBankQueue.getQueueLength(); i++) {
			MsgBean msgBean = askBankQueue.getTaskByIndex(i);
			String xmlPostString = createQueryResultMsg(msgBean);
			// 轮询队列不为空
			long nowTime = (new Date()).getTime();
			long putTime = (long) mapServerTime.get(msgBean.getServerUrl());
			logger.error(nowTime+"----------"+putTime);
			if (nowTime - putTime > msgBean.getIntervalTime()) {
				mapServerTime.put(msgBean.getServerUrl(), new Date().getTime());
				// 符合时间间隔,发送询问转账结果报文。
				String backXml = "";
				// 查找银行返回报文中的status内容。
				backXml = PostBankMessage.post(msgBean.getServerUrl(), xmlPostString);
				if(StringUtil.isNull(backXml)){
					logger.error("###############询问转账结果的时候，前置机传回来一个空串！！！");
					return;
				}
				String sendStatus = Util.getXmlElementByName(backXml.replace('"', '\"'), "status");
				String statusText = Util.getXmlElementByName(backXml.replace('"', '\"'), "statusText");
				String sendStatusOfRow = Util.getXmlElementByOneName(backXml.replace('"', '\"'), "status");
				String statusTextOfRow = Util.getXmlElementByOneName(backXml.replace('"', '\"'), "statusText");
				// 不同返回结果的处理
				if ("AAAAAAA".equals(sendStatus)) {
					if ("AAAAAAA".equals(sendStatusOfRow)) {
						// 发送成功，1.修改余额和锁定金额
						Map<String, Object> maps = new HashMap<String, Object>();
						maps.put("bankAcct", msgBean.getAccountOut());
						maps.put("bankAcctIn", msgBean.getAccountIn());
						
						maps.put("amt", msgBean.getAmt());
						factoringMsgService.releaseBalanceLockAmt(maps);
						// 2.更新报文成功状态
						Map<String, Object> mapsStatus = new HashMap<String, Object>();
						mapsStatus.put("bankFlowId", msgBean.getMsgId());
						mapsStatus.put("status", "1");
						factoringMsgService.updateSendToBankMsgStatus(mapsStatus);
						// 3.修改资金划转列表划账状态(1 刚接收，2解析完，3无需处理，4已处理)
						factoringMsgService.updateTransferStatus(msgBean.getMsgId(), 4);
						// 4.从询问列表移除
						askBankQueue.removeTask(msgBean);
						// 5.从List中删除此报文
						logger.error("############队列移除msgBean,从队列中移除银行流水号为：" + msgBean.getMsgId() + "的报文");
						checkTaskBeanIsEmpty(manulAjustService.removeMsgBean(msgBean.getFlowId(), msgBean.getMsgId()));
					} else if ("AAAAAA".equals(sendStatusOfRow.substring(0, 6)) || "ED02091".equals(sendStatusOfRow)) {
						//内层正常，下次查询
					} else {
						//内层查询不正常，如账户余额不足等。
						// 更新报文失败状态
						Map<String, Object> mapsStatus = new HashMap<String, Object>();
						mapsStatus.put("bankFlowId", msgBean.getMsgId());
						mapsStatus.put("status", "1");
						factoringMsgService.updateSendToBankMsgStatus(mapsStatus);
						// 修改出错报文，状态及备注。
						manulAjustService.updateActMsgBankStatus(msgBean.getFlowId(), msgBean.getMsgId(), sendStatusOfRow, statusTextOfRow);
						// 通过总flowid将此任务放入异常队列
						manulAjustService.addToManulQueue(msgBean.getFlowId(), "447");
						ResultBackThread resultBackThread = new ResultBackThread();
						Thread thread = new Thread(resultBackThread);
						thread.start();
					}
					
				} else if ("AAAAAA".equals(sendStatus.substring(0, 6))) {
					// 处理中，下次询问
				} else {
					// 更新报文失败状态
					Map<String, Object> mapsStatus = new HashMap<String, Object>();
					mapsStatus.put("bankFlowId", msgBean.getMsgId());
					mapsStatus.put("status", "1");
					factoringMsgService.updateSendToBankMsgStatus(mapsStatus);
					// 修改出错报文，状态及备注。
					manulAjustService.updateActMsgBankStatus(msgBean.getFlowId(), msgBean.getMsgId(), sendStatus, statusText);
					// 通过总flowid将此任务放入异常队列
					manulAjustService.addToManulQueue(msgBean.getFlowId(), "447");
					ResultBackThread resultBackThread = new ResultBackThread();
					Thread thread = new Thread(resultBackThread);
					thread.start();
				}
			}
		}
	}

	// 设置回调Bean的内容
	public ResultBackBean setResultBackQueue(TaskBean taskBean, String status, String content) {
		ResultBackBean reBackBean = new ResultBackBean();
		reBackBean.setFactoringUrl(taskBean.getFactoringUrl());
		reBackBean.setFlowId(taskBean.getFlowId());
		reBackBean.setSerialID(taskBean.getSerialID());
		reBackBean.setIntervalTime(taskBean.getIntervalTime());
		reBackBean.setMallUrl(taskBean.getMallUrl());
		reBackBean.setStatus(status);
		reBackBean.setResultContent(content);
		reBackBean.setStartTime(taskBean.getStartTime());
		return reBackBean;
	}

	// 向银行查询余额。
	private List<String> queryBalanceFromBank(TaskBean taskBean) {
		List<String> enoughFlg = new ArrayList<String>();
		List<MsgBean> queryMsgList = taskBean.getQueryMsgLst();
		for (int j = 0; j < queryMsgList.size(); j++) {
			// 发送报文
			String backXml = PostBankMessage.post(taskBean.getServerUrl(), taskBean.getQueryMsgLst().get(j).getMsgContent());
			if(StringUtil.isNull(backXml)){
				logger.error("###############查询银行卡余额的时候，前置机传回来一个空串！未知银行卡余额足够与否。");
				return enoughFlg;
			}
			// 查找银行返回报文中的status内容。
			String sendStatus = Util.getXmlElementByName(backXml.replace('"', '\"'), "status");
			if ("AAAAAAA".equals(sendStatus)) {
				// 取出银行余额
				String balanceString = Util.getXmlElementByOneName(backXml.replace('"', '\"'), "SJAMT");
				double queryBankBalance = 0.0;
				if (balanceString != null || "".equals(balanceString)) {
					queryBankBalance = Double.parseDouble(balanceString);
				}
				// 有一个账户银行查询余额不足则退出循环。
				if (queryBankBalance < taskBean.getActMsgLst().get(j).getAmt()) {
					enoughFlg.add(taskBean.getActMsgLst().get(j).getAccountOut());
					break;
				} else {
					// 更新余额表该账户余额。
					Freeze freeze = new Freeze();
					freeze.setBankAcct(taskBean.getActMsgLst().get(j).getAccountOut());
					freeze.setBalance(new BigDecimal(queryBankBalance));
					freezeMapper.updateByPrimaryKeySelective(freeze);
				}
			} else {
				// 回调保理（发生查询余额报文异常）
				enoughFlg.add("queryException");
				break;
			}
		}

		return enoughFlg;
	}

	// 做成查询转账结果报文
	public String createQueryResultMsg(MsgBean msgBean) {
		StringBuffer sbQuery = new StringBuffer();
		sbQuery.append("<?xml version=\"1.0\" encoding=\"GBK\"?><stream><action>");
		// 查询转账报文结果
		sbQuery.append("DLCIDSTT");
		sbQuery.append("</action><userName>");
		sbQuery.append(msgBean.getUserName());
		sbQuery.append("</userName><clientID>");
		sbQuery.append(msgBean.getMsgId());
		sbQuery.append("</clientID><type></type></stream>");
		return sbQuery.toString();
	}

	// 根据转账报文，生成资金划转明细表的内容
	public FundTransferDetail createFundTransferDetail(MsgBean transferList) {
		FundTransferDetail fundDetail = new FundTransferDetail();
		// 流水号
		fundDetail.setBankFlowId(transferList.getMsgId());
		// 业务类型
		fundDetail.setBusinessType(1);
		// 业务类型.保理
		fundDetail.setBusinessTypeName("保理");
		// 入账号
		fundDetail.setInAcct(transferList.getAccountIn());
		// 入账户的主账户
		fundDetail.setInMasterAcct(transferList.getAccount());
		// 接口类型
		fundDetail.setInterfaceStatus(1);
		// 接口类型名称
		fundDetail.setInterfaceStatusName("银企直联");
		// 未锁定
		fundDetail.setLockFlag(0);
		// 报文来源
		fundDetail.setMsgSource("保理");
		// 出账户
		fundDetail.setOutAcct(transferList.getAccountOut());
		// 出账户的主账户
		fundDetail.setOutMasterAcct(transferList.getAccount());
		return fundDetail;
	}
}
