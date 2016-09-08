package com.zllh.utils.common;

import java.util.Locale;
import java.util.Map;

/**
 * @ClassName: Language
 * @Description: 本地语言字典
 * @author JW
 * @date 2015年11月24日 上午10:52:28
 */
public interface Language {

	// ======================= 语言 =======================

	/** 英文 */
	public static final String ENGLISH = "en__";

	/** 法文 */
	public static final String FRENCH = "fr__";

	/** 德文 */
	public static final String GERMAN = "de__";

	/** 意大利文 */
	public static final String ITALIAN = "it__";

	/** 日文 */
	public static final String JAPANESE = "ja__";

	/** 韩文 */
	public static final String KOREAN = "ko__";

	/** 中文 */
	public static final String CHINESE = "zh__";

	/** 简体中文 */
	public static final String SIMPLIFIED_CHINESE = "zh_CN_";

	/** 繁体中文 */
	public static final String TRADITIONAL_CHINESE = "zh_TW_";

	// ======================= 国家 =======================

	/** 法国 */
	public static final String FRANCE = "fr_FR_";

	/** 德国 */
	public static final String GERMANY = "de_DE_";

	/** 意大利 */
	public static final String ITALY = "it_IT_";

	/** 日本 */
	public static final String JAPAN = "ja_JP_";

	/** 韩国 */
	public static final String KOREA = "ko_KR_";

	/** 中国 */
	public static final String CHINA = SIMPLIFIED_CHINESE;

	/** 中华人民共和国 */
	public static final String PRC = SIMPLIFIED_CHINESE;

	/** 台湾 */
	public static final String TAIWAN = TRADITIONAL_CHINESE;

	/** 大不列颠及北爱尔兰联合王国 */
	public static final String UK = "en_GB_";

	/** 美国 */
	public static final String US = "en_US_";

	/** 加拿大 */
	public static final String CANADA = "en_CA_";

	/** 加拿大的法语区 */
	public static final String CANADA_FRENCH = "fr_CA_";

	/**
	 * 获取语言
	 * 
	 * @param language
	 * @return Locale
	 */
	public Locale getLocale(String language);

	/**
	 * 获取语言参数值
	 * 
	 * @param key
	 * @return String
	 */
	public String getValue(String key);

	/**
	 * 获取完整的语言参数值集合
	 * 
	 * @param params
	 * @return Map<String, Object>
	 */
	public Map<String, Object> getParamValues(Map<String, Object> params);
}
