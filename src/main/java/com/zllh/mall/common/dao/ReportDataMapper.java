package com.zllh.mall.common.dao;

import com.zllh.mall.common.model.ReportData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReportDataMapper {
    int deleteByPrimaryKey(String reportId);

    int insert(ReportData record);

    int insertSelective(ReportData record);

    ReportData selectByPrimaryKey(String reportId);

    int updateByPrimaryKeySelective(ReportData record);

    int updateByPrimaryKeyWithBLOBs(ReportData record);

    int updateByPrimaryKey(ReportData record);

    /**
     * 根据 报表动态SQL 得到 报表数据
     * @param reportSql
     * @return
     */
    List queryReportDataFromSql(@Param("reportSql") String reportSql);

    /**
     * 根据报表ID 得到报表SQL
     * @param reportId
     * @return
     */
    String getReportSqlByReportId(String reportId);

    /**
     * 根据报表ID 得到报表名称
     * @param reportId
     * @return
     */
    String getReportNameByReportId(String reportId);

}