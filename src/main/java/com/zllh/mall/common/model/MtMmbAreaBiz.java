package com.zllh.mall.common.model;

public class MtMmbAreaBiz {
	//主键id
    private String id;
    //会员id
    private String mmbId;
    //地域id
    private String areaId;
    //业务类型
    private Integer bizType;
    //扩展属性，接收字符串信息
    private String types;
    //地域类--扩展属性
    private BusAreaTree busAreaTree;
    
    public BusAreaTree getBusAreaTree() {
		return busAreaTree;
	}

	public void setBusAreaTree(BusAreaTree busAreaTree) {
		this.busAreaTree = busAreaTree;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMmbId() {
        return mmbId;
    }

    public void setMmbId(String mmbId) {
        this.mmbId = mmbId == null ? null : mmbId.trim();
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    public Integer getBizType() {
        return bizType;
    }

    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }
}