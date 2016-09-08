<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />   <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
   <meta charset="UTF-8">
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



   <title>业务操作员管理</title>
   <script type="text/javascript">
   	<#--  查询用户拥有的角色  -->
   		$(function(){

   			//表单验证
   			$("#saveUserInfo").validationEngine('attach',{
				scroll:false,
				autoHidePrompt:true,
				autoHideDelay:2500,
				promptPosition : "bottomLeft"
			});

            //表单验证
            $("#editUserInfoTab").validationEngine('attach',{
                scroll:false,
                autoHidePrompt:true,
                autoHideDelay:2500,
                promptPosition : "bottomLeft"
            });

            $('#addOperator').on('hidden.bs.modal', function () {
                $(this).removeData("bs.modal");
			});

            $('#editUserInfo').on('hidden.bs.modal', function () {
                $(this).removeData("bs.modal");
            });



            $('#add').click(function(){
				$.ajax({
					url:'${ctx}/muserController/toAddUserPage.do',
					dataType:'JSON',
					type:'POST',
					success:function(data){

						data = eval(data);
						$("#roles_select").empty();
						for(var i=0;i<data.length;i++){
					    	$("#roles_select").append("<div class='col-md-6'><div class='checkbox'><label><input type='checkbox' name='roles' value='"+data[i].roleId+"'>"+data[i].roleName+"</label></div></div>");
					   	}
						$("#addOperator").modal("show");
					}
				});	
			});

			//判断密码的强度
//			$("#password1").keyup(function(e) {
//				var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
//				var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
//				var enoughRegex = new RegExp("(?=.{6,}).*", "g");
//				if (strongRegex.test($(this).val())) {
//					$("#passstrength").css("color","green");
//				    $('#passstrength').html('很安全!');
//				} else if (mediumRegex.test($(this).val())) {
//					$("#passstrength").css("color","yellow");
//				    $('#passstrength').html('安全一般!');
//				} else {
//					$("#passstrength").css("color","red");
//				    $('#passstrength').html('密码很弱!');
//				}
//				return true;
//			});

            var oTable = new tableInit();
            oTable.Init();
		});


    var tableInit = function () {
        var oTableInit = new Object();
        //初始化table
        oTableInit.Init = function () {
            $("#tb_userManage").bootstrapTable({
                url: "${ctx}/muserController/queryUser.do",
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
                        field: "account",
                        title: "账号",
                        align: "center",
                        valign: "middle",
                        sortable: false
                    },
                    {
                        field: "roleName",
                        title: "角色",
                        align: "center",
                        valign: "middle",
                        sortable: false
                    },
                    {
                        field: "name",
                        title: "名称",
                        align: "center",
                        valign: "middle",
                        sortable: false
                    },
                    {
                        field: "email",
                        title: "邮箱",
                        align: "center",
                        valign: "middle",
                        sortable: false
                    },
                    {
                        field: "telephone",
                        title: "电话",
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
							var enable ;
							if(row.state==1){
                                enable = '<a href="#" onclick="editUserState(\'' + row.id + '\');">停用</a>';
							}else{
                                enable = '<a href="#" onclick="editUserState(\'' + row.id + '\');">启用</a>';
							}
                            return  '<a href="javascript:void(0);" onclick="editUserInfo(\'' + row.id + '\')">修改 </a>' + enable;

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
            };
            return temp;
        };

        return oTableInit;
    };
		
		//校验密码是否合格
		/*function checkOne() {
			var enoughRegex = new RegExp("(?=.{6,}).*", "g");
			if (false == enoughRegex.test($("#password1").val())) {
				$("#passstrength").css("color","red");
				$('#passstrength').html('请输入不小于6位的密码！');
				$('#flag1').val('1');
			}else if($("#password1").val().length > 16){
                $("#passstrength").css("color","red");
                $('#passstrength').html('请输入小于16位的密码！');
                $('#flag1').val('1');
			}else{
				$('#flag1').val('0');
			}
		}
		
		//校验密码是否一致
		function checkAgain(){
			var password1 = $("#password1").val();
			var password = $("#password").val();
			if (password1 != password) {
				$("#passagain").css("color","red");
			    $('#passagain').html('两次密码输入不一致！');
			    $('#passagain').show();
				$('#flag2').val('1');
			}else{
				$('#flag2').val('0');
				$('#passagain').hide();
			}
		}*/
   		
   		//新增操作员信息
	   	function checkInfo(){
	   		if(!$("#saveUserInfo").validationEngine("validate")){
	   			return ;
	   		}
			if(!checkIsUnique($("#account").val())){
				alert("管理员账号已存在！");
				return ;
			}

			if($("input[name='roles']:checked").length == 0){
				alert("请至少选择一个角色！");
                return ;
			}

	   		var password1 = $("#password1").val();
			var flag1=$('#flag1').val();
			var flag2=$('#flag2').val();
			if(flag1==0&&flag2==0&&password1!=""){
				$.ajax({
					url:'${ctx}/muserController/createUser.do',
					type: 'POST',
	                dataType: 'json',
					data:$('#saveUserInfo').serialize(),
					success:function(data){
					   	data = eval(data);
					   	//如果成功
					   	if(data){
					   	 	//隐藏模态框
					   	 	$("#addOperator").modal('hide');
					   	 	//重新查询
					   	 	window.location.href = "${ctx}/muserController/toQueryUser.do";
					   	 }
					   	 //如果失败
					   	 else {
					   	 	alert("添加失败,请重新录入!");
					   	 }
					}
				});
			}else{
				alert("请录入正确密码！");
			}
	   		
		}
		
		//修改信息
		function editInfo(){

            if(!$("#editUserInfoTab").validationEngine("validate")){
                return ;
            }

            if(!checkIsUnique($("#account1").val())){
                alert("管理员账号已存在！");
                return ;
            }
            if($("input[name='roles1']:checked").length == 0){
                alert("请至少选择一个角色！");
                return ;
            }

			$.ajax({
				url:'${ctx}/muserController/editUser.do',
				type: 'POST',
                dataType: 'json',
				data:$('#editUserInfoTab').serialize(),
				success:function(data){
				   	 data = eval(data);
				   	 //如果成功
				   	 if(data){
				   	 	//隐藏模态框
				   	 	$("#editUserInfo").modal('hide');
				   	 	//重新查询
				   	 	window.location.href = "${ctx}/muserController/toQueryUser.do";
				   	 }
				   	 //如果失败
				   	 else {
				   	 	alert("修改失败!");
				   	 }
				},
				error:function(){
					alert("异常");
				}
			});
		}
		
		//关闭面板
	   	function checkPsd(){
	   		//校验密码是否是否满足要求，前后两次录入是否一致
            if(!$("#saveUserInfo").validationEngine("validate")){
                return ;
            }

	   		$("#setPassword").modal('hide');
		}
		
		//修改密码
	   	function checkPsd1(){

            if(!$("#editUserInfoTab").validationEngine("validate")){
                return ;
            }

	   		$("#editPassword").modal('hide');
		}
		
		//显示密码输入面板
		function showPsd(){
	   	//未进行数据校验
	   		$("#setPassword").modal('show');
		}

		//显示输入原始密码面板
		function showPsd1(){
			//未进行数据校验
			$("#oldPasswordModal").modal('show');
		}


		//验证输入密码是否是原始密码
		function  checkOldPassword(){

            var oldPassword = $("#oldPassword").val();
			$.ajax({
                url : '${ctx}/muserController/checkOldPassword.do',
                dataType :'JSON',
                type : 'POST',
                data : {"oldPassword":oldPassword,"account":$("#account1").val(),"password":$("#userPassword").val()},
				error : function(){
					alert("系统异常！");
				},
				success : function(data){
                    if(data != null && data.flag == "success"){
                        $("#oldPasswordModal").modal('hide');
                        $("#editPassword").modal('show');
                    }else{
                        alert("请输入正确的原始密码！");
                    }
				}


			});

		}

		//修改操作员信息
		function editUserInfo(userId){
			$.ajax({
					url:'${ctx}/muserController/toEditUserPage.do',
					dataType:'JSON',
					type:'POST',
					data:{"userId":userId},
					success:function(data1){
						 data1 = eval(data1);
						 if(data1!=null){
						 	//加载模板
							$.ajax({
								url:'${ctx}/muserController/toAddUserPage.do',
								dataType:'JSON',
								type:'POST',
								data:{"mmbId":'${mmbId!}'},
								success:function(result){
									$("#roles_select").empty();
									if(result!=""&&parseInt(result.length)>0){
										if(data1.rolesInfo!=""&&data1.rolesInfo.length>0){
											$("#roles_edit").empty();
								   	 		for(var j=0;j<result.length;j++){
								   	 			flag=0;
									   	 		for(var i=0;i<data1.rolesInfo.length;i++){
								   	 				if(result[j].roleId==data1.rolesInfo[i].roleId){
								   	 					$("#roles_edit").append("<div class='col-md-6'><div class='checkbox'><label><input type='checkbox' name='roles1' value='"+result[j].roleId+"' checked='true'>"+result[j].roleName+"</label></div></div>");
								   	 					flag=1;
								   	 				}
											    }
											    if(flag!=1){
											    	$("#roles_edit").append("<div class='col-md-6'><div class='checkbox'><label><input type='checkbox' name='roles1' value='"+result[j].roleId+"'>"+result[j].roleName+"</label></div></div>");
											    }
								   	 		}
							   	 		}else{
							   	 			alert("没有选择的角色");
							   	 		}			
							   	 	}else{
							   	 		alert("会员没有业务类型");
							   	 	}
								}
							});
							//数据填充
							$("#account1").val(data1.userInfo.account);
							$("#userPassword").val(data1.userInfo.password);
							$("#name1").val(data1.userInfo.name);
							$("#telephone1").val(data1.userInfo.telephone);
							$("#email1").val(data1.userInfo.email);
							$("#id1").val(userId);
						 }
						 $("#editOperator").modal('show');
					}
				});	
		}
		
		//修改操作员状态
		function editUserState(id,state){
			if(id!=""){
			//暂时省略校验部分，直接提交
				$.ajax({
					url:'${ctx}/muserController/editStateOfUser.do',
					type: "POST",
					dataType: "json",
					data:{"id":id,"state":state},
					success:function(data){
						data = eval(data);
						//如果成功
						if(data){
							alert("修改操作员状态成功！");
							//重新查询
							window.location.href = "${ctx}/muserController/toQueryUser.do";
						}
						//如果失败
						else{
							alert("修改操作员状态失败！");
						}
					}
				});
			}
		}

    //编辑页面需要将自己的ID 传入后台，不能和自己比较
    function checkIsUnique(account){
		var checkNum = 0;
		var id = $("#id1").val();
		$.ajax({
			url : '${ctx}/muserController/getNumByAccount.do',
			type : "POST",
			async : false,
			dataType : "json",
			data: {"account":account,"id":id},
			error : function(){
				alert("系统异常！");
			},
			success : function(data){
				checkNum= data;
			}
		});

        return checkNum == 0 ? true : false;
    }

   </script>
</head>

<body>
<div class="container-fluid" style="margin-top:15px;">
    <div class="row-fluid">
        <!-- col-sm-12 -->
        <div class="col-sm-12 ">
        	<div class="panel panel-default article-bj">
                <div class="panel-heading box-shodm">
                	业务操作员管理
                </div>
			<#-- <div class="table-responsive panel-table-body ">
                 <table class="table table-striped table-hover ">
                         <thead>
                             <tr>
                                 <th>账号</th>
                                 <th>角色</th>
                                 <th>名称</th>
                                 <th>邮箱</th>
                                 <th>电话</th>
                                 <th>操作</th>
                             </tr>
                         </thead>
                         <tbody>
                             <#if page.data ??>
                                 <#list page.data as userBiz>
                                     <tr>
                                         <td>${userBiz.account!}</td>
                                         <td>${userBiz.roleName!}</td>
                                         <td>${userBiz.name!}</td>
                                         <td>${userBiz.email!}</td>
                                         <td>${userBiz.telephone!}</td>
                                         <td>
                                             <a href="javascript:void(0);" onclick="editUserInfo('${userBiz.id}')">修改</a>
                                             <#if userBiz.state==1>
                                                 <a href="#" onclick="editUserState('${userBiz.id!}',0);">停用</a>
                                             <#else>
                                                 <a href="#" onclick="editUserState('${userBiz.id!}',1);">启用</a>
                                             </#if>
                                         </td>
                                     </tr>
                                 </#list>
                             </#if>
                         </tbody>
                 </table>

             </div>-->
                <div class="table-responsive panel-table-body ">
                    <table class="table table-striped table-hover " id="tb_userManage"></table>
                </div>
                <footer class="panel-footer text-right bg-light lter">
                    	<input id="add" class="btn btn-warning btn-s-xs" type="button" value="添加"/>
                </footer>
       		 </div>
        </div>
    </div> 
</div>

<!--添加操作员-->
<form method="post" id="saveUserInfo">
<div class="modal fade" id="addOperator" role="dialog" aria-labelledby="gridSystemModalLabel" data-backdrop="static">
	<div class="modal-dialog m-tanchu-box" role="document"> 
        <div class="container-fluid" style="margin-left:212px; margin-top:15px;">
            <div class="row-fluid">
                <!-- col-sm-12 -->
                <div class="col-sm-12 ">
                    <div class="panel panel-default article-bj">
                        <div class="panel-heading box-shodm modal-draggable">
                      		 添加操作员
                            <button class="close" aria-hidden="true" data-dismiss="modal" type="button">×</button>
                        </div>
                        	<div class="row wrapper form-margin">
                                <div class="col-md-6">
                                    <div class="input-group">
                                        <div class="input-group-btn">
                                        	<h5 class="h5-margin">账号:</h5>
                                        </div>
                                        <input type="text" class="form-control validate[custom[login,required,maxSize[30]]]" name="account" id="account" placeholder="用户账号"/>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="input-group">
                                        <div class="input-group-btn">
                                          <h5 class="h5-margin">名称:</h5>
                                         </div>
                                         <input type="text" placeholder="用户名称" class="form-control validate[custom[chineseAndEnglishAndNumber,required],maxSize[50]]" name="name" id="name" />
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="input-group">
                                        <div class="input-group-btn">
                                          <h5 class="h5-margin">电话:</h5>
                                         </div>
                                         <input type="text" placeholder="手机号码" class="form-control validate[custom[mobile]]" name="telephone" id="telephone" />
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="input-group">
                                        <div class="input-group-btn">
                                          <h5 class="h5-margin">邮箱:</h5>
                                         </div>
                                         <input type="text" placeholder="邮箱" class="form-control validate[custom[email,maxSize[100]]]" name="email" id="email" />
                                    </div>
                                </div>
                               <#-- <div class="col-md-6">
		        					<div class="input-group">
		       							 <div class="input-group-btn">
		       							 	<h5 class="h5-margin">所属会员:</h5>
		       							 </div>
		       							 <select name="mmbId" id="mmbId" tabindex="-1" class="form-control">
			                                 <option value="1">当前会员</option>
			                             <select>
		        					</div>
		        				</div>-->
                             </div>
                             <div class="row wrapper form-margin ca1_check_bg" id="roles_select">
							</div> 
                            <footer class="panel-footer text-right bg-light lter">
                                 <input type="button" class="btn btn-info btn-s-xs" onclick="showPsd()"  value="设置密码">
                                 <button class="btn btn-warning btn-s-xs" type="button" onclick="checkInfo()" >添加</button>
                            </footer>
                        </div>
                     </div>
                </div>
            </div> 
        </div>
	</div> 
</div>

<!--密码框弹出框-->
<div class="modal fade" id="setPassword" role="dialog" aria-labelledby="gridSystemModalLabel" style="margin-top:8%;" data-backdrop="static">
	<div class="modal-dialog s-tanchu-box" role="document" style="width:40%;"> 
        <div class="container-fluid" style="margin-left:212px; margin-top:15px;">
            <div class="row-fluid">
                <!-- col-sm-12 -->
                <div class="col-sm-12 ">
                    <div class="panel panel-default article-bj">
                        <div class="panel-heading box-shodm modal-draggable">
                        	设置密码
                            <button class="close" aria-hidden="true" data-dismiss="modal" type="button">×</button>
                        </div>
                            <div class="row wrapper form-margin">
                                <div class="col-md-12">
                                    <div class="input-group">
                                         <div class="input-group-btn">
                                            <h5 >登陆密码：</h5>
                                         </div>
                                         <input type="password" placeholder="输入密码" class="form-control validate[required,minSize[6],maxSize[16]]" name="password1" id="password1" >
                                         <input type="hidden" id="flag1" value="0">
                                         <span id="passstrength"></span>
                                    </div>
                                </div> 
                                <div class="col-md-12">
                                    <div class="input-group">
                                         <div class="input-group-btn">
                                            <h5 >确认密码：</h5>
                                         </div>
                                         <input type="password" placeholder="确认密码" class="form-control validate[required,minSize[6],maxSize[16],equals[password1]]" name="password" id="password2" >
                                         <input type="hidden" id="flag2" value="0">
                                         <span id="passagain"></span>
                                    </div>
                                </div>
                            </div>
                            <footer class="panel-footer text-right bg-light lter">
                            	<input type="button" class="btn btn-info cx-btn-margin" onclick="checkPsd()"  value="确定">
                            </footer>
                        </div>
                     </div>        	
                </div>
            </div> 
        </div>
	</div>
</div>
</form>

<!--修改操作员信息-->
<form method="post" id="editUserInfoTab">
<div class="modal fade" id="editOperator" role="dialog" aria-labelledby="gridSystemModalLabel" data-backdrop="static">
	<div class="modal-dialog m-tanchu-box" role="document"> 
        <div class="container-fluid" style="margin-left:212px; margin-top:15px;">
            <div class="row-fluid">
                <!-- col-sm-12 -->
                <div class="col-sm-12 ">
                    <div class="panel panel-default article-bj">
                        <div class="panel-heading box-shodm modal-draggable">
                      		 修改操作员
                            <button class="close" aria-hidden="true" data-dismiss="modal" type="button">×</button>
                        </div>
                        	<div class="row wrapper form-margin">
                                <div class="col-md-6">
                                    <div class="input-group">
                                        <div class="input-group-btn">
                                        	<h5 class="h5-margin">账号:</h5>
                                        </div>
                                        <input type="text" class="form-control validate[custom[login,required,maxSize[30]]]" name="account1" id="account1" placeholder="用户账号" readonly="readonly" />
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="input-group">
                                        <div class="input-group-btn">
                                          <h5 class="h5-margin">名称:</h5>
                                         </div>
                                         <input type="text" placeholder="用户名称" class="form-control validate[custom[chineseAndEnglishAndNumber,required],maxSize[50]]]"  name="name1" id="name1" />
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="input-group">
                                        <div class="input-group-btn">
                                          <h5 class="h5-margin">电话:</h5>
                                         </div>
                                         <input type="text" placeholder="联系电话" class="form-control  validate[custom[phone]]" name="telephone1" id="telephone1"/>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="input-group">
                                        <div class="input-group-btn">
                                          <h5 class="h5-margin">邮箱:</h5>
                                         </div>
                                         <input type="text" placeholder="邮箱" class="form-control validate[custom[email,maxSize[100]]]" name="email1" id="email1"/>
                                    </div>
                                </div>

                                <input type="hidden" name="mmbId1" id="mmbId1" value="${mmbId!}"/>
                                <input type="hidden"  id="userPassword" />
                                <input type="hidden" name="id1" id="id1"/>
                             </div>
                             <div class="row wrapper form-margin ca1_check_bg" id="roles_edit">
							</div> 
                            <footer class="panel-footer text-right bg-light lter">
                                 <input type="button" class="btn btn-info btn-s-xs" onclick="showPsd1()"  value="修改密码">
                                 <button class="btn btn-warning btn-s-xs" type="button" onclick="editInfo()" >修改</button>
                            </footer>
                        </div>
                     </div>
                </div>
            </div> 
        </div>
	</div> 
</div>

<!--修改密码框弹出框-->
<div class="modal fade" id="editPassword" role="dialog" aria-labelledby="gridSystemModalLabel" style="margin-top:8%;" data-backdrop="static">
	<div class="modal-dialog s-tanchu-box" role="document" style="width:40%;"> 
        <div class="container-fluid" style="margin-left:212px; margin-top:15px;">
            <div class="row-fluid">
                <!-- col-sm-12 -->
                <div class="col-sm-12 ">
                    <div class="panel panel-default article-bj">
                        <div class="panel-heading box-shodm modal-draggable">
                        	修改密码
                            <button class="close" aria-hidden="true" data-dismiss="modal" type="button">×</button>
                        </div>
                            <div class="row wrapper form-margin">
                                <div class="col-md-12">
                                    <div class="input-group">
                                         <div class="input-group-btn">
                                            <h5 >新密码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：</h5>
                                         <!--   <button class="btn" type="button"></button> -->
                                         </div>
                                         <input type="password" placeholder="新密码" class="form-control validate[required,minSize[6],maxSize[16]]" name="password1" id="password3" >
                                    </div>
                                </div> 
                                <div class="col-md-12">
                                    <div class="input-group">
                                         <div class="input-group-btn">
                                            <h5 >确认密码：</h5>
                                         <!--   <button class="btn" type="button"></button> -->
                                         </div>
                                         <input type="password" placeholder="确认新密码" class="form-control validate[required,minSize[6],maxSize[16],equals[password3]]" name="password" id="password4" >
                                    </div>
                                </div>
                            </div>
                            <footer class="panel-footer text-right bg-light lter">
                            <input type="button" class="btn btn-info cx-btn-margin" onclick="checkPsd1()"  value="确定">
                            <!--<button class="btn btn-info cx-btn-margin" type="submit"  data-dismiss="modal">确定</button>-->
                            </footer>
                        </div>
                     </div>        	
                </div>
            </div> 
        </div>
	</div>
</div>
</form>

<!--输入原始密码框弹出框-->
	<div class="modal fade" id="oldPasswordModal" role="dialog" aria-labelledby="gridSystemModalLabel" style="margin-top:8%;" data-backdrop="static">
		<div class="modal-dialog s-tanchu-box" role="document" style="width:40%;">
			<div class="container-fluid" style="margin-left:212px; margin-top:15px;">
				<div class="row-fluid">
					<!-- col-sm-12 -->
					<div class="col-sm-12 ">
						<div class="panel panel-default article-bj">
							<div class="panel-heading box-shodm modal-draggable">
								原始密码确认
                                <button class="close" aria-hidden="true" data-dismiss="modal" type="button">×</button>
							</div>
							<div class="row wrapper form-margin">
								<div class="col-md-12">
									<div class="input-group">
										<div class="input-group-btn">
											<h5 >原始密码：</h5>
										</div>
										<input type="password" placeholder="请输入原始密码" class="form-control validate[required,minSize[6],maxSize[16]]" name="oldPassword" id="oldPassword" >
									</div>
								</div>
							</div>
							<footer class="panel-footer text-right bg-light lter">
								<input type="button" class="btn btn-info cx-btn-margin" onclick="checkOldPassword()"  value="确定">
							</footer>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
