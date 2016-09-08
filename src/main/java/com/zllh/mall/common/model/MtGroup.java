package com.zllh.mall.common.model;

import java.io.Serializable;

public class MtGroup implements Serializable{
    /** 
	 * @Fields serialVersionUID : TODO
	 */ 
	
	private static final long serialVersionUID = -2057050741565286146L;

	private String id;

    private String groupName;

    private String remark;

    private int groupStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public int getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(int groupStatus) {
        this.groupStatus = groupStatus;
    }
}