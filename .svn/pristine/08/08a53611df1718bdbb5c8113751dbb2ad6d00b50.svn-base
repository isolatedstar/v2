package com.zllh.mall.common.dao;

import com.zllh.mall.common.model.ReportFields;

import java.util.List;

public interface ReportFieldsMapper {
    int deleteByPrimaryKey(String id);

    int insert(ReportFields record);

    int insertSelective(ReportFields record);

    ReportFields selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ReportFields record);

    int updateByPrimaryKey(ReportFields record);

    /**
     *根据报表ID 得到表头列表
     * @param reportId 报表ID
     * @return
     */
    List<ReportFields> getFiledsByReportId(String reportId);
}