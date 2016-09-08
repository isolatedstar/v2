package com.zllh.mall.common.dao;

import java.util.List;
import java.util.Map;

import com.zllh.mall.common.model.MtCategory;
import org.apache.ibatis.annotations.Param;


public interface MtCategoryMapper {
    int insert(MtCategory record);

    int insertSelective(MtCategory record);
    //查询出所有品类
    List<MtCategory> searchAll();
    //查询所有品类（easyui）
    List<MtCategory> searchAllCategory(Integer type);
    //根据parentId获取所有下级节点的集合
    List<MtCategory> getCategoryByParebtId(String parentId);
    //根据parentId获取用户该品类下所有下级节点的集合
    List<MtCategory> getUserMtCategory(Map<String, Object> map);
    // categoryId  获取单个品类
    MtCategory  getById(String categoryId);

    List<MtCategory> getMtCategoryByContractId(@Param("contractId")String contractId);
}