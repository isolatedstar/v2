package com.zllh.factoring.assure.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zllh.base.controller.BaseController;
import com.zllh.common.common.model.model_extend.UserExtendBean;
import com.zllh.factoring.assure.service.AssureService;
import com.zllh.factoring.common.interest.InterestConfig;
import com.zllh.factoring.common.model.FacGuaranteeBill;
import com.zllh.mall.common.dao.MtMemberMapper;
import com.zllh.mall.common.dao.MtMuserMapper;
import com.zllh.mall.common.dao.MtOrderMapper;
import com.zllh.mall.common.model.MtMember;
import com.zllh.mall.common.model.MtOrder;
import com.zllh.mall.common.model.MtSettle;
import com.zllh.mall.order.service.IOrderService;
import com.zllh.mall.settle.service.ISettleService;
import com.zllh.payment.front.service.AcctMgtService;
import com.zllh.payment.model.AcctMgt;
import com.zllh.utils.base.Utils;
import com.zllh.utils.common.StringUtil;

/**
 * @ClassName: AssureController
 * @Description: 担保管理
 * @author JW
 * @date 2015年12月25日 上午9:41:02
 */
@Controller
@RequestMapping("/assureController")
public class AssureController extends BaseController{

	@Autowired
	private AssureService assureService;
	
	@Autowired
	private IOrderService orderService;
	
	@Resource
	private MtOrderMapper mtOrderMapper;
	
	@Autowired
	private ISettleService settleService;
	
	@Autowired
	private MtMuserMapper muserMapper;
	
	@Autowired
	private MtMemberMapper mtMemberMapper;
	
	@Autowired
	private AcctMgtService acctMgtService;	
	
	private String jsonData;
	
	/**
	 * @Title: AssureRegister 
	 * @author JW
	 * @Description: 登记
	 * @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/assureRegisterNoFilter")
	public @ResponseBody String assureRegisterNoFilter(String guaranteetId,String discountRate){//担保单号和贴现率
		
		if(StringUtil.isNull(discountRate)){
			discountRate = String.valueOf(InterestConfig.PERCENT);
		}
		//修改担保单数据
		String result = assureService.executeAssureRegister(guaranteetId,discountRate);
		
		jsonData = JSON.toJSONString(result);
		
		return jsonData;
	}
	
	/**
	 * @Title: Signature 
	 * @author dongll
	 * @Description: 签章
	 * @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="/signature",method=RequestMethod.POST)
	public String signatureNoFilter(){
		/** 根据guaranteetId查询结款单
		 */
		String guaranteetId = request.getParameter("guaranteetId");
		
		/** 跟据guaranteetId查询 打开pdf
		 */
		int i = assureService.getThirdpartyAffirm(guaranteetId);
		
