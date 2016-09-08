package com.zllh.mall.common.dao;

import java.util.List;
import java.util.Map;

import com.zllh.mall.common.model.MtGetpaymoney;

public interface MtGetpaymoneyMapper {
	
	MtGetpaymoney selectByPrimaryKey(String id);
	
    int insert(MtGetpaymoney record);

    int insertSelective(MtGetpaymoney record);
    
    int updateByObject(MtGetpaymoney record);
    
    int deleteByPrimaryKey(String id);
    
    //已付：正向付款记录统计
    double selectForwardSumPay(Map<String,Object> record);
    
    //已退：反向付款记录统计
    double selectReverseSumPay(Map<String,Object> record);
    
    //已收：正向收款记录统计
    double selectForwardSumReceipt(Map<String,Object> record);
    
    //已收退：反向收款记录统计
    double selectReverseSumReceipt(Map<String,Object> record);
    
    //锁定
    double selectSumLock(Map<String,Object> record);
    
    List<MtGetpaymoney> searchGetMoney(Map<String, Object> record);
    
    List<MtGetpaymoney> searchGetRefund(Map<String, Object> record);
}