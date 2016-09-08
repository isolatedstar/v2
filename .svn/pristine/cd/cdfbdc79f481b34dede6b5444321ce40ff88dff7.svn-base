
package com.zllh.utils.common.lock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zllh.factoring.common.model.FacGuaranteeBill;
import com.zllh.factoring.common.model.model_extend.FacFinancingExtendBean;
import com.zllh.factoring.common.model.model_extend.FacFinancingGuaranteeExtendBean;
import com.zllh.factoring.common.model.model_extend.FacGuaranteeBillExtend;

public class Lock {
	
	public static List<String> lockList = new ArrayList<String>();

	/**
	 * @Title: lockByFins 
	 * @author JW
	 * @Description: 根据融资单及担保单信息判断Id锁及乐观锁
	 * @return HashMap<String,Object>
	 * @throws
	 */
	public synchronized HashMap<String, Object> lockByFins(List<FacFinancingExtendBean> fins, String lockFinjJson, String lockGuajJson){
		
		/**
		 * 返回信息集合
		 * code:    true-表示全部锁成功, false-表示有失败的
		 * success: 返回成功的集合; 
		 * error:   返回错误的集合
		 */
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<FacFinancingExtendBean> success = new ArrayList<FacFinancingExtendBean>();
		List<String> error = new ArrayList<String>();
		
		//前台融资单乐观锁(融资单号, 乐观锁值)
		JSONObject lockFinJsons = JSONObject.fromObject(lockFinjJson);
		//前台担保单乐观锁(担保单号, 乐观锁值)
		JSONObject lockGuaJsons = JSONObject.fromObject(lockGuajJson);
		
		//判断融资单Id锁 乐观锁
		boolean bool = true;
		for(FacFinancingExtendBean fin : fins){
			
			//判断融资单Id锁
			if(lockList.contains(fin.getFinancingId())){
				error.add(fin.getFinancingId());
				bool = false;
				continue;
			}
				
			//判断Id锁成功, 继续判断乐观锁
			if(fin.getLock()!=Integer.parseInt(lockFinJsons.get(fin.getFinancingId()).toString())){
				//当前乐观锁值不等于数据库的值说明已经被别人修改  则当前融资单不能继续处理
				error.add(fin.getFinancingId());
				bool = false;
				continue;
			}
			
			//布尔变量用来判断融资单所用担保单锁是否成功
			boolean boolGua = true;
			
			//担保单Id
			String[] guaIds = new String[fin.getFacFinancingGuarantees().size()];
			//判断当前融资单所用担保单Id锁及乐观锁
			int i = 0;
			for(FacFinancingGuaranteeExtendBean finGuaGuanL : fin.getFacFinancingGuarantees()){
				
				//获取担保单
				FacGuaranteeBill finGuaBill = finGuaGuanL.getFacGuaranteeBill();
				
				guaIds[i] = finGuaBill.getGuaranteeId();
				
				//判断担保单Id锁
				if(lockList.contains(finGuaBill.getGuaranteeId())){
					error.add(finGuaBill.getGuaranteeId());
					bool = false;
					boolGua = false;
					break;
				}
				
				String guaranteeId = "";
				try {
					JSONArray ksons = JSONArray.fromObject(lockGuaJsons.get(finGuaBill.getGuaranteeId()));
					guaranteeId = ksons.get(1).toString();
				} catch (Exception e) {
					guaranteeId = lockGuaJsons.get(finGuaBill.getGuaranteeId()).toString();
				}
				
				//判断担保单乐观锁
				if(finGuaBill.getLock()!=Integer.parseInt(guaranteeId)){
					//当前乐观锁值不等于数据库的值说明已经被别人修改  则当前担保单所属融资单不能继续处理
					error.add(finGuaBill.getGuaranteeId());
					bool = false;
					boolGua = false;
					break;
				}
				i++;
			}
			
			//最后都判断通过  锁成功
			if(boolGua){
				success.add(fin);
				lockList.add(fin.getFinancingId());
				for(String guaId : guaIds){
					lockList.add(guaId);
				}
			}
		}
		
		map.put("code", bool);
		map.put("success", success);
		map.put("error", error);
		return map;
	}
	
