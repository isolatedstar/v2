package com.zllh.mall.common.dao;

import java.util.List;

import com.zllh.mall.common.model.MtSettleOrder;

public interface MtSettleOrderMapper {
    int deleteByPrimaryKey(String id);

    int insert(MtSettleOrder record);

    int insertSelective(MtSettleOrder record);

    MtSettleOrder selectByPrimaryKey(String id);
    
    List<MtSettleOrder> queryForRefuse(String settleId);

    int updateByPrimaryKeySelective(MtSettleOrder record);

    int updateByPrimaryKey(MtSettleOrder record);
}