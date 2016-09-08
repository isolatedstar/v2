package com.zllh.mall.group.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zllh.mall.common.dao.MtGroupMapper;
import com.zllh.mall.common.dao.MtGroupRelationshipMapper;
import com.zllh.mall.common.dao.MtMemberMapper;
import com.zllh.mall.common.dao.MtMmbGroupRelaMapper;
import com.zllh.mall.common.dao.MtUserGroupRelaMapper;
import com.zllh.mall.common.model.MtGroup;
import com.zllh.mall.common.model.MtGroupBiz;
import com.zllh.mall.common.model.MtGroupBizz;
import com.zllh.mall.common.model.MtGroupRelationship;
import com.zllh.mall.common.model.MtMember;
import com.zllh.mall.common.model.MtMemberBiz;
import com.zllh.mall.common.model.MtMmbGroupRela;
import com.zllh.mall.common.model.MtUserGroupRela;
import com.zllh.mall.group.service.IGroupService;
import com.zllh.utils.common.DateUtil;
import com.zllh.utils.common.DictionaryUtil;
import com.zllh.utils.common.UUIDCreater;


@Service
public class GroupServiceImpl implements IGroupService  {

	@Autowired
	private MtGroupMapper groupMapper; 
	@Autowired
	private MtMmbGroupRelaMapper mmbGroupRelaMapper;
	@Autowired
	private MtGroupRelationshipMapper groupRelaMapper;
	@Autowired
	private MtUserGroupRelaMapper userGroupRelaMapper;
	@Autowired
	private MtMemberMapper mmbMapper;
	@Override
	public void createGroup(MtGroup group) {
		groupMapper.insertSelective(group);
	}

	//群组停用和启用
	@Override
	public boolean updateGroupState(String groupIds,int groupStatus) {
		boolean flag=false;
		if(groupIds.contains(",")){
			//判断传过来的群组是否为多个
			List<String> idList=java.util.Arrays.asList(groupIds.split(","));
			for(String id:idList){
				flag=editOne(id,groupStatus);
			}
		}else{
			flag=editOne(groupIds,groupStatus);
		}
		return flag;
	}
	
	//编辑单个群组的状态
	private boolean editOne(String id,int status) {
		int flag=0;
		if(!StringUtils.isBlank(id)){
			//UserDetails user = (UserDetails) this.session.getAttribute("user");
			MtUserGroupRela userGroupRela=new MtUserGroupRela();
			userGroupRela.setGroupId(id);
			userGroupRela.setState(status);
			flag=userGroupRelaMapper.updateByPrimaryKeySelective(userGroupRela);
		}
		if(flag>0){
			return true;
		}
		return false;
	}

	@Override
	public List<MtGroup> queryGroup(MtGroup group) {
		List<MtGroup> group2=groupMapper.queryGroupByCondition(group);
		return group2;
	}

	@Override
	public List<MtGroup> queryGroupList(Map<String, Object> map) {
		List<MtGroup> list=groupMapper.queryGroupList(map);
		return list;
	}

	@Override
	public int adduserforGroup(MtMmbGroupRela MtMmbGroupRela) {
		return mmbGroupRelaMapper.insertSelective(MtMmbGroupRela);
	}
	

	//把会员移除群组
	@Override
	public int deleteuserforGroup(MtMmbGroupRela MtMmbGroupRela) {
		int flag=mmbGroupRelaMapper.deleteUserforGroup(MtMmbGroupRela);
		return flag;
	}

	@Override
	public long countGroupByContiontion(Map<String, Object> map) {
		int count=groupMapper.countGroupByContiontion(map);
		return count;
	}

	//通过会员群组的标志ID 查找到相应的群组
	@Override
	public MtMmbGroupRela querymmbGroupRelaById(String id) {
		MtMmbGroupRela mmbGroupRela=mmbGroupRelaMapper.querymmGroupRelaById(id);
		return mmbGroupRela;
	}

