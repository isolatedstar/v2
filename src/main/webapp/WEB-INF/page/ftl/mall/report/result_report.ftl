<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />   <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="${ctx}/mall/css/bootstrap.min.css">
    <link href="${ctx}/mall/css/theme.css" rel="stylesheet">
    <script src="${ctx}/mall/js/jquery.js"></script>
    <script src="${ctx}/mall/js/common/common.js"></script>
    <script src="${ctx}/mall/js/jquery.min.js"></script>

    <script type="text/javascript" src="${ctx}/common/jqueryValidation/js/jquery.validationEngine-zh_CN.js"></script>
    <script type="text/javascript" src="${ctx}/common/jqueryValidation/js/jquery.validationEngine.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/common/jqueryValidation/css/validationEngine.jquery.css" />

    <script src="${ctx}/mall/js/bootstrap.min.js"></script>
    <script src="${ctx}/mall/js/bootstrap-treeview.js"></script>
    <script type="text/javascript" src="${ctx}/mall/js/extendPagination.js"></script>
    <title>自定义报表</title>
</head>

<style type="text/css">
    .table th{
        text-align: center;
        background-color: #F5F5F5;
    }
    .table td{
        text-align: center;
    }

</style>

<body>
    <div class="container-fluid" style=" margin-top:15px;">
        <div class="row-fluid">
            <!-- col-sm-12 -->
            <div class="col-sm-12 ">
                <div class="panel panel-default article-bj">
                    <div class="panel-heading box-shodm">
                        自定义报表展示
                    </div>
                    <#list rowList as r>
                        ${r.v1}
                        ${r.v2}

                    </#list>

                    <div align="right">
                            <input type="button" value="查看报表" class="btn btn-info btn-s-md btn-default" align="right"  style="height:35px;width:75px;"  onclick="showReport();" id="showReportButton"/>
                    </div>


                </div>
                <div class="table-responsive panel-table-body ">
                    <table class="table table-striped table-hover " id="reportTable">
                        <tr>
                            <th rowspan="2" >简称</th>
                            <th colspan="4" >合并</th>

                        </tr>
                        <tr>
                            <th>名称</th>
                            <th>地址</th>
                            <th>创建时间</th>
                            <th>操作</th>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>2</td>
                            <td>3</td>
                            <td>4</td>
                            <td>5</td>
                        </tr>

                    </table>
                </div>
                <footer class="panel-footer text-right bg-light lter">
                    <div id="callBackPager" float="right;"></div>
                </footer>
            </div>
        </div>
    </div>

<script type="text/javascript">

     $(function(){
         showReport();
     }) ;

     function showReport(){
         $.ajax({
             url : "${ctx}/report/createReport.do",
             //url : "${ctx}/test/createReportDemo.do",
             dataType : "json",
             type : "POST",
             cache : false,
             success : function(data){


                 $("#reportTable tr").remove() ;
                 //报表table内容
                 var tableHtml = "";

                 //绘制父维度
                 if(data.parentFildList.length>0){
                     var parentHead ="<tr>";
                     for(var p = 0; p < data.parentFildList.length; p++){

                         var pvalue = data.parentFildList[p];
                         //父维度合并行 和并列
                         parentHead += "<th colspan="+pvalue.colspanNums+" rowspan="+pvalue.rowspanNums+">"+pvalue.fieldName+"</th>"
                     }
                     parentHead += "</tr>";

                     tableHtml += parentHead;
                 }


                 //绘制子维度表头
                 if(data.childFildList.length>0){
                     var childHead = "<tr>";

                     for(var c = 1; c<data.childFildList.length; c++){
                         childHead +="<th>"+data.childFildList[c].fieldName+"</th>";
                     }
                     childHead += "</tr>";

                     tableHtml += childHead;
                 }


                 var json = data.dataList;

                 console.log(json);
                 if(json.length>0){
                     var trHtml = "";
                     for(var d = 0; d<json.length; d++){

                         trHtml += "<tr>";
                         var tdHtml = "";

                         //根据表头 绘制数据值（每格） 如果有链接 则绘制链接
                         var column = "";
                         var hideColumn = "";
                         var reportId = "";

                         //根据子维度列表中的 表字段名称取得每格数据
                         for(var z = 0; z < data.childFildList.length; z++){
                             column =  data.childFildList[z].fieldCode;  //维度对应表字段名称
                             hideColumn = data.childFildList[z].fieldCodeHide;//隐藏域字段名称
                             reportId  =  data.childFildList[z].reportId; //链接对应报表ID

                             // z==0 第一列 添加ID隐藏域
                             //维度是否可以点击
                             if(data.childFildList[z].canClick ) {
                                 if(hideColumn != null &&  hideColumn != "") {
                                     tdHtml += "<td><input type='hidden' name='"+json[d][hideColumn]+"' value='"+json[d][hideColumn]+"'/><a href='#' onclick=showMoreReport('22')>"+json[d][column]+"</a></td>"
                                 }else{
                                     tdHtml += "<td><a href='#' onclick=showMoreReport('"+json[d][reportId]+"')>"+json[d][column]+"</a></td>"
                                 }
                             }else{
                                 if(hideColumn != null &&  hideColumn != "") {
                                     tdHtml += "<td><input type='hidden' name='"+json[d][hideColumn]+"' value='"+json[d][hideColumn]+"'/>"+json[d][column]+"</td>"
                                 }else{
                                     tdHtml += "<td>"+json[d][column]+"</td>"
                                 }

                             }

                         }

                         trHtml += tdHtml;
                         trHtml += "</tr>"
                     }

                     tableHtml += trHtml;

                 }

                 $("#reportTable").html(tableHtml);

             }
         }) ;
     }

     /**
      * 点击链接显示 明细页面
      */
     function showMoreReport(reportId){
         alert("打开明细页面"+reportId);
     }

     /**
      * 为table指定行添加一行
      * tab 表id
      * row 行数，如：0->第一行 1->第二行 -2->倒数第二行 -1->最后一行
      * trHtml 添加行的html代码
      */
     function addTr(tab, row, trHtml){
         //获取table最后一行 $("#tab tr:last")
         //获取table第一行 $("#tab tr").eq(0)
         //获取table倒数第二行 $("#tab tr").eq(-2)
         var $tr=$("#"+tab+" tr").eq(row);
         if($tr.size()==0){
             alert("指定的table id或行数不存在！");
             return;
         }
         $tr.after(trHtml);
     }

</script>


</body>
</html>
