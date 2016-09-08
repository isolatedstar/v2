package com.zllh.mall.group.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zllh.base.controller.BaseController;
import com.zllh.common.common.model.model_extend.UserExtendBean;
import com.zllh.mall.common.model.MtGroup;
import com.zllh.mall.common.model.MtGroupBiz;
import com.zllh.mall.common.model.MtGroupBizz;
import com.zllh.mall.common.model.MtGroupRelationship;
import com.zllh.mall.common.model.MtMember;
import com.zllh.mall.common.model.MtMmbGroupRela;
import com.zllh.mall.common.model.MtMuser;
import com.zllh.mall.group.service.IGroupService;
import com.zllh.utils.base.Utils;
import com.zllh.utils.common.DictionaryUtil;
import com.zllh.utils.common.Page;
import com.zllh.utils.common.UUIDCreater;


/** 
 * @ClassName: GroupController 
 * @Description: 群组管理
 * @author cxl
 * @date  
 */

@Controller
@RequestMapping("/group")
public class GroupController extends BaseController{
	
	@Autowired
	private IGroupService groupService;
//	@Autowired
//	private IUserRedisService userRedisService;

	//	群组查询：
	@RequestMapping("/queryGroupList")
	public String queryGroupList(Model model, 
			@RequestParam(value="id",required=false,defaultValue="") String id,
			@RequestParam(value="groupName",required=false,defaultValue="") String groupName,
			@RequestParam(value="remark",required=false,defaultValue="") String remark,
			@RequestParam(value="pageNo",required=false,defaultValue="1") long pageNo,
			@RequestParam(value="pageSize",required=false,defaultValue="") Integer pageSize
			){
		/*获取页面传过来的参数
		调用查询service方法,得到list数据
		调用查询统计总数的service方法,返回查询的结果数
		将页面构造于分页页面对象中
		返回页面视图*/
		//查询分为两种,一种是查询自己管理的群,另一种是显示全部
		Map<String, Object> map = new HashMap<String, Object>();
		UserExtendBean user= Utils.securityUtil.getUser();
		String userId="";
		if (user!=null) {
			MtMuser u=user.getMuser();
			userId=u.getId();
		}
		if(!StringUtils.isBlank(id)){
			map.put("id", id);
		}
		if(!StringUtils.isBlank(groupName)){
			map.put("groupName", groupName);
		}
		if(!StringUtils.isBlank(remark)){
			map.put("remark", remark);
		}
		if(!StringUtils.isBlank(userId)){
			map.put("userId", userId);
		}
		
		//执行条件查询总数
    	long totalCount = groupService.countGroupByContiontion(map);
	    //处理页码和页面记录数
		pageNo=(pageNo==0||pageNo<1)?1:pageNo;
		pageSize=(pageSize==null||pageSize<1)?10:pageSize;
		//处理页码和页面记录数
		map.put("startFirst",pageNo>0?(pageNo - 1) * pageSize:0);
		map.put("startEnd", pageSize);
		List<MtGroup> list=groupService.queryGroupList(map);
		//当前总页数
		long totalPageCount =(totalCount+pageSize-1)/pageSize;
		if(pageNo>totalPageCount){
			pageNo = (totalCount+pageSize-1)/pageSize;
		}
		//返回分页对象,包括list对象
	    page = new Page(totalCount, pageNo, pageSize, list);
	    model.addAttribute("groupList", list);
	    model.addAttribute("page",page);
	    //返回键入的查询条件
	    model.addAttribute("groupName",groupName);
	    return "mall/group/group_configuration";
	} 
	
//	创建群组：
	/*@RequestMapping("/createGroup")
	public String createGroup(Model model,
			@RequestParam("id") String id,
			@RequestParam("name") String name,
			@RequestParam("remark") String remark){
		获取页面传过来的值
		把数据封装成一个对象,调用添加群组的service的方法
		判断返回的结果,并作相应的提示
		返回到管理页面
		MtGroup group=new MtGroup();
		id=UUIDCreater.getUUID();
		if(!StringUtils.isBlank(name)){
			group.setGroupName(name);
		}
		if(!StringUtils.isBlank(remark)){
			group.setRemark(remark);
		}
		group.setId(id);
		//设置群组的状态默认为为已开启
		group.setGroupStatus(1);
		groupService.createGroup(group);
		return "listAll";
	}*/
	
