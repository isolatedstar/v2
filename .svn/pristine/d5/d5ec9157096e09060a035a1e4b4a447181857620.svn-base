package com.zllh.mall.reportManage.model;

import com.zllh.mall.common.model.ReportFields;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.*;

/**
 * @description:
 * @author: CFY
 * @date:2016/6/20
 */
public class ReportUtils {

    public static List<ReportFields> createParentListByChildList(List<ReportFields> childColumnList){
        //目前暂时最多只支持2层维度报表
        //父维度列表
        List<ReportFields> parentFildList  = new ArrayList<ReportFields>();
        if(childColumnList == null || childColumnList.size() == 0){
            childColumnList = initChidlList();
        }

        boolean isHasParent = false;//是否存在父表头
        if(childColumnList != null && childColumnList.size() > 0){

            for (ReportFields childColumn : childColumnList) {

                boolean isInParentList = false;//父表头 是否在父表头列表中

                if(childColumn.getFieldParentTitle() != null && !"".equals(childColumn.getFieldParentTitle())){

                    ReportFields parentColumn = new ReportFields();//父表头对象

                    //如果父表头列表为空 则初始化一个父表头并放入 父表头列表中
                    if(parentFildList.size() == 0 || parentFildList == null){

                        parentColumn = initparentColumn(childColumn.getFieldParentTitle(), childColumn.getFieldIndex());
                        parentFildList.add(parentColumn);
                        isInParentList = true;
                    }else{
                        // 循环父表头列表 查看是否存在取得表头的父表头 然后计算个数放入 父表头列表中
                        for(ReportFields pField : parentFildList){

                            //如果父指标表头存在 则其列合并数 加1
                            if(childColumn.getFieldParentTitle().equals(pField.getFieldTitle())){
                                pField.setColspanNums(pField.getColspanNums()+1);
                                isInParentList = true;
                            }
                        }
                    }

                    //如果不存在父表头列表中 则初始化一个父表头并放入 父表头列表中
                    if(!isInParentList){
                        parentColumn = initparentColumn(childColumn.getFieldParentTitle(), childColumn.getFieldIndex());
                        parentFildList.add(parentColumn);
                        isInParentList = true;
                    }

                    isHasParent = true;
                }


            }
            //如果存在父表头 则将父表头为空的列 行合并数设置为2
            if(isHasParent){
                for (ReportFields childColumn2 : childColumnList) {
                    if (childColumn2.getFieldParentTitle() == null || "".equals(childColumn2.getFieldParentTitle())) {
                        ReportFields parentColumn = new ReportFields();//父表头对象
                        parentColumn.setFieldTitle(childColumn2.getFieldTitle());
                        parentColumn.setFieldIndex(childColumn2.getFieldIndex());
                        parentColumn.setRowspanNums(2);
                        parentColumn.setColspanNums(0);

                        parentFildList.add(parentColumn);
                    }
                }

                //对父表头列表按照表头顺序排序 (ReportField 对象实现compareble 重写compareTo方法)
                Collections.sort(parentFildList);

            }


        }

        return parentFildList;
    }

      /**
     * 初始化一个 父表头指标
     * @param parentColumnName  父表头名称
     * @return  表头
     */
    public  static ReportFields initparentColumn(String parentColumnName,int fieldOrder){

        ReportFields parentColumn = new ReportFields();//父表头对象
        parentColumn.setFieldTitle(parentColumnName);
        parentColumn.setFieldIndex(fieldOrder);
        parentColumn.setRowspanNums(0);
        parentColumn.setColspanNums(1);
        return parentColumn;
    }


    /**
     * 测试类中 初始化子表头列表数据
     * @return
     */
    public static List initChidlList(){
        //子表头列表
        List<ReportFields> childFildList  = new ArrayList<ReportFields>();

        //绘制 子表头
        //ReportFields childColumn  = new ReportFields(1,"v0","ID",false,null); //商品ID 显示商品详情参数
        ReportFields childColumn1 = new ReportFields(1,"v1","v1",0,"商品",null,0,null);
        ReportFields childColumn2 = new ReportFields(2,"v2","v2",1,"采购金额","采购",0,null);
        ReportFields childColumn3 = new ReportFields(3,"v3","v3",1,"采购数量","采购",1,null);
        ReportFields childColumn4 = new ReportFields(4,"v4","v4",1,"销售金额","销售",0,null);
        ReportFields childColumn5 = new ReportFields(5,"v5","v5",1,"销售数量","销售",1,null);

        childFildList.add(childColumn1);
        childFildList.add(childColumn2);
        childFildList.add(childColumn3);
        childFildList.add(childColumn4);
        childFildList.add(childColumn5);

        return childFildList;
    }

    /**
     * json 转换 map
     * @param jsonObject
     * @return
     */
    public static Map<String,Object> jsonToMap(JSONObject jsonObject){

        Map<String,Object> resultMap = new HashMap();
        String key ;
        Object value;

        Iterator iterator = jsonObject.keys();
        while(iterator.hasNext()){

            key =  iterator.next().toString();
            value = jsonObject.get(key);
            if(value instanceof  JSONObject){
                value = jsonToMap((JSONObject) jsonObject.get(key));
            }else if(value instanceof  JSONArray){
                value = jsonToList((JSONArray) jsonObject.get(key));
            }else{

            }

            resultMap.put(key, value);
        }
        return resultMap;

    }

    /**
     * jsonArray 转list
     * @param json
     * @return
     */
    public static List jsonToList(JSONArray json){

        if(json == null){
            return null;
        }
        List resultList = new ArrayList();
        for (int i = 0; i < json.size(); i++) {
           if(json.get(i) instanceof  JSONObject){
               resultList.add(jsonToMap((JSONObject) json.get(i)));
           }else if(json.get(i) instanceof  JSONArray){
               resultList.add(jsonToList((JSONArray) json.get(i)));
           }else{
               resultList.add(json.get(i));
           }
        }

        return resultList;
    }




    public static void main(String[] args) {

        List<ReportFields> childColumnList = initChidlList();
        List<ReportFields> parentFildList = createParentListByChildList(childColumnList);

        for(ReportFields parentReportField : parentFildList){
            System.out.println(parentReportField.toString());
        }

        String aa = "{goodsId:'id0',goodsId:'id0',goodsId:'id0',status:2,mmbFname:'dfsaadf'}";

        JSONObject jb = JSONObject.fromObject(aa);

        Map<String,Object> resultMap =  jsonToMap(jb);
        for(String key : resultMap.keySet()){
            System.out.println(key+" : " + resultMap.get(key));
        }

    }

}