	/**
	 * @Title: lockByFin 
	 * @author JW
	 * @Description: 根据融资单及担保单信息判断Id锁及乐观锁
	 * @param fin
	 * @param lockFinjJson
	 * @param lockGuajJson
	 * @return boolean
	 * @throws
	 */
	public synchronized boolean lockByFin(FacFinancingExtendBean fin, String lockFinjJson, String lockGuajJson){
		
		//前台融资单乐观锁(融资单号, 乐观锁值)
		JSONObject lockFinJsons = JSONObject.fromObject(lockFinjJson);
		//前台担保单乐观锁(担保单号, 乐观锁值)
		JSONObject lockGuaJsons = JSONObject.fromObject(lockGuajJson);
		
		//判断融资单Id锁
		if(lockList.contains(fin.getFinancingId())) return false;
		
		//判断Id锁成功, 继续判断乐观锁
		if(!fin.getLock().equals(Integer.parseInt(lockFinJsons.get(fin.getFinancingId()).toString()))) return false;

		//担保单Id
		String[] guaIds = new String[fin.getFacFinancingGuarantees().size()];
		//判断当前融资单所用担保单Id锁及乐观锁
		List<FacFinancingGuaranteeExtendBean> finGuas = fin.getFacFinancingGuarantees();
		for(int i=0;i<finGuas.size();i++){
			
			FacFinancingGuaranteeExtendBean finGuaGuanL = finGuas.get(i);
			
			//获取担保单
			FacGuaranteeBill finGuaBill = finGuaGuanL.getFacGuaranteeBill();
			
			guaIds[i] = finGuaBill.getGuaranteeId();
			
			//判断担保单Id锁
			if(lockList.contains(finGuaBill.getGuaranteeId())) return false;
			
			String guaranteeId = "";
			try {
				JSONArray ksons = JSONArray.fromObject(lockGuaJsons.get(finGuaBill.getGuaranteeId()));
				guaranteeId = ksons.get(1).toString();
			} catch (Exception e) {
				guaranteeId = lockGuaJsons.get(finGuaBill.getGuaranteeId()).toString();
			}
			
			//判断担保单乐观锁
			if(finGuaBill.getLock()!=Integer.parseInt(guaranteeId)) return false;
		}
		
		//最后都判断通过  锁成功
		lockList.add(fin.getFinancingId());
		for(String guaId : guaIds){
			lockList.add(guaId);
		}
			
		return true;
	}
	
	/**
	 * @Title: lockByFint 
	 * @Description: 只判断不加锁
	 * @param @param fin
	 * @param @param lockFinjJson
	 * @param @param lockGuajJson
	 * @param @return
	 * @return boolean
	 * @throws
	 */
    public synchronized boolean lockByFint(FacFinancingExtendBean fin, String lockFinjJson, String lockGuajJson){
        
        //前台融资单乐观锁(融资单号, 乐观锁值)
        JSONObject lockFinJsons = JSONObject.fromObject(lockFinjJson);
        //前台担保单乐观锁(担保单号, 乐观锁值)
        JSONObject lockGuaJsons = JSONObject.fromObject(lockGuajJson);
        
        //判断融资单Id锁
        if(lockList.contains(fin.getFinancingId())) return false;
        
        //判断Id锁成功, 继续判断乐观锁
        if(!fin.getLock().equals(Integer.parseInt(lockFinJsons.get(fin.getFinancingId()).toString()))) return false;

        //担保单Id
        String[] guaIds = new String[fin.getFacFinancingGuarantees().size()];
        //判断当前融资单所用担保单Id锁及乐观锁
        List<FacFinancingGuaranteeExtendBean> finGuas = fin.getFacFinancingGuarantees();
        for(int i=0;i<finGuas.size();i++){
            
            FacFinancingGuaranteeExtendBean finGuaGuanL = finGuas.get(i);
            
            //获取担保单
            FacGuaranteeBill finGuaBill = finGuaGuanL.getFacGuaranteeBill();
            
            guaIds[i] = finGuaBill.getGuaranteeId();
            
            //判断担保单Id锁
            if(lockList.contains(finGuaBill.getGuaranteeId())) return false;
            
            String guaranteeId = "";
            try {
                JSONArray ksons = JSONArray.fromObject(lockGuaJsons.get(finGuaBill.getGuaranteeId()));
                guaranteeId = ksons.get(1).toString();
            } catch (Exception e) {
                guaranteeId = lockGuaJsons.get(finGuaBill.getGuaranteeId()).toString();
            }
            
            //判断担保单乐观锁
            if(finGuaBill.getLock()!=Integer.parseInt(guaranteeId)) return false;
        }
            
        return true;
    }
	
