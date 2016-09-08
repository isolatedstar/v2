package com.zllh.mall.common.model;

import java.util.List;

public class MtBaseDiv {
    private String id;

    private String divName;

    private String remark;

    private String baseId;

    private Integer divType;

    private String divCode;

    private String parentCode;

    private String sysCode;
    // 显示的节点文本
 	private String text;
 	// 显示的节点文本
 	private List<MtBaseDiv> nodes;
 	// 节点状态，'open' 或 'closed'。
 	private String state;
 	private String parentId;
 	
 	//获取节点Id
 	private String navigateUrl;
 	
 	
 	
    public String getNavigateUrl() {
		return navigateUrl;
	}

	public void setNavigateUrl(String navigateUrl) {
		this.navigateUrl = navigateUrl;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<MtBaseDiv> getNodes() {
		return nodes;
	}

	public void setNodes(List<MtBaseDiv> nodes) {
		this.nodes = nodes;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDivName() {
        return divName;
    }

    public void setDivName(String divName) {
        this.divName = divName == null ? null : divName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getBaseId() {
        return baseId;
    }

    public void setBaseId(String baseId) {
        this.baseId = baseId == null ? null : baseId.trim();
    }

    public Integer getDivType() {
        return divType;
    }

    public void setDivType(Integer divType) {
        this.divType = divType;
    }

    public String getDivCode() {
        return divCode;
    }

    public void setDivCode(String divCode) {
        this.divCode = divCode == null ? null : divCode.trim();
    }

    public String getparentCode() {
        return parentCode;
    }

    public void setparentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode == null ? null : sysCode.trim();
    }
}