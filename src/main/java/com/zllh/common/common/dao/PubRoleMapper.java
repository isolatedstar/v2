package com.zllh.common.common.dao;

import java.util.List;

import com.zllh.common.common.model.PubRole;
import com.zllh.common.common.model.model_extend.RoleExtendBean;

public interface PubRoleMapper {
	
	public int deleteByPrimaryKey(String roleId);

    public int insert(PubRole record);

    public int insertSelective(PubRole record);

    public PubRole selectByPrimaryKey(String roleId);

    public int updateByPrimaryKeySelective(PubRole record);

    public int updateByPrimaryKey(PubRole record);

	public List<PubRole> toAddUser(String mmbId);

	public List<PubRole> selectByUserId(String id);

	public List<PubRole> selectByResourceId(String id);

	public List<RoleExtendBean> selectAllRoleAndResource();
}