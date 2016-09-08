package com.zllh.payment.front.dao;

import com.zllh.payment.model.CallbackMsg;

public interface CallbackMsgMapper {
    int deleteByPrimaryKey(String flowId);

    int insert(CallbackMsg record);

    int insertSelective(CallbackMsg record);

    CallbackMsg selectByPrimaryKey(String flowId);

    int updateByPrimaryKeySelective(CallbackMsg record);

    int updateByPrimaryKeyWithBLOBs(CallbackMsg record);

    int updateByPrimaryKey(CallbackMsg record);
}