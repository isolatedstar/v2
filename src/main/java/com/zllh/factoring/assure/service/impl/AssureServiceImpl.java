package com.zllh.factoring.assure.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.common.common.model.PubUser;
import com.zllh.common.enums.BillsType;
import com.zllh.factoring.assure.service.AssureService;
import com.zllh.factoring.common.dao.FacFinancingGuaranteeMapper;
import com.zllh.factoring.common.dao.FacGuaranteeBillMapper;
import com.zllh.factoring.common.fac_enum.AssureEnum;
import com.zllh.factoring.common.fac_enum.IsOrNoEnum;
import com.zllh.factoring.common.interest.InterestConfig;
import com.zllh.factoring.common.model.FacFinancingGuarantee;
import com.zllh.factoring.common.model.FacGuaranteeBill;
import com.zllh.factoring.common.model.model_extend.FacGuaBillFinGuaBillExtend;
import com.zllh.mall.common.model.MtSettle;
import com.zllh.payment.front.dao.AcctMgtMapper;
import com.zllh.payment.model.AcctMgt;
import com.zllh.utils.base.Utils;
import com.zllh.utils.common.StringUtil;
import com.zllh.utils.soleid.SoleIdUtil;

@Service
public class AssureServiceImpl implements AssureService {

	@Autowired
	private FacGuaranteeBillMapper facGuaranteeBillMapper;
	
	@Autowired
	private FacFinancingGuaranteeMapper facFinancingGuaranteeMapper;
	
	@Autowired
	private AcctMgtMapper acctMgtMapper;
	
	@Override
	public void getPDF() {
		
	}

	/** 担保登记 */
	@Override
	public String executeAssureRegister(String guaranteetId,String discountRate) {
		
		//根据guaranteetId查询结款单
		FacGuaranteeBill guaBill = facGuaranteeBillMapper.selectByPrimaryKey(guaranteetId);
		if(guaBill==null) return "该担保资源不存在！";
		//判断结款单是否存在，判断结款单是否未登记
		if(guaBill.getStatus()==AssureEnum.WAITING_REGISTER.getValue()
		   &&guaBill.getGuaranteeAffirm()==IsOrNoEnum.YES.getValue()
		   &&guaBill.getProposerAffirm()==IsOrNoEnum.YES.getValue()
		   &&guaBill.getThirdpartyAffirm()==IsOrNoEnum.YES.getValue()){
			
			FacGuaranteeBill gua = new FacGuaranteeBill();
			gua.setStatus(AssureEnum.REGISTERD.getValue());
			gua.setOperationDate(new Date());
			gua.setGuaranteeoperateId(((PubUser)Utils.securityUtil.getUserDetails()).getUserId());
			gua.setGuaranteeName(((PubUser)Utils.securityUtil.getUserDetails()).getUserName());
			gua.setGuaranteeId(guaranteetId);
			gua.setDiscountRate(Double.valueOf(discountRate));
			int i = facGuaranteeBillMapper.updateByPrimaryKeySelective(gua);
			if(i==0){
				return "登记失败！";
			}
		}else{
			
//			if(guaBill.getStatus()==AssureEnum.WAITING_SIGNATURE.getValue()) return "该担保资源未完成签章！";
			if(guaBill.getStatus()==AssureEnum.REGISTERD.getValue()) return "该担保资源已登记为担保单无需重复操作！";
			if(guaBill.getStatus()==AssureEnum.REFUSE.getValue()) return "该担保资源已被驳回！";
		}
		
		return "登记成功！";
	}
	
	@Override
	public String executeSignature() {
		return null;
	}


