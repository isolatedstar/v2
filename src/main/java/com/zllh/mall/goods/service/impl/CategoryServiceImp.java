package com.zllh.mall.goods.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.mall.common.dao.MtCategoryMapper;
import com.zllh.mall.common.dao.MtMmbCategoryMapper;
import com.zllh.mall.common.model.MtCategory;
import com.zllh.mall.common.model.MtMmbCategory;
import com.zllh.mall.goods.service.CategoryService;
import com.zllh.utils.common.UUIDCreater;
import com.zllh.mall.common.dao.MtGoodsMapper;

@Service
public class CategoryServiceImp implements CategoryService {
	@Autowired
	private MtCategoryMapper categoryMapper;// 所有品类表
	@Autowired
	private MtMmbCategoryMapper mmcMapper;// 用户品类

	@Autowired
	private MtGoodsMapper MtGoodsMapper;

	// CategoryService PubCtg 品类表

	// 修改品类（修改状态等，目前版本不支持）
	public boolean editCategory(MtCategory category) {
		// 通过品类Id修改品类
		// 调用dao修改方法
		// 根据结果返回ture或false
		return true;
	}

	// 查询所有品类
	@Override
	public List<MtCategory> serachAll() {

		return categoryMapper.searchAll();
	}

	// 查询所有品类（easyui）
	@Override
	public List<MtCategory> searchAllCategory(Integer type) {
		// 根节点
		MtCategory root = null;
		List<MtCategory> MtCategorys = categoryMapper.searchAllCategory(type);
		List<MtCategory> MtCategoryTree = new ArrayList<MtCategory>();

		for (int i = 0; i < MtCategorys.size(); i++) {
			// 找到根节点 ,根节点父Id为0
			if (MtCategorys.get(i).getParentId().equals("0")) {
				root = MtCategorys.get(i);

				/**
				 * // 找到根节点后移除，减少循环次数，提高效率 MtCategorys.remove(MtCategorys.get(i)); // 找到根节点的所有子节点 List<MtCategory> firstMtCategorys = getchildren(root, MtCategorys); root.setChildren(firstMtCategorys); // 为子节点再找到下一级子节点,二级节点下的集合 for (int j = 0; j < firstMtCategorys.size(); j++) { MtCategory sMtCategory = firstMtCategorys.get(j); List<MtCategory> secondMtCategory = getchildren(sMtCategory, MtCategorys); sMtCategory.setChildren(secondMtCategory); // 同样继续往下找子节点 三级节点下的集合 for (int k = 0; k < secondMtCategory.size(); k++) { MtCategory tMtCategory = secondMtCategory.get(k); List<MtCategory> thirdMtCategorys = getchildren(tMtCategory, MtCategorys); tMtCategory.setChildren(thirdMtCategorys); } }
				 */
			}
		}
		MtCategoryTree.add(root);

		return MtCategoryTree;

	}

	/**
	 * 找到子节点的方法
	 * 
	 * @param parent
	 * @param mtCategorys
	 * @return
	 */
	public List<MtCategory> getchildren(MtCategory parent, List<MtCategory> mtCategorys) {
		List<MtCategory> chindList = new ArrayList<MtCategory>();

		for (int i = 0; i < mtCategorys.size(); i++) {

			if (mtCategorys.get(i).getParentId().equals(parent.getId())) {
				chindList.add(mtCategorys.get(i));
			}

		}

		for (int i = 0; i < chindList.size(); i++) {

			// 找到子节点就移除，提高效率
			mtCategorys.remove(chindList.get(i));

		}

		return chindList;
	}

	/**
	 * 通过根节点查找出下面的所有的子级
	 * 
	 * @param root
	 * @param mtCategorys
	 * @return root
	 */
	public MtCategory setRoot(MtCategory root, List<MtCategory> mtCategorys) {

		// 移除节点
		mtCategorys.remove(root);
		// 找到节点的所有子节点
		List<MtCategory> firstMtCategorys = getchildren(root, mtCategorys);
		root.setChildren(firstMtCategorys);
		return root;
	}

