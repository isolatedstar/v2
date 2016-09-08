package com.zllh.payment.front.dao;

import java.util.List;
import java.util.Map;

import com.zllh.payment.model.InterfaceMgt;

public interface InterfaceMgtMapper {
    int deleteByPrimaryKey(String interfaceId);

    int insert(InterfaceMgt record);

    int insertSelective(InterfaceMgt record);

    InterfaceMgt selectByPrimaryKey(String interfaceId);

    int updateByPrimaryKeySelective(InterfaceMgt record);

    int updateByPrimaryKey(InterfaceMgt record);
    
    List<InterfaceMgt> selectByBankid(Map map);
    
    int updateByBankId(InterfaceMgt record);
    
    int deleteByBankId(String bankId);

    int selectByBankidCount(String bankId); 
//    int AddInterface(InterfaceMgt InterfaceMgt);
}