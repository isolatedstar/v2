package com.zllh.payment.front.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.payment.front.dao.SendBankMsgMapper;
import com.zllh.payment.front.service.MsgSendService;
import com.zllh.payment.model.SendBankMsg;

@Service
public class MsgSendServiceImpl implements MsgSendService {
	
	@Autowired
	private SendBankMsgMapper SendBankMsgMapper;

	@Override
	public List<SendBankMsg> getMessageByCon(SendBankMsg sendBankMsg, int page, int rows) {
		sendBankMsg.setPage((page - 1) * rows);
		sendBankMsg.setRows(rows);
		return SendBankMsgMapper.selectMsgSendByCon(sendBankMsg);
	}

	@Override
	public int getMsgSendCount(SendBankMsg sendBankMsg) {
		
		int msgCount = SendBankMsgMapper.selectMsgSendCount(sendBankMsg);

		return msgCount;
	}


}
