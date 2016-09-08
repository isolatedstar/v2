package com.zllh.factoring.common.dao;

import java.util.HashMap;
import java.util.List;

import com.zllh.factoring.common.model.FacRefund;

public interface FacRefundMapper {
    int deleteByPrimaryKey(String refundId);

    int insert(FacRefund record);

    int insertSelective(FacRefund record);

    FacRefund selectByPrimaryKey(String refundId);

    int updateByPrimaryKeySelective(FacRefund record);

    int updateByPrimaryKey(FacRefund record);

	List<FacRefund> findRepaymentByFinancingId(HashMap<String, Object> paramMap);

	int findRepaymentCountByFinancingId(HashMap<String, Object> paramMap);

	int selectByRefundOrderNum(HashMap<String, Object> param);
}