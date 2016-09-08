package com.zllh.mall.common.model;

import java.util.Date;
import java.util.List;

public class MtOrder {
	private String id;

    private String oredertitleCode;

    private String goodsId;

    private String goodsName;

    private String categoryId;

    private String categoryName;

    private Double goodsNum;

    private Double price;

    private Double money;

    private String quoteId;

    private Double exeSendgoodsNum;

    private Double exeGetgoodsNum;

    private Double exeReturngoodsNum;

    private Double exeGetreturngoodsNum;

    private Double exePaymoneyNum;

    private Double exeGetmoneyNum;

    private Double exeRefundNum;

    private Double exeGetrefundNum;

    private Double sendgoodsNum;

    private Double getgoodsNum;

    private Double returngoodsNum;

    private Double getreturngoodsNum;

    private Double paymoneyNum;

    private Double getmoneyNum;

    private Double refundNum;

    private Double getrefundNum;
    
    private Double lockmoneyNum;

	private String buyersId;

    private String buyersName;

    private String sellersId;

    private String sellersName;

    private Integer status;

    private String remark;

    private Integer workflowType;
    
    private Integer stopStatus;
    
    private Integer executeStatus;
    
    private Integer ordertitleNumber;
    
    private String receiveAddressId;
    
	//发货、收货、退货、收退货时存储货物数量
    //付款、收款、退款、收退款时存储金额
    private Double num;
    
	private String paymoneyCode;
    
    private String getPayRemark;
    
    private Date lockTime;
    
    private Date finishTime;
    
    private String userId;
    
    private String userName;
    
    private Date createTime;
    
    private List<MtMmbWarehouse> addressList;
    
    private List<MtMmbBankAccount> accountList;
    
