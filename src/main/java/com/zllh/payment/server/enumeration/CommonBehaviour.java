package com.zllh.payment.server.enumeration;

/**
 * 不同业务对应转账类型 枚举类
 * @author Xpress
 *
 */
public enum CommonBehaviour {

	CREATEQUERYXML("getCreditQuota","查询额度","createQueryXml",""),
	CREATETRANSFERXML("getCreditQuota","查询额度","createTransferXml",""),
	CXED("getCreditQuota","查询额度","",""), 
//	RZSQ("applyFinancing","融资申请","dlsubtrn","转账"), 
//	RZSP("approvalFinancing","融资审批","dlsubtrn","转账"), 
//	DJZF("PayFreeze","冻结支付","dlsubtrn","转账"), 
//	RZDB("assure","融资担保","dlsubtrn","转账"), 
	
	RZSQ("applyFinancing","融资申请","dlmudtrn","强制转账"),
	RZSP("approvalFinancing","融资审批","dlmudtrn","强制转账"), 
	DJZF("PayFreeze","冻结支付","dlmudtrn","强制转账"), 
	RZDB("assure","融资担保","dlmudtrn","强制转账"), 
	JDZF("unfreeze","解冻支付","dlmudtrn","强制转账"), 
	JDZFC("unfreeze","解冻支付","dlfcsout","强制出金"), 
//	RZHK("payBack","还款","dlsubtrn","转账"),
	RZHK("payBack","还款","dlmudtrn","强制转账"),
	RZHKM("payBack","还款","dlmudtrn","强制转账"),
	RZJK("payBack","结余款","dlfcsout","强制出金"),
	PTJL("process2awardMoney","奖励","dlsubtrn","转账"),
	PTHZ("process2transferMoney","划转","dlfcsout","强制出金"); 
	private String value; //类型对应的保理方法
	private String name; //类型对应的保理方法名称
	private String yqzlValue; //类型对应的银企直联方法
	private String yqzlName; //类型对应的银企直联方法名称
	
	
	private CommonBehaviour(String value,String name,String yqzlValue,String yqzlName) {
		this.value = value;
		this.name = name;
		this.yqzlValue = yqzlValue;
		this.yqzlName = yqzlName;
	}
	
	/**
	 * 根据枚举key获得应当执行的保理方法
	 * @return
	 */
	public String getMethodInfo() {
		return this.value;
	}
	
	/**
	 * 根据枚举key获得应当执行的保理方法名称
	 * @return
	 */
	public String getMethodName() {
		return this.name;
	}
	
	/**
	 * 根据枚举key获得应当执行的银企直联方法
	 * @return
	 */
	public String getYQZLMethodInfo() {
		return this.yqzlValue;
	}
	
	/**
	 * 根据枚举key获得应当执行的银企直联方法名称
	 * @return
	 */
	public String getYQZLMethodName() {
		return this.yqzlName;
	}
	
	public static String[][] getBLAll(){
		CommonBehaviour []aa= CommonBehaviour.values();
		String[][] vals = new String[aa.length][2];
		int i = 0;
		for(CommonBehaviour c : aa){
			vals[i][0] = c.toString();
			vals[i][1] = c.name;
			i++;
		}
		return vals;
	}
	
	public static String[][] getYQZLAll(){
		CommonBehaviour []aa= CommonBehaviour.values();
		String[][] vals = new String[aa.length][2];
		int i = 0;
		for(CommonBehaviour c : aa){
			vals[i][0] = c.toString();
			vals[i][1] = c.yqzlName;
			i++;
		}
		return vals;
	}
	
	public static void main(String[] args) {
		String[][] list = CommonBehaviour.getYQZLAll();
		for(int i=0;i<list.length;i++)
			System.out.println(list[i][0]+" ： "+list[i][1]);
	}

}
