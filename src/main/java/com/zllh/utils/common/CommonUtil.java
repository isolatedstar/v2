package com.zllh.utils.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: CommonUtil
 * @Description: 基本方法工具类接口
 * @author JW
 * @date 2015年11月24日 上午9:05:40
 */
public interface CommonUtil {

	/**
	 * 获取客户端IP地址
	 * 
	 * @return String
	 */
	public String getClientIP();

	/**
	 * 获取客户端IP地址
	 * 
	 * @param request
	 * @return String
	 */
	public String getClientIP(HttpServletRequest request);

	/**
	 * 把Object对象数组封装成Map
	 * 
	 * @param keys
	 * @param values
	 * @return Map
	 */
	public Map<String, Object> toMap(Object[] keys, Object[] values);

	/**
	 * 把Object对象封装成Map
	 * 
	 * @param key
	 * @param value
	 * @return Map<String, Object>
	 */
	public Map<String, Object> toMap(Object key, Object value);

	/**
	 * 把Object数组键值对封装入Map
	 * 
	 * @param params
	 * @param keys
	 * @param values
	 * @return Map<String, Object>
	 */
	public Map<String, Object> toMap(Map<String, Object> map, Object[] keys,
			Object[] values);

	/**
	 * 把Object键值对封装入Map
	 * 
	 * @param params
	 * @param keys
	 * @param values
	 * @return Map<String, Object>
	 */
	public Map<String, Object> toMap(Map<String, Object> map, Object key,
			Object value);

	/**
	 * 生成String类型UUID编号
	 * 
	 * @return String
	 */
	public String getUUID();
	
	/**
	 * @Title: getSerialNumber 
	 * @author JW
	 * @Description: 流水号(20位)
	 * @return String
	 * @throws
	 */
	public String getSerialNumber();

	/**
	 * @Title: createId 
	 * @author JW
	 * @Description: 生成唯一流水号
	 * @return String
	 * @throws
	 */
	public String createVersionId();
	
	/**
	 * 自定义编码强转(odlEncode不设置默认为ISO8859-1)
	 * 
	 * @param string
	 * @param oldEncode
	 * @param newEncode
	 * @return String
	 */
	public String toEncoding(String string, String oldEncode, String newEncode);
	
	/**
	 * @Title: toStringByComma
	 * @author JW
	 * @Description: 数组转以逗号切割的String字符串
	 * @param objs
	 * @return
	 * String
	 * @throws
	 */
	public String toStringByComma(Object[] objs);
}