	//审核群组是否被创建
	@RequestMapping("/checkGroupRepeat")
	public void checkGroupRepeat(String groupName){
		//根据群组的名称,查找群组的是否已经添加,或者说群名是否已经被占用
		if(!StringUtils.isBlank(groupName)){
			MtGroup group=new MtGroup();
			List<MtGroup> list=new ArrayList<>();
			group.setGroupName(groupName);
			//没有生成最新的对象
			list=groupService.queryGroup(group);
			if(CollectionUtils.isNotEmpty(list)){
				outJson(true);
			}else {
				outJson(false);
			}
		}else {
			logger.info("传入ID为空!");
		}
	}
	
//	群组编辑,停用和启用：批量
	@RequestMapping("/editGroup")
	public void editGroup (Model model,
			@RequestParam("groupIds") String groupIds,
			@RequestParam("groupState") Integer groupState){
		/*获取前台传过来的群组ID,把当前群组的状态设置为启用
		调用修改群组状态的service方法,传入参数为当前状态值
		返回到管理页面*/
		//获取页面传过来的id拼接字符串
		if(!StringUtils.isBlank(groupIds)){
			boolean flag=true;
			flag=groupService.updateGroupState(groupIds,groupState);
			if(flag){
				outJson("true");
			}else{
				outJson("false");
			}
		}else {
			logger.info("传入群组的ID为空");
		}
	}
	
//	按群成员查询,按状态(申请中,已加入)
	@RequestMapping("/queryMmbGroupRela")
	@ResponseBody
	public Map<String, Object> queryMmbGroupRela(
			@RequestParam(value="groupName",required=false,defaultValue="") String groupName,
			@RequestParam(value="status",required=false,defaultValue="") Integer status
			) {
		/*
		 * 首先封装从前台传过来的群查询条件,
		 * 由于前台页面有可能要进行分页,所以我们使用最熟悉的map对象进行封装
		 * 把封装好的对象送到后面,作为查询条件
		 * 返回查询单的结果,并用list集合进行返回
		 * */
		Map<String , Object> map=new HashMap<String , Object>();
		if(status!=0){
			map.put("status", status);
		}
		if(!StringUtils.isBlank(groupName)){
			map.put("groupName", groupName);
		}
		
		//查询记录总数
		UserExtendBean user= Utils.securityUtil.getUser();
		String userId="";
		if (user!=null) {
			MtMuser u=user.getMuser();
			userId=u.getId();
		}
		//分页组装
		int pageNo = StringUtils.isBlank(request.getParameter("pageNo"))? 0 : Integer.valueOf(request.getParameter("pageNo"));
		int pageSize = getPageSize();
		map.put("userId", userId);
		int total=0;
		List<MtGroupBizz> groups1 = groupService.queryGroupByUserId(map);
		if(groups1!=null&&groups1.size()>0){
			total = groups1.size();
		}
		Map<String, Object> map1=new HashMap<>();
		map.put("startFirst", pageNo);
		map.put("startEnd", pageSize);
		
		List<MtGroupBizz> groups=groupService.queryGroupByUserId(map);
		map1.put("rows", groups);
		map1.put("total",total);
		
		return map1;
	}
	
//	会员申请入群：
	@RequestMapping("/adduserforGroup")
	public void adduserforGroup(Model model,
			@RequestParam(value="mmbId",required=false,defaultValue="") String mmbId,
			@RequestParam(value="groupId",required=false,defaultValue="") String groupId){
		/*获取会员ID集合和群ID
		把当前会员和群组的关系改为申请中
		调用添加会员群组关系service方法,传入参数为当前会员的一些相关信息
		返回到管理页面*/
		int flag=0;
		MtMmbGroupRela mmbGroupRela=new MtMmbGroupRela();
		UserExtendBean user= Utils.securityUtil.getUser();
		if (user!=null) {
			MtMuser u=user.getMuser();
			mmbId=u.getMmbId();
		}
		if (!StringUtils.isBlank(mmbId)) {
			mmbGroupRela.setMmbId(mmbId);
		}
		if (!StringUtils.isBlank(groupId)) {
			mmbGroupRela.setGroupId(groupId);
		}
		String id=UUIDCreater.getUUID();
		mmbGroupRela.setId(id);
		//设置默认的关系状态,1-->申请中 2-->已加入 0-->未加入
		mmbGroupRela.setStatus(DictionaryUtil.RELA_MMB_GROUP_SQZ);
		flag=groupService.adduserforGroup(mmbGroupRela);
		if (flag>0) {
			outJson(true);
		}else {
			outJson(false);
		}
	}
	
