package com.zllh.mall.reportManage.controller;

import com.alibaba.fastjson.JSON;
import com.zllh.base.controller.BaseController;
import com.zllh.common.common.model.model_extend.UserExtendBean;
import com.zllh.mall.common.model.MtMuser;
import com.zllh.mall.common.model.ReportFilters;
import com.zllh.mall.reportManage.service.IReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description: 模拟跳转到报表引擎
 * @author: CFY
 * @date:2016/6/17
 */

@Controller
@RequestMapping("/test")
public class ReportController extends BaseController {

    @Resource
    private IReportService reportService;



    /**    /mmb/toMmbPageNoFilter.do       /test/initReportDemo.do?reportId=2
     * 初始化页面
     * @return
     */
    @RequestMapping("/initReportDemo")
    public String initReportDemo(Model model,
            @RequestParam(value = "reportId", required = false) String reportId) {


        String mmbId = "";//当前登录会员ID
        String userId = "";//当前登录操作员ID
        UserExtendBean user = (UserExtendBean) this.session.getAttribute("user");
        if (user != null) {
            MtMuser muser = user.getMuser();
            mmbId = muser.getMmbId();
            userId = muser.getId();
        }

        List<ReportFilters> reportFiltersList = new ArrayList<ReportFilters>();
        reportFiltersList = reportService.getFiltersByReportId(reportId);

        List selectDataList = reportService.getSelectDataByReportId(reportId,mmbId,userId);

        model.addAttribute("reportId",reportId);
        model.addAttribute("reportFiltersList",reportFiltersList);
        model.addAttribute("selectDataList",selectDataList);

        return "mall/report/report_demo";
    }


    /**
     * 接收查询条件 调用报表生成方法 返回 模板页面
     *
     * @param param json格式的 报表查询对象集合
     * @return 包含 "父表头、子表头、数据集" 3个list的 map
     */
    @RequestMapping("/createReport")
    @ResponseBody
    public Map<String, Object> createReport(
            @RequestParam(value = "param", required = false) String param,
            @RequestParam(value = "reportId", required = false) String reportId) {


        List<ReportFilters> filtersList = new ArrayList<>();
        if(param != null && param.trim() != ""){

            filtersList =  JSON.parseArray(param,ReportFilters.class);

        }

        Map<String, Object> resultMap;
        //(封装 父表头 子表头 以及数据集)供jquey解析生成动态报表使用。
        resultMap = reportService.createTable(filtersList, reportId);

        return resultMap;

    }


}


