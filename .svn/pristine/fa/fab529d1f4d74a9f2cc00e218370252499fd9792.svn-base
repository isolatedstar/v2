
package com.zllh.utils.common;

import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamResult;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @ClassName: ObjectUtil
 * @Description: Object处理类
 * @author JW
 * @date 2016年2月16日 下午1:44:52
 */
public class ObjectUtil {

	/**
	 * @Title: toHashMap 
	 * @author JW
	 * @Description: Object转换成Map
	 * @param object
	 * @return HashMap<String,String>
	 * @throws
	 */
	public HashMap<String, String> toHashMap(JSONArray objs){  
	       HashMap<String, String> dataMap = new HashMap<String, String>();  
	       for(Object obj : objs){
	    	   // 将Josn字符串转换成JSONObject  
		       JSONObject jsonObject = JSONObject.fromObject(obj);  
		       Iterator<?> it = jsonObject.keys();  
		       // 遍历JSONObject数据，添加到Map对象  
		       while (it.hasNext()){  
		           String key = String.valueOf(it.next());  
		           String value = (String) jsonObject.get(key);  
		           dataMap.put(key, value);  
		       }
			}
	       return dataMap;
	}
	
	/**
	 * @Title: beanToXML 
	 * @author JW
	 * @Description: JavaBean转XML
	 * @param t          被转换的JavaBean实例
	 * @return String    返回的XML字符串
	 * @throws
	 */
	public <T> String beanToXML(T t) {
		String re = "";
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			JAXBContext context = JAXBContext.newInstance(t.getClass());
			Marshaller marshaller = context.createMarshaller();
			StreamResult result = new StreamResult(bos);
			marshaller.marshal(t, result);
			re = bos.toString("UTF-8");
		} catch (JAXBException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return re;
	} 
	
	/**
	 * @Title: xmlStringToBean 
	 * @author JW
	 * @Description:    XML字符串转成JavaBean
	 * @param t			JavaBean实例
	 * @param xmlStr    XML字符串
	 * @return Object   返回的JavaBean需自己强转
	 * @throws
	 */
	public <T> Object xmlStringToBean(T t, String xmlStr){
		Object obj = null;
		try {
			JAXBContext context = JAXBContext.newInstance(t.getClass());
			Unmarshaller unmarshaller = context.createUnmarshaller();
			obj = unmarshaller.unmarshal(new StringReader(xmlStr));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return obj;
	} 
	
	  /**
     * 将json转化为实体POJO
     * @param jsonStr
     * @param obj
     * @return
     */
    public <T> T JSONToObj(String jsonStr,Class<T> obj) {
        T t = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            t = objectMapper.readValue(jsonStr, obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
    
    /**
     * @Title: JSONToObjects 
     * @author JW
     * @Description: JSON数组转实体数组
     * @param jsonStr
     * @param obj
     * @return List<T>
     * @throws
     */
	public <T> List<T> JSONToObjects(String jsonStr,Class<T> obj){
    	
    	List<T> list = new ArrayList<T>();
    	JSONArray jsons = JSONArray.fromObject(jsonStr);
		for(Object json : jsons){
			T t = JSONToObj(json.toString(), obj);
			list.add(t);
		}
    	return list;
    }
    
    /**
     * 将实体POJO转化为JSON
     * @param obj
     * @return
     * @throws JSONException
     * @throws IOException
     */
    public <T> String  objectToJson(T obj) throws JSONException, IOException {
    	JSONObject  jsonObject = JSONObject.fromObject(obj);
    	return jsonObject.toString();
    }
    
    /**
     * @Title: parseJSON2Map 
     * @Description: JSON转成Map
     * @param @param jsonStr
     * @param @return
     * @return Map<String,Object>
     * @throws
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> parseJSON2Map(String jsonStr) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 最外层解析
        JSONObject json = JSONObject.fromObject(jsonStr);
        for (Object k : json.keySet()) {
            Object v = json.get(k);
            // 如果内层还是数组的话，继续解析
            if (v instanceof JSONArray) {
                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                Iterator<JSONObject> it = ((JSONArray) v).iterator();
                while (it.hasNext()) {
                    JSONObject json2 = it.next();
                    list.add(parseJSON2Map(json2.toString()));
                }
                map.put(k.toString(), list);
            } else {
                map.put(k.toString(), v);
            }
        }
        return map;
    }
    
    /**
     * @Title: beanToMap 
     * @author JW
     * @Description: 实体转Map
     * @param obj
     * @return Map<String,Object>
     * @throws
     */
    public HashMap<String, Object> beanToMap(Object obj) { 
    	HashMap<String, Object> params = new HashMap<String, Object>(0); 
        try { 
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean(); 
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj); 
            for (int i = 0; i < descriptors.length; i++) { 
                String name = descriptors[i].getName(); 
                if (!"class".equals(name)) { 
                    params.put(name, propertyUtilsBean.getNestedProperty(obj, name)); 
                } 
            } 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return params;
    }
    
    /**
	 * @Title: serialize 
	 * @author JW
	 * @Description: 序列化
	 * @param object
	 * @return byte[]
	 * @throws
	 */
	public byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
        }
        return null;
    }

   /**
    * @Title: deserialize 
    * @author JW
    * @Description: 反序列化
    * @param bytes
    * @return Object
    * @throws
    */
    public Object deserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {

        }
        return null;
    }
    
}
