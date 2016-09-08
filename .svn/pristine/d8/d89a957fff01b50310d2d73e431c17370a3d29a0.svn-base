<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />   <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="save" content="history">
    <title>无标题文档</title>
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


    <script type="text/javascript" src="${ctx}/common/jqueryValidation/js/jquery.validationEngine-zh_CN.js"></script>
    <script type="text/javascript" src="${ctx}/common/jqueryValidation/js/jquery.validationEngine.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/common/jqueryValidation/css/validationEngine.jquery.css"/>

    <script>
        $(function () {

            $("#creatContractForm").validationEngine('attach', {
                scroll: false,
                autoHidePrompt: true,
                autoHideDelay: 2500,
                promptPosition: "bottomLeft"
            });
        });

    </script>
    <script type="text/javascript">
        $(function () {
            $(".st_tree").SimpleTree({
                click: function (a) {
                    if (!$(a).attr("hasChild"))
                        alert($(a).attr("ref"));
                }
            });
            //用户品类树
            $.ajax({
                url: '${ctx}/GoodController/getUserCategoryByMmbId.do',// 跳转到 action
                type: 'POST',
                cache: false,
                data: {
                    relaMmbId: $("#relaMmbId").val()
                },
                dataType: 'json',
                success: function (data) {
                    //alert(data.length);
                    if (data != "" && data.length > 0) {
                        $('#tree').treeview({
                            color: "#428bca",
                            enableLinks: true,
                            data: data,
                            showBorder: false,
                            expandIcon: 'glyphicon glyphicon-chevron-right',
                            collapseIcon: 'glyphicon glyphicon-chevron-down',
                            nodeIcon: 'glyphicon glyphicon-file'
                        });
                        //点击事件
                        $('#tree').on('nodeSelected', function (event, data) {
                            var goodsflag = true;
                            if (data.nodes == null || data.nodes == "") {
                                //赋值
                                $('#goodsTable').children("tbody").find("tr:not(first)").each(function (i, element) {
                                    if ($(element).children().find("[name=goods]").val() == data.id) {
                                        alert("已存在的商品，请勿重复提交！");
                                        goodsflag = false;
                                    }
                                })
                                if (goodsflag == true) {
                                    var row2 = document.getElementById("goodsTable").insertRow(goodsTable.rows.length);
                                    row2.insertCell(0).innerHTML = '<td><input type="text" hidden = "hidden "name ="goods" id="goods" value=' + data.id + ' />' + data.text + '</td>';
                                    row2.insertCell(1).innerHTML = '<td><a href="#" onclick="deleteCurrentRow(this)">删除</a></td>';
                                }
                            }
                        });
                    }
                },
                error: function () {
                    alert("异常！");
                }
            });
            $("#workerTime").val(new Date().toLocaleString());
            $("#userTime").daterangepicker(
                    //{
                    //timePicker: true,
                    //  },
                    //function(start, end, label) {
                    //console.log(start.toISOString(), end.toISOString(), label);
                    //}
            );
        });
        function deleteCurrentRow(obj) {
            var tr = obj.parentNode.parentNode;
            var rowIndex = tr.rowIndex;
            document.getElementById("goodsTable").deleteRow(rowIndex);
        }

        //添加协议
        function createCtr() {

            //验证规则
            if (!$("#creatContractForm").validationEngine("validate")) {
                return;
            }

            //商品不能为空
            var goodsNums = $("#goodsForm input[name='goods']").length;
            if (goodsNums == null || goodsNums == 0) {
                alert("请选择协议商品");
                return;
            }

            var userTime = $("#userTime").val();
            if (userTime.length != 23) {
                alert("有效时间格式不正确");
                return;
            }


            $.ajax({
                url: '${ctx}/contract/creatCTR.do',
                dataType: "JSON",
                type: "POST",
                data: "id=" + 1 + "&userTime=" + $("#userTime").val() + "&contractTitle=" + $("#contractTitle").val() + "&payType=" + $("#payType").val() + "&sendgoodsType=" + $("#sendgoodsType").val() + "&flowType=" + $("#flowType").val() + "&" + $("#goodsForm").serialize()
                + "&getmoneyName=" + $("#getmoneyCode").find("option:selected").text() + "&getmoneyCode=" + $("#getmoneyCode").find("option:selected").val()
                + "&payerName=" + $("#payerCode").find("option:selected").text() + "&payerCode=" + $("#payerCode").find("option:selected").val()
                + "&getgoodsAddress=" + $("#getgoodsAddress").find("option:selected").text() + "&sendgoodsAddress=" + $("#sendgoodsAddress").find("option:selected").text()
                + "&busType=" + "${type!}" + "&firstMmbId=" + "${mmb.id!}" + "&secondMmbId=" + "${mmb1.id!}" + "&beizhu=" + $("#beizhu").val(),
                success: function (data) {
                    window.location = "${ctx}/contract/toSubmitCtr.do";
                },
                error: function () {
                    alert("系统异常!");
                }
            })


        }
    </script>
