package com.zllh.mall.common.dao;

import java.util.List;
import java.util.Map;

import com.zllh.mall.common.model.MtMaterial;



public interface MtMaterialMapper {
    int insert(MtMaterial record);

    int insertSelective(MtMaterial record);
    
    int updateSelective(MtMaterial record);
    
    MtMaterial showById(String id);
    List<MtMaterial> showByDivId(Map<String, Object> map);
    Long countShowPic(Map<String, Object> map);
    List<String> showCariusel(Map<String, Object> map);
    List<String> searchByGood (String goodId);
    List<MtMaterial> searchByGoodId (String goodId);
    int delById (String id);
    //homePage
    List<MtMaterial> searchByHomePage(Map<String, Object> map);
    //pic
    List<MtMaterial> searchByPic(Map<String, Object> map);
}