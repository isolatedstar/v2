package com.zllh.factoring.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: FacFinancing
 * @Description: 融资单  
 * @author JW
 * @date 2016年2月25日 下午4:12:56
 */
public class FacFinancing implements Serializable{

	private static final long serialVersionUID = 1L;

	protected String id;

	/** 融资单号 */
	protected String financingId;

	/** 融资总金额 */
	protected BigDecimal waitPayTotal;

	/** 预计服务费 */
    protected BigDecimal predictServiceFee;

    /** 预计还款日期 */
    protected Date predictRefundDate;
    
    /** 预计利息 */
    private BigDecimal predictInterest;

    /** 担保方式 */
    protected Integer assureType;

    /** 选择银行 */
    protected String bank;

    /** 融资日期 */
    protected Date financingDate;

    /** 保理方id */
    protected Integer factoringNameId;
    
    /** 申请人组织_id */
    private String applyOrganizationId;

    /** 申请人ID */
    protected String proposerId;

    /** 申请人 */
    protected String proposerName;

    /** 申请时间 */
    protected Date proposerDate;

    /** 认可操作员id */
    protected String acceptOperatorId;

    /** 认可操作员 */
    protected String acceptOperatorName;

    /** 认可时间  */
    protected Date acceptDate;

    /** 乐观锁 */
    protected Integer lock;

    /** 状态(1-待认可2-待放款3-已认可4-已拒绝5-已还款6-已关闭11-超欠) */
    protected Integer status;

    /** 供应商户名 */
    protected String distributorName;

    /** 供应商虚拟户 */
    protected String distributorTheoreticalBank;

    /** 供应商一般户 */
    protected String distributorGeneralBank;

    /** 龙头企业户名 */
    protected String corporateChampionName;

    /** 龙头企业虚拟户 */
    protected String corporateChampionrTheoreticalBank;

    /** 龙头企业一般户 */
    protected String corporateChampionrGeneralBank;

    /** 到期利率 */
    protected Double expireRate;

    /** 超期利率 */
    protected Double extendRate;

    /** 集团账号 */
    protected String blocAccount;
    
    /** 放贷会员id */
    protected String memberId;

    /** 放贷会员 */
    protected String memberName;

    /** 放贷会员账号 商户名 */
    private String acctName;
    
    /** 放贷会员账号 */
    protected String memberAccount;
    
    /** 最新还款记录ID */
    private String sttLastRefundId;

    /** 最新还款日期 */
    private Date sttLastRefundData;

    /** 认可后银行的流水号 */
    private String acceptBankId;
    
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
    
    public String getId() {
        return id;
    }

    public String getFinancingId() {
        return financingId;
    }

    public void setFinancingId(String financingId) {
        this.financingId = financingId == null ? null : financingId.trim();
    }

    public BigDecimal getWaitPayTotal() {
        return waitPayTotal;
    }

    public void setWaitPayTotal(BigDecimal waitPayTotal) {
        this.waitPayTotal = waitPayTotal;
    }

    public BigDecimal getPredictServiceFee() {
        return predictServiceFee;
    }

    public void setPredictServiceFee(BigDecimal predictServiceFee) {
        this.predictServiceFee = predictServiceFee;
    }

    public Date getPredictRefundDate() {
        return predictRefundDate;
    }

    public void setPredictRefundDate(Date predictRefundDate) {
        this.predictRefundDate = predictRefundDate;
    }

    public BigDecimal getPredictInterest() {
		return predictInterest;
	}

	public void setPredictInterest(BigDecimal predictInterest) {
		this.predictInterest = predictInterest;
	}

	public Integer getAssureType() {
        return assureType;
    }

