package com.zllh.payment.front.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zllh.mall.common.model.MtMember;
import com.zllh.payment.front.service.AcctMgtService;
import com.zllh.payment.model.AcctMgt;
import com.zllh.payment.model.TaskBean;

@Controller
@RequestMapping("/acctMgtController")
public class AcctMgtController extends BaseController {

	@Autowired
	private AcctMgtService acctMgtService;
	
	@RequestMapping("/queryAcctByCon")
	@ResponseBody
	public Map<String, Object> queryAcctByCon(HttpServletRequest request, HttpServletResponse response, AcctMgt acctMgt, int page, int rows) {

		List<AcctMgt> accts = acctMgtService.getAcctByCon(acctMgt, page, rows);
		int acctCount = acctMgtService.getAcctCount(acctMgt);

		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put("rows", accts);// rows键 存放每页记录 list
		jsonMap.put("total", acctCount);// total键 存放总记录数，必须的

		return jsonMap;
	}

	@RequestMapping("/queryMasterAcct")
	@ResponseBody
	public List<AcctMgt> queryMasterAcct(HttpServletRequest request, HttpServletResponse response ,String type,String bankId) {
		List<AcctMgt> accts = acctMgtService.getAcctByAcctType(3,bankId);
		if(type!=null&&type.equals("0")){
			AcctMgt all = new AcctMgt();
			all.setBankAcct("全部");
			accts.add(0, all);
		}
		return accts;
	}

	@RequestMapping("/addAcct")
	@ResponseBody
	public Map<String, Object> addAcct(HttpServletRequest request, HttpServletResponse response, AcctMgt acctMgt) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map

		if (acctMgtService.acctIsExist(acctMgt.getBankAcct())) {
			jsonMap.put("result", "账号已存在！");
		} else if (acctMgtService.addAcct(acctMgt) == 1) {
			jsonMap.put("result", "添加成功！");
		} else {
			jsonMap.put("result", "添加失败！");
		}

		return jsonMap;
	}

	@RequestMapping("/editAcct")
	@ResponseBody
	public Map<String, Object> editAcct(HttpServletRequest request, HttpServletResponse response, AcctMgt acctMgt) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map

		if (acctMgtService.editAcct(acctMgt) == 1) {
			jsonMap.put("result", "修改成功！");

		} else {
			jsonMap.put("result", "修改失败！");

		}

		return jsonMap;
	}

	@RequestMapping("/deleteAcct")
	@ResponseBody
	public Map<String, Object> deleteAcct(HttpServletRequest request, HttpServletResponse response, AcctMgt acctMgt) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map

		if (acctMgtService.deleteAcct(acctMgt) == 1) {
			jsonMap.put("result", "删除成功！");
		} else {
			jsonMap.put("result", "删除失败！");
		}

		return jsonMap;
	}
	@RequestMapping("/queryOrg")
	@ResponseBody
	public Map<String, Object> queryOrg(String orgGroupName) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		//查询表
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("orgGroupName", orgGroupName);
		List<MtMember> results = acctMgtService.quetyMmbByOrgName(params);
		jsonMap.put("rows", results);
		return jsonMap;
	}
}
