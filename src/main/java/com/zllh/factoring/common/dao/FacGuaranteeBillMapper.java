package com.zllh.factoring.common.dao;

import java.util.List;
import java.util.Map;

import com.zllh.factoring.common.model.FacGuaranteeBill;
import com.zllh.factoring.common.model.model_extend.FacGuaBillFinGuaBillExtend;
import com.zllh.factoring.common.model.model_extend.FacGuaranteeBillExtend;

public interface FacGuaranteeBillMapper {
	
    int deleteByPrimaryKey(String guaranteeId);

    int insert(FacGuaranteeBill record);

    int insertSelective(FacGuaranteeBill record);

    FacGuaranteeBill selectByPrimaryKey(String guaranteeId);
    
    List<FacGuaranteeBill> selectByPrimaryKey1(String guaranteeId);

    int updateByPrimaryKeySelective(FacGuaranteeBill record);

    int updateByPrimaryKey(FacGuaranteeBill record);
    
    List<Map<String,Object>> getAllByGroupId(String payeeId);
    
    List<Map<String,Object>> getAllByGroupId1(Map<String,Object> map);
    
    List<Map<String,Object>> getAll();
    
    FacGuaranteeBill getByGuaranteeId(String guaranteetId);
    
    void update(Map<String,Object> map);
    
    List<FacGuaranteeBillExtend> selectlGuaranteeBillAndFinancingByIds(String[] dbIds);
    
    /**
     * 更新担保数据
     * @param guaranteetId
     * @return
     */
    int updateGuaranteeBill(FacGuaranteeBill fg);
    
    /**
	 * 根据融资单号，查询担保信息
	 */
    List<FacGuaBillFinGuaBillExtend> getGuaranteeBillByFinancingId(String financingId);
    
    /**
	 * 根据登录者的组织id 查询担保列表
	 * 
	 */
	List<FacGuaranteeBill> getFacGuaranteeBill(Map<String,Object> map);
	
	/**
	 * 获取长度
	 * @return
	 */
	String getAllLength(Map<String,Object> map);

	List<FacGuaranteeBillExtend> selectGuaranteeBillByIds(String[] gIds);

	/**
	 * @Title: updateGuaranteeBillByList 
	 * @author JW
	 * @Description: 批量更新
	 * @param facGuaranteeBillList void
	 * @throws
	 */
	void updateGuaranteeBillByList(List<FacGuaranteeBill> facGuaranteeBillList);

	void updateUsableAmount(FacGuaranteeBill gua);

}