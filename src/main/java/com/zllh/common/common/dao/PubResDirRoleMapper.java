package com.zllh.common.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zllh.common.common.model.PubResDirRole;


public interface PubResDirRoleMapper {
    int insert(PubResDirRole record);

    int insertSelective(PubResDirRole record);

	List<PubResDirRole> selecByUserRoles(@Param(value="roleIds") String roleIds);
    
    
}