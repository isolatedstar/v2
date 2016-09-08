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
        <link rel="stylesheet" type="text/css" href="${ctx}/common/jqueryValidation/css/validationEngine.jquery.css" />

        <script src="${ctx}/mall/js/bootstrap.min.js"></script>
        <script src="${ctx}/mall/js/bootstrap-treeview.js"></script>

        <script src="${ctx}/mall/js/common/common.js"></script>


	   <title>会员仓库管理</title>
	</head>

	<body>

		<div class="container-fluid" style="margin-top:15px;">
            <div class="row-fluid">
                <div class="col-sm-12 ">
                    <div class="panel panel-default article-bj">

                        <div class="panel-heading box-shodm"> 会员仓库（收发货地址）</div>
                        <form id="queryPendingOrderForm">
                            <div class="row wrapper form-margin" style="margin:15px;">

                                <div class="col-md-4">
                                    <div class="input-group">
                                        <div class="input-group-btn">
                                            <h5 class="h5-margin">地址:</h5>
                                        </div>
                                        <input type="text"  name="address2" value="${address2!}" class="form-control"  id="address2">
                                    </div>
                                </div>


                                <input type="button" class="btn btn-info btn-s-md btn-default float-right cx-btn-margin" value="查询"
                                       style="height:35px;width:65px" onclick="queryMmbWarehouse();" id="queryMmbWarehouseButton"/>

                                <input type="button" class="btn btn-info btn-s-md btn-default float-right cx-btn-margin" value="添加"
                                       style="height:35px;width:65px" onclick="toCreateMmbWarehouse();" id="toCreateMmbWarehouseBtn"/>
                            </div>
                        </form>
                        <div class="table-responsive panel-table-body ">
                            <table class="table table-striped table-hover " id="tb_wareHouse"></table>
                        </div>

		        </div>
		    </div>
		</div>

        <!--添加会员仓库-->
        <form method="post" id="createMmbWarehouseForm">
            <div class="modal fade" id="createMmbWarehouseModal" role="dialog" aria-labelledby="gridSystemModalLabel" data-backdrop="static">
                <div class="modal-dialog m-tanchu-box" role="document">
                    <div class="container-fluid" style=" margin-top:15px;">
                        <div class="row-fluid">
                            <!-- col-sm-12 -->
                            <div class="col-sm-12 ">
                                <div class="panel panel-default article-bj">
                                    <div class="panel-heading box-shodm modal-draggable">
                                        添加会员仓库
                                        <button class="close" aria-hidden="true" data-dismiss="modal" type="button">×</button>
                                    </div>
                                    <div class="row wrapper form-margin">
                                        <div class="col-md-6">
                                            <div class="input-group">
                                                <div class="input-group-btn">
                                                    <h5 class="h5-margin">地址:</h5>
                                                </div>
                                                <input type="text"  class="form-control validate[required]" name="address" id="address" />
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="input-group">
                                                <div class="input-group-btn">
                                                    <h5 class="h5-margin">所属地域:</h5>
                                                </div>
                                                <input  name="areaId" id="areaId" type="hidden">
                                                <input type="text" onclick="openTreeModal(this)" readonly="readonly" class="form-control " />
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="input-group">
                                                <div class="input-group-btn">
                                                    <h5 class="h5-margin">邮编:</h5>
                                                </div>
                                                <input type="text"  class="form-control validate[custom[onlyNumberSp]]" name="zipcode" id="zipcode" />
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="input-group">
                                                <div class="input-group-btn">
                                                    <h5 class="h5-margin">联系人名称:</h5>
                                                </div>
                                                <input type="text" class="form-control validate[custom[chineseAndEnglishAndNumber]]" name="contact" id="contact" />
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="input-group">
                                                <div class="input-group-btn">
                                                    <h5 class="h5-margin">手机号码:</h5>
                                                </div>
                                                <input type="text" class="form-control validate[custom[mobile]]" name="mobilephone" id="mobilephone" />
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="input-group">
                                                <div class="input-group-btn">
                                                    <h5 class="h5-margin">电话号码:</h5>
                                                </div>
                                                <input type="text"  class="form-control validate[custom[phone]]" name="phone" id="phone" />
                                            </div>
                                        </div>

                                    </div>
                                    <footer class="panel-footer text-right bg-light lter">
                                        <button class="btn btn-warning btn-s-xs" onclick="createMmbWarehouse()" type="button">添加</button>
                                    </footer>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
		</form>

        <!--修改操作员信息-->
        <form method="post" id="editMmbWarehouseForm">
            <div class="modal fade" id="editMmbWarehouseModal" role="dialog" aria-labelledby="gridSystemModalLabel" data-backdrop="static">
                <div class="modal-dialog m-tanchu-box" role="document">
                    <div class="container-fluid" style=" margin-top:15px;">
                        <div class="row-fluid">
                            <!-- col-sm-12 -->
                            <div class="col-sm-12 ">
                                <div class="panel panel-default article-bj">
                                    <div class="panel-heading box-shodm modal-draggable">
                                        修改会员仓库
                                        <button class="close" aria-hidden="true" data-dismiss="modal" type="button">×</button>
                                    </div>
                                    <div class="row wrapper form-margin">
                                        <div class="row wrapper form-margin">
                                            <div class="col-md-6">
                                                <div class="input-group">
                                                    <div class="input-group-btn">
                                                        <h5 class="h5-margin">地址:</h5>
                                                    </div>
                                                    <input type="text"  class="form-control validate[required]" name="address1" id="address1" />
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="input-group">
                                                    <div class="input-group-btn">
                                                        <h5 class="h5-margin">所属地域:</h5>
                                                    </div>
                                                    <input name="areaId1" id="areaId1" type="hidden">
                                                    <input type="text" onclick="openTreeModal(this)"  class="form-control ]"  />
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="input-group">
                                                    <div class="input-group-btn">
                                                        <h5 class="h5-margin">邮编:</h5>
                                                    </div>
                                                    <input type="text" class="form-control validate[custom[onlyNumberSp]]" name="zipcode1" id="zipcode1" />
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="input-group">
                                                    <div class="input-group-btn">
                                                        <h5 class="h5-margin">联系人名称:</h5>
                                                    </div>
                                                    <input type="text"  class="form-control validate[custom[chineseAndEnglishAndNumber]]" name="contact1" id="contact1" />
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="input-group">
                                                    <div class="input-group-btn">
                                                        <h5 class="h5-margin">手机号码:</h5>
                                                    </div>
                                                    <input type="text"  class="form-control validate[custom[mobile]]" name="mobilephone1" id="mobilephone1" />
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="input-group">
                                                    <div class="input-group-btn">
                                                        <h5 class="h5-margin">电话号码:</h5>
                                                    </div>
                                                    <input type="text"  class="form-control validate[custom[phone]]" name="phone1" id="phone1" />
                                                </div>
                                            </div>

                                        </div>

                                        <input type="hidden" name="id1" id="id1"/>
                                        <input type="hidden" name="mmbId1" id="mmbId1"/>
                                    </div>
                                    <footer class="panel-footer text-right bg-light lter">
                                        <button class="btn btn-warning btn-s-xs" type="button" onclick="updateMmbWarehouse()" >修改</button>
                                    </footer>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

			</form>

        <!--添加区域-->
        <!--弹出层-->
        <div class="modal fade" id="addArea" role="dialog" aria-labelledby="gridSystemModalLabel">
            <div class="modal-dialog s-tanchu-box" role="document" style="margin-right:-415px; margin-top:85px;">
                <div class="panel-heading box-shodm">
                    选择地域
                    <button class="close" aria-hidden="true" data-dismiss="modal" type="button">×</button>
                </div>
                <div class="row wrapper ">
                    <div class="col-md-12 tree-bg">
                        <h4><strong>地域名称</strong></h4>
                        <hr>
                        <div id="tree"></div>
                    </div>
                </div>
            </div>
        </div>

            <script type="text/javascript">

                $(function(){

                    //关闭模态框 清空内容

                    $('#createMmbWarehouseModal').on('hide.bs.modal', function () {

                        $("#createMmbBankAccountModal input").val("");
                    });

                    $('#editMmbWarehouseModal').on('hide.bs.modal', function () {

                        $("#editMmbWarehouseModal input").val("");
                    });

                    //表单验证
                    $("#createMmbWarehouseForm").validationEngine('attach',{
                        scroll:false,
                        autoHidePrompt:true,
                        autoHideDelay:2500,
                        promptPosition : "bottomLeft"
                    });

                    //表单验证
                    $("#editMmbWarehouseForm").validationEngine('attach',{
                        scroll:false,
                        autoHidePrompt:true,
                        autoHideDelay:2500,
                        promptPosition : "bottomLeft"
                    });

                    var oTable = new tableInit();
                    oTable.Init();


                });

                var tableInit = function(){
                    var oTableInit = new Object();
                    //初始化table
                    oTableInit.Init = function(){
                        $("#tb_wareHouse").bootstrapTable({
                            url : "${ctx}/mmbWarehouse/queryMmbWareHouse.do",
                            method : "post",
                            dataType : "json",
                            classes : "table table-no-bordered",
                            contentType : "application/x-www-form-urlencoded",
                            striped : true,
                            cache : false,
                            pagination : true,
                            sortable : false,
                            sortOrder : "asc",
                            queryParams : oTableInit.queryParams, //传递参数（*）
                            sidePagination : "server",
                            pageNumber : 1,
                            pageSize : 10,
                            pageList : [10],
                            search : false,
                            strictSearch : false,
                            showColumns : false,
                            showRefresh : false,
                            showFooter: true,
                            minimumCountColumns : 2,
                            clickToSelect : false,
                            uniqueId : "id",
                            showToggle : false,
                            cardView : false,
                            detailView : false,
                            paginationPreText : "«",
                            paginationNextText : "»",
                            columns : [
                                {
                                    field : "address",
                                    title : "地址",
                                    align : "center",
                                    valign : "middle",
                                    sortable : false
                                },
                                {
                                    field : "areaDesc",
                                    title : "所属地域",
                                    align : "center",
                                    valign : "middle",
                                    sortable : false
                                },
                                {
                                    field : "zipcode",
                                    title : "邮编",
                                    align : "center",
                                    valign : "middle",
                                    sortable : false
                                },
                                {
                                    field : "contact",
                                    title : "联系人名称",
                                    align : "center",
                                    valign : "middle",
                                    sortable : false
                                },
                                {
                                    field : "mobilephone",
                                    title : "手机号码",
                                    align : "center",
                                    valign : "middle",
                                    sortable : false
                                },
                                {
                                    field : "phone",
                                    title : "电话号码",
                                    align : "center",
                                    valign : "middle",
                                    sortable : false
                                },
                                {
                                    title : "操作",
                                    align : "center",
                                    valign : "middle",
                                    sortable : false,
                                    formatter : function(value,row,index){
                                        return '<a href="javascript:void(0);" onclick="toEditMmbWareInfo(\'' + row.id + '\')">修改 </a>'+
                                                '<a href="javascript:void(0);" onclick="deleteMmbWare(\'' + row.id + '\')">删除</a>';

                                    }
                                }
                            ]


                        });
                    };

                    //得到查询的参数
                    oTableInit.queryParams = function(params) {
                        var temp = {
                            pageNo : params.offset,
                            pageSize : params.limit,
                            address2 : $("#address2").val()
                        };
                        return temp;
                    };
                    return oTableInit;
                };


                //查询
                function queryMmbWarehouse(){

                    var pageSize =  $("#tb_wareHouse").bootstrapTable("getOptions").pageSize;
                    $.ajax({
                        url : "${ctx}/mmbWarehouse/queryMmbWareHouse.do?pageNo=0&pageSize="+ pageSize,
                        type : "post",
                        dataType : "json",
                        data: {
                            address2 : $("#address2").val()
                        },
                        cache : false,
                        error : function(){
                            alert("系统异常！");
                        },
                        success : function(data){
                            $("#tb_wareHouse").bootstrapTable("load",data);
                        }

                    });

                }

                //打开添加会员仓库模态框
                function toCreateMmbWarehouse(){
                    $("#createMmbWarehouseModal input[type='text']").val("");
                    $("#createMmbWarehouseModal").modal("show");
                }

                //打开 地域树模态框
                function openTreeModal(obj){

                    $.ajax({
                        url : "${ctx}/mmbWarehouse/getTreeModal.do",
                        dataType : "json",
                        cache : false,
                        type : "POST",
                        error : function(){
                            alert("异常");
                        },
                        success : function(data){
                            if(data != "" && data.length > 0){
                                $("#tree").treeview({
                                    color :  "#428bca",
                                    enableLinks : false,
                                    data : data,
                                    showBorder : false,
                                    expandIcon: 'glyphicon glyphicon-plus',
                                    collapseIcon: 'glyphicon glyphicon-minus',
                                    nodeIcon: 'glyphicon glyphicon-send'

                                });

                                //显示地域树 模态框
                                $("#addArea").modal("show");

                                //节点点击事件 给表格中对应的位置 赋值
                                $("#tree").on("nodeSelected",function(event,data){

                                    $(obj).val(data.text);
                                    $(obj).parent().children("input").first().val(data.id);
                                    $("#addArea").modal("hide");
                                });
                            }
                        }


                    });

                }

                //添加会员仓库到数据库中
                function createMmbWarehouse(){


                    if(!$("#createMmbWarehouseForm").validationEngine("validate")){
                        return false;
                    }
                    if(!checkAddress($("#address").val())){
                        alert("地址已存在！");
                        return false;
                    }


                    $.ajax({
                        url : "${ctx}/mmbWarehouse/createMmbWarehouse.do",
                        type : "POST",
                        async : false,
                        data : $("#createMmbWarehouseForm").serialize(),
                        dataType : "json",
                        error : function(){
                            alert("异常");
                        },
                        success : function (data) {
                            if(data != null && data.successMsg != null){
                                alert(data.successMsg);
                                //隐藏模态框
                                $("#createMmbWarehouseModal").modal('hide');
                                //重新查询
                                $("#queryMmbWarehouseButton").click();
                            }else if(data !=null && data.errorMsg != null){
                                alert(data.errorMsg);
                            }

                        }
                    });
                }


                //跳转到修改会员仓库
                function toEditMmbWareInfo(id){

                    //根据ID 查询出对应的会员仓库数据 填充到对应的输入框中
                    $.ajax({
                        url : "${ctx}/mmbWarehouse/toEditMmbWarehouse.do",
                        type : "POST",
                        data : {"id" : id},
                        dataType : "json",
                        error : function(){
                            alert("异常");
                        },
                        success : function (data) {

                            if(data != null){
                                //给编辑框的各个输入框赋值
                                $("#id1").val(data.id);
                                $("#mmbId1").val(data.mmbId);
                                $("#address1").val(data.address);
                                $("#areaId1").val(data.areaId);
                                $("#zipcode1").val(data.zipcode);
                                $("#contact1").val(data.contact);
                                $("#mobilephone1").val(data.mobilephone);
                                $("#phone1").val(data.phone);
                                $("#editMmbWarehouseModal").modal("show");

                            }else{
                                alert("系统异常！");
                            }
                        }
                    });
                };


                //修改会员仓库到数据库中
                function updateMmbWarehouse(){


                    if(!$("#editMmbWarehouseForm").validationEngine("validate")){

                        return false;
                    }

                    if(!checkAddress($("#address1").val())){
                        alert("地址已存在！");
                        return false;
                    }

                    //校验会员新增信息是否正确

                    $.ajax({
                        url : "${ctx}/mmbWarehouse/updateMmbWarehouse.do",
                        type : "POST",
                        async : false,
                        data : $("#editMmbWarehouseForm").serialize(),
                        dataType : "json",
                        error : function(){
                            alert("异常");
                        },
                        success : function (data) {
                            if(data != null && data.successMsg != null){
                                alert(data.successMsg);
                                //隐藏模态框
                                $("#editMmbWarehouseModal").modal('hide');
                                //重新查询
                                queryMmbWarehouse();
                            }else if(data !=null && data.errorMsg != null){
                                alert(data.errorMsg);
                                //隐藏模态框
                            }

                        }
                    });
                }

                //删除会员仓库
                function deleteMmbWare(id){
                    $.ajax({
                        url : "${ctx}/mmbWarehouse/deleteMmbWarehouse.do",
                        type : "POST",
                        data : {"id" : id},
                        dataType : "json",
                        error : function(){
                            alert("异常");
                        },
                        success : function (data) {
                            if(data != null && data.successMsg != null){
                                alert(data.successMsg);
                                //重新查询
                                queryMmbWarehouse();
                            }else if(data !=null && data.errorMsg != null){
                                alert(data.errorMsg);
                            }

                        }
                    });

                }

                //验证地址唯一性
                function checkAddress(address) {
                    var num = 0;
                    var id = $("#id1").val();
                    $.ajax({
                        url : "${ctx}/mmbWarehouse/checkAddress.do",
                        type : "POST",
                        data : {"address" : address,"id" : id},
                        dataType : "json",
                        async : false,
                        error: function () {
                            alert("异常");
                        },
                        success: function (data) {
                           // data = eval("("+data+")");
                            num  = data;
                        }
                    });

                    return  num == 0 ? true :false ;
                }


            </script>

	</body>



</html>