    public void setAssureType(Integer assureType) {
        this.assureType = assureType;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    public Date getFinancingDate() {
        return financingDate;
    }

    public void setFinancingDate(Date financingDate) {
        this.financingDate = financingDate;
    }

    public Integer getFactoringNameId() {
        return factoringNameId;
    }

    public void setFactoringNameId(Integer factoringNameId) {
        this.factoringNameId = factoringNameId;
    }

    public String getApplyOrganizationId() {
		return applyOrganizationId;
	}

	public void setApplyOrganizationId(String applyOrganizationId) {
		this.applyOrganizationId = applyOrganizationId;
	}

	public String getProposerId() {
        return proposerId;
    }

    public void setProposerId(String proposerId) {
        this.proposerId = proposerId == null ? null : proposerId.trim();
    }

    public String getProposerName() {
        return proposerName;
    }

    public void setProposerName(String proposerName) {
        this.proposerName = proposerName == null ? null : proposerName.trim();
    }

    public Date getProposerDate() {
        return proposerDate;
    }

    public void setProposerDate(Date proposerDate) {
        this.proposerDate = proposerDate;
    }

    public String getAcceptOperatorId() {
        return acceptOperatorId;
    }

    public void setAcceptOperatorId(String acceptOperatorId) {
        this.acceptOperatorId = acceptOperatorId == null ? null : acceptOperatorId.trim();
    }

    public String getAcceptOperatorName() {
        return acceptOperatorName;
    }

    public void setAcceptOperatorName(String acceptOperatorName) {
        this.acceptOperatorName = acceptOperatorName == null ? null : acceptOperatorName.trim();
    }

    public Date getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(Date acceptDate) {
        this.acceptDate = acceptDate;
    }

    public Integer getLock() {
        return lock;
    }

    public void setLock(Integer lock) {
        this.lock = lock;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDistributorName() {
        return distributorName;
    }

    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName == null ? null : distributorName.trim();
    }

    public String getDistributorTheoreticalBank() {
        return distributorTheoreticalBank;
    }

    public void setDistributorTheoreticalBank(String distributorTheoreticalBank) {
        this.distributorTheoreticalBank = distributorTheoreticalBank == null ? null : distributorTheoreticalBank.trim();
    }

    public String getDistributorGeneralBank() {
        return distributorGeneralBank;
    }

    public void setDistributorGeneralBank(String distributorGeneralBank) {
        this.distributorGeneralBank = distributorGeneralBank == null ? null : distributorGeneralBank.trim();
    }

    public String getCorporateChampionName() {
        return corporateChampionName;
    }

    public void setCorporateChampionName(String corporateChampionName) {
        this.corporateChampionName = corporateChampionName == null ? null : corporateChampionName.trim();
    }

    public String getCorporateChampionrTheoreticalBank() {
        return corporateChampionrTheoreticalBank;
    }

    public void setCorporateChampionrTheoreticalBank(String corporateChampionrTheoreticalBank) {
        this.corporateChampionrTheoreticalBank = corporateChampionrTheoreticalBank == null ? null : corporateChampionrTheoreticalBank.trim();
    }

    public String getCorporateChampionrGeneralBank() {
        return corporateChampionrGeneralBank;
    }

    public void setCorporateChampionrGeneralBank(String corporateChampionrGeneralBank) {
        this.corporateChampionrGeneralBank = corporateChampionrGeneralBank == null ? null : corporateChampionrGeneralBank.trim();
    }

    public Double getExpireRate() {
        return expireRate;
    }

    public void setExpireRate(Double expireRate) {
        this.expireRate = expireRate;
    }

    public Double getExtendRate() {
        return extendRate;
    }

    public void setExtendRate(Double extendRate) {
        this.extendRate = extendRate;
    }

    public String getBlocAccount() {
        return blocAccount;
    }

    public void setBlocAccount(String blocAccount) {
        this.blocAccount = blocAccount == null ? null : blocAccount.trim();
    }

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	public String getMemberAccount() {
		return memberAccount;
	}

	public void setMemberAccount(String memberAccount) {
		this.memberAccount = memberAccount;
	}

	public String getSttLastRefundId() {
		return sttLastRefundId;
	}

	public void setSttLastRefundId(String sttLastRefundId) {
		this.sttLastRefundId = sttLastRefundId;
	}

	public Date getSttLastRefundData() {
		return sttLastRefundData;
	}

	public void setSttLastRefundData(Date sttLastRefundData) {
		this.sttLastRefundData = sttLastRefundData;
	}

	public String getAcceptBankId() {
		return acceptBankId;
	}

	public void setAcceptBankId(String acceptBankId) {
		this.acceptBankId = acceptBankId;
	}
    
}