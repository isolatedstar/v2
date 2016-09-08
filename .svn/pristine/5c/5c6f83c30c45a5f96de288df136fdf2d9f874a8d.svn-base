package com.zllh.payment.front.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.payment.front.service.ManulAjustService;
import com.zllh.payment.model.MsgBean;
import com.zllh.payment.model.ResultBackBean;
import com.zllh.payment.model.TaskBean;
import com.zllh.payment.server.pool.ThreadPool;
import com.zllh.payment.server.queue.ResultBackQueue;
import com.zllh.payment.server.queue.TaskQueue;
import com.zllh.payment.server.queue.AskBankQueue;
import com.zllh.payment.server.queue.ManulQueue;
import com.zllh.payment.server.service.FactoringMsgService;
import com.zllh.payment.server.service.ReceiveMsgService;
import com.zllh.payment.server.thread.ResultBackThread;

@Service
public class ManulAjustServiceImpl implements ManulAjustService {

	@Autowired
	private TaskQueue taskQueue;
	
	@Autowired
	private ManulQueue manulQueue;
	
	@Autowired
	private AskBankQueue askBankQueue;
	
	@Autowired
	private  ResultBackQueue resultBackQueue;
	
	@Autowired
	private ReceiveMsgService receiveMsgService;
	
	@Autowired
	private FactoringMsgService factoringMsgService;
	
	public void addToManulQueue(String flowId, String status){
		TaskBean taskBean = null;
		if (!StringUtils.isBlank(flowId)){
			if (!StringUtils.isBlank(flowId)){
				List<TaskBean> taskBeans = taskQueue.getQueueList();
				if (null != taskBeans){
					for (int i = 0; i < taskBeans.size(); i++) {
						TaskBean tb = taskBeans.get(i);
						tb.setResultCode(status);
						if (flowId.equals(tb.getFlowId())){
							taskBean = tb;
							break;
						}
					}
				}
			}
			if (null != taskBean){
				manulQueue.addTaskInQ(taskBean);
				Map<String, Object> taskBeanMap = getTaskBeanMapRemovedAllList(flowId);
				if ( null != taskBeanMap && null != taskBeanMap.get("taskBean")){
					TaskBean tb = (TaskBean)taskBeanMap.get("taskBean");
					taskQueue.removeTask(tb);
				}
				List<MsgBean> msgBeans = askBankQueue.getQueueList();
				if (null != msgBeans){
					 List<MsgBean> mBeans = new ArrayList<MsgBean>();
					for (int i = 0; i < msgBeans.size(); i++) {
						MsgBean msgBean = msgBeans.get(i);
						if (flowId.equals(msgBean.getFlowId())){
							mBeans.add(msgBean);
						}
					}
					if (mBeans.size() > 0){
						for (MsgBean mBean : mBeans) {
							askBankQueue.removeTask(mBean);
						}
					}
				}
			}
		}
	}
	
