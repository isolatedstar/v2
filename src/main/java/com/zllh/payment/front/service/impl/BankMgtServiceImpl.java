package com.zllh.payment.front.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.payment.front.dao.BankAcctRuleMapper;
import com.zllh.payment.front.dao.BankMapper;
import com.zllh.payment.front.dao.BankServerMapper;
import com.zllh.payment.front.dao.InterfaceMgtMapper;
import com.zllh.payment.front.dao.SendBankMsgMapper;
import com.zllh.payment.front.service.BankMgtService;
import com.zllh.payment.model.Bank;
import com.zllh.payment.model.BankAcctRule;
import com.zllh.payment.model.BankServer;
import com.zllh.payment.model.InterfaceMgt;

@Service
public class BankMgtServiceImpl implements BankMgtService {

	@Autowired
	private BankMapper bankMapper;

	@Autowired
	private BankServerMapper bankServerMapper;

	@Autowired
	private SendBankMsgMapper SendBankMsgMapper;

	@Autowired
	private InterfaceMgtMapper InterfaceMgtMapper;

	@Autowired
	private BankAcctRuleMapper BankAcctRuleMapper;

	@Override
	public List<Bank> getBankTree(String type) {
		// 根节点
		Bank root = null;
		List<Bank> banks = bankMapper.findBankTree(type);
		List<Bank> bankTree = new ArrayList<Bank>();

		for (int i = 0; i < banks.size(); i++) {
			// 找到根节点
			if (banks.get(i).getId().equals("0")) {
				root = banks.get(i);
				// 找到根节点后移除，减少循环次数，提高效率
				banks.remove(banks.get(i));
				// 找到根节点的所有子节点
				List<Bank> zongBanks = getchildren(root, banks);
				root.setChildren(zongBanks);
				// 为子节点再找到下一级子节点
				for (int j = 0; j < zongBanks.size(); j++) {
					Bank zBank = zongBanks.get(j);
					List<Bank> fenBanks = getchildren(zBank, banks);
					zBank.setChildren(fenBanks);
					// 同样继续往下找子节点
					for (int k = 0; k < fenBanks.size(); k++) {
						Bank fBank = fenBanks.get(k);
						List<Bank> zhiBanks = getchildren(fBank, banks);
						fBank.setChildren(zhiBanks);
					}
				}
			}
		}
		bankTree.add(root);

		return bankTree;
	}

	/**
	 * 找到子节点的方法
	 * 
	 * @param parent
	 * @param banks
	 * @return
	 */
	public List<Bank> getchildren(Bank parent, List<Bank> banks) {
		List<Bank> chindList = new ArrayList<Bank>();

		for (int i = 0; i < banks.size(); i++) {

			if (banks.get(i).getParentBankId().equals(parent.getId())) {
				chindList.add(banks.get(i));
			}

		}

		for (int i = 0; i < chindList.size(); i++) {

			// 找到子节点就移除，提高效率
			banks.remove(chindList.get(i));

		}

		return chindList;
	}

	@Override
	public List QueryBankInformation(String bankId) {
		return null;
	}

	@Override
	public int updateserverfw(BankServer bankserver) {
		// TODO Auto-generated method stub
		return bankServerMapper.updateByPrimaryKeySelective(bankserver);
	}

	@Override
	public String updateserverwy(BankServer bankserver) {
		// TODO Auto-generated method stub
		return String.valueOf(bankServerMapper.updateByBankId(bankserver));
	}

	@Override
	public int deleteserverfw(String bankid) {
		// TODO Auto-generated method stub
		return bankServerMapper.deleteByPrimaryKey(bankid);
	}

	@Override
	public String deleteserverwy(String bankid) {
		// TODO Auto-generated method stub
		return String.valueOf(bankServerMapper.deleteByBankId(bankid));
	}

	@Override
	public int updateinterfacefw(InterfaceMgt interfacemat) {
		// TODO Auto-generated method stub
		return InterfaceMgtMapper.updateByPrimaryKeySelective(interfacemat);
	}

	@Override
	public String updateinterfacewy(InterfaceMgt interfacemat) {
		// TODO Auto-generated method stub
		return String.valueOf(InterfaceMgtMapper.updateByBankId(interfacemat));
	}

	@Override
	public String deleteinterfacefw(String bankid) {
		// TODO Auto-generated method stub
		return String.valueOf(InterfaceMgtMapper.deleteByBankId(bankid));
	}

