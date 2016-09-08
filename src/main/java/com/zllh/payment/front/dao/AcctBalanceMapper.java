package com.zllh.payment.front.dao;

import java.util.List;
import java.util.Map;

import com.zllh.payment.model.AccountBalance;

public interface AcctBalanceMapper {
	
	//查询每个账户的金额
	public List<AccountBalance> queryAccountAmt(Map map);
	//查询每个账户的冻结金额
	public List<AccountBalance> queryAccountFreezeAmt(Map map);
	//查询符合条件的账户总件数
	public int queryAccountCount(Map<String, Object> mapdetails);
}
