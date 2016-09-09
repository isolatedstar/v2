package com.zllh.mall.order.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zllh.mall.common.dao.MtGetgoodsMapper;
import com.zllh.mall.common.dao.MtGetpaymoneyMapper;
import com.zllh.mall.common.dao.MtOrderMapper;
import com.zllh.mall.common.dao.MtOrdertitleMapper;
import com.zllh.mall.common.dao.MtSendgoodsMapper;
import com.zllh.mall.common.model.MtGetgoods;
import com.zllh.mall.common.model.MtGetpaymoney;
import com.zllh.mall.common.model.MtOrder;
import com.zllh.mall.common.model.MtOrdertitle;
import com.zllh.mall.common.model.MtSendgoods;
import com.zllh.mall.order.service.IOrderService;
import com.zllh.utils.base.Utils;
import com.zllh.utils.common.UUIDCreater;

/**
 * 
 * @ClassName OrderServiceImpl
 * @PackageName com.zllh.mall.order.service.impl
 * @Description 订单服务
 * @author Liujf
 * @Date 2016年5月18日 上午11:02:16
 * @Version V1.0
 */
@Service
public class OrderServiceImpl implements IOrderService{
	
	@Resource
	private MtOrdertitleMapper mtOrdertitleMapper;
	
	@Resource
	private MtOrderMapper mtOrderMapper;
	
	@Resource
	private MtSendgoodsMapper mtSendgoodsMapper;
	
	@Resource
	private MtGetgoodsMapper mtGetgoodsMapper;
	
	@Resource
	private MtGetpaymoneyMapper mtGetpaymoneyMapper;
	
	@Override
	public boolean createOrder(MtOrdertitle record,String userId,String userName) {
		boolean result = false;
		// 订单表保存标识
		int mo = 0;
		// 订单表List
		List<?> orderList = record.getOrderList();
		record.setExecuteStatus(1);
		//人工终止状态设置为1，即不终止
		record.setStopStatus(1);
		record.setCreateTime(new Date());
		record.setUserId(userId);
		record.setUserName(userName);
		record.setOperateTime(new Date());
		int mot = mtOrdertitleMapper.insert(record);
		// 循环遍历订单表List,组装数据并保存
		for (int i = 0; i < orderList.size(); i++) {
			MtOrder order = (MtOrder) orderList.get(i);
			String id = UUIDCreater.getUUID();
			order.setId(id);
			order.setOredertitleCode(record.getId());
			order.setBuyersId(record.getBuyersId());
			order.setBuyersName(record.getBuyersName());
			order.setSellersId(record.getSellersId());
			order.setSellersName(record.getSellersName());
			order.setStopStatus(record.getStopStatus());
			order.setStatus(record.getStatus());
			order.setCreateTime(record.getCreateTime());
			order.setUserId(userId);
			order.setUserName(userName);
			order.setWorkflowType(record.getWorkflowTypeId());
			order.setExecuteStatus(record.getExecuteStatus());
			this.calculateStatus(order);
			this.calculateExecute(order);
			mo = mtOrderMapper.insert(order);
		}
		if(mo>0&&mot>0){
			result = true;
		}
		return result;
	}

	@Override
	public boolean updateOrder(MtOrdertitle record,String userId,String userName){
		boolean result = false;
		// 订单表保存标识
		int mo = 0;
		// 订单表List
		List<?> orderList = record.getOrderList();
		record.setUserId(userId);
		record.setUserName(userName);
		record.setOperateTime(new Date());
		int mot = mtOrdertitleMapper.updateByObject(record);
		// 循环遍历订单表List,组装数据并保存
		for (int i = 0; i < orderList.size(); i++) {
			MtOrder order = (MtOrder) orderList.get(i);
			order.setWorkflowType(record.getWorkflowTypeId());
			order.setStatus(record.getStatus());
			order.setExecuteStatus(record.getExecuteStatus());
			this.calculateStatus(order);
			this.calculateExecute(order);
			mo = mtOrderMapper.updateByObject(order);
		}
		// 订单标题表保存标识
		if(mo>0&&mot>0){
			result = true;
		}
		return result;
	}

