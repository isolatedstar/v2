package com.zllh.mall.common.dao;

import java.util.Map;

import com.zllh.mall.common.model.MtMmbHomepage;



public interface MtMmbHomepageMapper {
    int insert(MtMmbHomepage record);

    int insertSelective(MtMmbHomepage record);
    
    int delById(Map<String, Object> params);
}