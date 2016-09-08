package com.zllh.common.authority.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.common.authority.service.RoleService;
import com.zllh.common.common.dao.PubRoleMapper;
import com.zllh.common.common.model.PubRole;
import com.zllh.common.common.model.model_extend.RoleExtendBean;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private PubRoleMapper roleMapper;

	@Override
	public List<PubRole> findByUserId(String id) {
		
		return roleMapper.selectByUserId(id);
	}

	@Override
	public List<PubRole> findByResourceId(String id) {
		
		List<PubRole> ros = roleMapper.selectByResourceId(id);
		
		return ros;
	}

	@Override
	public List<RoleExtendBean> selectAllRoleAndResource() {
		return roleMapper.selectAllRoleAndResource();
	}

}
