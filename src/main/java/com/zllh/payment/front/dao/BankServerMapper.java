package com.zllh.payment.front.dao;

import java.util.List;
import java.util.Map;

import com.zllh.payment.model.BankServer;

public interface BankServerMapper {
    int deleteByPrimaryKey(String serverId);

    int insert(BankServer record);

    int insertSelective(BankServer record);

    BankServer selectByPrimaryKey(String serverId);

    int updateByPrimaryKeySelective(BankServer record);

    int updateByPrimaryKey(BankServer record);
    
    List<BankServer> selectByBankids(Map<String, Object> map);
    
    List<BankServer> selectByBankid(Map map);
    
    int updateByBankId(BankServer record);
    
    int deleteByBankId(String bankId);
    
    BankServer selectSingleByParams(Map<String, Object> params);

    int selectByBankidServerCount(String bankId);
}