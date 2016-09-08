package com.zllh.payment.front.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.mall.common.dao.MtMemberMapper;
import com.zllh.mall.common.model.MtMember;
import com.zllh.payment.front.dao.AcctMgtMapper;
import com.zllh.payment.front.dao.FreezeMapper;
import com.zllh.payment.front.service.AcctMgtService;
import com.zllh.payment.model.AcctMgt;
import com.zllh.payment.model.Freeze;

@Service
public class AcctMgtServiceImpl implements AcctMgtService {

	@Autowired
	private AcctMgtMapper acctMgtMapper;
	
	@Autowired
	private FreezeMapper freezeMapper;

	@Autowired
	private MtMemberMapper mtMemberMapper;

	@Override
	public AcctMgt queryAccountInfoByNo(String accountNo) {
		// 根据账号查询账户相关信息
		//return acctMgtMapper.selectByPrimaryKey(accountNo);
		List<AcctMgt> acct = acctMgtMapper.selectAcctByBankAcct(accountNo);
		if (null != acct && acct.size() > 0){
			return acct.get(0);
		}
		return null;
	}

	@Override
	public List<AcctMgt> queryAccountInfoByCompanyName(String companyName) {
		List<AcctMgt> tempResult = acctMgtMapper.queryAccountInfoByCompanyName(companyName);
		for (int i = 0; i < tempResult.size(); i++) {

		}
		return tempResult;
	}

	@Override
	public List<AcctMgt> getAcctByCon(AcctMgt acctMgt, int page, int rows) {
		// TODO Auto-generated method stub
		acctMgt.setPage((page - 1) * rows);
		acctMgt.setRows(rows);
		List<AcctMgt> accts = acctMgtMapper.selectAcctByCon(acctMgt);

		return accts;
	}

	public int getAcctCount(AcctMgt acctMgt) {
		// TODO Auto-generated method stub
		int acctCount = acctMgtMapper.selectAcctCount(acctMgt);

		return acctCount;
	}
	
	public int getAcctMsgCount(AcctMgt acctMgt){
		return acctMgtMapper.selectAcctMsgCount(acctMgt);
	}

	@Override
	public List<AcctMgt> getAcctByAcctType(int acctType,String bankId) {
		// TODO Auto-generated method stub
		List<AcctMgt> accts = acctMgtMapper.selectAcctByAcctType(acctType,bankId);

		return accts;
	}

	@Override
	public List<AcctMgt> queryAccountListByParams(HttpServletRequest request) {

		// 获取公司列表查询条件
		String acctName = request.getParameter("acctName");
		String bankId = request.getParameter("bankId");
		Map<String, Object> mapdetails = new HashMap<String, Object>();
		mapdetails.put("acctName", acctName);
		mapdetails.put("bankId", bankId);
		List<AcctMgt> accDetails = null;
		accDetails = acctMgtMapper.queryAccountListByParams(mapdetails);
		return accDetails;
	}

	@Override
	public boolean acctIsExist(String bankAcct) {
		// TODO Auto-generated method stub
		List<AcctMgt> acct = acctMgtMapper.selectAcctByBankAcct(bankAcct);
		if (acct.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int addAcct(AcctMgt acctMgt) {
		// TODO Auto-generated method stub
		acctMgtMapper.insertSelective(acctMgt);
		Freeze f = new Freeze();
		f.setBankAcct(acctMgt.getBankAcct());
		f.setBalance(new BigDecimal(0.0));
		f.setLockAmt(new BigDecimal(0.0));
		
		return freezeMapper.insertSelective(f);
	}
	
	@Override
	public int editAcct(AcctMgt acctMgt) {
		// TODO Auto-generated method stub
		return acctMgtMapper.updateByPrimaryKeySelective(acctMgt);
	}
	
	@Override
	public int deleteAcct(AcctMgt acctMgt) {
		// TODO Auto-generated method stub
		return acctMgtMapper.deleteByPrimaryKey(acctMgt.getBankAcct());
	}
	
	public AcctMgt findAcctMgt(String acctName, String masterAcct){
		AcctMgt acctMgt = null;
		if (StringUtils.isBlank(acctName) || StringUtils.isBlank(masterAcct)){
			return acctMgt;
		}
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("acctName", acctName);
		param.put("masterAcct", masterAcct);
		param.put("status", 1);
		List<AcctMgt> acctMgts = acctMgtMapper.queryAccountListByParams(param);
		if (null != acctMgts && acctMgts.size() > 0 ){
			acctMgt = acctMgts.get(0);
		}
		return acctMgt;
	}
	 
	public List<AcctMgt> findListByOrgGroupId( String orgGroupId ){
		List<AcctMgt> acctMgts = null;
		if (StringUtils.isBlank(orgGroupId)){
			return acctMgts;
		}
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("orgGroupId", orgGroupId);
		param.put("status", 1);
		acctMgts = acctMgtMapper.queryAccountListByParams(param);
		return acctMgts;
	}
	
	public String findMasterAcctByBanckAcct(String bankAcct){
		List<AcctMgt> acctMgts = null;
		if (StringUtils.isBlank(bankAcct)){
			return "";
		}
		String masterAcct = "";
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("bankAcct", bankAcct);
		param.put("status", 1);
		acctMgts = acctMgtMapper.queryAccountListByParams(param);
		if (null != acctMgts && acctMgts.size() > 0){
			AcctMgt acctMgt = acctMgts.get(0);
			masterAcct =  acctMgt.getMasterAcct() == null ? "" : acctMgt.getMasterAcct();
		}
		return masterAcct;
	}

	@Override
	public List<MtMember> quetyMmbByOrgName(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mtMemberMapper.quetyMmbByOrgName(map);
	}

}
