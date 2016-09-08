package com.zllh.factoring.common.fac_enum;
/**
 * 担保登记enum
 * 
 * @author dongll
 * @since 2016-01-14上午
 * @version2.0
 */
public enum AssureEnum {
	
//	//待签章
//	WAITING_SIGNATURE(0, "待签章"),
	
	//待登记
	WAITING_REGISTER(1,"待登记"),
	
	//已登记
	REGISTERD(2,"已登记"),
	
	//已驳回
	REFUSE(3,"已驳回"),
	
	//已撤销
	REVOKE(5,"已撤销");

	private String text;
	
	private Integer value;
	
	private AssureEnum(Integer value, String text){
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
