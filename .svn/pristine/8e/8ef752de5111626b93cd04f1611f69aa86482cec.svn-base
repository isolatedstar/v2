package com.zllh.utils.common;

import java.util.ArrayList;
import java.util.List;

public class TreeMapper {

public static TreeModel getTreeModel(Object entity , String...params) {
		
		TreeModel t = new  TreeModel();
		String[] treeModelParams = TreeModel.getTreeModelParams();
		
		for(int i = 0 ; i < params.length ; i ++ ) {
			if(params[i] == null ) continue ;
			Object val = ReflectUtils.invokeGetterMethod(entity, params[i]);
			if (val == null ) continue ;
			ReflectUtils.invokeSetterMethod(t,treeModelParams[i], val.toString());
		}
		return  t;
	}
	
	public static List<TreeModel> getTreeModelList(List<? extends Object> entities , String...params) {
		List<TreeModel> returnList = new ArrayList<TreeModel>();
		for(Object entity : entities) {
			returnList.add(TreeMapper.getTreeModel(entity, params));
		}
		return returnList;
	}
}
