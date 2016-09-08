package com.zllh.mall.mmbmmanage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.mall.common.dao.MtMemberRelationshipMapper;
import com.zllh.mall.common.model.MtMemberRelationship;
import com.zllh.mall.mmbmmanage.service.IMmbRelationshipService;
import com.zllh.utils.common.DateUtil;
import com.zllh.utils.common.DictionaryUtil;
import com.zllh.utils.common.UUIDCreater;

@SuppressWarnings("rawtypes")
@Service
public class MmbRelationShipServiceImpl implements IMmbRelationshipService {

	@Autowired
	private MtMemberRelationshipMapper mmbRelationShipMapper;// 会员关系dao

	/**
	 * 新增会员关系
	 */
	@Override
	public boolean createMmbRelationship(MtMemberRelationship mmbRelationship) {
		boolean flag = false;
		int result = mmbRelationShipMapper.insert(mmbRelationship);
		if (result > 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 批量新增会员关系
	 */
	@Override
	public boolean batchAddMmbRelationship(List<MtMemberRelationship> list) {
		boolean flag = false;
		int num = 0;
		if (list != null && list.size() > 0) {
			for (MtMemberRelationship mmbRelationship : list) {
				int result = mmbRelationShipMapper.insert(mmbRelationship);
				if (result > 0) {
					num++;
				}
			}
			if (num == list.size()) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 批量新增会员关系
	 * 
	 * @param mmbIds
	 * @param mmbId
	 * @param type
	 * @return
	 */
	@Override
	public boolean batchAddMmbRelas(String mmbIds, String mmbId, String type) {
		boolean flag = false;
		if (mmbIds == null || mmbId == null || type == null) {
			return false;
		}
		int num = 0;
		String[] ids = mmbIds.split(",");
		List<MtMemberRelationship> listAll = new ArrayList<MtMemberRelationship>();
		MtMemberRelationship mmbRelationship;

		Map exsitMap = new HashMap();

		if (ids.length > 0) {
			// 如果是买
			if (type.equals(DictionaryUtil.MMB_RELATIONSHIP_BUY)) {
				for (int i = 0; i < ids.length; i++) {
					mmbRelationship = new MtMemberRelationship();
					mmbRelationship.setId(UUIDCreater.getUUID());
					mmbRelationship.setCreateTime(DateUtil.getNowDate());
					mmbRelationship.setMmbId(mmbId);
					mmbRelationship.setRelaMmbId(ids[i]);
					// 买卖都存0，借贷存1
					mmbRelationship.setBizType(DictionaryUtil.MMB_BIZ_TYPE_BUY);
					// 这里的关系不需要审核
					mmbRelationship
							.setRelaStatus(DictionaryUtil.MMB_RELATIONSHIP_STATUS_NOMAL);
					// 这里保存的关系默认为合作关系
					mmbRelationship
							.setRelaType(DictionaryUtil.MMB_RELATIONSHIP_TYPE_OPRATION);
					mmbRelationship.setCreatUser(mmbId);

					exsitMap.put("mmbId",mmbId);
					exsitMap.put("relaMmbId",ids[i]);

					//删除之前存在的会员关系
					mmbRelationShipMapper.deleteRelaByRelationShip(exsitMap);

					listAll.add(mmbRelationship);



				}
			}
			// 如果是卖
			else if (type.equals(DictionaryUtil.MMB_RELATIONSHIP_SELL)) {
				for (int i = 0; i < ids.length; i++) {
					mmbRelationship = new MtMemberRelationship();
					mmbRelationship.setId(UUIDCreater.getUUID());
					mmbRelationship.setCreateTime(DateUtil.getNowDate());
					mmbRelationship.setMmbId(ids[i]);
					mmbRelationship.setRelaMmbId(mmbId);
					// 买卖都存1002，借贷存1
					mmbRelationship.setBizType(DictionaryUtil.MMB_BIZ_TYPE_BUY);
					// 这里的关系不需要审核
					mmbRelationship
							.setRelaStatus(DictionaryUtil.MMB_RELATIONSHIP_STATUS_NOMAL);
					// 这里保存的关系默认为合作关系
					mmbRelationship
							.setRelaType(DictionaryUtil.MMB_RELATIONSHIP_TYPE_OPRATION);
					mmbRelationship.setCreatUser(mmbId);

					exsitMap.put("mmbId",ids[i]);
					exsitMap.put("relaMmbId",mmbId);

					//删除之前存在的会员关系
					mmbRelationShipMapper.deleteRelaByRelationShip(exsitMap);

					listAll.add(mmbRelationship);
				}
			}
		}
		if (listAll != null && listAll.size() > 0) {
			for (MtMemberRelationship mmbRelationship1 : listAll) {
				int result = mmbRelationShipMapper.insert(mmbRelationship1);
				if (result > 0) {
					num++;
				}
			}
			if (num == listAll.size()) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 修改会员关系
	 */
	@Override
	public boolean updateMmbRelationship(MtMemberRelationship mmbRelationship) {
		boolean flag = false;
		int result = mmbRelationShipMapper
				.updateMmbRelationship(mmbRelationship);
		if (result > 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 根据条件查询会员数据
	 */
	@Override
	public List<MtMemberRelationship> queryMmbRelationShipForBuy(Map map) {
		return mmbRelationShipMapper.queryMmbRelationshipsByCon(map);
	}

	/**
	 * 根据条件查询总数
	 */
	@Override
	public int countMmbRelationShipForBuy(Map map) {
		return mmbRelationShipMapper.countMmbRelationshipByCondition(map);
	}

	/**
	 * JSON数组转对象
	 * 
	 * @param jsonArrayStr
	 * @return
	 */
	@Override
	public List<MtMemberRelationship> jsonArrayStrToObjects(String jsonArrayStr) {
		List<MtMemberRelationship> list = new ArrayList<MtMemberRelationship>();
		// 首先把字符串转成 JSONArray 对象
		JSONArray jsonArray = JSONArray.fromObject(jsonArrayStr);
		MtMemberRelationship mmbRelationship = null;
		String id = null;
		String mmbId = null;
		String relaMmbId = null;
		String bizType = null;
		if (jsonArray.size() > 0) {
			for (int i = 0; i < jsonArray.size(); i++) {
				mmbRelationship = new MtMemberRelationship();
				// 遍历 jsonarray 数组，把每一个对象转成 json 对象
				JSONObject areaBizJson = jsonArray.getJSONObject(i);
				id = areaBizJson.getString("id");
				// 获取甲方id
				mmbId = areaBizJson.getString("mmbId");
				// 获取乙方id
				relaMmbId = areaBizJson.getString("relaMmbId");
				bizType = areaBizJson.getString("bizType");
				if (!StringUtils.isBlank(id))
					mmbRelationship.setId(id);
				if (!StringUtils.isBlank(mmbId))
					mmbRelationship.setMmbId(mmbId);
				if (!StringUtils.isBlank(relaMmbId))
					mmbRelationship.setRelaMmbId(relaMmbId);
				if (!StringUtils.isBlank(bizType))
					mmbRelationship.setBizType(Integer.parseInt(bizType));
				list.add(mmbRelationship);
			}
		}
		return list;
	}

	/**
	 * 批量删除会员信息
	 */
	@Override
	public boolean deleteMmbRelationships(List<String> ids) {
		boolean flag = false;
		int num = 0;
		if (ids != null && ids.size() > 0) {
			for (String id : ids) {
				int result = mmbRelationShipMapper
						.deleteMmbRelationshipById(id);
				if (result > 0) {
					num++;
				}
			}
			if (num == ids.size()) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 根据id删除单条关系
	 */
	@Override
	public boolean deleteMmbRelationshipById(String id) {
		boolean flag = false;
		int result = mmbRelationShipMapper.deleteMmbRelationshipById(id);
		if (result > 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 升级业务合作关系
	 */
	@Override
	public Map<String, Object> upMmbRelaToBizOperation(String mmbId,
			String relaMmbId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isBlank(mmbId) || StringUtils.isBlank(relaMmbId)) {
			return null;
		}
		// 保存对象为买类型的数据
		MtMemberRelationship mmbRela = new MtMemberRelationship();
		// 合作关系
		mmbRela.setRelaType(DictionaryUtil.MMB_RELATIONSHIP_TYPE_OPRATION);
		// 买方为当前mmbId
		mmbRela.setMmbId(mmbId);
		// 卖方为关系会员
		mmbRela.setRelaMmbId(relaMmbId);
		// 关系类型为买、卖（都存的0）
		mmbRela.setBizType(DictionaryUtil.MMB_BIZ_TYPE_BUY);
		//审批状态为已审核通过 0
		mmbRela.setRelaStatus(DictionaryUtil.MMB_RELATIONSHIP_STATUS_NOMAL);


		// 查询买关系是否存在
		List<MtMemberRelationship> result1 = mmbRelationShipMapper
				.queryMmbRelationshipsByCondition(mmbRela);
		// 如果有数据。那么存入0表示
		if (result1 != null && result1.size() > 0) {
			map.put("buyBiz", "0");
		} else {
			map.put("buyBiz", "1");
		}
		// 保存对象为卖类型的数据
		MtMemberRelationship mmbRela1 = new MtMemberRelationship();
		// 合作关系
		mmbRela1.setRelaType(DictionaryUtil.MMB_RELATIONSHIP_TYPE_OPRATION);
		// 买方为关系relaMmbId
		mmbRela1.setMmbId(relaMmbId);
		// 卖方为mmbId会员
		mmbRela1.setRelaMmbId(mmbId);
		// 关系类型为买、卖（都存的0）
		mmbRela1.setBizType(DictionaryUtil.MMB_BIZ_TYPE_BUY);

		//审批状态为已审核通过 0
		mmbRela1.setRelaStatus(DictionaryUtil.MMB_RELATIONSHIP_STATUS_NOMAL);

		// 执行买类型对象
		List<MtMemberRelationship> result2 = mmbRelationShipMapper
				.queryMmbRelationshipsByCondition(mmbRela1);
		// 如果有数据。那么存入0表示
		if (result2 != null && result2.size() > 0) {
			map.put("sellBiz", "0");
		} else {
			map.put("sellBiz", "1");
		}
		return map;
	}

	/**
	 * 删除会员关系
	 */
	@Override
	public boolean deleteMmbRelationships(Map map) {
		boolean flag = false;
		int result = mmbRelationShipMapper.deleteMmbRelationships(map);
		if (result > 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 查询待审核数据
	 */
	@Override
	public List<MtMemberRelationship> queryMmbRelationShips(Map map) {
		return mmbRelationShipMapper.queryMmbRelationships(map);
	}

	/**
	 * 查询待审核数据总数
	 */
	@Override
	public int countMmbRelationShips(Map map) {
		return mmbRelationShipMapper.countMmbRelationships(map);
	}

	/**
	 * 根据会员关注等级查询相关会员数据
	 */
	@Override
	public List<MtMemberRelationship> queryMmbRelationShipsByGrade(Map map) {
		return mmbRelationShipMapper.queryMmbRelationshipsByGrade1(map);
	}

	/**
	 * 根据会员合作关系查询相关会员数据
	 */
	@Override
	public List<MtMemberRelationship> queryMmbRelationShipsByType(Map map) {
		return mmbRelationShipMapper.queryMmbRelationshipsByType(map);
	}

	/**
	 * 停止会员关系
	 * @param mmbId
	 * @param relaMmbId
	 * @param type
	 * @return
	 */
	@Override
	public String stopBizRelationShip(String mmbId, String relaMmbId,
			String type) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(StringUtils.isBlank(type)||StringUtils.isBlank(mmbId)||StringUtils.isBlank(relaMmbId))
			return null;
		map.put("mmbId", mmbId);
		map.put("relaMmbId", relaMmbId);
		map.put("type", Integer.parseInt(type));
		//根据类型删除关系数据，成功返回0
		int result = mmbRelationShipMapper.deleteRelaMmbsByCondition(map);
		if(result>0){
			return "0";
		}
		else{
			return "1";
		}
	}

	/**
	 * 开启关系
	 */
	@Override
	public String openBizRelationShip(String mmbId, String relaMmbId,
			String type) {
		String result = null;
		//判断type的值，如果是1表示开启买关系，2表示卖关系
		if(StringUtils.isBlank(type)||StringUtils.isBlank(mmbId)||StringUtils.isBlank(relaMmbId))
			return null;

		//删除业务合作关系
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("mmbId", mmbId);
		map.put("relaMmbId", relaMmbId);
		map.put("type", Integer.parseInt(type));
		mmbRelationShipMapper.deleteRelaMmbsByCondition(map);

		//买
		if("1".equals(type)){
			MtMemberRelationship buyMmbRela = new MtMemberRelationship();
			buyMmbRela.setId(UUIDCreater.getUUID());
			buyMmbRela.setMmbId(mmbId);
			buyMmbRela.setRelaMmbId(relaMmbId);
			//关系类型为业务合作
			buyMmbRela.setRelaType(DictionaryUtil.MMB_RELATIONSHIP_TYPE_OPRATION);
			//业务合作都是买卖0，借贷为1
			buyMmbRela.setBizType(DictionaryUtil.MMB_BIZ_TYPE_BUY);
			buyMmbRela.setCreateTime(DateUtil.getNowDate());
			//状态为未审批
			buyMmbRela.setRelaStatus(DictionaryUtil.MMB_RELATIONSHIP_STATUS_FAIL);
			buyMmbRela.setCreatUser(mmbId);
			boolean flag = this.createMmbRelationship(buyMmbRela);
			if(flag){
				result =  "0";
			}
			else{
				result =  "1";
			}
		}
		else if("2".equals(type)){
			MtMemberRelationship buyMmbRela = new MtMemberRelationship();
			buyMmbRela.setId(UUIDCreater.getUUID());
			//甲乙角换一下
			buyMmbRela.setMmbId(relaMmbId);
			buyMmbRela.setRelaMmbId(mmbId);
			//关系类型为业务合作
			buyMmbRela.setRelaType(DictionaryUtil.MMB_RELATIONSHIP_TYPE_OPRATION);
			//业务合作都是买卖0，借贷为1
			buyMmbRela.setBizType(DictionaryUtil.MMB_BIZ_TYPE_BUY);
			buyMmbRela.setCreateTime(DateUtil.getNowDate());
			//状态为未审批
			buyMmbRela.setRelaStatus(DictionaryUtil.MMB_RELATIONSHIP_STATUS_FAIL);
			buyMmbRela.setCreatUser(mmbId);
			boolean flag = this.createMmbRelationship(buyMmbRela);
			if(flag){
				result =  "0";
			}
			else{
				result =  "1";
			}
		}
		return result;
	}

	/**
	 * 根据当前会员查询有关系的会员信息
	 * @param mid
	 * @return
	 */
	@Override
	public List<MtMemberRelationship> queryMmbRelationshipByMid(String mid) {
		return mmbRelationShipMapper.queryMmbRelationshipByMid(mid);
	}

	@Override
	public List<MtMemberRelationship> getMmbRealByContract(Map map) {
		return mmbRelationShipMapper.getMmbRealByContract(map);
	}

	@Override
	public List getOperationRelaByMmbId(String mmbId) {
		return mmbRelationShipMapper.getOperationRelaByMmbId(mmbId);
	}

	//降级为仅关注
	@Override
	public int lowerToConcernOperation(Map map) {

		int result = 0 ;
		//查询是否已有会员关注关系  1、没有关注关系则新增关注关系 2、有关注关系则直接删除合作关系保留原关注关系
		if(map.get("relaType") == null ||map.get("relaType").equals("")){
			map.put("relaType",0);
		}

		MtMemberRelationship relationship = getMembRelaByMap(map);

		//买卖关系
		map.put("bizType",1002);
		//删除合作数据
		int deleteNums = 0;
		deleteNums	= mmbRelationShipMapper.deleteMmbRelationships(map);

		if(relationship == null){

			//新增一条关注数据
			MtMemberRelationship buyMmbRela = new MtMemberRelationship();
			buyMmbRela.setId(UUIDCreater.getUUID());
			if(map.get("mmbId") != null && !("").equals(map.get("mmbId"))){
				buyMmbRela.setMmbId(map.get("mmbId").toString());
				buyMmbRela.setCreatUser(map.get("mmbId").toString());
			}

			if(map.get("relaMmbId") != null && !("").equals(map.get("relaMmbId"))){
				buyMmbRela.setRelaMmbId(map.get("relaMmbId").toString());
			}

			//关系类型为关注
			buyMmbRela.setRelaType(DictionaryUtil.MMB_RELATIONSHIP_TYPE_ATTENTION);
			buyMmbRela.setRelaGrade(1);
			//业务合作都是买卖1002，借贷为1
			//buyMmbRela.setBizType(DictionaryUtil.MMB_BIZ_TYPE_BUY);
			buyMmbRela.setCreateTime(DateUtil.getNowDate());
			//状态为审批通过
			buyMmbRela.setRelaStatus(DictionaryUtil.MMB_RELATIONSHIP_STATUS_NOMAL);

			result	= mmbRelationShipMapper.insert(buyMmbRela);
		}else{
			//保留已有的关注数据
			result = 1;
		}

		return result;
	}

	@Override
	public MtMemberRelationship getMembRelaByMap(Map map) {
		return mmbRelationShipMapper.getMembRelaByMap(map);
	}

	@Override
	public int getConcernRelaNumsByLevel(Map map) {
		return mmbRelationShipMapper.getConcernRelaNumsByLevel(map);
	}

}
