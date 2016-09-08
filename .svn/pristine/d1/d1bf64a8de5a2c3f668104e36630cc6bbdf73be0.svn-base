package com.zllh.base.security.serviceImpl;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class AccessDecisionManagerImpl implements AccessDecisionManager {

	private final Logger logger = LoggerFactory.getLogger(AccessDecisionManagerImpl.class);
	
	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
		if (null == configAttributes)
			return;
		for (ConfigAttribute attribute : configAttributes) {
			String needRole = ((SecurityConfig) attribute).getAttribute();
			// authority为用户所被赋予的权限, needRole 为访问相应的资源应该具有的权限。
			for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
				if (needRole.equals(grantedAuthority.getAuthority()))
					return;
			}
		}
		logger.info("权限不足!");
		throw new AccessDeniedException("权限不足!");
		
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
