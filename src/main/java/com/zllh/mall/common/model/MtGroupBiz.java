package com.zllh.mall.common.model;

import java.sql.Date;

/** 
 * @ClassName: MtGroupBiz 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author yangdm
 * @date 2016-3-17 下午1:41:21 
 */
public class MtGroupBiz {
	private String id;
	private String groupId;
	private String rpGroupId;
	private String rpGroupName;
	private int busType;
	private Date createDate;
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public int getBusType() {
		return busType;
	}
	public void setStatus(int busType) {
		this.busType = busType;
	}
	public String getRpGroupId() {
		return rpGroupId;
	}
	public void setRpGroupId(String rpGroupId) {
		this.rpGroupId = rpGroupId;
	}
	public String getRpGroupName() {
		return rpGroupName;
	}
	public void setRpGroupName(String rpGroupName) {
		this.rpGroupName = rpGroupName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