	/**
	 * @Title: lockByFins 
	 * @author JW
	 * @Description: 判断融资单Id锁及乐观锁
	 * @param fins
	 * @param lockFinjJson
	 * @return HashMap<String,Object>
	 * @throws
	 */
	public synchronized HashMap<String, Object> lockByFins(List<FacFinancingExtendBean> fins, String lockFinjJson){
		
		/**
		 * 返回信息集合
		 * code:    true-表示全部锁成功, false-表示有失败的
		 * success: 返回成功的集合; 
		 * error:   返回错误的集合
		 */
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<FacFinancingExtendBean> success = new ArrayList<FacFinancingExtendBean>();
		List<String> error = new ArrayList<String>();
		
		//前台融资单乐观锁(融资单号, 乐观锁值)
		JSONObject lockFinJsons = JSONObject.fromObject(lockFinjJson);
		
		//判断融资单Id锁 乐观锁
		boolean bool = true;
		for(FacFinancingExtendBean fin : fins){
			
			//判断融资单Id锁
			if(lockList.contains(fin.getFinancingId())){
				error.add(fin.getFinancingId());
				bool = false;
				continue;
			}
			
			//判断Id锁成功, 继续判断乐观锁
			if(fin.getLock()!=Integer.parseInt(lockFinJsons.get(fin.getFinancingId()).toString())){
				//当前乐观锁值不等于数据库的值说明已经被别人修改  则当前融资单不能继续处理
				error.add(fin.getFinancingId());
				bool = false;
				continue;
			}
			
			//最后都判断通过  锁成功
			success.add(fin);
			lockList.add(fin.getFinancingId());
		}
		
		map.put("code", bool);
		map.put("success", success);
		map.put("error", error);
		return map;
	}
	
	/**
	 * @Title: lockFinsAndDb 
	 * @author JW
	 * @Description: 判断担保单和融资单的id锁(非事物锁不必全成功)
	 * @param facgs
	 * @return HashMap<String,Object>
	 * @throws
	 */
	public synchronized HashMap<String, Object> lockFinsAndDb(List<FacGuaranteeBillExtend> facgs){
		/**
		 * 返回信息集合
		 * code:    true-表示全部锁成功, false-表示有失败的
		 * success: 返回成功的集合; 
		 * error:   返回错误的集合
		 */
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<FacGuaranteeBillExtend> success = new ArrayList<FacGuaranteeBillExtend >();
		List<String> error = new ArrayList<String>();
		
		//判断担保单融资单Id锁
		boolean bool = true;
		for(FacGuaranteeBillExtend gua : facgs){
			
			//判断担保单Id锁
			if(lockList.contains(gua.getGuaranteeId())){
				error.add(gua.getGuaranteeId());
				bool = false;
				continue;
			}
			
			//判断融资单Id锁
			List<FacFinancingGuaranteeExtendBean> fings = gua.getFings();
			for(FacFinancingGuaranteeExtendBean guaFinc : fings){
				if(lockList.contains(guaFinc.getFacFinancing().getFinancingId())){
					error.add(gua.getGuaranteeId());
					bool = false;
					break;
				}
			}
			
			//最后都判断通过  锁成功
			success.add(gua);
			lockList.add(gua.getGuaranteeId());
		}
		
		map.put("code", bool);
		map.put("success", success);
		map.put("error", error);

		return map;
		
	}
	
