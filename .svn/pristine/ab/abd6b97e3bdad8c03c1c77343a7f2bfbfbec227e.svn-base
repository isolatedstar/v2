package com.zllh.payment.server.controller;

import com.zllh.payment.server.service.MallMsgService;
import com.zllh.payment.utils.CalculateLimitAmount;

public class MallMsgAction {
	
	public MallMsgService mallMsgService;
	
	public String msgFlowNo;
	
	public double outBankId;
	
    //入口，接收从商城发来的报文
	public void factoringMsgReceive(String msgXml){
		
		//1.解析校验报文是否合法
//		MsgParse.parseMsg();
		//校验报文
		mallMsgService.checkMessage();
		//2.判断数据库中是否有相同报文
		if(!mallMsgService.checkMsgRepeat(msgFlowNo))
		{
			//回调
		} else {
			//本地保存报文
			mallMsgService.saveMallSendMsg(msgXml);
			//计算极值
			CalculateLimitAmount.calcLimitAmt(msgXml, msgXml, null, null, null);
			//
			
		}
		
	}
}
