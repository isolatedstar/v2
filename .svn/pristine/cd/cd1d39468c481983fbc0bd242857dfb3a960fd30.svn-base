
package com.zllh.factoring.writeoffacc.service;

import java.util.HashMap;

import com.zllh.base.mybatis.Page;
import com.zllh.factoring.common.model.FacFinancingGuarantee;


/**
 * @ClassName: WriteOffAccService
 * @Description: 销账管理Service
 * @author JW
 * @date 2016年6月16日 上午8:29:36
 */
public interface WriteOffAccService {
	
	/**
	 * @Title: executeWriteOff 
	 * @author JW
	 * @Description: 销账
	 * @param id void
	 * @throws
	 */
	public void executeWriteOffAcc(String id);

	/**
	 * @param nowPage 
	 * @return 
	 * @Title: selectOutInterest 
	 * @author JW
	 * @Description: 查询销账列表
	 * @param param void
	 * @throws
	 */
	public Page<FacFinancingGuarantee> selectOutInterest(HashMap<String, Object> param, Integer nowPage);

	/**
	 * @Title: selectOutInterestHistry 
	 * @Description: 销账记录查询
	 * @param @param param
	 * @param @param nowPage
	 * @param @return
	 * @return Page<FacFinancingGuarantee>
	 * @throws
	 */
    public Page<FacFinancingGuarantee> selectOutInterestHistry(HashMap<String, Object> param, Integer nowPage);

}
