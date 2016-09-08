package com.zllh.mall.common.model;

/**
 * Created by CFY on 2016/8/29.
 */
public class AppData {



    private String menuType; //菜单类型
    private String menuDesc; //菜单描述
    private int  menuNums;   //菜单内容list条数


    private String content ; //提示内容
    private String editTime; //最新编辑时间
    private String id; //行ID

    private String relaMmbId;//会员ID
    private String mmbFname; //会员全称
    private int    grade;//会员关注等级
    private String relation;//关系会员标示

    private String code;//账号和地址编码
    private String desc;//账号和地址名称

    public AppData(){

    }



    /**
     * 手机端目录
     * @param menuType
     * @param menuDesc
     */
    public AppData(String menuType, String menuDesc, int  menuNums){
        this.menuType = menuType;
        this.menuDesc = menuDesc;
        this.menuNums = menuNums;
    }

    /**
     * 待办事宜列表内容 对象
     * @param menuType
     * @param id
     * @param content
     * @param editTime
     */
    public AppData(String menuType, String id, String content, String editTime){
        this.content = menuType;
        this.id = id;
        this.content = content;
        this.editTime = editTime;

    }

    /**
     * 我关注的会员 列表内容 对象
     * @param menuType
     * @param id
     * @param  relaMmbId
     * @param mmbFname
     * @param grade
     */
    public AppData(String menuType, String id, String relaMmbId, String mmbFname,int grade){
        this.menuType = menuType;
        this.id = id;
        this.mmbFname = mmbFname;
        this.grade = grade;
        this.relaMmbId = relaMmbId;
    }

    /**
     * 关系列表内容 对象
     * @param menuType
     * @param id
     * @param  relaMmbId
     * @param mmbFname
     * @param  relation
     */
    public AppData(String menuType, String id, String relaMmbId, String mmbFname,String relation){
        this.menuType = menuType;
        this.id = id;
        this.mmbFname = mmbFname;
        this.relaMmbId = relaMmbId;
        this.relation = relation;
    }

    /**
     * 同意按钮 银行账号和收发货地址构造器
     * @param code
     * @param desc
     */
    public AppData(String code,String desc){
        this.code = code;
        this.desc = desc;
    };


    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getMenuDesc() {
        return menuDesc;
    }

    public void setMenuDesc(String menuDesc) {
        this.menuDesc = menuDesc;
    }

    public int getMenuNums() {
        return menuNums;
    }

    public void setMenuNums(int menuNums) {
        this.menuNums = menuNums;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMmbFname() {
        return mmbFname;
    }

    public void setMmbFname(String mmbFname) {
        this.mmbFname = mmbFname;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getRelaMmbId() {
        return relaMmbId;
    }

    public void setRelaMmbId(String relaMmbId) {
        this.relaMmbId = relaMmbId;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
