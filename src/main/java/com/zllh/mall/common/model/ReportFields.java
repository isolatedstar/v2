package com.zllh.mall.common.model;

public class ReportFields implements Comparable{

    private String id;

    private String reportId;   //报表ID

    private Integer fieldIndex;    //表头显示顺序

    private String fieldCode;   //对应的表字段编码（jquery遍历数据列表时使用）

    private String fieldHideCode;   //对应的隐藏域表字段编码 (如：商品ID、订单ID、协议ID 等)

    private Integer fieldType;  //表头类型: 0(维度) 1（量度）

    private String fieldTitle;    //表头名称

    private String fieldParentTitle; //对应的父表头名称

    private Integer canClick;   //是否有链接 0:无 1:有

    private String linkReportId;      //链接对应的报表ID

    private Integer colspanNums;// 父表头 合并列 列数
    private Integer rowspanNums;//父表头  合并行 行数




    public  ReportFields(){

    }

    /**
     * 绘制子表头 构造方法
     *
     * @param fieldIndex      表头显示顺序
     * @param fieldCode       对应的表字段编码（jquery遍历数据列表时使用）
     * @param fieldHideCode   对应的隐藏域表字段编码 (如：商品ID、订单ID、协议ID 等)
     * @param fieldType       表头类型: 0(维度) 1（量度）
     * @param fieldTitle       表头名称
     * @param fieldParentTitle    对应的父（表头）名称
     * @param canClick        是否有链接 0:无 1:有
     * @param linkReportId    链接对应的报表ID
     */

    public ReportFields(int fieldIndex, String fieldCode, String fieldHideCode, int fieldType, String fieldTitle, String fieldParentTitle, int canClick,  String linkReportId) {
        this.fieldIndex = fieldIndex;
        this.fieldCode = fieldCode;
        this.fieldHideCode = fieldHideCode;
        this.fieldType = fieldType;
        this.fieldTitle = fieldTitle;
        this.fieldParentTitle = fieldParentTitle;
        this.canClick = canClick;
        this.linkReportId = linkReportId;

    }

    /**
     * 绘制父表头 构造方法
     *
     * @param fieldTitle  表头名称
     * @param rowspanNums 合并多少行
     * @param colspanNums 合并多少列
     */
    public ReportFields(String fieldTitle, int rowspanNums, int colspanNums) {

        this.fieldTitle = fieldTitle;
        this.rowspanNums = rowspanNums;
        this.colspanNums = colspanNums;
    }



    //按照维度顺序 排序  -1 : 0 : 1
    @Override
    public int compareTo(Object obj) {
        ReportFields reportfield = (ReportFields) obj;
        return this.getFieldIndex() - reportfield.getFieldIndex() < 0 ? -1 : (this.getFieldIndex() == reportfield.getFieldIndex() ? 0 : 1);

    }

    @Override
    public String toString(){
        return "fieldTitle:"+this.fieldTitle+"--rowspanNums:"+this.rowspanNums+"--colspanNums:"+colspanNums;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId == null ? null : reportId.trim();
    }

    public Integer getFieldIndex() {
        return fieldIndex;
    }

    public void setFieldIndex(Integer fieldIndex) {
        this.fieldIndex = fieldIndex;
    }

    public String getFieldCode() {
        return fieldCode;
    }

    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode == null ? null : fieldCode.trim();
    }

    public String getFieldHideCode() {
        return fieldHideCode;
    }

    public void setFieldHideCode(String fieldHideCode) {
        this.fieldHideCode = fieldHideCode == null ? null : fieldHideCode.trim();
    }

    public Integer getFieldType() {
        return fieldType;
    }

    public void setFieldType(Integer fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldTitle() {
        return fieldTitle;
    }

    public void setFieldTitle(String fieldTitle) {
        this.fieldTitle = fieldTitle == null ? null : fieldTitle.trim();
    }

    public String getFieldParentTitle() {
        return fieldParentTitle;
    }

    public void setFieldParentTitle(String fieldParentTitle) {
        this.fieldParentTitle = fieldParentTitle == null ? null : fieldParentTitle.trim();
    }

    public Integer getCanClick() {
        return canClick;
    }

    public void setCanClick(Integer canClick) {
        this.canClick = canClick;
    }

    public String getLinkReportId() {
        return linkReportId;
    }

    public void setLinkReportId(String linkReportId) {
        this.linkReportId = linkReportId;
    }

    public Integer getColspanNums() {
        return colspanNums;
    }

    public void setColspanNums(Integer colspanNums) {
        this.colspanNums = colspanNums;
    }

    public Integer getRowspanNums() {
        return rowspanNums;
    }

    public void setRowspanNums(Integer rowspanNums) {
        this.rowspanNums = rowspanNums;
    }
}