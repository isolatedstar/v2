<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />   <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

    <link href="${ctx}/mall/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/mall/css/theme.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/mall/css/bootstrap-table.css" rel="stylesheet">

    <script src="${ctx}/mall/js/jquery.js"></script>

    <script src="${ctx}/mall/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${ctx}/mall/js/bootstrap-table.js"></script>
    <script src="${ctx}/mall/js/bootstrap-table-zh-CN.js"></script>


    <script type="text/javascript" src="${ctx}/common/jqueryValidation/js/jquery.validationEngine.min.js"></script>
    <script type="text/javascript" src="${ctx}/common/jqueryValidation/js/jquery.validationEngine-zh_CN.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/common/jqueryValidation/css/validationEngine.jquery.css"/>


    <script src="${ctx}/mall/js/bootstrap.min.js"></script>

    <script src="${ctx}/mall/js/common/common.js"></script>

    <script src="${ctx}/mall/js/bootstrap-treeview.js"></script>

    <title>会员银行账号管理</title>
</head>

<body>

<div class="container-fluid" style="margin-top:15px;">
    <div class="row-fluid">
        <div class="col-sm-12 ">
            <div class="panel panel-default article-bj">

                <div class="panel-heading box-shodm"> 会员银行账号管理</div>
                <form id="queryPendingOrderForm">
                    <div class="row wrapper form-margin" style="margin:15px;">

                        <div class="col-md-4">
                            <div class="input-group">
                                <div class="input-group-btn">
                                    <h5 class="h5-margin">银行名称:</h5>
                                </div>
                                <input type="text" name="accountname2" value="${accountname2!}" class="form-control"
                                       id="accountname2">
                            </div>
                        </div>


                        <input type="button" class="btn btn-info btn-s-md btn-default float-right cx-btn-margin"
                               value="查询" style="height:35px;width:65px" onclick="queryMmbBankAccount()"
                               id="queryMmbBankAccountBtn"/>

                        <input type="button" class="btn btn-info btn-s-md btn-default float-right cx-btn-margin"
                               value="添加" style="height:35px;width:65px" onclick="toCreateBankModal()" id="toCreateMmbBankAccount"/>
                    </div>
                </form>
                <div class="table-responsive panel-table-body ">
                    <table class="table table-striped table-hover " id="tb_bankAccount"></table>
                </div>

            </div>
        </div>
    </div>

    <!--添加会员银行账号-->
    <form method="post" id="createMmbBankAccountForm">
        <div class="modal fade" id="createMmbBankAccountModal" role="dialog" aria-labelledby="gridSystemModalLabel" data-backdrop="static">
            <div class="modal-dialog m-tanchu-box" role="document">
                <div class="container-fluid" style=" margin-top:15px;">
                    <div class="row-fluid">
                        <!-- col-sm-12 -->
                        <div class="col-sm-12 ">
                            <div class="panel panel-default article-bj">
                                <div class="panel-heading box-shodm  modal-draggable">
                                    添加会员银行账号
                                    <button class="close" aria-hidden="true" data-dismiss="modal" type="button">×</button>
                                </div>
                                <div class="row wrapper form-margin">
                                    <div class="col-md-6">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                                <h5 class="h5-margin">账户号码:</h5>
                                            </div>
                                            <input type="text"
                                                   class="form-control validate[custom[onlyNumberSp,required]]"
                                                   name="accountno" id="accountno"/>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                                <h5 class="h5-margin">账户全称:</h5>
                                            </div>
                                            <input type="text"
                                                   class="form-control validate[custom[chineseAndEnglishAndNumber,required]]"
                                                   name="accountname" id="accountname"/>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                                <h5 class="h5-margin">银行名称:</h5>
                                            </div>
                                            <input type="text" class="form-control validate[required]" name="bankname"
                                                   id="bankname"/>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                                <h5 class="h5-margin">联系人名称:</h5>
                                            </div>
                                            <input type="text"
                                                   class="form-control validate[custom[chineseAndEnglishAndNumber]]"
                                                   name="contact" id="contact"/>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                                <h5 class="h5-margin">手机号码:</h5>
                                            </div>
                                            <input type="text" class="form-control validate[custom[mobile]]"
                                                   name="mobilephone" id="mobilephone"/>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                                <h5 class="h5-margin">电话号码:</h5>
                                            </div>
                                            <input type="text" class="form-control validate[custom[phone]]" name="phone"
                                                   id="phone"/>
                                        </div>
                                    </div>

                                </div>
                                <footer class="panel-footer text-right bg-light lter">
                                    <button class="btn btn-warning btn-s-xs" type="button"
                                            onclick="createMmbBankAccount()">添加
                                    </button>
                                </footer>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>

    <!--修改操作员信息-->
    <form method="post" id="editMmbBankAccountForm">
        <div class="modal fade" id="editMmbBankAccountModal" role="dialog" aria-labelledby="gridSystemModalLabel" data-backdrop="static">
            <div class="modal-dialog m-tanchu-box" role="document">
                <div class="container-fluid" style=" margin-top:15px;">
                    <div class="row-fluid">
                        <!-- col-sm-12 -->
                        <div class="col-sm-12">
                            <div class="panel panel-default article-bj">
                                <div class="panel-heading box-shodm modal-draggable">
                                    修改会员银行账号
                                    <button class="close" aria-hidden="true" data-dismiss="modal" type="button">×</button>
                                </div>
                                <div class="row wrapper form-margin">
                                    <div class="row wrapper form-margin">
                                        <div class="col-md-6">
                                            <div class="input-group">
                                                <div class="input-group-btn">
                                                    <h5 class="h5-margin">账户号码:</h5>
                                                </div>
                                                <input type="text"
                                                       class="form-control validate[custom[onlyNumberSp,required]]"
                                                       name="accountno1" id="accountno1"/>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="input-group">
                                                <div class="input-group-btn">
                                                    <h5 class="h5-margin">账户全称:</h5>
                                                </div>
                                                <input type="text"
                                                       class="form-control validate[custom[chineseAndEnglishAndNumber,required]]"
                                                       name="accountname1" id="accountname1"/>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="input-group">
                                                <div class="input-group-btn">
                                                    <h5 class="h5-margin">银行名称:</h5>
                                                </div>
                                                <input type="text" class="form-control validate[required]"
                                                       name="bankname1" id="bankname1"/>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="input-group">
                                                <div class="input-group-btn">
                                                    <h5 class="h5-margin">联系人名称:</h5>
                                                </div>
                                                <input type="text"
                                                       class="form-control validate[custom[chineseAndEnglishAndNumber]]"
                                                       name="contact1" id="contact1"/>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="input-group">
                                                <div class="input-group-btn">
                                                    <h5 class="h5-margin">手机号码:</h5>
                                                </div>
                                                <input type="text" class="form-control validate[custom[mobile]]"
                                                       name="mobilephone1" id="mobilephone1"/>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="input-group">
                                                <div class="input-group-btn">
                                                    <h5 class="h5-margin">电话号码:</h5>
                                                </div>
                                                <input type="text" class="form-control validate[custom[phone]]"
                                                       name="phone1" id="phone1"/>
                                            </div>
                                        </div>

                                    </div>

                                    <input type="hidden" name="id1" id="id1"/>
                                    <input type="hidden" name="mmbId1" id="mmbId1"/>
                                </div>
                                <footer class="panel-footer text-right bg-light lter">
                                    <button class="btn btn-warning btn-s-xs" type="button"
                                            onclick="updateMmbBankAccount()">修改
                                    </button>
                                </footer>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </form>

    <script type="text/javascript">

        $(function () {

            //关闭模态框 清空内容

            $('#createMmbBankAccountModal').on('hidden.bs.modal', function () {

                $("#createMmbBankAccountModal input").val("");
            });

            $('#editMmbBankAccountModal').on('hide.bs.modal', function () {
                $("#createMmbBankAccountModal input").val("");
            });

            //表单验证
            $("#createMmbBankAccountForm").validationEngine('attach', {
                scroll: false,
                autoHidePrompt: true,
                autoHideDelay: 2500,
                promptPosition: "bottomLeft"
            });

            //表单验证
            $("#editMmbBankAccountForm").validationEngine('attach', {
                scroll: false,
                autoHidePrompt: true,
                autoHideDelay: 2500,
                promptPosition: "bottomLeft"
            });


            var oTable = new tableInit();
            oTable.Init();


        });

        var tableInit = function () {
            var oTableInit = new Object();
            //初始化table
            oTableInit.Init = function () {
                $("#tb_bankAccount").bootstrapTable({
                    url: "${ctx}/mmbBankAccount/queryMmbBankAccount.do",
                    method: "post",
                    dataType: "json",
                    classes: "table table-no-bordered",
                    contentType: "application/x-www-form-urlencoded",
                    striped: true,
                    cache: false,
                    pagination: true,
                    sortable: false,
                    sortOrder: "asc",
                    queryParams: oTableInit.queryParams, //传递参数（*）
                    sidePagination: "server",
                    pageNumber: 1,
                    pageSize: 10,
                    pageList: [10],
                    search: false,
                    strictSearch: false,
                    showColumns: false,
                    showRefresh: false,
                    showFooter: true,
                    minimumCountColumns: 2,
                    clickToSelect: false,
                    uniqueId: "id",
                    showToggle: false,
                    cardView: false,
                    detailView: false,
                    paginationPreText: "«",
                    paginationNextText: "»",
                    columns: [
                        {
                            field: "accountno",
                            title: "账户号码",
                            align: "center",
                            valign: "middle",
                            sortable: false
                        },
                        {
                            field: "accountname",
                            title: "账户全称",
                            align: "center",
                            valign: "middle",
                            sortable: false
                        },
                        {
                            field: "bankname",
                            title: "银行名称",
                            align: "center",
                            valign: "middle",
                            sortable: false
                        },
                        {
                            field: "contact",
                            title: "联系人名称",
                            align: "center",
                            valign: "middle",
                            sortable: false
                        },
                        {
                            field: "mobilephone",
                            title: "手机号码",
                            align: "center",
                            valign: "middle",
                            sortable: false
                        },
                        {
                            field: "phone",
                            title: "电话号码",
                            align: "center",
                            valign: "middle",
                            sortable: false
                        },
                        {
                            title: "操作",
                            align: "center",
                            valign: "middle",
                            sortable: false,
                            formatter: function (value, row, index) {
                                return '<a href="javascript:void(0);" onclick="toEditMmbBankAccountInfo(\'' + row.id + '\')">修改 </a>' +
                                        '<a href="javascript:void(0);" onclick="deleteMmbBankAccount(\'' + row.id + '\')">删除</a>';

                            }
                        }
                    ]


                });
            };

            //得到查询的参数
            oTableInit.queryParams = function (params) {
                var temp = {
                    pageNo: params.offset,
                    pageSize: params.limit,
                    accountname2: $("#accountname2").val()
                };
                return temp;
            };
            return oTableInit;
        };


        //查询
        function queryMmbBankAccount() {

            var pageSize = $("#tb_bankAccount").bootstrapTable("getOptions").pageSize;

            $.ajax({
                url: "${ctx}/mmbBankAccount/queryMmbBankAccount.do?pageNo=0&pageSize=" + pageSize,
                type: "post",
                dataType: "json",
                data: {
                    accountname2: $("#accountname2").val()
                },
                cache: false,
                error: function () {
                    alert("系统异常！");
                },
                success: function (data) {
                    $("#tb_bankAccount").bootstrapTable("load", data);
                }
            });

        }


        //打开添加会员银行账号模态框
        function toCreateBankModal() {
            /* 完成拖拽 */

            $("#createMmbBankAccountModal").modal("show");

        }

        //添加会员银行账号到数据库中
        function createMmbBankAccount() {

            if (!$("#createMmbBankAccountForm").validationEngine("validate")) {
                return;
            }

            if(!checkAccountNo($("#accountno").val())){
                alert("银行号码已存在！");
                return false;
            }

            $.ajax({
                url: "${ctx}/mmbBankAccount/createMmbBankAccount.do",
                type: "POST",
                data: $("#createMmbBankAccountForm").serialize(),
                dataType: "json",
                error: function () {
                    alert("异常");
                },
                success: function (data) {
                    if (data != null && data.successMsg != null) {
                        alert(data.successMsg);
                        //隐藏模态框
                        $("#createMmbBankAccountModal").modal('hide');
                        //重新查询
                        queryMmbBankAccount();

                    } else if (data != null && data.errorMsg != null) {
                        alert(data.errorMsg);
                    }

                }
            });
        }


        //跳转到修改会员银行账号
        function toEditMmbBankAccountInfo(id) {

            //根据ID 查询出对应的会员银行账号数据 填充到对应的输入框中
            $.ajax({
                url: "${ctx}/mmbBankAccount/toEditMmbBankAccount.do",
                type: "POST",
                data: {"id": id},
                dataType: "json",
                error: function () {
                    alert("异常");
                },
                success: function (data) {
                    if (data != null) {
                        //给编辑框的各个输入框赋值
                        $("#id1").val(data.id);
                        $("#mmbId1").val(data.mmbId);
                        $("#accountno1").val(data.accountno);
                        $("#accountname1").val(data.accountname);
                        $("#bankname1").val(data.bankname);
                        $("#contact1").val(data.contact);
                        $("#mobilephone1").val(data.mobilephone);
                        $("#phone1").val(data.phone);
                        $("#editMmbBankAccountModal").modal("show");

                    } else {
                        alert("系统异常！");
                    }
                }
            });
        }
        ;


        //修改会员银行账号到数据库中
        function updateMmbBankAccount() {

            //校验会员新增信息是否正确
            if (!$("#editMmbBankAccountForm").validationEngine("validate")) {
                return false;
            }

            if(!checkAccountNo($("#accountno1").val())){
                alert("银行号码已存在！");
                return false;
            }

            $.ajax({
                url: "${ctx}/mmbBankAccount/updateBankAccount.do",
                type: "POST",
                data: $("#editMmbBankAccountForm").serialize(),
                dataType: "json",
                error: function () {
                    alert("异常");
                },
                success: function (data) {
                    if (data != null && data.successMsg != null) {
                        alert(data.successMsg);
                        //隐藏模态框
                        $("#editMmbBankAccountModal").modal('hide');
                        //重新查询
                        queryMmbBankAccount();
                    } else if (data != null && data.errorMsg != null) {
                        alert(data.errorMsg);
                        //隐藏模态框
                    }

                }
            });

        }

        //删除会员银行账号
        function deleteMmbBankAccount(id) {
            $.ajax({
                url: "${ctx}/mmbBankAccount/deleteMmbBankAccount.do",
                type: "POST",
                data: {"id" : id},
                dataType: "json",
                error: function () {
                    alert("异常");
                },
                success: function (data) {
                    if (data != null && data.successMsg != null) {
                        alert(data.successMsg);
                        //重新查询
                        queryMmbBankAccount();
                    } else if (data != null && data.errorMsg != null) {
                        alert(data.errorMsg);
                    }

                }
            });

        }

        //验证银行账号唯一性
        function checkAccountNo(accountno) {
            var num = 0 ;
            var id = $("#id1").val();
            $.ajax({
                url: "${ctx}/mmbBankAccount/checkAccountNo.do",
                type: "POST",
                data: {"accountno" : accountno,"id" : id},
                dataType: "json",
                async : false,
                error: function () {
                    alert("异常");
                },
                success: function (data) {
                     num  = data;
                }
            });

            return num == 0 ? true :false ;
        }

    </script>

</body>


</html>
