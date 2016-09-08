package com.zllh.common.common.dao;

import java.util.List;

import com.zllh.common.common.model.PubRoleUser;
import com.zllh.common.common.model.PubRoleUserBiz;


public interface PubRoleUserMapper {
    public int deleteByUserId(String userId);

    public int insert(PubRoleUser record);

    public int insertSelective(PubRoleUser record);

    public PubRoleUser selectByPrimaryKey(String id);

    public int updateByPrimaryKeySelective(PubRoleUser record);

    public int updateByPrimaryKey(PubRoleUser record);

	public List<PubRoleUserBiz> selectUserRoles(String userId);
}