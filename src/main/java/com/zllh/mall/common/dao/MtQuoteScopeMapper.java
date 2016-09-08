package com.zllh.mall.common.dao;

import java.util.List;
import java.util.Map;

import com.zllh.mall.common.model.MtQuoteScope;




public interface MtQuoteScopeMapper {
    int insert(MtQuoteScope record);

    int insertSelective(MtQuoteScope record);
    //根据类型，报价Id查询是否存在关系
    List<MtQuoteScope> serachQuoteRalation(Map<String, Object> map);
    //根据报价Id以及type类型删除关系
    int deleteQutoRalation(Map<String, Object> map);
    
}