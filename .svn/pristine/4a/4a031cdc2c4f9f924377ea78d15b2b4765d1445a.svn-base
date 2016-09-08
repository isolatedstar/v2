package com.zllh.factoring.financing.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zllh.base.controller.BaseController;
import com.zllh.common.authority.service.UserService;
import com.zllh.common.common.model.model_extend.UserExtendBean;
import com.zllh.common.enums.BillsType;
import com.zllh.factoring.assure.service.AssureService;
import com.zllh.factoring.common.interest.InterestConfig;
import com.zllh.factoring.common.model.FacFinancing;
import com.zllh.factoring.common.model.FacGuaranteeBill;
import com.zllh.factoring.common.model.model_extend.FacGuaBillFinGuaBillExtend;
import com.zllh.factoring.financing.service.FinancingService;
import com.zllh.mall.common.dao.MtMemberMapper;
import com.zllh.mall.common.model.MtMember;
import com.zllh.mall.common.model.MtMemberRelationship;
import com.zllh.mall.mmbmmanage.service.IMmbRelationshipService;
import com.zllh.payment.front.service.AcctMgtService;
import com.zllh.payment.model.AcctMgt;
import com.zllh.utils.base.Utils;
import com.zllh.utils.soleid.SoleIdUtil;

/**
 * 直接融资
 */
@Controller
@RequestMapping("/directFinancingController")
public class DirectFinancingController extends BaseController{

	@Autowired
	private FinancingService financingService;
	
	@Autowired
	private AssureService assureService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AcctMgtService acctMgtService;
	
	@Autowired
	private IMmbRelationshipService relationshipService;
	
	@Autowired
	private MtMemberMapper mtMemberMapper;
	
	private String jsonData;
	
	private List<FacFinancing> Flist;
	
	private Map<String,Object> map=new HashMap<String,Object>();
	
	private List<String> ids_List = new ArrayList<String>();
	

	/**
	 * 直接融资
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/directFinancingNoFilter")
	public String directFinancingNoFilter(String page,String financingId) throws UnsupportedEncodingException{
		
		Map<String,Object> map=new HashMap<String,Object>();
		
		/**获取参数
		 * **/
		String proposerName = request.getParameter("proposerName");//操作员
		if(proposerName!=null){
			proposerName = new String(request.getParameter("proposerName").getBytes("iso-8859-1"), "UTF-8");
		}
		String predictRefundDate = request.getParameter("predictRefundDate");//还款日期
		
		//还款日期的初始时间
		String predictRefundDate_start = "";
		String predictRefundDate_end = "";
		if(predictRefundDate!=null ){
			if(predictRefundDate!="" ){
				predictRefundDate_start = predictRefundDate.substring(0, 10);
			predictRefundDate_end = predictRefundDate.substring(13, 23);
			}
		}
		map.put("predictRefundDate_start", predictRefundDate_start);
		map.put("predictRefundDate_end", predictRefundDate_end);
		
		String proposerDate = request.getParameter("proposerDate");//融资日期
		
		//融资日期的初始时间
		String proposerDate_start = "";
		String proposerDate_end = "";
		if(proposerDate!=null){
			if(proposerDate!=""){
				proposerDate_start = proposerDate.substring(0, 10);
				proposerDate_end = proposerDate.substring(13, 23);
			}
		}
		map.put("proposerDate_start", proposerDate_start);
		map.put("proposerDate_end", proposerDate_end);
		
		
		/**放到map中**/
		map.put("financingId", financingId);
		map.put("proposerName", proposerName);
		map.put("predictRefundDate", predictRefundDate);
		map.put("proposerDate", proposerDate);
		map.put("proposerId", ((UserExtendBean)Utils.securityUtil.getUserDetails()).getUserId());
		
		if(page!=null){
			map.put("page", Integer.parseInt(page));
		}
		
		/** 取融资信息总条数，计算显示页数 **/
		int length = financingService.getAllLength(map);
		if(length%10>0){
			length = length/10+1;
		}else{
			length = length/10;
		}
		
		/**  获取融资信息 **/
		Flist = financingService.getAllFinancing(map);
		List<MtMember> mebers = mtMemberMapper.findAllMtMember2();
		
		request.setAttribute("Flist", financingService.getAllFinancing(map));
		
		request.setAttribute("financingId", financingId);
		request.setAttribute("proposerName", proposerName);
		request.setAttribute("predictRefundDate", predictRefundDate);
		request.setAttribute("proposerDate", proposerDate);
		request.setAttribute("length", length);
		request.setAttribute("mebers", mebers);
		
		request.setAttribute("clas", page==null?1:Integer.parseInt(page));
		
//		/** 获取放贷方信息  **/
//		List<MtMemberRelationship> MmbRelationShipForBuy = getMmbRelationShipForBuy(((UserExtendBean)Utils.securityUtil.getUserDetails()).getUserId());
//		request.setAttribute("MmbRelationShipForBuy", MmbRelationShipForBuy);

