package com.zllh.payment.server.controller;

import com.zllh.payment.server.service.FactoringMsgService;
import com.zllh.payment.server.service.MallMsgService;

public class BankCallAction {

	public MallMsgService mallMsgService;
	
	public FactoringMsgService factoringMsgService;
	
	public void bankCall(String msgs){
		
		//保存银行回调的报文
//		mallMsgService = new MallMsgServiceImpl();
		mallMsgService.saveBankBackMsg();
		//解析银行回调的报文

	
	}
}
