
package com.zllh.common.job.factoring;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.base.controller.BaseController;
import com.zllh.factoring.common.dao.FacFinancingGuaranteeMapper;
import com.zllh.factoring.common.dao.FacFinancingMapper;
import com.zllh.factoring.common.fac_enum.FinancingEnum;
import com.zllh.factoring.common.fac_enum.FinancingGuaranteeEnum;
import com.zllh.factoring.common.model.FacFinancingGuarantee;
import com.zllh.factoring.common.model.model_extend.FacFinancingExtendBean;
import com.zllh.factoring.common.model.model_extend.FacFinancingGuaranteeExtendBean;
import com.zllh.factoring.common.repayment.RepaymentCalculate;

/**
 * @ClassName: InterestJob
 * @Description: 利息计算
 * @author JW
 * @date 2016年6月16日 下午2:40:07
 */
@Service
public class InterestJob {
	
	@Autowired
	private FacFinancingMapper financingMapper;
	
	@Autowired
	private FacFinancingGuaranteeMapper  financingGuaranteeMapper;
	
	@Autowired
	private RepaymentCalculate repaymentCalculate;
	
	public final Logger logger = LoggerFactory.getLogger(BaseController.class); 
	
	/**
	 * @Title: guaranteeRefund 
	 * @author JW
	 * @Description: 每天定时计算融资担保的利息, 检查有没有超期
	 * @throws
	 */
	public void guaranteeRefund(){
		List<FacFinancingExtendBean> fins = financingMapper.selectFinancingAndGuaByStatus(FinancingEnum.ACCEPTED.getValue());
		for(FacFinancingExtendBean fin : fins){
			//所用担保单
			List<FacFinancingGuaranteeExtendBean> facFinancingGuarantees = fin.getFacFinancingGuarantees();
			for(FacFinancingGuaranteeExtendBean finGua : facFinancingGuarantees){
				
				//要更新的融资担保关系实体
				FacFinancingGuarantee financingGuarantee = new FacFinancingGuarantee();
				//利息
				BigDecimal lixi = repaymentCalculate.guaranteeInterest(finGua.getSurplusPaymentsAmount(), fin.getAcceptDate(), fin.getExpireRate(), fin.getExtendRate());
				//本息(当前融资单所用担保单的本金及截止日期的利息)
				BigDecimal benxi = finGua.getSurplusPaymentsAmount().add(lixi);
				//冻结金额
				BigDecimal dong = finGua.getSurplusFreezeAmount();
				financingGuarantee.setId(finGua.getId());
				financingGuarantee.setInterest(lixi);
				if((benxi.compareTo(dong)==1||benxi.compareTo(dong)==0)) {
					financingGuarantee.setStatus(FinancingGuaranteeEnum.CHAOQIAN.getValue());
				}
				//更新利息，状态
				financingGuaranteeMapper.updateByPrimaryKeySelective(financingGuarantee);
			}
		}
	}
}
