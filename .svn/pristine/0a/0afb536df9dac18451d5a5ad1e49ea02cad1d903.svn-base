package com.zllh.mall.order.service;


import java.util.List;
import java.util.Map;

import com.zllh.mall.common.model.MtGetpaymoney;
import com.zllh.mall.common.model.MtOrder;
import com.zllh.mall.common.model.MtOrdertitle;

/**
 * 
 * @ClassName IOrderService
 * @PackageName com.zllh.mall.order.service
 * @Description 订单服务
 * @author Liujf
 * @Date 2016年5月10日 上午10:30:46
 * @Version V1.0
 */
public interface IOrderService {
	
	/**
	 * @Title createOrder
	 * @Description: 创建订单标题和订单
	 * @Author Liujf
	 * @CreateDate 2016年5月19日 下午2:25:18
	 * @param record
	 * @param userId
	 * @param userName
	 * @return
	 * @return boolean
	 */
	public boolean createOrder(MtOrdertitle record,String userId,String userName);
    
	/**
	 * @Title updateOrder
	 * @Description: 修改订单
	 * @Author Liujf
	 * @CreateDate 2016年5月19日 下午2:25:30
	 * @param record
	 * @param userId
	 * @param userName
	 * @return
	 * @return boolean
	 */
	public boolean updateOrder(MtOrdertitle record,String userId,String userName);
	
	/**
	 * @Title updateAbolishOrder
	 * @Description: 订单作废
	 * @Author Liujf
	 * @CreateDate 2016年3月31日 下午1:44:07
	 * @param mtOrdertitle
	 * @return
	 * @return boolean
	 */
	public boolean updateAbolishOrder(MtOrdertitle mtOrdertitle,String userId,String userName);
	
	/**
	 * @Title updateLockOrder
	 * @Description: 锁定订单
	 * @Author Liujf
	 * @CreateDate 2016年3月31日 下午1:43:10
	 * @param mtOrder
	 * @return
	 * @return boolean
	 */
	public boolean updateLockOrder(MtOrdertitle mtOrder,String userId,String userName);
	
	/**
	 * @Title updateStopOrder
	 * @Description: 终止订单
	 * @Author Liujf
	 * @CreateDate 2016年3月31日 下午1:45:21
	 * @param mtOrdertitle
	 * @return
	 * @return boolean
	 */
	public boolean updateStopOrder(MtOrdertitle mtOrdertitle,String userId,String userName);
	
	/**
	 * @Title queryOrderList
	 * @Description: 查询订单
	 * @Author Liujf
	 * @CreateDate 2016年5月10日 上午10:31:27
	 * @param record
	 * @return
	 * @return List<MtOrdertitle>
	 */
	public List<MtOrdertitle> queryOrderList(Map<String, Object> record);
	
	/**
	 * @Title queryOrderByMemberId
	 * @Description: 根据会员id查询订单标题
	 * @Author Liujf
	 * @CreateDate 2016年6月7日 下午2:24:30
	 * @param record
	 * @return
	 * @return List<MtOrdertitle>
	 */
	public List<MtOrdertitle> queryOrderByMemberId(Map<String, Object> record);
	
	/**
	 * @Title queryOrderList
	 * @Description: 查询结款单
	 * @Author Liujf
	 * @CreateDate 2016年5月18日 下午2:09:50
	 * @param mtOrder
	 * @return
	 * @return List<MtOrder>
	 */
	public List<MtOrder> querySettleOrderList(MtOrder mtOrder);
	
	/**
	 * @Title queryPendingOrderList
	 * @Description: 查询待定订单
	 * @Author Liujf
	 * @CreateDate 2016年5月10日 上午10:30:18
	 * @param record
	 * @return
	 * @return List<MtOrdertitle>
	 */
	public List<MtOrdertitle> queryPendingOrderList(Map<String, Object> record);
	
	/**
	 * @Title queryMyPendingOrderList
	 * @Description: 查询我的待定订单
	 * @Author Liujf
	 * @CreateDate 2016年8月23日 下午2:29:48
	 * @param record
	 * @return
	 * @return List<MtOrdertitle>
	 */
	public List<MtOrdertitle> queryMyPendingOrderList(Map<String, Object> record);
	
