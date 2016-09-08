package com.zllh.payment.server.service;

import java.util.List;
import java.util.Map;

import com.zllh.payment.model.SendBankMsg;

public interface SendBankMsgService {
	/**
	 * 更新状态
	 * @param maps
	 */
	void updateStatusByPrimaryKey(Map<String, Object> map);
	
	/**
	 * 添加发送记录
	 * @param sendBankMsg
	 */
	void save(SendBankMsg sendBankMsg);
	
	/**
	 * 添加批量发送报文记录
	 * @param sendBankList
	 */
	void saveBatch(Map<String, Object> map);
	
	/**
	 * 查询发送银行报文存在数量
	 * @param clientIds ＝ bankflowIds
	 * @return
	 */
	int selectCount(List<String> clientIds);
	
	/**
	 * 查询bankflowId是否存在
	 * @param clientIds
	 * @return
	 */
	boolean isBankFlowIdExist(List<String> clientIds);
}
