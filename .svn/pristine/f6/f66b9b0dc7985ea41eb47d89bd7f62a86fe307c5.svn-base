
package com.zllh.factoring.common.model.message.common;

import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.zllh.utils.base.Utils;

/**
 * @ClassName: Message
 * @Description: 支付报文 强制转账
 * @author JW
 * @date 2016年3月14日 下午3:21:07
 */
@XmlRootElement
public class Message {

	/** all或part 完整性约束 */
	protected String integrity;
	
	/** 操作员 */
	protected String operator;
	
	/** 返回地址 */
	protected CallBack callBack;
	
	/** 流水号 */
	private String serialID;

	public String getSerialID() {
		return serialID;
	}

	public void setSerialID(String serialID) {
		this.serialID = serialID;
	}

	public String getIntegrity() {
		return integrity;
	}

	public void setIntegrity(String integrity) {
		this.integrity = integrity;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Map<String, Object> getCallBack() {
		return Utils.objUtil.beanToMap(callBack);
	}

	public void setCallBack(CallBack callBack) {
		this.callBack = callBack;
	}
	
	public Message(String integrity, String operator, CallBack callBack) {
		super();
		this.integrity = integrity;
		this.operator = operator;
		this.callBack = callBack;
	}

	public Message(){
		super();
	}
}
