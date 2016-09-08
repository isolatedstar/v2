package com.zllh.mall.common.model;

public class MtMuserBiz {
    private String id;

    private String mmbId;

    //所属会员名称
    private String mmbName;

    //名称
    private String name;

    private String email;

    private String telephone;

    //登录用户
    private String account;

    //角色的集合
    private String roleName;

    private int state;
    
    public String getRoleName() {
		return roleName;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMmbId() {
        return mmbId;
    }

    public void setMmbId(String mmbId) {
        this.mmbId = mmbId == null ? null : mmbId.trim();
    }

    public String getMmbName() {
        return mmbName;
    }

    public void setMmbName(String mmbName) {
        this.mmbName = mmbName == null ? null : mmbName.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }
}