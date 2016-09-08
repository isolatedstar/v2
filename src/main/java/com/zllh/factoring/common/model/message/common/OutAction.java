
package com.zllh.factoring.common.model.message.common;

/**
 * @ClassName: Action
 * @Description: 出金请求报文
 * @author JW
 * @date 2016年3月14日 下午2:11:53
 */
public class OutAction {

	/** 转账类型标示 */
	private String code;
	
	/** 登录名 */
	private String userName;
	
	/** 客户流水号 */
	private String clientID;
	
	/** 主体账号 */
	private String accountNo;
	
	/** 付款账号 */
	private String payAccNo;
	
	/** 收款账号 */
	private String recvAccNo;
	
	/** 收款账户名称 */
	private String recvAccNm;

	/** 交易金额  */
	private String tranAmt;
	
	/** 标识 */
	private char sameBank;
	
	/** 收款账户开户行支付联行号 */
	private String recvTgfi;
	
	/** 收款账户开户行名 */
	private String recvBankNm;
	
	/** 摘要 可空 */
	private String memo;
	
	/** 预约标志（0：非预约1：预约） */
	private char preFlg;
	
	/** 预约日期（格式：YYYYMMDD 预约时非空） */
	private char preDate;
	
	/** 预约时间（格式：hhmmss 预约时非空，只限100000、120000、140000、160000四个时间点） */
	private char preTime;
	
	public OutAction(String code, String userName, String clientID,
			String accountNo, String payAccNo, String recvAccNo,
			String recvAccNm, String tranAmt, char sameBank, String recvTgfi,
			String recvBankNm, String memo, char preFlg, char preDate,
			char preTime) {
		super();
		this.code = code;
		this.userName = userName;
		this.clientID = clientID;
		this.accountNo = accountNo;
		this.payAccNo = payAccNo;
		this.recvAccNo = recvAccNo;
		this.recvAccNm = recvAccNm;
		this.tranAmt = tranAmt;
		this.sameBank = sameBank;
		this.recvTgfi = recvTgfi;
		this.recvBankNm = recvBankNm;
		this.memo = memo;
		this.preFlg = preFlg;
		this.preDate = preDate;
		this.preTime = preTime;
	}

	public OutAction(){
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

	public char getSameBank() {
		return sameBank;
	}

	public void setSameBank(char sameBank) {
		this.sameBank = sameBank;
	}

	public String getRecvTgfi() {
		return recvTgfi;
	}

	public void setRecvTgfi(String recvTgfi) {
		this.recvTgfi = recvTgfi;
	}

	public String getRecvBankNm() {
		return recvBankNm;
	}

	public void setRecvBankNm(String recvBankNm) {
		this.recvBankNm = recvBankNm;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public char getPreFlg() {
		return preFlg;
	}

	public void setPreFlg(char preFlg) {
		this.preFlg = preFlg;
	}

	public char getPreDate() {
		return preDate;
	}

	public void setPreDate(char preDate) {
		this.preDate = preDate;
	}

	public char getPreTime() {
		return preTime;
	}

	public void setPreTime(char preTime) {
		this.preTime = preTime;
	}

}
