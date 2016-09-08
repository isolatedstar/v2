package com.zllh.mall.common.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MtMember implements Serializable{
    private static final long serialVersionUID = -94148662703638697L;
    // 主键Id
	private String id;
	// 会员全称
	private String mmbFname;
	// 会员简称
	private String mmbSname;
	// 英文简称
	private String mmbEngSname;
	// 会员类型
	private Integer mmbType;
	// 会员状态
	private Integer mmbStatus;
	// 会员主页
	private String mmbHomepage;
	// 会员邮箱
	private String mmbEmail;
	// 会员电话
	private String mmbPhone;
	// 会员地址
	private String mmbAddress;
	// 会员所属地域id
	private String mmbAreaId;
	// 新添加字段
	private Date createTime;
	// 扩展属性接受地域名称
	private String mmbAreaName;
	// 扩展属性，接收业务类型字段
	private List<MtMmbType> bizTypes;
	// 扩展属性--操作员用户名
	private String operationName;
	// 扩展属性--操作员用户名
	private String operationPassWord;
	// 扩展字段，接收创建时间字符串格式化
	private String createDate;
	// 扩展字段，显示关注等级
	private String concernGrade;
	// 扩展字段接收关系id
	private String relaId;
	// 扩展字段接收关系mmbId_
	private String mmbId_;
	// 扩展字段接收关系relaMmbId_
	private String relaMmbId_;
	
	public String getMmbId_() {
		return mmbId_;
	}

	public void setMmbId_(String mmbId_) {
		this.mmbId_ = mmbId_;
	}

	public String getRelaMmbId_() {
		return relaMmbId_;
	}

	public void setRelaMmbId_(String relaMmbId_) {
		this.relaMmbId_ = relaMmbId_;
	}

	public String getRelaId() {
		return relaId;
	}

	public void setRelaId(String relaId) {
		this.relaId = relaId;
	}

	public String getConcernGrade() {
		return concernGrade;
	}

	public void setConcernGrade(String concernGrade) {
		this.concernGrade = concernGrade;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getMmbEngSname() {
		return mmbEngSname;
	}

	public void setMmbEngSname(String mmbEngSname) {
		this.mmbEngSname = mmbEngSname;
	}

	public Date getCreatTime() {
		return createTime;
	}

	public void setCreatTime(Date creatTime) {
		this.createTime = creatTime;
	}

	public String getMmbAreaName() {
		return mmbAreaName;
	}

	public void setMmbAreaName(String mmbAreaName) {
		this.mmbAreaName = mmbAreaName;
	}

	public Integer getMmbStatus() {
		return mmbStatus;
	}

	public void setMmbStatus(Integer mmbStatus) {
		this.mmbStatus = mmbStatus;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public String getOperationPassWord() {
		return operationPassWord;
	}

	public void setOperationPassWord(String operationPassWord) {
		this.operationPassWord = operationPassWord;
	}

	public List<MtMmbType> getBizTypes() {
		return bizTypes;
	}

	public void setBizTypes(List<MtMmbType> bizTypes) {
		this.bizTypes = bizTypes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getMmbFname() {
		return mmbFname;
	}

	public void setMmbFname(String mmbFname) {
		this.mmbFname = mmbFname == null ? null : mmbFname.trim();
	}

	public String getMmbSname() {
		return mmbSname;
	}

	public void setMmbSname(String mmbSname) {
		this.mmbSname = mmbSname == null ? null : mmbSname.trim();
	}

	public Integer getMmbType() {
		return mmbType;
	}

	public void setMmbType(Integer mmbType) {
		this.mmbType = mmbType;
	}

	public String getMmbHomepage() {
		return mmbHomepage;
	}

	public void setMmbHomepage(String mmbHomepage) {
		this.mmbHomepage = mmbHomepage == null ? null : mmbHomepage.trim();
	}

	public String getMmbEmail() {
		return mmbEmail;
	}

	public void setMmbEmail(String mmbEmail) {
		this.mmbEmail = mmbEmail == null ? null : mmbEmail.trim();
	}

	public String getMmbPhone() {
		return mmbPhone;
	}

	public void setMmbPhone(String mmbPhone) {
		this.mmbPhone = mmbPhone == null ? null : mmbPhone.trim();
	}

	public String getMmbAddress() {
		return mmbAddress;
	}

	public void setMmbAddress(String mmbAddress) {
		this.mmbAddress = mmbAddress == null ? null : mmbAddress.trim();
	}

	public String getMmbAreaId() {
		return mmbAreaId;
	}

	public void setMmbAreaId(String mmbAreaId) {
		this.mmbAreaId = mmbAreaId == null ? null : mmbAreaId.trim();
	}
}