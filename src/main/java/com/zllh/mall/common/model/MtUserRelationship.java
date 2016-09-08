package com.zllh.mall.common.model;

public class MtUserRelationship {
    private String id;

    //(代理)操作员
    private String busUserId;

    //(被代理)操作员
    private String plaUserId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = (id == null ? null : id.trim());
    }

    public String getBusUserId() {
        return busUserId;
    }

    public void setBusUserId(String busUserId) {
        this.busUserId = (busUserId == null ? null : busUserId.trim());
    }

    public String getPlaUserId() {
        return plaUserId;
    }

    public void setPlaUserId(String plaUserId) {
        this.plaUserId = (plaUserId == null ? null : plaUserId.trim());
    }
}