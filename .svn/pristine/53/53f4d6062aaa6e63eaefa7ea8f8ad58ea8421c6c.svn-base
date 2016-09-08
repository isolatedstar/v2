package com.zllh.mall.common.dao;

import java.util.List;
import java.util.Map;

import com.zllh.mall.common.model.MtOrdertitle;

public interface MtOrdertitleMapper {
    int insert(MtOrdertitle record);

    int insertSelective(MtOrdertitle record);
    
    int updateAll(MtOrdertitle record);
    
    int updateByObject(MtOrdertitle record);
    
    MtOrdertitle selectByPrimaryKey(String id);
    
    List<MtOrdertitle> searchOrderTitle(Map<String,Object> record);
    
    List<MtOrdertitle> searchPendingOrderTitle(Map<String,Object> record);
    
    List<MtOrdertitle> searchMyPendingOrderTitle(Map<String,Object> record);
    
    List<MtOrdertitle> selectByMemberId(Map<String,Object> record);
    
}