		if(i == 1){
			jsonData = JSON.toJSONString("已签章，请刷新页面！");
		}else{
			jsonData = JSON.toJSONString("http://");
		}
		return jsonData;
	}
	
	/**
	 * @Title: Signature 
	 * @author dongll
	 * @Description: 签章页面
	 * @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="/signatureNoFilter",method=RequestMethod.GET)
	public String signatureNoFilter(String guaranteetId){
		//根据guaranteetId查询结款单
		//判断结款单是否存在，判断结款单第三方否为未确认
			//if yes(未确认) 
				//更新结款单乐观锁值()
				//返回签章页面
			//else no(已确认)
				//返回错误提示页面
		 return "assure/assure_signature";
	}
	
	/**
	 * @Title: turnDown 
	 * @Description: 驳回
	 * @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="/turnDownNoFilter")
	public @ResponseBody String turnDownNoFilter(){
		/**
		 * 取页面参数
		 */
		String ids = request.getParameter("guaranteetIds");
		List<String> guaranteetIds = Arrays.asList(ids.split(","));
		
		/**
		 * 循环guaranteetIds
		 */
		List<String> refuseFail = new ArrayList<String>();
		for(String guaranteetId:guaranteetIds){
			/**
			 * 根据guaranteetId调用方法 修改状态 
			 */
			boolean bool = assureService.executerefuse(guaranteetId);
			/**
			 * 记录驳回失败的担保单号 
			 */
			if(bool == false){
				refuseFail.add(guaranteetId);
			}
		}
		/**
		 * 返回结果
		 */
		jsonData = JSON.toJSONString(refuseFail);
		return jsonData;
	}
	
	/**
	 * @Title: revoke 
	 * @Description: 撤销
	 * @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="/revokeNoFilter")
	public @ResponseBody String revokeNoFilter(){
		/**
		 * 取页面参数
		 */
		String ids = request.getParameter("guaranteetIds");
		List<String> guaranteetIds = Arrays.asList(ids.split(","));
		
		/**
		 * 循环guaranteetIds
		 */
		List<String> refuseFail = new ArrayList<String>();
	
		for(String guaranteetId:guaranteetIds){
			/**
			 * 根据guaranteetId调用方法进行判断 注销担保资源
			 */
			boolean bool = assureService.executerevoke(guaranteetId);
			/**
			 * 记录驳回失败的担保单号 
			 */
			if(bool == false){
				refuseFail.add(guaranteetId);
			}
		}
		/**
		 * 返回结果
		 */
		jsonData = JSON.toJSONString(refuseFail);
		return jsonData;
	}
	
	/**
	 * @Title: ThirdSubrogationStamp 
	 * @author JW
	 * @Description: 商城申请担保登记接口
	 * @return
	 * @return String
	 * @throws
	 */
	public String thirdSubrogationStamp(){
		
		//商城传过来的数据 暂时没有
		FacGuaranteeBill gua = new FacGuaranteeBill();
		
		String result = assureService.thirdSubrogationStamp(gua);
		
		return result;
	}
	
	/**
	 * @Title: mallCallback 
	 * @author JW
	 * @Description: 商城回调
	 * @return
	 * @return String
	 * @throws
	 */
	public String mallCallback(){
		return null;
	}
	
	/**
	 * 查询担保记录列表
	 * @param guaranteetId   结款单号
	 * @param payerName      付款方
	 * @param payeeName  首款方
	 * @param status         状态
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException 
	 */ 
	//待登记(本人)
	@RequestMapping("/toAssureNoFilter")
	public String toAssureNoFilter(String payerName,String payeeName,String type,String page) throws UnsupportedEncodingException{
		Map<String,Object> map = new HashMap<String,Object>();
		
		//获取用户id
		String payeeId = ((UserExtendBean)Utils.securityUtil.getUserDetails()).getMuser().getMmbId();
		
		type="1";//待登记状态
		map.put("payeeId", payeeId);
		map.put("payerName", payerName);
		map.put("payeeName", payeeName);
		
		if(page!=null){
			map.put("page", Integer.parseInt(page));
		}
		
		map.put("status", type);
		
		//查询担保收款方id==登录者id，状态 == 1 的列表list
		List<FacGuaranteeBill> list = assureService.getFacGuaranteeBill(map);
		
	    List<MtMember> mebers = mtMemberMapper.findAllMtMember();
		
		/** 取融资信息总条数，计算显示页数 **/
		int length = assureService.getAllLength(map);
		if(length%10>0){
			length = length/10+1;
		}else{
			length = length/10;
		}
		
		request.setAttribute("list", list);
		request.setAttribute("mebers", mebers);
		request.setAttribute("length", length);
		request.setAttribute("payerName", payerName);
		request.setAttribute("payeeName", payeeName);
		request.setAttribute("status", type);
		
		if(page == null){
			request.setAttribute("clas", 1);
		}else
			request.setAttribute("clas", Integer.parseInt(page));
		
		return "factoring/assure/assure_index";
	}
	//待登记(all)
	@RequestMapping("/toAssureNoFiltera")
	public String toAssureNoFiltera(String payerName,String payeeName,String type,String page) throws UnsupportedEncodingException{
		Map<String,Object> map = new HashMap<String,Object>();
		
		//获取用户id
		//String payeeId = ((UserExtendBean)Utils.securityUtil.getUserDetails()).getMuser().getMmbId();
		
		type="1";//待登记状态
		//map.put("payeeId", payeeId);
		map.put("payerName", payerName);
		map.put("payeeName", payeeName);
		
		if(page!=null){
			map.put("page", Integer.parseInt(page));
		}
		
		map.put("status", type);
		
		//查询担保收款方id==登录者id，状态 == 1 的列表list
		List<FacGuaranteeBill> list = assureService.getFacGuaranteeBill(map);
		
		List<MtMember> mebers = mtMemberMapper.findAllMtMember();
		
		/** 取融资信息总条数，计算显示页数 **/
		int length = assureService.getAllLength(map);
		if(length%10>0){
			length = length/10+1;
		}else{
			length = length/10;
		}
		
		request.setAttribute("list", list);
		request.setAttribute("mebers", mebers);
		request.setAttribute("length", length);
		request.setAttribute("payerName", payerName);
		request.setAttribute("payeeName", payeeName);
		request.setAttribute("status", type);
		
		if(page == null){
			request.setAttribute("clas", 1);
		}else
			request.setAttribute("clas", Integer.parseInt(page));
		
		return "factoring/assure/assure_indexa";
	}
	
	//已登记(本人)
	@RequestMapping("/toAssureNoFilter1")
	public String toAssureNoFilter1(String payerName,String payeeName,String type,String page) throws UnsupportedEncodingException{
		Map<String,Object> map = new HashMap<String,Object>();
		
		String payeeId = ((UserExtendBean)Utils.securityUtil.getUserDetails()).getMuser().getMmbId();
		type="2";//已登记状态
		map.put("payeeId", payeeId);
		map.put("payerName", payerName);
		map.put("payeeName", payeeName);
		
		if(page!=null){
			map.put("page", Integer.parseInt(page));
		}
		
		map.put("status", type);
		//查询担保收款方id==登录者id，状态 == 2 已登记列表
		List<FacGuaranteeBill> list = assureService.getFacGuaranteeBill(map);
		
		/** 取融资信息总条数，计算显示页数 **/
		int length = assureService.getAllLength(map);
		if(length%10>0){
			length = length/10+1;
		}else{
			length = length/10;
		}
		
		request.setAttribute("list", list);
		request.setAttribute("length", length);
		request.setAttribute("payerName", payerName);
		request.setAttribute("payeeName", payeeName);
		request.setAttribute("status", type);
		
		if(page == null){
			request.setAttribute("clas", 1);
		}else
			request.setAttribute("clas", Integer.parseInt(page));
		
		return "factoring/assure/assure_index1";
	}
	
	//已登记(all)
		@RequestMapping("/toAssureNoFilterb")
		public String toAssureNoFilterb(String payerName,String payeeName,String type,String page) throws UnsupportedEncodingException{
			Map<String,Object> map = new HashMap<String,Object>();
			
			//String payeeId = ((UserExtendBean)Utils.securityUtil.getUserDetails()).getMuser().getMmbId();
			type="2";//已登记状态
			//map.put("payeeId", payeeId);
			map.put("payerName", payerName);
			map.put("payeeName", payeeName);
			
			if(page!=null){
				map.put("page", Integer.parseInt(page));
			}
			
			map.put("status", type);
			//查询担保收款方id==登录者id，状态 == 2 已登记列表
			List<FacGuaranteeBill> list = assureService.getFacGuaranteeBill(map);
			
			/** 取融资信息总条数，计算显示页数 **/
			int length = assureService.getAllLength(map);
			if(length%10>0){
				length = length/10+1;
			}else{
				length = length/10;
			}
			
			request.setAttribute("list", list);
			request.setAttribute("length", length);
			request.setAttribute("payerName", payerName);
			request.setAttribute("payeeName", payeeName);
			request.setAttribute("status", type);
			
			if(page == null){
				request.setAttribute("clas", 1);
			}else
				request.setAttribute("clas", Integer.parseInt(page));
			
			return "factoring/assure/assure_indexb";
		}
	
	/**
	 * 根据结款单号查询 订单信息
	 */
	@RequestMapping("/getOrderDetail")
	public @ResponseBody String getOrderDetail(String guaranteetId){
		List<MtOrder> list_order = new ArrayList<MtOrder>();
		
		/**
		 * 根据guaranteetId查询订单id
		 */
		List<String> order_ids = settleService.getOrderIdByBalanceId(guaranteetId);
				
		
		MtOrder order = new MtOrder();
		
		/**
		 * 根据订单id 循环查询订单详情
		 */
		for(String order_id:order_ids){
			order = mtOrderMapper.selectByPrimaryKey(order_id);
			list_order.add(order);
		}
		
		jsonData = JSON.toJSONString(list_order);
		return jsonData;
	}
	
	/***
	 * 保存担保信息
	 */
	public boolean saveAssureMessage(MtSettle mt){
		//生成FacGuaranteeBill对象
		FacGuaranteeBill facGua = getFacGuaranteeBill(mt);
		
		int i = assureService.insertFacGua(facGua);
		
		if(i>0){
			return  true;
		}else{
			return  false;
		}
	}
	
	public FacGuaranteeBill getFacGuaranteeBill(MtSettle mt){
		FacGuaranteeBill gua_new = new FacGuaranteeBill();
		
		gua_new.setGuaranteeId(mt.getId());
		gua_new.setExpireDate(mt.getCtrTime());
		gua_new.setGuaranteeAmount(new BigDecimal(mt.getCtrMoney()));
		gua_new.setLock(1);
		gua_new.setPaymentAmount(new BigDecimal(mt.getGotMoney()));//已支付金额
		gua_new.setNonPayAmount(new BigDecimal(mt.getCtrMoney()).subtract(new BigDecimal(mt.getGotMoney())));
		gua_new.setOperationDate(new Date());
		
		gua_new.setPayeeId(mt.getMmbgetId());
		gua_new.setPayeeName(mtMemberMapper.queryMmbById(mt.getMmbgetId()).getMmbFname());
		gua_new.setPayerId(mt.getMmbpayId());
		gua_new.setPayerName(mtMemberMapper.queryMmbById(mt.getMmbpayId()).getMmbFname());
		
		if(mt.getStatusSign().equals("1")){
			gua_new.setGuaranteeAffirm(0);
			gua_new.setProposerAffirm(0);
		}else if(mt.getStatusSign().equals("2")){
			gua_new.setGuaranteeAffirm(0);
			gua_new.setProposerAffirm(1);//收款方
		}else if(mt.getStatusSign().equals("3")){
			gua_new.setGuaranteeAffirm(1);
			gua_new.setProposerAffirm(0);//收款方
		}else if(mt.getStatusSign().equals("4")){
			gua_new.setGuaranteeAffirm(1);
			gua_new.setProposerAffirm(1);//收款方
		}
		gua_new.setThirdpartyAffirm(0);//第三方是否签章
		gua_new.setStatus(0);
		
		//根据付款方id查询账号
		
		gua_new.setSchoolGeneralAccount("河北学校");//学校一般户
		gua_new.setSchoolTheoreticalAccount("3111810000800000006");//学校虚拟户
		
		//根据收款方id查询银行账号
		List<AcctMgt> li =	acctMgtService.findListByOrgGroupId(mt.getMmbpayId());
		
		for(AcctMgt at : li){
			if(at.getAcctTypeName().equals("附属户")){
				gua_new.setDistributorTheoreticalBank(at.getBankAcct());//小b虚拟户
			}else if(at.getAcctTypeName().equals("一般户")){
				gua_new.setDistributorGeneralBank(at.getBankAcct());//小b一般户
			}
		}
		gua_new.setDistributorName(li.get(0).getAcctName());//小b商户名
		
		gua_new.setBlocAccount(li.get(0).getMasterAcct());//集团账号
		
		gua_new.setUsableAmount(new BigDecimal(mt.getCtrMoney()).multiply(new BigDecimal(InterestConfig.PERCENT)).setScale(2,BigDecimal.ROUND_HALF_UP));
		
		return gua_new;
	}
	
	
	/**
	 * 查询银行账号
	 * @return
	 */
	@RequestMapping("/getBankAccNoFilter")
	public @ResponseBody String getBankAccNoFilter(){
		Map<String,Object> map = new HashMap<String,Object>();
		
		String mbId = request.getParameter("mbId");//获取传的参数（付款方name）
        
        if("".equals(mbId)) return null;
        
        /** 根据groupId查询银行账号   **/
        List<AcctMgt> li =  acctMgtService.findListByOrgGroupId(mbId);
        
        map.put("mbId", mbId);
        map.put("list", li);
        jsonData = JSON.toJSONString(map);
        
        return jsonData;
	}
	
	@RequestMapping("/getmember")
	public @ResponseBody String getmember(){
	    Map<String,Object> map = new HashMap<String,Object>();
	    List<MtMember> mebers = mtMemberMapper.findAllMtMember();
	    map.put("mebers", mebers);
	    jsonData = JSON.toJSONString(map);
	    return jsonData;
	}
	
	
	@RequestMapping("/addAssureMassageNoFilter")
	public @ResponseBody String addAssureMassageNoFilter(){
		/**  获取页面参数  **/
		List<FacGuaranteeBill> list_gua = JSON.parseArray(request.getParameter("jsonStr"),FacGuaranteeBill.class);
		
		/***  保存数据   **/
		int i = assureService.insertFacGua(list_gua.get(0));
		
		if(i>0){
			jsonData = JSON.toJSONString("保存成功！！！");
		}
		
		return jsonData;
	}
	
	
	public String getJsonData() { 
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
}