	//添加群组关系
	@Override
	public int addGroupRelationship(MtGroupRelationship groupRelationship) {
		int flag=0;
		if(groupRelationship.getRpGroupId().contains(",")){
			List<String> idList=java.util.Arrays.asList(groupRelationship.getRpGroupId().split(","));
			int busType=groupRelationship.getBusType();
			String groupId=groupRelationship.getGroupId();
			for(String id:idList){
				groupRelationship.setGroupId(groupId);
				groupRelationship.setBusType(busType);
				groupRelationship.setRpGroupId(id);
				flag=createGroupRealByOne(groupRelationship);
			}
		}else {
			flag=createGroupRealByOne(groupRelationship);
		}
		return flag;
	}
	
//	单个建立群组关系：
	private int createGroupRealByOne(MtGroupRelationship groupRelationship){
		/*获取页面传过来的A群ID、B群ID、关系类型集合的集合
		调用群组关系添加service方法,给每个A群、B群、关系类型创建一条群间关系记录
		买卖关系共同计为买的关系
		返回到管理页面*/
		MtGroupRelationship groupRelationship1=null;
		Date nowDate=DateUtil.getNowDate();
		String id=UUIDCreater.getUUID();
		groupRelationship.setCreateTime(nowDate);
		groupRelationship.setId(id);
		groupRelationship1=formatGroupReal(groupRelationship);
		int flag=groupRelaMapper.insertSelective(groupRelationship1);
		return flag;
	}

//	群组交易类型转化
	private MtGroupRelationship formatGroupReal(MtGroupRelationship groupRelationship){
		//判断传过来的交易类型是否为多个
		String change="";
		if(groupRelationship.getBusType()==DictionaryUtil.RELA_GROUP_GROUP_LOAN){
			change=groupRelationship.getGroupId();
			groupRelationship.setGroupId(groupRelationship.getRpGroupId());
			groupRelationship.setRpGroupId(change);
			groupRelationship.setBusType(DictionaryUtil.RELA_GROUP_GROUP_BORROW);
		}else if(groupRelationship.getBusType()==DictionaryUtil.RELA_GROUP_GROUP_SELL){
			change=groupRelationship.getGroupId();
			groupRelationship.setGroupId(groupRelationship.getRpGroupId());
			groupRelationship.setRpGroupId(change);
			groupRelationship.setBusType(DictionaryUtil.RELA_GROUP_GROUP_BUY);
		}
		return groupRelationship;
	}
	
	@Override
	public int deleteGroupRelationship(String ids) {
		int flag=0;
		if (ids.contains(",")) {
			List<String> idList=java.util.Arrays.asList(ids.split(","));
			for(String id:idList){
				flag=groupRelaMapper.deleteGroupRelationship(id);
			}
		}else {
			flag=groupRelaMapper.deleteGroupRelationship(ids);
		}
		return flag;
	}

	@Override
	public List<MtGroupRelationship> jsonToListObject(JSONArray jsonArray) {
		//把传过来的JSON数组转化为list对象的形式
		List<MtGroupRelationship> list=new ArrayList<MtGroupRelationship>();
		MtGroupRelationship groupRelationship=null;
		String group_id=null;
		String rp_group_id=null;
		String bus_type_biz=null;
		for (int i = 0; i < jsonArray.size(); i++) {
			groupRelationship=new MtGroupRelationship();
			if(!StringUtils.isBlank(group_id))
				groupRelationship.setGroupId(group_id);
			//返回后在进行迭代
			if(!StringUtils.isBlank(bus_type_biz))
				groupRelationship.setBusTypeBiz(bus_type_biz);
			if(!StringUtils.isBlank(rp_group_id))
				groupRelationship.setRpGroupId(rp_group_id);
			list.add(groupRelationship);
		}
		return list;
		
	}

