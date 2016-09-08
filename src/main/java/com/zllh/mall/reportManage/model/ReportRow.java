package com.zllh.mall.reportManage.model;

/**
 * @description:
 * @author: CFY
 * @date:2016/6/15
 */
public class ReportRow {
    private String v0; //id
    private String v1;
    private String v2;
    private String v3;
    private String v4;
    private String v5;

    public ReportRow(){

    }

    public ReportRow(String v0 , String v1, String v2, String v3, String v4, String v5){
        this.v0 = v0;
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.v4 = v4;
        this.v5 = v5;
    }


    public String getV1() {
        return v1;
    }

    public void setV1(String v1) {
        this.v1 = v1;
    }

    public String getV2() {
        return v2;
    }

    public void setV2(String v2) {
        this.v2 = v2;
    }

    public String getV3() {
        return v3;
    }

    public void setV3(String v3) {
        this.v3 = v3;
    }

    public String getV4() {
        return v4;
    }

    public void setV4(String v4) {
        this.v4 = v4;
    }

    public String getV5() {
        return v5;
    }

    public void setV5(String v5) {
        this.v5 = v5;
    }
}
