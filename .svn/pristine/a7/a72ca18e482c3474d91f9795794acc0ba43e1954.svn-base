package com.zllh.payment.front.service.impl;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.payment.front.dao.AcctBalanceMapper;
import com.zllh.payment.front.dao.AcctMgtMapper;
import com.zllh.payment.front.service.AcctBalanceService;
import com.zllh.payment.model.AccountBalance;
import com.zllh.payment.model.AccountTable;
import com.zllh.payment.model.AcctMgt;
import com.zllh.payment.model.BankBean;
import com.zllh.payment.model.BankServer;
import com.zllh.payment.server.service.BankServerFactory;
import com.zllh.payment.utils.PostBankMessage;
import com.zllh.payment.utils.Util;

@Service
public class AcctBalanceServiceImpl implements AcctBalanceService {

	@Autowired
	private AcctBalanceMapper acctBalanceMapper;

	@Autowired
	private AcctMgtMapper acctMgtMapper;

	@Autowired
	private BankServerFactory bankServerFactory;
	@Override
	public List SendBalanceRequests(List lists) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccountBalance> queryAccountBalanceByParams(HttpServletRequest request) {
		//1.查询出符合条件的每个账号的转账金额

		List<AccountTable> list = new ArrayList<AccountTable>();
		Map<String, Object> mapdetails = new HashMap<String, Object>();
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		String tableRowString = request.getParameter("tableRows").toString();
		int tableSize = Util.jsonToListMap(tableRowString).size();
		for (int i = 0; i < tableSize; i++ ) {
			Map<String, Object> m = (Map<String, Object>) Util.jsonToListMap(tableRowString).get(i);
			String relationBalance = m.get("relationBalance").toString();
			String outString = m.get("accountOut").toString();
			AccountTable aTable = new AccountTable();
			if("否".equals(relationBalance)){
				aTable.setRelation("0");
				aTable.setAccountOut(outString);
				list.add(aTable);
			} else {
				aTable.setAccountOut(outString);
				aTable.setRelation("1");
				list.add(aTable);
			}
		}
	    HashMap<String, String> map = new HashMap<String, String>();
		//排序
		String sort = request.getParameter("sort");
		//升降
		String order = request.getParameter("order");
		if (sort != null && order != null) {
			//排序设定
			//将画面的排序字段转化为数据库中的排序字段
			map.put("companyName", "OUT_ACCT_COPY_NAME");
		    map.put("bankName", "BANK_NAME");
		    map.put("amtAccount", "AMT_ACCOUNT");
			mapdetails.put("sort", map.get(sort));
			mapdetails.put("order", order);
		} else {

			mapdetails.put("sort", null);
			mapdetails.put("order", null);
		}
		mapdetails.put("list", list);
		//分页参数设定
		mapdetails.put("pageIndex", (page-1)*rows);
		mapdetails.put("pageSize", rows);

		//2 查询明细
		//1.查询账户余额信息（查询视图）
		List<AccountBalance> accountBalances = acctBalanceMapper.queryAccountAmt(mapdetails);

		//2.查询账户的冻结金额信息。（查询冻结表）
		List<AccountBalance> accountBalanceFreeze = acctBalanceMapper.queryAccountFreezeAmt(mapdetails);

		//3.查询账户的银行金额信息（假数据，目前未实现）
		Map<String, String> mapFreezeMap = new HashMap<String, String>();
		for (int i = 0; i < accountBalanceFreeze.size(); i++) {
			mapFreezeMap.put(accountBalanceFreeze.get(i).getAmtAccount(), accountBalanceFreeze.get(i).freezeAmt);
		}
		for (int j = 0; j < accountBalances.size(); j++) {
			//冻结金额
			String freezeTemp = mapFreezeMap.get(accountBalances.get(j).getAmtAccount());
			//double freezeTempInt = Float.parseFloat(freezeTemp);
			BigDecimal freezeBigDecimal = new BigDecimal(freezeTemp);
			//总金额
			String amtTemp = accountBalances.get(j).getAmt();
			//double amtTempInt = Float.parseFloat(amtTemp);
			BigDecimal freezeBigDecimal2 = new BigDecimal(amtTemp);
			//可用金额
			//String useableAmt = (amtTempInt - freezeTempInt) + "";
			String useableAmt = freezeBigDecimal.subtract(freezeBigDecimal2) + "";
			//设定画面显示的平台冻结金额
			accountBalances.get(j).setFreezeAmt(
					mapFreezeMap.get(accountBalances.get(j).getAmtAccount()));
			//设定画面显示的银行冻结金额（假数据）
			accountBalances.get(j).setBankFreezeAmt(
					mapFreezeMap.get(accountBalances.get(j).getAmtAccount()));
			//设定画面显示的银行金额（真数据）
			accountBalances.get(j).setBankAmt(getBankBalance(accountBalances.get(j).getAmtAccount()));
			//设定画面显示的银行可用金额（假数据）
			accountBalances.get(j).setBankUsableAmt(useableAmt);
			//设定画面显示的平台可用金额
			accountBalances.get(j).setUsableAmt(useableAmt);
		}
		return accountBalances;
	}

