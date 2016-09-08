package com.zllh.mall.mmbmmanage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zllh.mall.common.dao.MtMuserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.mall.common.dao.MtMaterialBaseMapper;
import com.zllh.mall.common.dao.MtMemberMapper;
import com.zllh.mall.common.dao.MtMmbTypeMapper;
import com.zllh.mall.common.model.MtMaterialBase;
import com.zllh.mall.common.model.MtMember;
import com.zllh.mall.common.model.MtMmbType;
import com.zllh.mall.material.service.MBaseService;
import com.zllh.mall.mmbmmanage.service.IMMBService;
import com.zllh.mall.user.service.IMUserService;
import com.zllh.utils.common.UUIDCreater;

/**
 * @ClassName: MMBServiceImpl
 * @Description: TODO
 * @author luobo
 * @date 2015-12-28 下午4:50:33
 * @version V1.0
 */
@SuppressWarnings("rawtypes")
@Service
public class MMBServiceImpl implements IMMBService {

	@Autowired
	private MtMemberMapper mmbMapper;
	@Autowired
	private MtMmbTypeMapper bizTypeMapper;
	@Autowired
	private IMUserService userService;
	@Autowired
	private MtMaterialBaseMapper mbaseMapper;

	@Autowired
	private MtMuserMapper mtMuserMapper;
	/**
	 * 创建会员
	 * @param pubMmb 会员基本信息
	 * @param operationName 操作员账号
	 * @param operationPass 操作员密码
	 * @param bizType 启用业务（多个为字符串用逗号分隔）
	 * @return
	 */
	@Override
	public Map<String, Object> createMMB(MtMember pubMmb, String operationName,
			String operationPass, String bizType) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 调用mapper执行新增操作
		int result = mmbMapper.insert(pubMmb);
		// 创建会员成功
		if (result > 0) {
			// 创建操作员信息
			boolean flag = userService.createSupUser(operationName,pubMmb.getMmbEmail(),pubMmb.getMmbPhone(),operationName,pubMmb.getMmbFname(),operationPass,pubMmb.getId());
			
			
			// 如果创建操作员信息成功
			if (flag) {
				if (!StringUtils.isBlank(bizType)) {
					if (bizType.contains(",")) {
						String[] bizTypes = bizType.split(",");
						MtMmbType mmbBizType = null;
						// 迭代业务类型，执行保存
						for (int i = 0; i < bizTypes.length; i++) {
							// 执行新增业务数据
							Integer bizType1 = Integer.parseInt(bizTypes[i]);
							mmbBizType = new MtMmbType();
							mmbBizType.setId(UUIDCreater.getUUID());
							mmbBizType.setMmbid(pubMmb.getId());
							mmbBizType.setMmbtype(bizType1);
							// 执行新增会员业务类型
							bizTypeMapper.insert(mmbBizType);
						}
						map.put("successMsg", "创建会员成功！");
					} else {
						MtMmbType mmbBizType = new MtMmbType();
						// 执行新增业务数据
						Integer bizType1 = Integer.parseInt(bizType);
						mmbBizType.setId(UUIDCreater.getUUID());
						mmbBizType.setMmbid(pubMmb.getId());
						mmbBizType.setMmbtype(bizType1);
						// 执行新增会员业务类型
						bizTypeMapper.insert(mmbBizType);
						map.put("successMsg", "创建会员成功！");
					}
				} else {
					map.put("errorMsg", "启用业务类型不能为空，请勾选！");
					throw new RuntimeException("启用业务类型不能为空，请勾选！");
				}
				
				//创建会员成功  创建会员资料库
				MtMaterialBase add  = new MtMaterialBase();
				add.setId(UUIDCreater.getUUID());
				add.setMaterialType(1);
				add.setMmbId(pubMmb.getId());
				add.setMaterialName(pubMmb.getMmbSname());
				add.setStatus(0);
				mbaseMapper.insertSelective(add);
			} else {
				map.put("errorMsg", "创建操作员失败，请检查！");
				throw new RuntimeException("创建操作员失败，请检查！");
			}
		} else {
			map.put("errorMsg", "创建会员信息失败，请检查！");
			throw new RuntimeException("创建会员信息失败，请检查！");
		}
		return map;
	}

	/**
	 * 修改会员信息
	 */
	@Override
	public Map<String, Object> updateMMB(MtMember pubMmb, String bizType) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 调用mapper执行新增操作
		int result = mmbMapper.updatePubMmb(pubMmb);
		// 创建会员成功
		if (result > 0) {
			// 执行修改启用业务类型，先删除，然后再新增
			List<MtMmbType> typeList = bizTypeMapper.queryAllBizTypesByMid(pubMmb.getId());
			if(typeList!=null&&typeList.size()>0){
				//批量执行删除每个业务类型对象
				bizTypeMapper.deleteAllBizTypesByMid(pubMmb.getId());
			}
			if (!StringUtils.isBlank(bizType)) {
				if (bizType.contains(",")) {
					String[] bizTypes = bizType.split(",");
					MtMmbType mmbBizType = null;
					// 迭代业务类型，执行保存
					for (int i = 0; i < bizTypes.length; i++) {
						// 执行新增业务数据
						Integer bizType1 = Integer.parseInt(bizTypes[i]);
						mmbBizType = new MtMmbType();
						mmbBizType.setId(UUIDCreater.getUUID());
						mmbBizType.setMmbid(pubMmb.getId());
						mmbBizType.setMmbtype(bizType1);
						// 执行新增会员业务类型
						bizTypeMapper.insert(mmbBizType);
					}
					map.put("successMsg", "修改会员成功！");
				} else {
					MtMmbType mmbBizType = new MtMmbType();
					// 执行新增业务数据
					Integer bizType1 = Integer.parseInt(bizType);
					mmbBizType.setId(UUIDCreater.getUUID());
					mmbBizType.setMmbid(pubMmb.getId());
					mmbBizType.setMmbtype(bizType1);
					// 执行新增会员业务类型
					bizTypeMapper.insert(mmbBizType);
					map.put("successMsg", "修改会员成功！");
				}
			} else {
				map.put("errorMsg", "启用业务类型不能为空，请勾选！");
				throw new RuntimeException("启用业务类型不能为空，请勾选！");
			}
		} else {
			map.put("errorMsg", "修改会员信息失败，请检查！");
			throw new RuntimeException("修改会员信息失败，请检查！");
		}
		return map;
	}

	/**
	 * 修改会员信息
	 */
	@Override
	public boolean updateMMB(MtMember pubMmb) {
		boolean flag = false;
		// 调用mapper执行新增操作
		int result = mmbMapper.updatePubMmb(pubMmb);
		if (result > 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 条件查询所有会员信息
	 */
	@Override
	public List<MtMember> queryMMBList(Map map) {
		return mmbMapper.queryAllMmbByCondition(map);
	}

	/**
	 * 根据id查询会员信息
	 */
	@Override
	public MtMember queryMmbById(String id) {
		return mmbMapper.queryMmbById(id);
	}

	/**
	 * 根据条件查询会员列表总数
	 */
	@Override
	public int countMmbList(Map map) {
		return mmbMapper.countMmbsByCondition(map);
	}

	/**
	 * 修改会员状态
	 */
	@Override
	public boolean updateMmbStatus(MtMember pubMmb) {
		boolean flag = false;
		// 调用mapper执行新增操作
		int result = mmbMapper.updatePubMmbStatus(pubMmb);

		Map<String,Object> mtUserMap = new HashMap();
		//如果会员停用/启用，则将其下所有的操作员全部停用/启用
		if(pubMmb.getId() != null && pubMmb.getMmbStatus() != null){
			mtUserMap.put("mmbId",pubMmb.getId());
			mtUserMap.put("state",pubMmb.getMmbStatus() == 0 ? 1 : 0);
			mtMuserMapper.updateMtUserStateByMember(mtUserMap);
		}


		if (result > 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 根据当前会员id以及关联会员id查询信息
	 */
	@Override
	public MtMember queryInfoById(String id, String relaId,String type,String operateType) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("relaId", relaId);
		map.put("relaType", Integer.parseInt(type));
		if(operateType!=null&&!"".equals(operateType))
		map.put("operateType", Integer.parseInt(operateType));
		return mmbMapper.queryInfoById(map);
	}

	/**
	 * 根据报价id查询相关会员信息
	 */
	@Override
	public List<MtMember> queryMmbsInfoByQuoteId(Map<String, Object> map) {
		return mmbMapper.queryMmbsInfoByQuoteId(map);
	}

	/**
	 * 根据报价类型查询信息
	 */
	@Override
	public List<MtMember> queryMmbsByQuoteType(Map<String, Object> map) {
		return mmbMapper.queryMmbsByQuoteType(map);
	}

	/**
	 * 计算总数
	 */
	@Override
	public int countMmbsByQuoteType(Map<String, Object> map) {
		return mmbMapper.countMmbsByQuoteType(map);
	}

	@Override
	public int getNumByCheckType(String checkField,String checkValue,String id) {

		if("account_name".equals(checkField)){
			//验证管理员账号
			return   mmbMapper.getNumByOperationName(checkField,checkValue,id);
		}else{
			//验证 会员信息
			return   mmbMapper.getNumByCheckType(checkField,checkValue,id);
		}
	}

}