	/**
	 * @Title queryOrder
	 * @Description: 根据订单标题id查询订单标题和所属订单
	 * @Author Liujf
	 * @CreateDate 2016年3月31日 下午1:45:41
	 * @param id
	 * @return
	 * @return MtOrdertitle
	 */
	public MtOrdertitle queryOrder(String id);
	
	/**
	 * @Title querySendOrder
	 * @Description: 查询发货订单信息
	 * @Author Liujf
	 * @CreateDate 2016年3月30日 上午10:08:41
	 * @param record
	 * @return
	 * @return List<MtOrder>
	 */
	public List<MtOrder> querySendOrder(Map<String, Object> record);
	
	/**
	 * @Title queryGetOrder
	 * @Description: 查询收货订单信息
	 * @Author Liujf
	 * @CreateDate 2016年3月30日 下午2:30:21
	 * @param record
	 * @return
	 * @return List<MtOrder>
	 */
	public List<MtOrder> queryGetOrder(Map<String, Object> record);
	
	/**
	 * @Title queryReturnOrder
	 * @Description: 查询退货订单信息
	 * @Author Liujf
	 * @CreateDate 2016年3月31日 上午11:29:16
	 * @param record
	 * @return
	 * @return List<MtOrder>
	 */
	public List<MtOrder> queryReturnOrder(Map<String, Object> record);
	
	/**
	 * @Title queryGetReturnOrder
	 * @Description: 查询收退货信息
	 * @Author Liujf
	 * @CreateDate 2016年3月31日 下午1:39:43
	 * @param record
	 * @return
	 * @return List<MtOrder>
	 */
	public List<MtOrder> queryGetReturnOrder(Map<String, Object> record);
	
	/**
	 * @Title queryPayMoneyOrder
	 * @Description: 查询付款信息
	 * @Author Liujf
	 * @CreateDate 2016年4月5日 下午1:45:35
	 * @param record
	 * @return
	 * @return List<MtOrder>
	 */
	public List<MtOrder> queryPayMoneyOrder(Map<String, Object> record);
	
	/**
	 * @Title queryPayMoneyOrderForSettle
	 * @Description: 为结款单查询待定订单信息
	 * @Author Liujf
	 * @CreateDate 2016年7月29日 下午4:13:06
	 * @param record
	 * @return
	 * @return List<MtOrder>
	 */
	public List<MtOrder> queryPayMoneyOrderForSettle(Map<String, Object> record);
	
	/**
	 * @Title queryGetMoneyOrder
	 * @Description: 查询收款信息
	 * @Author Liujf
	 * @CreateDate 2016年4月5日 下午1:45:47
	 * @param record
	 * @return
	 * @return List<MtOrder>
	 */
	public List<MtGetpaymoney> queryGetMoney(Map<String, Object> record);
	
	/**
	 * @Title queryRefundOrder
	 * @Description: 查询退款信息
	 * @Author Liujf
	 * @CreateDate 2016年4月5日 下午1:46:00
	 * @param record
	 * @return
	 * @return List<MtOrder>
	 */
	public List<MtOrder> queryRefundOrder(Map<String, Object> record);
	
	/**
	 * @Title queryGetRefundOrder
	 * @Description: 查询收退款信息
	 * @Author Liujf
	 * @CreateDate 2016年4月5日 下午1:46:12
	 * @param record
	 * @return
	 * @return List<MtOrder>
	 */
	public List<MtGetpaymoney> queryGetRefund(Map<String, Object> record);
	
	/**
	 * @Title updateSendOrder
	 * @Description: 执行发货
	 * @Author Liujf
	 * @CreateDate 2016年3月30日 下午2:59:23
	 * @param orderList
	 * @param userId
	 * @param userName
	 * @return
	 * @return boolean
	 */
	public boolean updateSendOrder(MtOrder order,String userId,String userName);
	
	/**
	 * @Title updateGetOrder
	 * @Description: 执行收货
	 * @Author Liujf
	 * @CreateDate 2016年3月30日 下午2:59:49
	 * @param orderList
	 * @param userId
	 * @param userName
	 * @return
	 * @return boolean
	 */
	public boolean updateGetOrder(MtOrder order,String userId,String userName);
	
