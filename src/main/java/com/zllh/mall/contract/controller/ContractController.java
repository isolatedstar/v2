package com.zllh.mall.contract.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zllh.mall.common.model.*;
import com.zllh.mall.mmbmmanage.service.IMmbBankAccountService;
import com.zllh.mall.mmbmmanage.service.IMmbWarehouseService;
import com.zllh.utils.common.DateUtil;
import com.zllh.utils.common.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zllh.base.controller.BaseController;
import com.zllh.common.common.model.model_extend.UserExtendBean;
import com.zllh.mall.contract.service.ICtractService;
import com.zllh.mall.goods.service.GoodsService;
import com.zllh.mall.mmbmmanage.service.IMMBService;
import com.zllh.mall.mmbmmanage.service.IMmbRelationshipService;
import com.zllh.utils.base.Utils;
import com.zllh.utils.common.UUIDCreater;

/**
 * @ClassName: ContractController
 * @Description: 协议管理
 * @author luobo
 * @date 2016-03-08 下午1:50:35
 */
@Controller
@RequestMapping("/contract")
public class ContractController extends BaseController {

	@Autowired
	private IMmbRelationshipService mmbRelaService;// 会员关系service

	@Autowired
	private ICtractService contractService;

	@Autowired
	private IMMBService mmbService;

	@Autowired
	private GoodsService goodsService;

	@Autowired
	private IMmbBankAccountService mmbBankAccountService;//银行账号

	@Autowired
	private IMmbWarehouseService mmbWarehouseService; //收发货地址

	/**
	 * 查询当前会员的所有关系会员信息
	 *
	 * @return
	 */
	@RequestMapping("/queryAllRelaMmbsByMmbId")
	public @ResponseBody
	List<MtMemberRelationship> queryAllRelaMmbsByMmbId() {
		List<MtMemberRelationship> list = null;
		String mmbid = request.getParameter("mmbId");
		if (!StringUtils.isBlank(mmbid)) {
			list = mmbRelaService.queryMmbRelationshipByMid(mmbid);
		}
		return list;
	}

	/**
	 * 进入待审核协议页面
	 *
	 * @return
	 */
	@RequestMapping("/toPendingCtr")
	public String toPendingCtr() {
		// 获取当前会员信息传入主页面供查询使用
		UserExtendBean user = (UserExtendBean) this.session.getAttribute("user");
		if (user != null) {
			MtMuser muser = user.getMuser();
			String mmbId = muser.getMmbId();
			// 会员id存入页面
			if (user != null){
				request.setAttribute("mmbId", mmbId);
			}

		}

		return "mall/contract/contract_pending_contract";
	}

	/**
	 * 进入提交合作协议页面
	 *
	 * @return
	 */
	@RequestMapping("/toSubmitCtr")
	public String toSubmitCtr() {
		// 获取当前会员信息传入主页面供查询使用
		UserExtendBean user = (UserExtendBean) this.session.getAttribute("user");
		if (user != null) {
			MtMuser muser = user.getMuser();
			String mmbId = muser.getMmbId();
			// 会员id存入页面
			if (user != null){
				request.setAttribute("mmbId", mmbId);

				//取出默认 当前会员的买关系的会员
				List<MtMemberRelationship> relaMmbList =  queryRelaMmbList(mmbId, 1);
				request.setAttribute("relaMmbList", relaMmbList);

			}

		}

		return "mall/contract/contract_submit_contract";
	}

	/**
	 * 进入执行中合作协议页面
	 * @return
	 */
	@RequestMapping("/toRunningContract")
	public String toRunningContract() {
		UserExtendBean user = (UserExtendBean) this.session.getAttribute("user");
		if (user != null) {
			MtMuser muser = user.getMuser();
			String mmbId = muser.getMmbId();
			// 会员id存入页面
			if (user != null){
				request.setAttribute("mmbId", mmbId);
			}
		}
		return "mall/contract/contract_running_contract";
	}

