package com.zllh.factoring.common.dao;

import java.util.List;

import com.zllh.factoring.common.model.FacGuaranteeRefundInterest;

public interface FacGuaranteeRefundInterestMapper {
    int deleteByPrimaryKey(String id);

    int insert(FacGuaranteeRefundInterest record);

    int insertSelective(FacGuaranteeRefundInterest record);

    FacGuaranteeRefundInterest selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FacGuaranteeRefundInterest record);

    int updateByPrimaryKey(FacGuaranteeRefundInterest record);

	void insertguaranteeRefundInterestByList(List<FacGuaranteeRefundInterest> guaranteeRefundInterestList);
}