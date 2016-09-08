package com.zllh.mall.common.model;

import java.util.Date;

public class DocInfo {
    private String id;

    private String docName;

    private String docGroupId;

    private String docSuffix;

    private String docCreateId;

    private String docSubType;

    private String docPath;

    private Date docCreateTime;

    private Date docUpdateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName == null ? null : docName.trim();
    }

    public String getDocGroupId() {
        return docGroupId;
    }

    public void setDocGroupId(String docGroupId) {
        this.docGroupId = docGroupId == null ? null : docGroupId.trim();
    }

    public String getDocSuffix() {
        return docSuffix;
    }

    public void setDocSuffix(String docSuffix) {
        this.docSuffix = docSuffix == null ? null : docSuffix.trim();
    }

    public String getDocCreateId() {
        return docCreateId;
    }

    public void setDocCreateId(String docCreateId) {
        this.docCreateId = docCreateId == null ? null : docCreateId.trim();
    }

    public String getDocSubType() {
        return docSubType;
    }

    public void setDocSubType(String docSubType) {
        this.docSubType = docSubType == null ? null : docSubType.trim();
    }

    public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath == null ? null : docPath.trim();
    }

    public Date getDocCreateTime() {
        return docCreateTime;
    }

    public void setDocCreateTime(Date docCreateTime) {
        this.docCreateTime = docCreateTime;
    }

    public Date getDocUpdateTime() {
        return docUpdateTime;
    }

    public void setDocUpdateTime(Date docUpdateTime) {
        this.docUpdateTime = docUpdateTime;
    }
}