	/**
	 * 合作协议协议 查询
	 * @param pageType 3个页面类型（pending:待审批 submit:提交 running：执行中）
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/queryContractByPageType")
	public @ResponseBody
	Map<String, Object> queryContractByPageType(
			 String pageType) throws ParseException {
		Map<String, Object> returnMap = new HashMap<String, Object>();


		int pendingStatus  = 0; //能查询到的协议状态
		List<Integer> pendingStatusList = new ArrayList<>();//能查询到的协议状态列表

		String mmbId = request.getParameter("mmbId");//当前会员ID

		if(StringUtil.isNull(mmbId)) {
			UserExtendBean user = (UserExtendBean) this.session.getAttribute("user");
			if (user != null) {
				MtMuser muser = user.getMuser();
				mmbId = muser.getMmbId();
			}
		}


		String type = request.getParameter("contractType"); //协议类型
		String name = request.getParameter("name"); //协议对方名称
		String contractStatus = request.getParameter("contractStatus"); //协议状态 执行中或 已终止



		type = (type == null || "".equals(type.trim()) ? "1" : type); //如果协议类型为空 则默认为采购协议


		if("pending".equals(pageType)){
			//待审批协议页面
			pendingStatus = "1".equals(type)? 2 : 1;//采购协议 则查询状态为“卖家确认” 销售协议则查询状态为“买家确认"

		}else if("submit".equals(pageType)){
			//提交合作协议页面
			//采购协议 则查询状态为“买家确认” “已废除” "同意终止" 状态  销售协议则查询状态为“买家确认"“已废除” "同意终止"状态
			if("1".equals(type)) {
				//采购协议
				pendingStatusList.add(1);
				pendingStatusList.add(4);
				//pendingStatusList.add(7);

			}else if("2".equals(type)){
				pendingStatusList.add(2);
				pendingStatusList.add(4);
				//pendingStatusList.add(7);

			}else{
				throw  new RuntimeException("无效的协议类型");
			}


		}else if("running".equals(pageType)){
			//执行中合作协议页面
			if("1".equals(type)||"2".equals(type)) {
				//协议状态为"执行中 3" 则查询状态为“卖家终止确认、双方确认、买方终止确认”
				//协议状态为"已终止 7" 则查询状态为“已终止”
				if("3".equals(contractStatus)){
					pendingStatusList.add(3);
					pendingStatusList.add(6);
					pendingStatusList.add(5);
				}else if("7".equals(contractStatus)){
					pendingStatusList.add(7);
				}

			}else{
				throw  new RuntimeException("无效的协议类型");
			}
		}else{
			throw  new RuntimeException("无效的页面标示类型");
		}

		// 分页查询数据map
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mmbId", mmbId);
		map.put("type", Integer.parseInt(type));
		map.put("pendingStatus", pendingStatus);
		if(pendingStatusList.size() > 0){
			map.put("pendingStatusList", pendingStatusList);
			map.remove("pendingStatus");
		}
		map.put("name", name);

		int count = contractService.queryContracts(map).size();

		// 分页数据初始化
		int pageNo = getPageNo();
		int pageSize = getPageSize();

		// 接收页面参数并传递给service
		map.put("startFirst", pageNo);
		map.put("startEnd", pageSize);

		List<MtCtr> contracts = contractService.queryContracts(map);
		// total 和rows 供bootstrap table 分页使用
		returnMap.put("total", count);
		returnMap.put("mmbId", mmbId);
		returnMap.put("rows", contracts);
		returnMap.put("type", type);


		return returnMap;
	}

	/**
	 * 根据当前会员ID 和协议类型得到关系会员列表
	 * @param mmbId
	 * @param contractType
	 * @return
	 */
	@RequestMapping("/queryRelaMmbList")
	@ResponseBody
	public List<MtMemberRelationship> queryRelaMmbList(String mmbId,Integer contractType){

		Map<String,Object> contractMap = new HashMap<String,Object>();

		contractType = contractType == null ? 1:contractType;

		contractMap.put("mmbId",mmbId);
		contractMap.put("contractType",contractType);

		List<MtMemberRelationship> list = null;
		list = mmbRelaService.getMmbRealByContract(contractMap);

		return list;

	}

