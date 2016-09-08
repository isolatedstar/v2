package com.zllh.payment.server.service;

import java.util.List;
import java.util.Map;

import com.zllh.payment.model.ReceiveMsgRecord;
import com.zllh.payment.model.ResultBackBean;
import com.zllh.payment.model.TaskBean;

/**
 * 
 * @author dongll
 * @since 新支付系统
 */

public interface ReceiveMsgService {

	/**
	 * 根据报文流水号判断数据库中是否有相同报文
	 * @param msgFlowNo
	 * @return
	 */
	public boolean checkMsgRepeat(String msgFlowNo);
	
	
	/**
	 * 保存报文
	 * @param receiveMsgRecords
	 */
	void saveReceiveMsgRecords(List<ReceiveMsgRecord> receiveMsgRecords);
	
	/**
	 * 处理商城报文（验证、入库）
	 * @param data
	 * @return
	 */
	//boolean handleMallMsg(String data);
	
	/**
	 * 处理保理报文（验证、入库）
	 * @param data
	 * @return
	 */
	ResultBackBean  buildResultBackBean(String serialID, String factoringUrl, String mallUrl, String flowId, String resultCode, String data);
	
	/**
	 * 报文组校验
	 * @param msgList
	 * @param integrity
	 * @param factoringUrl
	 * @param mallUrl
	 * @return
	 */
	List<Map<String, Object>> checkedMsgList(String serialID, List<Map<String, Object>> msgList, String integrity, String factoringUrl, String mallUrl);
	
	/**
	 * 校验报文
	 * @param flowId
	 * @param actions
	 * @return
	 */
	Map<String, Object> checkedMsg(String serialID, List<Map<String, Object>> actions, String integrity, boolean isFirst);
	
	/**
	 *构建商城接收报文入库记录
	 * @param flowId
	 * @param data
	 * @param actions
	 * @return
	 */
	ReceiveMsgRecord buildMallReceiveMsgRecord(String serialID, String data, Map<String, Object> action);
	
	/**
	 *构建保理接收报文入库记录
	 * @param flowId
	 * @param data
	 * @param actions
	 * @return
	 */
	ReceiveMsgRecord buildFactoringReceiveMsgRecord(String serialID, String data, Map<String, Object> action);
	
	/**
	 * 构建商城taskbeans
	 * @param integrity  （报文执行方式：all 、 part）
	 * @param serialID   （流水号）
	 * @param callBackUrl （回调路径）
	 * @param msgList （报文msg数组）
	 * @param operator 
	 * @return
	 */
	List<TaskBean> buildFactoringTaskBeans(String serialID, String integrity,  String mallUrl, String factoringUrl, List<Map<String, Object>> msgList, String operator);
			
	/**
	 * 构建保理taskbeans
	 * @param integrity  （报文执行方式：all 、 part）
	 * @param flowId   （流水号）
	 * @param callBackUrl （回调路径）
	 * @param msgList （报文msg数组）
	 * @param operator 
	 * @return
	 */
	List<TaskBean> buildMallTaskBeans(String serialID, String integrity, String mallUrl, String factoringUrl, List<Map<String, Object>> msgList, String operator);
			
	/**
	 * 构建taskbeans	
	 * @param integrity  （报文执行方式：all 、 part）
	 * @param type    （类型：1、保理  2、商城）
	 * @param flowId   （流水号）
	 * @param callBackUrl （回调路径）
	 * @param msgList （报文msg数组）
	 * @param operator 
	 * @return
	 */
	List<TaskBean> buildTaskBeans(String serialID, String integrity,String type, String mallUrl, String factoringUrl, List<Map<String, Object>> msgList, String operator);
			
	/**
	 * 根据msg构建taskbean
	 * @param flowId
	 * @param mallUrl
	 * @param factoringUrl
	 * @param type
	 * @param calculatedMsgs
	 * @param parsedMsgs
	 * @return
	 */
	TaskBean buildTaskBean (String serialID, String flowId, String mallUrl, String factoringUrl, String type, List<Map<String, String>> calculatedMsgs, List<Map<String, String>> parsedMsgs, String operator);
	/**
	 * 计算msg内所有账号极值
	 * @param msg
	 * @return
	 */
	List<Map<String, String>> calculateLimitAmountByMsg(Map<String, Object> msg);
	
	/**
	 * integrity时，合并msglist中的action，并计算所有账号极值
	 * @param msgList
	 * @return
	 */
	List<Map<String, String>> calculateLimitAmountByAllMsg(List<Map<String, Object>> msgList);
	
	/**
	 * 保存报文
	 * @param receiveMsgRecord
	 */
	void saveReceiveMsgRecord(ReceiveMsgRecord receiveMsgRecord);
	
	/**
	 * 更新报文状态
	 * @param status
	 * @param flowId
	 */
	void updateStatus(Byte status, String flowId);

}
