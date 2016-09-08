package com.zllh.mall.common.model;

import java.util.Date;

public class MtGroupRelationship {
    private String id;

    private String groupId;

    private String rpGroupId;

    private Integer busType;
    
    //群组交易类型的扩展字段
    private String busTypeBiz;

    public String getBusTypeBiz() {
		return busTypeBiz;
	}

	public void setBusTypeBiz(String busTypeBiz) {
		this.busTypeBiz = busTypeBiz;
	}

	private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    public String getRpGroupId() {
        return rpGroupId;
    }

    public void setRpGroupId(String rpGroupId) {
        this.rpGroupId = rpGroupId == null ? null : rpGroupId.trim();
    }

    public Integer getBusType() {
        return busType;
    }

    public void setBusType(Integer busType) {
        this.busType = busType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}