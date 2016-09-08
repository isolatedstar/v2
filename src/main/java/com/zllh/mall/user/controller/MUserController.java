package com.zllh.mall.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zllh.base.security.encoder.impl.MD5Encoder;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zllh.base.controller.BaseController;
import com.zllh.common.common.model.PubRole;
import com.zllh.common.common.model.PubRoleUserBiz;
import com.zllh.common.common.model.PubUser;
import com.zllh.common.common.model.model_extend.UserExtendBean;
import com.zllh.mall.common.model.MtMuser;
import com.zllh.mall.common.model.MtMuserBiz;
import com.zllh.mall.user.service.IMUserService;
import com.zllh.utils.base.Utils;
import com.zllh.utils.common.DateUtil;
import com.zllh.utils.common.Page;
import com.zllh.utils.common.UUIDCreater;

@Controller
@RequestMapping("/muserController")
public class MUserController extends BaseController{
	
	@Autowired
	private IMUserService userService;
	
	@RequestMapping("/createUser")
	@ResponseBody
	public boolean createUser(Model model,
			@RequestParam(value="account",required=false,defaultValue="") String account,
			@RequestParam(value="email",required=false,defaultValue="") String email,
			@RequestParam(value="telephone",required=false,defaultValue="") String telephone,
			@RequestParam(value="name",required=false,defaultValue="") String name,
			@RequestParam(value="mmbId",required=false,defaultValue="") String mmbId,
			@RequestParam(value="roles",required=false,defaultValue="") String roles,
			@RequestParam(value="password",required=false,defaultValue="") String password){
//		取得前台的传过来的数据集合,个人数据用对象进行封装,角色用list进行保存
//		调用添加的service方法,把我们封装好的对象和map集合作为参数传到service中.
//		这需要两个service方法,分别是添加操作员信息和添加角色用户关系.
//		返回到管理页面
		boolean flag=false;
		MtMuser muser=new MtMuser();
		PubUser pUser=new PubUser();
		String id=UUIDCreater.getUUID();
		String newDate=DateUtil.getNow();
		UserExtendBean user=(UserExtendBean)session.getAttribute("user");
		String mmbName="";
		if (user!=null) {
			MtMuser u=user.getMuser();
			mmbId=u.getMmbId();
			mmbName=u.getMmbName();
		}
		//封装数据
		//密码
		if(!StringUtils.isBlank(password)){
			String enpa=Utils.securityUtil.encodePassword(account, password);
			muser.setPassword(enpa);
			pUser.setPassword(enpa);
		}
		//登录账号
		if(!StringUtils.isBlank(account)){
			muser.setAccount(account);
			pUser.setAcountName(account);
		}
		if(!StringUtils.isBlank(email)){
			muser.setEmail(email);
		}
		if(!StringUtils.isBlank(telephone)){
			muser.setTelephone(telephone);
		}
		//操作员名称
		if(!StringUtils.isBlank(name)){
			muser.setName(name);
			pUser.setUserName(name);
		}
		//会员ID
		if(!StringUtils.isBlank(mmbId)){
			muser.setMmbId(mmbId);
		}
		//会员姓名
		if(!StringUtils.isBlank(mmbName)){
			muser.setMmbName(mmbName);
		}
		//添加操作员ID 
		muser.setId(id);
		pUser.setUserId(id);
		//添加创建时间
		pUser.setCreatedatetime(newDate);
		//添加状态值,默认是1,表示已开启
		muser.setState(1);
		
		//添加操作员
		flag=userService.createUser(pUser,muser,roles);
		//返回操作状态
		return flag;
	}
	
