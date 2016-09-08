package com.zllh.common.common.dao;

import com.zllh.common.common.model.PubMembertype;

/** 
 * @ClassName: PubMembertypeMapper 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author yangdm
 * @date 2016-3-14 下午3:55:13 
 */
public interface PubMembertypeMapper {
	public int deleteByPrimaryKey(String id);

    public int insert(PubMembertype record);

    public int insertSelective(PubMembertype record);

    public PubMembertype selectByPrimaryKey(String id);

    public int updateByPrimaryKeySelective(PubMembertype record);

    public int updateByPrimaryKey(PubMembertype record);
}
