package com.zllh.mall.common.model;

import java.util.Date;

public class PictureDire {
    private String id;

    private String picDirParId;

    private String picDirGroupId;

    private String picDirName;

    private String picDirLiveCode;

    private String picDirAllCode;

    private Integer picDirLiveNum;

    private Integer picDirSort;

    private String picDirRepId;

    private Date picDirCtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPicDirParId() {
        return picDirParId;
    }

    public void setPicDirParId(String picDirParId) {
        this.picDirParId = picDirParId == null ? null : picDirParId.trim();
    }

    public String getPicDirGroupId() {
        return picDirGroupId;
    }

    public void setPicDirGroupId(String picDirGroupId) {
        this.picDirGroupId = picDirGroupId == null ? null : picDirGroupId.trim();
    }

    public String getPicDirName() {
        return picDirName;
    }

    public void setPicDirName(String picDirName) {
        this.picDirName = picDirName == null ? null : picDirName.trim();
    }

    public String getPicDirLiveCode() {
        return picDirLiveCode;
    }

    public void setPicDirLiveCode(String picDirLiveCode) {
        this.picDirLiveCode = picDirLiveCode == null ? null : picDirLiveCode.trim();
    }

    public String getPicDirAllCode() {
        return picDirAllCode;
    }

    public void setPicDirAllCode(String picDirAllCode) {
        this.picDirAllCode = picDirAllCode == null ? null : picDirAllCode.trim();
    }

    public Integer getPicDirLiveNum() {
        return picDirLiveNum;
    }

    public void setPicDirLiveNum(Integer picDirLiveNum) {
        this.picDirLiveNum = picDirLiveNum;
    }

    public Integer getPicDirSort() {
        return picDirSort;
    }

    public void setPicDirSort(Integer picDirSort) {
        this.picDirSort = picDirSort;
    }

    public String getPicDirRepId() {
        return picDirRepId;
    }

    public void setPicDirRepId(String picDirRepId) {
        this.picDirRepId = picDirRepId == null ? null : picDirRepId.trim();
    }

    public Date getPicDirCtime() {
        return picDirCtime;
    }

    public void setPicDirCtime(Date picDirCtime) {
        this.picDirCtime = picDirCtime;
    }
}