	/** 商城申请结款单担保登记接口处理 */
	@Override
	public String Settle2Guarantee(MtSettle ms) {
//		根据settleid查mt_settle表，获得mt_settle对象 ms
//		创建FacGuaranteeBill对象 fgb
		FacGuaranteeBill fgb = new FacGuaranteeBill();
//		根据结款单ms字段填写担保单fgb对象的字段（需要查act_mgt表里的银行账户信息）
		fgb.setGuaranteeId(ms.getSettleCode().toString());
		fgb.setGuaranteeAmount(BigDecimal.valueOf(ms.getCtrMoney()));
		String gotMoney = ms.getGotMoney();
		if(gotMoney=="" || gotMoney==null){
			gotMoney="0";
		}
		fgb.setPaymentAmount(BigDecimal.valueOf(Double.valueOf(gotMoney)));
		fgb.setNonPayAmount(BigDecimal.valueOf(Double.valueOf(ms.getCtrMoney())-Double.valueOf(gotMoney)));
		fgb.setUsableAmount(BigDecimal.valueOf(Double.valueOf(ms.getCtrMoney())-Double.valueOf(gotMoney))) ;//ctrMoney - gotMoney 
		fgb.setPayerId(ms.getMmbpayId());
		fgb.setPayerName(ms.getMmbpayName());
		fgb.setPayeeId(ms.getMmbgetId());
		fgb.setPayeeName(ms.getMmbgetName());
		fgb.setGuaranteeAffirm(1);
		fgb.setProposerAffirm(1);
		fgb.setThirdpartyAffirm(1);
		fgb.setGuaranteeoperateId(Utils.securityUtil.getUser().getUserId());
		fgb.setGuaranteeName(Utils.securityUtil.getUser().getUserName());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date time=null;
		try {
		   time= sdf.parse(sdf.format(new Date()));
		} catch (ParseException e) {
		   e.printStackTrace();
		}
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//		Date time = sdf.parse(sdf.format(new Date()));
		fgb.setOperationDate(time);
		fgb.setExpireDate(ms.getCtrTime());
		fgb.setLock(1);
		fgb.setStatus(1);
		AcctMgt acctMgt = new AcctMgt();
		AcctMgt acctMgt2 = new AcctMgt();
		AcctMgt acctMgt3 = new AcctMgt();
		AcctMgt acctMgt4 = new AcctMgt();
		AcctMgt acctMgt5 = new AcctMgt();
		AcctMgt acctMgt6 = new AcctMgt();
		acctMgt.setAcctName("集团主户");
		fgb.setBlocAccount((acctMgtMapper.selectAcctByCon2(acctMgt)).get(0).getBankAcct()); //固定填平台一般户
		acctMgt2.setOrgGroupId(ms.getMmbpayId());
		acctMgt2.setAcctTypeName("一般户");
		fgb.setSchoolGeneralAccount((acctMgtMapper.selectAcctByCon2(acctMgt)).get(0).getBankAcct()); //学校一般户 根据mmbpayId为会员id取 act_mgt表中 一般户
		acctMgt3.setOrgGroupId(ms.getMmbpayId());
		acctMgt3.setAcctTypeName("附属户");
		fgb.setSchoolTheoreticalAccount((acctMgtMapper.selectAcctByCon2(acctMgt)).get(0).getBankAcct()); //学校虚拟户  根据mmbpayId为会员id取 act_mgt表中 虚拟户
		acctMgt4.setOrgGroupId(ms.getMmbgetId());
		fgb.setDistributorName((acctMgtMapper.selectAcctByCon2(acctMgt4)).get(0).getAcctName()); //供应商户名 根据mmbgetId为会员id取 act_mgt表中 账户名
		acctMgt5.setOrgGroupId(ms.getMmbgetId());
		acctMgt5.setAcctTypeName("附属户");
		fgb.setDistributorTheoreticalBank((acctMgtMapper.selectAcctByCon2(acctMgt5)).get(0).getBankAcct()); //供应商虚拟户  根据mmbgetId为会员id取 act_mgt表中 虚拟户
		acctMgt6.setOrgGroupId(ms.getMmbgetId());
		acctMgt6.setAcctTypeName("一般户");
		fgb.setDistributorGeneralBank((acctMgtMapper.selectAcctByCon2(acctMgt6)).get(0).getAcctName()); // 供应商一般户  根据mmbgetId为会员id取 act_mgt表中 一般户名
		fgb.setDiscountRate(0.8); // 供应商一般户  根据mmbgetId为会员id取 act_mgt表中 一般户名
//		担保单对象fgb 状态设为待登记
		
		
//		调service方法
		
//		返回结果
		return thirdSubrogationStamp(fgb);
	}

	
	
