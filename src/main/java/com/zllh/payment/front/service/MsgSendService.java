package com.zllh.payment.front.service;

import java.util.List;

import com.zllh.payment.model.SendBankMsg;

public interface MsgSendService {

	public List<SendBankMsg> getMessageByCon(SendBankMsg sendBankMsg, int page, int rows);
	
	public int getMsgSendCount(SendBankMsg sendBankMsg);

}
