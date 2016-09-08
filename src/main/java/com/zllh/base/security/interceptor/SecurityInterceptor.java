package com.zllh.base.security.interceptor;

import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @ClassName: SecurityInterceptor
 * @Description: 拦截器JS脚本安全(防止js脚本注入)
 * @author JW
 * @date 2015年11月24日 上午11:03:01
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter  {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String result = "";
		@SuppressWarnings("unchecked")
		Map<String, Object> parameters = request.getParameterMap();
		for (String key : parameters.keySet()) {
			Object value = parameters.get(key);
			if (value instanceof String[]) {
				// 遍历参数
				String[] values = (String[]) value;
				for (int i = 0, len = values.length; i < len; i++) {
					boolean bool = stripXSS(values[i]);
					if(bool){
						result = "Fuck You Want?";
						break;
					}
				}
			}
		}
		if(result.isEmpty()){
			return super.preHandle(request, response, handler);
		}else{ 
			request.setAttribute("message", result);
			request.getRequestDispatcher("/test1.jsp").forward(request, response);
		}
		
		return false;
	}
	
	/**
	 * @Title: stripXSS 
	 * @author JW
	 * @Description: 如果找到非法字符则返回true,如果没找到则返回false
	 * @param value
	 * @return boolean
	 * @throws
	 */
    public static boolean stripXSS(String value) {
        boolean result = false;
        if (value != null) {
 
            // Avoid null characters
            value = value.replaceAll("", "");
 
            // Avoid anything between script tags
            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>",
                    Pattern.CASE_INSENSITIVE);
            result = scriptPattern.matcher(value).find();// .replaceAll("");
            // //如果找到则为true
            if (result)
                return result;
 
            // Avoid anything in a src='...' type of expression
            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                            | Pattern.DOTALL);
            result = scriptPattern.matcher(value).find();// .replaceAll("");
            if (result)
                return result;
 
            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                            | Pattern.DOTALL);
            result = scriptPattern.matcher(value).find();// .replaceAll("");
            if (result)
                return result;
 
            // Remove any lonesome </script> tag
            scriptPattern = Pattern.compile("</script>",
                    Pattern.CASE_INSENSITIVE);
            result = scriptPattern.matcher(value).find();// .replaceAll("");
            if (result)
                return result;
 
            // Remove any lonesome <script ...> tag
            scriptPattern = Pattern.compile("<script(.*?)>",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                            | Pattern.DOTALL);
            result = scriptPattern.matcher(value).find();// .replaceAll("");
            if (result)
                return result;
 
            // Avoid eval(...) expressions
            scriptPattern = Pattern.compile("eval\\((.*?)\\)",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                            | Pattern.DOTALL);
            result = scriptPattern.matcher(value).find();// .replaceAll("");
            if (result)
                return result;
 
            // Avoid expression(...) expressions
            scriptPattern = Pattern.compile("expression\\((.*?)\\)",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                            | Pattern.DOTALL);
            result = scriptPattern.matcher(value).find();// .replaceAll("");
            if (result)
                return result;
 
            scriptPattern = Pattern.compile("vbscript:",
                    Pattern.CASE_INSENSITIVE);
            result = scriptPattern.matcher(value).find();// .replaceAll("");
            if (result)
                return result;
 
            // Avoid onload= expressions
            scriptPattern = Pattern.compile("onload(.*?)=",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                            | Pattern.DOTALL);
            result = scriptPattern.matcher(value).find();// .replaceAll("");
            if (result)
                return result;
 
            // Avoid alert:... expressions
            scriptPattern = Pattern.compile("alert", Pattern.CASE_INSENSITIVE);
            result = scriptPattern.matcher(value).find();// .replaceAll("");
            if (result)
                return result;
        }
        return result;
    }

}