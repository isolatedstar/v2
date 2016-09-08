package com.zllh.factoring.common.model;

import java.math.BigDecimal;
import java.util.Date;

public class FacWriteOffAcc {
    private String id;

    private String financingId;

    private String guaranteeId;

    private BigDecimal surplusFreezeAmount;

    private BigDecimal surplusPaymentsAmount;

    private BigDecimal interest;

    private Date creattime;

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

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }
}