	@Override
	public int deleteinterfacewy(String bankid) {
		// TODO Auto-generated method stub
		return InterfaceMgtMapper.deleteByPrimaryKey(bankid);
	}

	@Override
	public Map<String,Object> QueryBankInformationinterface(Map mapPara) {
		List<InterfaceMgt> listRule = InterfaceMgtMapper.selectByBankid(mapPara);

		Map<String, Object> map = new HashMap<String, Object>();
		int count = InterfaceMgtMapper.selectByBankidCount(mapPara.get("bankId").toString());
		map.put("listInterfaceKey", listRule);
		map.put("count", count);
		return map;
	}

	@Override
	public Map<String,Object> QueryBankInformationserver(Map mapPara) {
		List<BankServer> listServer = bankServerMapper.selectByBankid(mapPara);
		for (int i = 0; i < listServer.size(); i++) {
			if (listServer.get(i).getServerType().equals("0")) {
				BankServer BankServer = new BankServer();
				BankServer = listServer.get(i);
				BankServer.setServerType("银企直联");
				listServer.set(i, BankServer);
			} else if (listServer.get(i).getServerType().equals("1")) {
				BankServer BankServer = new BankServer();
				BankServer = listServer.get(i);
				BankServer.setServerType("网银");
				listServer.set(i, BankServer);
			}
		}
		for (int i = 0; i < listServer.size(); i++) {
			if (listServer.get(i).getStatus().equals("0")) {
				BankServer BankServer = new BankServer();
				BankServer = listServer.get(i);
				BankServer.setStatus("启用");
				listServer.set(i, BankServer);
			} else if (listServer.get(i).getStatus().equals("1")) {
				BankServer BankServer = new BankServer();
				BankServer = listServer.get(i);
				BankServer.setStatus("停用");
				listServer.set(i, BankServer);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		int count = bankServerMapper.selectByBankidServerCount(mapPara.get("bankId").toString());
		map.put("listServerKey", listServer);
		map.put("count", count);
		return map;
	}

	@Override
	public List<BankAcctRule> QueryBankInformationrule(BankAcctRule BankAcctRule) {
		List<BankAcctRule> listRule = BankAcctRuleMapper.selectByBankid(BankAcctRule);
		
		return listRule;
	}

	@Override
	public String GetTopNum(String id) {

		return bankMapper.getTopNum(id);
	}

	@Override
	public int addBank(Bank bank) {

		return bankMapper.AddBank(bank);
	}

	@Override
	public int updateBank(Bank bank) {
		return bankMapper.updateByPrimaryKeySelective(bank);
	}

	@Override
	public int addInterface(InterfaceMgt InterfaceMgt) {
		return InterfaceMgtMapper.insertSelective(InterfaceMgt);
	}

	@Override
	public int addServer(BankServer BankServer) {
		return bankServerMapper.insertSelective(BankServer);
	}

	@Override
	public int addRule(BankAcctRule BankAcctRule) {
		return BankAcctRuleMapper.insertSelective(BankAcctRule);
	}

	@Override
	public int updateRule(BankAcctRule bankAcctRule) {
		return BankAcctRuleMapper.updateByPrimaryKeySelective(bankAcctRule);
	}

	@Override
	public int deleteRule(String ruleid) {

		return BankAcctRuleMapper.deleteByPrimaryKey(ruleid);
	}

	@Override
	public int selectByBankidCount(BankAcctRule BankAcctRule) {
		return BankAcctRuleMapper.selectByBankidCount(BankAcctRule).size();
	}

	@Override
	public List<BankAcctRule> getFatherRule(String bankId,int acctType) {
		
		return BankAcctRuleMapper.getFatherRule(bankId,acctType);
	}
	
	public boolean isServerExist(String serverType, String serverAddress){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("serverType", Integer.parseInt(serverType));
		params.put("serverAddress", serverAddress);
		BankServer bankServer = bankServerMapper.selectSingleByParams(params);
		if (null == bankServer){
			return false;
		}
		return true;
	}	
	
	public boolean isServerNamed(String serverNamebig){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("serverName", serverNamebig);
		BankServer bankServer = bankServerMapper.selectSingleByParams(params);
		if (null == bankServer){
			return false;
		}
		return true;
	}

	@Override
	public List<Bank> getBankByName(String bankName) {
		return bankMapper.selectByBankName(bankName);
	}

}
