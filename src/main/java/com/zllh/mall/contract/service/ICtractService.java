package com.zllh.mall.contract.service;

import java.util.List;
import java.util.Map;

import com.zllh.mall.common.model.MtCtr;
import com.zllh.mall.common.model.MtCtrCtg;

@SuppressWarnings("rawtypes")
public interface ICtractService {

	public List<MtCtr> queryContracts(Map map);

	public int countContracts(Map map);

	public int creatCTR(MtCtr MtCtr);

	public int creatGoods(MtCtrCtg MtCtrCtg);

	public int lockCTR(MtCtr MtCtr);

	public List<MtCtr> getTradeName(String mmid);

	/**
	 *合作协议进入下一个状态
	 */
	public int updateContractToNextStatus(Map map);

	/**
	 * 根据协议ID 得到合作协议
	 * @param id
	 * @return
	 */
	public MtCtr getContractById(String id, String contractType);

	/**
	 * 根据协议ID 得到协议中的商品大类
	 * @param contractId
	 * @return
	 */
	public List<MtCtrCtg> getGoodsListByContractId(String contractId);

	/**
	 * 根据协议ID 删除删除协议商品大类关系
	 * @param contractId
	 * @return
	 */
	public int deleteCtrCtgByContractId(String contractId);

	public int updateContracts(MtCtr mtCtr);

	/**
	 * 同意合作协议 更新收发货、收发款账号
	 * @param agreeMap
	 * @return
	 */
	public int agreeContract(Map agreeMap);

	public int getNextStatus( int currentStatus, String buyOrSell, String operateType);

}
