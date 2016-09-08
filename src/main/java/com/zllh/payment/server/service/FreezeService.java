package com.zllh.payment.server.service;


import com.zllh.payment.model.Freeze;

public interface FreezeService {

	/**
	 * 根据银行帐户查询冻结记录
	 * @param bankAcct
	 * @return
	 */
	Freeze findByBankAcct(String bankAcct);

	/**
	 * 更新冻结记录
	 * @param freeze
	 */
	void updateFreeze(Freeze freeze);
	
}
