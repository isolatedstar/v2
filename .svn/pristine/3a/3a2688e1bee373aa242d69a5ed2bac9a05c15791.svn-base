package com.zllh.payment.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class TaskBean implements Serializable {

	private static final long serialVersionUID = -7512305219268583072L;

	//总流水号
	private String serialID;
	
	// 完整性约束
	public String allPartStatus;

	// 操作员
	public String operator;

	// 流水号
	public String flowId;

	// 查询报文
	public List<MsgBean> queryMsgLst;

	// 转账报文
	public List<MsgBean> actMsgLst;

	// 服务器地址
	public String serverUrl;

	// 进入队列时间
	public Long startTime;

	// 轮芯执行间隔
	public Long intervalTime;

	// 回调商城地址
	public String mallUrl;

	// 回调保理地址
	public String factoringUrl;

	// 划账类型
	public String transferType;

	//解析校验完的报文结构
	public List<Map<String, String>> parsedMsgs;

	//极值计算完的报文结构
	public List<Map<String, String>> calculatedMsgs;

	// 银企直联转账（1：银企，2：网银）
	public String msgFrom;

	//已发送查询报文，从list中逻辑删除标志
	public int queryDeleteFlg;
	
	//已发送转账报文，从list中逻辑删除标志
	public int transDeleteFlg;

	public String resultCode;

	//银行发送状态
	public String bankStatus;

	//发送状态
	public String statusText;

	public String getSerialID() {
        return serialID;
    }

    public void setSerialID(String serialID) {
        this.serialID = serialID == null ? null : serialID.trim();
    }

	public String getAllPartStatus() {
		return allPartStatus;
	}

	public void setAllPartStatus(String allPartStatus) {
		this.allPartStatus = allPartStatus;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public List<MsgBean> getQueryMsgLst() {
		return queryMsgLst;
	}

	public void setQueryMsgLst(List<MsgBean> queryMsgLst) {
		this.queryMsgLst = queryMsgLst;
	}

	public List<MsgBean> getActMsgLst() {
		return actMsgLst;
	}

	public void setActMsgLst(List<MsgBean> actMsgLst) {
		this.actMsgLst = actMsgLst;
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getIntervalTime() {
		return intervalTime;
	}

	public void setIntervalTime(Long intervalTime) {
		this.intervalTime = intervalTime;
	}

	public String getTransferType() {
		return transferType;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}

	public String getMsgFrom() {
		return msgFrom;
	}

	public void setMsgFrom(String msgFrom) {
		this.msgFrom = msgFrom;
	}

	public List<Map<String, String>> getParsedMsgs() {
		return parsedMsgs;
	}

	public void setParsedMsgs(List<Map<String, String>> parsedMsgs) {
		this.parsedMsgs = parsedMsgs;
	}

	public List<Map<String, String>> getCalculatedMsgs() {
		return calculatedMsgs;
	}

	public void setCalculatedMsgs(List<Map<String, String>> calculatedMsgs) {
		this.calculatedMsgs = calculatedMsgs;
	}

	public String getMallUrl() {
		return mallUrl;
	}

	public void setMallUrl(String mallUrl) {
		this.mallUrl = mallUrl;
	}

	public String getFactoringUrl() {
		return factoringUrl;
	}

	public void setFactoringUrl(String factoringUrl) {
		this.factoringUrl = factoringUrl;
	}

	public int getQueryDeleteFlg() {
		return queryDeleteFlg;
	}

	public void setQueryDeleteFlg(int queryDeleteFlg) {
		this.queryDeleteFlg = queryDeleteFlg;
	}

	public int getTransDeleteFlg() {
		return transDeleteFlg;
	}

	public void setTransDeleteFlg(int transDeleteFlg) {
		this.transDeleteFlg = transDeleteFlg;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getBankStatus() {
		return bankStatus;
	}

	public void setBankStatus(String bankStatus) {
		this.bankStatus = bankStatus;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

}
