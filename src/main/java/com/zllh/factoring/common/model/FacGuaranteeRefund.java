package com.zllh.factoring.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: FacGuaranteeRefund
 * @Description: 担保还款实体
 * @author JW
 * @date 2016年2月25日 下午4:18:50
 */
public class FacGuaranteeRefund implements Serializable{
    
	private static final long serialVersionUID = 1L;

	/** id */
	private String guaranteeRefundId;

	/** 还款id */
    private String refundId;

    /** 担保单号 */
    private String guaranteeId;

    /** 担保还款金额 */
    private BigDecimal guaranteeRefundAmount;

    /** 还款日期 */
    private Date refundDate;

    /** 还本金额 */
    private BigDecimal refundPrincipalTotal;

    /** 还息金额 */
    private BigDecimal refundInterestTotal;

    /** 还款余额 */
    private BigDecimal refundBalance;
    
    /** 滞纳金 */
    private BigDecimal overdueFine;

    public String getGuaranteeRefundId() {
        return guaranteeRefundId;
    }

    public void setGuaranteeRefundId(String guaranteeRefundId) {
        this.guaranteeRefundId = guaranteeRefundId == null ? null : guaranteeRefundId.trim();
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId == null ? null : refundId.trim();
    }

    public String getGuaranteeId() {
        return guaranteeId;
    }

    public void setGuaranteeId(String guaranteeId) {
        this.guaranteeId = guaranteeId == null ? null : guaranteeId.trim();
    }

    public BigDecimal getGuaranteeRefundAmount() {
        return guaranteeRefundAmount;
    }

    public void setGuaranteeRefundAmount(BigDecimal guaranteeRefundAmount) {
        this.guaranteeRefundAmount = guaranteeRefundAmount;
    }

    public Date getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(Date refundDate) {
        this.refundDate = refundDate;
    }

    public BigDecimal getRefundPrincipalTotal() {
        return refundPrincipalTotal;
    }

    public void setRefundPrincipalTotal(BigDecimal refundPrincipalTotal) {
        this.refundPrincipalTotal = refundPrincipalTotal;
    }

    public BigDecimal getRefundInterestTotal() {
        return refundInterestTotal;
    }

    public void setRefundInterestTotal(BigDecimal refundInterestTotal) {
        this.refundInterestTotal = refundInterestTotal;
    }

    public BigDecimal getRefundBalance() {
        return refundBalance;
    }

    public void setRefundBalance(BigDecimal refundBalance) {
        this.refundBalance = refundBalance;
    }

	public BigDecimal getOverdueFine() {
		return overdueFine;
	}

	public void setOverdueFine(BigDecimal overdueFine) {
		this.overdueFine = overdueFine;
	}
}