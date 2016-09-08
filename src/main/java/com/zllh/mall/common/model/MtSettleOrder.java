package com.zllh.mall.common.model;

public class MtSettleOrder {
    private String id;

    private String settleId;

    private String orderId;

    private Double money;
    
    private String getpaymoneyId;

    public String getGetpaymoneyId() {
		return getpaymoneyId;
	}

	public void setGetpaymoneyId(String getpaymoneyId) {
		this.getpaymoneyId = getpaymoneyId;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSettleId() {
        return settleId;
    }

    public void setSettleId(String settleId) {
        this.settleId = settleId == null ? null : settleId.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}