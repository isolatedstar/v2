package com.zllh.mall.common.dao;

import java.util.List;

import com.zllh.mall.common.model.MtMmbAreaBiz;


public interface MtMmbAreaBizMapper {
	int insert(MtMmbAreaBiz record);

    int insertSelective(MtMmbAreaBiz record);
    
    //根据mid查询所有地域业务
    List<MtMmbAreaBiz> queryAllMmbAreaBizsByMid(String id);
    
    //根据会员id删除会员地域业务信息
    int deleteMmbBizsByMid(String mid);
    
    //根据会员id查询数量
    int countMmbAreaBizsByMid(String id);
}