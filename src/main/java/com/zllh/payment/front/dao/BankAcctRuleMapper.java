package com.zllh.payment.front.dao;

import java.util.List;

import com.zllh.payment.model.BankAcctRule;

public interface BankAcctRuleMapper {
	int deleteByPrimaryKey(String bankRuleId);

	int insert(BankAcctRule record);

	int insertSelective(BankAcctRule record);

	BankAcctRule selectByPrimaryKey(String bankRuleId);

	int updateByPrimaryKeySelective(BankAcctRule record);

	int updateByPrimaryKey(BankAcctRule record);

	List<BankAcctRule> selectByBankid(BankAcctRule bankAcctRule);

	List<BankAcctRule> selectByBankidCount(BankAcctRule bankAcctRule);

	List<BankAcctRule> getFatherRule(String bankRuleId,int acctType);

}