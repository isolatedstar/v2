package com.zllh.payment.model;

import java.util.Date;

public class ReceiveMsgRecord {
    private String serialID;

    private String msgSender;

    private String msgRecevier;

    //(1.刚接收（保存时默认值）。2.接口类找不到。3.服务器找不到。4.报文生成错误。5.银行找不到。6.已生成报文。7.已发送。 8.已驳回。  9.处理成功。10.调整成功。
    private Byte status;

    private Date msgSenderTime;

    private Byte lockFlag;

    private String msgContext;

    public String getSerialID() {
        return serialID;
    }

    public void setSerialID(String serialID) {
        this.serialID = serialID == null ? null : serialID.trim();
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