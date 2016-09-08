package com.zllh.mall.common.dao;

import java.util.List;
import java.util.Map;

import com.zllh.mall.common.model.MtGroup;
import com.zllh.mall.common.model.MtGroupBizz;



public interface MtGroupMapper {
    public int insert(MtGroup record);

    public int insertSelective(MtGroup record);

	public List<MtGroup> queryGroupByCondition(MtGroup group);

	public List<MtGroup> queryGroupList(Map<String, Object> map);

	public int countGroupByContiontion(Map<String, Object> map);

	public int updateGroupState(MtGroup group);

	public List<MtGroup> queryGroupByCondition(Map<String, Object> map);

	public List<MtGroup> querGroupsByConditionFormat(Map<String, Object> map);

	public List<MtGroup> queryGroupsByCondition(Map<String, Object> map);

	public List<MtGroup> queryGroupsByMmbId(Map<String, Object> map);

	public int countQueryGroupsByMmbId(Map<String, Object> map);

	public List<MtGroupBizz> queryGroupByUserId(Map<String, Object> map);
	
	public List<MtGroup> queryGroupRelationshipByContiontionContrary(Map<String, Object> map);

	public int countGroupByContion(MtGroup group);
	  //<!-- 通过会员ID,返回群組的集合 -->
	  
	  public List<MtGroup> queryGroupByMMbId(Map<String, Object> map);
	
	
}