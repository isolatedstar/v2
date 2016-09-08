package com.zllh.payment.server.service;

import java.util.List;

import com.zllh.payment.model.MsgBean;
import com.zllh.payment.model.TaskBean;

public interface BankMsgBuilder {

	//动态指定接口类，做成查询报文
	public List<MsgBean> callInterfaceClassQuery(TaskBean taskBean, String interfaceClass) throws Exception;
	//动态指定接口类，做成转账报文。
	public List<MsgBean> callInterfaceClassTransfer(TaskBean taskBean, String interfaceClass) throws Exception;
}
