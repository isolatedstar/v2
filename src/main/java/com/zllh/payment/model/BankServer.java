package com.zllh.payment.model;

import java.util.Date;

public class BankServer {
    private String serverId;

    private String bankId;

    private String serverType;

    private String serverAddress;

    private Date createTime;

    private String status;

    private Byte lockFlag;
    
    private String serverName;

    public String getServerType() {
		return serverType;
	}

	public void setServerType(String serverType) {
		this.serverType = serverType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId == null ? null : serverId.trim();
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId == null ? null : bankId.trim();
    }


    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress == null ? null : serverAddress.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Byte getLockFlag() {
        return lockFlag;
    }

    public void setLockFlag(Byte lockFlag) {
        this.lockFlag = lockFlag;
    }

	@Override
	public String toString() {
		return " [serverId=" + serverId + ", bankId=" + bankId + ", serverType=" + serverType + ", serverAddress=" + serverAddress + ", createTime=" + createTime + ", status=" + status + ", lockFlag=" + lockFlag + "]";
	}


	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
    
}