	//核查操作员是否已添加,检查登录账号是否已经注册
	@RequestMapping("/checkUser")
	public void checkUser(Model model,
			@RequestParam("account") String account){
		//获取传过来的account(用户账号)
		MtMuser muser=new MtMuser();
		if(!StringUtils.isBlank(account)){
			muser.setAccount(account);
			muser=userService.checkUser(muser);
		}
		//若查询到已经注册,则直接返回true;
		if(muser!=null){
			outJson(true);
		}else {
			outJson(false);
		}
			
	}
	
 
	@RequestMapping("/editUser")
	@ResponseBody
	public boolean editUser(
			@RequestParam(value="id1",required=false,defaultValue="") String id1,
			@RequestParam(value="account1",required=false,defaultValue="") String account1,
			@RequestParam(value="email1",required=false,defaultValue="") String email1,
			@RequestParam(value="telephone1",required=false,defaultValue="") String telephone1,
			@RequestParam(value="name1",required=false,defaultValue="") String name1,
			@RequestParam(value="mmbId1",required=false,defaultValue="") String mmbId1,
			@RequestParam(value="mmbName1",required=false,defaultValue="") String mmbName1,
			@RequestParam(value="roles1",required=false,defaultValue="") String roles,
			@RequestParam(value="state",required=false,defaultValue="1") Integer state,
			@RequestParam(value="password",required=false,defaultValue="") String password){
		/*获取前台传过来的操作员ID,通过查询service方法,拿到操作员对象
		再用操作员ID去调查询会员角色的service,返回他拥有的所有角色,并在会员的所有的拥有角色中勾选出来
		把修改以后的数据分别用对象和map进行封装,调用他们各自修改的service方法,修改数据
		返回到管理页面*/
		boolean flag=false;
		MtMuser muser=new MtMuser();
		PubUser pUser=new PubUser();
		String newDate=DateUtil.getNow();
		//封装数据
		//密码
		if(!StringUtils.isBlank(password)){
			String enpa=Utils.securityUtil.encodePassword(account1, password);
			muser.setPassword(enpa);
			pUser.setPassword(enpa);
		}
		//登录账号
		if(!StringUtils.isBlank(account1)){
			muser.setAccount(account1);
			pUser.setAcountName(account1);
		}
		if(!StringUtils.isBlank(email1)){
			muser.setEmail(email1);
		}
		if(!StringUtils.isBlank(telephone1)){
			muser.setTelephone(telephone1);
		}
		//操作员名称
		if(!StringUtils.isBlank(name1)){
			muser.setName(name1);
			pUser.setUserName(name1);
		}
		//会员ID
		if(!StringUtils.isBlank(mmbId1)){
			muser.setMmbId(mmbId1);
		}
		//会员姓名
		if(!StringUtils.isBlank(mmbName1)){
			muser.setMmbName(mmbName1);
		}
		//添加操作员ID 
		if(!StringUtils.isBlank(id1)){
			muser.setId(id1);
			pUser.setUserId(id1);
		}
		//添加创建时间
		pUser.setModifydatetime(newDate);
		
		//操作员状态
		muser.setState(state);
		//修改操作员
		flag=userService.updateUser(pUser,muser,roles);
		//返回操作状态
		return flag;
	}
	
	@RequestMapping("/toEditUserPage")
	@ResponseBody
	public Map<String, Object> toEditUserPage(Model model,
			@RequestParam(value="userId",required=false,defaultValue="") String userId) {
		List<PubRoleUserBiz> list=new ArrayList<PubRoleUserBiz>();
		Map<String, Object> content=new HashMap<String, Object>();
		MtMuser muser=new MtMuser();
		if(!StringUtils.isBlank(userId)){
			//通过ID查询角色对象
			list=userService.selectUserRoles(userId);
			muser=userService.selectUserById(userId);
		}
		if (!list.isEmpty()&&muser!=null) {
			content.put("rolesInfo", list);
			content.put("userInfo", muser);
		}else {
			logger.info("查询角色信息为空");
		}
		return content;
	}


	/**
	 * 修改密码前 先验证 输入的是否是原始密码
	 * @param oldPassword 输入的原始密码
	 * @param password 用户的密码
	 * @return
	 */
	@RequestMapping("/checkOldPassword")
	@ResponseBody
	public Map<String, Object> checkOldPassword(@RequestParam(value="oldPassword",required=false,defaultValue="") String oldPassword,
												@RequestParam(value="account",required=false,defaultValue="") String account,
												@RequestParam(value="password",required=false,defaultValue="") String password) {
		Map<String, Object> result=new HashMap<String, Object>();
		String enPass = null;
		if(!StringUtils.isBlank(account)){
			enPass = Utils.securityUtil.encodePassword(account,oldPassword);
		}

		if(password.equals(enPass)){
			result.put("flag","success");
		}else{
			result.put("flag","fail");
		}

		return result;
	}
  
