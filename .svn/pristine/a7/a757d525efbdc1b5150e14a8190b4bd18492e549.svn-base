/**
 * Created by xxx on 2016/6/21.
 */


//jquery 声明一个查询参数对象
function reportFilter(filterCode,filterValue,filterType){
    this.filterCode = filterCode;
    this.filterValue = filterValue;
    this.filterType = filterType;

}

var linkUrl;

/**
 * 报表引擎
 * @param url  ：查询按钮发送的请求
 * @param json ：查询条件封装的json格式的数据
 * @param tableId ： 报表tableID 或 明细页面tableID
 */
function createReport(url,param,tableId,reportId){

    linkUrl = url;
    //condition = json;
    $.ajax({
        url : url,
        data :{"param" : param,"reportId" : reportId},
        dataType : "json",
        type : "POST",
        error : function(){
           alert("异常！");
        },
        success : function(data){

            //清空table内容
            $("#"+tableId+" tr").remove() ;
            //报表table内容
            var tableHtml = "";

            //绘制父维度
            if(data.parentFildList.length>0){

                var parentHead ="<tr>";
                for(var p = 0; p < data.parentFildList.length; p++){


                    var pvalue = data.parentFildList[p];

                    //父维度合并行 和并列
                    parentHead += "<th colspan="+pvalue.colspanNums+" rowspan="+pvalue.rowspanNums+">"+pvalue.fieldTitle+"</th>"
                }
                parentHead += "</tr>";

                tableHtml += parentHead;
            }


            //绘制子维度表头
            if(data.childFildList.length>0){
                var childHead = "<tr>";

                for(var c = 0; c<data.childFildList.length; c++){

                    childHead +="<th>"+data.childFildList[c].fieldTitle+"</th>";
                }
                childHead += "</tr>";

                tableHtml += childHead;
            }


            var json = data.dataList;

            if(json!=""&&json!=null&&json.length>0){
                var trHtml = "";
                for(var d = 0; d<json.length; d++){

                    trHtml += "<tr>";
                    var tdHtml = "";

                    //根据表头 绘制数据值（每格） 如果有链接 则绘制链接
                    var column = "";
                    var hideColumn = "";
                    var linkReportId = "";
                    var fieldType = "";

                    //根据子维度列表中的 表字段名称取得每格数据
                    for(var z = 0; z < data.childFildList.length; z++){
                        column =  data.childFildList[z].fieldCode;  //维度对应表字段名称
                        hideColumn = data.childFildList[z].fieldHideCode;//隐藏域字段名称
                        linkReportId  =  data.childFildList[z].linkReportId; //链接对应报表ID
                        fieldType =  data.childFildList[z].fieldType;//表头类型 0:维度  1:量度

                        // z==0 第一列 添加ID隐藏域
                        //维度是否可以点击
                        if(data.childFildList[z].canClick ) {
                            if(hideColumn != null && $.trim(hideColumn) != "") {
                                tdHtml += "<td><input type='hidden' name='"+hideColumn+"' value='"+json[d][hideColumn]+"'/><a href='#' onclick=showMoreReport('"+fieldType+"','"+json[d][hideColumn]+"','"+linkReportId+"',this)>"+json[d][column]+"</a></td>"
                            }else{
                                tdHtml += "<td><a href='#' onclick=showMoreReport('"+fieldType+"','"+json[d][hideColumn]+"','"+linkReportId+"',this)>"+json[d][column]+"</a></td>"
                            }
                        }else{
                            if(hideColumn != null && $.trim(hideColumn) != "") {
                                tdHtml += "<td><input type='hidden' name='"+hideColumn+"' value='"+json[d][hideColumn]+"'/>"+json[d][column]+"</td>"
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

            $("#"+tableId).html(tableHtml);
        }
    })
} ;



/**
 * 点击链接显示模态框中内容
 * @param fieldType   表头类型 0:维度  1:量度
 * @param linkReportId    下钻报表ID
 * @param fieldHideCode  隐藏域内容
 */

function showMoreReport(fieldType,fieldHide,linkReportId,obj){

    //将查询条件和每行的所有维度封装为json格式
    var paramArray = new Array();

    var key = ""
    var value = "";
    //遍历点击行的所有维度的隐藏域
    //<!--遍历点击行维度条件 start -->

    $(obj).parent().parent().find("input[type='hidden']").each(function(index,elem){

        if($(elem).val() != null && $.trim($(elem).val()) != ""){

            key = $(elem).attr("name");
            value = $(elem).val();
            paramArray.push(new reportFilter(key,value,"text"));
        }

    });

    //<!-- 遍历点击行维度条件 end -->

    //遍历查询form表单下的所有不为空的查询条件
    //<!-- 遍历查询条件开始 -->

    $("form  select ").each(function(index,element){

       if($(element).val() != null && $.trim($(element).val()) != ""){
           key = $(element).attr("name");
           value = $(element).val();
           
           paramArray.push(new reportFilter(key,value,"select"));
       }

    });

    $("form  input:not(:button) ").each(function(index,element){

        if($(element).val() != null && $.trim($(element).val()) != ""){
            key = $(element).attr("name");
            value = $(element).val();
            paramArray.push(new reportFilter(key,value,"text"));
        }


    });
    //<!--遍历查询条件结束 -->

    var param = JSON.stringify(paramArray);

    if(0 == fieldType){
        //查看名片
        showDetailDesc(fieldHide,param,linkUrl,linkReportId);
    }else{
        showNumsList(fieldHide,param,linkUrl,linkReportId);
    }

}

/**
 * 动态填充 维度具体信息 模态框内容  #detailDesc 模态框ID
 *
 * @param fieldType
 * @param fieldHide
 */
function showDetailDesc(fieldHide,param,linkUrl,linkReportId){

    //清空内容
    $("#detailDesc").html();

    var tableHtml = "";
    tableHtml += '<div class="modal-dialog m-tanchu-box" role="document">';
    tableHtml += '<div class="container-fluid" style="margin-left:212px; margin-top:15px;">';
    tableHtml += '<div class="row-fluid">';
    tableHtml += '<div class="col-sm-12 ">';
    tableHtml += '<div class="panel panel-default article-bj">';
    tableHtml += '<div class="panel-heading box-shodm">商品详情   '+param+'</div>';
    tableHtml += '<div class="row wrapper form-margin">';
    tableHtml += '<div class="col-md-3">';
    tableHtml += '<div class="input-group">';
    tableHtml += '<div class="input-group-btn">';
    tableHtml += '<h5 class="h5-margin" id="o_t_status"></h5>';
    tableHtml += '</div>';
    tableHtml += '</div>';
    tableHtml += '</div>';
    tableHtml += '<div class="col-md-3">';
    tableHtml += '<div class="input-group">';
    tableHtml += '<div class="input-group-btn">';
    tableHtml += '<h5 class="h5-margin" id="o_t_code"></h5>';
    tableHtml += '</div>';
    tableHtml += '</div>';
    tableHtml += '</div>';
    tableHtml += '<div class="col-md-3">';
    tableHtml += '<div class="input-group">';
    tableHtml += '<div class="input-group-btn">';
    tableHtml += '<h5 class="h5-margin" id="o_t_counterparty"></h5>';
    tableHtml += '</div>';
    tableHtml += '</div>';
    tableHtml += '</div>';
    tableHtml += '<div class="col-md-3">';
    tableHtml += '<div class="input-group">';
    tableHtml += '<div class="input-group-btn">';
    tableHtml += '<h5 class="h5-margin" id="o_t_flow"></h5>';
    tableHtml += '</div>';
    tableHtml += '</div>';
    tableHtml += '</div>';
    tableHtml += '</div>';
    tableHtml += '<div class="table-responsive panel-table-body ">';
    tableHtml += '<table class="table table-striped table-hover " id="tb_orderDetail"></table>';
    tableHtml += '</div>';
    tableHtml += '<footer class="panel-footer text-right bg-light lter">';
    tableHtml += '<button class="btn btn-warning btn-s-xs" type="submit" data-dismiss="modal">退出</button>';
    tableHtml += '</footer>';
    tableHtml += '</div>';
    tableHtml += '</div>';
    tableHtml += '</div>';
    tableHtml += '</div>';
    tableHtml += '</div>';

    $("#detailDesc").html(tableHtml);

    $("#detailDesc").modal("show");
}

/**
 * 量度明细列表    numsListTable：存放明细列表内容的tableID
 * @param linkReportId
 * @param fieldHide
 */
function showNumsList(fieldHide,param,linkUrl,linkReportId){

    $("#numsList").html();


    var tableHtml = "";

    //清空内容
    tableHtml += '<div class="modal-dialog m-tanchu-box" role="document">';
    tableHtml += '<div class="container-fluid" style="margin-left:212px; margin-top:15px;">';
    tableHtml += '<div class="row-fluid">';
    tableHtml += '<div class="col-sm-12 ">';
    tableHtml += '<div class="panel panel-default article-bj">';
    tableHtml += '<div class="panel-heading box-shodm">订单详情</div>';

    tableHtml += '<div class="table-responsive panel-table-body ">';
    tableHtml += '<table class="table table-striped table-hover " id="tb_otherReport"></table>';
    tableHtml += '</div>';
    tableHtml += '<footer class="panel-footer text-right bg-light lter">';
    tableHtml += '<button class="btn btn-warning btn-s-xs" type="submit" data-dismiss="modal">退出</button>';
    tableHtml += '</footer>';
    tableHtml += '</div>';
    tableHtml += '</div>';
    tableHtml += '</div>';
    tableHtml += '</div>';
    tableHtml += '</div>';

    $("#numsList").html(tableHtml);

    $("#numsList").modal("show");

    //调用报表引擎生成新的动态报表
    createReport(linkUrl,param,"tb_otherReport",linkReportId)

} ;

/*$('#detailDesc').on('hidden.bs.modal', function () {
    alert('aa');
    console.log(this);
    $(this).removeData("bs.modal");
});

$('#numsList').on('hidden.bs.modal', function () {
    alert('aa');
    console.log(this);
    $(this).removeData("bs.modal");
});*/



