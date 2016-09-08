package com.zllh.factoring.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: FacFreezePayment
 * @Description: 冻结支付实体
 * @author JW
 * @date 2016年2月25日 下午4:17:26
 */
public class FacFreezePayment implements Serializable{
    
	private static final long serialVersionUID = 1L;

	/** id */
	private String freezeId;

	/** 融资单号 */
    private String financingId;

    /** 冻结金额 */
    private BigDecimal frozenaMount;

    /** 冻结日期 */
    private Date frozenDate;

    /** 操作人ID */
    private String operatorId;

    /** 操作人 */
    private String operator;

    public String getFreezeId() {
        return freezeId;
    }

    public void setFreezeId(String freezeId) {
        this.freezeId = freezeId == null ? null : freezeId.trim();
    }

    public String getFinancingId() {
        return financingId;
    }

    public void setFinancingId(String financingId) {
        this.financingId = financingId == null ? null : financingId.trim();
    }

    public BigDecimal getFrozenaMount() {
        return frozenaMount;
    }

    public void setFrozenaMount(BigDecimal frozenaMount) {
        this.frozenaMount = frozenaMount;
    }

    public Date getFrozenDate() {
        return frozenDate;
    }

    public void setFrozenDate(Date frozenDate) {
        this.frozenDate = frozenDate;
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