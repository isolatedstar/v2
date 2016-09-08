package com.zllh.mall.common.model;

import java.util.List;

public class BusAreaTree {
    private String id;

    private String areaCode;

    private String areaName;

    private String parentCode;

    private String sysCode;
    
    //地域树输出字段
    private String text;

    // 显示的节点文本
 	private List<BusAreaTree> nodes;
 	
    public List<BusAreaTree> getNodes() {
		return nodes;
	}

	public void setNodes(List<BusAreaTree> nodes) {
		this.nodes = nodes;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}


	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode == null ? null : sysCode.trim();
    }
}