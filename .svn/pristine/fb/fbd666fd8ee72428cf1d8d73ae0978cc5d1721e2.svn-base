package com.zllh.mall.reportManage.service;

import com.zllh.mall.common.model.ReportFilters;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: CFY
 * @date:2016/6/17
 */
public interface IReportService {

    /**
     * 报表引擎 绘制报表
     * @param param
     * @return
     */
    public Map<String,Object> createTable(List<ReportFilters> param, String reportId);

    /**
     * 根据 reportId 得到报表数据
     * @param param
     * @param reportId
     * @return
     */

    List getDataList(List<ReportFilters> param, String reportId);

    /**
     * 根据报表ID得到报表名称
     * @param reportId
     * @return
     */
    String getReportNameByReportId(String reportId);

    /**
     * 根据报表ID得到 报表查询参数列表
     * @param reportId
     * @return
     */
    List<ReportFilters> getFiltersByReportId(String reportId);

    List getSelectDataByReportId(String reportId,String mmbId,String userId);

}
