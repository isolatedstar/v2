package com.zllh.mall.mmbmmanage.service;

import java.util.List;
import java.util.Map;

import com.zllh.mall.common.model.MtMember;



/**
 * 
 * @ClassName: IMMBService 
 * @Description: 会员管理service
 * @author luobo
 * @date 2016-03-07 下午4:47:41 
 * @version V1.0
 */
@SuppressWarnings("rawtypes")
public interface IMMBService{
	/**
	 * 创建会员
	 * @param pubMmb 会员基本信息
	 * @param operationName 操作员账号
	 * @param operationPass 操作员密码
	 * @param bizType 启用业务（多个为字符串用逗号分隔）
	 * @return
	 */
	public Map<String, Object> createMMB(MtMember pubMmb,String operationName,String operationPass,String bizType);
	
	/**
	 * @Title: createMMB 
	 * @Description: 修改会员
	 * @param @param o
	 * @param @return    
	 * @return String    
	 * @throws 
	 * @author luobo
	 * @date 2016-03-07 下午4:47:41 
	 */
	public boolean updateMMB(MtMember pubMmb);

	/**
	 * 执行修改会员信息
	 * @param pubMmb
	 * @param bizType
	 * @return
	 */
	public Map<String, Object> updateMMB(MtMember pubMmb,String bizType);
	/**
	 * @Title: createMMB 
	 * @Description: 修改会员状态
	 * @param @param o
	 * @param @return    
	 * @return String    
	 * @throws 
	 * @author luobo
	 * @date 2016-03-07 下午4:47:41 
	 */
	public boolean updateMmbStatus(MtMember pubMmb);
	
	/**
	 * @Title: queryMMBList 
	 * @Description: 根据条件查询会员资料信息
	 * @param @param o
	 * @param @return    
	 * @return String    
	 * @throws 
	 * @author luobo
	 * @date 2016-03-07 下午4:47:41 
	 */
	public List<MtMember> queryMMBList(Map map);
	
	/**
	 * @Title: queryMMBList 
	 * @Description: 根据条件匹配查询会员总数
	 * @param @param o
	 * @param @return    
	 * @return String    
	 * @throws 
	 * @author luobo
	 * @date 2016-03-07 下午4:47:41 
	 */
	public int countMmbList(Map map);
	
	/**
	 * 查询单个会员对象
	 * @param id
	 * @return
	 */
	public MtMember queryMmbById(String id);

	/**
	 * 会员查询基本信息
	 * @param id
	 * @param relaId
	 * @return
	 */
	public MtMember queryInfoById(String id,String relaId,String type,String operateType);
	
	/**
	 * 报价使用1
	 * @param map
	 * @return
	 */
	public List<MtMember> queryMmbsInfoByQuoteId(Map<String,Object> map);
	
	/**
	 * 报价使用2
	 * @param map
	 * @return
	 */
	public List<MtMember> queryMmbsByQuoteType(Map<String,Object> map);
	
	/**
	 * 计算总数
	 * @param map
	 * @return
	 */
	public int countMmbsByQuoteType(Map<String,Object> map);

	/**
	 * 添加和编辑会员时 验证简称:mmbSname   英文简称:mmbEngSname 注册全称:mmbFname 是否唯一
	 * @param checkValue  值
	 * @return 管理员账号:operationName
	 */

	public int getNumByCheckType(String checkField,String checkValue,String id);
}
