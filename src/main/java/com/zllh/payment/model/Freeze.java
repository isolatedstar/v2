package com.zllh.payment.model;

import java.math.BigDecimal;

public class Freeze {
    private String bankAcct;

    private BigDecimal lockAmt;

    private BigDecimal balance;

    public String getBankAcct() {
        return bankAcct;
    }

    public void setBankAcct(String bankAcct) {
        this.bankAcct = bankAcct == null ? null : bankAcct.trim();
    }

    public BigDecimal getLockAmt() {
        return lockAmt;
    }

    public void setLockAmt(BigDecimal lockAmt) {
        this.lockAmt = lockAmt;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}