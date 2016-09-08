package com.zllh.mall.contract.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;

import com.zllh.base.controller.BaseController;
import com.zllh.mall.common.dao.MtCtrCtgMapper;
import com.zllh.mall.common.dao.MtCtrMapper;
import com.zllh.mall.common.model.MtCtr;
import com.zllh.mall.common.model.MtCtrCtg;
import com.zllh.mall.contract.service.ICtractService;
import com.zllh.mall.mmbmmanage.service.IMMBService;

@Service
@SuppressWarnings("rawtypes")
public class ContractService implements ICtractService {

	@Autowired
	private MtCtrMapper contractMapper;

	@Autowired
	private MtCtrCtgMapper MtCtrCtgMapper;

	@Autowired
	private IMMBService mmbService;

	// 日志显示

	@Override
	public List<MtCtr> queryContracts(Map map) {
		return contractMapper.queryContractByCondition(map);
	}

	@Override
	public int countContracts(Map map) {
		return contractMapper.countContracts(map);
	}

	@Override
	public int creatCTR(MtCtr MtCtr) {
		return contractMapper.insertSelective(MtCtr);
	}

	@Override
	public int creatGoods(MtCtrCtg MtCtrCtg) {
		return MtCtrCtgMapper.insertSelective(MtCtrCtg);
	}

	@Override
	public int lockCTR(MtCtr MtCtr) {
		return contractMapper.updateContracts(MtCtr);
	}

	@Override
	public List<MtCtr> getTradeName(String mmid) {
		MtCtr MtCtr = new MtCtr();
		MtCtr.setText(mmid);
		List<MtCtr> list = contractMapper.getTradeName(MtCtr);
		List<MtCtr> backlist = new ArrayList<MtCtr>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getFirstMmbId().equals(mmid) || list.get(i).getSecondMmbId().equals(mmid)) {
				MtCtr.setFirstMmbId(list.get(i).getFirstMmbId());
				MtCtr.setSecondMmbId(list.get(i).getSecondMmbId());
				List<MtCtr> listTwo = contractMapper.getTradeNameByOne(MtCtr);
				MtCtr Mt = new MtCtr();
				if (listTwo.get(0).getFirstMmbId().equals(mmid)) {
					Mt.setText(mmbService.queryMmbById(list.get(0).getSecondMmbId()).getMmbFname());
				} else if (listTwo.get(0).getSecondMmbId().equals(mmid)) {
					Mt.setText(mmbService.queryMmbById(list.get(0).getFirstMmbId()).getMmbFname());
				}
				for (int j = 0; j < listTwo.size(); j++) {
					listTwo.get(j).setText(listTwo.get(j).getContractTitle());
				}
				Mt.setNodes(listTwo);
				if (backlist.size() == 0) {
					backlist.add(Mt);
				} else {
					for (int a = 0; a < backlist.size(); a++) {
						if (!(Mt.getText().equals(backlist.get(a).getText()))) {
							backlist.add(Mt);
						}
					}

				}
			}
		}
		System.out.println("得到的树！！！！！！！！！！！！！！！！！" + backlist);
		return backlist;
	}

	@Override
	public int updateContractToNextStatus(Map map) {
		return contractMapper.updateContractToNextStatus(map);
	}

	@Override
	public MtCtr getContractById(String id, String contractType) {
		return  contractMapper.getContractById(id ,contractType);
	}

	@Override
	public List<MtCtrCtg> getGoodsListByContractId(String contractId) {
		return MtCtrCtgMapper.getGoodsListByContractId(contractId);
	}

	@Override
	public int deleteCtrCtgByContractId(String contractId) {
		return MtCtrCtgMapper.deleteCtrCtgByContractId(contractId);
	}

	@Override
	public int updateContracts(MtCtr mtCtr) {
		return contractMapper.updateContracts(mtCtr);
	}

	@Override
	public int agreeContract(Map agreeMap) {
		return contractMapper.agreeContract(agreeMap);
	}


	/**
	 *
	 *根据 1）协议类型（1：采购协议 2：销售协议） 2)当前协议状态 3)当前会员是 1:买方  2:卖方 查询采购协议就是买方 查询销售协议 就是卖方
	 *4)当前操作类型（submit：提交  agree：同意 refuse:拒绝 terminate：终止 recallTerminate:撤销终止 agreeTerminate:同意终止 refuseTerminate:拒绝终止） 得到协议下一个状态
	 */
	public int getNextStatus( int currentStatus, String buyOrSell, String operateType) {

		int nextStatus = 0;
		//采购协议
		switch (operateType) {
			case "submit":
				nextStatus = buyOrSell.equals("1") ? 1 : 2;
				break;

			case "agree":
				nextStatus = 3;
				break;

			case "refuse":
				nextStatus = 4;
				break;

			case "terminate":
				nextStatus = buyOrSell.equals("1") ? 5 : 6;
				break;

			case "recallTerminate":
				nextStatus = 3;
				break;

			case "agreeTerminate":
				nextStatus = 7;
				break;

			case "refuseTerminate":
				nextStatus = 3;
				break;

			default: nextStatus = 3;
				break;

		}

		return nextStatus;

	}
}
