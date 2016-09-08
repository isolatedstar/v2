
package com.zllh.factoring.writeoffacc.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.base.mybatis.Page;
import com.zllh.factoring.common.dao.FacFinancingGuaranteeMapper;
import com.zllh.factoring.common.dao.FacFinancingMapper;
import com.zllh.factoring.common.dao.FacGuaranteeBillMapper;
import com.zllh.factoring.common.dao.FacGuaranteeRefundMapper;
import com.zllh.factoring.common.dao.FacWriteOffAccMapper;
import com.zllh.factoring.common.fac_enum.FinancingEnum;
import com.zllh.factoring.common.fac_enum.FinancingGuaranteeEnum;
import com.zllh.factoring.common.model.FacFinancing;
import com.zllh.factoring.common.model.FacFinancingGuarantee;
import com.zllh.factoring.common.model.FacGuaranteeBill;
import com.zllh.factoring.common.model.FacWriteOffAcc;
import com.zllh.factoring.writeoffacc.service.WriteOffAccService;

/**
 * @ClassName: WriteOffAccServiceImpl
 * @Description: 销账管理Service
 * @author JW
 * @date 2016年6月16日 上午8:34:27
 */
@Service
public class WriteOffAccServiceImpl implements WriteOffAccService {

	@Autowired
	private FacFinancingMapper financingMapper;
	
	@Autowired
	private FacFinancingGuaranteeMapper financingGuaranteeMapper;
	
	@Autowired
	private FacGuaranteeBillMapper guaranteeBillMapper;
	
	@Autowired
	private FacGuaranteeRefundMapper guaranteeRefundMapper;
	
	@Autowired
	private FacWriteOffAccMapper writeOffAccMapper;
	
	/** 销账 */
	@Override
	public void executeWriteOffAcc(String ids) {
		
		String[] idArry = ids.trim().split(",");
		List<FacFinancingGuarantee> finGuas = financingGuaranteeMapper.selectFinancingGuarantee(idArry);
		
		for(FacFinancingGuarantee finGua : finGuas){
			//更新（去掉所有的剩余给付金额和剩余冻结金额）
			FacFinancingGuarantee finGuaTo = new FacFinancingGuarantee();
			finGuaTo.setId(finGua.getId());
			finGuaTo.setRefundAmount(finGua.getPaymentsAmount());
			finGuaTo.setSurplusPaymentsAmount(BigDecimal.ZERO);
			finGuaTo.setSurplusFreezeAmount(BigDecimal.ZERO);
			finGuaTo.setStatus(FinancingGuaranteeEnum.WRITEOFFACC.getValue());
			financingGuaranteeMapper.updateByPrimaryKeySelective(finGuaTo);
			
			//更新（融资单状态）
			HashMap<String, Object> param = new HashMap<String, Object>();
			param.put("id", finGua.getId());
			param.put("finId", finGua.getFinancingId());
			//如果该融资单其它担保已全清则更新融资单的状态为已还款
			int count = financingGuaranteeMapper.selectByFinIdAndGuaId(param);
			if(count==0){
				FacFinancing fin = new FacFinancing();
				fin.setFinancingId(finGua.getFinancingId());
				fin.setStatus(FinancingEnum.FAC_REFUNDED.getValue());
				financingMapper.updateByFinId(fin);
			}
			
			//更新（还原担保单可用金额）
			FacGuaranteeBill gua = new FacGuaranteeBill();
			gua.setGuaranteeId(finGua.getGuaranteeId());
			gua.setUsableAmount(finGua.getSurplusFreezeAmount());
			guaranteeBillMapper.updateUsableAmount(gua);
			
			//插入销账记录
			FacWriteOffAcc woa = new FacWriteOffAcc();
			woa.setId(finGua.getId());
			woa.setFinancingId(finGua.getFinancingId());
			woa.setGuaranteeId(finGua.getGuaranteeId());
			woa.setSurplusFreezeAmount(finGua.getSurplusFreezeAmount());
			woa.setSurplusPaymentsAmount(finGua.getSurplusPaymentsAmount());
			woa.setInterest(finGua.getInterest());
			woa.setCreattime(new Date());
			writeOffAccMapper.insertSelective(woa);
		}
	}

	/** 查询销账列表*/
	@Override
	public Page<FacFinancingGuarantee> selectOutInterest(HashMap<String, Object> param, Integer nowPage) {
		Page<FacFinancingGuarantee> page = new Page<FacFinancingGuarantee>();
		page.setNowPage(nowPage);
		//查询总条数用于计算页数
		int totalCount = financingGuaranteeMapper.selectOutInterestCount(param);
		page.setTotalCount(totalCount);
		param.put("beginIndex", page.getBeginIndex());
		param.put("pageSize", page.getPageSize());
		List<FacFinancingGuarantee> finGuas= financingGuaranteeMapper.selectOutInterest(param);
		page.setTotalCount(totalCount);
		page.setResult(finGuas);
		return page;
	}

    @Override
    public Page<FacFinancingGuarantee> selectOutInterestHistry(HashMap<String, Object> param, Integer nowPage) {
        Page<FacFinancingGuarantee> page = new Page<FacFinancingGuarantee>();
        page.setNowPage(nowPage);
        //查询总条数用于计算页数
        int totalCount = financingGuaranteeMapper.selectOutInterestHistryCount(param);
        page.setTotalCount(totalCount);
        param.put("beginIndex", page.getBeginIndex());
        param.put("pageSize", page.getPageSize());
        List<FacFinancingGuarantee> finGuas= financingGuaranteeMapper.selectOutInterestHistry(param);
        page.setTotalCount(totalCount);
        page.setResult(finGuas);
        return page;
    }

}
