package com.zllh.mall.mmbmmanage.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zllh.mall.common.dao.MtMmbAreaBizMapper;
import com.zllh.mall.common.model.MtMmbAreaBiz;
import com.zllh.mall.mmbmmanage.service.IMmbAreaBizService;

/**
 * @ClassName: MMBServiceImpl
 * @Description: 会员地域业务service
 * @author luobo
 * @date 2016-03-07 下午4:47:41 
 * @version V1.0
 */
@SuppressWarnings("rawtypes")
@Service
public class MmbAreaBizServiceImpl implements IMmbAreaBizService{
	
	@Autowired
	private MtMmbAreaBizMapper areaBizMapper;

	@Override
	public boolean createMMBAreaBiz(MtMmbAreaBiz pubMmbAreaBiz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateMMBAreaBiz(MtMmbAreaBiz pubMmbAreaBiz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<MtMmbAreaBiz> queryMMBAreaBizList(Map map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countMMBAreaBizList(Map map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MtMmbAreaBiz queryMMBAreaBizById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 查询所有地域业务
	 */
	@Override
	public List<MtMmbAreaBiz> queryMmbAreaBizByMid(String mid) {
		return areaBizMapper.queryAllMmbAreaBizsByMid(mid);
	}

	/**
	 * JSON数组转对象
	 * @param jsonArrayStr
	 * @return
	 */
	@Override
	public List<MtMmbAreaBiz> jsonArrayStrToObjects(String jsonArrayStr,String mmbId) {
		List<MtMmbAreaBiz> list = new ArrayList<MtMmbAreaBiz>();
		// 首先把字符串转成 JSONArray  对象
		JSONArray jsonArray = JSONArray.fromObject(jsonArrayStr ); 
		MtMmbAreaBiz areaBiz = null;
		String areaId = null;
		String types = null;
		if(jsonArray.size()>0&&mmbId!=null){
		  for(int i=0;i<jsonArray.size();i++){
			areaBiz = new MtMmbAreaBiz();
		    // 遍历 jsonarray 数组，把每一个对象转成 json 对象
		    JSONObject areaBizJson = jsonArray.getJSONObject(i);  
		    //mmbId = areaBizJson.getString("mmbId");
		    areaId = areaBizJson.getString("id");
		    types = areaBizJson.getString("bizType");
		    if(!StringUtils.isBlank(mmbId))
		    	areaBiz.setMmbId(mmbId);
		    if(!StringUtils.isBlank(areaId))
		    	areaBiz.setAreaId(areaId);
		    if(!StringUtils.isBlank(types))
		    	areaBiz.setTypes(types);
		    list.add(areaBiz);
		  }
		}
		return list;
	}

	/**
	 * 批量执行新增
	 */
	@Override
	public boolean batchAddAreaBiz(List<MtMmbAreaBiz> list,String mid) {
		boolean flag = false;
		//判断是否存在数据，如果存在先删除
		int count = areaBizMapper.countMmbAreaBizsByMid(mid);
		if(count>0){
			//先执行删除mid下已经保存的数据，然后再新增获取的新数据
			int result1 = areaBizMapper.deleteMmbBizsByMid(mid);
			if(result1<=0){
				return false;
			}
		}
		//执行新增数据
		int num = 0;
		if(list!=null&&list.size()>0){
			for(MtMmbAreaBiz areaBiz:list){
				int result = areaBizMapper.insert(areaBiz);
				if(result>0){
					num++;
				}
			}
			if(num == list.size()){
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 删除会员地域信息
	 */
	@Override
	public boolean deleteMmbBizsByMid(String mid) {
		boolean flag = false;
		int result = areaBizMapper.deleteMmbBizsByMid(mid);
		if(result>0){
			flag = true;
		}
		return flag;
	}
	
}
