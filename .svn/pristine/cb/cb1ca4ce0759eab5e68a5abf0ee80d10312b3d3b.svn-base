package com.zllh.mall.material.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.mall.common.dao.MtBaseDivMapper;
import com.zllh.mall.common.model.MtBaseDiv;
import com.zllh.mall.material.service.MDivService;
@Service
public class MDivServiceImp implements MDivService {
	@Autowired
	private MtBaseDivMapper mdivMapper;
	
	@Override
	public boolean addMDiv(MtBaseDiv mbd) {
		boolean flag = false;
		int num = mdivMapper.insertSelective(mbd);
		if(num>0){
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean delMDiv(String mbdId) {
		boolean flag = false;
		int num = mdivMapper.delById(mbdId);
		if(num>0){
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean updateMDiv(MtBaseDiv mbd) {
		boolean flag = false;
		int num = mdivMapper.updateSelective(mbd);
		if(num>0){
			flag = true;
		}
		return flag;
	}
	//显示库的所有根节点
	@Override
	public List<MtBaseDiv> showBaseDiv(String baseId) {
		//获取所有的根节点    根节点默认的parentId为0
		Map<String, Object> map = new  HashMap<String, Object>();
		map.put("baseId",baseId);
		map.put("parentId", "0");
		List<MtBaseDiv> glist = mdivMapper.selectByMap(map);
		List<MtBaseDiv> mmd = new ArrayList<MtBaseDiv>();
		for (MtBaseDiv mbd : glist) {
			mbd = setMbd(mbd, baseId);
//			if(mbd.getNodes()!=null&&mbd.getNodes().size()>0){
//				mmd.add(mbd);
//			}
			mmd.add(mbd);
		}
		return mmd;
	}
	//根据一个根节点查找他的所有的下级
	public MtBaseDiv setMbd(MtBaseDiv mbd ,String baseId){
		//查找到所有的下级
		mbd.setNavigateUrl(mbd.getId());
		Map<String, Object> map = new  HashMap<String, Object>();
		map.put("baseId",baseId);
		map.put("parentId", mbd.getId());
		List<MtBaseDiv> mlist = null;
		 mlist= mdivMapper.selectByMap(map);
		if(mlist!=null&&mlist.size()>0){
			List<MtBaseDiv> mmd = new ArrayList<MtBaseDiv>();
			for (MtBaseDiv mdd : mlist) {
				mdd = setMbd(mdd, baseId);
				mmd.add(mdd);
			}
			mbd.setNodes(mmd);
		}
		return mbd;
	}
	//根据一个节点查询该节点下的所有的最底层节点的集合
	public List<String> getDivIds(String parentId){
		List<String> divIds = new ArrayList<String>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", parentId);
		List<MtBaseDiv> list = mdivMapper.selectByMap(map);
		if(list!=null&&list.size()>0){
			for (MtBaseDiv mm : list) {
				
			}
		}
		
		return null;
	}

	@Override
	public List<MtBaseDiv> showBaseDiv1(String baseId) {
		Map<String, Object> map = new  HashMap<String, Object>();
		map.put("baseId",baseId);
		List<MtBaseDiv> glist = mdivMapper.selectByMap(map);
		return glist;
	}
	

}