	//会员退群
	@RequestMapping("/deleteUserforGroup")
	public void deleteUserforGroup(
			@RequestParam(value="id",required=false,defaultValue="") String id,
			@RequestParam(value="mmbId",required=false,defaultValue="") String mmbId,
			@RequestParam(value="groupId",required=false,defaultValue="") String groupId,
			@RequestParam(value="status",required=false,defaultValue="") Integer status) {
		
		MtMmbGroupRela mmbGroupRela=new MtMmbGroupRela();
		UserExtendBean user= Utils.securityUtil.getUser();
		if (user!=null) {
			MtMuser u=user.getMuser();
			mmbId=u.getMmbId();
		}
		
		if (!StringUtils.isBlank(id)) {
			mmbGroupRela.setId(id);
		}
		if (!StringUtils.isBlank(groupId)) {
			mmbGroupRela.setGroupId(groupId);
		}
		if(!StringUtils.isBlank(mmbId)){
			mmbGroupRela.setMmbId(mmbId);
			int flag=0;
			flag=groupService.deleteUserforGroup(mmbGroupRela);
			if(flag>0){
				outJson("false");
			}else {
				outJson("true");
			}
		}else {
			logger.debug("群组关系编号为空");
		}
	}
	
//	群成员审批(批量)：
	@RequestMapping("/checkuserforGroup")
	public void checkuserforGroup(Model model,
			@RequestParam(value="realIds",required=false,defaultValue="") String realIds,
			@RequestParam(value="groupId",required=false,defaultValue="") String groupId,
			@RequestParam(value="status") Integer status){
		/*获取会员ID集合和群ID
		获取当前会员和群组的关系状态
		若为批量处理,则进行迭代,单个调用checkuserforGroupByOne方法,
		若为单个则直接调用checkuserforGroupByOne方法
		判断返回的结果,并作相应的提示
		返回到管理页面*/
		//判断传过来的群组关系ID的集合
		if(!StringUtils.isBlank(realIds)){
			boolean flag=true;
			flag=groupService.updateGroupofMmb(realIds, status,groupId);
			if(flag){
				outJson("0");
			}else{
				outJson("1");
			}
		}
	}
	
//	批量建立群组关系：
	@RequestMapping("/createGroupReal")
	public void createGroupReal(Model model,
			@RequestParam(value="groupId",required=false,defaultValue="") String groupId,
			@RequestParam(value="rpGroupIds",required=false,defaultValue="") String rpGroupIds,
			@RequestParam(value="busType",required=false,defaultValue="1") Integer busType){
		/*获取页面传过来的A群ID、B群ID、关系类型集合的集合
		若传过来的是多个,对群组进行迭代,调用createGroupRealByOne方法
		若为单个,则直接调用createGroupRealByOne方法即可
		判断执行的返回值,并作相应的提示
		返回到管理页面*/
		MtGroupRelationship groupRelationship=new MtGroupRelationship();
		int flag=0;
		if(!StringUtils.isBlank(groupId)) {
			groupRelationship.setGroupId(groupId);
		}
		groupRelationship.setBusType(busType);
		if(!StringUtils.isBlank(rpGroupIds)){
			groupRelationship.setRpGroupId(rpGroupIds);
			flag=groupService.addGroupRelationship(groupRelationship);
		}
		if (flag>0) {
			outJson(true);
		}else {
			outJson(false);
		}
	}

//	删除群组关系：
	@RequestMapping("/deleteGroupReal")
	public void deleteGroupReal(Model model,
			@RequestParam("ids") String ids
			){
		/*获取页面传过来的ID值
		 * 通过ID去删除我们对应的群组关系
		 * 判断执行的返回值,并作相应的提示
		 * ajax异步返回到管理页
		 */
		if(!StringUtils.isBlank(ids)){
			int flag=0;
			flag=groupService.deleteGroupRelationship(ids);
			if(flag>0){
				outJson("false");
			}else {
				outJson("true");
			}
		}else {
			logger.debug("群组关系编号为空");
		}
	}
		
