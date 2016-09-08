
package com.zllh.factoring.common.model.message.common;

/**
 * @ClassName: CallBack
 * @Description: 支付报文
 * @author JW
 * @date 2016年3月14日 下午3:15:18
 */
public class CallBack {
	
	private String mallUrl;
	
	private String factoringUrl;

	public String getMallUrl() {
		return mallUrl;
	}

	public void setMallUrl(String mallUrl) {
		this.mallUrl = mallUrl;
	}

	public String getFactoringUrl() {
		return factoringUrl;
	}

	public void setFactoringUrl(String factoringUrl) {
		this.factoringUrl = factoringUrl;
	}

	public CallBack(String mallUrl, String factoringUrl) {
		super();
		this.mallUrl = mallUrl;
		this.factoringUrl = factoringUrl;
	}
	
	public CallBack(){
		super();
	}
}
