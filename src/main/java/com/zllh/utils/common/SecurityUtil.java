package com.zllh.utils.common;

import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.zllh.base.security.exception.LoginErrException;
import com.zllh.common.common.model.model_extend.UserExtendBean;
import com.zllh.utils.base.Utils;

/**
 * @ClassName: SecurityUtil
 * @Description: SPRING SECURITY工具类实现类
 * @author JW
 * @date 2015年11月24日 上午9:07:48
 */
public class SecurityUtil {

	public final static String SPRING_SECURITY_CONTEXT = "SPRING_SECURITY_CONTEXT";

	public SecurityContext getSecurityContext(HttpSession session) {
		
		return (SecurityContext) session.getAttribute(SPRING_SECURITY_CONTEXT);
	}

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	/**
	 * 获取SECURITY的认证对象
	 * 
	 * @return UserDetails
	 */
	public UserDetails getUserDetails() {
		
		UserDetails userDetails = null;
		
		try{
		    userDetails = (UserDetails) getAuthentication().getPrincipal();
		}catch(Exception e){
			e.printStackTrace();
			throw new LoginErrException("登录过期!");
		}
		return userDetails;
	}
	
	/**
	 * @Title: getUser 
	 * @author JW
	 * @Description: 获取登录人信息
	 * @return UserExtendBean
	 * @throws
	 */
	public UserExtendBean getUser() {
		return (UserExtendBean) getUserDetails();
	}

	/**
	 * @Title: encodePassword 
	 * @author JW
	 * @Description: 密码加密
	 * @param name
	 * @param pwa
	 * @return String
	 * @throws
	 */
	public String encodePassword(String name, String pwa){
		MessageDigestPasswordEncoder a = new MessageDigestPasswordEncoder("MD5");
		String md = a.encodePassword(pwa, name);
		return md;
	}
	
	public static void main(String[] args) {
		System.err.println(Utils.securityUtil.encodePassword("DDD", "asdf1234"));
	}
}