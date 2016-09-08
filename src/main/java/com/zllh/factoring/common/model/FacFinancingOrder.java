package com.zllh.factoring.common.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName: FacFinancingOrder
 * @Description: 融资单和订单表
 * @author JW
 * @date 2016年2月25日 下午4:14:49
 */
public class FacFinancingOrder implements Serializable{
    
	private static final long serialVersionUID = 1L;

	/** id */
	private String id;

	/** 融资单号 */
    private String financingId;

    /** 订单号 */
    private String orderId;

    /** 订单行号 */
    private String orderLineId;

    /** 融资金额 */
    private BigDecimal financingAmount;

    /** 自付金额 */
    private BigDecimal oneselfPayAmount;

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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getOrderLineId() {
        return orderLineId;
    }

    public void setOrderLineId(String orderLineId) {
        this.orderLineId = orderLineId == null ? null : orderLineId.trim();
    }

    public BigDecimal getFinancingAmount() {
        return financingAmount;
    }

    public void setFinancingAmount(BigDecimal financingAmount) {
        this.financingAmount = financingAmount;
    }

    public BigDecimal getOneselfPayAmount() {
        return oneselfPayAmount;
    }

    public void setOneselfPayAmount(BigDecimal oneselfPayAmount) {
        this.oneselfPayAmount = oneselfPayAmount;
    }
}