    public List<MtMmbBankAccount> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<MtMmbBankAccount> accountList) {
		this.accountList = accountList;
	}

	public Date getLockTime() {
		return lockTime;
	}

	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<MtMmbWarehouse> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<MtMmbWarehouse> addressList) {
		this.addressList = addressList;
	}

	public String getGetPayRemark() {
		return getPayRemark;
	}

	public void setGetPayRemark(String getPayRemark) {
		this.getPayRemark = getPayRemark;
	}

	public String getPaymoneyCode() {
		return paymoneyCode;
	}

	public void setPaymoneyCode(String paymoneyCode) {
		this.paymoneyCode = paymoneyCode;
	}

	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
	}
	
	public String getReceiveAddressId() {
		return receiveAddressId;
	}

	public void setReceiveAddressId(String receiveAddressId) {
		this.receiveAddressId = receiveAddressId;
	}
	
	public Integer getOrdertitleNumber() {
		return ordertitleNumber;
	}

	public void setOrdertitleNumber(Integer ordertitleNumber) {
		this.ordertitleNumber = ordertitleNumber;
	}

	public Integer getExecuteStatus() {
		return executeStatus;
	}

	public void setExecuteStatus(Integer executeStatus) {
		this.executeStatus = executeStatus;
	}

	public Integer getStopStatus() {
		return stopStatus;
	}

	public void setStopStatus(Integer stopStatus) {
		this.stopStatus = stopStatus;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOredertitleCode() {
        return oredertitleCode;
    }

    public void setOredertitleCode(String oredertitleCode) {
        this.oredertitleCode = oredertitleCode == null ? null : oredertitleCode.trim();
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public Double getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Double goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId == null ? null : quoteId.trim();
    }

    public Double getExeSendgoodsNum() {
        return exeSendgoodsNum;
    }

    public void setExeSendgoodsNum(Double exeSendgoodsNum) {
        this.exeSendgoodsNum = exeSendgoodsNum;
    }

    public Double getExeGetgoodsNum() {
        return exeGetgoodsNum;
    }

    public void setExeGetgoodsNum(Double exeGetgoodsNum) {
        this.exeGetgoodsNum = exeGetgoodsNum;
    }

    public Double getExeReturngoodsNum() {
        return exeReturngoodsNum;
    }

    public void setExeReturngoodsNum(Double exeReturngoodsNum) {
        this.exeReturngoodsNum = exeReturngoodsNum;
    }

    public Double getExeGetreturngoodsNum() {
        return exeGetreturngoodsNum;
    }

    public void setExeGetreturngoodsNum(Double exeGetreturngoodsNum) {
        this.exeGetreturngoodsNum = exeGetreturngoodsNum;
    }

    public Double getExePaymoneyNum() {
        return exePaymoneyNum;
    }

    public void setExePaymoneyNum(Double exePaymoneyNum) {
        this.exePaymoneyNum = exePaymoneyNum;
    }

    public Double getExeGetmoneyNum() {
        return exeGetmoneyNum;
    }

    public void setExeGetmoneyNum(Double exeGetmoneyNum) {
        this.exeGetmoneyNum = exeGetmoneyNum;
    }

    public Double getExeRefundNum() {
        return exeRefundNum;
    }

    public void setExeRefundNum(Double exeRefundNum) {
        this.exeRefundNum = exeRefundNum;
    }

    public Double getExeGetrefundNum() {
        return exeGetrefundNum;
    }

    public void setExeGetrefundNum(Double exeGetrefundNum) {
        this.exeGetrefundNum = exeGetrefundNum;
    }

    public Double getSendgoodsNum() {
        return sendgoodsNum;
    }

    public void setSendgoodsNum(Double sendgoodsNum) {
        this.sendgoodsNum = sendgoodsNum;
    }

    public Double getGetgoodsNum() {
        return getgoodsNum;
    }

    public void setGetgoodsNum(Double getgoodsNum) {
        this.getgoodsNum = getgoodsNum;
    }

    public Double getReturngoodsNum() {
        return returngoodsNum;
    }

    public void setReturngoodsNum(Double returngoodsNum) {
        this.returngoodsNum = returngoodsNum;
    }

    public Double getGetreturngoodsNum() {
        return getreturngoodsNum;
    }

    public void setGetreturngoodsNum(Double getreturngoodsNum) {
        this.getreturngoodsNum = getreturngoodsNum;
    }

    public Double getPaymoneyNum() {
        return paymoneyNum;
    }

    public void setPaymoneyNum(Double paymoneyNum) {
        this.paymoneyNum = paymoneyNum;
    }

    public Double getGetmoneyNum() {
        return getmoneyNum;
    }

    public void setGetmoneyNum(Double getmoneyNum) {
        this.getmoneyNum = getmoneyNum;
    }

    public Double getRefundNum() {
        return refundNum;
    }

    public void setRefundNum(Double refundNum) {
        this.refundNum = refundNum;
    }

    public Double getGetrefundNum() {
        return getrefundNum;
    }

    public void setGetrefundNum(Double getrefundNum) {
        this.getrefundNum = getrefundNum;
    }
    
    public Double getLockmoneyNum() {
		return lockmoneyNum;
	}

	public void setLockmoneyNum(Double lockmoneyNum) {
		this.lockmoneyNum = lockmoneyNum;
	}

    public String getBuyersId() {
        return buyersId;
    }

    public void setBuyersId(String buyersId) {
        this.buyersId = buyersId == null ? null : buyersId.trim();
    }

    public String getBuyersName() {
        return buyersName;
    }

    public void setBuyersName(String buyersName) {
        this.buyersName = buyersName == null ? null : buyersName.trim();
    }

    public String getSellersId() {
        return sellersId;
    }

    public void setSellersId(String sellersId) {
        this.sellersId = sellersId == null ? null : sellersId.trim();
    }

    public String getSellersName() {
        return sellersName;
    }

    public void setSellersName(String sellersName) {
        this.sellersName = sellersName == null ? null : sellersName.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getWorkflowType() {
        return workflowType;
    }

    public void setWorkflowType(Integer workflowType) {
        this.workflowType = workflowType;
    }
}