	/**
	 * @Title: lockFinsAndDb 
	 * @Description: TODO
	 * @param @param 判断担保单和融资单的乐观锁
	 * @param @return
	 * @return HashMap<String,Object>
	 * @throws
	 */
	public synchronized boolean lockFinsAndDb(FacGuaranteeBillExtend gua, String lockFinjJson, String lockGuajJson){
        
	    //前台融资单乐观锁(融资单号, 乐观锁值)
        JSONObject lockFinJsons = JSONObject.fromObject(lockFinjJson);
        //前台担保单乐观锁(担保单号, 乐观锁值)
        JSONObject lockGuaJsons = JSONObject.fromObject(lockGuajJson);
        
        //判断担保单Id锁
        if(lockList.contains(gua.getGuaranteeId())) return false;
        
        //判断Id锁成功, 继续判断乐观锁
        if(!gua.getLock().equals(Integer.parseInt(lockGuaJsons.get(gua.getGuaranteeId()).toString()))) return false;

        //判断当前担保单所被用融资单Id锁及乐观锁
        List<FacFinancingGuaranteeExtendBean> finGuas = gua.getFings();
        for(int i=0;i<finGuas.size();i++){
            
            FacFinancingGuaranteeExtendBean finGuaGuanL = finGuas.get(i);
            
            //获取融资单
            FacFinancingExtendBean fin = finGuaGuanL.getFacFinancing();
            
            //判断融资单Id锁
            if(lockList.contains(fin.getFinancingId())) return false;
            
            String finId = "";
            try {
                JSONArray ksons = JSONArray.fromObject(lockFinJsons.get(fin.getFinancingId()));
                finId = ksons.get(1).toString();
            } catch (Exception e) {
                finId = lockFinJsons.get(fin.getFinancingId()).toString();
            }
            
            //判断担保单乐观锁
            if(fin.getLock()!=Integer.parseInt(finId)) return false;
        }
        
        return true;
    }
	
	/**
	 * @Title: lockByIds
	 * @author JW
	 * @Description: 加锁(可以单个成功的锁)
	 * @param ids
	 * @return HashMap<String,Object>
	 * @throws
	 */
	public synchronized HashMap<String, Object> lockByIds(String[] ids){
		
		/**
		 * 返回信息集合
		 * code:    true-表示全部锁成功, false-表示有失败的
		 * success: 返回成功的集合; 
		 * error:   返回错误的集合
		 */
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<String> success = new ArrayList<String>();
		List<String> error = new ArrayList<String>();
		boolean bool = true;
		for(String id : ids){
			if(lockList.contains(id)){
				error.add(id);
				bool = false;
			}else{
				success.add(id);
				lockList.add(id);
			}
		}
		map.put("code", bool);
		map.put("success", bool?ids:success);
		map.put("error", error);
		return map;
	}
	
	/**
	 * @Title: lockByIdOne 
	 * @author JW
	 * @Description: 加锁(可以单个成功的锁)返回成功的id
	 * @param ids
	 * @return List<String>
	 * @throws
	 */
	public synchronized List<String> lockByIdOne(String[] ids){
		
		List<String> success = new ArrayList<String>();
		for(String id : ids){
			if(!lockList.contains(id)){
				success.add(id);
				lockList.add(id);
			}
		}
		return success;
	}
	
	/**
	 * @Title: thingLockByIds 
	 * @author JW
	 * @Description: 加锁(事物锁， 成功则全部成功， 失败则全部失败)
	 * @param ids
	 * @return boolean true表示锁成功, false表示锁失败
	 * @throws
	 */
	public synchronized boolean thingLockByIds(String[] ids){
		
		boolean bool = true;
		for(String id : ids){
			if(lockList.contains(id)){
				bool = false;
			}
		}
		if(bool){
			for(String id : ids){
				lockList.add(id);
			}
		}
		return bool;
	}
	
	/**
	 * @Title: lockToId 
	 * @author JW
	 * @Description: 单个Id枷锁  成功返回 ：true 失败返回：false
	 * @param id
	 * @return boolean
	 * @throws
	 */
	public synchronized boolean lockById(String id){
		
		if(id!=null&&!"".equals(id)){
			if(!lockList.contains(id)){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * @Title: deblocking
	 * @author JW
	 * @Description: 解锁
	 * @param ids 
	 * @return void
	 * @throws
	 */
	public void deblocking(String[] ids){
		
		for(String id : ids){
			if(lockList.contains(id)){
				lockList.remove(id);
			}
		}
	}
	
	/**
	 * @Title: deblockingbById 
	 * @author JW
	 * @Description: 解锁
	 * @param id void
	 * @throws
	 */
	public void deblockingbById(String id){
		if(lockList.contains(id)){	
			lockList.remove(id);
		}
	}
	
}