	/**
	 * 进入新增协议页面
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping("/toAddContractPage")
	public String toAddContractPage(Model model) {

		String mmbId = request.getParameter("mmbId"); //当前会员ID
		String relaMmbId = request.getParameter("relaMmbId"); //协议对方会员ID
		String type = request.getParameter("contractType"); //协议类型

		// 获取到的参数不为空的情况下分别查询会员信息
		if (!StringUtils.isBlank(mmbId) && !StringUtils.isBlank(relaMmbId) && !StringUtils.isBlank(type)) {

			List<MtMmbWarehouse> listWareHouse = new ArrayList<>(); //收发货地址
			List<MtMmbBankAccount> listBank = new ArrayList<>(); //收付款账号

			listWareHouse = mmbWarehouseService.getMmbWareHouseByMmbId(mmbId);
			listBank = mmbBankAccountService.getMmbBankAccountByMmbId(mmbId);


			// 查询会员信息返回新增页面显示
			MtMember member1 = new MtMember(); //采购方
			MtMember member2 = new MtMember(); //供货方
			if("1".equals(type)){
				 member1 = mmbService.queryMmbById(mmbId);
				 member2 = mmbService.queryMmbById(relaMmbId);

			}else if("2".equals(type)){
				 member1 = mmbService.queryMmbById(relaMmbId);
				 member2 = mmbService.queryMmbById(mmbId);
			}

			model.addAttribute("mmb", member1);
			model.addAttribute("mmb1", member2);
			model.addAttribute("type", type);
			model.addAttribute("workerName", Utils.securityUtil.getUser().getUserName());
			model.addAttribute("addressList", listWareHouse);
			model.addAttribute("bankList", listBank);
			model.addAttribute("relaMmbId", relaMmbId);
			model.addAttribute("mmbId", mmbId);
		}
		return "mall/contract/contract_add";
	}



	/**
	 * 创建合作协议
	 * @param MtCtr
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/creatCTR")
	@ResponseBody
	public int creatCTR(MtCtr MtCtr) throws ParseException {


		String id = UUIDCreater.getUUID();
		String userTime = request.getParameter("userTime");
		String[] goodsForm = request.getParameterValues("goods");


		if (goodsForm == null || goodsForm.length == 0) {
			logger.info("商品为空！");
		} else {
			List list = Arrays.asList(goodsForm);
			for (int i = 0; i < list.size(); i++) {
				MtCtrCtg MtCtrCtg = new MtCtrCtg();
				logger.info("得到的商品大类id" + list.get(i).toString());
				MtCtrCtg.setId(UUIDCreater.getUUID());
				MtCtrCtg.setCtrId(id);
				MtCtrCtg.setCtgId(list.get(i).toString());
				int j = contractService.creatGoods(MtCtrCtg);
			}
		}
		// 还款日期的初始时间
		String predictRefundDate_start = "";
		String predictRefundDate_end = "";
		if (userTime != null) {
			if (userTime != "") {
				predictRefundDate_start = userTime.substring(0, 10);
				predictRefundDate_end = userTime.substring(13, 23);

				MtCtr.setStartTime(new SimpleDateFormat("yyyy-MM-dd").parse(predictRefundDate_start));
				MtCtr.setEndTime(new SimpleDateFormat("yyyy-MM-dd").parse(predictRefundDate_end));
			}
		}

		MtCtr.setId(id);

		// 采购协议
		if (MtCtr.getBusType().equals(1)) {
			MtCtr.setContractStatus(1); //买家确认
		} else if (MtCtr.getBusType().equals(2)) {
			MtCtr.setContractStatus(2);//卖家确认
		}
		MtCtr.setCreateUser(Utils.securityUtil.getUser().getUserId());
		MtCtr.setCreateTime(new Date());

		MtCtr.setOperateUser(Utils.securityUtil.getUser().getUserId());
		MtCtr.setOperateTime(new Date());

		return  contractService.creatCTR(MtCtr);
	}

	/**
	 * 编辑合作协议和同意合作协议之前检查 自己是否已经配置了银行账号和收发货地址
	 * @return
	 */
	@RequestMapping("/checkBankAndWarehouseOfSelf")
	@ResponseBody
	public Map<String,Object> checkBankAndWarehouseOfSelf(){

		String check = "success";
		String msg = "";
		Map<String,Object> checkMap = new HashMap<>();
		String mmbId = "";
		UserExtendBean  user = (UserExtendBean) this.session.getAttribute("user");
		if(user != null){
			MtMuser mtMuser = user.getMuser();
			mmbId = mtMuser.getMmbId();
		}

		List<MtMmbWarehouse> selfAddressList = new ArrayList<>(); //地址
		List<MtMmbBankAccount>  selfBankList = new ArrayList<>(); //账号

		selfAddressList= mmbWarehouseService.getMmbWareHouseByMmbId(mmbId);
		selfBankList = mmbBankAccountService.getMmbBankAccountByMmbId(mmbId);

		if(selfAddressList == null || selfAddressList.size() == 0 ){
			check = "fail";
			msg = "请您创建收发货地址！";
		}
		if(selfBankList == null || selfBankList.size() == 0 ){
			check = "fail";
			msg = "请您创建银行账号！";
		}
		if((selfAddressList == null || selfAddressList.size() == 0 ) && (selfBankList == null || selfBankList.size() == 0)){
			check = "fail";
			msg = "请您创建收发货地址和银行账号！";
		}

		checkMap.put("checkStatus",check);
		checkMap.put("msg",msg);

		return checkMap;
	}

