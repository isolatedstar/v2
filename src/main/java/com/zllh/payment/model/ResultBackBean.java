package com.zllh.payment.model;

import java.io.Serializable;

public class ResultBackBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2078189978797910915L;
	
	//总流水号
	public String serialID;
	
	// 流水号
	public String flowId;
	// 结果内容
	public String resultContent;

	// 回调商城地址
	public String mallUrl;
	
	// 回调保理地址
	public String factoringUrl;

	// 时间
	public Long startTime;

	// 轮芯执行间隔
	public Long intervalTime;

	//状态码
	public String status;

	public String getSerialID() {
		return serialID;
	}

	public void setSerialID(String serialID) {
		this.serialID = serialID;
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public String getResultContent() {
		return resultContent;
	}

	public void setResultContent(String resultContent) {
		this.resultContent = resultContent;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
