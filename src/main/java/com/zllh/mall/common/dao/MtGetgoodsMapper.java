package com.zllh.mall.common.dao;

import java.util.Map;

import com.zllh.mall.common.model.MtGetgoods;


public interface MtGetgoodsMapper {
    int insert(MtGetgoods record);

    int insertSelective(MtGetgoods record);
    
    //正向收货记录统计
    double selectForwardSumGetGoods(Map<String,Object> record);
    
    //反向收货记录统计
    double selectReverseSumGetGoods(Map<String,Object> record);
}