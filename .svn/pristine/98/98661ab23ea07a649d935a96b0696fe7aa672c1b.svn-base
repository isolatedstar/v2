package com.zllh.mall.group.service;

import java.util.List;
import java.util.Map;

import com.zllh.mall.common.model.MtGroup;
import com.zllh.mall.common.model.MtGroupBiz;
import com.zllh.mall.common.model.MtGroupBizz;
import com.zllh.mall.common.model.MtGroupRelationship;
import com.zllh.mall.common.model.MtMember;
import com.zllh.mall.common.model.MtMemberBiz;
import com.zllh.mall.common.model.MtMmbGroupRela;





import net.sf.json.JSONArray;

public interface IGroupService {

//	创建群组：
	public void createGroup(MtGroup group);
//		调用DAO方法
//		根据结果返回ture或false
//	
//	修改群组的状态
	public boolean updateGroupState(String groupIds,int groupStatus);
//		调用DAO方法
//		根据结果返回ture或false
	
//	添加新成员入群：
	public int adduserforGroup(MtMmbGroupRela MtMmbGroupRela);
	
//	查询群组列表
	public List<MtGroup> queryGroupList(Map<String, Object> map);
//		调用DAO方法
//		根据结果返回相关群组列表
//		
//	删除群组关系
	public int deleteuserforGroup(MtMmbGroupRela mmbGroupRela);
	
	//会员退群
	public int deleteUserforGroup(MtMmbGroupRela mgr);
	
	//查询群组
	public List<MtGroup> queryGroup(MtGroup group);

	public int addGroupRelationship(MtGroupRelationship MtGroupRelationship);
	
	public int deleteGroupRelationship(String id);
	
	public List<MtGroupRelationship> jsonToListObject(JSONArray jsonArray);
	
	public MtMmbGroupRela querymmbGroupRelaById(String id);
	
	public int countMmbGroupRelaByContiontion(Map<String, Object> map);
	
	public boolean updateGroupofMmb(String id,int status,String groupId);
	
	public int countGroupRelationshipByContiontion(Map<String, Object> map);
	
	public List<MtMemberBiz> queryMmbGroupRelaList(Map<String, Object> map);
	
	public List<MtGroupBiz> queryGroupRelationshipByContiontion(Map<String, Object> map);
	
	public List<MtGroup> queryGroupByCondition(Map<String, Object> map);
	
	public long countGroupByContiontion(Map<String, Object> map);
	
	public int editGroupRelationships(String groupId, JSONArray jsonArray);
	
	public int countQueryGroupsByMmbId(Map<String, Object> Map);
	
	public List<MtGroup> queryGroupsByMmbId(Map<String, Object> Map);
	
	public List<MtGroup> queryGroupsByCondition(Map<String, Object> Map);
	
	public List<MtGroupBizz> queryGroupByUserId(Map<String, Object> map);
	
	public List<MtGroup> queryGroupRelationshipByContiontionContrary(Map<String, Object> map);
	
	public List<MtGroup> queryGroupByCon(MtGroup group,String userId);
	
	public int countGroupByContion(MtGroup group);
	
	public List<MtMember> queryMmbByGroupId(Map<String, Object> map);
	public int queryMmbByGroupIdCount(Map<String, Object> map);
	public List<MtGroup> queryGroupByMMbId(Map<String, Object> map);
	
}
