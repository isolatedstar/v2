package com.zllh.payment.front.dao;

import java.util.Map;

import com.zllh.payment.model.ReceiveMsgRecord;

public interface ReceiveMsgRecordMapper {
    int deleteByPrimaryKey(String flowId);

    int insert(ReceiveMsgRecord record);

    int insertSelective(ReceiveMsgRecord record);

    ReceiveMsgRecord selectByPrimaryKey(String flowId);

    int updateByPrimaryKeySelective(ReceiveMsgRecord record);

    int updateByPrimaryKeyWithBLOBs(ReceiveMsgRecord record);

    int updateByPrimaryKey(ReceiveMsgRecord record);
    
    int updateStatusByPrimaryKey(Map<String, Object> map);
    
    public String updateMallSendMsgStatus(Map<String, Object> map);
}