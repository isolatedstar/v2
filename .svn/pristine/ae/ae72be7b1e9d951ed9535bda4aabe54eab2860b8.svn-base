package com.zllh.factoring.common.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName: FacFinancingGuarantee
 * @Description: 融资和结款单的关联实体
 * @author JW
 * @date 2016年2月25日 下午4:14:31
 */
public class FacFinancingGuarantee implements Serializable{
    
	private static final long serialVersionUID = 1L;

	/** id */
	protected String id;

	/** 融资单号 */
	protected String financingId;

	/** 担保单号 */
	protected String guaranteeId;

	/** 冻结金额 */
	protected BigDecimal freezeAmount;

	/** 给付金额 */
	protected BigDecimal paymentsAmount;

	/** 还款金额 */
	protected BigDecimal refundAmount;

	/** 预锁金额 */
	protected BigDecimal lockAmount;

	/** 剩余冻结金额 */
	protected BigDecimal surplusFreezeAmount;

	/** 剩余给付金额 */
	protected BigDecimal surplusPaymentsAmount;

	/** 截止当天利息*/
	private BigDecimal interest;
	
	/** 状态 */
	protected Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFinancingId() {
        return financingId;
    }

    public void setFinancingId(String financingId) {
        this.financingId = financingId == null ? null : financingId.trim();
    }

    public String getGuaranteeId() {
        return guaranteeId;
    }

    public void setGuaranteeId(String guaranteeId) {
        this.guaranteeId = guaranteeId == null ? null : guaranteeId.trim();
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

    public BigDecimal getSurplusFreezeAmount() {
        return surplusFreezeAmount;
    }

    public void setSurplusFreezeAmount(BigDecimal surplusFreezeAmount) {
        this.surplusFreezeAmount = surplusFreezeAmount;
    }

    public BigDecimal getSurplusPaymentsAmount() {
        return surplusPaymentsAmount;
    }

    public void setSurplusPaymentsAmount(BigDecimal surplusPaymentsAmount) {
        this.surplusPaymentsAmount = surplusPaymentsAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}
}