	@Override
	public void exportBalanceInfo() {
		// TODO Auto-generated method stub

	}

	@Override
	public int queryAccountCount(HttpServletRequest request) {
		//初期化总记录条数
		int count = 0;
		List<AccountTable> list = new ArrayList<AccountTable>();
		Map<String, Object> mapdetails = new HashMap<String, Object>();
		String tableRowString = request.getParameter("tableRows").toString();
		int tableSize = Util.jsonToListMap(tableRowString).size();
		for (int i = 0; i < tableSize; i++ ) {
			Map<String, Object> m = (Map<String, Object>) Util.jsonToListMap(tableRowString).get(i);
			String relationBalance = m.get("relationBalance").toString();
			String outString = m.get("accountOut").toString();
			AccountTable aTable = new AccountTable();
			if("否".equals(relationBalance)){
				aTable.setRelation("0");
				aTable.setAccountOut(outString);
				list.add(aTable);
			} else {
				aTable.setAccountOut(outString);
				aTable.setRelation("1");
				list.add(aTable);
			}
		}
		mapdetails.put("list", list);

		//查询银行转账明细条数。
		count = acctBalanceMapper.queryAccountCount(mapdetails);
		return count;
	}

	//查询银行卡余额报文做成
	public String getBankBalance(String accountList){
		
		//根据银行账号查询出报文参数，用户名，主账户。
		List<AcctMgt> acct = acctMgtMapper.selectAcctByBankAcct(accountList);
		String userName = acct.get(0).getAcctName();
		String subAccNo = acct.get(0).getMasterAcct();
		StringBuffer sbQuery = new StringBuffer();
		//根据银行卡号查出对应的服务器
		//1.找到该银行卡号对应的总行分行支行
		BankBean bkB = bankServerFactory.findBankByAcctRule(accountList);
		String url = "";
		List<BankServer> bkServers = bankServerFactory.findServerInfoByBankId(bkB.getBankZongId(),bkB.getBankFenId(),bkB.getBankZhiId());
		//如果服务器存在
		if(bkServers.size() > 0){
			url = bkServers.get(0).getServerAddress();
		} else 
		//查询报文的内容
		sbQuery.append("<?xml version=\"1.0\" encoding=\"GBK\"?><stream><action>");
		//
		sbQuery.append("DLSBALQR");
		sbQuery.append("</action><userName>");
		//
		sbQuery.append(userName);
		sbQuery.append("</userName><accountNo>");
		//
		sbQuery.append(subAccNo);
		sbQuery.append("</accountNo><subAccNo>");
		//
		sbQuery.append("");
		sbQuery.append("</subAccNo></stream>");
		//向银行查询余额
		String backXml;
		// 查找银行返回报文中的status内容。
		String balanceString = "";
		try {
			backXml = PostBankMessage.post(url, sbQuery.toString());
			String sendStatus = Util.getXmlElementByName(backXml.replace('"', '\"'), "status");
			if ("AAAAAAA".equals(sendStatus)) {
				balanceString = Util.getXmlElementByOneName(backXml.replace('"', '\"'), "SJAMT");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return balanceString;
	}
}