</head>

<body>
<div class="panel panel-default"
     style="margin-top:10px; box-shadow:3px 3px 8px rgba(0,0,0,0.1); margin-right:1%; height:auto;">
    <!---------------------------------------con top  start-------------------------------------------------------------->
    <div class="con_top">
        <p>创建合作协议
            <input type="hidden" id="mmbId" value="${mmb.id!}" name="mmbId"/>
            <input type="hidden" id="relaMmbId" value="${mmb1.id!}" name="relaMmbId"/>
            <input type="hidden" value="${type!}" id="type" name="type"/>
        </p>
    </div>
    <!---------------------------------------con top  over--------------------------------------------------------------->
    <div class="A_b_3_in_title" style=" height:30px; background-color:#418bca;  padding-left:50px;">
        <p>业务类型：<#if (type!'')=='1'>采购<#elseif (type!'')=='2'>销售</#if> </p>

        <p>采购方：${mmb.mmbFname!}</p>

        <p>供货方：${mmb1.mmbFname!}</p>
    </div>
    <div class="clear"></div>
    <div style="padding-left:20px; width:60%; float:left; ">
        <div class="container-fluid" style="margin-top:15px;">
            <div class="row-fluid">
                <!-- col-sm-12 -->
                <div class="col-sm-12 ">
                    <form class="form-horizontal" role="form" id="creatContractForm">
                        <div class="col-md-6">
                            <div class="input-group">
                                <div class="input-group-btn">
                                    <h5 class="h5-margin">操作人:</h5>

                                </div>
                                <input type="text" class="form-control" value="${workerName}" readonly="readonly"
                                       id="firstname"/>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="input-group">
                                <div class="input-group-btn">
                                    <h5 class="h5-margin">操作时间:</h5>

                                </div>
                                <input type="text" class="form-control" id="workerTime" value="javascript:new Date();"
                                       readonly="readonly"/>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="input-group">
                                <div class="input-group-btn">
                                    <h5 class="h5-margin">标题:</h5>
                                </div>
                                <input type="text" class="form-control validate[required,maxSize[100]]"
                                       id="contractTitle"/>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="input-group">
                                <div class="input-group-btn">
                                    <h5 class="h5-margin">有效时间:</h5>
                                </div>
                                <input type="text" class="form-control validate[required]" readonly="readonly"
                                       id="userTime"
                                       style="behavior:url(#default#savehistory)"/>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="input-group">
                                <div class="input-group-btn">
                                    <h5 class="h5-margin">结款规则:</h5>

                                </div>
                                <select class="form-control validate[required]" id="payType" name="payType">
                                    <option value="0">每月</option>
                                    <option value="1">每季</option>
                                    <option value="2">6个月</option>
                                    <option value="3">每年</option>
                                    <option value="4">其他</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="input-group">
                                <div class="input-group-btn">
                                    <h5 class="h5-margin">发货规则:</h5>
                                </div>
                                <select class="form-control validate[required]" id="flowType" name="flowType">
                                    <option value="0">自取</option>
                                    <option value="1">免费配送</option>
                                    <option value="2">有偿配送（1%）</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="input-group">
                                <div class="input-group-btn">
                                    <h5 class="h5-margin">运输方式:</h5>

                                </div>
                                <select class="form-control validate[required]" id="sendgoodsType" name="sendgoodsType">
                                    <option value="0">行运</option>
                                    <option value="1">空运</option>
                                </select>
                            </div>
                        </div>

                    <#if (type!'')== "1">
                        <div class="col-md-6">
                            <div class="input-group">
                                <div class="input-group-btn">
                                    <h5 class="h5-margin">付款账号:</h5>
                                </div>
                                <select class="form-control validate[required]" id="payerCode">
                                    <#if bankList ??>
                                        <#list bankList as bank>
                                            <option value="${bank.accountno}">${bank.bankname}</option>
                                        </#list>
                                    </#if>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="input-group">
                                <div class="input-group-btn">
                                    <h5 class="h5-margin">收货地址:</h5>
                                </div>
                                <select class="form-control validate[required]" id="getgoodsAddress">
                                    <#if addressList ??>
                                        <#list addressList as adr>
                                            <option value="${adr.id}">${adr.address}</option>
                                        </#list>
                                    </#if>
                                </select>
                            </div>
                        </div>


                    <#elseif (type!"")== "2">
                        <div class="col-md-6">
                            <div class="input-group">
                                <div class="input-group-btn">
                                    <h5 class="h5-margin">收款账号:</h5>
                                </div>
                                <select class="form-control validate[required]" id="getmoneyCode">
                                    <#if bankList ??>
                                        <#list bankList as bank>
                                            <option value="${bank.accountno}">${bank.bankname}</option>
                                        </#list>
                                    </#if>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="input-group">
                                <div class="input-group-btn">
                                    <h5 class="h5-margin">发货地址:</h5>
                                </div>
                                <select class="form-control validate[required]" id="sendgoodsAddress">
                                    <#if addressList ??>
                                        <#list addressList as adr>
                                            <option value="${adr.id}">${adr.address}</option>
                                        </#list>
                                    </#if>
                                </select>
                            </div>
                        </div>
                    </#if>


                        <div class="col-md-12">
                            <div class="input-group">
                                <div class="input-group-btn">
                                    <h5 class="h5-margin">备注:</h5>
                                </div>

                                <textarea class="form-control" rows="3" id="beizhu" style="width:100%"></textarea>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="clear"></div>
    </div>

    <div class="A_b_3_in_right" style="width:40%;float:left;">

        <!--ztree start-->
        <div style="width:30%; height:450px; float:left; margin-right:6%;margin-top: 23px;">
            <p style="font-size: 16px;">协议商品品类列表：</p>

            <div id="tree"></div>
        </div>
        <!--ztree over-->
        <div id="goodsDvi" style="float:left;">
            <form id="goodsForm">
                <table id="goodsTable" style="behavior:url(#default#savehistory)">
                    <tbody>
                    <tr>
                        <td name="goods" id="0">已选商品列表:</td>
                        &nbsp;&nbsp;</tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
    <div class="clear"></div>
    <div style="margin-left:40%; margin-bottom:22px;">
        <button type="button" class="btn btn-primary" style=" margin-right:20px; width:100px;"
                onclick="createCtr()">保&nbsp;&nbsp;&nbsp;存
        </button>
        <a href="javaScript:history.go(-1);">
            <button type="button" class="btn btn-primary" style=" margin-right:20px; width:100px;">返回上一级
            </button>
        </a>
    </div>
</div>
</body>
</html>
