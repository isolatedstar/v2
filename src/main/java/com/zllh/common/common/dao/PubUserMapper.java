package com.zllh.common.common.dao;

import java.util.List;

import com.zllh.common.common.model.PubRole;
import com.zllh.common.common.model.PubUser;
import com.zllh.common.common.model.model_extend.UserExtendBean;
import org.apache.ibatis.annotations.Param;

public interface PubUserMapper {
    public int deleteByPrimaryKey(String userId);

    public int insert(PubUser record);

    public int insertSelective(PubUser record);

    public PubUser selectByPrimaryKey(String userId);

    public int updateByPrimaryKeySelective(PubUser record);

    public int updateByPrimaryKey(PubUser record);

	public List<PubRole> toAddUser(String mmbId);

	public UserExtendBean findByUserName(String username);

	public PubUser selectByName(String username);

    public int getNumByAccount(@Param("account")String account,@Param("id") String id);
	
	

}