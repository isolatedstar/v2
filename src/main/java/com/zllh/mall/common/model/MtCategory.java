package com.zllh.mall.common.model;

import java.util.List;



public class MtCategory {
	
	
	
	// 绑定节点的标识值
	private String id;

	// 显示的节点文本
	private String text;

	// 显示的节点文本
	private List<MtCategory> children;

	// 显示节点的图标
	private String iconCls;

	// 节点是否被选中
	private String checked;

	// 节点状态，'open' 或 'closed'。
	private String state;
	
	
	//底层Id 所有的上级节点的集合（包括自己的Id）
	private String parentIds;
	
	 // 显示的节点文本
 	private List<MtCategory> nodes;
   

    public List<MtCategory> getNodes() {
		return nodes;
	}

	public void setNodes(List<MtCategory> nodes) {
		this.nodes = nodes;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<MtCategory> getChildren() {
		return children;
	}

	public void setChildren(List<MtCategory> children) {
		this.children = children;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	private String name;

    private String typeId;

    private Integer status;

    private String url;

    private String tableName;

    private String parentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }
}