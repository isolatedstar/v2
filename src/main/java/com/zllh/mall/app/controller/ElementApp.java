package com.zllh.mall.app.controller;

/**
 * Created by CFY on 2016/8/31.
 */
public enum  ElementApp {

    operateRelation ("operateRelation","待确认合作关系"),
    buyContract ("buyContract","待审采购协议"),
    sellContract    ("sellContract","待审销售协议"),
    buyOrder    ("buyOrder","待签采购订单"),
    sellOrder   ("sellOrder","待签销售订单"),
    getMoneySettle  ("getMoneySettle","待确认应收账款"),
    payMoneySettle ("payMoneySettle","待确认应付账款"),
    myConcern   ("myConcern","我关注的会员"),
    buyer   ("buyer","采购合作会员"),
    seller  ("seller","销售合作会员");

    private String menuType;
    private String menuDesc;

    private ElementApp(String menuType,String menuDesc){
        this.menuType = menuType;
        this.menuDesc = menuDesc;
    }


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

    /**
     * 根据类型得到 描述
     * @param menuType
     * @return
     */
    public static String getMenuDescByType(String menuType){

        for(ElementApp elementApp : ElementApp.values()){
            if(menuType.equals(elementApp.menuType)){
                return   elementApp.menuDesc;
            }
        }

        return  null;

    }

    public static void main(String[] args) {
        System.out.println(ElementApp.values());
        System.out.println(getMenuDescByType("operateRelation"));
    }

}
