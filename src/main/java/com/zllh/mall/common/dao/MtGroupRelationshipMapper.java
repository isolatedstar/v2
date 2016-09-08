package com.zllh.mall.common.dao;

import java.util.List;
import java.util.Map;

import com.zllh.mall.common.model.MtGroup;
import com.zllh.mall.common.model.MtGroupBiz;
import com.zllh.mall.common.model.MtGroupRelationship;


public interface MtGroupRelationshipMapper {
    public int insert(MtGroupRelationship record);

    public int insertSelective(MtGroupRelationship record);

	public int  countGroupRelationshipByContiontion(Map<String, Object> map);

	public List<MtGroupBiz> queryGroupRelationshipByContiontion(Map<String, Object> map);

	public int deleteGroupRelationship(String id);

	public int countGroupRelationshipByContiontionContrary(Map<String, Object> map);

}