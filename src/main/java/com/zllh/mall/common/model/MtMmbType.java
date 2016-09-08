package com.zllh.mall.common.model;

public class MtMmbType {
	//主键id
    private String id;
    //会员id
    private String mmbId;
    //会员类型
    private Integer mmbType;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMmbid() {
		return mmbId;
	}
	public void setMmbid(String mmbid) {
		this.mmbId = mmbid;
	}
	public Integer getMmbtype() {
		return mmbType;
	}
	public void setMmbtype(Integer mmbtype) {
		this.mmbType = mmbtype;
	}

    
}