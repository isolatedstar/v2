package com.zllh.payment.server.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.payment.front.dao.SendBankMsgMapper;
import com.zllh.payment.model.SendBankMsg;
import com.zllh.payment.server.service.SendBankMsgService;
import com.zllh.payment.utils.SpringBeanFactoryUtils;

@Service
public class SendBankMsgServiceImpl implements SendBankMsgService{

    @Autowired
	SpringBeanFactoryUtils sBeanFactoryUtils;

    private SendBankMsgMapper sendBankMsgMapper = (SendBankMsgMapper) sBeanFactoryUtils.getBean("sendBankMsgMapper");
	
	public void updateStatusByPrimaryKey(Map<String, Object> map) {
		sendBankMsgMapper.updateStatusByPrimaryKey(map);
	}

	public void save(SendBankMsg sendBankMsg){
		sendBankMsgMapper.insert(sendBankMsg);
	}
	
	public void saveBatch(Map<String, Object> map){
		sendBankMsgMapper.insertBatch(map);
	}
	
	public int selectCount(List<String> clientIds){
		return sendBankMsgMapper.selectCount(clientIds);
	}
	
	public boolean isBankFlowIdExist(List<String> clientIds){
		int count = sendBankMsgMapper.selectCount(clientIds);
		if (count > 0 ){
			return true;
		}else{
			return false;
		}
	}
}
