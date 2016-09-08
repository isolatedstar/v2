package com.zllh.factoring.common.model.model_extend;

import java.math.BigDecimal;

import com.zllh.factoring.common.model.FacFinancing;

public class FacFinancingGuaranExtendean {

	private String id;
	
    private BigDecimal freezeAmount;//冻结金额

    private BigDecimal paymentsAmount;//给付金额

    private BigDecimal refundAmount;//还款金额

    private BigDecimal lockAmount;//预锁金额
    
    private FacFinancing facFinancing;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getFreezeAmount() {
		return freezeAmount;
	}

	public void setFreezeAmount(BigDecimal freezeAmount) {
		this.freezeAmount = freezeAmount;
	}

	public BigDecimal getPaymentsAmount() {
		return paymentsAmount;
	}

	public void setPaymentsAmount(BigDecimal paymentsAmount) {
		this.paymentsAmount = paymentsAmount;
	}

	public BigDecimal getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}

	public BigDecimal getLockAmount() {
		return lockAmount;
	}

	public void setLockAmount(BigDecimal lockAmount) {
		this.lockAmount = lockAmount;
	}

	public FacFinancing getFacFinancing() {
		return facFinancing;
	}

	public void setFacFinancing(FacFinancing facFinancing) {
		this.facFinancing = facFinancing;
	}
    
    
}
