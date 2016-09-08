package com.zllh.payment.server.service;

import java.util.Map;

import com.zllh.payment.model.FundTransferDetail;

/**
 * 转账记录明细service接口
 * @author dongll
 *
 */
public interface FundTransferDetailService {

	/**
	 * 根据银行流水号查询转账明细
	 * @param bankFlowId
	 */
	FundTransferDetail findByPrimaryKey(String bankFlowId);
	
	/**
	 * 根据银行流水号更新转账记录状态
	 * @param bankFlowId
	 * @param transferStatus
	 */
	void updateTransferStatusByPrimaryKey(String bankFlowId, int transferStatus);
	
	/**
	 * 保存转账明细记录
	 * @param fundTransferDetail
	 */
	void save(Map<String, Object> map);
	
	/**
	 * 批量添加转账详细
	 * @param fundTransferList
	 */
	void saveBatch(Map<String, Object> map);
}