	//修改群组与会员关系,会员入群和退群
	@Override
	public boolean updateGroupofMmb(String ids,int status,String groupId) {
		boolean flag=false;
		if(ids.contains(",")){
			List<String> idList=java.util.Arrays.asList(ids.split(","));
			for(String id:idList){
				flag=checkuserforGroupByOne(id,status,groupId);
			}
		}else{
			flag=checkuserforGroupByOne(ids,status, groupId);
		}
		return flag;
	}
	
//	群成员审批(单个)：
	private boolean checkuserforGroupByOne(String id,int status,String groupId){
		/*获取会员ID集合和群ID
		获取当前会员和群组的关系状态
		若为同意,调用修改会员群组关系service方法,传入状态值为已加入
		若为拒绝或移除,调用删除会员群组关系service方法
		返回到管理页面*/
		int flag=0;
		if(!StringUtils.isBlank(id)){
			MtMmbGroupRela mmbGroupRela= new MtMmbGroupRela();
			mmbGroupRela.setGroupId(groupId);
			mmbGroupRela.setMmbId(id);
			
			if (mmbGroupRela!=null) {
				//传入状态为RELA_MMB_GROUP_SQL(1)则是同意入群,反之,则是拒绝入群,或者是移除群成员
				if (status==DictionaryUtil.RELA_MMB_GROUP_SQZ) {
					mmbGroupRela.setStatus(DictionaryUtil.RELA_MMB_GROUP_YJR);
					flag=mmbGroupRelaMapper.updateGroupofMmb(mmbGroupRela);
				}else {
					flag=deleteuserforGroup(mmbGroupRela);
				}
			}
		}
		if(flag>0){
			return true;
		}
		return false;
	}

	@Override
	public int countMmbGroupRelaByContiontion(Map<String, Object> map) {
		int result=mmbGroupRelaMapper.countMmbGroupRelaByContiontion(map);
		return result;
	}

	@Override
	public List<MtMemberBiz> queryMmbGroupRelaList(Map<String, Object> map) {
		List<MtMemberBiz> list=mmbGroupRelaMapper.queryMmbGroupRelaList(map);
		return list;
	}

	@Override
	public int countGroupRelationshipByContiontion(Map<String, Object> map) {
		int total=0;
		if(map.get("queryStatus")==DictionaryUtil.RELA_GROUP_GROUP_QUERY_ZX){
			total=groupRelaMapper.countGroupRelationshipByContiontion(map);
		}else if (map.get("queryStatus")==DictionaryUtil.RELA_GROUP_GROUP_QUERY_FX) {
			total=groupRelaMapper.countGroupRelationshipByContiontionContrary(map);
		}
		return total;
	}

	@Override
	public List<MtGroupBiz> queryGroupRelationshipByContiontion(Map<String, Object> map) {
		List<MtGroupBiz> list=groupRelaMapper.queryGroupRelationshipByContiontion(map);
		return list;
	}
	
	@Override
	public List<MtGroup> queryGroupRelationshipByContiontionContrary(Map<String, Object> map) {
		List<MtGroup> list=groupMapper.queryGroupRelationshipByContiontionContrary(map);
		return list;
	}
	
	//根据会员ID和群组的关系类型查询满足条件的群组
	@Override
	public List<MtGroup> queryGroupByCondition(Map<String, Object> map){
		int status=(int) map.get("busType");
		List<MtGroup> list=null;
		if(status==DictionaryUtil.RELA_GROUP_GROUP_SELL){
			map.put("busType", DictionaryUtil.RELA_GROUP_GROUP_BUY);
			list=groupMapper.querGroupsByConditionFormat(map);
		}else if(status==DictionaryUtil.RELA_GROUP_GROUP_LOAN){
			map.put("busType", DictionaryUtil.RELA_GROUP_GROUP_BORROW);
			list=groupMapper.querGroupsByConditionFormat(map);
		}else {
			list=groupMapper.queryGroupByCondition(map);
		}
		return list;
		
	}

