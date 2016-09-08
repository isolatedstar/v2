
package com.zllh.factoring.common.model.model_extend;

import java.math.BigDecimal;
import java.util.Date;

import com.zllh.factoring.common.model.FacFinancingGuarantee;

//融资担保表和但保表关联表
public class FacGuaBillFinGuaBillExtend{
	
	/** 结款单号 */
    private String guaranteeId;

    /** 结款单金额  */
    private BigDecimal guaranteeAmount;

    /** 已支付金额 */
    private BigDecimal paymentAmount;

    /** 可用金额 */
    private BigDecimal usableAmount;
 
    /** 付款方ID */
    private String payerId;
    
    /** 付款方 */
    private String payerName;

    /** 担保操作员 */
    private String guaranteeName;

    /** 到期时间*/
    private Date expireDate;
    
    private FacFinancingGuarantee facFinancingGuarantee;

	public String getGuaranteeId() {
		return guaranteeId;
	}

	public void setGuaranteeId(String guaranteeId) {
		this.guaranteeId = guaranteeId;
	}

	public BigDecimal getGuaranteeAmount() {
		return guaranteeAmount;
	}

	public void setGuaranteeAmount(BigDecimal guaranteeAmount) {
		this.guaranteeAmount = guaranteeAmount;
	}

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public BigDecimal getUsableAmount() {
		return usableAmount;
	}

	public void setUsableAmount(BigDecimal usableAmount) {
		this.usableAmount = usableAmount;
	}

	public String getPayerId() {
		return payerId;
	}

	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}

	public String getGuaranteeName() {
		return guaranteeName;
	}

	public void setGuaranteeName(String guaranteeName) {
		this.guaranteeName = guaranteeName;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getPayerName() {
		return payerName;
	}

	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}

	public FacFinancingGuarantee getFacFinancingGuarantee() {
		return facFinancingGuarantee;
	}

	public void setFacFinancingGuarantee(FacFinancingGuarantee facFinancingGuarantee) {
		this.facFinancingGuarantee = facFinancingGuarantee;
	}


	public FacGuaBillFinGuaBillExtend(String guaranteeId,
			BigDecimal guaranteeAmount, BigDecimal paymentAmount,
			BigDecimal usableAmount, String payerId, String payerName,
			String guaranteeName, Date expireDate,
			FacFinancingGuarantee facFinancingGuarantee) {
		super();
		this.guaranteeId = guaranteeId;
		this.guaranteeAmount = guaranteeAmount;
		this.paymentAmount = paymentAmount;
		this.usableAmount = usableAmount;
		this.payerId = payerId;
		this.payerName = payerName;
		this.guaranteeName = guaranteeName;
		this.expireDate = expireDate;
		this.facFinancingGuarantee = facFinancingGuarantee;
	}

	public FacGuaBillFinGuaBillExtend() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "FacGuaBillFinGuaBillExtend [guaranteeId=" + guaranteeId
				+ ", guaranteeAmount=" + guaranteeAmount + ", paymentAmount="
				+ paymentAmount + ", usableAmount=" + usableAmount
				+ ", payerId=" + payerId + ", payerName=" + payerName
				+ ", guaranteeName=" + guaranteeName + ", expireDate="
				+ expireDate + ", facFinancingGuarantee="
				+ facFinancingGuarantee + "]";
	}


	
}
