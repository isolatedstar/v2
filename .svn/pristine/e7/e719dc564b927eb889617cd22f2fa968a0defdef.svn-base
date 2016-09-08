package com.zllh.payment.front.service;

import java.util.List;
import java.util.Map;

import com.zllh.payment.model.Bank;
import com.zllh.payment.model.BankAcctRule;
import com.zllh.payment.model.BankServer;
import com.zllh.payment.model.InterfaceMgt;

public interface BankMgtService {
	public List<Bank> getBankTree(String type);

	public List QueryBankInformation(String bankid);

	public Map<String,Object> QueryBankInformationinterface(Map para);

	public Map<String,Object> QueryBankInformationserver(Map map);

	public List QueryBankInformationrule(BankAcctRule BankAcctRule);

	public int updateserverfw(BankServer bankserver);

	public String updateserverwy(BankServer bankserver);

	public int deleteserverfw(String bankid);

	public String deleteserverwy(String bankid);

	public int updateinterfacefw(InterfaceMgt interfacemat);

	public String updateinterfacewy(InterfaceMgt interfacemat);

	public String deleteinterfacefw(String bankid);

	public int deleteinterfacewy(String bankid);

	public String GetTopNum(String id);

	public int addBank(Bank bank);

	public int updateBank(Bank bank);

	public int addInterface(InterfaceMgt InterfaceMgt);

	public int addServer(BankServer BankServer);

	public int addRule(BankAcctRule BankAcctRule);

	public int updateRule(BankAcctRule BankAcctRule);

	public int deleteRule(String ruleid);

	public int selectByBankidCount(BankAcctRule BankAcctRule);

	public List<BankAcctRule> getFatherRule(String bankId,int acctType);

	//通过银行名称查询同名银行有无。
	public List<Bank> getBankByName(String bankName);
	/**
	 * 根据服务起类型和服务器地址查询是否存在
	 * @param serverType
	 * @param serverAddress
	 * @return
	 */
	boolean isServerExist(String serverType, String serverAddress);
	
	/**
	 * 验证服务器名称是否存在
	 * @param serverNamebig
	 * @return
	 */
	boolean isServerNamed(String serverNamebig);
}
