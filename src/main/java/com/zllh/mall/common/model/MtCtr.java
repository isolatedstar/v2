package com.zllh.mall.common.model;

import java.util.Date;
import java.util.List;

public class MtCtr {

    // 主键
    private String id;
    // 业务类型 1:采购协议 2：销售协议
    private Integer busType; //废除  没用的字段
    // 签订时间
    private Date createTime;
    // 开始时间
    private Date startTime;
    // 结束时间
    private Date endTime;
    // 物流类型
    private Integer flowType;
    // 发货承运方
    private String mmbId;
    // 发货延迟天数
    private Integer delayDate;

    private Integer sendgoodsType;

    private Date payTime;

    private Integer payType;

    private String payerCode;//付款银行账号

    private String payerName;//付款账号名称

    private String getmoneyCode;//收款银行账号

    private String getmoneyName;//收款账号名称

    private String sendgoodsAddress;//发货地址
    private String getgoodsAddress;//收货地址
    // 甲方会员id
    private String firstMmbId; //买方
    // 乙方会员id
    private String secondMmbId;//卖方
    // 审核人
    private String createUser;
    // 标题
    private String contractTitle;
    // 审核状态
    private Integer contractStatus;
    // 扩展字段接收查询结果
    private String flow_type_;// 配送方式
    private String sendgoods_type_;// 运输方式
    private String pay_type_;// 支付方式

    private String beizhu;

    private String text;

    private String mmbName;//协议对方名称

    private List<MtCtr> nodes;

    // 最新操作人
    private String operateUser;
    // 最新操作时间
    private Date operateTime;

    // 最新操作人名称
    private String operateUserName;


    @Override
    public String toString() {
        return "MtCtr [id=" + id + ", busType=" + busType + ", createTime=" + createTime + ", startTime=" + startTime + ", endTime=" + endTime + ", flowType=" + flowType + ", mmbId=" + mmbId + ", delayDate=" + delayDate + ", sendgoodsType=" + sendgoodsType + ", payTime=" + payTime + ", payType=" + payType + ", payerCode=" + payerCode + ", payerName=" + payerName + ", getmoneyCode=" + getmoneyCode + ", getmoneyName=" + getmoneyName + ", firstMmbId=" + firstMmbId + ", secondMmbId=" + secondMmbId + ", createUser=" + createUser + ", contractTitle=" + contractTitle + ", contractStatus=" + contractStatus + ", flow_type_=" + flow_type_ + ", sendgoods_type_=" + sendgoods_type_ + ", pay_type_=" + pay_type_ + ", beizhu=" + beizhu + ", text=" + text + ", nodes=" + nodes + "]";
    }



    public String getContractTitle() {
        return contractTitle;
    }

    public void setContractTitle(String contractTitle) {
        this.contractTitle = contractTitle;
    }

    public String getFlow_type_() {
        return flow_type_;
    }

    public void setFlow_type_(String flow_type_) {
        this.flow_type_ = flow_type_;
    }

    public String getSendgoods_type_() {
        return sendgoods_type_;
    }

    public void setSendgoods_type_(String sendgoods_type_) {
        this.sendgoods_type_ = sendgoods_type_;
    }

    public String getPay_type_() {
        return pay_type_;
    }

    public void setPay_type_(String pay_type_) {
        this.pay_type_ = pay_type_;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Integer getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(Integer contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getFirstMmbId() {
        return firstMmbId;
    }

    public void setFirstMmbId(String firstMmbId) {
        this.firstMmbId = firstMmbId;
    }

    public String getSecondMmbId() {
        return secondMmbId;
    }

    public void setSecondMmbId(String secondMmbId) {
        this.secondMmbId = secondMmbId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getBusType() {
        return busType;
    }

    public void setBusType(Integer busType) {
        this.busType = busType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getFlowType() {
        return flowType;
    }

    public void setFlowType(Integer flowType) {
        this.flowType = flowType;
    }

    public String getMmbId() {
        return mmbId;
    }

    public void setMmbId(String mmbId) {
        this.mmbId = mmbId == null ? null : mmbId.trim();
    }

    public Integer getDelayDate() {
        return delayDate;
    }

    public void setDelayDate(Integer delayDate) {
        this.delayDate = delayDate;
    }

    public Integer getSendgoodsType() {
        return sendgoodsType;
    }

    public void setSendgoodsType(Integer sendgoodsType) {
        this.sendgoodsType = sendgoodsType;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPayerCode() {
        return payerCode;
    }

    public void setPayerCode(String payerCode) {
        this.payerCode = payerCode == null ? null : payerCode.trim();
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName == null ? null : payerName.trim();
    }

    public String getGetmoneyCode() {
        return getmoneyCode;
    }

    public void setGetmoneyCode(String getmoneyCode) {
        this.getmoneyCode = getmoneyCode == null ? null : getmoneyCode.trim();
    }

    public String getGetmoneyName() {
        return getmoneyName;
    }

    public void setGetmoneyName(String getmoneyName) {
        this.getmoneyName = getmoneyName == null ? null : getmoneyName.trim();
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<MtCtr> getNodes() {
        return nodes;
    }

    public void setNodes(List<MtCtr> nodes) {
        this.nodes = nodes;
    }

    public String getMmbName() {
        return mmbName;
    }

    public void setMmbName(String mmbName) {
        this.mmbName = mmbName;
    }

    public String getSendgoodsAddress() {
        return sendgoodsAddress;
    }

    public void setSendgoodsAddress(String sendgoodsAddress) {
        this.sendgoodsAddress = sendgoodsAddress;
    }

    public String getGetgoodsAddress() {
        return getgoodsAddress;
    }

    public void setGetgoodsAddress(String getgoodsAddress) {
        this.getgoodsAddress = getgoodsAddress;
    }

    public String getOperateUser() {
        return operateUser;
    }

    public void setOperateUser(String operateUser) {
        this.operateUser = operateUser;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperateUserName() {
        return operateUserName;
    }

    public void setOperateUserName(String operateUserName) {
        this.operateUserName = operateUserName;
    }
}