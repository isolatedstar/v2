package com.zllh.factoring.common.dao;

import com.zllh.factoring.common.model.FacWriteOffAcc;

public interface FacWriteOffAccMapper {
    int deleteByPrimaryKey(String id);

    int insert(FacWriteOffAcc record);

    int insertSelective(FacWriteOffAcc record);

    FacWriteOffAcc selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FacWriteOffAcc record);

    int updateByPrimaryKey(FacWriteOffAcc record);
}