	//查询群组之间关系
	@ResponseBody
	@RequestMapping("/queryGroupRelationshipsByCondition")
	public Map<String, Object> queryGroupRelationshipsByCondition(Model model,
			@RequestParam(value="groupId",required=false,defaultValue="") String groupId,
			@RequestParam(value="id",required=false,defaultValue="") String id,
			@RequestParam(value="rpGroupId",required=false,defaultValue="") String rpGroupId,
			@RequestParam(value="groupName",required=false,defaultValue="") String groupName,
			@RequestParam(value="busType",required=false,defaultValue="1") Integer busType,
			@RequestParam(value="createTime",required=false,defaultValue="") String createTime,
			@RequestParam(value="queryStatus",required=false,defaultValue="0") Integer queryStatus,
			@RequestParam(value="pageNo",required=false,defaultValue="1") long pageNo,
			@RequestParam(value="pageSize",required=false,defaultValue="") Integer pageSize){
			/*
			 * 获取到当前群组ID,
			 * 通过查询方法,查找到与之相关的群组,并以list<对象>返回
			 * 遍历list,显示当前结果
			 * Ajax返回执行结果,并进行刷新
			 * */
			Map<String, Object> map=new HashMap<String, Object>();
			Map<String, Object> content=new HashMap<String, Object>();
			List<MtGroupBiz> list1=null;
			List<MtGroup> list2=null;
			if(!StringUtils.isBlank(id)){
				map.put("id", id);
			}
			if(!StringUtils.isBlank(rpGroupId)){
				map.put("rpGroupId", rpGroupId);
			}
			if(!StringUtils.isBlank(groupId)){
				map.put("groupId", groupId);
			}
			if(!StringUtils.isBlank(groupName)){
				map.put("groupName", groupName);
			}
			map.put("busType", busType);
			
			//默认是查询买卖的关系,还是借贷
			if(busType.equals(DictionaryUtil.RELA_GROUP_GROUP_SELL)){
				map.put("rpGroupId", groupId);
				map.remove("groupId");
			}else if (busType.equals(DictionaryUtil.RELA_GROUP_GROUP_LOAN)) {
				map.put("rpGroupId", groupId);
				map.remove("groupId");
			}
			if(!StringUtils.isBlank(createTime)){
				map.put("createTime", createTime);
			}
			
			//查询记录总数
			int total=groupService.countGroupRelationshipByContiontion(map);
			//分页组装
			//显示的页码
			pageNo=(pageNo<1)?1:pageNo;
			//每一页显示的记录数
			pageSize=(pageSize==null||pageSize<1)?10:pageSize;
			//处理显示的页码开头和记录数
			map.put("startFirst", (pageNo - 1) * pageSize);
			map.put("startEnd", pageSize);
			// 计算显示的总页数
			long totalPageCount = (total + pageSize - 1) / pageSize;
			// 当当前页码大于总页码数,显示最大的页码数
			if (pageNo > totalPageCount) {
				pageNo = (total + pageSize - 1) / pageSize;
			}
			
			//正向查询还是反向查询
			if (queryStatus==DictionaryUtil.RELA_GROUP_GROUP_QUERY_ZX) {
				list1 = groupService.queryGroupRelationshipByContiontion(map);
				page = new Page(total, pageNo, pageSize, list1);
			}else {
				list2=groupService.queryGroupRelationshipByContiontionContrary(map);
				page = new Page(total, pageNo, pageSize, list2);
			}
			
			content.put("groupList1", list1);
			content.put("groupList2", list2);
			content.put("pageNo", pageNo);
			content.put("pageSize", pageSize);
			model.addAttribute("page", page);	
			
			return content;
		}
	