	@Override
	public boolean updateLockOrder(MtOrdertitle mtOrdertitle,String userId,String userName) {
		boolean result = false;
		// 订单表保存标识
		int mo = 0;
		int mot = 0;
		MtOrder mtOrder = new MtOrder();
		mtOrder.setOredertitleCode(mtOrdertitle.getId());
		// 查询该订单下的所有订单货物
		List<MtOrder> orderList = mtOrderMapper.searchMtOrder(mtOrder);
		//货款两清，直接创建结束订单
		if (mtOrdertitle.getWorkflowTypeId() == 1) {
			mtOrdertitle.setExecuteStatus(2);
			mtOrdertitle.setStatus(4);
			mtOrdertitle.setFinishTime(new Date());
		}
		mtOrdertitle.setUserId(userId);
		mtOrdertitle.setUserName(userName);
		mtOrdertitle.setOperateTime(new Date());
		//修改订单标题表中状态
		mot = mtOrdertitleMapper.updateByObject(mtOrdertitle);
		// 循环修改订单表中的状态
		for (MtOrder order : orderList) {
			order.setStatus(mtOrdertitle.getStatus());
			order.setUserId(mtOrdertitle.getUserId());
			order.setUserName(mtOrdertitle.getUserName());
			order.setLockTime(mtOrdertitle.getLockTime());
			if(mtOrdertitle.getWorkflowTypeId()==1||mtOrdertitle.getWorkflowTypeId()==3){
				//发货表一条记录
				MtSendgoods mtSendgoods = new MtSendgoods();
				mtSendgoods.setId(UUIDCreater.getUUID());
				mtSendgoods.setOrderId(order.getId());
				mtSendgoods.setOrdertitleId(order.getOredertitleCode());
				mtSendgoods.setShipperId(order.getSellersId());
				mtSendgoods.setShipperName(order.getSellersName());
				mtSendgoods.setGoodsId(order.getGoodsId());
				mtSendgoods.setNum(order.getGoodsNum());
				mtSendgoods.setUserId(userId);
				mtSendgoods.setUserName(userName);
				mtSendgoods.setCreateTime(new Date());
				mtSendgoods.setSendgoodsTime(mtSendgoods.getCreateTime());
				mtSendgoods.setSendDirct(1);
				mo = mtSendgoodsMapper.insert(mtSendgoods);
				//收货表一条记录
				MtGetgoods mtGetgoods = new MtGetgoods();
				mtGetgoods.setId(UUIDCreater.getUUID());
				mtGetgoods.setOrderId(order.getId());
				mtGetgoods.setOrdertitleId(order.getOredertitleCode());
				mtGetgoods.setReceiverId(order.getBuyersId());
				mtGetgoods.setReceiverName(order.getBuyersName());
				mtGetgoods.setGoodsId(order.getGoodsId());
				mtGetgoods.setNum(order.getGoodsNum());
				mtGetgoods.setUserId(userId);
				mtGetgoods.setUserName(userName);
				mtGetgoods.setCreateTime(new Date());
				mtGetgoods.setGetgoodsTime(mtGetgoods.getCreateTime());
				mtGetgoods.setReceiveDirct(1);
				mtGetgoods.setReceiveAddressId(order.getReceiveAddressId());
				mo = mtGetgoodsMapper.insert(mtGetgoods);
			}
			if(mtOrdertitle.getWorkflowTypeId()==1||mtOrdertitle.getWorkflowTypeId()==5){
				//收付款表一条记录
				MtGetpaymoney mtGetpaymoney = new MtGetpaymoney();
				mtGetpaymoney.setId(UUIDCreater.getUUID());
				mtGetpaymoney.setOrderId(order.getId());
				mtGetpaymoney.setOrdertitleId(order.getOredertitleCode());
				mtGetpaymoney.setPaymoneyId(order.getBuyersId());
				mtGetpaymoney.setPaymoneyName(order.getBuyersName());
				mtGetpaymoney.setPaymoneyCode(order.getPaymoneyCode());
				mtGetpaymoney.setPaymoneyTime(new Date());
				mtGetpaymoney.setPaymoneyType(1);
				mtGetpaymoney.setGetmoneyId(order.getSellersId());
				mtGetpaymoney.setGetmoneyName(order.getSellersName());
				mtGetpaymoney.setGetmoneyCode(mtOrdertitle.getGetAccount());
				mtGetpaymoney.setGetmoneyTime(mtGetpaymoney.getPaymoneyTime());
				mtGetpaymoney.setMoney(order.getMoney());
				mtGetpaymoney.setStatus(4);
				mtGetpaymoney.setPayDirct(1);
				mtGetpaymoney.setGetDirct(1);
				mtGetpaymoney.setRemark(order.getGetPayRemark());
				mtGetpaymoney.setPayuserId(userId);
				mtGetpaymoney.setPayuserName(userName);
				mo = mtGetpaymoneyMapper.insert(mtGetpaymoney);
			}
			this.calculateStatus(order);
			this.calculateExecute(order);
			this.checkOrderExecuteStatus(order);
			mo = mtOrderMapper.updateByObject(order);
		}
		if (mo > 0 && mot > 0) {
			result = true;
		}
		return result;
	}
	
