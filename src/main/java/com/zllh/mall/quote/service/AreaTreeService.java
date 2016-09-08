package com.zllh.mall.quote.service;

import java.util.List;
import java.util.Map;

import com.zllh.mall.common.model.BusAreaTree;



public interface AreaTreeService {
	//通过地域Id获取到地域对象
	public BusAreaTree searchTreeByID(String id);
	//通过区域代码  获取到包含该地域的所有下级地域
	public List<BusAreaTree> searchAreaBySyscode(Map<String, Object> params);
	//查询出中国以及下面的所有省份
	public List<BusAreaTree> searchChina();
	//通过地域Id获取该地域的省级Id
	public BusAreaTree  getTree(String id);
	//通过地域Id查询出所属的省份
	public BusAreaTree  getChild (String id);
}
