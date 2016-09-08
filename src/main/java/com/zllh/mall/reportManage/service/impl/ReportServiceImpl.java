package com.zllh.mall.reportManage.service.impl;

import com.zllh.mall.common.dao.ReportDataMapper;
import com.zllh.mall.common.dao.ReportFieldsMapper;
import com.zllh.mall.common.dao.ReportFiltersMapper;
import com.zllh.mall.common.model.ReportFields;
import com.zllh.mall.common.model.ReportFields;
import com.zllh.mall.common.model.ReportFilters;
import com.zllh.mall.reportManage.model.GoodsDemo;
import com.zllh.mall.reportManage.model.ReportField;
import com.zllh.mall.reportManage.model.ReportUtils;
import com.zllh.mall.reportManage.service.IReportService;
import com.zllh.utils.common.DictionaryUtil;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ReportServiceImpl implements IReportService {


    @Autowired
    private ReportFieldsMapper ReportFieldsMapper;

    @Autowired
    private ReportFiltersMapper reportFiltersMapper;

    @Autowired
    private ReportDataMapper reportDataMapper;


    /**
     * 报表引擎-自动化报表 初始化数据
     *
     * @param param    查询参数封装json
     * @param reportId 报表ID
     * @return map  返回 报表引擎所需的3个列表list(
     */
    @Override
    public Map<String, Object> createTable(List<ReportFilters> param, String reportId) {


        //目前暂时最多只支持2层表头报表

        //父表头列表
        List<ReportFields> parentFildList;
        //子表头列表
        List<ReportFields> childFildList;

        childFildList = ReportFieldsMapper.getFiledsByReportId(reportId);

        //调用报表工具类 根据子表头列表生成父表头列表
        parentFildList = ReportUtils.createParentListByChildList(childFildList);

        //对子表头列表按照表头顺序进行排序
        Collections.sort(childFildList);

        //根据参数 调用dao层得到报表具体数据
        List dataList = getDataList(param, reportId);

        JSONArray jsonArray = null;
        //将报表数据转换为json数组
        if (dataList != null && dataList.size() > 0) {
            jsonArray = JSONArray.fromObject(dataList);
        }


        //将报表引擎所需的3个列表list( 父表头 子表头 以及数据集 列表) 放入map中返回到controller 最后放到request中 供jquery引擎动态绘制
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("parentFildList", parentFildList);
        resultMap.put("childFildList", childFildList);
        resultMap.put("dataList", jsonArray == null ? null : jsonArray);

        return resultMap;
    }


    /**
     * 根据报表ID得到报表SQL 查询报表数据
     *
     * @param param
     * @return
     */
    @Override
    public List getDataList(List<ReportFilters> param, String reportId) {

        List mmbList = new ArrayList();


        String reportSql = "";
        reportSql = reportDataMapper.getReportSqlByReportId(reportId);

        //将查询条件 匹配到SQL语句上
//        if (param != null || param.size() > 0) {
//            StringBuffer sqlBuffer = new StringBuffer();
//            String conditionStr = "";
//            sqlBuffer.append(reportSql);
//
//            for(ReportFilters reportFilter : param){
//                if("text".equals(reportFilter.getFilterType())){
//                    conditionStr = "  and " +reportFilter.getFilterCode()+"  like  '%"+reportFilter.getFilterValue()+"%'";
//                    sqlBuffer.append(conditionStr);
//                }
//            }
//            if(sqlBuffer!=null && sqlBuffer.length() >0 ){
//                reportSql = sqlBuffer.toString();
//            }
//        }
        
        //匹配判断  reportId     
       
        	if (param != null && param.size() > 0) {  
                StringBuffer sqlBuffer = new StringBuffer();
                String conditionStr = "";
                sqlBuffer.append(reportSql);

                for(ReportFilters reportFilter : param){
                    if("select".equals(reportFilter.getFilterType())){
                        conditionStr = "  and " +reportFilter.getFilterCode()+"  = "+"\'"+reportFilter.getFilterValue()+"\'";
                        sqlBuffer.append(conditionStr);
                    }else if("text".equals(reportFilter.getFilterType())){
                      conditionStr = "  and " +reportFilter.getFilterCode()+"  like  '%"+reportFilter.getFilterValue()+"%'";
                     sqlBuffer.append(conditionStr);
                    }
                }
                if(DictionaryUtil.REPORT_BUYER.equals(reportId)){
                	sqlBuffer.append("     GROUP BY od.mbid, od.goods_id ");
                }
                if(DictionaryUtil.REPORT_SELLER.equals(reportId)){
                	 sqlBuffer.append("      GROUP BY od.msid ,od.goods_id  ");
                }
                if(DictionaryUtil.REPORT_GOODS.equals(reportId)){
                	sqlBuffer.append("      GROUP BY od.goods_id  ");
                }
                if(sqlBuffer!=null && sqlBuffer.length() >0 ){
                    reportSql = sqlBuffer.toString();
                }
            }else {
            	return null;
            }
        if (reportSql != null && reportSql != "") {

            mmbList = reportDataMapper.queryReportDataFromSql(reportSql);
        }

        return mmbList;
    }

    @Override
    public String getReportNameByReportId(String reportId) {

        return reportDataMapper.getReportNameByReportId(reportId);
    }



    @Override
    public List<ReportFilters> getFiltersByReportId(String reportId) {

        return reportFiltersMapper.getFiltersByReportId(reportId);
    }

    @Override
    public List getSelectDataByReportId(String reportId,String mmbId,String userId) {

        String selectSql =  reportFiltersMapper.getFilterDataByReportId(reportId);
        List selectList = new ArrayList();

        if (selectSql != null && selectSql != "") {

            if(selectSql.contains("session_mmbId")){
                selectSql = selectSql.replaceAll("session_mmbId", "\'" + mmbId + "\'");
            }else if(selectSql.contains("session_userId")){
                selectSql = selectSql.replaceAll("session_userId", "\'" + userId + "\'");
            }

            selectList = reportDataMapper.queryReportDataFromSql(selectSql);
        }

        return selectList;
    }


}
