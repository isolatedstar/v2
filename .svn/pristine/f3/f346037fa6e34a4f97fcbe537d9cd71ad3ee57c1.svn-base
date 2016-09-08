package com.zllh.payment.front.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zllh.payment.model.AccountBalance;


public interface AcctBalanceService {
	
	//查询银行账户余额
	public List SendBalanceRequests(List lists);
	//查询支付平台账余额
	public List<AccountBalance> queryAccountBalanceByParams(HttpServletRequest request);
	//导出查询条件下的全部数据
	public void exportBalanceInfo();
	//查询总记录条数
	public int queryAccountCount(HttpServletRequest request);
}
