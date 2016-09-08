package com.zllh.factoring.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: FacFinancingRefund
 * @Description: 融资还款实体
 * @author JW
 * @date 2016年2月25日 下午4:16:16
 */
public class FacFinancingRefund implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id;
	
	/** 还款id */
	private String refundId;

	/** 融资单号 */
    private String financingId;

    /** 还款日期 */
    private Date refundDate;

    /** 本次还息金额 */
    private BigDecimal refundInterest;

    /** 本次还本金额 */
    private BigDecimal refundPrincipal;
    
    /** 滞纳金 */
    private BigDecimal overdueFine;

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId == null ? null : refundId.trim();
    }

    public String getFinancingId() {
        return financingId;
    }

    public void setFinancingId(String financingId) {
        this.financingId = financingId == null ? null : financingId.trim();
    }

    public Date getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(Date refundDate) {
        this.refundDate = refundDate;
    }

    public BigDecimal getRefundInterest() {
        return refundInterest;
    }

    public void setRefundInterest(BigDecimal refundInterest) {
        this.refundInterest = refundInterest;
    }

    public BigDecimal getRefundPrincipal() {
        return refundPrincipal;
    }

    public void setRefundPrincipal(BigDecimal refundPrincipal) {
        this.refundPrincipal = refundPrincipal;
    }

	public BigDecimal getOverdueFine() {
		return overdueFine;
	}

	public void setOverdueFine(BigDecimal overdueFine) {
		this.overdueFine = overdueFine;
	}
}