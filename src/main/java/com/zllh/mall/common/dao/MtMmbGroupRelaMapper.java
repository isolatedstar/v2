package com.zllh.mall.common.dao;

import java.util.List;
import java.util.Map;

import com.zllh.mall.common.model.MtMemberBiz;
import com.zllh.mall.common.model.MtMmbGroupRela;


public interface MtMmbGroupRelaMapper {
    public int insert(MtMmbGroupRela record);

    public int insertSelective(MtMmbGroupRela record);
    
	public MtMmbGroupRela querymmGroupRelaById(String id);

	public int countMmbGroupRelaByContiontion(Map<String, Object> map);

	public List<MtMemberBiz> queryMmbGroupRelaList(Map<String, Object> map);

	public int updateGroupofMmb(MtMmbGroupRela mmbGroupRela);

	public int deleteuserforGroup(String id);

	public int deleteUserforGroup(MtMmbGroupRela mgr);
}