package com.zllh.utils.redis.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.SortParameters.Order;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.query.SortQueryBuilder;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

import com.zllh.utils.base.Utils;
import com.zllh.utils.redis.IBaseRedisDao;
  
/**
 *   
 * @ClassName: AbstractBaseRedisDao 
 * @Description: TODO
 * @author congshuai
 * @date 2016-2-16 下午04:38:30 
 * @version V1.1   
 * @param <K>
 * @param <V>
 */
@Repository
public   class BaseRedisDaoImpl<K, V> implements IBaseRedisDao{
   @Autowired  
    protected RedisTemplate<K, V> redisTemplate;  
  
   /** 
    * 批量新增 单个
    *@param
    *@return 
    */  
   public boolean add(final String key,final String value ) {  
       boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
           public Boolean doInRedis(RedisConnection connection)  
                   throws DataAccessException {  
               RedisSerializer<String> serializer = getRedisSerializer();  
                   byte[] bkey  = serializer.serialize(key);  
                   byte[] bvalue  = serializer.serialize(value);  
                   connection.setNX(bkey, bvalue);  
               return true;  
           }  
       }, false, true);  
       return result;  
   }  
   
   /**
    * @Title: addMap 
    * @author JW
    * @Description: 插入Map集合
    * @param key
    * @param valueMap
    * @return boolean
    * @throws
    */
   public boolean addMap(final String key,final HashMap<String, Object> valueMap ) {
	   boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
           public Boolean doInRedis(RedisConnection connection) throws DataAccessException {  
               RedisSerializer<String> serializer = getRedisSerializer();  
                   byte[] bkey  = serializer.serialize(key); 
                   final HashMap<byte[], byte[]> byMap = new HashMap<byte[], byte[]>();
                   for(String value : valueMap.keySet()){
            				byMap.put(Utils.objUtil.serialize(value), Utils.objUtil.serialize(valueMap.get(value)));
                   }
                   connection.hMSet(bkey, byMap);
               return true;  
           }  
       }, false, true);  
       return result;  
   }
   
   /**
    * @Title: getObjectByKey 
    * @author JW
    * @Description: 通过key查询Map结果集
    * @param keyId
    * @return Object
    * @throws
    */
   public HashMap<String, Object> getObjectByKey(final String keyId) {  
	   Object result = redisTemplate.execute(new RedisCallback<Object>() {  
           public Object doInRedis(RedisConnection connection)  
                   throws DataAccessException {  
               RedisSerializer<String> serializer = getRedisSerializer();  
               byte[] key = serializer.serialize(keyId);  
               Map<byte[], byte[]> name = connection.hGetAll(key);
               return  name;  
           }  
       }); 
	   
	   HashMap<String, Object> reMap = new HashMap<String, Object>();
	   if(result!=null){
		   @SuppressWarnings("unchecked")
		   Map<byte[], byte[]> rebMap = (Map<byte[], byte[]>) result;
		   for(byte[] bkey : rebMap.keySet()){
			   reMap.put(Utils.objUtil.deserialize(bkey).toString(), Utils.objUtil.deserialize(rebMap.get(bkey)));
		   }
	   }
	   
       return reMap;  
   }
   
   /**
    * @Title: deletByKey 
    * @author JW
    * @Description: 根据key删除
    * @param key
    * @return boolean
    * @throws
    */
   public boolean deletByKey(final String key) {  
       boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
           public Boolean doInRedis(RedisConnection connection)  
                   throws DataAccessException {  
               RedisSerializer<String> serializer = getRedisSerializer();  
               byte[] bkey  = serializer.serialize(key);  
               connection.del(bkey); 
               return true;  
           }  
       });  
       return result;  
   } 
   
   /** 
    * 循环批量新增 
    *@param
    *@return 
    */  
   public boolean addmore(final List<Map<String, Object>> list,final String tableName) {  
       boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
           public Boolean doInRedis(RedisConnection connection)  
                   throws DataAccessException {  
               RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
               for (Map<String, Object> map : list) {  
                   byte[] key  = serializer.serialize(tableName+":"+map.get("hcode").toString());  
                   byte[] name = serializer.serialize(map.get("pcode").toString());  
                   connection.setNX(key, name);  
               }  
               return true;  
           }  
       }, false, true);  
       return result;  
   }  
   /**  
    * 删除 
    * @param key 
    */  
   public void delete(String key) {  
       List<String> list = new ArrayList<String>();  
       list.add(key);  
       delete(list);  
   }  
 
   /** 
    * 删除多个 
    * @param keys 
    */  
   public void delete(List<String> keys) {  
       redisTemplate.delete((K) keys);  
   }  
 
   /** 
    * 修改  
    * @param user 
    * @return  
    */  
   public boolean update(final String key,final String value) {  
       if (get(key) == null) {  
           throw new NullPointerException("数据行不存在, key = " + key);  
       }  
       boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
           public Boolean doInRedis(RedisConnection connection)  
                   throws DataAccessException {  
               RedisSerializer<String> serializer = getRedisSerializer();  
               byte[] bkey  = serializer.serialize(key);  
               byte[] bvalue  = serializer.serialize(value); 
               connection.set(bkey, bvalue); 
               return true;  
           }  
       });  
       return result;  
   }  
 
   /**  
    * 通过key获取 
    * @param keyId 
    * @return 
    */  
   public Object get(final String keyId) {  
	   Object result = redisTemplate.execute(new RedisCallback<Object>() {  
           public Object doInRedis(RedisConnection connection)  
                   throws DataAccessException {  
               RedisSerializer<String> serializer = getRedisSerializer();  
               byte[] key = serializer.serialize(keyId);  
               byte[] value = connection.get(key);  
               if (value == null) {  
                   return null;  
               }  
               String name = serializer.deserialize(value);  
               return  name;  
           }  
       });  
       return result;  
   }  
   /*********************************************************************************redis 的list and 队列  start**********************************************************/
   
   /**  
    * 通过listname获取 redis 的list 
    * @param keyId 
    * @return 
    */ 
   public List<V> getListByName(K listName) {
		
	    List<V> newList=new ArrayList<V>();
		//list的长度
		Long listSize=redisTemplate.opsForList().size(listName);
		for(int i=0;i<listSize;i++){
			newList.add(index(listName, i));
		}
		return newList;
	}
   
   
   /** 
    * 压栈 
    *  
    * @param key 
    * @param value 
    * @return 
    */  
   public Long push(K key, V value) {  
       return redisTemplate.opsForList().leftPush(key,value);  
   }  
 
   /** 
    * 出栈 
    *  
    * @param key 
    * @return 
    */  
   public V pop(K key) {  
       return (V) redisTemplate.opsForList().leftPop(key);  
   }  
 
   /** 
    * 入队 
    *  
    * @param key 
    * @param value 
    * @return 
    */  
   public Long in(K key, V value) {  
       return redisTemplate.opsForList().rightPush((K)key,value);  
   }  
 
   /** 
    * 出队 
    *  
    * @param key 
    * @return 
    */  
   public  V out(K key) {  
       return  redisTemplate.opsForList().leftPop(key);  
   }  
 
   /** 
    * 栈/队列长 
    *  
    * @param key 
    * @return 
    */  
   public Long length(K key) {  
       return redisTemplate.opsForList().size(key);  
   }  
 
   /** 
    * 范围检索 
    *  
    * @param key 
    * @param start 
    * @param end 
    * @return 
    */  
   public List<V> range(K key, long start, long end) {  
       return (List<V>) redisTemplate.opsForList().range( key, start, end);  
   }  
 
   /** 
    * 移除 
    *  
    * @param key 
    * @param i 
    * @param value 
    */  
   public void remove(K key, long i, V value) {  
       redisTemplate.opsForList().remove(key, i, value);  
   }  
   
   public void removeAll(K key) {  
	   Long removeSize=redisTemplate.opsForList().size(key);
		for(int i=0;i<removeSize;i++){
			out(key);
		}
   }  
 
   /** 
    * 检索 
    *  
    * @param key 
    * @param index 
    * @return 
    */  
   public V index(K key, long index) {  
       return (V) redisTemplate.opsForList().index(key, index);  
   }  
 
   /** 
    * 置值 
    *  
    * @param key 
    * @param index 
    * @param value 
    */  
   public void set(K key, long index, V value) {  
       redisTemplate.opsForList().set(key, index,value);  
   }  
 
   /** 
    * 裁剪 
    *  
    * @param key 
    * @param start 
    * @param end 
    */  
   public void trim(K key, long start, int end) {  
       redisTemplate.opsForList().trim((K) key, start, end);  
   } 
   
   
   
   /*********************************************************************************redis 的list end**********************************************************/
  
   
   
   /*********************************************************************************redis 的Set and 队列  start**********************************************************/
   
   /** 
    *添加set 值
    *  
    * @param key 
    * @param values
    */  
   public void addSet(K key,V... values) {  
       redisTemplate.opsForSet().add(key,values);  
   } 
   
   
   
   /** 
    *移除 值
    *  
    * @param key 
    * @param value
    */  
   public V popSet(K key) {  
    return   redisTemplate.opsForSet().pop(key);  
   }
   
   /** 
    *查询Set 值
    *  
    * @param key 
    * @param value
    */  
   public Set<V> smembersSet(K key) {  
    return   (Set<V>) redisTemplate.opsForSet().members(key);  
   } 
    
   /** 
    *添加set 值
    *  
    * @param key 
    * @param values
    */  
   public void zaddSet(K key,V value) {  
       redisTemplate.opsForZSet().add(key, value, 0);  
   } 
   
   /** 
    *查询Set 值
    *  
    * @param key 
    * @param value
    */  
   public Set<V> rangeSet(K key,int start,int end) {  
    return   (Set<V>) redisTemplate.opsForZSet().range(key, start, end);  
   } 
    
   
   
   
   
   /*********************************************************************************redis 的Set and 队列  end**********************************************************/
   
   /*********************************************************************************redis 的 hash  and 队列  start**********************************************************/
   
   /** 
    *添加set 值
    *  
    * @param key 
    * @param values
    */  
   public void addHASH(K key, K hashKey, V value) {  
       redisTemplate.opsForHash().put(key,hashKey,value);
   } 
   /** 
    *get set 值
    *  
    * @param key 
    * @param values
    */  
   public Object getHash(K Key,K hashKey) {  
       return   redisTemplate.opsForHash().get(Key, hashKey);
   } 
   
   /** 
    *get set 值
    *  
    * @param key 
    * @param values
    */  
   public Object getHashByKey(K Key) {  
       return   redisTemplate.opsForHash().entries(Key);
   } 
   
   /*********************************************************************************redis 的 hash  and 队列  end**********************************************************/
   
   
   
   /**
    * 对Set或者List 排序
    * @param key
    * @param start
    * @param end
    */
   public List<V> sort(K listKey,int num ) {  
      
	          SortQueryBuilder<K> builder = (SortQueryBuilder<K>) SortQueryBuilder.sort(listKey);  
	          builder.alphabetical(true);//对字符串使用“字典顺序”  
	          builder.order(Order.DESC);//倒序  
	          builder.limit(0, num);  
	          //builder.limit(new Range(0, 2));  
	           return redisTemplate.sort(builder.build());  
	           

   } 
    
    /** 
     * 设置redisTemplate 
     * @param redisTemplate the redisTemplate to set 
     */ 
   
    public void setRedisTemplate(RedisTemplate<K, V> redisTemplate) {  
        this.redisTemplate = redisTemplate;  
    }  
      
    /** 
     * 获取 RedisSerializer 
     */  
    protected RedisSerializer<String> getRedisSerializer() {  
        return redisTemplate.getStringSerializer();  
    }

  
    
}  
