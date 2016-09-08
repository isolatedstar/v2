<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />   <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${ctx}/mall/css/bootstrap.min.css">
    <link href="${ctx}/mall/css/theme.css" rel="stylesheet">
    <script src="${ctx}/mall/js/jquery.js"></script>
    <script src="${ctx}/mall/js/jquery.min.js"></script>
    <script src="${ctx}/mall/js/bootstrap.min.js"></script>
    <script src="${ctx}/mall/js/bootstrap-treeview.js"></script>
    <script src="${ctx}/mall/js/SimpleTree.js" type="text/javascript"></script>
    <link href="${ctx}/mall/css/SimpleTree.css" rel="stylesheet" type="text/css">
    <!-- 创建订单导入开始 -->
    <link href="${Request.basePath}/mall/css/bootstrap-table.css" rel="stylesheet">
    <link href="${Request.basePath}/mall/css/bootstrap-datetimepicker.css" rel="stylesheet">
    <script src="${Request.basePath}/mall/js/bootstrap.min.js"></script>
    <script src="${Request.basePath}/mall/js/bootstrap-table.js"></script>
    <script src="${Request.basePath}/mall/js/bootstrap-table-zh-CN.js"></script>
    <script src="${Request.basePath}/mall/js/moment-with-locales.js"></script>
    <script src="${Request.basePath}/mall/js/bootstrap-datetimepicker.js"></script>
    <script src="${Request.basePath}/mall/js/serializeJson.js"></script>
    <script src="${Request.basePath}/mall/js/createOrder.js"></script>
    <script type="text/javascript" src="${ctx}/common/jqueryValidation/js/jquery.validationEngine-zh_CN.js"></script>
    <script type="text/javascript" src="${ctx}/common/jqueryValidation/js/jquery.validationEngine.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/common/jqueryValidation/cssalidationEngine.jquery.css"/>
    <!-- 创建订单导入结束 -->
    <script language="javascript">
        var changedata = null;
        $(function () {
            //用户品类树

            $.ajax({
                        url: '${ctx}/contract/getTree.do',// 跳转到 action
                        type: 'POST',
                        cache: false,
                        data: {
                            relaMmbId: $("#relaMmbId").val()
                        },
                        dataType: 'json',
                        success: function (data) {
                            //alert(data.length);
                            alert(data);
                            if (data != "" && data.length > 0) {
                                $('#tree').treeview({
                                    color: "#428bca",
                                    enableLinks: true,
                                    data: data,
                                    showBorder: false,；
					  expandIcon: 'glyphicon glyphicon-chevron-right',
                              collapseIcon
                            :
                                'glyphicon glyphicon-chevron-down',
                                        nodeIcon
                            :
                                'glyphicon glyphicon-file'
                            }
                            )
                            ;
                            alert("马上出现！");
                            $("#contractKS").attr("hidden", "");
                            //点击事件
                            $('#tree').on('nodeSelected', function (event, data) {
                                /**var goodsflag = true;
                                 if(data.nodes==null||data.nodes==""){
							//赋值
							//alert(data.id);
							
							$('#goodsTable').children("tbody").find("tr:not(first)").each(function(i,element){
							//alert($(element).children().find("[name=goods]").val(	));
							//alert($(element).children().find("[name=goods]").val() == data.id);
								if($(element).children().find("[name=goods]").val() == data.id){
								alert("已存在的商品，请勿重复提交！");
								goodsflag = false;
								}
							})
							if(goodsflag == true){
							var row2 = document.getElementById("goodsTable").insertRow(goodsTable.rows.length);
							row2.insertCell(0).innerHTML='<td><input type="text" hidden = "hidden "name ="goods" id="goods" value='+data.id+' />'+data.text+'</td>';
							row2.insertCell(1).innerHTML='<td><a href="#" onclick="deleteCurrentRow(this)">删除</a></td>';
							}
						}*/
                                $("#contractKS").attr("hidden", "");
                                $.ajax({
                                    url: '${ctx}/contract/getYourName.do',
                                    dataType: "JSON",
                                    type: "POST",
                                    data: {"id": data.firstMmbId},
                                    success: function (data) {
                                        alert(data);
                                        $("#xieyiduifang").attr("value", data);
                                    }
                                })
                                $("#fahuoguize").attr("value", data.flow_type_);
                                $("#yunshufangshi").attr("value", data.sendgoods_type_);
                                $("#meiriyijie").attr("value", data.pay_type_);
                                $("#beizhu").attr("value", data.beizhu);
                                //用户品类树
                                changedata = data;
                                $.ajax({
                                    url: '${ctx}/GoodController/getUserMtGoods.do',// 跳转到 action
                                    type: 'POST',
                                    cache: false,
                                    data: {
                                        relaMmbId: data.firstMmbId
                                    },
                                    dataType: 'json',
                                    success: function (data) {
                                        if (data != "" && data.length > 0) {
                                            $('#goodsTree').treeview({
                                                color: "#428bca",
                                                enableLinks: true,
                                                data: data,
                                                showBorder: false,
                                                expandIcon: 'glyphicon glyphicon-chevron-right',
                                                collapseIcon: 'glyphicon glyphicon-chevron-down',
                                                nodeIcon: 'glyphicon glyphicon-file'
                                            });
                                            //点击事件
                                            $('#goodsTree').on('nodeSelected', function (event, data) {
                                                var goodsflag = true;
                                                if (data.nodes == null || data.nodes == "") {
                                                    //赋值
                                                    $('#goodsTable').children("tbody").find("tr:not(first)").each(function (i, element) {
                                                        if ($(element).children().find("[name=goodss]").val() == data.id) {
                                                            alert("已存在的商品，请勿重复提交！");
                                                            goodsflag = false;
                                                        }
                                                    })
                                                    if (goodsflag == true) {
                                                        var row2 = document.getElementById("goodsTable").insertRow(goodsTable.rows.length);
                                                        row2.insertCell(0).innerHTML = '<td><input type="text" hidden = "hidden "name ="goodss" id="goodss" value=' + data.id + ' />' + data.text + '</td>';
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

                            });
                        }
                    },
                    error
            :
            function () {
                alert("异常！");
            }
        });
        })
        ;

        function deleteCurrentRow(obj) {
            var tr = obj.parentNode.parentNode;
            var rowIndex = tr.rowIndex;
            document.getElementById("goodsTable").deleteRow(rowIndex);
        }

    </script>
    <title>合作协议</title>
</head>

<body>
<input type="text" id="changedata" hidden="hidden" readonly="readonly">

<div class="container-fluid" style="margin-top:15px;">
    <div class="row-fluid">
        <!-- col-sm-12 -->
        <div class="panel panel-default article-bj ">
            <div class="panel-heading box-shodm">
                合作协议
            </div>
            <div class="row wrapper">
                <div class="col-md-4 tree-bg">
                    <h4><strong>协议名称</strong></h4>
                    <hr>
                    <div id="tree"></div>
                </div>

                <div class="col-md-8" id="contractKS" hidden="hidden">
                    <div class="container-fluid ae3_right_margin">
                        <div class="row-fluid">
                            <div class="panel panel-primary ">
                                <div class="panel-heading">
                                    <h3 class="panel-title">协议内容</h3>
                                </div>
                                <div class="row wrapper form-margin">
                                    <div class="col-md-6">
                                        <div class="input-group">

                                            <div class="input-group-btn">
                                                <h5>协议对方：<input type="text" id="xieyiduifang" readonly="readonly"></h5>
                                                <!--   <button class="btn" type="button"></button> -->
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                                <h5>发货规则：<input type="text" id="fahuoguize" readonly="readonly"></h5>
                                                <!--   <button class="btn" type="button"></button> -->
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row wrapper form-margin">
                                    <div class="col-md-6">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                                <h5>运输方式：<input type="text" id="yunshufangshi" readonly="readonly"></h5>
                                                <!--   <button class="btn" type="button"></button> -->
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                                <h5>结款规则：<input type="text" id="meiriyijie" readonly="readonly"></h5>
                                                <!--   <button class="btn" type="button"></button> -->
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row wrapper form-margin">
                                    <div class="col-md-8">
                                        <div class="input-group">
                                            <h5>备注:</h5>
                                        </div>
                                         <textarea rows="3" cols="93" id="beizhu">
                                           
                                         </textarea>
                                    </div>
                                </div>
                                <div class="row wrapper " id="products">
                                    <div class="col-md-4 tree-bg">
                                        <div id="goodsTree"></div>
                                    </div>

                                    <form id="goodsForm">
                                        <table id="goodsTable" style="behavior:url(#default#savehistory)">

                                            <tbody>
                                            <tr>
                                                <td name="goodss" id="0">已选商品列表:</td>
                                                <td>操作:</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </form>
                                </div>
                                <div class="row wrapper form-margin text-right">
                                    <div class="col-md-12">
                                        <button class="btn btn-warning btn-s-xs" type="submit"
                                                onclick="showCreateOrder();" data-dismiss="modal"
                                                style="margin-right:4%;">
                                            下订单
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        var defaultData1 = [
            {
                text: '北京大学',
                href: '#parent1',
                tags: ['4'],
                nodes: [
                    {
                        text: '水果协议',
                        href: '#child1',
                        tags: ['2'],
                        nodes: [
                            {
                                text: '2015年水果协议',
                                href: '#grandchild1',
                                tags: ['0']
                            },
                            {
                                text: '2016年水果协议',
                                href: '#grandchild2',
                                tags: ['0']
                            }
                        ]
                    },
                    {
                        text: '副食品协',
                        href: '#child2',
                        tags: ['0']
                    }
                ]
            },
            {
                text: '北京交通大学',
                href: '#parent2',
                tags: ['0']
            },
            {
                text: '北京邮电大学',
                href: '#parent3',
                tags: ['0']
            },
            {
                text: '天津绿源',
                href: '#parent4',
                tags: ['0']
            },
            {
                text: '贵荣商贸',
                href: '#parent5',
                tags: ['0']
            }
        ];

        var goodsData1 = [
            {
                text: '面类',
                href: '#parent1',
                tags: ['2'],
                nodes: [
                    {
                        text: '五得利面粉',
                        href: '#grandchild1',
                        tags: ['0']
                    },
                    {
                        text: '北大荒高粱面',
                        href: '#grandchild2',
                        tags: ['0']
                    }
                ]

            },
            {
                text: '蔬菜',
                href: '#parent2',
                tags: ['0'],
                nodes: [
                    {
                        text: '有机菠菜',
                        href: '#grandchild1',
                        tags: ['0']
                    },
                    {
                        text: '有机豆角',
                        href: '#grandchild2',
                        tags: ['0']
                    },
                    {
                        text: '胡萝卜',
                        href: '#grandchild2',
                        tags: ['0']
                    },
                    {
                        text: '土豆',
                        href: '#grandchild2',
                        tags: ['0']
                    }
                ]
            }
        ];

        $('#tree').treeview({
            color: "#428bca",
            enableLinks: true,
            data: defaultData,
            showBorder: false,
            expandIcon: 'glyphicon glyphicon-chevron-right',
            collapseIcon: 'glyphicon glyphicon-chevron-down',
            nodeIcon: 'glyphicon glyphicon-file'

        });

        $('#goodsTree').treeview({
            color: "#428bca",
            enableLinks: true,
            data: goodsData,
            showBorder: false,
            expandIcon: 'glyphicon glyphicon-chevron-right',
            collapseIcon: 'glyphicon glyphicon-chevron-down',
            nodeIcon: 'glyphicon glyphicon-shopping-cart'

        });

    });
</script>
<!--    订单信息表 -->
<div class="modal fade" id="changeChar" role="dialog" aria-labelledby="gridSystemModalLabel"></div>
<script type="text/javascript">
    /*----------------------------------------------------创建订单js开始----------------------------------------------------------*/
    var createOrder;
    $(function () {
        createOrder = new createOrder("changeChar", '<%=memberId%>', '${Request.basePath}', "");
    });
    var orderData = null;
    //处理页面业务逻辑，页面自己写
    function showCreateOrder() {
        var dataaa = null;
        var orderData = null;
        var dataa = changedata;
        //alert(dataa);
        //alert($("#goodsForm").serialize());
        $.ajax({
            url: '${ctx}/contract/getGoodsData.do',
            dataType: "JSON",
            type: "POST",
            data: $("#goodsForm").serialize() + "&payBankId=" + dataa.payerCode + "&sellerId=" + dataa.getmoneyCode + "&firstMmbId=" + dataa.firstMmbId + "&secondMmbId=" + dataa.secondMmbId,
            success: function (data) {
                //data=eval("("+data+")");
                //alert(data);
                orderData = data;
                //alert(orderData.mm);
                //alert(orderData.list);
                //订单详细数据格式参考
                dataaa = {
                    "buyersAddressList": orderData.mm2,
                    "sellersAddressList": orderData.mm3,
                    "buyersAccountList": orderData.mm,
                    "sellersAccountList": orderData.mm1,
                    "total": data.total,
                    "data": orderData.list,
                    "ordertitle": {
                        "buyersId": data.secondMmbId1.secondMmbId,
                        "buyersName": data.firstMmbId1.mmbFname,
                        "sellersId": data.firstMmbId1.firstMmbId,
                        "sellersName": data.secondMmbId1.mmbFname,
                        "totalMoney": 0,
                        "payBank": data.MtAccountManangerpayBankId.openManager,
                        "payAccount": data.MtAccountManangerpayBankId.payerCode,
                        "getBank": data.MtAccountManangersellerId.openManager,
                        "getAccount": data.MtAccountManangersellerId.payerCode
                    },
                };
                alert("数据采集完毕");
                createOrder.initOrder(dataaa);
                $("#changeChar").modal("show");
            }
        })
    }
    /*----------------------------------------------------创建订单js结束----------------------------------------------------------*/
</script>
</body>
</html>
