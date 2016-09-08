package com.zllh.factoring.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FacTenderRespond implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/*     **/
    private String id;

    /*     **/
    private BigDecimal expectedServiceCharge;

    /*     **/
    private BigDecimal interestRate;

    /*     **/
    private String treatedHuman;

    /*     **/
    private Integer intentionalStates;

    /*     **/
    private Date operationDate;

    /*     **/
    private String intentionNum;

    /*     **/
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public BigDecimal getExpectedServiceCharge() {
        return expectedServiceCharge;
    }

    public void setExpectedServiceCharge(BigDecimal expectedServiceCharge) {
        this.expectedServiceCharge = expectedServiceCharge;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public String getTreatedHuman() {
        return treatedHuman;
    }

    public void setTreatedHuman(String treatedHuman) {
        this.treatedHuman = treatedHuman == null ? null : treatedHuman.trim();
    }

    public Integer getIntentionalStates() {
        return intentionalStates;
    }

    public void setIntentionalStates(Integer intentionalStates) {
        this.intentionalStates = intentionalStates;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public String getIntentionNum() {
        return intentionNum;
    }

    public void setIntentionNum(String intentionNum) {
        this.intentionNum = intentionNum == null ? null : intentionNum.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}