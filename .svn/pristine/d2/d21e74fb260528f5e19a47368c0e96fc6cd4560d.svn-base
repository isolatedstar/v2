package com.zllh.payment.server.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface FactoringMsgService {

	//根据报文流水号判断数据库中是否有相同报文。
	public boolean checkMsgRepeat(String msgFlowNo);
	//校验
	public boolean checkMessage();
	//本地保存报文
	public void saveFactoringSendMsg(String msgXml);
	public void saveTransferStatus();

	//标记报文到数据库([无需处理]等) && 解冻金额
	public String updateFactoringSendMsgStatusAndUnLockAmt(Map<String, Object> maps);
	//调银企直联处理流程
	public void directBankProcess();

	public void queryBalance(String acctNo);

	//查询数据库中余额足够
	public double queryBalanceFromDB(String acctNo);

	//更新余额冻结表,更新报文成功状态
	public String updateBalanceAndStatus(Map<String, Object> map);

	//修改元报文状态(1.银行找不到。2.接口类找不到。3.服务器找不到4.正在处理5.已处理)
	public void updateMsgStatus(String flowId, Byte status);

	public void queryBalanceFromBank(String acctNo);

	/**
	 * 锁定金额
	 * @param map
	 * @return
	 */
	String updateBalanceLockAmt(Map<String, Object> map);
	
	/**
	 * 转账成功，释放锁定金额
	 * @param map
	 * @return
	 */
	String releaseBalanceLockAmt(Map<String, Object> map);
	
	/**
	 * 转账失败，回滚锁定金额
	 * @param map
	 * @return
	 */
	String rollBackBalanceLockAmt(Map<String, Object> map);

	public BigDecimal setBalanceLockAmt(String acctNo);

	public void removeMsgFromList();
	//记录资金划转明细
	public void saveTransferDetail();
	
	//更新资金划转表
	boolean updateTransferStatus(String bankFlowId, int transferStatus);
	
	//查找账号表，找到银行id
	public String findBankByAccountNum(String acct);
	
	//回调报文入库
	public void saveCallBackMsg();
	
	//修改报文状态&保持回调报文
	public void updateMsgStatusSaveBackMsg();
	
	//更新报文状态
	public void updateSendToBankMsgStatus(Map<String, Object> maps);
	
	//解锁全部账户结余
	public void updateUnlockAllAccountBalance(List<Map<String, String>> accountBalance);
	/**
	 * 处理从保理接收的报文
	 * @param data
	 * @return
	 */
	boolean handleFactoringMsg(String data);

}