		return "factoring/financing/financing_direct";
	}
	
	/** 获取放贷方信息  **/
	public List<MtMemberRelationship> getMmbRelationShipForBuy(String userId){
		Map<String,Object> map = new HashMap<String,Object>();
		if (!StringUtils.isBlank(userId)){
			map.put("mmbId", userId);
			map.put("bizType", Integer.valueOf(2));
		}
		return relationshipService.queryMmbRelationShipForBuy(map);
	}
	
	/**
	 * 点击添加新融资
	 */
	@RequestMapping("/addFinancingNoFilter")
	public String addFinancingNoFilter(){
		return "financing/financing_direct_add";
	}
	
	
//	@RequestMapping("/directFinancingQueryNoFilter")
//	public String directFinancingQueryNoFilter(){
//		return "financing/financing_direct_query";
//	}
	
	/**
	 * 点击融资按钮，进行融资操作
	 * @throws ParseException 
	 */
	@RequestMapping("/FinancingNoFilter")
	public @ResponseBody String FinancingNoFilter(String[] ids,String loanData,String factoringId,String bcrz_money,String yjhk_time,String repayDays,String member_name) throws ParseException{
		/**
		 * 页面参数ids：担保单id
		 */
		ids = request.getParameter("ids").split(",");
		ids_List = Arrays.asList(ids);
		
		/**
		 * 获取页面融资数据，生成融资对象
		 */
		FacFinancing fac = getFinancing(factoringId,bcrz_money,yjhk_time,repayDays,member_name);
		
		/**  1、锁担保资源  **/
		Utils.lock.thingLockByIds(ids);
		
		/**  2、取页面担保数据
		 */
		List<FacGuaranteeBill> fglist_page = JSON.parseArray(request.getParameter("loanData"), FacGuaranteeBill.class);
		
		/**3、从数据库中取担保资源
		 */
		List<FacGuaranteeBill> fgList = assureService.getByGuaranteeId(ids_List);
		
		/**
		 * 4、取页面选择的担保资源的本次担保金额  和  数据库中的可用担保金额
		 */
		BigDecimal usableAmount = new BigDecimal(0);//本次担保金额
		BigDecimal usableAmount_fg = new BigDecimal(0);//可用担保金额
		for(FacGuaranteeBill fg : fglist_page){
			usableAmount = fg.getUsableAmount();
			
			/**根据页面所选担保单号 从数据库取相应担保单所对应的可用担保金额
			 **/
			for(int i = 0;i<fgList.size();i++){
				if(fgList.get(i).getGuaranteeId().equals(fg.getGuaranteeId())){
					usableAmount_fg = fgList.get(i).getUsableAmount();
					break;
				}
			}
			
			/**
			 * 5、循环页面担保资源，判断担保可用金额是否已改变{是：解锁，返回json数据 金额不足，不能进行融资；否：更新数据}
			 */
			if(usableAmount.compareTo(usableAmount_fg)>0){
				/**解锁
				 */
				Utils.lock.deblocking(ids);
				jsonData = JSON.toJSONString("可用担保金额小于本次担保使用金额，请重新添加担保资源后再进行融资！");
				return jsonData;
				
			}
		}
		
		map.put("fac", fac);
		map.put("fglist_page", fglist_page);
		/**
		 * 5、保存融资数据和融资担保数据, 如果保存数据失败返回
		 */
		
		int i = financingService.saveFinancing(map);
		
		if(i == 0){
			jsonData = JSON.toJSONString("保存融资数据出错！");
			return jsonData;
		}else{
			/**
			 * 循环页面担保数据，更新数据库担保资源数据
			 */
			for(FacGuaranteeBill fg:fglist_page){
				
				/**
				 * 取数据库原来的 担保可用金额
				 */
				for(int j = 0;j<fgList.size();j++){
					if(fgList.get(j).getGuaranteeId().equals(fg.getGuaranteeId())){
						usableAmount_fg = fgList.get(j).getUsableAmount();
						break;
					}
				}
			  /**
			   * 剩余担保可用金额
			   */
				BigDecimal usableAmount_sy = usableAmount_fg.subtract(fg.getUsableAmount()).setScale(2 ,java.math.BigDecimal.ROUND_HALF_UP);
				
				map.put("guaranteeId", fg.getGuaranteeId());
				map.put("usableAmount", usableAmount_sy);
				
				assureService.update(map);
			}
			
			
			/**解锁
			 */
			Utils.lock.deblocking(ids);
			
			jsonData = JSON.toJSONString("融资成功！");
			
			return jsonData;
		}
	}
	

	/**
	 * 选择担保资源（根据groupId查询担保信息）
	 */
	@RequestMapping("/assureResourcesNoFilter")
	public @ResponseBody String assureResourcesNoFilter(){
		/**
		 * 取页面参数 放到map中
		 */
		String groupId = ((UserExtendBean)Utils.securityUtil.getUserDetails()).getMuser().getMmbId();
		String guaranteeName = request.getParameter("guaranteeName");
		String payerName = request.getParameter("payerName");
		
		map.put("payeeId", groupId);
		map.put("payerName", payerName);
		map.put("guaranteeName", guaranteeName);
		
		List<Map<String,Object>> li = assureService.getAllByGroupId1(map);
		
		jsonData = JSON.toJSONString(li);
		return jsonData ;
	}
	
	
	/**
	 * 获取银行账号
	 * @return 
	 * @return
	 */
	@RequestMapping("/AccountNOFilter")
    public void AccountNoFilter(){
		
	}
	
	/**
	 * 根据融资单号查询  担保信息
	 * @return
	 */
	@RequestMapping("/getAssureMssgNoFilter")
	public @ResponseBody String getAssureMssgNoFilter(){
		
		String financingId = request.getParameter("financingId");
		
		List<FacGuaBillFinGuaBillExtend> list = assureService.getGuaranteeBillByFinancingId(financingId);
		jsonData = JSON.toJSONString(list);
		
		return jsonData;
	}
	
	public FacFinancing getFinancing(String factoringId,String bcrz_money,String yjhk_time,String repayDays,String member_name) throws ParseException{
		
		FacFinancing fac = new FacFinancing();
		/**
		 * 获取页面融资数据，生成融资对象
		 */
		fac.setId(Utils.commonUtil.getUUID());
		//if(StringUtils.isBlank(fac.getFinancingId())){         
			//String financingId = financingService.getMaxFinancingId() ;
			fac.setFinancingId(SoleIdUtil.getSoleIdSingletion().getIntSoleId(BillsType.FINANCING.getValue()));
		//}
			
		fac.setMemberId(factoringId);
		fac.setMemberName(member_name);
		
		fac.setWaitPayTotal(new BigDecimal(bcrz_money));//融资总金额
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date date = sdf.parse(yjhk_time);
		fac.setPredictRefundDate(date);//预计还款日期
		
		fac.setFinancingDate(new Date());//融资日期
		
		fac.setPredictServiceFee(new BigDecimal(bcrz_money).multiply(new BigDecimal(InterestConfig.SERVICE_FEE)).setScale(2, BigDecimal.ROUND_HALF_UP));//预计服务费
		
		fac.setPredictInterest((new BigDecimal(bcrz_money).multiply(new BigDecimal(InterestConfig.EXPIRE_RATE)).multiply(new BigDecimal(repayDays))).setScale(2, BigDecimal.ROUND_HALF_UP));//预计利息
		
		fac.setExpireRate(new BigDecimal(InterestConfig.EXPIRE_RATE).doubleValue());//到期利率
		
		fac.setExtendRate(new BigDecimal(InterestConfig.OVERDUE_RATE).doubleValue());
		/**
		 * //银行账号
		**/
		
		 List<AcctMgt> li =	acctMgtService.findListByOrgGroupId(((UserExtendBean)Utils.securityUtil.getUserDetails()).getMuser().getMmbId());
		
		 for(AcctMgt mgt:li){
			 if(mgt.getAcctTypeName().equals("附属户")){
				 fac.setDistributorTheoreticalBank(mgt.getBankAcct());//供应商虚拟户
				 fac.setBlocAccount(mgt.getMasterAcct());//集团主账号
			 }else if(mgt.getAcctTypeName().equals("一般户")){
				 fac.setDistributorGeneralBank(mgt.getBankAcct());//供应商一般户
				 fac.setDistributorName(mgt.getAcctName());//供应商名
			 }
		 }
		 
		 li =	acctMgtService.findListByOrgGroupId(factoringId);
		 for(AcctMgt mgt:li){
			 if(mgt.getAcctTypeName().equals("附属户")){
					fac.setMemberAccount(mgt.getBankAcct());//放贷会员账号（账号）
					fac.setAcctName(mgt.getAcctName());
			 }else if(mgt.getAcctTypeName().equals("一般户")){
			 }
		 }
		
		fac.setProposerName(((UserExtendBean)Utils.securityUtil.getUserDetails()).getUsername());
		
		//根据登录名查询id
//		String proposerId = userService.getByName(Utils.securityUtil.getUserDetails().getUsername().toString()).getUserId();
	    fac.setProposerId(((UserExtendBean)Utils.securityUtil.getUserDetails()).getUserId());
	    fac.setProposerDate(new Date());
	    
	    fac.setApplyOrganizationId(((UserExtendBean)Utils.securityUtil.getUserDetails()).getMuser().getMmbId());
		
		return fac;
	}
	
	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	
	public List<FacFinancing> getFlist() {
		return Flist;
	}

	public void setFlist(List<FacFinancing> flist) {
		Flist = flist;
	}
	
}