	//修改群组关系
	@RequestMapping("/editGroupRelationships")
	public void editGroupRelationships(Model model,
			@RequestParam("group_id") String groupId,
			@RequestParam("objectJson") String objectJson){
		/*
		 * 获取前台传过来的json字符串
		 * 把字符串转化为一个个对象
		 * 遍历每一个对象,取出他们的关系类型
		 * 保存每一条记录,若是有相反的自动转化为买和借的关系
		 * */
		if (!StringUtils.isBlank(groupId)) {
			//把传过来的json对象转化为json数组
			JSONArray jsonArray=JSONArray.fromObject(objectJson);
			//判断传过来的json数据是否有数据
			if(jsonArray.size()>0){
				int flag=0;
				flag=groupService.editGroupRelationships(groupId,jsonArray);
				if (flag>0) {
					outJson(true);
				}else {
					outJson(false);
				}
			}else {
				logger.info("传入的对象为空");
			}
		}
	}
	
	//转向群组关系管理页面
	@RequestMapping("/toEditGroupRelationships")
	public String toEditGroupRelationships(Model model,
			@RequestParam("group_id") String groupId){
		/*
		 * 通过带入的群组ID查询群组关系和群组
		 * 返回查询的查询的结果
		 */
		if(!StringUtils.isBlank(groupId)){
			Map<String , Object> map=new HashMap<>();
			List<MtGroupBiz> list=new ArrayList<MtGroupBiz>();
			map.put("group_id", groupId);
			list=groupService.queryGroupRelationshipByContiontion(map);
			if (list!=null) {
				model.addAttribute("list", list);
			}else {
				logger.debug("未找到与群相关的信息");
			}
		}
		//转到相应的页面
		return "listAll";
	}
	
	//查询当前操作员管理的群组
	private List<MtGroupBizz> queryGroupByMmb(){
		Map<String, Object> map=new HashMap<>();
		List<MtGroupBizz> groups=new ArrayList<MtGroupBizz>();
		UserExtendBean user= Utils.securityUtil.getUser();
		String mmbId="";
		if (user!=null) {
			MtMuser u=user.getMuser();
			mmbId=u.getMmbId();
		}
		map.put("userId", mmbId);
		groups=groupService.queryGroupByUserId(map);
		
		return groups;
	}
	
	//转向群组配置界面
	@RequestMapping("/toGroupManage")
	public String toGroupManage(Model model){
		/*
		 * 录入操作员ID
		 * 返回查询的结果
		 */
		//转到相应的页面
		return "mall/group/group_configuration";
	}
	
	//通过条件查询群组
	@RequestMapping("/queryGroupByMmbCon")
	@ResponseBody
	public Map<String, Object> queryGroupByMmbCon(){
		Map<String, Object> returnMap=new HashMap<>();
		List<MtGroupBizz> groups=new ArrayList<MtGroupBizz>();
		Map<String, Object> map=new HashMap<>();
		UserExtendBean user= Utils.securityUtil.getUser();
		String userId="";
		String groupName=request.getParameter("groupName");
		if(!StringUtils.isBlank(groupName)){
			map.put("groupName", groupName);
		}
		if (user!=null) {
			MtMuser u=user.getMuser();
			userId=u.getId();
		}
		map.put("userId", userId);
		int count=groupService.queryGroupByUserId(map).size();
		int pageNo = StringUtils.isBlank(request.getParameter("pageNo"))? 0 : Integer.valueOf(request.getParameter("pageNo"));
		int pageSize = getPageSize();
		map.put("startFirst", pageNo);
		map.put("startEnd", pageSize);
		groups=groupService.queryGroupByUserId(map);
		
		returnMap.put("total", count);
		returnMap.put("rows", groups);
		
		return returnMap;
	}

	//转向群组审批管理
	@RequestMapping("/toGroupChcek")
	public String toGroupChcek(Model model){
		//查询待审批群组
		List<MtGroupBizz> groups=new ArrayList<MtGroupBizz>();
		Map<String, Object> map=new HashMap<>();
		UserExtendBean user= Utils.securityUtil.getUser();
		String userId="";
		if (user!=null) {
			MtMuser u=user.getMuser();
			userId=u.getId();
		}
		map.put("userId", userId);
		map.put("status", DictionaryUtil.GROUP_USER_OPEN);
		groups=groupService.queryGroupByUserId(map);
		model.addAttribute("groups", groups);
		
		return "mall/group/group_user_in";
	}
	