	public TaskBean removeMsgBean(String flowId, String msgId){
		if (!StringUtils.isBlank(flowId)){
			List<TaskBean> taskBeans = taskQueue.getQueueList();
			if (null != taskBeans){
				for (int i = 0; i < taskBeans.size(); i++) {
					TaskBean taskBean = taskBeans.get(i);
					if (flowId.equals(taskBean.getFlowId())){
						if (null != taskBean.getActMsgLst()){
							for (int j = 0; j < taskBean.getActMsgLst().size(); j++) {
								MsgBean msgBean = taskBean.getActMsgLst().get(j);
								if (msgId.equals(msgBean.getMsgId())){
									taskBean.getActMsgLst().remove(msgBean);
									taskQueue.updateTask(i, taskBean);
									return taskBean;
								}
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	public Map<String, Object> getTaskBeanMapRemovedQueryMsgList(String flowId){
		Map<String, Object> taskBeanMap = null;
		if (!StringUtils.isBlank(flowId)){
			taskBeanMap = new HashMap<String, Object>();
			List<TaskBean> taskBeans = taskQueue.getQueueList();
			if (null != taskBeans){
				for (int i = 0; i < taskBeans.size(); i++) {
					TaskBean tb = taskBeans.get(i);
					if (flowId.equals(tb.getFlowId())){
						tb.setQueryMsgLst(null);
						taskQueue.updateTask(i, tb);
						taskBeanMap.put("index", i);
						taskBeanMap.put("taskBean", tb);
						break;
					}
				}
			}
		}
		return taskBeanMap;
	}
	
	public Map<String, Object> getTaskBeanMapRemovedAllList(String flowId){
		Map<String, Object> taskBeanMap = null;
		if (!StringUtils.isBlank(flowId)){
			taskBeanMap = new HashMap<String, Object>();
			List<TaskBean> taskBeans = taskQueue.getQueueList();
			if (null != taskBeans){
				for (int i = 0; i < taskBeans.size(); i++) {
					TaskBean tb = taskBeans.get(i);
					if (flowId.equals(tb.getFlowId())){
						tb.setQueryMsgLst(null);
						tb.setActMsgLst(null);
						tb.setCalculatedMsgs(null);
						tb.setParsedMsgs(null);
						taskQueue.updateTask(i, tb);
						taskBeanMap.put("index", i);
						taskBeanMap.put("taskBean", tb);
						break;
					}
				}
			}
		}
		return taskBeanMap;
	}
	
	public Map<String, Object> getManulTaskBeanMapRemovedAllList(String flowId){
		Map<String, Object> taskBeanMap = null;
		if (!StringUtils.isBlank(flowId)){
			taskBeanMap = new HashMap<String, Object>();
			List<TaskBean> taskBeans = manulQueue.getQueueList();
			if (null != taskBeans){
				for (int i = 0; i < taskBeans.size(); i++) {
					TaskBean tb = taskBeans.get(i);
					if (flowId.equals(tb.getFlowId())){
						tb.setQueryMsgLst(null);
						tb.setActMsgLst(null);
						tb.setCalculatedMsgs(null);
						tb.setParsedMsgs(null);
						manulQueue.updateTask(i, tb);
						taskBeanMap.put("index", i);
						taskBeanMap.put("taskBean", tb);
						break;
					}
				}
			}
		}
		return taskBeanMap;
	}
	
	public void updateActMsgStatus(String flowId, String msgId, int status){
		if (!StringUtils.isBlank(flowId) && !StringUtils.isBlank(msgId)){
			List<TaskBean> taskBeans = taskQueue.getQueueList();
			if (null != taskBeans){
				for (int i = 0; i < taskBeans.size(); i++) {
					TaskBean taskBean = taskBeans.get(i);
					if (flowId.equals(taskBean.getFlowId())){
						List<MsgBean>  msgBeans = taskBean.getActMsgLst();
						if (null != msgBeans){
							boolean isUpdate = false;
							for (int j = 0; j < msgBeans.size(); j++) {
								MsgBean msgBean = msgBeans.get(j);
								if (msgId.equals(msgBean.getMsgId())){
									msgBean.setStatus(status);
									taskBean.getActMsgLst().set(j, msgBean);
									taskQueue.updateTask(i, taskBean);
									isUpdate = true;
									break;
								}
							}
							if (isUpdate){
								break;
							}
						}
					}
				}
			}
		}
	}
	
	public void updateActMsgBankStatus(String flowId, String msgId, String bankStatus, String statusText){
		if (!StringUtils.isBlank(flowId) && !StringUtils.isBlank(msgId)){
			List<TaskBean> taskBeans = taskQueue.getQueueList();
			if (null != taskBeans){
				for (int i = 0; i < taskBeans.size(); i++) {
					TaskBean taskBean = taskBeans.get(i);
					if (flowId.equals(taskBean.getFlowId())){
						List<MsgBean>  msgBeans = taskBean.getActMsgLst();
						if (null != msgBeans){
							boolean isUpdate = false;
							for (int j = 0; j < msgBeans.size(); j++) {
								MsgBean msgBean = msgBeans.get(j);
								if (msgId.equals(msgBean.getMsgId())){
									msgBean.setBankStatus(bankStatus);
									msgBean.setStatusText(statusText);
									taskBean.getActMsgLst().set(j, msgBean);
									taskQueue.updateTask(i, taskBean);
									isUpdate = true;
									break;
								}
							}
							if (isUpdate){
								break;
							}
						}
					}
				}
			}
		}
	}
	
	public void updateTaskBankStatus(String flowId, String bankStatus, String statusText){
		if (!StringUtils.isBlank(flowId)){
			List<TaskBean> taskBeans = taskQueue.getQueueList();
			if (null != taskBeans){
				for (int i = 0; i < taskBeans.size(); i++) {
					TaskBean taskBean = taskBeans.get(i);
					if (flowId.equals(taskBean.getFlowId())){
						taskBean.setBankStatus(bankStatus);
						taskBean.setStatusText(statusText);
						taskQueue.updateTask(i, taskBean);
						break;
					}
				}
			}
		}
	}
	
	public void removeTaskBean(String flowId){
		if (!StringUtils.isBlank(flowId)){
			Map<String, Object> taskBeanMap = getTaskBeanMapRemovedAllList(flowId);
			if ( null != taskBeanMap && null != taskBeanMap.get("index") && null != taskBeanMap.get("taskBean")){
				TaskBean taskBean = (TaskBean)taskBeanMap.get("taskBean");
				taskQueue.removeTask(taskBean);
			}
		}
	}
	
	public List<Map<String, Object>> findList(){
		//查询所有报文记录
		List<TaskBean> taskBeans = taskQueue.getQueueList();
		if (null != taskBeans){
			List<Map<String, Object>> taskBeanList = new ArrayList<Map<String, Object>>();
			for (TaskBean taskBean : taskBeans) {
				List<MsgBean> msgBeans = taskBean.getActMsgLst();
				Map<String, Object> taskBeanMap = new HashMap<String, Object>();// 定义map
				taskBeanMap.put("flowId", taskBean.getFlowId());
				List<Map<String, Object>> msgBeanList = new ArrayList<Map<String, Object>>();
				for (MsgBean msgBean : msgBeans) {
					Map<String, Object> msgBeanMap = new HashMap<String, Object>();// 定义map
					msgBeanMap.put("msgId", msgBean.getMsgId());
					msgBeanMap.put("msgContent", msgBean.getMsgContent());
					msgBeanMap.put("account", msgBean.getAccount());
					msgBeanMap.put("amt", msgBean.getAmt());
					msgBeanMap.put("accountOut", msgBean.getAccountOut());
					msgBeanMap.put("accountIn", msgBean.getAccountIn());
					msgBeanMap.put("userName", msgBean.getUserName());
					msgBeanList.add(msgBeanMap);
				}
				taskBeanMap.put("msgBeans", msgBeanList);
				taskBeanList.add(taskBeanMap);
			}
			return taskBeanList;
		}
		return null;
	}
	
	
	public List<TaskBean> findTaskBeanList(){
		return manulQueue.getQueueList();
	}
	
	public TaskBean findbyFlowID(String flowID){
		TaskBean taskBean = null;
		List<TaskBean> taskBeans = manulQueue.getQueueList();
		if (null != taskBeans){
			for (TaskBean tb : taskBeans) {
				if (flowID.equals(tb.getFlowId())){
					taskBean = tb;
					break ;
				}
			}
		}
		return taskBean;
	}
	
	public boolean handleTaskBean(String flowID){
		if (StringUtils.isBlank(flowID)){
			return false;
		}
		TaskBean taskBean = findbyFlowID(flowID);
		if (null == taskBean){
			return false;
		}
		//原始报文修改状态
		receiveMsgService.updateStatus(Byte.parseByte("10"), taskBean.getSerialID());
		//释放冻结金额
		List<Map<String, String>> freezeAccountAmounts = taskBean.getCalculatedMsgs();
		boolean isReleased = true;
		if (null != freezeAccountAmounts && freezeAccountAmounts.size() > 0){
			for (Map<String, String> freezeAccountAmount : freezeAccountAmounts) {
				Map<String, Object> map = new HashMap<String,Object>();
				map.put("bankAcct", freezeAccountAmount.get("payAccNo"));
				map.put("amt", freezeAccountAmount.get("tranAmt"));
				String result = factoringMsgService.rollBackBalanceLockAmt(map);
				if (!"success".equals(result)){
					isReleased = false;
				}
			}
		}
		if (isReleased){
			//回调商城或保理处理结果
			String serialID = taskBean.getSerialID();
			String factoringUrl = taskBean.getFactoringUrl();
			String mallUrl = taskBean.getMallUrl();
			ResultBackBean resultBackBean = receiveMsgService.buildResultBackBean(serialID, factoringUrl, mallUrl, flowID, "350", "处理成功");
			resultBackQueue.addResultBackInQ(resultBackBean);
			
			//从人工调整队列里移除本次处理的taskbean
			Map<String, Object> taskBeanMap = getManulTaskBeanMapRemovedAllList(flowID);
			if ( null != taskBeanMap && null != taskBeanMap.get("taskBean")){
				TaskBean tb = (TaskBean)taskBeanMap.get("taskBean");
				manulQueue.removeTask(tb);
			}
			manulQueue.removeTask(taskBean);
			
			//启动回调队列
			ResultBackThread resultBackThread = new ResultBackThread();
			Thread thread = new Thread(resultBackThread);
			thread.start();
			return true;
		}
		return false;
	}
	
	public boolean addIntoTaskQueue(String flowID){
		if (StringUtils.isBlank(flowID)){
			return false;
		}
		
		TaskBean taskBean = findbyFlowID(flowID);
		if (null == taskBean){
			return false;
		}
		
		//从人工调整队列里移除本次处理的taskbean
		Map<String, Object> taskBeanMap = getManulTaskBeanMapRemovedAllList(flowID);
		if ( null != taskBeanMap && null != taskBeanMap.get("taskBean")){
			TaskBean tb = (TaskBean)taskBeanMap.get("taskBean");
			manulQueue.removeTask(tb);
		}
		//添加到任务队列
		taskQueue.addTaskInQ(taskBean);
		try {
			new ThreadPool().getInstanceConsumerThread();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return true;
	}
	
}
