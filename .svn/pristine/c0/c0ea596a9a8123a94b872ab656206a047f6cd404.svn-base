package com.zllh.mall.common.dao;

import com.zllh.mall.common.model.ReportFilters;

import java.util.List;

public interface ReportFiltersMapper {
    int deleteByPrimaryKey(String id);

    int insert(ReportFilters record);

    int insertSelective(ReportFilters record);

    ReportFilters selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ReportFilters record);

    int updateByPrimaryKey(ReportFilters record);

    /**
     * 根据报表ID 得到查询条件列表
     * @param reportId 报表ID
     * @return
     */
    public List<ReportFilters> getFiltersByReportId(String reportId);

    /**
     * 根据报表ID 得到查询条件下拉框等的数据SQL
     * @param reportId
     * @return
     */
    public String  getFilterDataByReportId(String reportId);
}