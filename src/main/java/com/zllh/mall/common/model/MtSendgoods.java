package com.zllh.mall.common.model;

import java.util.Date;

public class MtSendgoods {
	private String id;

    private String ordertitleId;

    private String orderId;

    private String shipperId;

    private String goodsId;

    private Double num;

    private String shipperName;

    private String userId;

    private String userName;

    private Date createTime;

    private String eventId;

    private Date sendgoodsTime;

    private Integer status;

    private Integer sendDirct;

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

    public String getShipperId() {
        return shipperId;
    }

    public void setShipperId(String shipperId) {
        this.shipperId = shipperId == null ? null : shipperId.trim();
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

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName == null ? null : shipperName.trim();
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

    public Date getSendgoodsTime() {
        return sendgoodsTime;
    }

    public void setSendgoodsTime(Date sendgoodsTime) {
        this.sendgoodsTime = sendgoodsTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSendDirct() {
        return sendDirct;
    }

    public void setSendDirct(Integer sendDirct) {
        this.sendDirct = sendDirct;
    }
}