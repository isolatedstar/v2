package com.zllh.common.enums;

public enum BillsType {

	/** 订单 */
	ORDER(10000000, "订单"),

	/** 报价单 */
	QUOTATION(30000000, "出金"),

	/** 结款单 */
	JIEKUAN(40000000, "结款单"),
	
	/** 其它单 */
	OTHER(80000000, "其它单"),

	/** 融资单 */
	FINANCING(50000000, "融资单");

	private String text;

	private Integer value;

	private BillsType(Integer value, String text) {
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
