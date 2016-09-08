package com.zllh.mall.common.model;

public class MtMmbPic {
    private String id;

    private String mmbId;

    private String malId;

    private Integer picType;

    private String picRemark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMmbId() {
        return mmbId;
    }

    public void setMmbId(String mmbId) {
        this.mmbId = mmbId == null ? null : mmbId.trim();
    }

    public String getMalId() {
        return malId;
    }

    public void setMalId(String malId) {
        this.malId = malId == null ? null : malId.trim();
    }

    public Integer getPicType() {
        return picType;
    }

    public void setPicType(Integer picType) {
        this.picType = picType;
    }

    public String getPicRemark() {
        return picRemark;
    }

    public void setPicRemark(String picRemark) {
        this.picRemark = picRemark == null ? null : picRemark.trim();
    }
}