	/**
	 * @Title updateReturnOrder
	 * @Description: 执行退货
	 * @Author Liujf
	 * @CreateDate 2016年3月31日 下午1:57:24
	 * @param orderList
	 * @param userId
	 * @param userName
	 * @return
	 * @return boolean
	 */
	public boolean updateReturnOrder(MtOrder order,String userId,String userName);
	
	/**
	 * @Title updateGetReturnOrder
	 * @Description: 执行收退货
	 * @Author Liujf
	 * @CreateDate 2016年3月31日 下午1:57:28
	 * @param orderList
	 * @param userId
	 * @param userName
	 * @return
	 * @return boolean
	 */
	public boolean updateGetReturnOrder(MtOrder order,String userId,String userName);
	
	/**
	 * @Title updatePayMoneyOrder
	 * @Description: 执行线下付款
	 * @Author Liujf
	 * @CreateDate 2016年4月5日 下午1:44:29
	 * @param orderList
	 * @param userId
	 * @param userName
	 * @return
	 * @return boolean
	 */
	public boolean updatePayMoneyOfflineOrder(MtOrder order,String userId,String userName);
	
	/**
	 * @Title updatePayMoneyOnlineOrder
	 * @Description: 执行线上付款
	 * @Author Liujf
	 * @CreateDate 2016年4月6日 下午2:33:07
	 * @param orderList
	 * @param userId
	 * @param userName
	 * @return
	 * @return boolean
	 */
	public boolean updatePayMoneyOnlineOrder(List<MtOrder> orderList,String userId,String userName);
	
	/**
	 * @Title updateGetMoneyOrder
	 * @Description: 执行收款
	 * @Author Liujf
	 * @CreateDate 2016年4月5日 下午1:44:42
	 * @param orderList
	 * @param userId
	 * @param userName
	 * @return
	 * @return boolean
	 */
	public boolean updateGetMoneyOrder(MtGetpaymoney getPayMoney,String userId,String userName);
	
	/**
	 * @Title updateRefundOrder
	 * @Description: 执行线下退款
	 * @Author Liujf
	 * @CreateDate 2016年4月5日 下午1:44:53
	 * @param orderList
	 * @param userId
	 * @param userName
	 * @return
	 * @return boolean
	 */
	public boolean updateOfflineRefundOrder(MtOrder order,String userId,String userName);
	
	/**
	 * @Title updateGetRefundOrder
	 * @Description: 执行收退款
	 * @Author Liujf
	 * @CreateDate 2016年4月5日 下午1:45:06
	 * @param orderList
	 * @param userId
	 * @param userName
	 * @return
	 * @return boolean
	 */
	public boolean updateGetRefundOrder(MtGetpaymoney getPayMoney,String userId,String userName);
	
	/**
	 * @Title executeCalculateStatus
	 * @Description: 计算状态量
	 * @Author Liujf
	 * @CreateDate 2016年3月31日 下午1:52:57
	 * @param order
	 * @param flag
	 * @return void
	 */
	public void calculateStatus(MtOrder order);
	
	/**
	 * @Title executeCalculateExecute
	 * @Description: 计算执行量
	 * @Author Liujf
	 * @CreateDate 2016年3月31日 下午1:53:17
	 * @param order
	 * @return void
	 */
	public void calculateExecute(MtOrder order);
	
	/**
	 * @Title checkOrderExecuteStatus
	 * @Description: 计算该订单商品是否已经达到结束状态
	 * @Author Liujf
	 * @CreateDate 2016年5月25日 下午4:53:50
	 * @param order
	 * @return void
	 */
	public void checkOrderExecuteStatus(MtOrder order);
	
	/**
	 * @Title checkOrdertitleExecuteStatus
	 * @Description: 计算订单标题的执行状态
	 * @Author Liujf
	 * @CreateDate 2016年5月25日 下午5:20:16
	 * @param ordertitle
	 * @return void
	 */
	public void checkOrdertitleExecuteStatus(String id);
	
}
