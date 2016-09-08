
package com.zllh.factoring.common.fac_enum;

/**
 * @ClassName: RefundEnum
 * @Description: 还款状态
 * @author JW
 * @date 2016年5月5日 上午9:41:15
 */
public enum RefundEnum {

	//还款单状态(1-待结款，2-已结款)
	WAITING_BALANCE(1, "待结款"),
	BALANCE(2, "已结款");
	
	private String text;
	private Integer value;
	
	private RefundEnum(Integer value, String text){
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
