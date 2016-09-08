package com.zllh.factoring.assure.service;

import java.util.List;
import java.util.Map;

import com.zllh.factoring.common.model.FacGuaranteeBill;
import com.zllh.factoring.common.model.model_extend.FacGuaBillFinGuaBillExtend;
import com.zllh.mall.common.model.MtSettle;

public interface AssureService {

	/**
	 * @Title: getPDF 
	 * @author JW
	 * @Description: 获取PDF
	 * @return void
	 * @throws
	 */
	public void getPDF();

	/**
	 * @Title: Signature 
	 * @author JW
	 * @Description: 签章
	 * @return
	 * @return String
	 * @throws
	 */
	public String executeSignature();
	
	/**
	 * @Title: executeturnDown 
	 * @author JW
	 * @Description: 驳回
	 * @return
	 * @return String
	 * @throws
	 */
	public boolean executerefuse(String guaranteetId);
	
	/**
	 * 撤销
	 * @param guaranteetId
	 * @return
	 */
	public boolean executerevoke(String guaranteetId);
	
	/**
	 * @Title: ThirdSubrogationStamp 
	 * @author JW
	 * @Description: 商城申请担保登记接口service
	 * @return
	 * @return String
	 * @throws
	 */
	public String thirdSubrogationStamp(FacGuaranteeBill gua);
	
	/**
	 * 根据groupId查询已登记的担保资源
	 * @param groupId
	 * @return
	 */
	public List<Map<String,Object>> getAllByGroupId(String groupId);
	
	public List<Map<String,Object>> getAllByGroupId1(Map<String,Object> map);

	/**
	 * @Title: assureRegister 
	 * @author JW
	 * @Description: 担保登记
	 * @param guaranteetId
	 * @return String
	 * @throws
	 */
	public String executeAssureRegister(String guaranteetId,String discountRate);
	
	public void insert();
	
	public int  insertFacGua(FacGuaranteeBill gua);
	
	/**
	 * 根据GuaranteetId 查询担保信息
	 * @param ids
	 * @return
	 */
	public List<FacGuaranteeBill> getByGuaranteeId(List<String> ids);
	
	/**
	 * 更新担保可用金额
	 * @param fglist_page
	 */
	public void update(Map<String,Object> map);
	
	/**
	 * 根据guaranteetId查询担保信息
	 * @param guaranteetId
	 * @return
	 */
	public int getThirdpartyAffirm(String guaranteetId);
	
	/**
	 * 更新担保状态
	 * @param guaranteetId
	 * @return
	 */
	public int updateGuaranteeBill(FacGuaranteeBill fg);
	
	/**
	 * 根据融资单号，查询担保信息
	 */
	public List<FacGuaBillFinGuaBillExtend> getGuaranteeBillByFinancingId(String financingId);
	
	/**
	 * 根据登录者的组织id 查询担保列表
	 * 
	 */
	public List<FacGuaranteeBill> getFacGuaranteeBill(Map<String,Object> map);
	
	public int getAllLength(Map<String,Object> map);
	
	public String Settle2Guarantee(MtSettle mtSettle);
	
}