	/** 商城申请担保登记接口处理 */
	@Override
	public String thirdSubrogationStamp(FacGuaranteeBill gua) {
		
		//根据结款单号查询结款单是否存在
		FacGuaranteeBill guaBill = facGuaranteeBillMapper.selectByPrimaryKey(gua.getGuaranteeId());
		//如果存在返回状态给商城
		if(guaBill!=null){
			return guaBill.getStatus().toString();
	    //如果不存在则插入一条
		}else{
			facGuaranteeBillMapper.insertSelective(gua);
			return "success";
		}
	}

	/**
	 * @Title: getRegisterStatus 
	 * @author JW
	 * @Description: 查询结款单状态
	 * @return
	 * @return String
	 * @throws
	 */
	public String getRegisterStatus(){
		
		return null;
	}
	
	/**
	 * @Title: SaveRegister 
	 * @author JW
	 * @Description: 保存结款单
	 * @return
	 * @return String
	 * @throws
	 */
	public String SaveRegister(){
		return "";
	}
	
	@Override
	public List<Map<String,Object>> getAllByGroupId(String groupId){
		List<Map<String,Object>> list= facGuaranteeBillMapper.getAllByGroupId(groupId);
		return list;
	}
	
	@Override
	public List<Map<String,Object>> getAllByGroupId1(Map<String,Object> map){
		List<Map<String,Object>> list= facGuaranteeBillMapper.getAllByGroupId1(map);
		return list;
	}
	
	@Override
	public void insert(){
		FacGuaranteeBill gua = null;
		for(int i=0;i<5;i++){
			gua = new FacGuaranteeBill();
			gua.setGuaranteeId(SoleIdUtil.getSoleIdSingletion().getIntSoleId(BillsType.JIEKUAN.getValue()));
			gua.setExpireDate(new Date());
			gua.setGuaranteeAffirm(1);
			gua.setGuaranteeAmount(new BigDecimal("80000"));
			gua.setGuaranteeName("姜大伟");
			gua.setGuaranteeoperateId("9527");
			gua.setLock(1);
			gua.setNonPayAmount(new BigDecimal("80000"));
			gua.setOperationDate(new Date());
			gua.setPayeeId("007");
			gua.setPayeeName("张三");
			gua.setPayerId("007");
			gua.setPayerName("李四");
			gua.setPaymentAmount(new BigDecimal("0"));
			gua.setProposerAffirm(1);
			gua.setSchoolGeneralAccount("00000000000000000");
			gua.setSchoolTheoreticalAccount("00000000000000000");
			gua.setStatus(1);
			gua.setThirdpartyAffirm(1);
			gua.setUsableAmount(new BigDecimal("80000"));
			facGuaranteeBillMapper.insert( gua);
		}
	}
	
	public int insertFacGua(FacGuaranteeBill gua){
		FacGuaranteeBill gua_new = new FacGuaranteeBill();
		gua_new.setGuaranteeId(SoleIdUtil.getSoleIdSingletion().getIntSoleId(BillsType.OTHER.getValue()));
		gua_new.setExpireDate(gua.getExpireDate());
		gua_new.setGuaranteeAffirm(1);
		gua_new.setGuaranteeAmount(gua.getGuaranteeAmount());
		gua_new.setGuaranteeName(Utils.securityUtil.getUser().getUsername());
		gua_new.setGuaranteeoperateId(Utils.securityUtil.getUser().getUserId());
		gua_new.setLock(1);
		gua_new.setNonPayAmount(gua.getGuaranteeAmount());
		gua_new.setOperationDate(new Date());
		gua_new.setPayeeId(gua.getPayeeId());
		gua_new.setPayeeName(gua.getPayeeName());
		gua_new.setPayerId(gua.getPayerId());
		gua_new.setPayerName(gua.getPayerName());
		gua_new.setPaymentAmount(new BigDecimal("0"));
		gua_new.setProposerAffirm(1);
		gua_new.setSchoolGeneralAccount(gua.getSchoolGeneralAccount());
		gua_new.setSchoolTheoreticalAccount(gua.getSchoolTheoreticalAccount());
		gua_new.setStatus(1);
		gua_new.setThirdpartyAffirm(1);
		gua_new.setBlocAccount(gua.getBlocAccount());
		gua_new.setDistributorName(gua.getDistributorName());
		gua_new.setDistributorTheoreticalBank(gua.getDistributorTheoreticalBank());
		gua_new.setUsableAmount(gua.getGuaranteeAmount().multiply(new BigDecimal(InterestConfig.PERCENT)).setScale(2,BigDecimal.ROUND_HALF_UP));
		if(gua.getDiscountRate()==null){
			gua_new.setDiscountRate(0.8);
		}else{
			gua_new.setDiscountRate(gua.getDiscountRate());
		}
		
		int i = facGuaranteeBillMapper.insert(gua_new);
		return i;
	}
	
	
	public List<FacGuaranteeBill> getByGuaranteeId(List<String> ids){
		
		List<FacGuaranteeBill> list = new ArrayList<FacGuaranteeBill>();
		
		for(String GuaranteetId:ids){
			FacGuaranteeBill fg = facGuaranteeBillMapper.getByGuaranteeId(GuaranteetId);
			list.add(fg);
		}
		
		return list;
	}
	
