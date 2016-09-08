package com.zllh.base.security.interceptor;

import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * @ClassName: CsrfSecurityRequestMatcher
 * @Description: CSRF安全处理
 * @author JW
 * @date 2016年1月4日 上午10:47:07
 */
public class CsrfSecurityRequestMatcher implements RequestMatcher {

	private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");

	/**
	 * 需要排除的url列表
	 */
	private List<String> execludeUrls;

	@Override
	public boolean matches(HttpServletRequest request) {
		if (execludeUrls != null && execludeUrls.size() > 0) {
			String servletPath = request.getServletPath();
			for (String url : execludeUrls) {
				if (servletPath.contains(url)) {
					return !allowedMethods.matcher(request.getMethod()).matches();
				}
			}
		}
		return false;
	}

	public List<String> getExecludeUrls() {
		return execludeUrls;
	}

	public void setExecludeUrls(List<String> execludeUrls) {
		this.execludeUrls = execludeUrls;
	}

}
