package com.zllh.mall.material.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zllh.base.controller.BaseController;
import com.zllh.common.common.model.model_extend.UserExtendBean;
import com.zllh.mall.common.model.MtMaterialBase;
import com.zllh.mall.common.model.MtMuser;
import com.zllh.mall.material.service.MBaseService;
import com.zllh.utils.base.Utils;
import com.zllh.utils.common.DateUtil;
import com.zllh.utils.common.DictionaryUtil;
import com.zllh.utils.common.UUIDCreater;




@Controller
@RequestMapping("/mbase")
public class MBaseController extends BaseController {
	//资料库
	@Autowired
	private MBaseService mbaseService;
	
	@RequestMapping("/toshowBase")
	public String toshowBase(){
		logger.info("====toshowPageBase====");
		return "mall/mal/mal_show";
	}
	@RequestMapping("/toshowBase1")
	public String toshowBase1(){
		logger.info("====toshowPageBase====");
		return "mall/mal/mal_show1";
	}
	/**
	 * 显示资源库
	 * @param model
	 * @param mmbId  登陆的所属的会员Id
	 * @return  会员的个人资料库与公共资料库
	 */
	@RequestMapping("/showBase")
	@ResponseBody
	public Map<String, Object> showBase(
			Model model,
			@RequestParam(value = "materialName", required = false) String materialName,
			@RequestParam(value = "materialType", required = false) Integer materialType){
		logger.info("===showBase====");
		//公共资料库默认mmbId为0  资料库类型0为公告  1为个人
		List<MtMaterialBase> pub = mbaseService.getPub(DictionaryUtil.MMB_BASE_ID);
		//匹配条件
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(!StringUtils.isBlank(materialName)){
			map.put("materialName", materialName);
		}
		if(materialType!=null){
			map.put("materialType", materialType);
		}
		UserExtendBean user1 = Utils.securityUtil.getUser();
		MtMuser user = user1.getMuser();
		map.put("mmbId", user.getMmbId());
		List<MtMaterialBase> list = mbaseService.showMBase(map);
		Map<String , Object> map1 = new HashMap<String, Object>();
		
		//判断当前登录人的权限
		
		map1.put("pub", pub);
		map1.put("baseList", list);
		return map1;
	}
	//判断是否有上传图片的权限
	@RequestMapping("/addUpload")
	@ResponseBody
	public String addUpload(){
		String view = "upload";
		return view;
	}
	//创建资料库
	@RequestMapping("/addMBase")
	@ResponseBody
	public void addMBase(Model model,
			@RequestParam(value = "materialName1", required = false) String materialName,
			@RequestParam(value = "remark1", required = false) String remark){
		MtMaterialBase add  = new MtMaterialBase();
		UserExtendBean user1 = Utils.securityUtil.getUser();
		MtMuser user = user1.getMuser();
		
		add.setId(UUIDCreater.getUUID());
		add.setMaterialName(materialName);
		//0  公共  1个人
		add.setMaterialType(1);
		add.setMmbId(user.getMmbId());
		add.setCreateDate(DateUtil.getNowDate());
		add.setCreateId(user.getId());
		add.setCreateName(user.getName());
		add.setStatus(0);
		boolean flag = mbaseService.addMBase(add);
		if(flag){
			outJson("0");
		}else{
			outJson("1");
		}
	}
	
	//创建资料库
	@RequestMapping("/addMBaseName")
	@ResponseBody
	public void addMBaseName(Model model,
			@RequestParam(value = "materialName1", required = false) String materialName,
			@RequestParam(value = "baseId", required = false) String baseId){
		MtMaterialBase add  = new MtMaterialBase();
		UserExtendBean user1 = Utils.securityUtil.getUser();
		MtMuser user = user1.getMuser();
		
		add.setId(UUIDCreater.getUUID());
		add.setMaterialName(materialName);
		//0  公共  1个人
		add.setMaterialType(1);
		add.setMmbId(user.getMmbId());
		add.setCreateDate(DateUtil.getNowDate());
		add.setCreateId(user.getId());
		add.setCreateName(user.getName());
		add.setStatus(0);
		boolean flag = mbaseService.addMBase(add);
		if(flag){
			outJson("0");
		}else{
			outJson("1");
		}
	}

}
