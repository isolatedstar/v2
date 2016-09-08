package com.zllh.factoring.common.dao;

import java.util.List;

import com.zllh.factoring.common.model.FacFinancingRefund;

public interface FacFinancingRefundMapper {
    int deleteByPrimaryKey(String refundId);

    int insert(FacFinancingRefund record);

    int insertSelective(FacFinancingRefund record);

    FacFinancingRefund selectByPrimaryKey(String refundId);

    int updateByPrimaryKeySelective(FacFinancingRefund record);

    int updateByPrimaryKey(FacFinancingRefund record);

	void insertFinancingRefundByList(List<FacFinancingRefund> facFinancingRefundList);
}