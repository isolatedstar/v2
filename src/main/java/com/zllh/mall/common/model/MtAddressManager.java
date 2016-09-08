package com.zllh.mall.common.model;

public class MtAddressManager {
    private Integer id;

    private String contactor;

    private String phone;

    private String postcode;

    private String addresstype;

    private String areaSysCode;
    
    private String name;

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContactor() {
        return contactor;
    }

    public void setContactor(String contactor) {
        this.contactor = contactor == null ? null : contactor.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public String getAddresstype() {
        return addresstype;
    }

    public void setAddresstype(String addresstype) {
        this.addresstype = addresstype == null ? null : addresstype.trim();
    }

	public String getAreaSysCode() {
		return areaSysCode;
	}

	public void setAreaSysCode(String areaSysCode) {
		this.areaSysCode = areaSysCode;
	}


}