	/**
	 * 跳转到编辑合作协议页面
	 * @param id  协议ID
	 * @param contractType  协议类型
	 * @return
	 */
	@RequestMapping("/toUpdateContractPage")
	public String toUpdateContractPage(Model model,String id,String contractType){

		String userTime = "";//生效时间
		String mmbId = "";
		UserExtendBean  user = (UserExtendBean) this.session.getAttribute("user");
		if(user != null){
			MtMuser mtMuser = user.getMuser();
			mmbId = mtMuser.getMmbId();
		}

		MtCtr mtCtr = contractService.getContractById(id,contractType);

		userTime = DateUtil.formatDate(mtCtr.getStartTime())+" - "+ DateUtil.formatDate(mtCtr.getEndTime());

		MtMember memberBuy = mmbService.queryMmbById(mtCtr.getFirstMmbId());  //采购方
		MtMember memberSell = mmbService.queryMmbById(mtCtr.getSecondMmbId()); //供货方


		List<MtMmbWarehouse> sendGoodsAddressList = new ArrayList<>(); //发货地址
		List<MtMmbWarehouse> getGoodsAddressList = new ArrayList<>(); //收货地址

		List<MtMmbBankAccount> payMoneyBankList = new ArrayList<>(); //付款账号
		List<MtMmbBankAccount> getMoneyBankList = new ArrayList<>(); //收款账号

		//采购方
		getGoodsAddressList = mmbWarehouseService.getMmbWareHouseByMmbId(mtCtr.getFirstMmbId());
		payMoneyBankList = mmbBankAccountService.getMmbBankAccountByMmbId(mtCtr.getFirstMmbId());

		//销售方
		sendGoodsAddressList = mmbWarehouseService.getMmbWareHouseByMmbId(mtCtr.getSecondMmbId());
		getMoneyBankList = mmbBankAccountService.getMmbBankAccountByMmbId(mtCtr.getSecondMmbId());




		List<MtCtrCtg> ctrCtgList = new ArrayList<MtCtrCtg>(); //得到协中的商品大类列表
		ctrCtgList = contractService.getGoodsListByContractId(id);


		model.addAttribute("mmb", memberBuy);
		model.addAttribute("mmb1", memberSell);

		model.addAttribute("getGoodsAddressList", getGoodsAddressList);
		model.addAttribute("payMoneyBankList", payMoneyBankList);

		model.addAttribute("sendGoodsAddressList", sendGoodsAddressList);
		model.addAttribute("getMoneyBankList", getMoneyBankList);

		model.addAttribute("mtCtr",mtCtr);
		model.addAttribute("type",contractType);
		model.addAttribute("ctrCtgList",ctrCtgList);
		model.addAttribute("workerName", Utils.securityUtil.getUser().getUserName());
		model.addAttribute("userTime",userTime);

		return "mall/contract/contract_edit";
	}

