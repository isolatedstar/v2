package com.zllh.mall.common.dao;

import java.util.List;
import java.util.Map;

import com.zllh.mall.common.model.MtUserRelationship;
import com.zllh.mall.common.model.MtUserRelationshipBiz;

public interface MtUserRelationshipMapper {
    public int deleteByPrimaryKey(String id);

    public int insert(MtUserRelationship record);

    public int insertSelective(MtUserRelationship record);

    public MtUserRelationship selectByPrimaryKey(String id);

    public int updateByPrimaryKeySelective(MtUserRelationship record);

    public int updateByPrimaryKey(MtUserRelationship record);

    public List<MtUserRelationshipBiz> selectUserRealByUserId(String userId);

    public int deleteMtUserRelBeforeInsert(Map relMap);
}