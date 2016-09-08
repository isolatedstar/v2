package com.zllh.mall.common.dao;

import java.util.Map;

import com.zllh.mall.common.model.MtSendgoods;

public interface MtSendgoodsMapper {
    int insert(MtSendgoods record);

    int insertSelective(MtSendgoods record);
    
    //正向发货记录统计
    double selectForwardSumSendGoods(Map<String,Object> record);
    
    //反向发货记录统计
    double selectReverseSumSendGoods(Map<String,Object> record);
}