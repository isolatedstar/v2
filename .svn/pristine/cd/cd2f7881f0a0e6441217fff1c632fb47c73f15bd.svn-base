
package com.zllh.factoring.accreditation.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zllh.factoring.common.model.FacFinancing;
import com.zllh.factoring.common.model.model_extend.FacFinancingExtendBean;


/**
 * @ClassName: AccreditationMgService
 * @Description: 融资保理认可管理Service
 * @author JW
 * @date 2015-12-18 下午3:07:15
 */
public interface AccreditationMgService {

	
	/**
	 * @Title: findFinancingDetailById
	 * @author JW
	 * @Description: 根据融资单号查询订单行和担保信息明细
	 * @param id
	 * @return Map<String,Object>
	 * @throws
	 */
	public FacFinancingExtendBean findFinancingDetailById(String id);
	
	/**
	 * @Title: Accept
	 * @author JW
	 * @Description: 认可
	 * @return String
	 * @throws
	 */
	public String executeAccept(String[] ids,String lockFinjJson);
	
	public String executeAccept2(String[] ids,String lockFinjJson);
	
	/**
	 * @Title: Accept
	 * @author JW
	 * @Description: 待放款处理方法
	 * @return String
	 * @throws
	 */
	
	public String Loan_Processing(String financing_Order ,int acceptSdtatus);
	
	
	//支付系统回调payCAllback()方法,参数为融资单号、执行状态
	public String payCAllback(String financingID, int status);
	
	/**
	 * @Title: executeVeto
	 * @author JW
	 * @Description: 否决
	 * @param ids
	 * @return String
	 * @throws
	 */
	public String executeVeto(String[] id, String lockFinjJson);
	
	/**
	 * @Title: executeVeto
	 * @author JW
	 * @Description: 乐观锁
	 * @param ids
	 * @return String
	 * @throws
	 */
//	public String Lock(String financingOrder);

	/**
	 * @Title: selectAccept 
	 * @author JW
	 * @Description: 查询认可列表
	 * @return List<FacFinancing>
	 * @throws
	 */
	public List<FacFinancing> selectAccept(HashMap<String, Object> param);
	
	/**
	 *更新数据 
	 */
	public void executeAccept_update(String[] ids);

		
}
