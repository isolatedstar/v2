package com.zllh.factoring.common.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName: FacGuaranteeRefundInterest
 * @Description: 担保还款利息实体
 * @author JW
 * @date 2016年2月25日 下午4:19:55
 */
public class FacGuaranteeRefundInterest implements Serializable{
   
	private static final long serialVersionUID = 1L;

	/** id */
	private String id;

	/** 担保还款id */
    private String guaranteeRefundId;

    /** 担保单号 */
    private String guaranteeId;

    /** 融资单id */
    private String financingId;

    /** 本次还息金额 */
    private BigDecimal refundInterest;

    /** 本次还本金额 */
    private BigDecimal refundPrincipal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getGuaranteeRefundId() {
        return guaranteeRefundId;
    }

    public void setGuaranteeRefundId(String guaranteeRefundId) {
        this.guaranteeRefundId = guaranteeRefundId == null ? null : guaranteeRefundId.trim();
    }

    public String getGuaranteeId() {
        return guaranteeId;
    }

    public void setGuaranteeId(String guaranteeId) {
        this.guaranteeId = guaranteeId == null ? null : guaranteeId.trim();
    }

    public String getFinancingId() {
        return financingId;
    }

    public void setFinancingId(String financingId) {
        this.financingId = financingId == null ? null : financingId.trim();
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
}