	//修改群组关系
	@Override
	public int editGroupRelationships(String groupId,JSONArray jsonArray) {
		int flag=0;
		int mark=0;
		MtGroupRelationship groupRelationship=null;
		List<MtGroupRelationship> list=new ArrayList<MtGroupRelationship>();
		//删除原来的群组关系
		flag=deleteGroupRelationship(groupId);
		list=jsonToListObject(jsonArray);
		for (int i = 0; i < list.size(); i++) {
			groupRelationship=new MtGroupRelationship();
			//获取单个组装对象
			groupRelationship=list.get(i);
			if(groupRelationship.getBusTypeBiz().contains(",")){
				//判断是否为多个
				List<String> busTypList=java.util.Arrays.asList(groupRelationship.getBusTypeBiz().split(","));
				for(String busType:busTypList){
					//合成对象
					groupRelationship.setId(UUIDCreater.getUUID());
					groupRelationship.setBusType(Integer.valueOf(busType));
					//重新添加群组关系
					mark=addGroupRelationship(groupRelationship);
				}
			}else {
				groupRelationship.setId(UUIDCreater.getUUID());
				groupRelationship.setBusType(Integer.valueOf(groupRelationship.getBusTypeBiz()));
				//重新添加群组关系
				mark=addGroupRelationship(groupRelationship);
			}
		}
		if (flag>0&&mark>0){
			flag=1;
		}else {
			flag=0;
		}
		return flag;
	}
	
	//根据会员ID和查询类型,返回所有群组对象的集合
	@Override
	public List<MtGroup> queryGroupsByCondition(Map<String, Object> Map) {
		List<MtGroup> list=new ArrayList<>();
		if(Map!=null) {
			list=groupMapper.queryGroupsByCondition(Map);
		}
		return list;
	}
	
	//根据会员ID和查询类型,返回所有群组对象的集合
	@Override
		public List<MtGroup> queryGroupsByMmbId(Map<String, Object> Map) {
			List<MtGroup> list=new ArrayList<>();
			if(Map!=null) {
				list=groupMapper.queryGroupsByMmbId(Map);
			}
			return list;
		}
	
	//根据会员ID和查询类型,返回所有群组对象的集合的数目
	@Override
	public int countQueryGroupsByMmbId(Map<String, Object> Map) {
		int num=0;
		if(Map!=null) {
			num=groupMapper.countQueryGroupsByMmbId(Map);
		}
		return num;
	}
	//通过会员ID,返回群組的集合
	@Override
	public List<MtGroup> queryGroupByMMbId(Map<String, Object> map) {
		List<MtGroup> groups=groupMapper.queryGroupByMMbId(map);
		return groups;
	}
	//通过操作员ID,返回群組的集合
	@Override
	public List<MtGroupBizz> queryGroupByUserId(Map<String, Object> map) {
		List<MtGroupBizz> groups=groupMapper.queryGroupByUserId(map);
		return groups;
	}
	
	//通过群组ID,返回会员的集合
	@Override
	public List<MtMember> queryMmbByGroupId(Map<String, Object> map) {
		
		return mmbMapper.selsectMmmbByGroupId(map);
	}
	@Override
	public int queryMmbByGroupIdCount(Map<String, Object> map) {
		
		return mmbMapper.selsectMmmbByGroupIdCount(map);
	}

	@Override
	public List<MtGroup> queryGroupByCon(MtGroup group,String userId) {
		//查询所有的群组
		Map<String, Object> map=null;
		List<MtGroup> list=groupMapper.queryGroupList(map);
		map.put("userId", userId);
		//查询当前会员已经建立关系的群组
		//迭代修改当前会员群组的状态
		//返回整理好的群组关系
		return groupMapper.queryGroupByCondition(group);
	}

	@Override
	public int countGroupByContion(MtGroup group) {
		return groupMapper.countGroupByContion(group);
	}

	@Override
	public int deleteUserforGroup(MtMmbGroupRela mgr) {
		return mmbGroupRelaMapper.deleteUserforGroup(mgr);
	}
	
	
}
