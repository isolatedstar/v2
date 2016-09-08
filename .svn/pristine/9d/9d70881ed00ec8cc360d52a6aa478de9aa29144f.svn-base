package com.zllh.mall.common.dao;

import java.util.List;
import com.zllh.mall.common.model.MtMmbType;


public interface MtMmbTypeMapper {
	//根据id删除
    int deleteByPrimaryKey(String id);
    //新增会员类型
    int insert(MtMmbType record);
    //新增by条件
    int insertSelective(MtMmbType record);
    
    MtMmbType selectByPrimaryKey(String id);
    
    int updateByPrimaryKeySelective(MtMmbType record);
    
    int updateByPrimaryKey(MtMmbType record);
    
    List<MtMmbType> queryAllBizTypesByMid(String mid);
    
    int deleteAllBizTypesByMid(String mid);
}