	/**
	 * 跳转到合作协议详情页面
	 * @param id  协议ID
	 * @param contractType  协议类型
	 * @return
	 */
	@RequestMapping("/toContractDetailPage")
	@ResponseBody
	public Map<String,Object> toContractDetailPage(Model model,String id,String contractType){

		Map<String,Object> resultMap = new HashMap<>();
		String userTime = "";//生效时间
		String mmbId = "";
		UserExtendBean  user = (UserExtendBean) this.session.getAttribute("user");
		if(user != null){
			MtMuser mtMuser = user.getMuser();
			mmbId = mtMuser.getMmbId();
		}

		MtCtr mtCtr = contractService.getContractById(id,contractType);

		userTime = DateUtil.formatDate(mtCtr.getStartTime())+" - "+ DateUtil.formatDate(mtCtr.getEndTime());

		MtMember memberBuy = mmbService.queryMmbById(mtCtr.getFirstMmbId());  //采购方
		MtMember memberSell = mmbService.queryMmbById(mtCtr.getSecondMmbId()); //供货方


		List<MtMmbWarehouse> sendGoodsAddressList = new ArrayList<>(); //发货地址
		List<MtMmbWarehouse> getGoodsAddressList = new ArrayList<>(); //收货地址

		List<MtMmbBankAccount> payMoneyBankList = new ArrayList<>(); //付款账号
		List<MtMmbBankAccount> getMoneyBankList = new ArrayList<>(); //收款账号

		//采购方
		getGoodsAddressList = mmbWarehouseService.getMmbWareHouseByMmbId(mtCtr.getFirstMmbId());
		payMoneyBankList = mmbBankAccountService.getMmbBankAccountByMmbId(mtCtr.getFirstMmbId());

		//销售方
		sendGoodsAddressList = mmbWarehouseService.getMmbWareHouseByMmbId(mtCtr.getSecondMmbId());
		getMoneyBankList = mmbBankAccountService.getMmbBankAccountByMmbId(mtCtr.getSecondMmbId());




		List<MtCtrCtg> ctrCtgList = new ArrayList<MtCtrCtg>(); //得到协中的商品大类列表
		ctrCtgList = contractService.getGoodsListByContractId(id);


		resultMap.put("buyer", memberBuy);
		resultMap.put("seller", memberSell);

		resultMap.put("getGoodsAddressList", getGoodsAddressList);
		resultMap.put("payMoneyBankList", payMoneyBankList);

		resultMap.put("sendGoodsAddressList", sendGoodsAddressList);
		resultMap.put("getMoneyBankList", getMoneyBankList);

		resultMap.put("mtCtr", mtCtr);
		resultMap.put("type", contractType);
		resultMap.put("ctrCtgList", ctrCtgList);
		resultMap.put("workerName", Utils.securityUtil.getUser().getUserName());
		resultMap.put("userTime", userTime);

		return resultMap;
	}

