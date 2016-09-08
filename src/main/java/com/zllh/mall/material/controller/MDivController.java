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
import com.zllh.mall.common.model.MtBaseDiv;
import com.zllh.mall.common.model.MtMaterial;
import com.zllh.mall.material.service.MDivService;
import com.zllh.mall.material.service.MaterialService;
import com.zllh.utils.common.TreeMapper;
import com.zllh.utils.common.UUIDCreater;
@Controller
@RequestMapping("/mdiv")
public class MDivController extends BaseController {
	//目录
	@Autowired
	private MDivService mdivService;
	//资源
	@Autowired
	private MaterialService materialService;
	
	
	//返回json-treeparentId
	private String[] params = {"id","parentId","text"};
	/**
	 * 
	 * @param model
	 * @param baseId
	 * @return  资料库目录树状结构  ztree
	 * @throws Exception 
	 */
	@RequestMapping("/showDiv1")
	@ResponseBody
	public String showDiv1(Model model,
			@RequestParam(value = "baseId", required = false) String baseId) throws Exception{
		logger.info("===展示目录====");
		List<MtBaseDiv> list =null;
		list = mdivService.showBaseDiv1(baseId);
		String jsonTreeRight = null;
		jsonTreeRight = this.createJsonStr(TreeMapper.getTreeModelList(list,params));
		return jsonTreeRight;
	}
	
	/**
	 * 
	 * @param model
	 * @param baseId 资料库Id
	 * @return  资料库目录树状结构    bootstrap
	 */
	@RequestMapping("/showDiv")
	@ResponseBody
	public List<MtBaseDiv> showDiv(Model model,
			@RequestParam(value = "baseId", required = false) String baseId){
		logger.info("===展示目录====");
		List<MtBaseDiv> list =null;
		list = mdivService.showBaseDiv(baseId);
		return list;
	}
	@RequestMapping("/addDiv")
	@ResponseBody
	public void addDiv(Model model,
			@RequestParam(value = "divName", required = false) String divName,
			@RequestParam(value = "parentId", required = false) String parentId,
			@RequestParam(value = "baseId", required = false) String baseId){
		
		MtBaseDiv add = new MtBaseDiv();
		add.setId(UUIDCreater.getUUID());
		
		if (StringUtils.isBlank(parentId)) {
			add.setparentCode("0");
		}else{
			add.setparentCode(parentId);
		}
		add.setDivName(divName);
		add.setBaseId(baseId);
		add.setDivType(0);
		boolean flag = mdivService.addMDiv(add);
		if(flag){
			outJson("0");
		}{
			outJson("1");
		}
	}
	@RequestMapping("/updateDiv")
	@ResponseBody
	public void updateDiv(Model model,
			@RequestParam(value = "divName", required = false) String divName,
			
			@RequestParam(value = "divId", required = false) String divId){
		
		MtBaseDiv update = new MtBaseDiv();
		update.setId(divId);
		update.setDivName(divName);
		
		boolean flag = mdivService.updateMDiv(update);
		if(flag){
			outJson("0");
		}else{
			outJson("1");
		}
	}
	@RequestMapping("/delDiv")
	@ResponseBody
	public Map<String, Object> delDiv(Model model,
			@RequestParam(value = "divId", required = false) String divId){
		//查询判断 该目录下是否有资源  (选中的为底层节点)  查询判断该节点下是否有资源
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("divId", divId);
		List<MtMaterial> list = materialService.showPic(params);
		Map<String, Object>  map = new HashMap<String, Object>();
		if(list!=null&&list.size()>0){
			map.put("errorMsg", "该节点下有资源，不可删除");
		}else{
			boolean flag = mdivService.delMDiv(divId);
			if(flag){
				map.put("successMsg", "删除成功");
			}else{
				map.put("errorMsg", "删除失败");
			}
		}
		return  map;
	}
}
