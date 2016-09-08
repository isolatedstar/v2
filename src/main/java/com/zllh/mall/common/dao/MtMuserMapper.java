package com.zllh.mall.common.dao;

import java.util.List;
import java.util.Map;

import com.zllh.mall.common.model.MtMuser;
import com.zllh.mall.common.model.MtMuserBiz;


public interface MtMuserMapper {
    public int insert(MtMuser record);

    public int insertSelective(MtMuser record);

	public int countUserByContiontion(Map<String, Object> map);

	public List<MtMuserBiz> queryUserList(Map<String, Object> map);

	public MtMuser selectUserById(String userId);

	public int updateByPrimaryKeySelective(MtMuser user);

	public List<MtMuser> queryUserListAll(String mmbId);
	
	public MtMuser checkUser(MtMuser muser);
	
	public MtMuser selectUserByname(String name);

	public int updateMtUserStateByMember(Map map);

}