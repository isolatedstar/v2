package com.zllh.utils.redis;

import java.util.List;


public interface IBaseRedisDao {
	
	public boolean add(final String key,final String value ) ;
	   /**  
	    * 删除 
	    * @param key 
	    */  
	   public void delete(String key) ;
	 
	   /** 
	    * 删除多个 
	    * @param keys 
	    */  
	   public void delete(List<String> keys);
	 
	   /** 
	    * 修改  
	    * @param user 
	    * @return  
	    */  
	   public boolean update(final String key,final String value);
	 
	   /**  
	    * 通过key获取 
	    * @param keyId 
	    * @return 
	    */  
	   public Object get(final String keyId);
	   
	   
}
