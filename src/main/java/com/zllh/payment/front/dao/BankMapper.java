package com.zllh.payment.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zllh.payment.model.Bank;

public interface BankMapper {
	List<Bank> findBankTree(@Param("type") String type);

	int deleteByPrimaryKey(String bankId);

	int insert(Bank record);

	int insertSelective(Bank record);

	Bank selectByPrimaryKey(String bankId);
	String getTopNum(String bankId);

	int updateByPrimaryKeySelective(Bank record);

	int updateByPrimaryKey(Bank record);
	
	int AddBank(Bank record); 
	
	public List<Bank> selectByBankName(String bankName);
}
