package com.zllh.payment.model;

import java.util.Date;

public class InterfaceMgt {
    private String interfaceId;

    private String bankId;

    private String serverType;

    private String interfaceClass;

    private Date createTime;

    private String status;

    private Byte lockFlag;

    public String getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId == null ? null : interfaceId.trim();
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId == null ? null : bankId.trim();
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    public String getInterfaceClass() {
        return interfaceClass;
    }

    public void setInterfaceClass(String interfaceClass) {
        this.interfaceClass = interfaceClass == null ? null : interfaceClass.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Byte getLockFlag() {
        return lockFlag;
    }

    public void setLockFlag(Byte lockFlag) {
        this.lockFlag = lockFlag;
    }

	@Override
	public String toString() {
		return " [interfaceId=" + interfaceId + ", bankId=" + bankId + ", serverType=" + serverType + ", interfaceClass=" + interfaceClass + ", createTime=" + createTime + ", status=" + status + ", lockFlag=" + lockFlag + "]";
	}
    
}