	//转向群组移除管理
	@RequestMapping("/toGroupOut")
	public String toGroupOut(Model model){
		
		//查询已加入的群组
		List<MtGroupBizz> groups=new ArrayList<MtGroupBizz>();
		Map<String, Object> map=new HashMap<>();
		UserExtendBean user= Utils.securityUtil.getUser();
		String userId="";
		if (user!=null) {
			MtMuser u=user.getMuser();
			userId=u.getId();
		}
		map.put("userId", userId);
		map.put("status", DictionaryUtil.GROUP_USER_OPEN);
		groups=groupService.queryGroupByUserId(map);
		model.addAttribute("groups", groups);
		
		return "mall/group/group_user_out";
	}
	
	//查询已加入的该群组的会员列表
	@RequestMapping("/queryGroupListOfIn")
	@ResponseBody
	public Map<String, Object> queryGroupListOfIn(){
		Map<String, Object> returnMap=new HashMap<>();
		Map<String, Object> map=new HashMap<>();
		List<MtMember> groups = new ArrayList<MtMember>();
		
		String flag=request.getParameter("flag");
		String status=request.getParameter("status");
		map.put("groupId", flag);
		map.put("status", status);
		
		
		groups=groupService.queryMmbByGroupId(map);
		
		
		returnMap.put("rows", groups);
		return returnMap;
	}
	
	//转向自己群组模块界面
	@RequestMapping("/toGroupMan")
	public String toGroupMan(){
		return "mall/group/group_manage";
	}
	
	//查询自己的群组模块
	//转向自己群组模块界面
		@RequestMapping("/queryGroupMan")
		@ResponseBody
		public Map<String, Object> queryGroupMan(Model model, 
				@RequestParam(value="id",required=false,defaultValue="") String id,
				@RequestParam(value="groupName",required=false,defaultValue="") String groupName,
				@RequestParam(value="remark",required=false,defaultValue="") String remark,
				@RequestParam(value="pageNo",required=false,defaultValue="") long pageNo,
				@RequestParam(value="pageSize",required=false,defaultValue="") Integer pageSize){
			
			UserExtendBean user= Utils.securityUtil.getUser();
			String mmbId="";
			
			Map<String, Object> returnMap=new HashMap<>();
			MtGroup group=new MtGroup();
			List<MtGroup> list=new ArrayList<>();
			
			if(!StringUtils.isBlank(id)){
				group.setId(id);
			}
			if(!StringUtils.isBlank(groupName)){
				group.setGroupName(groupName);
			}
			if(!StringUtils.isBlank(remark)){
				group.setRemark(remark);
			}
			group.setGroupStatus(DictionaryUtil.GROUP_STATUS_OPEN);
			Map<String, Object> map=new HashMap<>();
			if (user!=null) {
				MtMuser u=user.getMuser();
				mmbId=u.getMmbId();
				if (mmbId!=null) {
					map.put("mmbId", mmbId);
					//map.put("mmbId", "111");
				}
			}
			
			//查询待审批(申请中)的群组
			List<MtGroup> list1=new ArrayList<MtGroup>();
			map.put("status",DictionaryUtil.RELA_MMB_GROUP_SQZ);
			list1 = groupService.queryGroupByMMbId(map);
			//查询已加入的群组
			List<MtGroup> list2=new ArrayList<MtGroup>();
			map.put("status",DictionaryUtil.RELA_MMB_GROUP_YJR);
			list2 = groupService.queryGroupByMMbId(map);
			//查询满足条件的群组的个数
			int total=groupService.countGroupByContion(group);
			//查询满足条件的所有群组
			list=groupService.queryGroup(group);
			
			//判断状态  申请中 状态改为0  已加入状态改为2
			
			if(list!=null&&list.size()>0){
				if(list1!=null&&list1.size()>0){
					//待审核
					for (int i = 0; i < list.size(); i++) {
						for (int j = 0; j < list1.size(); j++) {
							if((list.get(i).getId()).equals(list1.get(j).getId())){
								list.get(i).setGroupStatus(0);
								break;
							}
							
						}
					}
				}
				if(list2!=null&&list1.size()>0){
					//已申请
					for (int i = 0; i < list.size(); i++) {
						for (int j = 0; j < list2.size(); j++) {
							if((list.get(i).getId()).equals(list2.get(j).getId())){
								list.get(i).setGroupStatus(2);
								break;
							}
							
						}
					}
				}
				
			}

			returnMap.put("total", total);
			returnMap.put("data", list);
			
			return returnMap;
		}
}
