package com.zllh.mall.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zllh.mall.common.model.MtOrder;

public interface MtOrderMapper {
    int insert(MtOrder record);

    int insertSelective(MtOrder record);
    
    int updateAll(MtOrder record);
    
    int updateByObject(@Param("record") MtOrder record);
    
    MtOrder selectByPrimaryKey(String id);
    
    List<MtOrder> searchMtOrder(MtOrder record);
    
    List<MtOrder> searchSettleMtOrder(MtOrder record);
    
    List<MtOrder> searchSendMtOrder(Map<String, Object> record);
    
    List<MtOrder> selectOppositeList(Map<String, Object> record);
    
    List<MtOrder> searchGetMtOrder(Map<String, Object> record);
    
    List<MtOrder> searchReturnMtOrder(Map<String, Object> record);
    
    List<MtOrder> searchGetReturnMtOrder(Map<String, Object> record);
    
    List<MtOrder> searchPayMoneyMtOrder(Map<String, Object> record);
    
    List<MtOrder> searchPayMoneyMtOrderForSettle(Map<String, Object> record);
    
//    List<MtOrder> searchGetMoneyMtOrder(Map<String, Object> record);
    
    List<MtOrder> searchRefundMtOrder(Map<String, Object> record);
    
//    List<MtOrder> searchGetRefundMtOrder(Map<String, Object> record);
}