
package com.zllh.factoring.common.model.message.common;

/**
 * @ClassName: PayMentCallBack
 * @Description: 支付回调报文实体
 * @author JW
 * @date 2016年5月5日 下午3:11:44
 */
public class PayMentCallBack {

	private String factoringUrl;
	
	private String flowId;
	
	private String intervalTime;
	
	private String mallUrl;
	
	private String resultContent;
	
	private String serialID;
	
	private String startTime;
	
	private String status;

	public String getFactoringUrl() {
		return factoringUrl;
	}

	public void setFactoringUrl(String factoringUrl) {
		this.factoringUrl = factoringUrl;
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public String getIntervalTime() {
		return intervalTime;
	}

	public void setIntervalTime(String intervalTime) {
		this.intervalTime = intervalTime;
	}

	public String getMallUrl() {
		return mallUrl;
	}

	public void setMallUrl(String mallUrl) {
		this.mallUrl = mallUrl;
	}

	public String getResultContent() {
		return resultContent;
	}

	public void setResultContent(String resultContent) {
		this.resultContent = resultContent;
	}

	public String getSerialID() {
		return serialID;
	}

	public void setSerialID(String serialID) {
		this.serialID = serialID;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
