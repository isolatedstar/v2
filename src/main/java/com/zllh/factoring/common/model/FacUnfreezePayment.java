package com.zllh.factoring.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: FacUnfreezePayment
 * @Description: 解冻实体
 * @author JW
 * @date 2016年2月25日 下午4:23:49
 */
public class FacUnfreezePayment implements Serializable{
    
	private static final long serialVersionUID = 1L;

	/** id */
	private String unfreezeId;

	/** 融资单号 */
    private String financingId;

    /** 解冻金额 */
    private BigDecimal financingAmount;

    /** 解冻日期 */
    private Date financingDate;

    /** 操作人id */
    private String operatorId;

    /** 操作人 */
    private String operator;

    public String getUnfreezeId() {
        return unfreezeId;
    }

    public void setUnfreezeId(String unfreezeId) {
        this.unfreezeId = unfreezeId == null ? null : unfreezeId.trim();
    }

    public String getFinancingId() {
        return financingId;
    }

    public void setFinancingId(String financingId) {
        this.financingId = financingId == null ? null : financingId.trim();
    }

    public BigDecimal getFinancingAmount() {
        return financingAmount;
    }

    public void setFinancingAmount(BigDecimal financingAmount) {
        this.financingAmount = financingAmount;
    }

    public Date getFinancingDate() {
        return financingDate;
    }

    public void setFinancingDate(Date financingDate) {
        this.financingDate = financingDate;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId == null ? null : operatorId.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }
}