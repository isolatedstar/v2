package com.zllh.base.security.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.zllh.common.authority.service.RoleService;
import com.zllh.common.authority.service.UserService;
import com.zllh.common.common.model.PubRole;
import com.zllh.common.common.model.model_extend.UserExtendBean;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// 使用User服务类查询数据用户是否存在,如不存在或密码错误则抛出对应的异常
		UserExtendBean user = this.userService.findByUserName(username);
		if (null == user) throw new UsernameNotFoundException("用户/密码错误,请重新输入!");
	    if(Integer.valueOf(0).equals(user.getMuser().getState())) throw new UsernameNotFoundException("该用户已停用!");

		List<PubRole> roles = this.roleService.findByUserId(user.getUserId());
		
		// 把权限赋值给当前对象
		Collection<GrantedAuthority> gaRoles = new ArrayList<GrantedAuthority>();
		for (PubRole role : roles) {
			gaRoles.add(new SimpleGrantedAuthority(role.getRoleSecuritycode()));
		}
		user.setAuthorities(gaRoles);
		
		return user;
	};
}
