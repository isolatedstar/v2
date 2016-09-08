
package com.zllh.factoring.repayment.service;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import com.zllh.base.mybatis.Page;
import com.zllh.factoring.common.model.FacMessageStatus;
import com.zllh.factoring.common.model.FacRefund;
import com.zllh.factoring.common.model.model_extend.FacFinancingExtendBean;
import com.zllh.factoring.common.model.model_extend.FacGuaranteeBillExtend;

/**
 * @ClassName: RefundMgService
 * @Description: 还款管理
 * @author JW
 * @date 2015-12-21 上午10:52:34
 */
public interface RefundMgService {
	
	/**
	 * @Title: executeFinancingRefund
	 * @author JW
	 * @Description: 融资还款
	 * @return String
	 * @throws
	 */
	public HashMap<String, Object> executeFinancingRefund(String financingIds, String lockFinjJson, String lockGuajJson, String repaymentJson);

	/**
	 * @return 
	 * @Title: selectFinancingRepaymen 
	 * @author JW
	 * @Description: 查询还款列表信息
	 * @param orderId
	 * @param guaranteetId
	 * @param refundDate
	 * @param isExpire
	 * @param refundSource
	 * @param status void
	 * @throws
	 */
	public Page<FacFinancingExtendBean> selectFinancingRepaymen(String refundDate, String financingId, String refundSource, String status, Integer nowPage);
	
	/**
	 * @Title: findRepaymentByFinancingId 
	 * @author JW
	 * @Description: 跳转到融资还款详细页查询
	 * @param financingId
	 * @return List<FacRefundExtendBean>
	 * @throws
	 */
	public Page<FacRefund> findRepaymentByFinancingId(String financingId, Integer nowPage);

	/**
	 * @Title: guaranteeRefund 
	 * @author JW
	 * @Description: 担保还款支付接口
	 * @param xml void
	 * @throws
	 */
	public void disposeGuaranteeRefund(String json);
	
	/**
	 * @return 
	 * @Title: executeGuaranteeRefund 
	 * @author JW
	 * @Description: 担保还款
	 * @param mess void
	 * @throws
	 */
	public void executeGuaranteeRefund(FacMessageStatus mess);
	
	public HashMap<String, Object> executeGuaranteeRefund(String guaranteereId, String refundAmount);
	
	/**
	 * @Title: factoringCallback 
	 * @author JW
	 * @Description: 保理回调接口
	 * @return String
	 * @throws+
	 */
	public String disposeFacRefundCallback(JSONObject json);

	/**
	 * @Title: selectFinancingRepaymenByFinId 
	 * @Description: 根据融资单号查询融资信息
	 * @param @return
	 * @return FacFinancingExtendBean
	 * @throws
	 */
	public FacFinancingExtendBean selectFinancingRepaymenByFinId(String financingId);

	/**
	 * @param repaymentJson 
	 * @param lockGuaJson 
	 * @param lockFinJson 
	 * @param financingId 
	 * @Title: disposeFacRefundFinancialCallback 
	 * @Description: 自还款(财务版)
	 * @param @param resMap
	 * @param @return
	 * @return String
	 * @throws
	 */
    void disposeFacRefundFinancialCallback(HashMap<String, Object> resMap, String financingId, String lockFinJson, String lockGuaJson, String repaymentJson, Integer type);

    /**
     * @Title: selectFinancingRepaymenByGuaId 
     * @Description: 根据担保单查询担保单和融资单
     * @param @param financingId
     * @param @return
     * @return FacGuaranteeBillExtend
     * @throws
     */
    public List<FacGuaranteeBillExtend> selectFinancingRepaymenByGuaId(String guaId);

}
