package com.zllh.base.security.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.zllh.common.authority.service.RoleService;
import com.zllh.common.common.model.PubResource;
import com.zllh.common.common.model.model_extend.RoleExtendBean;

public class SecurityMetadataSourceExtendImpl implements FilterInvocationSecurityMetadataSource  {

	private boolean expire = false; 			//过期标识

	@Autowired
	private RoleService roleService; 			//角色服务类
	
	private RequestMatcher requestMatcher;

	private final Logger logger = LoggerFactory.getLogger(SecurityMetadataSourceExtendImpl.class);
	
	private Map<String, Collection<ConfigAttribute>> kv = new HashMap<String, Collection<ConfigAttribute>>(); //角色和资源关系集合

	public boolean supports(Class<?> clazz) {
		return true;
	}

	// 初始化方法时候从数据库中读取资源
	@PostConstruct
	public void init() {
		load();
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		Set<ConfigAttribute> attributes = new HashSet<ConfigAttribute>();
		for (Map.Entry<String, Collection<ConfigAttribute>> entry : kv.entrySet()) {
			attributes.addAll(entry.getValue());
		}
		return attributes;
	}

	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

		HttpServletRequest request = ((FilterInvocation) object).getRequest();
		
		String url = ((FilterInvocation) object).getRequestUrl().trim();
		if(!url.contains("Anonymously")&&!url.contains("_Auth")&&!url.contains("refresh_Auth")){
    		//判断匿名登录就重定向到登录页
     		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
     		if(authentication!=null)
     		if("anonymousUser".equals(authentication.getPrincipal().toString())){
     			throw new AccessDeniedException("please login in first");
     		}
		}
		
		if(url.contains("refresh_Auth")){
		    //重新刷权限
            kv.clear();
            expire = false;
		}
		
		// 检测是否刷新了资源
		if (isExpire()) {
			// 清空原本资源
			kv.clear();
			expire = false;
		}

		// 如果资源Map为空的时候则重新加载一次
		if (null == kv || kv.isEmpty()) load();
		
		logger.info(url);
		// 检测请求与当前资源匹配的正确性 默认使用ant验证策略
		Iterator<String> iterator = kv.keySet().iterator();
 		while (iterator.hasNext()) {
			String uri = iterator.next();
			requestMatcher = new AntPathRequestMatcher(uri);
			if (requestMatcher.matches(request)) {
				return kv.get(uri);
			}
		}
 		
//		拦截所有
// 		Collection<ConfigAttribute> returnCollection = new ArrayList<ConfigAttribute>(); 
// 		returnCollection.add(new SecurityConfig("ROLE_NO_USER"));
 		
		//如果数据库里面没有该URL的信息，表示该URL不需要经过权限验证了
		return null;
	}

	/**
	 * 加载所有资源与权限的关系
	 */
	public void load() {

		//查询所有角色和拥有的资源(URL)权限
		List<RoleExtendBean> roles = roleService.selectAllRoleAndResource();
		for(RoleExtendBean ro : roles){
			
			//角色名:ROLE_XXXX
			ConfigAttribute ca = new SecurityConfig(ro.getRoleSecuritycode());
			//当前角色所拥有的资源(URL)权限
			List<PubResource> res = ro.getResources();
			for(PubResource re : res){
				
				String url = re.getUrl();
				
				/**
				 * 判断资源文件和权限的对应关系，如果已经存在相关的资源URL，则要通过该URL为key提取出权限集合，将权限增加到权限集合中
				 * ROLE_AdMIN  index.jsp
				 * ROLE_MAN    index.jsp
				 * map<index.jsp, [ROLE_AdMIN,ROLE_MAN]>
				 */
				if (kv.containsKey(url)) {
					//获得原来的集合
					Collection<ConfigAttribute> value = kv.get(url);
					value.add(ca);
					kv.put(url, value);
				} else {
					//首次添加的key
					Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
					atts.add(ca);
					kv.put(url, atts);
				}
			}
		}
	}

	public boolean isExpire() {
		return expire;
	}

	public void expireNow() {
		this.expire = true;
	}
}