	/**
	 * 编辑合作协议
	 * @param MtCtr
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/updateCTR")
	@ResponseBody
	public int updateCTR(MtCtr MtCtr) throws ParseException {

		String mmbId ="";//当前会员ID
		UserExtendBean  user = (UserExtendBean) this.session.getAttribute("user");
		if(user != null){
			MtMuser mtMuser = user.getMuser();
			mmbId = mtMuser.getMmbId();
		}

		String userTime = request.getParameter("userTime"); //生效时间
		String[] goodsForm = request.getParameterValues("goods"); //商品列表
		if (goodsForm == null || goodsForm.length == 0) {
			logger.info("商品为空！");
		} else {
			//先删除协议中的商品大类
			contractService.deleteCtrCtgByContractId(MtCtr.getId());
			List list = Arrays.asList(goodsForm);
			for (int i = 0; i < list.size(); i++) {
				MtCtrCtg MtCtrCtg = new MtCtrCtg();
				logger.info("得到的商品id" + list.get(i).toString());
				MtCtrCtg.setId(UUIDCreater.getUUID());
				MtCtrCtg.setCtrId(MtCtr.getId());
				MtCtrCtg.setCtgId(list.get(i).toString());
				int j = contractService.creatGoods(MtCtrCtg);
			}
		}
		// 还款日期的初始时间
		String predictRefundDate_start = "";
		String predictRefundDate_end = "";
		if (userTime != null) {
			if (userTime != "") {
				predictRefundDate_start = userTime.substring(0, 10);
				predictRefundDate_end = userTime.substring(13, 23);

				MtCtr.setStartTime(new SimpleDateFormat("yyyy-MM-dd").parse(predictRefundDate_start));
				MtCtr.setEndTime(new SimpleDateFormat("yyyy-MM-dd").parse(predictRefundDate_end));
			}
		}


		if(mmbId.equals(MtCtr.getFirstMmbId())){
			MtCtr.setContractStatus(1); //买家确认
		}else if(mmbId.equals(MtCtr.getSecondMmbId())){
			MtCtr.setContractStatus(2);//卖家确认
		}

		MtCtr.setOperateUser(Utils.securityUtil.getUser().getUserId());
		MtCtr.setOperateTime(new Date());

		return  contractService.updateContracts(MtCtr);
	}

	/**
	 * 显示合作协议模态框
	 * @param id
	 * @param contractType
	 * @param currentStatus
	 * @return
	 */
	@RequestMapping("/showAgreeModal")
	@ResponseBody
	private  Map<String,Object> showAgreeModal(String id , String  contractType,String currentStatus){

		String mmbId = "";
		UserExtendBean  user = (UserExtendBean) this.session.getAttribute("user");
		if(user != null){
			MtMuser mtMuser = user.getMuser();
			mmbId = mtMuser.getMmbId();
		}

		MtCtr mtCtr = contractService.getContractById(id,contractType);//当前合作协议

		List<MtMmbWarehouse> listWareHouse = new ArrayList<>(); //收发货地址
		List<MtMmbBankAccount> listBank = new ArrayList<>(); //收付款账号

		String bankAccountNo = "";//银行账号
		String addressName = "";//地址

		if("1".equals(contractType)){
			bankAccountNo = mtCtr.getPayerCode();
			addressName = mtCtr.getGetgoodsAddress();
		}else{
			bankAccountNo = mtCtr.getGetmoneyCode();
			addressName = mtCtr.getSendgoodsAddress();
		}

		listWareHouse = mmbWarehouseService.getMmbWareHouseByMmbId(mmbId);
		listBank = mmbBankAccountService.getMmbBankAccountByMmbId(mmbId);

		Map<String,Object> resultMap = new HashMap<>();

		resultMap.put("addressList", listWareHouse);
		resultMap.put("bankList", listBank);
		resultMap.put("type", contractType);
		resultMap.put("ctrId", id);

		resultMap.put("bankAccountNo", bankAccountNo);
		resultMap.put("addressName", addressName);
		resultMap.put("currentStatus", currentStatus);

		return resultMap;
	}

