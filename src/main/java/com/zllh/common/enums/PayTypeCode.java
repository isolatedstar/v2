package com.zllh.common.enums;

public enum PayTypeCode {

	/** 入金 */
	ZLFONDIN("ZLFONDIN", "入金"),

	/** 出金 */
	ZLFNDOUT("ZLFNDOUT", "出金"),

	/** 强制转账 */
	ZLMDTFER("ZLMDTFER", "强制转账"),

	/** 附属账户余额查询 */
	ZLSBALQR("ZLSBALQR", "附属账户余额查询"),
	
	/** 交易状态查询 */
	ZLCIDSTT("ZLCIDSTT", "交易状态查询");

	private String text;

	private String value;

	private PayTypeCode(String value, String text) {
		this.text = text;
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public String getValue() {
		return value;
	}
	
	 public static String getText(String value) {
         for (PayTypeCode c : PayTypeCode.values()) {
             if (c.getValue().equals(value)) {
                 return c.text;
             }
         }
         return null;
     }
}
