package com.zllh.utils.base;

import java.util.HashMap;
import java.util.Map;

import com.zllh.base.security.encoder.Encoder;
import com.zllh.base.security.encoder.impl.MD5Encoder;
import com.zllh.utils.common.BigDecimalInterest;
import com.zllh.utils.common.CfcUtil;
import com.zllh.utils.common.CommonUtil;
import com.zllh.utils.common.DateUtil;
import com.zllh.utils.common.FileUtil;
import com.zllh.utils.common.HttpUtils;
import com.zllh.utils.common.ObjectUtil;
import com.zllh.utils.common.SecurityUtil;
import com.zllh.utils.common.StringUtil;
import com.zllh.utils.common.VerificationCode;
import com.zllh.utils.common.XmlDispose;
import com.zllh.utils.common.impl.CommonUtilImpl;
import com.zllh.utils.common.impl.FileUtilImpl;
import com.zllh.utils.common.lock.Lock;
import com.zllh.utils.common.validate.Validator;
import com.zllh.utils.common.validate.ValidatorImpl;

/**
 * @ClassName: Utils
 * @Description: 对外调用工具类
 * @author JW
 * @date 2015年11月24日 上午9:02:19
 */
public abstract class Utils {
	
	/** Xml处理类 */
	public static XmlDispose xmlDispose = new XmlDispose();
	
	/** BigDecimal计算 */
	public static BigDecimalInterest bigDecimalInterest = new BigDecimalInterest();
	
	/** id锁处理类 */
	public static Lock lock = new Lock();
	
	/** CFC工具类 */
	public static CfcUtil cfcUtil = new CfcUtil();
	
	/** String处理类 */
	public static StringUtil stringUtil = new StringUtil();
	
	/** Object处理类 */
	public static ObjectUtil objUtil = new ObjectUtil();
	
	/**日期时间工具类*/
	public static DateUtil dateUtil = new DateUtil();
	
	/** 验证码工具类*/
	public static VerificationCode verificationCode = new VerificationCode();
	
	/** http工具类 */
	public static HttpUtils httpUtil = new HttpUtils();
	
	/** 基本工具类 */
	public static CommonUtil commonUtil = new CommonUtilImpl();

	/** SECURITY工具类 */
	public static SecurityUtil securityUtil = new SecurityUtil();

	/** 加密工具类(默认使用SECURITY的MD5加密) */
	public static Encoder encoder = new MD5Encoder();

	/** 文件工具类 */
	public static FileUtil fileUtil = new FileUtilImpl();
	
	/** 校验工具类 */
	public static Validator validator = new ValidatorImpl();

	/** 多语言参数值 */
	public static Map<String, Object> textParams = new HashMap<String, Object>();
	
}
