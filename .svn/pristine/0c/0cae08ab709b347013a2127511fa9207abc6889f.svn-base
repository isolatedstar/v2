package com.zllh.mall.order.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zllh.base.controller.BaseController;
import com.zllh.common.common.model.model_extend.UserExtendBean;
import com.zllh.common.enums.BillsType;
import com.zllh.mall.common.model.MtGetpaymoney;
import com.zllh.mall.common.model.MtMmbBankAccount;
import com.zllh.mall.common.model.MtMmbWarehouse;
import com.zllh.mall.common.model.MtOrder;
import com.zllh.mall.common.model.MtOrdertitle;
import com.zllh.mall.mmbmmanage.service.IMmbBankAccountService;
import com.zllh.mall.mmbmmanage.service.IMmbWarehouseService;
import com.zllh.mall.order.service.IOrderService;
import com.zllh.utils.common.UUIDCreater;
import com.zllh.utils.soleid.SoleIdUtil;

/**
 * @ClassName OrderController
 * @PackageName com.zllh.nxdj.mall.dealmmanage.controller
 * @Description 订单操作action类
 * @author Liujf
 * @Date 2016年3月30日 上午9:58:50
 * @Version V1.0
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private IMmbWarehouseService mmbWarehouseService;
	
	@Autowired
	private IMmbBankAccountService mmbBankAccountService;

    /**
     * @Title toPendingOrder
     * @Description: 跳转到待定订单页面
     * @Author Liujf
     * @CreateDate 2016年4月28日 下午4:11:13
     * @return
     * @return String
     */
    @RequestMapping("/toPendingOrder")
	public String toPendingOrder(){
		logger.info("====toPendingOrder====");
		return "mall/order/order_pendingorder";
	}
    
    /**
     * @Title toMyPendingOrder
     * @Description: 跳转到我的待定订单
     * @Author Liujf
     * @CreateDate 2016年8月22日 下午3:23:13
     * @return
     * @return String
     */
    @RequestMapping("/toMyPendingOrder")
	public String toMyPendingOrder(){
		logger.info("====toMyPendingOrder====");
		return "mall/order/order_mypendingorder";
	}
    
    /**
     * @Title queryPendingOrder
     * @Description: 获取待定订单
     * @Author Liujf
     * @CreateDate 2016年3月30日 上午10:01:39
     * @return
     * @return Map<String, Object>
     */
    @RequestMapping(value="/queryPendingOrder")
    @ResponseBody
	public Map<String, Object> queryPendingOrder(){
    	String memberId = "";
		UserExtendBean userExtendBean = (UserExtendBean)session.getAttribute("user");     //session中获取当前登录操作员的对象
		if (userExtendBean != null) {
			memberId = userExtendBean.getMuser().getMmbId(); // 操作员所属会员id
		}
		String name = request.getParameter("name");
		String orderId = request.getParameter("orderId");
		String status = request.getParameter("status");
		String executeStatus = request.getParameter("executeStatus");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberId", memberId);
		if(name!=null&&!"".equals(name)){
			map.put("oppositeName", name);
		}
		if(orderId!=null&&!"".equals(orderId)){
			map.put("ordertitleCode", orderId);
		}
		if(executeStatus!=null&&!"".equals(executeStatus)){
			map.put("executeStatus", executeStatus);
		}
		if (startTime != null && !"".equals(startTime)) {
			map.put("executeStartTime", startTime);
		}
		if (endTime != null && !"".equals(endTime)) {
			map.put("executeEndTime", endTime);
		}
		if (status != null && !"".equals(status)) {
			map.put("orderstatus", status);
		}
		int count = orderService.queryPendingOrderList(map).size();
    	// 执行条件查询总数
		// 分页数据初始化
		int pageNo = getPageNo();
		int pageSize = getPageSize();
		// 接收页面参数并传递给service
		map.put("startFirst", pageNo);
		map.put("startEnd", pageSize);
    	List<MtOrdertitle> result = orderService.queryPendingOrderList(map);
    	Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("total", count);
		returnMap.put("rows", result);
		return returnMap;
	}
    
    /**
     * @Title queryMyPendingOrder
     * @Description: 查询我的待定订单
     * @Author Liujf
     * @CreateDate 2016年8月22日 下午3:24:25
     * @return
     * @return Map<String,Object>
     */
    @RequestMapping(value="/queryMyPendingOrder")
    @ResponseBody
	public Map<String, Object> queryMyPendingOrder(){
    	String memberId = "";
		UserExtendBean userExtendBean = (UserExtendBean)session.getAttribute("user");     //session中获取当前登录操作员的对象
		if (userExtendBean != null) {
			memberId = userExtendBean.getMuser().getMmbId(); // 操作员所属会员id
		}
		String name = request.getParameter("name");
		String orderId = request.getParameter("orderId");
		String status = request.getParameter("status");
		String executeStatus = request.getParameter("executeStatus");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberId", memberId);
		if(name!=null&&!"".equals(name)){
			map.put("oppositeName", name);
		}
		if(orderId!=null&&!"".equals(orderId)){
			map.put("ordertitleCode", orderId);
		}
		if(executeStatus!=null&&!"".equals(executeStatus)){
			map.put("executeStatus", executeStatus);
		}
		if (startTime != null && !"".equals(startTime)) {
			map.put("executeStartTime", startTime);
		}
		if (endTime != null && !"".equals(endTime)) {
			map.put("executeEndTime", endTime);
		}
		if (status != null && !"".equals(status)) {
			map.put("orderstatus", status);
		}
		int count = orderService.queryMyPendingOrderList(map).size();
    	// 执行条件查询总数
		// 分页数据初始化
		int pageNo = getPageNo();
		int pageSize = getPageSize();
		// 接收页面参数并传递给service
		map.put("startFirst", pageNo);
		map.put("startEnd", pageSize);
    	List<MtOrdertitle> result = orderService.queryMyPendingOrderList(map);
    	Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("total", count);
		returnMap.put("rows", result);
		return returnMap;
	}
    
    /**
     * @Title toOrderManage
     * @Description: 跳转到已定订单页面
     * @Author Liujf
     * @CreateDate 2016年5月18日 下午3:40:44
     * @return
     * @return String
     */
    @RequestMapping("/toOrderManage")
	public String toOrderManage(){
		logger.info("====toOrderManage====");
		return "mall/order/order_ordermanage";
	}
    
    /**
     * @Title queryOrderManage
     * @Description: 查询已定订单
     * @Author Liujf
     * @CreateDate 2016年5月18日 下午3:41:04
     * @param orderId
     * @param executeStatus
     * @param name
     * @param status
     * @param startTime
     * @param endTime
     * @return
     * @return Map<String,Object>
     */
    @RequestMapping(value="/queryOrderManage")
    @ResponseBody
	public Map<String, Object> queryOrderManage(@RequestParam String orderId,       //订单编号
			@RequestParam String executeStatus,                          			//订单执行状态
			@RequestParam String name,                                   			//对方名称
			@RequestParam String status,                                   			//订单类型
			@RequestParam String startTime,                              			//开始时间
			@RequestParam String endTime){                               			//结束时间
    	//从session中获取当前登录用户的会员id
    	String memberId = "";
		UserExtendBean userExtendBean = (UserExtendBean)session.getAttribute("user");     //session中获取当前登录操作员的对象
		if (userExtendBean != null) {
			memberId = userExtendBean.getMuser().getMmbId(); // 操作员所属会员id
		}
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("memberId", memberId);
    	if(name!=null&&!"".equals(name)){
			map.put("oppositeName", name);
		}
		if(orderId!=null&&!"".equals(orderId)){
			map.put("ordertitleCode", orderId);
		}
		if(executeStatus!=null&&!"".equals(executeStatus)){
			map.put("executeStatus", executeStatus);
		}
		if (startTime != null && !"".equals(startTime)) {
			map.put("executeStartTime", startTime);
		}
		if (endTime != null && !"".equals(endTime)) {
			map.put("executeEndTime", endTime);
		}
		if (status != null && !"".equals(status)) {
			map.put("orderstatus", status);
		}
    	// 执行条件查询总数
		int count = orderService.queryOrderList(map).size();
		// 分页数据初始化
		int pageNo = getPageNo();
		int pageSize = getPageSize();
		// 接收页面参数并传递给service
		map.put("startFirst", pageNo);
		map.put("startEnd", pageSize);
    	List<MtOrdertitle> result = orderService.queryOrderList(map);
    	Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("total", count);
		returnMap.put("rows", result);
		return returnMap;
	}
    
    /**
     * @Title createOrder
     * @Description: 创建订单，根据页面传来的参数，创建订单标题和对应的订单商品
     * @Author Liujf
     * @CreateDate 2016年5月19日 下午2:16:10
     * @param param
     * @return
     * @return Map<String,Object>
     */
	@RequestMapping(value = "/createOrder")
	@ResponseBody
	public Map<String, Object> createOrder(@RequestParam String param) {
		List<MtOrdertitle> orderTitleList = JSON.parseArray(param, MtOrdertitle.class);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		UserExtendBean userExtendBean = (UserExtendBean)session.getAttribute("user");     //session中获取当前登录操作员的对象
		String memberId = "";
		String userId = "";
		String userName = "";
		if(userExtendBean!=null){
			memberId = userExtendBean.getMuser().getMmbId();                              //根据操作员信息获取当前操作员的所属会员id
			userId = userExtendBean.getUserId();                                          //操作员id
			userName = userExtendBean.getUserName();
		}
		for(MtOrdertitle orderTitle : orderTitleList){
			try {
				String id = UUIDCreater.getUUID();
				Integer code = Integer.valueOf(SoleIdUtil.getSoleIdSingletion().getIntSoleId(BillsType.ORDER.getValue()));
				orderTitle.setId(id);
				orderTitle.setOrdertitleCode(code);
				if (memberId.equals(orderTitle.getBuyersId())) {
					orderTitle.setStatus(2);
				} else {
					orderTitle.setStatus(3);
				}
				orderService.createOrder(orderTitle,userId,userName);
				returnMap.put("msg", "新建订单成功！");
			} catch (Exception e) {
				returnMap.put("msg", "创建订单失败！");
			}
		}
		// 确定返回页面
		return returnMap;

	}
	
	/**
	 * @Title editOrder
	 * @Description: 编辑订单
	 * @Author Liujf
	 * @CreateDate 2016年5月19日 下午2:15:45
	 * @param param
	 * @return
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/editOrder")
	@ResponseBody
	public Map<String, Object> editOrder(@RequestParam String param) {
		List<MtOrdertitle> orderTitleList = JSON.parseArray(param, MtOrdertitle.class);
		String remark = "";
		Map<String, Object> returnMap = new HashMap<String, Object>();
		UserExtendBean userExtendBean = (UserExtendBean)session.getAttribute("user");     //session中获取当前登录操作员的对象
		String memberId = "";
		String userId = "";
		String userName = "";
		if(userExtendBean!=null){
			memberId = userExtendBean.getMuser().getMmbId();                              //根据操作员信息获取当前操作员的所属会员id
			userId = userExtendBean.getUserId();                                          //操作员id
			userName = userExtendBean.getUserName();
		}
		for(MtOrdertitle orderTitle : orderTitleList){
			try {
				if (memberId.equals(orderTitle.getBuyersId())) {
					orderTitle.setStatus(2);
				} else if(memberId.equals(orderTitle.getSellersId())){
					orderTitle.setStatus(3);
				}
				//查询当前编辑订单的原信息便于和新的订单信息对比
				MtOrdertitle orderTitleTemp = orderService.queryOrder(orderTitle.getId());
				List<MtOrder> orderTempList = orderTitleTemp.getOrderList();
				List<MtOrder> orderList = orderTitle.getOrderList();
				if(orderTitle.getWorkflowTypeId()!=orderTitleTemp.getWorkflowTypeId()){
					switch(orderTitle.getWorkflowTypeId()){
					case 1:
						remark += "订单流程修改为“货款两清”\n";
					case 2:
						remark += "订单流程修改为“先货后款”\n";
					case 3:
						remark += "订单流程修改为“先货后款已交货”\n";
					case 4:
						remark += "订单流程修改为“先款后货”\n";
					case 5:
						remark += "订单流程修改为“先款后货已交款”\n";
					}
				}
				if(!orderTitle.getExecuteStartTime().equals(orderTitleTemp.getExecuteStartTime())){
					remark += "“送货开始时间”被修改\n";
				}
				if(!orderTitle.getExecuteEndTime().equals(orderTitleTemp.getExecuteEndTime())){
					remark += "“送货结束时间”被修改\n";
				}
				if(!orderTitle.getPayTime().equals(orderTitleTemp.getPayTime())){
					remark += "“付款时间”被修改\n";
				}
				if(orderTitle.getPayAccount()!=null&&!"".equals(orderTitle.getPayAccount())){
					if(!orderTitle.getPayAccount().equals(orderTitleTemp.getPayAccount())){
						remark += "“支付账号”被修改\n";
					}
				}
				if(orderTitle.getGetAccount()!=null&&!"".equals(orderTitle.getGetAccount())){
					if(!orderTitle.getGetAccount().equals(orderTitleTemp.getGetAccount())){
						remark += "“收款账号”被修改\n";
					}
				}
				if(orderTitle.getBuyersAddressId()!=null&&!"".equals(orderTitle.getBuyersAddressId())){
					if(!orderTitle.getBuyersAddressId().equals(orderTitleTemp.getBuyersAddressId())){
						remark += "“收货地址”被修改\n";
					}
				}
				if(orderTitle.getSellersAddressId()!=null&&!"".equals(orderTitle.getSellersAddressId())){
					if(!orderTitle.getSellersAddressId().equals(orderTitleTemp.getSellersAddressId())){
						remark += "“发货地址”被修改\n";
					}
				}
				for(int i=0;i<orderList.size();i++){
					String oId = orderList.get(i).getId();
					Double gNumber = orderList.get(i).getGetgoodsNum();
					Double gPrice = orderList.get(i).getPrice();
					for(int j=0;j<orderTempList.size();j++){
						String otId = orderTempList.get(j).getId();
						if(oId.equals(otId)){
							String gtName = orderTempList.get(i).getGoodsName();
							Double gtNumber = orderTempList.get(i).getGetgoodsNum();
							Double gtPrice = orderTempList.get(i).getPrice();
							if(!gtNumber.equals(gNumber)){
								remark += gtName+"的数量被修改\n";
							}
							if(!gtPrice.equals(gPrice)){
								remark += gtName+"的价格被修改\n";
							}
							break;
						}
					}
				}
				if(remark.length()>50){
					remark = remark.substring(0, 48)+"...";
				}
				orderTitle.setRemark(remark);
				orderService.updateOrder(orderTitle,userId,userName);
				returnMap.put("msg", "编辑订单成功！");
			}catch(Exception e){
				returnMap.put("msg", "编辑订单失败！");
			}
		}
		return returnMap;
	}

	/**
	 * @Title abolishOrder
	 * @Description: 订单作废
	 * @Author Liujf
	 * @CreateDate 2016年3月30日 上午10:04:02
	 * @return
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/abolishOrder")
	@ResponseBody
	public Map<String, Object> abolishOrder() {
		String json = ServletRequestUtils.getStringParameter(request, "param", "");
		UserExtendBean userExtendBean = (UserExtendBean)session.getAttribute("user");     //session中获取当前登录操作员的对象
		String userId = "";
		String userName = "";
		if(userExtendBean!=null){
			userId = userExtendBean.getUserId();                                          //操作员id
			userName = userExtendBean.getUserName();
		}
		Map<String, Object> returnMap = new HashMap<String, Object>();
		int success = 0;
		int fail = 0;
		if(json!= null && !"".equals(json)){
			List<MtOrdertitle> mtOrder = JSON.parseArray(json, MtOrdertitle.class);
			for (MtOrdertitle orderTitle : mtOrder) {
				try {
					// 状态字段改为作废
					orderTitle.setStatus(5);
					orderService.updateAbolishOrder(orderTitle,userId,userName);
					success++;
				}catch (Exception e) {
					fail++;
				}
			}
			returnMap.put("msg", "订单作废成功"+success+"条\n订单作废失败"+fail+"条");
		}else{
			returnMap.put("msg", "订单作废失败！");
		}
		return returnMap;
	}

	/**
	 * @Title lockOrder
	 * @Description: 锁定订单
	 * @Author Liujf
	 * @CreateDate 2016年3月30日 上午10:05:06
	 * @return
	 * @return Map<String, Object>
	 */
	@RequestMapping(value="/lockOrder")
	@ResponseBody
	public Map<String, Object> lockOrder() {
		String json = ServletRequestUtils.getStringParameter(request, "param", "");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		UserExtendBean userExtendBean = (UserExtendBean)session.getAttribute("user");     //session中获取当前登录操作员的对象
		String memberId = "";
		String userId = "";
		String userName = "";
		if(userExtendBean!=null){
			userId = userExtendBean.getUserId();                                          //操作员id
			userName = userExtendBean.getUserName();                                      //操作员name
			memberId = userExtendBean.getMuser().getMmbId();                              //根据操作员信息获取当前操作员的所属会员id
		}
		//从session中获取当前登录会员信息和订单对象中的买卖双方比较，来设置订单标题表中的"status"值
		//标记是buyer或者seller
		String bos = "";
		int success = 0;
		int fail = 0;
		if(json!= null && !"".equals(json)){
			List<MtOrdertitle> mtOrder = JSON.parseArray(json, MtOrdertitle.class);
			for (MtOrdertitle orderTitle : mtOrder) {
				try {
					if (memberId.equals(orderTitle.getBuyersId())) {
						bos = "b";
					} else {
						bos = "s";
					}
					// 买方锁定并且卖方已签字
					if (bos.equals("b") && orderTitle.getStatus() == 3) {
						orderTitle.setStatus(4);
						orderTitle.setUserId(userId);
						orderTitle.setUserName(userName);
						orderTitle.setLockTime(new Date());
						// 买方锁定并且卖方未签字
					} else if (bos.equals("b") && orderTitle.getStatus() < 2) {
						orderTitle.setStatus(2);
					}
					// 卖方锁定并且买方已签字
					if (bos.equals("s") && orderTitle.getStatus() == 2) {
						orderTitle.setStatus(4);
						orderTitle.setUserId(userId);
						orderTitle.setUserName(userName);
						orderTitle.setLockTime(new Date());
						// 卖方锁定并且买方未签字
					} else if (bos.equals("s") && orderTitle.getStatus() < 3) {
						orderTitle.setStatus(3);
					}
					orderService.updateLockOrder(orderTitle,userId,userName);
					success++;
				}catch(Exception e){
					fail++;
				}
			}
			returnMap.put("msg", "订单锁定成功"+success+"条\n订单锁定失败"+fail+"条");
		}else{
			returnMap.put("msg", "订单锁定失败！");
		}
		return returnMap;
	}
	
	/**
	 * @Title queryOrderDetail
	 * @Description: 根据订单标题信息查询订单物品详细信息
	 * @Author Liujf
	 * @CreateDate 2016年5月12日 下午2:01:31
	 * @param id  订单标题表id
	 * @return
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryOrderDetail")
	@ResponseBody
	public Map<String, Object> queryOrderDetail(@RequestParam String id) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		MtOrdertitle mtOrdertitle = orderService.queryOrder(id);
		List<MtOrder> result = new ArrayList<MtOrder>();
		if(mtOrdertitle!=null){
			if(mtOrdertitle.getOrderList()!=null){
				result = mtOrdertitle.getOrderList();
			}
		}else{
			returnMap.put("msg", "没有找到相关数据!");
			return returnMap;
		}
		int count = result.size();
		Map<String, String> buyerMap = new HashMap<String, String>();
		Map<String, String> sellerMap = new HashMap<String, String>();
		buyerMap.put("mmbId", mtOrdertitle.getBuyersId());
		sellerMap.put("mmbId", mtOrdertitle.getSellersId());

		List<MtMmbWarehouse> buyersAddressList = mmbWarehouseService.getMmbWareHouse(buyerMap);
		List<MtMmbWarehouse> sellersAddressList = mmbWarehouseService.getMmbWareHouse(sellerMap);
		List<MtMmbBankAccount> buyersAccountList = mmbBankAccountService.getMmbBankAccount(buyerMap);
		for(MtMmbBankAccount mtMmbBankAccount : buyersAccountList){
			mtMmbBankAccount.setBankname(mtMmbBankAccount.getBankname()+" "+mtMmbBankAccount.getAccountno());
		}
		List<MtMmbBankAccount> sellersAccountList = mmbBankAccountService.getMmbBankAccount(sellerMap);
		for(MtMmbBankAccount mtMmbBankAccount : sellersAccountList){
			mtMmbBankAccount.setBankname(mtMmbBankAccount.getBankname()+" "+mtMmbBankAccount.getAccountno());
		}
		returnMap.put("ordertitle", mtOrdertitle);
		returnMap.put("total", count);
		returnMap.put("data", result);
		returnMap.put("buyersAddressList", buyersAddressList);
		returnMap.put("sellersAddressList", sellersAddressList);
		returnMap.put("buyersAccountList", buyersAccountList);
		returnMap.put("sellersAccountList", sellersAccountList);
		return returnMap;
	}

	/**
	 * @Title stopOrder
	 * @Description: 终止订单
	 * @Author Liujf
	 * @CreateDate 2016年3月30日 上午10:05:40
	 * @return
	 * @return Map<String, Object>
	 */
	@RequestMapping(value="/stopOrder")
	@ResponseBody
	public Map<String, Object> stopOrder() {
		String json = ServletRequestUtils.getStringParameter(request, "param", "");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		UserExtendBean userExtendBean = (UserExtendBean)session.getAttribute("user");     //session中获取当前登录操作员的对象
		String memberId = "";
		String userName = "";
		String userId = "";
		if(userExtendBean!=null){
			userId = userExtendBean.getUserId();                                          //操作员id
			userName = userExtendBean.getUserName();                                      //操作员name
			memberId = userExtendBean.getMuser().getMmbId();                              //根据操作员信息获取当前操作员的所属会员id
		}
		if(json!= null && !"".equals(json)){
			List<MtOrdertitle> mtOrder = JSON.parseArray(json, MtOrdertitle.class);
			try {
				for (MtOrdertitle orderTitle : mtOrder) {
					if (memberId.equals(orderTitle.getBuyersId())) {
						orderTitle.setStopStatus(2);
					} else {
						orderTitle.setStopStatus(3);
					}
					orderService.updateStopOrder(orderTitle,userId,userName);
				}
				returnMap.put("msg", "请求终止订单成功！");
			} catch(Exception e){
				returnMap.put("msg", "请求终止订单失败！");
			}
		}else{
			returnMap.put("msg", "请求终止订单失败！");
		}
		return returnMap;
	}
	
	/**
	 * @Title revokeToStop
	 * @Description: 撤销终止订单
	 * @Author Liujf
	 * @CreateDate 2016年5月9日 下午2:51:09
	 * @return
	 * @return Map<String,Object>
	 */
	@RequestMapping(value="/revokeToStop")
	@ResponseBody
	public Map<String, Object> revokeToStop() {
		String json = ServletRequestUtils.getStringParameter(request, "param", "");
		UserExtendBean userExtendBean = (UserExtendBean)session.getAttribute("user");     //session中获取当前登录操作员的对象
		String userName = "";
		String userId = "";
		if(userExtendBean!=null){
			userId = userExtendBean.getUserId();                                          //操作员id
			userName = userExtendBean.getUserName();                                      //操作员name
		}
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if(json!= null && !"".equals(json)){
			try {
				List<MtOrdertitle> mtOrder = JSON.parseArray(json, MtOrdertitle.class);
				for (MtOrdertitle orderTitle : mtOrder) {
					orderTitle.setStopStatus(1);
					orderService.updateStopOrder(orderTitle,userId,userName);
				}
				returnMap.put("msg", "撤销终止订单成功！");
			} catch(Exception e){
				returnMap.put("msg", "撤销终止订单失败！");
			}
		}else{
			returnMap.put("msg", "撤销终止订单失败！");
		}
		return returnMap;
	}
	
	/**
	 * @Title allowToStop
	 * @Description: 同意终止订单
	 * @Author Liujf
	 * @CreateDate 2016年5月9日 下午2:51:22
	 * @return
	 * @return Map<String,Object>
	 */
	@RequestMapping(value="/allowToStop")
	@ResponseBody
	public Map<String, Object> allowToStop() {
		String json = ServletRequestUtils.getStringParameter(request, "param", "");
		UserExtendBean userExtendBean = (UserExtendBean)session.getAttribute("user");     //session中获取当前登录操作员的对象
		String memberId = "";
		String userName = "";
		String userId = "";
		if(userExtendBean!=null){
			userId = userExtendBean.getUserId();                                          //操作员id
			userName = userExtendBean.getUserName();                                      //操作员name
			memberId = userExtendBean.getMuser().getMmbId();                              //根据操作员信息获取当前操作员的所属会员id
		}
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if(json!= null && !"".equals(json)){
			try {
				List<MtOrdertitle> mtOrder = JSON.parseArray(json, MtOrdertitle.class);
				for (MtOrdertitle orderTitle : mtOrder) {
					orderTitle.setStopStatus(4);
					//成功终止订单后此订单为执行结束状态
					orderTitle.setExecuteStatus(2);
					orderTitle.setFinishTime(new Date());
					orderService.updateStopOrder(orderTitle,userId,userName);
				}
				returnMap.put("msg", "同意终止订单成功！");
			} catch(Exception e){
				returnMap.put("msg", "同意终止订单失败！");
			}
		}else{
			returnMap.put("msg", "同意终止订单失败！");
		}
		return returnMap;
	}
	
	/**
	 * @Title refuseToStop
	 * @Description: 拒绝终止订单
	 * @Author Liujf
	 * @CreateDate 2016年5月9日 下午2:51:34
	 * @return
	 * @return Map<String,Object>
	 */
	@RequestMapping(value="/refuseToStop")
	@ResponseBody
	public Map<String, Object> refuseToStop() {
		String json = ServletRequestUtils.getStringParameter(request, "param", "");
		UserExtendBean userExtendBean = (UserExtendBean)session.getAttribute("user");     //session中获取当前登录操作员的对象
		String memberId = "";
		String userName = "";
		String userId = "";
		if(userExtendBean!=null){
			userId = userExtendBean.getUserId();                                          //操作员id
			userName = userExtendBean.getUserName();                                      //操作员name
			memberId = userExtendBean.getMuser().getMmbId();                              //根据操作员信息获取当前操作员的所属会员id
		}
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if(json!= null && !"".equals(json)){
			try {
				List<MtOrdertitle> mtOrder = JSON.parseArray(json, MtOrdertitle.class);
				for (MtOrdertitle orderTitle : mtOrder) {
					orderTitle.setStopStatus(1);
					orderService.updateStopOrder(orderTitle,userId,userName);
				}
				returnMap.put("msg", "拒绝终止订单成功！");
			} catch(Exception e){
				returnMap.put("msg", "拒绝终止订单失败！");
			}
		}else{
			returnMap.put("msg", "拒绝终止订单失败！");
		}
		return returnMap;
	}
	
	/**
	 * @Title toSendGoods
	 * @Description: 跳转到发货页面
	 * @Author Liujf
	 * @CreateDate 2016年4月14日 下午2:55:11
	 * @return
	 * @return String
	 */
	@RequestMapping("/toSendGoods")
	public String toSendGoods(){
		logger.info("====toSendGoods====");
		return "mall/order/order_sendgoods";
	}
	
	/**
	 * @Title toGetGoods
	 * @Description: 跳转到收货页面
	 * @Author Liujf
	 * @CreateDate 2016年4月21日 下午5:25:52
	 * @return
	 * @return String
	 */
	@RequestMapping("/toGetGoods")
	public String toGetGoods(){
		logger.info("====toGetGoods====");
		return "mall/order/order_getgoods";
	}
	
	/**
	 * @Title toReturnGoods
	 * @Description: 跳转到退货页面
	 * @Author Liujf
	 * @CreateDate 2016年4月25日 下午1:53:57
	 * @return
	 * @return String
	 */
	@RequestMapping("/toReturnGoods")
	public String toReturnGoods(){
		logger.info("====toReturnGoods====");
		return "mall/order/order_returngoods";
	}
	
	/**
	 * @Title toGetReturnGoods
	 * @Description: 跳转到收退货页面
	 * @Author Liujf
	 * @CreateDate 2016年4月25日 下午1:55:53
	 * @return
	 * @return String
	 */
	@RequestMapping("/toGetReturnGoods")
	public String toGetReturnGoods(){
		logger.info("====toGetReturnGoods====");
		return "mall/order/order_getreturngoods";
	}
	
	/**
	 * @Title toPayMoney
	 * @Description: 跳转到付款页面
	 * @Author Liujf
	 * @CreateDate 2016年4月26日 上午10:07:21
	 * @return
	 * @return String
	 */
	@RequestMapping("/toPayMoney")
	public String toPayMoney(){
		logger.info("====toPayMoney====");
		return "mall/order/order_paymoney";
	}
	
	/**
	 * @Title toGetMoney
	 * @Description: 跳转到收款页面
	 * @Author Liujf
	 * @CreateDate 2016年4月26日 上午10:07:36
	 * @return
	 * @return String
	 */
	@RequestMapping("/toGetMoney")
	public String toGetMoney(){
		logger.info("====toGetMoney====");
		return "mall/order/order_getmoney";
	}
	
	/**
	 * @Title toRefundMoney
	 * @Description: 跳转到退款页面
	 * @Author Liujf
	 * @CreateDate 2016年4月26日 上午10:09:21
	 * @return
	 * @return String
	 */
	@RequestMapping("/toRefundMoney")
	public String toRefundMoney(){
		logger.info("====toRefundMoney====");
		return "mall/order/order_refundmoney";
	}
	
	/**
	 * @Title toGetRefundMoney
	 * @Description: 跳转到收退款页面
	 * @Author Liujf
	 * @CreateDate 2016年4月26日 上午10:09:39
	 * @return
	 * @return String
	 */
	@RequestMapping("/toGetRefundMoney")
	public String toGetRefundMoney(){
		logger.info("====toGetRefundMoney====");
		return "mall/order/order_getrefundmoney";
	}
	
	/**
	 * @Title querySendGoods
	 * @Description: 查询发货订单信息
	 * @Author Liujf
	 * @CreateDate 2016年3月30日 上午10:33:28
	 * @return
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/querySendGoods")
	@ResponseBody
	public Map<String,Object> querySendGoods(){
		// session获取当前登录人的会员id
		String memberId = "";
		UserExtendBean userExtendBean = (UserExtendBean)session.getAttribute("user");     //session中获取当前登录操作员的对象
		if (userExtendBean != null) {
			memberId = userExtendBean.getMuser().getMmbId(); // 操作员所属会员id
		}
		String json = ServletRequestUtils.getStringParameter(request, "param", "");
		Map<String, Object> map = new HashMap<String, Object>();
		String goodsName = request.getParameter("goodsName");
		String orderId = request.getParameter("orderId");
		map.put("memberId", memberId);
		if(json!= null && !"".equals(json)){
			JSONObject jsonObject = JSON.parseObject(json);
			goodsName = (String) jsonObject.get("goodsName");
			orderId = (String) jsonObject.get("orderId");
		}
		if (goodsName != null && !"".equals(goodsName)) {
			map.put("goodsName", goodsName);
		}
		if (orderId != null && !"".equals(orderId)) {
			map.put("ordertitleNumber", orderId);
		}
		// 执行条件查询总数
		int count = orderService.querySendOrder(map).size();
		// 分页数据初始化
		int pageNo = getPageNo();
		int pageSize = getPageSize();
		map.put("startFirst", pageNo);
		map.put("startEnd", pageSize);
		List<MtOrder> result = orderService.querySendOrder(map);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("total", count);
		returnMap.put("rows", result);
		return returnMap;
	}
	
	/**
	 * @Title sendGoods
	 * @Description: 发货
	 * @Author Liujf
	 * @CreateDate 2016年4月21日 下午3:45:51
	 * @return
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/sendGoods")
	@ResponseBody
    public Map<String, Object> sendGoods(){
		String json = ServletRequestUtils.getStringParameter(request, "param", "");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if(json!= null && !"".equals(json)){
			//自动生成电子货单
			//从session中取操作员id和操作员名称
			String userId = "";
			String userName = "";
			int success = 0;                                                                  //成功的记录条数
			int fail = 0;                                                                     //失败的记录条数
			UserExtendBean userExtendBean = (UserExtendBean)session.getAttribute("user");     //session中获取当前登录操作员的对象
			if(userExtendBean!=null){
				userId = userExtendBean.getUserId();                                          //操作员id
				userName = userExtendBean.getUserName();                                      //操作员name
			}
			List<MtOrder> orderList = JSON.parseArray(json, MtOrder.class);
			for(MtOrder order : orderList){
				try{
					orderService.updateSendOrder(order, userId, userName);
					success++;
				}catch(Exception e){
					fail++;
				}
			}
			returnMap.put("msg", "发货成功"+success+"条\n发货失败"+fail+"条");
		}else{
			returnMap.put("msg", "发货失败！");
		}
		return returnMap;
	}
	
	/**
	 * @Title queryGetGoods
	 * @Description: 查询收货订单信息
	 * @Author Liujf
	 * @CreateDate 2016年3月30日 下午2:28:54
	 * @return
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryGetGoods")
	@ResponseBody
	public Map<String,Object> queryGetGoods() {
		// session获取当前登录人的会员id
		String memberId = "";
		UserExtendBean userExtendBean = (UserExtendBean)session.getAttribute("user");     //session中获取当前登录操作员的对象
		if (userExtendBean != null) {
			memberId = userExtendBean.getMuser().getMmbId(); // 操作员所属会员id
		}
		String json = ServletRequestUtils.getStringParameter(request, "param", "");
		Map<String, Object> map = new HashMap<String, Object>();
		String goodsName = request.getParameter("goodsName");
		String orderId = request.getParameter("orderId");
		map.put("memberId", memberId);
		if(json!= null && !"".equals(json)){
			JSONObject jsonObject = JSON.parseObject(json);
			goodsName = (String) jsonObject.get("goodsName");
			orderId = (String) jsonObject.get("orderId");
		}
		if (goodsName != null && !"".equals(goodsName)) {
			map.put("goodsName", goodsName);
		}
		if (orderId != null && !"".equals(orderId)) {
			map.put("ordertitleNumber", orderId);
		}
		// 执行条件查询总数
		int count = orderService.queryGetOrder(map).size();
		// 分页数据初始化
		int pageNo = getPageNo();
		int pageSize = getPageSize();
		map.put("startFirst", pageNo);
		map.put("startEnd", pageSize);
		List<MtOrder> result = orderService.queryGetOrder(map);
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("mmbId", memberId);

		//查询收货地址
		List<MtMmbWarehouse> addressList = mmbWarehouseService.getMmbWareHouse(paramMap);
		if(addressList!=null){
			for(MtOrder order : result){
				order.setAddressList(addressList);
			}
		}
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("total", count);
		returnMap.put("rows", result);
		return returnMap;
	}
	
	/**
	 * @Title getGoods
	 * @Description: 收货
	 * @Author Liujf
	 * @CreateDate 2016年3月30日 下午2:46:35
	 * @return
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/getGoods")
	@ResponseBody
    public Map<String,Object> getGoods(){
		String json = ServletRequestUtils.getStringParameter(request, "param", "");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if(json!= null && !"".equals(json)){
			//从session中取操作员id和操作员名称
			String userId = "";
			String userName = "";
			int success = 0;                                                                  //成功的记录条数
			int fail = 0;                                                                     //失败的记录条数
			UserExtendBean userExtendBean = (UserExtendBean)session.getAttribute("user");     //session中获取当前登录操作员的对象
			if(userExtendBean!=null){
				userId = userExtendBean.getUserId();                                          //操作员id
				userName = userExtendBean.getUserName();                                      //操作员name
			}
			List<MtOrder> orderList = JSON.parseArray(json, MtOrder.class);
			for(MtOrder order : orderList){
				try{
					orderService.updateGetOrder(order, userId, userName);
					orderService.checkOrdertitleExecuteStatus(order.getOredertitleCode());
					success++;
				}catch(Exception e){
					fail++;
				}
			}
			returnMap.put("msg", "收货成功"+success+"条\n收货失败"+fail+"条");
		}else{
			returnMap.put("msg", "收货失败！");
		}
		return returnMap;
	}
	
	/**
	 * @Title queryReturnGoods
	 * @Description: 查询退货订单信息
	 * @Author Liujf
	 * @CreateDate 2016年3月31日 上午11:28:03
	 * @return
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/queryReturnGoods")
	@ResponseBody
	public Map<String, Object> queryReturnGoods() {
		String memberId = "";
		UserExtendBean userExtendBean = (UserExtendBean)session.getAttribute("user");     //session中获取当前登录操作员的对象
		if (userExtendBean != null) {
			memberId = userExtendBean.getMuser().getMmbId(); // 操作员所属会员id
		}
		String json = ServletRequestUtils.getStringParameter(request, "param", "");
		Map<String, Object> map = new HashMap<String, Object>();
		String goodsName = request.getParameter("goodsName");
		String orderId = request.getParameter("orderId");
		map.put("memberId", memberId);
		if(json!= null && !"".equals(json)){
			JSONObject jsonObject = JSON.parseObject(json);
			goodsName = (String) jsonObject.get("goodsName");
			orderId = (String) jsonObject.get("orderId");
		}
		if (goodsName != null && !"".equals(goodsName)) {
			map.put("goodsName", goodsName);
		}
		if (orderId != null && !"".equals(orderId)) {
			map.put("ordertitleNumber", orderId);
		}
		// 执行条件查询总数
		int count = orderService.queryReturnOrder(map).size();
		// 分页数据初始化
		int pageNo = getPageNo();
		int pageSize = getPageSize();
		map.put("startFirst", pageNo);
		map.put("startEnd", pageSize);
		List<MtOrder> result = orderService.queryReturnOrder(map);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("total", count);
		returnMap.put("rows", result);
		return returnMap;
	}
	
	/**
	 * @Title returnGoods
	 * @Description: 退货
	 * @Author Liujf
	 * @CreateDate 2016年3月31日 上午11:43:07
	 * @return
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/returnGoods")
	@ResponseBody
    public Map<String,Object> returnGoods(){
		String json = ServletRequestUtils.getStringParameter(request, "param", "");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if(json!= null && !"".equals(json)){
			//从session中取操作员id和操作员名称
			String userId = "";
			String userName = "";
			int success = 0;                                                                  //成功的记录条数
			int fail = 0;                                                                     //失败的记录条数
			UserExtendBean userExtendBean = (UserExtendBean)session.getAttribute("user");     //session中获取当前登录操作员的对象
			if(userExtendBean!=null){
				userId = userExtendBean.getUserId();                                          //操作员id
				userName = userExtendBean.getUserName();                                      //操作员name
			}
			List<MtOrder> orderList = JSON.parseArray(json, MtOrder.class);
			for(MtOrder order : orderList){
				try{
					orderService.updateReturnOrder(order, userId, userName);
					success++;
				}catch(Exception e){
					fail++;
				}
			}
			returnMap.put("msg", "退货成功"+success+"条\n退货失败"+fail+"条");
		}else{
			returnMap.put("msg", "退货失败！");
		}
		return returnMap;
	}
	
	/**
	 * @Title queryGetReturnGoods
	 * @Description: 查询收退货订单信息
	 * @Author Liujf
	 * @CreateDate 2016年3月31日 上午11:43:40
	 * @return
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/queryGetReturnGoods")
	@ResponseBody
	public Map<String, Object> queryGetReturnGoods() {
		String memberId = "";
		UserExtendBean userExtendBean = (UserExtendBean)session.getAttribute("user");     //session中获取当前登录操作员的对象
		if (userExtendBean != null) {
			memberId = userExtendBean.getMuser().getMmbId(); // 操作员所属会员id
		}
		String json = ServletRequestUtils.getStringParameter(request, "param", "");
		Map<String, Object> map = new HashMap<String, Object>();
		String goodsName = request.getParameter("goodsName");
		String orderId = request.getParameter("orderId");
		map.put("memberId", memberId);
		if(json!= null && !"".equals(json)){
			JSONObject jsonObject = JSON.parseObject(json);
			goodsName = (String) jsonObject.get("goodsName");
			orderId = (String) jsonObject.get("orderId");
		}
		if (goodsName != null && !"".equals(goodsName)) {
			map.put("goodsName", goodsName);
		}
		if (orderId != null && !"".equals(orderId)) {
			map.put("ordertitleNumber", orderId);
		}
		// 执行条件查询总数
		int count = orderService.queryGetReturnOrder(map).size();
		// 分页数据初始化
		int pageNo = getPageNo();
		int pageSize = getPageSize();
		map.put("startFirst", pageNo);
		map.put("startEnd", pageSize);
		List<MtOrder> result = orderService.queryGetReturnOrder(map);
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("mmbId", memberId);

		//查询收货地址
		List<MtMmbWarehouse> addressList = mmbWarehouseService.getMmbWareHouse(paramMap);
		if(addressList!=null){
			for(MtOrder order : result){
				order.setAddressList(addressList);
			}
		}
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("total", count);
		returnMap.put("rows", result);
		return returnMap;
	}
	
	/**
	 * @Title getReturnGoods
	 * @Description: 收退货
	 * @Author Liujf
	 * @CreateDate 2016年3月31日 上午11:44:24
	 * @return
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/getReturnGoods")
	@ResponseBody
    public Map<String, Object> getReturnGoods(){
		String json = ServletRequestUtils.getStringParameter(request, "param", "");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if(json!= null && !"".equals(json)){
			//从session中取操作员id和操作员名称
			String userId = "";
			String userName = "";
			int success = 0;                                                                  //成功的记录条数
			int fail = 0;                                                                     //失败的记录条数
			UserExtendBean userExtendBean = (UserExtendBean)session.getAttribute("user");     //session中获取当前登录操作员的对象
			if(userExtendBean!=null){
				userId = userExtendBean.getUserId();                                          //操作员id
				userName = userExtendBean.getUserName();                                      //操作员name
			}
			List<MtOrder> orderList = JSON.parseArray(json, MtOrder.class);
			for(MtOrder order : orderList){
				try{
					orderService.updateGetReturnOrder(order, userId, userName);
					success++;
				}catch(Exception e){
					fail++;
				}
			}
			returnMap.put("msg", "收退货成功"+success+"条\n收退货失败"+fail+"条");
		}else{
			returnMap.put("msg", "收退货失败！");
		}
		return returnMap;
	}
	
	/**
	 * @Title queryPayMoney
	 * @Description: 查询付款订单信息
	 * @Author Liujf
	 * @CreateDate 2016年4月5日 上午10:28:09
	 * @return
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/queryPayMoney")
	@ResponseBody
	public Map<String, Object> queryPayMoney() {
		// session获取当前登录人的会员id
		String memberId = "";
		UserExtendBean userExtendBean = (UserExtendBean)session.getAttribute("user");     //session中获取当前登录操作员的对象
		if (userExtendBean != null) {
			memberId = userExtendBean.getMuser().getMmbId(); // 操作员所属会员id
		}
		String json = ServletRequestUtils.getStringParameter(request, "param", "");
		Map<String, Object> map = new HashMap<String, Object>();
		String goodsName = request.getParameter("goodsName");
		String orderId = request.getParameter("orderId");
		map.put("memberId", memberId);
		if(json!= null && !"".equals(json)){
			JSONObject jsonObject = JSON.parseObject(json);
			goodsName = (String) jsonObject.get("goodsName");
			orderId = (String) jsonObject.get("orderId");
		}
		if (goodsName != null && !"".equals(goodsName)) {
			map.put("goodsName", goodsName);
		}
		if (orderId != null && !"".equals(orderId)) {
			map.put("ordertitleNumber", orderId);
		}
		// 执行条件查询总数
		int count = orderService.queryPayMoneyOrder(map).size();
		// 分页数据初始化
		int pageNo = getPageNo();
		int pageSize = getPageSize();
		map.put("startFirst", pageNo);
		map.put("startEnd", pageSize);
		List<MtOrder> result = orderService.queryPayMoneyOrder(map);
		Map<String, String> buyerMap = new HashMap<String, String>();
		buyerMap.put("mmbId", memberId);

		//查询付款账号
		List<MtMmbBankAccount> buyersAccountList = mmbBankAccountService.getMmbBankAccount(buyerMap);
		if(buyersAccountList!=null){
			for(MtMmbBankAccount mtMmbBankAccount : buyersAccountList){
				mtMmbBankAccount.setBankname(mtMmbBankAccount.getBankname()+" "+mtMmbBankAccount.getAccountno());
			}
			for(MtOrder order : result){
				order.setAccountList(buyersAccountList);
			}
		}
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("total", count);
		returnMap.put("rows", result);
		return returnMap;
	}
	
	/**
	 * @Title queryGetMoney
	 * @Description: 查询收款订单信息
	 * @Author Liujf
	 * @CreateDate 2016年4月7日 下午2:17:39
	 * @return
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/queryGetMoney")
	@ResponseBody
	public Map<String, Object> queryGetMoney() {
		// 根据卖家id等于当前登陆人的会员id and 发货执行量>0
		String memberId = "";
		UserExtendBean userExtendBean = (UserExtendBean)session.getAttribute("user");     //session中获取当前登录操作员的对象
		if (userExtendBean != null) {
			memberId = userExtendBean.getMuser().getMmbId(); // 操作员所属会员id
		}
		String json = ServletRequestUtils.getStringParameter(request, "param", "");
		Map<String, Object> map = new HashMap<String, Object>();
		String goodsName = request.getParameter("goodsName");
		String orderId = request.getParameter("orderId");
		map.put("memberId", memberId);
		if(json!= null && !"".equals(json)){
			JSONObject jsonObject = JSON.parseObject(json);
			goodsName = (String) jsonObject.get("goodsName");
			orderId = (String) jsonObject.get("orderId");
		}
		if (goodsName != null && !"".equals(goodsName)) {
			map.put("goodsName", goodsName);
		}
		if (orderId != null && !"".equals(orderId)) {
			map.put("ordertitleNumber", orderId);
		}
		// 执行条件查询总数
		int count = orderService.queryGetMoney(map).size();
		// 分页数据初始化
		int pageNo = getPageNo();
		int pageSize = getPageSize();
		map.put("startFirst", pageNo);
		map.put("startEnd", pageSize);
		List<MtGetpaymoney> result = orderService.queryGetMoney(map);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("total", count);
		returnMap.put("rows", result);
		return returnMap;
	}
	
	/**
	 * @Title queryRefundMoney
	 * @Description: 查询退款订单信息
	 * @Author Liujf
	 * @CreateDate 2016年4月11日 上午8:54:40
	 * @return
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/queryRefundMoney")
	@ResponseBody
	public Map<String, Object> queryRefundMoney() {
		String memberId = "";
		UserExtendBean userExtendBean = (UserExtendBean)session.getAttribute("user");     //session中获取当前登录操作员的对象
		if (userExtendBean != null) {
			memberId = userExtendBean.getMuser().getMmbId(); // 操作员所属会员id
		}
		String json = ServletRequestUtils.getStringParameter(request, "param", "");
		Map<String, Object> map = new HashMap<String, Object>();
		String goodsName = request.getParameter("goodsName");
		String orderId = request.getParameter("orderId");
		map.put("memberId", memberId);
		if(json!= null && !"".equals(json)){
			JSONObject jsonObject = JSON.parseObject(json);
			goodsName = (String) jsonObject.get("goodsName");
			orderId = (String) jsonObject.get("orderId");
		}
		if (goodsName != null && !"".equals(goodsName)) {
			map.put("goodsName", goodsName);
		}
		if (orderId != null && !"".equals(orderId)) {
			map.put("ordertitleNumber", orderId);
		}
		// 执行条件查询总数
		int count = orderService.queryRefundOrder(map).size();
		// 分页数据初始化
		int pageNo = getPageNo();
		int pageSize = getPageSize();
		map.put("startFirst", pageNo);
		map.put("startEnd", pageSize);
		List<MtOrder> result = orderService.queryRefundOrder(map);
		Map<String, String> sellerMap = new HashMap<String, String>();
		sellerMap.put("mmbId", memberId);

		//查询退款账号
		List<MtMmbBankAccount> sellersAccountList = mmbBankAccountService.getMmbBankAccount(sellerMap);
		if(sellersAccountList!=null){
			for(MtMmbBankAccount mtMmbBankAccount : sellersAccountList){
				mtMmbBankAccount.setBankname(mtMmbBankAccount.getBankname()+" "+mtMmbBankAccount.getAccountno());
			}
			for(MtOrder order : result){
				order.setAccountList(sellersAccountList);
			}
		}
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("total", count);
		returnMap.put("rows", result);
		return returnMap;
	}
	
	/**
	 * @Title queryGetRefundMoney
	 * @Description: 查询收退款订单信息
	 * @Author Liujf
	 * @CreateDate 2016年4月7日 下午2:19:53
	 * @return
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/queryGetRefundMoney")
	@ResponseBody
	public Map<String, Object> queryGetRefundMoney() {
		String memberId = "";
		UserExtendBean userExtendBean = (UserExtendBean)session.getAttribute("user");     //session中获取当前登录操作员的对象
		if(userExtendBean!=null){
			memberId = userExtendBean.getMuser().getMmbId();                              //操作员所属会员id
		}
		String json = ServletRequestUtils.getStringParameter(request, "param", "");
		Map<String, Object> map = new HashMap<String, Object>();
		String goodsName = request.getParameter("goodsName");
		String orderId = request.getParameter("orderId");
		map.put("memberId", memberId);
		if(json!= null && !"".equals(json)){
			JSONObject jsonObject = JSON.parseObject(json);
			goodsName = (String) jsonObject.get("goodsName");
			orderId = (String) jsonObject.get("orderId");
		}
		if (goodsName != null && !"".equals(goodsName)) {
			map.put("goodsName", goodsName);
		}
		if (orderId != null && !"".equals(orderId)) {
			map.put("ordertitleNumber", orderId);
		}
		// 执行条件查询总数
		int count = orderService.queryGetRefund(map).size();
		// 分页数据初始化
		int pageNo = getPageNo();
		int pageSize = getPageSize();
		map.put("startFirst", pageNo);
		map.put("startEnd", pageSize);
		List<MtGetpaymoney> result = orderService.queryGetRefund(map);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("total", count);
		returnMap.put("rows", result);
		return returnMap;
	}
	
	/**
	 * @Title payMoney
	 * @Description: 线下付款
	 * @Author Liujf
	 * @CreateDate 2016年4月5日 上午10:28:57
	 * @return
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/payMoneyOffline")
	@ResponseBody
    public Map<String, Object> payMoneyOffline(){
		String json = ServletRequestUtils.getStringParameter(request, "param", "");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if(json!= null && !"".equals(json)){
			//从session中取操作员id和操作员名称
			String userId = "";
			String userName = "";
			int success = 0;                                                                  //成功的记录条数
			int fail = 0;                                                                     //失败的记录条数
			UserExtendBean userExtendBean = (UserExtendBean)session.getAttribute("user");     //session中获取当前登录操作员的对象
			if(userExtendBean!=null){
				userId = userExtendBean.getUserId();                                          //操作员id
				userName = userExtendBean.getUserName();                                      //操作员name
			}
			List<MtOrder> orderList = JSON.parseArray(json, MtOrder.class);
			for(MtOrder order : orderList){
				try{
					orderService.updatePayMoneyOfflineOrder(order, userId, userName);
					success++;
				}catch(Exception e){
					fail++;
				}
			}
			returnMap.put("msg", "付款成功"+success+"条\n付款失败"+fail+"条");
		}else{
			returnMap.put("msg", "付款失败！");
		}
		return returnMap;
	}
	
	/**
	 * @Title payMoneyOnline
	 * @Description: 线上付款
	 * @Author Liujf
	 * @CreateDate 2016年4月7日 下午3:41:28
	 * @return
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/payMoneyOnline")
	@ResponseBody
    public Map<String, Object> payMoneyOnline(){
		String json = ServletRequestUtils.getStringParameter(request, "param", "");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		//从session中取操作员id和操作员名称
		String userId = "";
		String userName = "";
		UserExtendBean userExtendBean = (UserExtendBean)session.getAttribute("user");     //session中获取当前登录操作员的对象
		if(userExtendBean!=null){
			userId = userExtendBean.getUserId();                                          //操作员id
			userName = userExtendBean.getUserName();                                      //操作员name
		}
		try{
			List<MtOrder> order = JSON.parseArray(json, MtOrder.class);
			orderService.updatePayMoneyOnlineOrder(order, userId, userName);
			returnMap.put("msg", "付款成功！");
		}catch(Exception e){
			returnMap.put("msg", "付款失败！");
		}
		return returnMap;
	}
	
	/**
	 * @Title getMoney
	 * @Description: 执行收款
	 * @Author Liujf
	 * @CreateDate 2016年4月7日 下午3:46:16
	 * @return
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/getMoney")
	@ResponseBody
	public Map<String, Object> getMoney(){
		String json = ServletRequestUtils.getStringParameter(request, "param", "");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if(json!= null && !"".equals(json)){
			//从session中取操作员id和操作员名称
			String userId = "";
			String userName = "";
			int success = 0;                                                                  //成功的记录条数
			int fail = 0;                                                                     //失败的记录条数
			UserExtendBean userExtendBean = (UserExtendBean)session.getAttribute("user");     //session中获取当前登录操作员的对象
			if(userExtendBean!=null){
				userId = userExtendBean.getUserId();                                          //操作员id
				userName = userExtendBean.getUserName();                                      //操作员name
			}
			List<MtGetpaymoney> getPayMoneyList = JSON.parseArray(json, MtGetpaymoney.class);
			for(MtGetpaymoney getpaymoney : getPayMoneyList){
				try{
					orderService.updateGetMoneyOrder(getpaymoney, userId, userName);
					orderService.checkOrdertitleExecuteStatus(getpaymoney.getOrdertitleId());
					success++;
				}catch(Exception e){
					fail++;
				}
			}
			returnMap.put("msg", "收款成功"+success+"条\n收款失败"+fail+"条");
		}else{
			returnMap.put("msg", "收款失败！");
		}
		return returnMap;
	}
	
	/**
	 * @Title refundMoneyOffline
	 * @Description: 执行线下退款
	 * @Author Liujf
	 * @CreateDate 2016年4月7日 下午3:46:29
	 * @return
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/refundMoneyOffline")
	@ResponseBody
    public Map<String, Object> refundMoneyOffline(){
		String json = ServletRequestUtils.getStringParameter(request, "param", "");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if(json!= null && !"".equals(json)){
			//从session中取操作员id和操作员名称
			String userId = "";
			String userName = "";
			int success = 0;                                                                  //成功的记录条数
			int fail = 0;                                                                     //失败的记录条数
			UserExtendBean userExtendBean = (UserExtendBean)session.getAttribute("user");     //session中获取当前登录操作员的对象
			if(userExtendBean!=null){
				userId = userExtendBean.getUserId();                                          //操作员id
				userName = userExtendBean.getUserName();                                      //操作员name
			}
			List<MtOrder> orderList = JSON.parseArray(json, MtOrder.class);
			for(MtOrder order : orderList){
				try{
					orderService.updateOfflineRefundOrder(order, userId, userName);
					success++;
				}catch(Exception e){
					fail++;
				}
			}
			returnMap.put("msg", "退款成功"+success+"条\n退款失败"+fail+"条");
		}else{
			returnMap.put("msg", "退款失败！");
		}
		return returnMap;
	}
	
	/**
	 * @Title getRefundMoney
	 * @Description: 执行收退款
	 * @Author Liujf
	 * @CreateDate 2016年4月7日 下午3:46:45
	 * @return
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/getRefundMoney")
	@ResponseBody
	public Map<String, Object> getRefundMoney(){
		String json = ServletRequestUtils.getStringParameter(request, "param", "");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if(json!= null && !"".equals(json)){
			//从session中取操作员id和操作员名称
			String userId = "";
			String userName = "";
			int success = 0;                                                                  //成功的记录条数
			int fail = 0;                                                                     //失败的记录条数
			UserExtendBean userExtendBean = (UserExtendBean)session.getAttribute("user");     //session中获取当前登录操作员的对象
			if(userExtendBean!=null){
				userId = userExtendBean.getUserId();                                          //操作员id
				userName = userExtendBean.getUserName();                                      //操作员name
			}
			List<MtGetpaymoney> getPayMoneyList = JSON.parseArray(json, MtGetpaymoney.class);
			for(MtGetpaymoney getpaymoney : getPayMoneyList){
				try{
					orderService.updateGetRefundOrder(getpaymoney, userId, userName);
					success++;
				}catch(Exception e){
					fail++;
				}
			}
			returnMap.put("msg", "收退款成功"+success+"条\n收退款失败"+fail+"条");
		}else{
			returnMap.put("msg", "收退款失败！");
		}
		return returnMap;
	}
	
	/**
	 * @Title queryPayMoneyOrderForSettle
	 * @Description: 为结款单查询待付款订单
	 * @Author Liujf
	 * @CreateDate 2016年7月29日 下午4:04:33
	 * @return
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryPayMoneyOrderForSettle")
	@ResponseBody
	public Map<String, Object> queryPayMoneyOrderForSettle() {
		Map<String, Object> map = new HashMap<String, Object>();
		String goodsName = request.getParameter("goodsName");
		String orderId = request.getParameter("orderId");
		String buyersId = request.getParameter("buyersId");
		String sellersId = request.getParameter("sellersId");
		if (goodsName != null && !"".equals(goodsName)) {
			map.put("goodsName", goodsName);
		}
		if (orderId != null && !"".equals(orderId)) {
			map.put("ordertitleNumber", orderId);
		}
		if (buyersId != null && !"".equals(buyersId)) {
			map.put("buyersId", buyersId);
		}
		if (sellersId != null && !"".equals(sellersId)) {
			map.put("sellersId", sellersId);
		}
		// 执行条件查询总数
		int count = orderService.queryPayMoneyOrderForSettle(map).size();
		List<MtOrder> result = orderService.queryPayMoneyOrderForSettle(map);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("total", count);
		returnMap.put("data", result);
		return returnMap;
	}
	
	/**
	 * @Title 买家会员Id  卖家会员Id
	 * @Description: 查询地址 银行
	 * @Author Liujf
	 * @CreateDate 2016年6月7日 下午3:46:45
	 * @return
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/queryAddressAccount")
	@ResponseBody
	public Map<String, Object> queryAddressAccount(
			@RequestParam(value = "buyersId", required = false) String buyersId,
			@RequestParam(value = "sellersId", required = false) String sellersId) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, String> buyerMap = new HashMap<String, String>();
		Map<String, String> sellerMap = new HashMap<String, String>();
		if(buyersId!=null&&!"".equals(buyersId)){
			buyerMap.put("mmbId", buyersId);
			List<MtMmbWarehouse> buyersAddressList = mmbWarehouseService.getMmbWareHouse(buyerMap);
			List<MtMmbBankAccount> buyersAccountList = mmbBankAccountService.getMmbBankAccount(buyerMap);
			for(MtMmbBankAccount mtMmbBankAccount : buyersAccountList){
				mtMmbBankAccount.setBankname(mtMmbBankAccount.getBankname()+" "+mtMmbBankAccount.getAccountno());
			}
			returnMap.put("buyersAddressList", buyersAddressList);
			returnMap.put("buyersAccountList", buyersAccountList);
		}
		if(sellersId!=null&&!"".equals(sellersId)){
			sellerMap.put("mmbId", sellersId);
			List<MtMmbWarehouse> sellersAddressList = mmbWarehouseService.getMmbWareHouse(sellerMap);
			List<MtMmbBankAccount> sellersAccountList = mmbBankAccountService.getMmbBankAccount(sellerMap);
			for(MtMmbBankAccount mtMmbBankAccount : sellersAccountList){
				mtMmbBankAccount.setBankname(mtMmbBankAccount.getBankname()+" "+mtMmbBankAccount.getAccountno());
			}
			returnMap.put("sellersAddressList", sellersAddressList);
			returnMap.put("sellersAccountList", sellersAccountList);
		}
		return returnMap;
	}

}
