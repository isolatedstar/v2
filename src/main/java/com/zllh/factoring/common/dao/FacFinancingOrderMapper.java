package com.zllh.factoring.common.dao;

import com.zllh.factoring.common.model.FacFinancingOrder;

public interface FacFinancingOrderMapper {
    int deleteByPrimaryKey(String id);

    int insert(FacFinancingOrder record);

    int insertSelective(FacFinancingOrder record);

    FacFinancingOrder selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FacFinancingOrder record);

    int updateByPrimaryKey(FacFinancingOrder record);
}