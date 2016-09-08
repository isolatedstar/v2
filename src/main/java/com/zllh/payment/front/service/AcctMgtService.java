package com.zllh.payment.front.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zllh.mall.common.model.MtMember;
import com.zllh.payment.model.AcctMgt;

public interface AcctMgtService {

	public AcctMgt queryAccountInfoByNo(String accountNo);

	//
	public List<AcctMgt> queryAccountInfoByCompanyName(String companyName);

	// 根据传入条件
	public List<AcctMgt> getAcctByCon(AcctMgt acctMgt, int curRow, int pageSize);
	
	public int getAcctCount(AcctMgt acctMgt);

	// 根据账号类型查找账号
	public List<AcctMgt> getAcctByAcctType(int acctType,String bankId);
	
	public boolean acctIsExist(String bankAcct);
	
	public int addAcct(AcctMgt acctMgt);
	
	public int editAcct(AcctMgt acctMgt);
	
	public int deleteAcct(AcctMgt acctMgt);
	
	public int getAcctMsgCount(AcctMgt acctMgt);

	//根据过滤条件查询待选则的账号列表
	public List<AcctMgt> queryAccountListByParams(HttpServletRequest request);
	
	/**
	 * 根据组织id查询账户记录信息
	 * @param orgGroupId
	 * @return
	 */
	public List<AcctMgt> findListByOrgGroupId( String orgGroupId );

	//支付系统—账户添加画面集团名称获取 add by---wxl
	List<MtMember> quetyMmbByOrgName(Map<String,Object> map);
	
	/**
	 * 根据帐号信息查询帐户主账号
	 * @param bankAcct
	 * @return
	 */
	public String findMasterAcctByBanckAcct(String bankAcct);
	
	/**
	 * 根据账号名称和主账户查询帐户信息
	 * @param acctName
	 * @param masterAcct
	 * @return
	 */
	public AcctMgt findAcctMgt(String acctName, String masterAcct);
}
