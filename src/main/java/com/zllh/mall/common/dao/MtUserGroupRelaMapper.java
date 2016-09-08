package com.zllh.mall.common.dao;

import com.zllh.mall.common.model.MtUserGroupRela;

public interface MtUserGroupRelaMapper {

    int deleteByPrimaryKey(String id);

    int insert(MtUserGroupRela record);

    int insertSelective(MtUserGroupRela record);

    MtUserGroupRela selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MtUserGroupRela record);

    int updateByPrimaryKey(MtUserGroupRela record);
}