	/**
	 * 同意合作协议
	 * @param id
	 * @param contractType
	 * @param address
	 * @param
	 * @return
	 */

	@RequestMapping("/agreeContract")
	@ResponseBody
	public Map<String,Object> agreeContract(
			String id,
			String contractType,
			String address,
			String bankAccountCode,
			String bankAccountName){

		Map<String,Object> resultMap = new HashMap<>();

		resultMap.put("id",id);
		resultMap.put("address",address);
		resultMap.put("bankAccountCode",bankAccountCode);
		resultMap.put("bankAccountName",bankAccountName);
		resultMap.put("contractType",contractType);
		resultMap.put("nextStatus",3);//双方都同意

		int agreeNum = contractService.agreeContract(resultMap);

		if( agreeNum == 1){
			resultMap.put("msg","操作成功！");
		}else{
			resultMap.put("msg","操作失败！");
		}

		return  resultMap;

	}

	@RequestMapping("/lockCTR")
	@ResponseBody
	public int lockCTR(MtCtr MtCtr) {
		int j = 0;
		String[] contract = request.getParameterValues("balanceCheckBox");
		if (contract != null || contract.length != 0) {
			for (int i = 0; i < contract.length; i++) {
				MtCtr.setId(contract[i]);
				j = contractService.lockCTR(MtCtr);
			}
		}
		return j;
	}

	//跳转到创建订单页面
	@RequestMapping("/toCreateOrder")
	public String toCreateOrder(Model model,String id , String  contractType) {

		String userTime = "";//生效时间
		String mmbId = "";
		UserExtendBean  user = (UserExtendBean) this.session.getAttribute("user");
		if(user != null){
			MtMuser mtMuser = user.getMuser();
			mmbId = mtMuser.getMmbId();
		}

		MtCtr mtCtr = contractService.getContractById(id,contractType);

		userTime = DateUtil.formatDate(mtCtr.getStartTime())+" - "+ DateUtil.formatDate(mtCtr.getEndTime());

		MtMember memberBuy = mmbService.queryMmbById(mtCtr.getFirstMmbId());  //采购方
		MtMember memberSell = mmbService.queryMmbById(mtCtr.getSecondMmbId()); //供货方


		List<MtMmbWarehouse> listWareHouse = new ArrayList<>(); //收发货地址
		List<MtMmbBankAccount> listBank = new ArrayList<>(); //收付款账号

		listWareHouse = mmbWarehouseService.getMmbWareHouseByMmbId(mmbId);
		listBank = mmbBankAccountService.getMmbBankAccountByMmbId(mmbId);


		List<MtCtrCtg> ctrCtgList = new ArrayList<MtCtrCtg>(); //得到协中的商品大类列表
		ctrCtgList = contractService.getGoodsListByContractId(id);


		model.addAttribute("mmb", memberBuy);
		model.addAttribute("mmb1", memberSell);
		model.addAttribute("mmbId",mmbId);//当前会员ID

		model.addAttribute("addressList", listWareHouse);
		model.addAttribute("bankList", listBank);

		model.addAttribute("mtCtr",mtCtr);
		model.addAttribute("type",contractType);
		model.addAttribute("ctrCtgList",ctrCtgList);
		model.addAttribute("workerName", Utils.securityUtil.getUser().getUserName());
		model.addAttribute("userTime",userTime);

		return "mall/contract/contract_create_order";
	}



