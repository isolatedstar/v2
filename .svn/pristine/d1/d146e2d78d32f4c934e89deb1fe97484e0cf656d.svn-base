<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />   <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <link href="${ctx}/mall/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/mall/css/theme.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/mall/css/pulic.css" rel="stylesheet" type="text/css">

    <link href="${ctx}/mall/css/SimpleTree.css" rel="stylesheet" type="text/css">

    <script src="${ctx}/mall/js/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script src="${ctx}/mall/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${ctx}/mall/js/SimpleTree.js" type="text/javascript"></script>
    <script src="${ctx}/mall/js/bootstrap-treeview.js"></script>
    <link rel="stylesheet" type="text/css" media="all" href="${ctx}/factoring/css/daterangepicker-bs3.css"/>
    <script type="text/javascript" src="${ctx}/factoring/js/moment.js"></script>

    <script type="text/javascript" src="${ctx}/factoring/js/daterangepicker.js"></script>
    <script type="text/javascript" src="${ctx}/factoring/js/DatePicker/WdatePicker.js"></script>



    <!--json格式化文件 -->
    <script type="text/javascript" src="${ctx}/mall/js/json2.js"></script>
    <!--报表引擎JS文件 -->
    <script type="text/javascript" src="${ctx}/mall/js/reportEngine.js"></script>

    <title>会员管理</title>

    <style type="text/css">
        .table th{
            text-align: center;
            background-color: #F5F5F5;
        }
        .table td{
            text-align: center;
        }

    </style>
</head>

<body>
<div class="container-fluid" style=" margin-top:15px;">
    <div class="row-fluid">
        <!-- col-sm-12 -->
        <div class="col-sm-12 ">
            <div class="panel panel-default article-bj">
                <div class="panel-heading box-shodm">
                    ${reportName?default("自定义报表")}
                    <input type="hidden" value="${reportId!}" id="reportId" name="reportId">
                </div>
                <form id="queryMmbForm" action="${ctx}/mmb/queryMMBListNoFilter.do" 　method="post">
                    <div class="row wrapper form-margin">

                        <#if reportFiltersList ??>
                            <#list reportFiltersList as reportFilter>
                                <#if reportFilter.filterType?? && reportFilter.filterType =="text">
                                    <div class="col-md-3">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                                <h5 class="h5-margin">${reportFilter.filterTitle!}:</h5>
                                            </div>
                                            <input type="text" placeholder="" name="${reportFilter.filterCode!}" value="${reportFilter.filterValue!}"
                                                   class="form-control" id="${reportFilter.filterCode!}" title="${reportFilter.filterDesc!}">
                                        </div>
                                    </div>


                                <#elseif reportFilter.filterType?? && reportFilter.filterType =="date">
                                    <div class="col-md-3">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                                <h5 class="h5-margin">${reportFilter.filterTitle!}:</h5>
                                            </div>
                                            <input type="text"  class="ui_timepicker form-control" id="startTime" name="${reportFilter.filterCode!}" />
                                        </div>
                                    </div>

                                <#elseif reportFilter.filterType?? && reportFilter.filterType =="select">
                                    <div class="col-md-3">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                                <h5 class="h5-margin">${reportFilter.filterTitle!}:</h5>
                                            </div>
                                            <select id="status" name="${reportFilter.filterCode!}"  tabindex="-1" class="form-control">
                                                <#if selectDataList ??>
                                                    <#list selectDataList as selectData>
                                                        <option value="${selectData.selectValue!}">${selectData.selectText!}</option>
                                                    </#list>
                                                </#if>

                                            </select>
                                        </div>
                                    </div>

                                <#elseif reportFilter.filterType?? && reportFilter.filterType =="daterange">
                                    <div class="col-md-3">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                                <h5 class="h5-margin">${reportFilter.filterTitle!}:</h5>
                                            </div>

                                            <input type="text" class="form-control validate[required]" readonly="readonly" />


                                            </select>
                                        </div>
                                    </div>

                                </#if>

                            </#list>

                        </#if>

                       <#-- <div class="col-md-3">
                            <div class="input-group">
                                <div class="input-group-btn">
                                    <h5 class="h5-margin">订单类型:</h5>
                                </div>
                                <select id="status" name="status" tabindex="-1" class="form-control">
                                    <option value="1">全部</option>
                                    <option value="2">采购</option>
                                    <option value="3">销售</option>
                                </select>
                            </div>
                        </div>-->

                        <input type="button" class="btn btn-info btn-s-md btn-default" value="查询"
                               style="height:35px;width:65px" onclick="queryMmbByCon();" id="queryMmbButton"/>
                    </div>
                </form>
            </div>

            <#--报表内容-->
            <div class="table-responsive panel-table-body ">
                <table class="table table-striped table-hover " id="reportTable">

                </table>
            </div>


            <#--维度具体信息-->
            <div class="modal fade" id="detailDesc" role="dialog" aria-labelledby="gridSystemModalLabel"></div>

            <#--量度明细链接报表-->
            <div class="modal fade" id="numsList" role="dialog" aria-labelledby="gridSystemModalLabel"></div>

            <footer class="panel-footer text-right bg-light lter">
                <div id="callBackPager" float="right;"></div>
            </footer>
        </div>
    </div>
</div>

<script type="text/javascript">

    <#--页面加载完后 调用查询方法

    $(function(){

        $("#startTime").datetimepicker({
            locale: moment.locale('zh-cn'),
            showTodayButton: true,
            dayViewHeaderFormat: 'YYYY MM',
            format: 'YYYY-MM-DD'
        });

    });-->

    function queryMmbByCon(){

        var reportId =  $("#reportId").val();

        var key = "";
        var value = "";


        var paramFilters = new Array();

        // 遍历非空的查询条件 start
        $("form select ").each(function(index,elem){
            value = $(elem).val();
            if(value != null && $.trim(value) != ""){
                key = $(elem).attr("name");
                paramFilters.push( new reportFilter(key, value,"select"));
            }
        });

        $("form input:not(:button) ").each(function(index,elem){
            value = $(elem).val();
            if(value != null && $.trim(value) != ""){
                key = $(elem).attr("name");
                paramFilters.push( new reportFilter(key, value,"input"));
            }
        });

        //遍历非空的查询条件 end

        var param = JSON.stringify(paramFilters);

        /**
         * 调用报表引擎的绘制报表方法
         * @param url  ：查询按钮发送的请求 固定为 ${ctx}/test/createReport.do
         * @param json ：查询条件封装的json格式的数据
         * @param tableId ： 报表tableID 或 明细页面tableID
         * @param
         */
        createReport("${ctx}/test/createReport.do",param,"reportTable",reportId);
    }

</script>

</body>
</html>
