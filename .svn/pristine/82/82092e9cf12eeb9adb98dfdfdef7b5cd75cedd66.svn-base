package com.zllh.payment.server.service;

import java.util.List;

public interface MallMsgService {
	
	//根据报文流水号判断数据库中是否有相同报文。
	public boolean checkMsgRepeat(String msgFlowNo);
	//校验报文
	public boolean checkMessage();
	//本地保存接收到的报文	
	public void saveMallSendMsg(String msgXml);
	//更新（原）报文状态
	public void updateMsgStatus();
	//调网银
	public void internetbankProcess();
	//
	public List getResultBackByStatus(String status);
	//添加已加入回调队列的报文到数据库。状态：已添加
	public void saveMsgStatus();
	//向保理/商城发送向保理/商城发送
	public boolean sendResultToMall();
	//网银回调的存入DB
	public boolean saveBankBackMsg();
	//更新商城保理收到的报文状态并保存新生成的报文
	public void updateMsgStatusSaveBuildMsg();
	//查找账号表，找到银行id
	public String findBankByAccountNum(String acct);
	//回调报文入库
	public void saveCallBackMsg();
	//修改报文状态&保持回调报文
	public void updateMsgStatusSaveBackMsg();
	
	public void addTaskInQ(String json);
	
	/**
	 * 处理从商城接收的报文信息
	 * @param data
	 * @return
	 */
	boolean handleMallMsg(String data);
}
