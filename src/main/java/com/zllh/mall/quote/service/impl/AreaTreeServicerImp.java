package com.zllh.mall.quote.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.mall.common.dao.BusAreaTreeMapper;
import com.zllh.mall.common.model.BusAreaTree;
import com.zllh.mall.quote.service.AreaTreeService;

@Service
public class AreaTreeServicerImp implements AreaTreeService {
	@Autowired
	private BusAreaTreeMapper areaTreeMapper;
	@Override
	//通过Id查询出一个数据
	public BusAreaTree searchTreeByID(String id) {
		
		return areaTreeMapper.selectByPrimaryKey(id);
	}

	@Override
	//通过sysCode查询出他的所有下级
	public List<BusAreaTree> searchAreaBySyscode(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return areaTreeMapper.searchMore(params);
	}

	@Override
	//查询出所有省份以及省份下的市级对象
		public List<BusAreaTree> searchChina() {
			List<BusAreaTree> areaTree = new ArrayList<BusAreaTree>();
			//查询所有省份节点
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("parentCode", "100");
			//查询中国所有省份数据
			List<BusAreaTree> areaNodes = areaTreeMapper.searchMore(map);
			//迭代省份，查询各个省份下的市
			if(areaNodes!=null){
				Map<String, Object> map1 = new HashMap<String, Object>();
				List<BusAreaTree> childNodes = null;
				for (int i = 0; i < areaNodes.size(); i++) {
					//根据省份查询下一级节点
					map1.put("parentCode", areaNodes.get(i).getAreaCode());
					childNodes = areaTreeMapper.searchMore(map1);
					if(childNodes!=null&&childNodes.size()>0)
						areaNodes.get(i).setNodes(childNodes);
					areaTree.add(areaNodes.get(i));
				}
			}
			return areaTree;
		}
	//查询出所有省份以及一下所有
	public List<BusAreaTree> searchChinaAll() {
		List<BusAreaTree> areaTree = new ArrayList<BusAreaTree>();
		//查询所有省份节点
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentCode", "100");
		//查询中国所有省份数据
		List<BusAreaTree> areaNodes = areaTreeMapper.searchMore(map);
		//迭代省份
		if(areaNodes!=null&&areaNodes.size()>0){
			for (BusAreaTree parent : areaNodes) {
				parent = getChildNodes(parent);
				areaTree.add(parent);
			}
		}
		return areaTree;
	}
	// 通过一个根节点对象根据parentCode，获取所有的下级节点的集合
	public BusAreaTree  getChildNodes(BusAreaTree mt){
		// 获取所有子节点集合
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("parentCode", mt.getAreaCode());
		List<BusAreaTree> childNodes =  new ArrayList<BusAreaTree>();
		childNodes = areaTreeMapper.searchMore(map1);
		if (childNodes != null && childNodes.size() > 0) {
			List<BusAreaTree> list =  new ArrayList<BusAreaTree>();
			for (BusAreaTree child  : childNodes ) {
				if(child.getSysCode().length()<16){
					child = getChildNodes(child);
				}
				list.add(child);

			}
			mt.setNodes(list);
		}
		return mt;
		
	}

	@Override
	public BusAreaTree getTree(String id) {
		
		return areaTreeMapper.selectByChildId(id);
	}

	@Override
	public BusAreaTree getChild(String id) {
		return areaTreeMapper.selectByChildId(id);
		
	}

	

	
	
}
