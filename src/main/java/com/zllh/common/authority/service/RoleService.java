package com.zllh.common.authority.service;

import java.util.List;

import com.zllh.common.common.model.PubRole;
import com.zllh.common.common.model.model_extend.RoleExtendBean;

public interface RoleService {

	List<PubRole> findByUserId(String id);

	List<PubRole> findByResourceId(String id);
	
	List<RoleExtendBean> selectAllRoleAndResource();

}
