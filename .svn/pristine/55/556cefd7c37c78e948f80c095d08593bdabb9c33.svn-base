
package com.zllh.factoring.common.fac_enum;

public enum MessageStatus {
	
    //被调用报文状态
	SUCC(1, "执行成功"), ERR(0, "未执行或执行失败"),

	//调用报文状态
	YES(2, "已执行"), NO(3, "未执行");
	
	private String text;
	private Integer value;

	private MessageStatus(Integer value, String text) {
		this.text = text;
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public Integer getValue() {
		return value;
	}
	
}
