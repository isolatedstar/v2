package com.zllh.mall.reportManage.model;

/**
 * @description: 表头对象 字段对象
 * @author: CFY
 * @date:2016/6/16
 */
public class ReportField implements Comparable {


    private int fieldOrder;//显示顺序
    private String fieldCode;//表头对应的字段名称
    private String fieldName;// 表头名称
    private String parentFiledName;//父表头维度名称
    private String fieldType;//表头类型（维度:dimension 量度:measure）

    private boolean canClick;//是否可以点击
    private int colspanNums;// 父表头 合并列 列数
    private int rowspanNums;//父表头  合并行 行数
    private String reportId;//点击量度 对应的报表id

    private String fieldCodeHide;//对应的隐藏域表字段编码 (如：商品ID、订单ID、协议ID 等)

    private String linkUrl;//跳转url

    private int  fieldType2; //表头类型（0：维度 1：量度）
    private int  canClick2;//是否可以点击(0：不能  1：能)

    public ReportField() {

    }


    /**
     * 绘制父表头 构造方法
     *
     * @param fieldName   （表头）名称
     * @param rowspanNums 合并多少行
     * @param colspanNums 合并多少列
     */
    public ReportField(String fieldName, int rowspanNums, int colspanNums) {

        this.fieldName = fieldName;
        this.rowspanNums = rowspanNums;
        this.colspanNums = colspanNums;
    }


    /**
     *  绘制子维度 构造方法 (old)
     * @param fieldCode  对应的表字段编码（重要 jquery遍历数据列表时使用）
     * @param fieldName  对应的（表头）名称
     * @param canClick   是否有链接
     * @param reportId   链接对应的报表ID
     */
    /*public ReportField(String fieldCode, String fieldName, boolean canClick,  String reportId) {
        this.fieldCode   = fieldCode;
        this.fieldName   = fieldName;
        this.canClick    = canClick;
        this.reportId    = reportId;

    }*/

    /**
     * 绘制子维度 构造方法
     *
     * @param fieldOrder      表头显示顺序
     * @param fieldCode       对应的表字段编码（jquery遍历数据列表时使用）
     * @param fieldCodeHide   对应的隐藏域表字段编码 (如：商品ID、订单ID、协议ID 等)
     * @param fieldName       表头名称
     * @param fieldType       表头类型:dimension(维度) measure（量度）
     * @param parentFiledName 对应的父维度（表头）名称
     * @param canClick        是否有链接
     * @param reportId        链接对应的报表ID
     * @param linkUrl   链接地址
     */

    public ReportField(int fieldOrder, String fieldCode, String fieldCodeHide, String fieldType, String fieldName, String parentFiledName, boolean canClick,  String reportId, String linkUrl) {
        this.fieldOrder = fieldOrder;
        this.fieldCode = fieldCode;
        this.fieldCodeHide = fieldCodeHide;
        this.fieldType = fieldType;
        this.fieldName = fieldName;
        this.parentFiledName = parentFiledName;
        this.canClick = canClick;
        this.reportId = reportId;
        this.linkUrl = linkUrl;

    }

    /**
     * 绘制子维度 构造方法
     *
     * @param fieldOrder      表头显示顺序
     * @param fieldCode       对应的表字段编码（jquery遍历数据列表时使用）
     * @param fieldCodeHide   对应的隐藏域表字段编码 (如：商品ID、订单ID、协议ID 等)
     * @param fieldName       表头名称
     * @param fieldType       表头类型:dimension(维度) measure（量度）
     * @param parentFiledName 对应的父维度（表头）名称
     * @param canClick        是否有链接
     * @param reportId        链接对应的报表ID
     */

    public ReportField(int fieldOrder, String fieldCode, String fieldCodeHide, int fieldType, String fieldName, String parentFiledName, int canClick,  String reportId) {
        this.fieldOrder = fieldOrder;
        this.fieldCode = fieldCode;
        this.fieldCodeHide = fieldCodeHide;
        this.fieldType2 = fieldType;
        this.fieldName = fieldName;
        this.parentFiledName = parentFiledName;
        this.canClick2 = canClick;
        this.reportId = reportId;
        this.linkUrl = linkUrl;

    }

    //按照维度顺序 排序  -1 : 0 : 1
    @Override
    public int compareTo(Object obj) {
        ReportField rFied = (ReportField) obj;
        return (this.getFieldOrder() - rFied.getFieldOrder() < 0 ? -1 :(this.getFieldOrder() == rFied.getFieldOrder() ? 0 : 1));
    }

    @Override
    public String toString(){
        return "order:" + this.fieldOrder + "fieldName:" + this.fieldName + "rowspanNums:" + this.rowspanNums + "colspanNums:" + this.colspanNums;
    }

    public int getFieldOrder() {
        return fieldOrder;
    }

    public void setFieldOrder(int fieldOrder) {
        this.fieldOrder = fieldOrder;
    }

    public String getParentFiledName() {
        return parentFiledName;
    }

    public void setParentFiledName(String parentFiledName) {
        this.parentFiledName = parentFiledName;
    }


    public String getFieldCode() {
        return fieldCode;
    }

    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public boolean isCanClick() {
        return canClick;
    }

    public void setCanClick(boolean canClick) {
        this.canClick = canClick;
    }

    public int getColspanNums() {
        return colspanNums;
    }

    public void setColspanNums(int colspanNums) {
        this.colspanNums = colspanNums;
    }

    public int getRowspanNums() {
        return rowspanNums;
    }

    public void setRowspanNums(int rowspanNums) {
        this.rowspanNums = rowspanNums;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getFieldCodeHide() {
        return fieldCodeHide;
    }

    public void setFieldCodeHide(String fieldCodeHide) {
        this.fieldCodeHide = fieldCodeHide;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
}
