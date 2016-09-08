package com.zllh.mall.common.model;

public class MtMmbWebsite {
    private String mmbId;

    private String mmbIntroduce;

    private String mmbTitle;

    private String mmbName;

    private String mmbPath;
    //企业图标路径
    private String mmbLogo;
    
    public String getMmbLogo() {
		return mmbLogo;
	}

	public void setMmbLogo(String mmbLogo) {
		 this.mmbLogo = mmbLogo == null ? null : mmbLogo.trim();
	}

	public String getMmbId() {
        return mmbId;
    }

    public void setMmbId(String mmbId) {
        this.mmbId = mmbId == null ? null : mmbId.trim();
    }

    public String getMmbIntroduce() {
        return mmbIntroduce;
    }

    public void setMmbIntroduce(String mmbIntroduce) {
        this.mmbIntroduce = mmbIntroduce == null ? null : mmbIntroduce.trim();
    }

    public String getMmbTitle() {
        return mmbTitle;
    }

    public void setMmbTitle(String mmbTitle) {
        this.mmbTitle = mmbTitle == null ? null : mmbTitle.trim();
    }

    public String getMmbName() {
        return mmbName;
    }

    public void setMmbName(String mmbName) {
        this.mmbName = mmbName == null ? null : mmbName.trim();
    }

    public String getMmbPath() {
        return mmbPath;
    }

    public void setMmbPath(String mmbPath) {
        this.mmbPath = mmbPath == null ? null : mmbPath.trim();
    }
}