package com.zllh.mall.common.model;

import java.util.Date;
import java.util.List;

public class MtSettle {
	
    private String id;

    private Integer settleCode;

    private Double settleMoney;

    private Date ctrTime;

    private Double ctrMoney;

    private String gotMoney;

    private Integer payType;

    private String mmbpayId;

    private String mmbpayAccount;

    private String mmbpayName;

    private String mmbgetId;

    private String mmbgetAccount;

    private String mmbgetName;

    private Integer status;

    private Integer statusSign;

    private Integer statusRegist;
    
    private Date myTime;
    
    private Double myMoney;

    private String signInfo;

    private String payInfowithsign;

    private String getInfowithsign;

    private String payOperator;

    private String getOperator;
    
    private List<MtSettleOrder> settleOrderList;
    
    public List<MtSettleOrder> getSettleOrderList() {
		return settleOrderList;
	}

	public void setSettleOrderList(List<MtSettleOrder> settleOrderList) {
		this.settleOrderList = settleOrderList;
	}

	public String getSignInfo() {
        return signInfo;
    }

    public void setSignInfo(String signInfo) {
        this.signInfo = signInfo == null ? null : signInfo.trim();
    }

    public String getPayInfowithsign() {
        return payInfowithsign;
    }

    public void setPayInfowithsign(String payInfowithsign) {
        this.payInfowithsign = payInfowithsign == null ? null : payInfowithsign.trim();
    }

    public String getGetInfowithsign() {
        return getInfowithsign;
    }

    public void setGetInfowithsign(String getInfowithsign) {
        this.getInfowithsign = getInfowithsign == null ? null : getInfowithsign.trim();
    }
    
    public String getPayOperator() {
        return payOperator;
    }
    
    public void setPayOperator(String payOperator) {
        this.payOperator = payOperator;
    }
    
    public String getGetOperator() {
        return getOperator;
    }

    
    public void setGetOperator(String getOperator) {
        this.getOperator = getOperator;
    }

    public Double getMyMoney() {
		return myMoney;
	}

	public void setMyMoney(Double myMoney) {
		this.myMoney = myMoney;
	}

	public Date getMyTime() {
		return myTime;
	}

	public void setMyTime(Date myTime) {
		this.myTime = myTime;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }


    public Integer getSettleCode() {
		return settleCode;
	}

	public void setSettleCode(Integer settleCode) {
		this.settleCode = settleCode;
	}

	public Double getSettleMoney() {
        return settleMoney;
    }

    public void setSettleMoney(Double settleMoney) {
        this.settleMoney = settleMoney;
    }

    public Date getCtrTime() {
        return ctrTime;
    }

    public void setCtrTime(Date ctrTime) {
        this.ctrTime = ctrTime;
    }

    public Double getCtrMoney() {
        return ctrMoney;
    }

    public void setCtrMoney(Double ctrMoney) {
        this.ctrMoney = ctrMoney;
    }

    public String getGotMoney() {
        return gotMoney;
    }

    public void setGotMoney(String gotMoney) {
        this.gotMoney = gotMoney == null ? null : gotMoney.trim();
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getMmbpayId() {
        return mmbpayId;
    }

    public void setMmbpayId(String mmbpayId) {
        this.mmbpayId = mmbpayId == null ? null : mmbpayId.trim();
    }

    public String getMmbpayAccount() {
        return mmbpayAccount;
    }

    public void setMmbpayAccount(String mmbpayAccount) {
        this.mmbpayAccount = mmbpayAccount == null ? null : mmbpayAccount.trim();
    }

    public String getMmbpayName() {
        return mmbpayName;
    }

    public void setMmbpayName(String mmbpayName) {
        this.mmbpayName = mmbpayName == null ? null : mmbpayName.trim();
    }

    public String getMmbgetId() {
        return mmbgetId;
    }

    public void setMmbgetId(String mmbgetId) {
        this.mmbgetId = mmbgetId == null ? null : mmbgetId.trim();
    }

    public String getMmbgetAccount() {
        return mmbgetAccount;
    }

    public void setMmbgetAccount(String mmbgetAccount) {
        this.mmbgetAccount = mmbgetAccount == null ? null : mmbgetAccount.trim();
    }

    public String getMmbgetName() {
        return mmbgetName;
    }

    public void setMmbgetName(String mmbgetName) {
        this.mmbgetName = mmbgetName == null ? null : mmbgetName.trim();
    }

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatusSign() {
		return statusSign;
	}

	public void setStatusSign(Integer statusSign) {
		this.statusSign = statusSign;
	}

	public Integer getStatusRegist() {
		return statusRegist;
	}

	public void setStatusRegist(Integer statusRegist) {
		this.statusRegist = statusRegist;
	}

}