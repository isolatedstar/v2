package com.zllh.factoring.common.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zllh.factoring.common.model.FacFinancing;
import com.zllh.factoring.common.model.FacFinancingGuarantee;
import com.zllh.factoring.common.model.model_extend.FacFinancingExtendBean;

public interface FacFinancingMapper {
    int deleteByPrimaryKey(String id);

    int insert(FacFinancing record);

    int insertSelective(FacFinancing record);

    FacFinancing selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FacFinancing record);

    int updateByPrimaryKey(FacFinancing record);
    
    int updateByPrimaryKey1(FacFinancing record);

    /**
     * @Title: selectFinancingRepaymen 
     * @author JW
     * @Description: 查询融资还款列表
     * @param paramMap
     * @return List<FacFinancingExtendBean>
     * @throws
     */
	List<FacFinancingExtendBean> selectFinancingRepaymen(HashMap<String, Object> paramMap);
	
	/**
	 * @Title: selectFinancingRepaymenCount 
	 * @author JW
	 * @Description: 查询融资还款列表条数
	 * @param paramMap
	 * @return Integer
	 * @throws
	 */
	Integer selectFinancingRepaymenCount(HashMap<String, Object> paramMap);
	
	
	/**
	 * @Title: Accept
	 * @author JW
	 * @Description: 融资认可
	 * @return String
	 * @throws
	 */
	public String executeAccept(String id);
	
	/**
	 * 查询融资信息
	 * @param map
	 * @return
	 */
	List<FacFinancing> getAllFinancing(Map<String,Object> map);

	/**
	 * @Title: findFinancingAndGuaranteeByFinId 
	 * @author JW
	 * @Description: 根据融资单ID查询融资单担保单及关联信息
	 * @param id
	 * @return FacFinancingExtendBean
	 * @throws
	 */
	FacFinancingExtendBean findFinancingAndGuaranteeByFinId(String id);

	/**
	 * @Title: selectAccept 
	 * @author JW
	 * @Description: 认可查询列表
	 * @param param
	 * @return List<FacFinancing>
	 * @throws
	 */
	List<FacFinancing> selectAccept(HashMap<String, Object> param);
	
	/**
	 * @Title: findFinancingAndGuaranteeListByFinIds 
	 * @author JW
	 * @Description: 根据融资单id数组查询融资单担保单及关联信息
	 * @param ids
	 * @return List<FacFinancingExtendBean>
	 * @throws
	 */
	List<FacFinancingExtendBean> findFinancingAndGuaranteeListByFinIds(String[] ids);
	
	/**
	 * 保存融资担保信息
	 * @param fac
	 * @return
	 */
	int saveFacFinancingGuarantee(FacFinancingGuarantee ffg);
	
	/**
	 * 获取融资单的最大融资单号
	 * @return
	 */
	Integer getMaxFinancingId();
	
	/**
	 * 根据融资担保删除融资担保信息
	 */
	int deleteFinancingGuaranteeByFinancingId(String financingId); 
	
	/**
	 * 获取长度
	 * @return
	 */
	String getAllLength(Map<String,Object> map);

	FacFinancing selectByFinId(String financingId);

	/**
	 * @Title: updateFinancingByList 
	 * @author JW
	 * @Description: 批量更新
	 * @param fins void
	 * @throws
	 */
	void updateFinancingByList(List<FacFinancing> fins);

	List<FacFinancingExtendBean> findAllFinancing(Map<String, Object> map);

	void updateByFinId(FacFinancing fin);

	List<FacFinancingExtendBean> selectFinancingAndGuaByStatus(Integer status);
	
	/***
	 * 根据认可流水号id查找融资id
	 */
	List<FacFinancing> getFinanaingIdByAcceptBankId(String acceptBankId);

    FacFinancingExtendBean selectFinancingRepaymenByFinId(String financingId);
}