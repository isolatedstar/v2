package com.zllh.mall.common.dao;

import java.util.Map;

import com.zllh.mall.common.model.MtMmbPic;



public interface MtMmbPicMapper {
    int insert(MtMmbPic record);

    int insertSelective(MtMmbPic record);
    
    int delById(Map<String, Object> params);
}