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

<body>
    <div class="container-fluid" style=" margin-top:15px;">
        <div class="row-fluid">
            <!-- col-sm-12 -->
            <div class="col-sm-12 ">
                <div class="panel panel-default article-bj">
                    <div class="panel-heading box-shodm">
                        自定义报表展示
                    </div>


                    <div id="sqlArea" style="width: 90%;margin-left: 300px;display: none;">
                        <lable>SQL语句：</lable>
                        <textarea rows="6" cols="80" id="sqlArea" name="sqlArea"></textarea>
                    </div>
                    <div id="conditionDiv" style="width: 50%;display: none;" >
                        <lable>查询条件：</lable>
                         <table class="table table-striped table-hover " id="conditionTable">
                             <thead>
                             <tr>
                                 <th>键值</th>
                                 <th>操作符</th>
                                 <th>值1</th>
                                 <th>值2</th>
                                 <th>操作</th>
                             </tr>
                             </thead>
                             <tbody id="conditionTableBody">
                                 <tr>
                                     <td><input type="text" id="key" name="key"></td>
                                     <td><input type="text" id="operation" name="operation"></td>
                                     <td><input type="text" id="valueOne" name="valueOne"></td>
                                     <td><input type="text" id="valueTow" name="valueTow"></td>
                                     <td>
                                         <input type="button" id="addCondition" value="添加" onclick="addCondition()">
                                         <input type="button" id="delCondition" value="删除" onclick="delCondition()">
                                     </td>
                                 </tr>

                                 <tr>
                                     <td><input type="text" id="key" name="key"></td>
                                     <td><input type="text" id="operation" name="operation"></td>
                                     <td><input type="text" id="valueOne" name="valueOne"></td>
                                     <td><input type="text" id="valueTow" name="valueTow"></td>
                                     <td>
                                         <input type="button" id="addCondition" value="添加" onclick="addCondition()">
                                         <input type="button" id="delCondition" value="删除" onclick="delCondition()">
                                     </td>
                                 </tr>
                             </tbody>
                         </table>
                    </div>

                    <div align="right">
                        <#--<input type="button" value="创建报表" class="btn btn-info btn-s-md btn-default" align="right"  style="height:35px;width:75px;"  onclick="createReport();" id="showReportButton"/>-->
                        <input type="button" value="查看报表" class="btn btn-info btn-s-md btn-default" align="right"  style="height:35px;width:75px;"  onclick="showReport();" id="showReportButton"/>
                    </div>

                </div>
                <div class="table-responsive panel-table-body ">
                    <table class="table table-striped table-hover " id="reportTable">
                        <thead>
                        <tr>
                            <!--<td><input type="checkbox" name="checkAll" onclick="checkAllBox(this)"></td>-->
                            <th>简称</th>
                            <th>名称</th>
                            <th>地址</th>
                            <th>创建时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody id="mmbListBody">

                        </tbody>
                    </table>
                </div>
                <footer class="panel-footer text-right bg-light lter">
                    <div id="callBackPager" float="right;"></div>
                </footer>
            </div>
        </div>
    </div>

<script type="text/javascript">
     function showReport(){
         $.ajax({
             url : "${ctx}/report/getDataList.do",
             type : "POST",
             dataType : "json",
             cache : false,
             success : function(data){
                 console.log(data);
                 if(data!=null&&data.length > 0){
                     //先清空table中的数据
                     $("#reportTable tr:not(:first)").remove();

                     for(var i = 0; i< data.length; i++){
                         var content = "";
                         content += "<tr>";
                         content += "<td>"+data[i].v1+"</td>";
                         content += "<td>"+data[i].v2+"</td>";
                         content += "<td>"+data[i].v3+"</td>";
                         content += "<td>"+data[i].v4+"</td>";
                         content += "<td>"+data[i].v5+"</td>";
                         content += "</tr>";

                         addTr('reportTable', -1, content);
                     }
                 }

             }

         });

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