	//修改操作员的状态
	@RequestMapping("/editStateOfUser")
	public void editStateOfUser(
			@RequestParam(value="id",required=false,defaultValue="") String id,
			@RequestParam("state") int state){
		/*获取前台传过来的操作员ID,把当前操作员的状态设置为停用
		调用修改操作员状态的service方法,传入参数为当前状态值
		返回到管理页面*/
		boolean flag=false;
		MtMuser muser=new MtMuser();
		//封装数据
		if(!StringUtils.isBlank(id)){
			muser.setId(id);
			muser.setState(state);
			flag=userService.updateUser(muser);
		}
		outJson(flag);
	}
	
	
	@RequestMapping("/restUserOfPassword")
	public void restUserOfPassword(
			@RequestParam("userId") String userId,
			@RequestParam("password") String password){
		/*校验密码成功以后,把新密码进行加密处理
		把处理后的密码串作为参数,调用修改密码的service方法
		返回操作员管理页面*/
		boolean flag=false;
		MtMuser muser=new MtMuser();
		PubUser pUser=new PubUser();
		//封装数据
		if(!StringUtils.isBlank(userId)){
			muser.setId(userId);
			pUser.setUserId(userId);
			//封装密码
			muser.setPassword(password);
			pUser.setPassword(password);
			flag=userService.updateUser(muser,pUser);
		}
		outJson(flag);
	}
	
	//查询操作员信息
	@RequestMapping("/toQueryUser")
	public String toQueryUser(){

	    return "mall/user/user_manage";
	}

	//查询操作员信息
	@RequestMapping("/queryUser")
	@ResponseBody
	public Map<String,Object> queryUser(){
		/*获取页面传过来的参数
		调用查询service方法,得到list数据
		调用查询统计总数的service方法,返回查询的结果数
		将页面构造于分页页面对象中
		返回页面视图*/
		Map<String, Object> map = new HashMap<String, Object>();
		//获取登录的用户信息
		UserExtendBean user=(UserExtendBean)session.getAttribute("user");
		String mmbId="";
		if (user!=null) {
			MtMuser u=user.getMuser();
			mmbId=u.getMmbId();
		}
		if(!StringUtils.isBlank(mmbId)){
			map.put("mmbId", mmbId);
		}

		int total =  userService.queryUserList(map).size();

		// 分页数据初始化
		int pageNo = getPageNo();
		int pageSize = getPageSize();

		// 接收页面参数并传递给service
		map.put("startFirst", pageNo);//当前页开始行数
		map.put("startEnd", pageSize);

		List<MtMuserBiz> list=userService.queryUserList(map);

		Map<String,Object> resultMap = new HashMap<String,Object>();

		resultMap.put("rows", list);
		resultMap.put("total",total);

		return resultMap;
	}
	
	//转向添加操作员界面
	@RequestMapping("/toAddUserPage")
	public @ResponseBody List<PubRole> toAddUserPage(Model model){
		/*
		 * 这主要是作为添加操作员的一些准备动作
		 * 比如说查询出会员的角色的备选类型
		 * 首先加载出会员Id信息
		 * 根据会员联合查询出他所拥有的角色
		 */
		List<PubRole> list=new ArrayList<PubRole>();
		UserExtendBean user=(UserExtendBean)session.getAttribute("user");
		String mmbId="";
		if (user!=null) {
			MtMuser u=user.getMuser();
			mmbId=u.getMmbId();
		}
		if(!StringUtils.isBlank(mmbId)){
			list=userService.toAddUser(mmbId);
		}
		if (!list.isEmpty()&&list!=null) { 
			model.addAttribute("list", list);	
		}else {
			logger.info("查询角色信息为空");
		}
		return list;
	}


	/**
	 * 验证操作员账号唯一性
	 * @param account
	 * @return
	 */
	@RequestMapping("/getNumByAccount")
	@ResponseBody
	public int getNumByAccount(String  account,String id){

		int num = userService.getNumByAccount(account,id);
		return num;
	}

}
