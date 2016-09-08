package com.zllh.mall.quote.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.zllh.base.controller.BaseController;
import com.zllh.mall.common.model.BusAreaTree;
import com.zllh.mall.quote.service.AreaTreeService;
import com.zllh.utils.common.TreeMapper;


public class AvgController extends BaseController{
	@Autowired
	private AreaTreeService areaTreeService;
	//返回json-treeparentId
	private String[] area = {"areaCode","areaName","parentCode"};
	//通过地域Id获取到地域对象
	public BusAreaTree searchTreeByID(String id){
		
		return areaTreeService.searchTreeByID(id);
	}
	//通过区域代码  获取到包含该地域的所有下级地域,传递到前台
	public void searchAreaTreeBySyscode(Model model ,
			  @RequestParam (value="mmbId",required=false)String areaId) throws Exception{
		String jsonTreeRight = null;
		//查询出所属的地域对象
		BusAreaTree bt = searchTreeByID(areaId);
		//封装条件
		Map<String, Object> params = new HashMap <String, Object>();
		if(!StringUtils.isBlank(bt.getSysCode())){
			params.put("sysCode", bt.getSysCode());
		}
		List<BusAreaTree> areaTrees = areaTreeService.searchAreaBySyscode(params);
		// 查询出的实例节点转换成json字符串,发给前台页面接收
		jsonTreeRight = this.createJsonStr(TreeMapper.getTreeModelList(areaTrees,area));
		// this.writeJson(jsonTreeRight);
		outJson(jsonTreeRight);
	}
	

}
