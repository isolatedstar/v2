
package com.zllh.factoring.common.model.message.financingrefund;

import javax.xml.bind.annotation.XmlElement;

import com.zllh.factoring.common.model.message.common.ForceTransferAction;
import com.zllh.factoring.common.model.message.common.OutAction;

/**
 * @ClassName: MsgList
 * @Description:融资还款转账支付报文
 * @author JW
 * @date 2016年3月14日 下午3:06:26
 */
public class MsgList {

	private String flowID;

	private ForceTransferAction forceTransferAction;
	
	private OutAction outAction;
	
	@XmlElement(name="action")
	public ForceTransferAction getForceTransferAction() {
		return forceTransferAction;
	}

	public void setForceTransferAction(ForceTransferAction forceTransferAction) {
		this.forceTransferAction = forceTransferAction;
	}

	@XmlElement(name="action")
	public OutAction getOutAction() {
		return outAction;
	}

	public void setOutAction(OutAction outAction) {
		this.outAction = outAction;
	}

	public String getFlowID() {
		return flowID;
	}

	public void setFlowID(String flowID) {
		this.flowID = flowID;
	}

	public MsgList(String flowID, ForceTransferAction forceTransferAction,
			OutAction outAction) {
		super();
		this.flowID = flowID;
		this.forceTransferAction = forceTransferAction;
		this.outAction = outAction;
	}

	public MsgList(){
		super();
	}
}
