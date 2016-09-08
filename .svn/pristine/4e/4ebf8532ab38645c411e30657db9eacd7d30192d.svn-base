package com.zllh.payment.front.dao;
import java.util.List;
import java.util.Map;

import com.zllh.payment.model.FundTransferDetail;

public interface FundTransferDetailMapper {
    int deleteByPrimaryKey(String bankFlowId);

    int insert(Map<String, Object> map);

    int insertSelective(FundTransferDetail record);

    FundTransferDetail selectByPrimaryKey(String bankFlowId);

    int updateByPrimaryKeySelective(FundTransferDetail record);

    int updateByPrimaryKey(FundTransferDetail record);
    
    List<FundTransferDetail> queryAccountBalanceByParams(Map map);

    //銀行流水明细的查詢
	List<FundTransferDetail> queryAllTransferByParams(Map<String, Object> mapdetails);
	//查询符合查询条件的总件数
	public int queryTransferCount(Map<String, Object> mapdetails);
	
	void updateTransferStatusByPrimaryKey(Map<String, Object> map);
	
	/**
	 * 批量添加转账详细
	 * @param fundTransferList
	 */
	void insertBatch(Map<String, Object> map);
}