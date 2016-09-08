
package com.zllh.factoring.common.model.message.common;

/**
 * @ClassName: Action
 * @Description: 强制转账请求报文
 * @author JW
 * @date 2016年3月14日 下午2:11:53
 */
public class ForceTransferAction {

	/**  */
	private String code;
	
	/** 登录名 */
	private String userName;
	
	/** 客户流水号 */
	private String clientID;
	
	/** 主体账号 */
	private String accountNo;
	
	/** 付款账号 */
	private String payAccNo;
	
	/** 转账类型 ，BF：转账；BG：解冻；BH：解冻支付；BR：冻结；BS：支付冻结 */
	private String tranType;
	
	/** 收款账号，当转账类型为“冻结”时可空，其他类型必输 */
	private String recvAccNo;
	
	/** 收款账户名称 ，当转账类型为“冻结”时可空，其他类型必输 */
	private String recvAccNm;
	
	/** 交易金额  */
	private String tranAmt;
	
	/** 前置条件的账号 */
	private String AccNoCon;
	
	/** 前置条件账号余额不小于 */
	private String AccAmtCon;
	
	/** 冻结编号，转账类型为“解冻”或“解冻支付”时，必输 */
	private String freezeNo;
	
	/** 摘要 可空 */
	private String memo;
	
	/** 转账时效标识char (1)，0：异步交易；1：同步交易- */
	private int tranFlag;
	
	public ForceTransferAction(String code, String userName, String clientID,
			String accountNo, String payAccNo, String tranType,
			String recvAccNo, String recvAccNm, String tranAmt,
			String accNoCon, String accAmtCon, String freezeNo,
			String memo, int tranFlag) {
		super();
		this.code = code;
		this.userName = userName;
		this.clientID = clientID;
		this.accountNo = accountNo;
		this.payAccNo = payAccNo;
		this.tranType = tranType;
		this.recvAccNo = recvAccNo;
		this.recvAccNm = recvAccNm;
		this.tranAmt = tranAmt;
		AccNoCon = accNoCon;
		AccAmtCon = accAmtCon;
		this.freezeNo = freezeNo;
		this.memo = memo;
		this.tranFlag = tranFlag;
	}
	
	public ForceTransferAction(){
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getPayAccNo() {
		return payAccNo;
	}

	public void setPayAccNo(String payAccNo) {
		this.payAccNo = payAccNo;
	}

	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public String getRecvAccNo() {
		return recvAccNo;
	}

	public void setRecvAccNo(String recvAccNo) {
		this.recvAccNo = recvAccNo;
	}

	public String getRecvAccNm() {
		return recvAccNm;
	}

	public void setRecvAccNm(String recvAccNm) {
		this.recvAccNm = recvAccNm;
	}

	public String getTranAmt() {
		return tranAmt;
	}

	public void setTranAmt(String tranAmt) {
		this.tranAmt = tranAmt;
	}

	public String getAccNoCon() {
		return AccNoCon;
	}

	public void setAccNoCon(String accNoCon) {
		AccNoCon = accNoCon;
	}

	public String getAccAmtCon() {
		return AccAmtCon;
	}

	public void setAccAmtCon(String accAmtCon) {
		AccAmtCon = accAmtCon;
	}

	public String getFreezeNo() {
		return freezeNo;
	}

	public void setFreezeNo(String freezeNo) {
		this.freezeNo = freezeNo;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getTranFlag() {
		return tranFlag;
	}

	public void setTranFlag(int tranFlag) {
		this.tranFlag = tranFlag;
	}
}
