package com.zllh.payment.model;
import java.util.Date;

public class BankCallbackMsg {
    private String flowId;

    private String msgSender;

    private String msgRecevier;

    private Byte status;

    private Date msgSenderTime;

    private Byte lockFlag;

    private String msgContext;

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId == null ? null : flowId.trim();
    }

    public String getMsgSender() {
        return msgSender;
    }

    public void setMsgSender(String msgSender) {
        this.msgSender = msgSender == null ? null : msgSender.trim();
    }

    public String getMsgRecevier() {
        return msgRecevier;
    }

    public void setMsgRecevier(String msgRecevier) {
        this.msgRecevier = msgRecevier == null ? null : msgRecevier.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getMsgSenderTime() {
        return msgSenderTime;
    }

    public void setMsgSenderTime(Date msgSenderTime) {
        this.msgSenderTime = msgSenderTime;
    }

    public Byte getLockFlag() {
        return lockFlag;
    }

    public void setLockFlag(Byte lockFlag) {
        this.lockFlag = lockFlag;
    }

    public String getMsgContext() {
        return msgContext;
    }

    public void setMsgContext(String msgContext) {
        this.msgContext = msgContext == null ? null : msgContext.trim();
    }
}