	// 用户品类关系
	@Override
	public boolean addMmbCtr(String mmbId, String categoryId) {
		MtMmbCategory mmc = new MtMmbCategory();
		mmc.setId(UUIDCreater.getUUID());
		mmc.setMmbId(mmbId);
		mmc.setCategoryId(categoryId);

		boolean flag = true;
		int num = mmcMapper.insertSelective(mmc);
		// 新增的是底层的品类
		if (num > 0) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean delMmbCtr(String mmbId, String categoryId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mmbId", mmbId);
		map.put("categoryId", categoryId);

		boolean flag = true;
		int num = mmcMapper.deleteByIds(map);
		if (num > 0) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	@Override
	public MtMmbCategory serachById(String mmbId, String categoryId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mmbId", mmbId);
		map.put("categoryId", categoryId);

		return mmcMapper.serachByIds(map);
	}

	// 通过一个根节点对象根据parentId，获取所有的下级节点的集合
	public MtCategory setParent(MtCategory mt) {

		// 获取所有子节点集合
		List<MtCategory> childMts = categoryMapper.getCategoryByParebtId(mt.getId());
		if (childMts != null && childMts.size() > 0) {
			List<MtCategory> list = new ArrayList<MtCategory>();
			for (MtCategory child : childMts) {
				child.setParentIds(mt.getParentIds() + "," + child.getId());
				child = setParent(child);
				list.add(child);
			}
			mt.setNodes(list);

		}
		return mt;
	}

	// 查询所有品类树
	@Override
	public List<MtCategory> getAllMtCategory() {
		// 根节点的品类parentId为0,获取所有的根节点的集合
		List<MtCategory> parents = categoryMapper.getCategoryByParebtId("1");
		List<MtCategory> list = new ArrayList<MtCategory>();
		if (parents != null && parents.size() > 0) {
			for (MtCategory mt : parents) {
				mt.setParentIds(mt.getId());
				mt = setParent(mt);
				// if(mt.getNodes()!=null){
				// list.add(mt);
				// }
				list.add(mt);
			}
		}
		return list;
	}

	// 查询得到用户的品类树 mmbId用户Id
	@Override
	public List<MtCategory> getUserMtCategory(String mmbId) {
		// 根节点的品类parentId为0,获取所有的根节点的集合
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mmbId", mmbId);
		map.put("parentId", "1");
		List<MtCategory> parents = categoryMapper.getUserMtCategory(map);
		List<MtCategory> list = new ArrayList<MtCategory>();
		if (parents != null && parents.size() > 0) {
			for (MtCategory mt : parents) {
				mt.setParentIds(mt.getId());
				mt = setUserParent(mt, mmbId);
				if (mt.getNodes() != null) {
					list.add(mt);
				}
			}
		}
		return list;
	}



	// 通过一个根节点对象根据parentId，获取所有的下级节点的集合
	public MtCategory setUserParent(MtCategory mt, String mmbId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mmbId", mmbId);
		map.put("parentId", mt.getId());
		// 获取所有子节点集合
		List<MtCategory> childMts = categoryMapper.getUserMtCategory(map);
		if (childMts != null && childMts.size() > 0) {
			List<MtCategory> list = new ArrayList<MtCategory>();
			for (MtCategory child : childMts) {
				child.setParentIds(mt.getParentIds() + "," + child.getId());
				child = setUserParent(child, mmbId);
				list.add(child);
			}
			mt.setNodes(list);

		}
		return mt;
	}

	@Override
	public List<MtCategory> getUserMtGoods(String mmbId) {
		// 根节点的品类parentId为0,获取所有的根节点的集合
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mmbId", mmbId);
		map.put("parentId", "1");
		List<MtCategory> parents = categoryMapper.getUserMtCategory(map);
		List<MtCategory> list = new ArrayList<MtCategory>();
		if (parents != null && parents.size() > 0) {
			for (MtCategory mt : parents) {
				mt.setParentIds(mt.getId());
				mt = setUserCategory(mt, mmbId);
				if (mt.getNodes() != null) {
					list.add(mt);
				}
			}
		}
		return list;
	}

	// 通过一个根节点对象根据parentId，获取所有的下级节点的集合
	public MtCategory setUserCategory(MtCategory mt, String mmbId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mmbId", mmbId);
		map.put("parentId", mt.getId());
		// 获取所有子节点集合 --将得到的商品也封装为 品类
		List<MtCategory> childMts = MtGoodsMapper.searchGoodByCategoryId(map);
//		if (childMts != null && childMts.size() > 0) {
//			List<MtCategory> list = new ArrayList<MtCategory>();
//			for (MtCategory child : childMts) {
//				child.setParentIds(mt.getParentIds() + "," + child.getId());
//				child = setUserCategory(child, mmbId);
//				list.add(child);
//			}
			mt.setNodes(childMts);

//		}
		return mt;
	}

	@Override
	public MtCategory getById(String categoryId) {
		
		
		return categoryMapper.getById(categoryId);
	}

	/**
	 * 1、根据协议ID得到协议中商品大类。
	 * 2、根据商品大类ID和供应商ID 得到具体的商品
	 * @param contractId
	 * @return
	 */
	@Override
	public List<MtCategory> getMtCategoryByContractId(String contractId,String sellMmbId) {
		// 根节点的品类parentId为0,获取所有的根节点的集合
		List<MtCategory> parents = categoryMapper.getMtCategoryByContractId(contractId);
		List<MtCategory> list = new ArrayList<MtCategory>();
		if (parents != null && parents.size() > 0) {
			for (MtCategory mt : parents) {
				//得到具体的商品
				mt = setUserCategory(mt, sellMmbId);
				if (mt.getNodes() != null) {
					list.add(mt);
				}
			}
		}
		return list ;
	}
}
