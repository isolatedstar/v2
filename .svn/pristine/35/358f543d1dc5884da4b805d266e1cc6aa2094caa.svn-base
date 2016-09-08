package com.zllh.mall.common.enums;

public enum StatusSignEnum {

    //0.双方未签、1.付款方已签、2.收款方已签、3.双方已签
	/** 双方未签 */
	NOSIGN(1, "双方未签"),

	/** 付款方已签 */
	PAYSIGN(2, "付款方已签"),

	/** 收款方已签 */
	GETSIGN(3, "收款方已签"),
	
	/** 双方已签 */
	SIGN(4, "双方已签");

	private String text;

	private Integer value;

	private StatusSignEnum(Integer value, String text) {
		this.text = text;
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public Integer getValue() {
		return value;
	}
	
	public static String getText(Integer value){
	    
	    for(StatusSignEnum enu : StatusSignEnum.values()){
	        if(enu.getValue().equals(value)){
	            return enu.getText();
	        }
	    }
	    
	    return null;
	}
}