	public void update(Map<String,Object> map){
		facGuaranteeBillMapper.update(map);
	}
	
	public int getThirdpartyAffirm(String guaranteetId){
		
		FacGuaranteeBill guaranteeBill = facGuaranteeBillMapper.selectByPrimaryKey(guaranteetId);
		int i = guaranteeBill.getThirdpartyAffirm();
		return i;
	}
	
	public int updateGuaranteeBill(FacGuaranteeBill fg){
		int i = facGuaranteeBillMapper.updateGuaranteeBill(fg);
		return i;
	}
	
	public boolean executerefuse(String guaranteetId){
		/** 
		 *  1、根据guaranteetId获得一个结款单对象
		 */
		FacGuaranteeBill guaranteeBill = facGuaranteeBillMapper.selectByPrimaryKey(guaranteetId);
		
		if(guaranteeBill!=null){
			/**
			 * 2、判断结款单状态,如果结款单状态不为已驳回，修改登记状态字段为已驳回
			 */
			if(guaranteeBill.getStatus()!=AssureEnum.REFUSE.getValue()){
				guaranteeBill.setStatus(AssureEnum.REFUSE.getValue());
				facGuaranteeBillMapper.updateGuaranteeBill(guaranteeBill);
			}
		}
		
		return true;
	}
	
	public boolean executerevoke(String guaranteetId){
		/**
		 *  1、根据guaranteetId获得一个结款单对象(查询融资关联表，看是否有融资用该担保单做担保，否 则删除，有 则不能注销)
		 */
		List<FacFinancingGuarantee> guaranteeBill = facFinancingGuaranteeMapper.selectByPrimaryKey1(guaranteetId);
		
		if(guaranteeBill.size()>0){
			/**
			 * 如果不为空则不可注销，返回false
			 */
			return false;
		}else{
			/**
			 * 2、如果为空,修删除该记录
			 */
//			FacGuaranteeBill guaBill = new FacGuaranteeBill();
//			guaBill.setStatus(AssureEnum.REVOKE.getValue());
//			guaBill.setGuaranteeId(guaranteetId);
//			facGuaranteeBillMapper.updateGuaranteeBill(guaBill);
			facGuaranteeBillMapper.deleteByPrimaryKey(guaranteetId);
		}
		
		return true;
	}
	
	public List<FacGuaBillFinGuaBillExtend> getGuaranteeBillByFinancingId(String financingId){
		
		List<FacGuaBillFinGuaBillExtend> list =facGuaranteeBillMapper.getGuaranteeBillByFinancingId(financingId);
		return list;
	}
	
	public List<FacGuaranteeBill> getFacGuaranteeBill(Map<String,Object> map){
		List<FacGuaranteeBill> list = facGuaranteeBillMapper.getFacGuaranteeBill(map);
		return list;
	}
	
	/**
	 * 获取长度
	 */
	public int getAllLength(Map<String,Object> map){
		int i = Integer.parseInt(facGuaranteeBillMapper.getAllLength(map));
		return i;
	}
}
