
package com.zllh.factoring.common.fac_enum;

/**
 * @ClassName: FacEnumDetail
 * @Description: 保理枚举类
 * @author JW
 * @date 2016年1月6日 上午10:44:55
 */
public enum FinancingEnum {

	//融资单状态(1-待认可2-待放款3-已认可4-已拒绝5-已还款6-已关闭)
	WAITING_ACCEPT(1, "待认可"),
	WAITING_LOAN(2, "待放款"),
	ACCEPTED(3, "已认可"),
	REJECTED(4, "已拒绝"),
	FAC_REFUNDED(5, "已还款"),
	CLOSED(6, "已关闭");
	
	private String text;
	private Integer value;
	
	private FinancingEnum(Integer value, String text){
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
