package com.zllh.payment.front.dao;
import java.util.List;
import java.util.Map;

import com.zllh.payment.model.SendBankMsg;

public interface SendBankMsgMapper {
    int deleteByPrimaryKey(String bankFlowId);

    int insert(SendBankMsg record);

    int insertSelective(SendBankMsg record);

    SendBankMsg selectByPrimaryKey(String bankFlowId);

    List<SendBankMsg> selectMsgSendByCon(SendBankMsg sendBankMsg);
    
    int selectMsgSendCount(SendBankMsg sendBankMsg);
    
    int updateByPrimaryKeySelective(SendBankMsg record);

    int updateByPrimaryKeyWithBLOBs(SendBankMsg record);

    int updateByPrimaryKey(SendBankMsg record);
    
    int updateStatusByPrimaryKey(Map map);
    
    void insertBatch(Map<String, Object> map);
    
    int selectCount(List<String> clientIds);
}