	@Override
	public boolean updateStopOrder(MtOrdertitle mtOrdertitle,String userId,String userName) {
		boolean result = false;
		// 订单表保存标识
		int mo = 0;
		int mot = 0;

		MtOrder mtOrder = new MtOrder();
		mtOrder.setOredertitleCode(mtOrdertitle.getId());
		mtOrdertitle.setUserId(userId);
		mtOrdertitle.setUserName(userName);
		mtOrdertitle.setOperateTime(new Date());
		// 查询该订单下的所有订单货物
		List<MtOrder> orderList = mtOrderMapper.searchMtOrder(mtOrder);
		//修改订单标题表中状态
		mot = mtOrdertitleMapper.updateByObject(mtOrdertitle);
		// 循环修改订单表中的状态
		for (MtOrder order : orderList) {
			order.setStopStatus(mtOrdertitle.getStopStatus());
			order.setExecuteStatus(mtOrdertitle.getExecuteStatus());
			order.setFinishTime(mtOrdertitle.getFinishTime());
			mo = mtOrderMapper.updateByObject(order);
		}
		if (mo > 0 && mot > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public List<MtOrdertitle> queryOrderList(Map<String, Object> record) {
		List<MtOrdertitle> orderTitleList = mtOrdertitleMapper.searchOrderTitle(record);
		return orderTitleList;
	}
	
	@Override
	public List<MtOrdertitle> queryPendingOrderList(Map<String, Object> record) {
		List<MtOrdertitle> orderTitleList = mtOrdertitleMapper.searchPendingOrderTitle(record);
		return orderTitleList;
	}
	
	@Override
	public List<MtOrdertitle> queryMyPendingOrderList(Map<String, Object> record) {
		List<MtOrdertitle> orderTitleList = mtOrdertitleMapper.searchMyPendingOrderTitle(record);
		return orderTitleList;
	}
	
	@Override
	public List<MtOrdertitle> queryOrderByMemberId(Map<String, Object> record) {
		List<MtOrdertitle> orderTitleList = mtOrdertitleMapper.selectByMemberId(record);
		return orderTitleList;
	}
	
	@Override
	public List<MtOrder> querySettleOrderList(MtOrder mtOrder) {
		List<MtOrder> orderList = mtOrderMapper.searchSettleMtOrder(mtOrder);
		return orderList;
	}

	@Override
	public MtOrdertitle queryOrder(String id) {
		MtOrdertitle ordertitle = mtOrdertitleMapper.selectByPrimaryKey(id);
		MtOrder mtOrder = new MtOrder();
		mtOrder.setOredertitleCode(id);
		List<MtOrder> orderList = mtOrderMapper.searchMtOrder(mtOrder);
		if(orderList.size()>0){
			ordertitle.setOrderList(orderList);
		}
		return ordertitle;
	}

	@Override
	public boolean updateAbolishOrder(MtOrdertitle mtOrdertitle,String userId,String userName) {
		boolean result = false;
		int mo = 0;
		int mot = 0;
		MtOrder mtOrder = new MtOrder();
		mtOrder.setOredertitleCode(mtOrdertitle.getId());
		mtOrdertitle.setUserId(userId);
		mtOrdertitle.setUserName(userName);
		mtOrdertitle.setOperateTime(new Date());
		List<MtOrder> orderList = mtOrderMapper.searchMtOrder(mtOrder);
		for (MtOrder order : orderList) {
			order.setStatus(mtOrdertitle.getStatus());
			mo = mtOrderMapper.updateByObject(order);
		}
		mot = mtOrdertitleMapper.updateByObject(mtOrdertitle);
		if (mo > 0 && mot > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public List<MtOrder> querySendOrder(Map<String, Object> record) {
		List<MtOrder> result = mtOrderMapper.searchSendMtOrder(record);
		return result;
	}
	
	@Override
	public List<MtOrder> queryGetOrder(Map<String, Object> record) {
		List<MtOrder> result = mtOrderMapper.searchGetMtOrder(record);
		return result;
	}
	
	@Override
	public List<MtOrder> queryReturnOrder(Map<String, Object> record) {
		List<MtOrder> result = mtOrderMapper.searchReturnMtOrder(record);
		return result;
	}
	
	@Override
	public List<MtOrder> queryGetReturnOrder(Map<String, Object> record) {
		List<MtOrder> result = mtOrderMapper.searchGetReturnMtOrder(record);
		return result;
	}

	@Override
	public List<MtOrder> queryPayMoneyOrder(Map<String, Object> record) {
		List<MtOrder> result = mtOrderMapper.searchPayMoneyMtOrder(record);
		return result;
	}

	@Override
	public List<MtGetpaymoney> queryGetMoney(Map<String, Object> record) {
		List<MtGetpaymoney> result = mtGetpaymoneyMapper.searchGetMoney(record);
		return result;
	}

	@Override
	public List<MtOrder> queryRefundOrder(Map<String, Object> record) {
		List<MtOrder> result = mtOrderMapper.searchRefundMtOrder(record);
		return result;
	}

	@Override
	public List<MtGetpaymoney> queryGetRefund(Map<String, Object> record) {
		List<MtGetpaymoney> result = mtGetpaymoneyMapper.searchGetRefund(record);
		return result;
	}
	
	@Override
	public boolean updateSendOrder(MtOrder order,String userId,String userName) {
		boolean result = false;
		int mo = 0;
		int mot = 0;
		if(order.getExeSendgoodsNum()<order.getNum()||order.getNum()<=0){
			throw new RuntimeException("发货数量不在合法范围内！");
		}
		//发货表一条记录
		MtSendgoods mtSendgoods = new MtSendgoods();
		String id = UUIDCreater.getUUID();
		mtSendgoods.setId(id);
		mtSendgoods.setOrderId(order.getId());
		mtSendgoods.setOrdertitleId(order.getOredertitleCode());
		mtSendgoods.setShipperId(order.getSellersId());
		mtSendgoods.setShipperName(order.getSellersName());
		mtSendgoods.setGoodsId(order.getGoodsId());
		mtSendgoods.setNum(order.getNum());
		mtSendgoods.setUserId(userId);
		mtSendgoods.setUserName(userName);
		mtSendgoods.setCreateTime(new Date());
		mtSendgoods.setSendgoodsTime(mtSendgoods.getCreateTime());
		mtSendgoods.setSendDirct(1);
		mo = mtSendgoodsMapper.insert(mtSendgoods);
		this.calculateStatus(order);
		this.calculateExecute(order);
		mot = mtOrderMapper.updateByObject(order);
		if (mo > 0 && mot > 0) {
			result = true;
		}
		return result;
	}
	
	@Override
	public boolean updateGetOrder(MtOrder order,String userId,String userName) {
		boolean result = false;
		int mo = 0;
		int mot = 0;
		if(order.getExeGetgoodsNum()<order.getNum()||order.getNum()<=0){
			throw new RuntimeException("收货数量不在合法范围内！");
		}
		//收货表一条记录
		MtGetgoods mtGetgoods = new MtGetgoods();
		String id = UUIDCreater.getUUID();
		mtGetgoods.setId(id);
		mtGetgoods.setOrderId(order.getId());
		mtGetgoods.setOrdertitleId(order.getOredertitleCode());
		mtGetgoods.setReceiverId(order.getBuyersId());
		mtGetgoods.setReceiverName(order.getBuyersName());
		mtGetgoods.setGoodsId(order.getGoodsId());
		mtGetgoods.setNum(order.getNum());
		mtGetgoods.setUserId(userId);
		mtGetgoods.setUserName(userName);
		mtGetgoods.setCreateTime(new Date());
		mtGetgoods.setGetgoodsTime(mtGetgoods.getCreateTime());
		mtGetgoods.setReceiveDirct(1);
		mtGetgoods.setReceiveAddressId(order.getReceiveAddressId());
		mo = mtGetgoodsMapper.insert(mtGetgoods);
		this.calculateStatus(order);
		this.calculateExecute(order);
		this.checkOrderExecuteStatus(order);
		mot = mtOrderMapper.updateByObject(order);
		if (mo > 0 && mot > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean updateReturnOrder(MtOrder order, String userId,String userName) {
		boolean result = false;
		int mo = 0;
		int mot = 0;
		if(order.getExeReturngoodsNum()<order.getNum()||order.getNum()<=0){
			throw new RuntimeException("退货数量不在合法范围内！");
		}
		//发货表一条记录
		MtSendgoods mtSendgoods = new MtSendgoods();
		String id = UUIDCreater.getUUID();
		mtSendgoods.setId(id);
		mtSendgoods.setOrderId(order.getId());
		mtSendgoods.setOrdertitleId(order.getOredertitleCode());
		mtSendgoods.setShipperId(order.getBuyersId());
		mtSendgoods.setShipperName(order.getBuyersName());
		mtSendgoods.setGoodsId(order.getGoodsId());
		mtSendgoods.setNum(order.getNum());
		mtSendgoods.setUserId(userId);
		mtSendgoods.setUserName(userName);
		mtSendgoods.setCreateTime(new Date());
		mtSendgoods.setSendgoodsTime(mtSendgoods.getCreateTime());
		mtSendgoods.setSendDirct(2);
		mo = mtSendgoodsMapper.insert(mtSendgoods);
		this.calculateStatus(order);
		this.calculateExecute(order);
		mot = mtOrderMapper.updateByObject(order);
		if (mo > 0 && mot > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean updateGetReturnOrder(MtOrder order, String userId,String userName) {
		boolean result = false;
		int mo = 0;
		int mot = 0;
		if(order.getExeGetreturngoodsNum()<order.getNum()||order.getNum()<=0){
			throw new RuntimeException("收退货数量不在合法范围内！");
		}
		//收货表一条记录
		MtGetgoods mtGetgoods = new MtGetgoods();
		String id = UUIDCreater.getUUID();
		mtGetgoods.setId(id);
		mtGetgoods.setOrderId(order.getId());
		mtGetgoods.setOrdertitleId(order.getOredertitleCode());
		mtGetgoods.setReceiverId(order.getSellersId());
		mtGetgoods.setReceiverName(order.getSellersName());
		mtGetgoods.setGoodsId(order.getGoodsId());
		mtGetgoods.setNum(order.getNum());
		mtGetgoods.setUserId(userId);
		mtGetgoods.setUserName(userName);
		mtGetgoods.setCreateTime(new Date());
		mtGetgoods.setGetgoodsTime(mtGetgoods.getCreateTime());
		mtGetgoods.setReceiveDirct(2);
		mtGetgoods.setReceiveAddressId(order.getReceiveAddressId());
		mo = mtGetgoodsMapper.insert(mtGetgoods);
		this.calculateStatus(order);
		this.calculateExecute(order);
		mot = mtOrderMapper.updateByObject(order);
		if (mo > 0 && mot > 0) {
			result = true;
		}
		return result;
	}
	
	@Override
	public boolean updatePayMoneyOfflineOrder(MtOrder order, String userId,String userName) {
		boolean result = false;
		int mo = 0;
		int mot = 0;
		if(order.getExePaymoneyNum()<order.getNum()||order.getNum()<=0){
			throw new RuntimeException("付款金额不在合法范围内！");
		}
		MtGetpaymoney mtGetpaymoney = new MtGetpaymoney();
		String id = UUIDCreater.getUUID();
		mtGetpaymoney.setId(id);
		mtGetpaymoney.setOrderId(order.getId());
		mtGetpaymoney.setOrdertitleId(order.getOredertitleCode());
		mtGetpaymoney.setPaymoneyId(order.getBuyersId());
		mtGetpaymoney.setPaymoneyName(order.getBuyersName());
		mtGetpaymoney.setPaymoneyCode(order.getPaymoneyCode());
		mtGetpaymoney.setPaymoneyTime(new Date());
		mtGetpaymoney.setPaymoneyType(1);
		mtGetpaymoney.setGetmoneyId(order.getSellersId());
		mtGetpaymoney.setGetmoneyName(order.getSellersName());
		mtGetpaymoney.setMoney(order.getNum());
		//线下支付为已付状态
		mtGetpaymoney.setStatus(2);
		mtGetpaymoney.setPayDirct(1);
		mtGetpaymoney.setRemark(order.getGetPayRemark());
		mtGetpaymoney.setPayuserId(userId);
		mtGetpaymoney.setPayuserName(userName);
		
		mo = mtGetpaymoneyMapper.insert(mtGetpaymoney);
		this.calculateStatus(order);
		this.calculateExecute(order);
		mot = mtOrderMapper.updateByObject(order);
		if (mo > 0 && mot > 0) {
			result = true;
		}
		return result;
	}
	
	/*暂时不能用线上支付，以后线上支付时要调用支付系统，传收付款记录id，金额等待定。然后支付系统会把银行的支付页面返回，在
	 * 浏览器中打开页面支付，银行再调支付系统的方法告诉是否成功，支付系统再调用商城的方法告诉是否支付成功
	 * (non-Javadoc)
	 * @see com.zllh.main.mall.dealmmanage.service.IOrderService#updatePayMoneyOnlineOrder(java.util.List, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean updatePayMoneyOnlineOrder(List<MtOrder> orderList, String userId,String userName) {
		boolean result = false;
		int mo = 0;
		int mot = 0;
		for(MtOrder order : orderList){
			MtGetpaymoney mtGetpaymoney = new MtGetpaymoney();
			String id = UUIDCreater.getUUID();
			mtGetpaymoney.setId(id);
			mtGetpaymoney.setOrderId(order.getId());
			mtGetpaymoney.setOrdertitleId(order.getOredertitleCode());
			mtGetpaymoney.setPaymoneyId(order.getBuyersId());
			mtGetpaymoney.setPaymoneyName(order.getBuyersName());
			mtGetpaymoney.setPaymoneyCode(order.getPaymoneyCode());
			mtGetpaymoney.setPaymoneyTime(new Date());
			mtGetpaymoney.setPaymoneyType(2);
			mtGetpaymoney.setGetmoneyId(order.getSellersId());
			mtGetpaymoney.setGetmoneyName(order.getSellersName());
			//从协议里获取收款账号
//			mtGetpaymoney.setGetmoneyCode(getmoneyCode);
			//收款时写入
//			mtGetpaymoney.setGetmoneyTime(getmoneyTime);
			mtGetpaymoney.setMoney(order.getNum());
			mtGetpaymoney.setStatus(1);
			//线上支付和借款单支付状态为未定
			mtGetpaymoney.setPayDirct(1);
//			mtGetpaymoney.setGetDirct(getDirct);
			//页面校验长度
			mtGetpaymoney.setRemark(order.getGetPayRemark());
			mtGetpaymoney.setPayuserId(userId);
			mtGetpaymoney.setPayuserName(userName);
			
			mo = mtGetpaymoneyMapper.insert(mtGetpaymoney);
			this.calculateStatus(order);
			this.calculateExecute(order);
			mot = mtOrderMapper.updateByObject(order);
		}
		if (mo > 0 && mot > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean updateGetMoneyOrder(MtGetpaymoney getpaymoney, String userId,String userName) {
		boolean result = false;
		int mo = 0;
		int mot = 0;
		MtOrder order = mtOrderMapper.selectByPrimaryKey(getpaymoney.getOrderId());
		getpaymoney.setGetmoneyTime(new Date());
		String getmoneyCode = mtOrdertitleMapper.selectByPrimaryKey(order.getOredertitleCode()).getGetAccount();
		getpaymoney.setGetmoneyCode(getmoneyCode);
		getpaymoney.setGetuserId(userId);
		getpaymoney.setGetuserName(userName);
		//状态为签收
		getpaymoney.setStatus(4);
		//收款状态为正常收款
		getpaymoney.setGetDirct(1);
		mo = mtGetpaymoneyMapper.updateByObject(getpaymoney);
		this.calculateStatus(order);
		this.calculateExecute(order);
		this.checkOrderExecuteStatus(order);
		mot = mtOrderMapper.updateByObject(order);
		if (mo > 0 && mot > 0) {
			result = true;
		}else{
			throw new RuntimeException("收款失败！");
		}
		return result;
	}

	@Override
	public boolean updateOfflineRefundOrder(MtOrder order, String userId, String userName) {
		boolean result = false;
		int mo = 0;
		int mot = 0;
		if(order.getExeRefundNum()<order.getNum()||order.getNum()<=0){
			throw new RuntimeException("退款金额不在合法范围内！");
		}
		MtGetpaymoney mtGetpaymoney = new MtGetpaymoney();
		String id = UUIDCreater.getUUID();
		mtGetpaymoney.setId(id);
		mtGetpaymoney.setOrderId(order.getId());
		mtGetpaymoney.setOrdertitleId(order.getOredertitleCode());
		mtGetpaymoney.setPaymoneyId(order.getSellersId());
		mtGetpaymoney.setPaymoneyName(order.getSellersName());
		mtGetpaymoney.setPaymoneyCode(order.getPaymoneyCode());
		mtGetpaymoney.setPaymoneyTime(new Date());
		mtGetpaymoney.setPaymoneyType(1);
		mtGetpaymoney.setGetmoneyId(order.getBuyersId());
		mtGetpaymoney.setGetmoneyName(order.getBuyersName());
		mtGetpaymoney.setMoney(order.getNum());
		mtGetpaymoney.setStatus(3);
		mtGetpaymoney.setPayDirct(2);
		//页面校验长度
		mtGetpaymoney.setRemark(order.getGetPayRemark());
		mtGetpaymoney.setPayuserId(userId);
		mtGetpaymoney.setPayuserName(userName);
		mo = mtGetpaymoneyMapper.insert(mtGetpaymoney);
		this.calculateStatus(order);
		this.calculateExecute(order);
		mot = mtOrderMapper.updateByObject(order);
		if (mo > 0 && mot > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean updateGetRefundOrder(MtGetpaymoney getpaymoney, String userId, String userName) {
		boolean result = false;
		int mo = 0;
		int mot = 0;
		MtOrder order = mtOrderMapper.selectByPrimaryKey(getpaymoney.getOrderId());
		getpaymoney.setGetmoneyTime(new Date());
		String getmoneyCode = mtOrdertitleMapper.selectByPrimaryKey(order.getOredertitleCode()).getPayAccount();
		getpaymoney.setGetmoneyCode(getmoneyCode);
		getpaymoney.setGetuserId(userId);
		getpaymoney.setGetuserName(userName);
		getpaymoney.setStatus(4);
		//收款状态为收退款
		getpaymoney.setGetDirct(2);
		mo = mtGetpaymoneyMapper.updateByObject(getpaymoney);
		this.calculateStatus(order);
		this.calculateExecute(order);
		mot = mtOrderMapper.updateByObject(order);
		if (mo > 0 && mot > 0) {
			result = true;
		}else{
			throw new RuntimeException("收退款失败！");
		}
		return result;
	}
	
	public void calculateStatus(MtOrder order) {
		/*
		 * 定义已发、已退、已收、已收退、已付、已收款、已退款、收退款。
		 * 之后在不同的动作中计算上述变量，最后统一计算状态量
		 */
		//已发：正向发货记录数量统计
		double sendYet = 0;
		// 已收发：正向收货记录数量统计
		double getYet = 0;
		// 已退：反向发货记录数量统计
		double returnYet = 0;
		// 已收退：反向收货记录数量统计
		double getReturnYet = 0;
		// 已付=正向收付记录金额统计
		double payYet = 0;
		// 已收付=状态为签收以上的正向收付款记录金额统计
		double receiptYet = 0;
		// 已退=反向收付记录金额统计
		double refundYet = 0;
		// 已收退=状态为签收以上的反向收付款记录金额统计
		double getRefundYet = 0;
		// 已锁定：状态为未定的收付款记录金额统计
		double lockYet = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", order.getId());
		map.put("shipperId", order.getSellersId());
		map.put("receiverId", order.getBuyersId());
		// 初始化订单
		if (order.getWorkflowType() == 1 || order.getWorkflowType() == 3) {// 货款两清或先货后款已交货将发货数量和收货数量设为货物数量
			sendYet = order.getGoodsNum();
			getYet = order.getGoodsNum();
		}
		if (order.getWorkflowType() == 1 || order.getWorkflowType() == 5) {// 货款两清或先款后货已交款将付款和收款设为商品总金额
			payYet = order.getMoney();
			receiptYet = order.getMoney();
		}
		// 已发：正向发货记录数量统计
		sendYet = mtSendgoodsMapper.selectForwardSumSendGoods(map);
		// 已收发：正向收货记录数量统计
		getYet = mtGetgoodsMapper.selectForwardSumGetGoods(map);
		// 已退：反向发货记录数量统计
		returnYet = mtSendgoodsMapper.selectReverseSumSendGoods(map);
		// 已收退：反向收货记录数量统计
		getReturnYet = mtGetgoodsMapper.selectReverseSumGetGoods(map);
		// 已付=正向收付记录金额统计
		payYet = mtGetpaymoneyMapper.selectForwardSumPay(map);
		// 已收付=状态为签收以上的正向收付款记录金额统计
		receiptYet = mtGetpaymoneyMapper.selectForwardSumReceipt(map);
		// 已退=反向收付记录金额统计
		refundYet = mtGetpaymoneyMapper.selectReverseSumPay(map);
		// 已收退=状态为签收以上的反向收付款记录金额统计
		getRefundYet = mtGetpaymoneyMapper.selectReverseSumReceipt(map);
		// 已锁定：状态为未定的收付款记录金额统计
		lockYet = mtGetpaymoneyMapper.selectSumLock(map);
		order.setSendgoodsNum(Utils.bigDecimalInterest.sub(sendYet,
				getReturnYet));
		order.setGetgoodsNum(Utils.bigDecimalInterest.sub(getYet, returnYet));
		order.setReturngoodsNum(returnYet);
		order.setGetreturngoodsNum(getReturnYet);
		order.setPaymoneyNum(Utils.bigDecimalInterest.sub(payYet, getRefundYet));
		order.setGetmoneyNum(Utils.bigDecimalInterest
				.sub(receiptYet, refundYet));
		order.setRefundNum(refundYet);
		order.setGetrefundNum(getRefundYet);
		order.setLockmoneyNum(lockYet);
	}
	
	public void calculateExecute(MtOrder order) {
		if(order.getWorkflowType()==1){                                               							  //货款两清
			order.setExeSendgoodsNum((double) 0);
			order.setExePaymoneyNum((double) 0);
		}else if(order.getWorkflowType()==2||order.getWorkflowType()==3){                                         //先货后款
			//发货执行量=订单数量-发货数量
			order.setExeSendgoodsNum(Utils.bigDecimalInterest.sub(order.getGoodsNum(), order.getSendgoodsNum()));
			//付款执行量=收货数量*单价-付款金额-锁定金额
			order.setExePaymoneyNum(Utils.bigDecimalInterest.sub(
					Utils.bigDecimalInterest.mul(order.getGetgoodsNum(),
							order.getPrice()),
					Utils.bigDecimalInterest.add(order.getPaymoneyNum(),
							order.getLockmoneyNum())));
		}else if(order.getWorkflowType()==4||order.getWorkflowType()==5){                                         //先款后货
			//发货执行量=收款金额/单价-发货数量
			order.setExeSendgoodsNum(Utils.bigDecimalInterest.sub(
					Utils.bigDecimalInterest.div(order.getGetmoneyNum(),
							order.getPrice(), 2), order.getSendgoodsNum()));
			//付款执行量=订单金额-付款金额-锁定金额
			order.setExePaymoneyNum(Utils.bigDecimalInterest.sub(
					Utils.bigDecimalInterest.sub(order.getMoney(),
							order.getPaymoneyNum()), order.getLockmoneyNum()));
		}
		//收货执行量=发货数量-收货数量-退货数量+收退货数量
		order.setExeGetgoodsNum(Utils.bigDecimalInterest.add(
				Utils.bigDecimalInterest.sub(Utils.bigDecimalInterest.sub(
						order.getSendgoodsNum(), order.getGetgoodsNum()), order
						.getReturngoodsNum()), order.getGetreturngoodsNum()));
		//退货执行量=收货数量
		order.setExeReturngoodsNum(order.getGetgoodsNum());
		//收退货执行量=退货数量-收退货数量
		order.setExeGetreturngoodsNum(Utils.bigDecimalInterest.sub(order.getReturngoodsNum(),order.getGetreturngoodsNum()));
		//收款执行量=付款金额-收款金额-退款金额+收退款金额
		order.setExeGetmoneyNum(Utils.bigDecimalInterest.add(
				Utils.bigDecimalInterest.sub(Utils.bigDecimalInterest.sub(
						order.getPaymoneyNum(), order.getGetmoneyNum()), order
						.getRefundNum()), order.getGetrefundNum()));
		//退款执行量=收款金额
		order.setExeRefundNum(order.getGetmoneyNum());
		//收退款执行量=退款金额-收退款金额
		order.setExeGetrefundNum(Utils.bigDecimalInterest.sub(order.getRefundNum(),order.getGetrefundNum()));
	}
	
	public void checkOrderExecuteStatus(MtOrder order){
		Set<Double> set = new HashSet<Double>();
		set.add(0.0);
		set.add(order.getExeGetgoodsNum());
		set.add(order.getExeGetmoneyNum());
		set.add(order.getExePaymoneyNum());
		set.add(order.getExeSendgoodsNum());
		if(set.size()==1){
			order.setExecuteStatus(2);
			order.setFinishTime(new Date());
		}
	}
	
	public void checkOrdertitleExecuteStatus(String id){
		MtOrdertitle ordertitle = new MtOrdertitle();
		MtOrder mtOrder = new MtOrder();
		boolean flag = true;
		mtOrder.setOredertitleCode(id);
		ordertitle.setId(id);
		List<MtOrder> orderList = mtOrderMapper.searchMtOrder(mtOrder);
		for (MtOrder order : orderList) {
			if(order.getExecuteStatus()!=2){
				flag = false;
				break;
			}
		}
		if(flag){
			ordertitle.setExecuteStatus(2);
			ordertitle.setFinishTime(new Date());
			mtOrdertitleMapper.updateByObject(ordertitle);
		}
	}

	@Override
	public List<MtOrder> queryPayMoneyOrderForSettle(Map<String, Object> record) {
		List<MtOrder> result = mtOrderMapper.searchPayMoneyMtOrderForSettle(record);
		return result;
	}
}