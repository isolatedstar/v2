
package com.zllh.common.common.model.model_extend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.zllh.common.common.model.PubUser;
import com.zllh.mall.common.model.model_extend.MtMuserEx;

public class UserExtendBean extends PubUser implements UserDetails{

	private static final long serialVersionUID = 1L;

	private List<RoleExtendBean> roles;
	
	private MtMuserEx muser;
	
	private String sessionId;

	private Collection<? extends GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	
	public List<RoleExtendBean> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleExtendBean> roles) {
		this.roles = roles;
	}
	
	public MtMuserEx getMuser() {
		return muser;
	}

	public void setMuser(MtMuserEx muser) {
		this.muser = muser;
	}
	
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		return this.getAcountName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof UserExtendBean) {  
	        if (this.getUserId().equals(((UserExtendBean) obj).getUserId()))  
	            return true;  
	    }  
	    return false;  
	}
	
	@Override
	public int hashCode() {
		 return this.getUserId().hashCode(); 
	}
}