	/**
	 *  生成订单 准备数据 传给---->createOrder.js
	 * @param mtCtrId
	 * @param contractType
	 * @return
	 */
	@RequestMapping("/getGoodsData")
	@ResponseBody
	public Map getGoodsData( String mtCtrId, String contractType) {
		@SuppressWarnings("rawtypes")
		Map<String, Object> map = new HashMap();
		String[] goodss = request.getParameterValues("goods");
		List<MtGoods> list = new ArrayList<MtGoods>();
		if (goodss != null) {
			if (goodss.length != 0) {
				for (int i = 0; i < goodss.length; i++) {
					MtGoods mt = goodsService.searchGoodById(goodss[i]);
					mt.setMin(mt.getMinPrice());
					mt.setMax(mt.getMaxPrice());
					list.add(mt);
				}
			}
		}

		//合作协议
		MtCtr mtCtr = contractService.getContractById(mtCtrId,contractType);

		MtMember buyMmb_ = mmbService.queryMmbById(mtCtr.getFirstMmbId());
		MtMember sellMmb_ = mmbService.queryMmbById(mtCtr.getSecondMmbId());

		map.put("payMoneyBank", mmbBankAccountService.getBankAccountByAccountNo(mtCtr.getPayerCode())); //付款银行
		map.put("getMoneyBank", mmbBankAccountService.getBankAccountByAccountNo(mtCtr.getGetmoneyCode())); // 收款银行

		map.put("buyMmb_", buyMmb_);  //采购会员
		map.put("sellMmb_", sellMmb_); //销售会员

		map.put("payMoneyBankList", mmbBankAccountService.getMmbBankAccountByMmbId(mtCtr.getFirstMmbId()));//付款银行列表
		map.put("getMoneyBankList", mmbBankAccountService.getMmbBankAccountByMmbId(mtCtr.getSecondMmbId()));//收款银行列表

		map.put("buyersAddressId", mmbWarehouseService.getAddressIdByAddress(mtCtr.getGetgoodsAddress(),mtCtr.getFirstMmbId()));//收货地址id
		map.put("sellersAddressId", mmbWarehouseService.getAddressIdByAddress(mtCtr.getSendgoodsAddress(),mtCtr.getSecondMmbId()));//发货地址ID

		map.put("getGoodsAddressList", mmbWarehouseService.getMmbWareHouseByMmbId(mtCtr.getFirstMmbId()));//发货地址
		map.put("sendGoodsAddressList", mmbWarehouseService.getMmbWareHouseByMmbId(mtCtr.getSecondMmbId()));//收货地址


		map.put("total", goodss.length);

		for(int i = 0;i<list.size();i++){
			list.get(i).setGoodsName(list.get(i).getName());

			//生成订单所需
			list.get(i).setPrice("");
			list.get(i).setGoodsNum("");
			list.get(i).setMoney("");

			list.get(i).setBuyersId(buyMmb_.getId());
			list.get(i).setBuyersName(buyMmb_.getMmbFname());
			list.get(i).setSellersId(sellMmb_.getId());
			list.get(i).setSellersName(sellMmb_.getMmbFname());

		}

		map.put("list", list);
		return map;
	}


	@RequestMapping("/getTree")
	@ResponseBody
	public List<MtCtr> getTree() {
		return contractService.getTradeName(Utils.securityUtil.getUser().getUserId());
	}


	/**
	 * 操作合作协议
	 * @param id   	协议ID
	 * @param operateType   操作类型
	 * @param currentStatus   协议当前状态
	 * @param contractType   协议类型
	 * @return
	 */
	@RequestMapping("/operateContract")
	@ResponseBody
	public Map<String,Object>  operateContract(
			@RequestParam String id,
			@RequestParam String operateType,
			@RequestParam String currentStatus,
			@RequestParam String contractType){

		int currentStatuss = 0;
		if(!StringUtil.isNull(currentStatus)){
			currentStatuss = Integer.parseInt(currentStatus);
		}

		int nextStatus = contractService.getNextStatus(currentStatuss,contractType ,operateType);

		Map<String,Object> nextMap = new HashMap<>();
		nextMap.put("id",id);
		nextMap.put("nextStatus",nextStatus);
		int result = contractService.updateContractToNextStatus(nextMap);

		Map<String, Object> resultMap = new HashMap();
		if(result == 1){
			resultMap.put("msg","操作成功！");
		}else{
			resultMap.put("msg","操作失败！");

		}

		return resultMap;
	}


}
