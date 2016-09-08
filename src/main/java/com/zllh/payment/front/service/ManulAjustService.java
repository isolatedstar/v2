package com.zllh.payment.front.service;

import java.util.List;
import java.util.Map;

import com.zllh.payment.model.TaskBean;

public interface ManulAjustService {
	
	/**
	 * 添加到人工调整队列
	 * @param flowId
	 */
	void addToManulQueue(String flowId, String status);
	
	/**
	 * 查询转账任务队列
	 * @return
	 */
	List<Map<String, Object>> findList();
	
	/**
	 * 从队列中删除msg
	 * @param flowId
	 * @param msgId
	 * @return 
	 */
	TaskBean removeMsgBean(String flowId, String msgId);
	
	/**
	 * 删除taskBean
	 * @param flowId
	 */
	void removeTaskBean(String flowId);
	
	/**
	 * 获取taskBean并且置空taskBean中的msgBeanList
	 * @param flowId
	 * @return
	 */
	Map<String, Object> getTaskBeanMapRemovedQueryMsgList(String flowId);
	
	/**
	 * 更新taskBean状态
	 * @param flowId
	 * @param msgId
	 * @param status
	 */
	void updateActMsgStatus(String flowId, String msgId, int status);
	
	/**
	 * 修改银行返回处理结果状态
	 * @param flowId
	 * @param msgId
	 * @param bankStatus
	 * @param statusText
	 */
	void updateActMsgBankStatus(String flowId, String msgId, String bankStatus, String statusText);
	
	/**
	 * 查询taskbean列表
	 * @return
	 */
	List<TaskBean> findTaskBeanList();
	
	/**
	 * 根据flowID查询taskbean
	 * @param flowID
	 * @return
	 */
	TaskBean findbyFlowID(String flowID);
	
	/**
	 * 根据flowID处理异常队列任务
	 * @param flowID
	 * @return
	 */
	boolean handleTaskBean(String flowID);
	
	/**
	 * 根据流水号修改操作状态
	 * @param flowId
	 * @param bankStatus
	 * @param statusText
	 */
	void updateTaskBankStatus(String flowId, String bankStatus, String statusText);
	
	/**
	 * 加入任务队列
	 * @param flowID
	 * @return
	 */
	boolean addIntoTaskQueue(String flowID);

}
