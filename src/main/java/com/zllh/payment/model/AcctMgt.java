package com.zllh.payment.model;

public class AcctMgt {
	
    private String bankAcct;

    private String acctName;

    private Byte acctTypeId;

    private String acctTypeName;

    /** 1:启用，2:停用*/
    private Byte status;

    private String orgGroupId;
    
    private String orgGroupName;

    private String topFiveAcctNum;

    private String bankId;
    
    private String bankName;

    private String masterAcct;
    
    private String masterAcctName;
    
    private Byte isDefault;
    
    private int page;
    
    private int rows;
    
    private Bank bank;
    

    public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public String getBankAcct() {
        return bankAcct;
    }

    public void setBankAcct(String bankAcct) {
        this.bankAcct = bankAcct == null ? null : bankAcct.trim();
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName == null ? null : acctName.trim();
    }

    public Byte getAcctTypeId() {
        return acctTypeId;
    }

    public void setAcctTypeId(Byte acctTypeId) {
        this.acctTypeId = acctTypeId;
    }

    public String getAcctTypeName() {
        return acctTypeName;
    }

    public void setAcctTypeName(String acctTypeName) {
        this.acctTypeName = acctTypeName == null ? null : acctTypeName.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getOrgGroupId() {
        return orgGroupId;
    }

    public void setOrgGroupId(String orgGroupId) {
        this.orgGroupId = orgGroupId == null ? null : orgGroupId.trim();
    }

    public String getTopFiveAcctNum() {
        return topFiveAcctNum;
    }

    public void setTopFiveAcctNum(String topFiveAcctNum) {
        this.topFiveAcctNum = topFiveAcctNum == null ? null : topFiveAcctNum.trim();
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId == null ? null : bankId.trim();
    }
    
    public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getMasterAcct() {
        return masterAcct;
    }

    public void setMasterAcct(String masterAcct) {
        this.masterAcct = masterAcct == null ? null : masterAcct.trim();
    }

	public String getMasterAcctName() {
		return masterAcctName;
	}

	public void setMasterAcctName(String masterAcctName) {
		this.masterAcctName = masterAcctName;
	}

	public Byte getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Byte isDefault) {
		this.isDefault = isDefault;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getOrgGroupName() {
		return orgGroupName;
	}

	public void setOrgGroupName(String orgGroupName) {
		this.orgGroupName = orgGroupName;
	}

	


    
    
}