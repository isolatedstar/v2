package com.zllh.mall.common.model;

import java.util.Date;

public class MtGetgoods {
	private String id;

    private String ordertitleId;

    private String orderId;

    private String sendgoodsId;

    private String goodsId;

    private Double num;

    private String receiverId;

    private String receiverName;

    private String userId;

    private String userName;

    private Date createTime;

    private String eventId;

    private Date getgoodsTime;

    private Integer status;

    private Integer receiveDirct;

    private String receiveAddressId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrdertitleId() {
        return ordertitleId;
    }

    public void setOrdertitleId(String ordertitleId) {
        this.ordertitleId = ordertitleId == null ? null : ordertitleId.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getSendgoodsId() {
        return sendgoodsId;
    }

    public void setSendgoodsId(String sendgoodsId) {
        this.sendgoodsId = sendgoodsId == null ? null : sendgoodsId.trim();
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    public Double getNum() {
        return num;
    }

    public void setNum(Double num) {
        this.num = num;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId == null ? null : receiverId.trim();
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId == null ? null : eventId.trim();
    }

    public Date getGetgoodsTime() {
        return getgoodsTime;
    }

    public void setGetgoodsTime(Date getgoodsTime) {
        this.getgoodsTime = getgoodsTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getReceiveDirct() {
        return receiveDirct;
    }

    public void setReceiveDirct(Integer receiveDirct) {
        this.receiveDirct = receiveDirct;
    }

    public String getReceiveAddressId() {
        return receiveAddressId;
    }

    public void setReceiveAddressId(String receiveAddressId) {
        this.receiveAddressId = receiveAddressId == null ? null : receiveAddressId.trim();
    }
}