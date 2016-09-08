package com.zllh.mall.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.zllh.base.controller.BaseController;
import com.zllh.common.common.model.model_extend.UserExtendBean;
import com.zllh.mall.common.model.MtMuser;
import com.zllh.mall.common.model.MtMuserBiz;
import com.zllh.mall.common.model.MtUserRelationship;
import com.zllh.mall.common.model.MtUserRelationshipBiz;
import com.zllh.mall.user.service.IMUserService;
import com.zllh.utils.common.Page;
import com.zllh.utils.common.UUIDCreater;


@Controller
@RequestMapping("/agentUserController")
public class AgentUserController extends BaseController{
	
	@Autowired
	private IMUserService userService;
	
	//添加代理操作员:
	@RequestMapping("/createAgentUser")
	@ResponseBody
	public boolean createAgentUser(Model model,
			@RequestParam(value="userId",required=false,defaultValue="") String userId,
			@RequestParam(value="account",required=false,defaultValue="") String account,
			@RequestParam(value="agentUserId",required=false,defaultValue="") String agentUserId){
		/*获取得前台的传过来的平台服务员集合和用户输入的代理操作员账号
		调用查询操作员的service方法,通过账号去查询ID
		调用添加代理操作员service方法记录
		返回到管理页面*/
		//创建操作员
		boolean flag=false;
		MtUserRelationship muserRela=new MtUserRelationship();
		String id= UUIDCreater.getUUID();
		if (!StringUtils.isBlank(userId)) {
			muserRela.setBusUserId(userId);
		}
		if (!StringUtils.isBlank(agentUserId)){
			muserRela.setPlaUserId(agentUserId);
		}
		muserRela.setId(id);

		flag=userService.addUserRela(muserRela);
		return flag;
	}
			
//	删除代理操作员：
	@RequestMapping("/deleteAgentUser")
	public void deleteAgentUser(
			@RequestParam(value="ids") String ids){
		/*获取页面传过来的代理操作员ID
		调用删除代理操作员的service方法
		若成功,刷新当前页面,若失败,则进行提示*/
		boolean flag=false;
		if (!StringUtils.isBlank(ids)) {
			//通过ID解除代理操作员
			flag=userService.deleteUserRela(ids);
		}
		outJson(flag);
	}
	
//	根据平台操作员的Id查询指定代理操作员:
	@RequestMapping("/showAgentUser")
	@ResponseBody
	public Map<String, Object> showAgentUser(Model model,
			@RequestParam(value="userId",required=false,defaultValue="") String userId,
			@RequestParam(value = "mmbId",required = false) String mmbId,
			@RequestParam(value="state",required=false,defaultValue="1") Integer state){
		/* 获取当前查询人的ID,判断是显示全部还是显示个人
		 * 调用查询代理操作员的service方法,返回满足条件的list集合
		 * 返回管理页面
		 */
		Map<String, Object> map=new HashMap<>();
		List<MtUserRelationshipBiz> muRealList=new ArrayList<MtUserRelationshipBiz>();
		List<MtMuser> muList=new ArrayList<MtMuser>();
		
		if (!StringUtils.isBlank(userId)) {
			//根据平台操作员的ID查询他锁代理的操作员
			muRealList=userService.selectUserReal(userId);
		}
		//查询所有未停用的所有平台操作员
//		muList=userService.queryUserList(mmbId);
//		if (muList!=null&&muList.size()!=0){
//			map.put("muList", muList);
//		}
		if (muRealList!=null&&muRealList.size()!=0){
			map.put("muRealList", muRealList);
		}
		map.put("userId", userId);
		return map;
	}
	
	//跳转到客服管理的主页：
	@RequestMapping("/toAgentIndex")
	public String toAgentIndex(Model model,
			@RequestParam(value="pageNo",required=false,defaultValue="1") long pageNo,
			@RequestParam(value="pageSize",required=false,defaultValue="") Integer pageSize
			){
		//需要返回所有未停用的所有平台操作员(第一个默认为所有)
		List<MtMuser> list=new ArrayList<MtMuser>();

		String mmbId = "";
		UserExtendBean user = (UserExtendBean) session.getAttribute("user");
		if(user != null){
			MtMuser mtMuser = user.getMuser();
			mmbId = mtMuser.getMmbId(); //当前操作员所属会员。
		}

		//查询所有未停用的所有平台操作员
		list=userService.queryUserList(mmbId);
		
	    model.addAttribute("userList",list);
		
		return "mall/user/agent_user_manage";
	}
	
	//核查业务操作员是否存在,所属会员不为99
	@RequestMapping("/checkUser")
	@ResponseBody
	public MtMuser checkUser(
			@RequestParam(value="account",required=false,defaultValue="") String account,
			@RequestParam(value="userId",required=false,defaultValue="") String userId,
			@RequestParam(value="state",required=false,defaultValue="1") Integer state){
		//封装数据
		MtMuser muser=new MtMuser();
		if (!StringUtils.isBlank(account)) {
			muser.setAccount(account);
		}
		
//		if (!StringUtils.isBlank(userId)) {
//			muser.setId(userId);
//		}
		
		muser.setState(state);
		//查询操作员是否存在
		muser=userService.checkUser(muser);
		return muser;
	}
	
	//跳转到操作员代理管理界面
	@RequestMapping("/toAgentUserIndex")
	public String toAgentUserIndex(Model model,
			@RequestParam(value="pageNo",required=false,defaultValue="1") long pageNo,
			@RequestParam(value="pageSize",required=false,defaultValue="") Integer pageSize,
			@RequestParam(value="state",required=false,defaultValue="1") Integer state
			){
		List<MtUserRelationshipBiz> muRealList=new ArrayList<MtUserRelationshipBiz>();
		UserExtendBean user=(UserExtendBean)session.getAttribute("user");
		String userId="";
		if (user!=null) {
			MtMuser u=user.getMuser();
			userId=u.getId();
		}
		if (!StringUtils.isBlank(userId)) {
			//根据平台操作员的ID查询他锁代理的操作员
			muRealList=userService.selectUserReal(userId);
			model.addAttribute("muRealList", muRealList);
		}
		return "mall/user/agent_user";
	}

}
