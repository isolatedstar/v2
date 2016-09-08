package com.zllh.utils.common;
/**
 * 树模型构造类
 * @author luobo
 *
 */
public class TreeModel {

	
	private static final String []treeModelParams = {"id","name","pId"} ;
	
	private String id; // 节点id
	private String name;//节点上的名字
	private String pId; // 父节点Id
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPId() {
		return pId;
	}

	public void setPId(String pId) {
		this.pId = pId;
	}
	
	public static String[] getTreeModelParams() {
		return treeModelParams;
	}

}
