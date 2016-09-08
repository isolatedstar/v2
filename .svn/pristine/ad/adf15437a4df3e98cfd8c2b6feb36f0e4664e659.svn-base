package com.zllh.mall.settle.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.zllh.mall.common.model.MtOrder;
import com.zllh.mall.common.model.MtSettle;
import com.zllh.mall.common.model.MtSettleOrder;

/**
 * 
 * @ClassName ISettleService
 * @PackageName com.zllh.mall.settle.service
 * @Description 结款单接口服务类
 * @author Liujf
 * @Date 2016年7月12日 下午3:52:01
 * @Version V1.0
 */
public interface ISettleService {
	
	/**
	 * @Title queryPendingSettleList
	 * @Description: 根据条件查询结款单
	 * @Author Liujf
	 * @CreateDate 2016年7月18日 下午4:45:51
	 * @param map
	 * @return
	 * @return List<MtSettle>
	 */
	public List<MtSettle> queryPendingSettleList(Map<String,Object> map);
	
	/**
	 * @Title queryMyPendingSettleList
	 * @Description: 查询我提交的待定结款单
	 * @Author Liujf
	 * @CreateDate 2016年8月10日 上午10:31:23
	 * @param map
	 * @return
	 * @return List<MtSettle>
	 */
	public List<MtSettle> queryMyPendingSettleList(Map<String, Object> map);
	
	/**
	 * @Title querySttleManage
	 * @Description: 查询执行中结款单
	 * @Author Liujf
	 * @CreateDate 2016年8月11日 下午2:41:44
	 * @param map
	 * @return
	 * @return List<MtSettle>
	 */
	public List<MtSettle> querySttleManage(Map<String, Object> map);
	
	/**
	 * @Title querySttleSignature
	 * @Description: 查询签章结款单
	 * @Author Liujf
	 * @CreateDate 2016年8月12日 下午3:00:24
	 * @param map
	 * @return
	 * @return List<MtSettle>
	 */
	public List<MtSettle> querySttleSignature(Map<String, Object> map);
	
	/**
	 * @Title querySttleRegist
	 * @Description: 查询担保结款单
	 * @Author Liujf
	 * @CreateDate 2016年8月12日 下午3:00:45
	 * @param map
	 * @return
	 * @return List<MtSettle>
	 */
	public List<MtSettle> querySttleRegist(Map<String, Object> map);
	
	/**
	 * @Title querySttleOrderList
	 * @Description: 查询结款单订单
	 * @Author Liujf
	 * @CreateDate 2016年8月29日 下午3:11:50
	 * @param id
	 * @return
	 * @return List<MtSettleOrder>
	 */
	public List<MtSettleOrder> querySttleOrderList(String id);
	
	/**
	 * @Title updateStopSettle
	 * @Description: 更新结款单
	 * @Author Liujf
	 * @CreateDate 2016年8月11日 下午4:49:22
	 * @param mtSettle
	 * @return
	 * @return int
	 */
	public int updateStopSettle(MtSettle mtSettle);
	
	/**
	 * @Title queryOppositeList
	 * @Description: 查询结款单对方用户列表
	 * @Author Liujf
	 * @CreateDate 2016年7月27日 下午3:12:13
	 * @param map
	 * @return
	 * @return List<MtOrder>
	 */
	public List<MtOrder> queryOppositeList(Map<String,Object> map);
	
	/**
	 * @Title createSettle
	 * @Description: 创建结款单
	 * @Author Liujf
	 * @CreateDate 2016年8月8日 下午2:13:35
	 * @param settle
	 * @param orderList
	 * @return
	 * @return boolean
	 */
	public String createSettle(MtSettle settle,List<MtOrder> orderList,String memberId,String userId,String userName);
	
	/**
	 * @Title modifySettle
	 * @Description: 编辑待定结款单
	 * @Author Liujf
	 * @CreateDate 2016年8月10日 下午3:09:19
	 * @param settle
	 * @param memberId
	 * @return
	 * @return int
	 */
	public int modifySettle(MtSettle settle,String memberId);
	
	/**
	 * @Title modifySettle
	 * @Description: 更新同意对方的待定结款单
	 * @Author Liujf
	 * @CreateDate 2016年8月10日 下午3:09:48
	 * @param settle
	 * @return
	 * @return int
	 */
	public int modifySettle(MtSettle settle);
	
	/**
	 * @Title deleteSettle
	 * @Description: 拒绝结款单
	 * @Author Liujf
	 * @CreateDate 2016年8月11日 下午2:41:08
	 * @param settle
	 * @return
	 * @return int
	 */
	public int deleteSettle(MtSettle settle);
	
	/**
	 * @Title getOrderIdByBalanceId
	 * @Description: 根据结款单号查询 订单信息
	 * @Author Liujf
	 * @CreateDate 2016年8月1日 下午3:24:14
	 * @param guaranteetId
	 * @return
	 * @return List<String>
	 */
	List<String> getOrderIdByBalanceId(String guaranteetId);
	
	/**
	 * @Title disposeMallRefundCallback
	 * @Description: 回掉函数
	 * @Author Liujf
	 * @CreateDate 2016年8月1日 下午3:29:57
	 * @param json
	 * @return
	 * @return String
	 */
	public String disposeMallRefundCallback(JSONObject json);
	
	/**
	 * @Title: selectMtSettleById 
	 * @Description: 根据结款单Id查询
	 * @param settleId
	 * @return
	 * @return MtSettle
	 */
	public MtSettle selectMtSettleById(String settleId);


	/**
	 * 待审批结款单app接口
	 * @param map
	 * @return
	 */
	List<MtSettle> searchPendingSettleForApp(Map<String,Object> map);
	
}
