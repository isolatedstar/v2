package com.zllh.mall.common.model;

import java.util.Date;

public class MtOrderEventLogs {
    private String id;

    private String orederId;

    private Date eventTime;

    private String eventDescribe;

    private Integer eventType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrederId() {
        return orederId;
    }

    public void setOrederId(String orederId) {
        this.orederId = orederId == null ? null : orederId.trim();
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventDescribe() {
        return eventDescribe;
    }

    public void setEventDescribe(String eventDescribe) {
        this.eventDescribe = eventDescribe == null ? null : eventDescribe.trim();
    }

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }
}