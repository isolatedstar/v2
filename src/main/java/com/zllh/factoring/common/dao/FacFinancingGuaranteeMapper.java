package com.zllh.factoring.common.dao;

import java.util.HashMap;
import java.util.List;

import com.zllh.factoring.common.model.FacFinancingGuarantee;
import com.zllh.factoring.common.model.model_extend.FacFinancingGuaranteeExtendBean;

public interface FacFinancingGuaranteeMapper {
    int deleteByPrimaryKey(String id);

    int insert(FacFinancingGuarantee record);

    int insertSelective(FacFinancingGuarantee record);

    FacFinancingGuarantee selectByPrimaryKey(String id);
    
    //通过融资单号查询
    List<FacFinancingGuarantee> selectByPrimaryKey1(String id);
    
  //通过担保单号查询
    List<FacFinancingGuarantee> selectByPrimaryKey2(String id);

    int updateByPrimaryKeySelective(FacFinancingGuarantee record);

    int updateByPrimaryKey(FacFinancingGuarantee record);
    
    int updateByPrimaryKeys(FacFinancingGuarantee record);

	void updateFinancingGuarantee(List<FacFinancingGuarantee> finGuaList);

	void insertList(List<FacFinancingGuarantee> financingGuaranteeList);

	List<FacFinancingGuaranteeExtendBean> selectFinGuaByPayeeId(HashMap<String, Object> paraMap);
	
	//删除融资担保关联表数据
	int deleteByForeignKey(FacFinancingGuarantee fingua);

	List<FacFinancingGuarantee> selectOutInterest(HashMap<String, Object> param);

	int selectOutInterestCount(HashMap<String, Object> param);

	List<FacFinancingGuarantee> selectFinancingGuarantee(String[] idArry);

	int selectByFinIdAndGuaId(HashMap<String, Object> param);

    int selectOutInterestHistryCount(HashMap<String, Object> param);

    List<FacFinancingGuarantee> selectOutInterestHistry(HashMap<String, Object> param);
}