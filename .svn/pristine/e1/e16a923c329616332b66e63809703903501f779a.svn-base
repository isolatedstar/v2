<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />   <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   <meta charset="UTF-8">
	   <link rel="stylesheet" href="${ctx}/mall/css/bootstrap.min.css">
	   <link href="${ctx}/mall/css/theme.css" rel="stylesheet">
	   <script src="${ctx}/mall/js/jquery.js"></script>
	   <script src="${ctx}/mall/js/jquery.min.js"></script>
	   <script src="${ctx}/mall/js/bootstrap.min.js"></script>
	   <script type="text/javascript">
	   
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
	        return;;
	     }
	     $tr.after(trHtml);
	  }
	   
	   //修改平台操作员的时候
	   function check1(){
	   		//若为默认的值:"0",则清空下面显示的数据
			var userId=$("#userId").val();
			if(userId!="0"){
				$.ajax({
					url:'${ctx}/agentUserController/showAgentUser.do',
					dataType:'JSON',
					type:'POST',
					data:{"userId":userId},
					cache : false,
					success : function(data) {
						//两步动作,一步添加数据在下面的表格中,另一步把我们查询出来的操作员显示在下拉列表中
						//先清空table中的数据
						$("#userTable  tr:not(:first)").remove();
						if (data.muRealList != null && data.muRealList.length > 0) {
							for ( var i = 0; i < data.muRealList.length; i++) {
								var content = '';
								content += '<tr>';
								content += "<td><input type=checkbox name=deleUser value='"+data.muRealList[i].id+"'></td>";
								content += "<td>"+data.muRealList[i].name+"</td>";
								content += "<td>"+data.muRealList[i].mmbName+"</td>";
								content += "<td>"+data.muRealList[i].roleName+"</td>";
								content += '</tr>';
								addTr('userTable', -1, content);
							}
						}
					},
					error : function() {
						alert("异常！");
					}
				});
			}else{
				alert("没有选择代理操作员");
				$("#userTable  tr:not(:first)").remove();
			}
						
	   }
	   
	   //删除代理之间关系
	   function deleAgentUser(){
	   	if(confirm("确认删除此代理关系?")){
	   		//把选中的复选框转化为一个字符串
		   	//第一步:取得选中的复选框,返回jQuery对象数组
		   	var checkUsers=$("#userTable tr td input[name=deleUser]:checked");
		   	//判断返回是否有值
		   	if(checkUsers.length==0){
				alert("请选择需要删除的关系会员！");
				return false;
			}
			var stringsId = "";
			//把返回的jQuery对象数组转化为字符串
			checkUsers.each(function(i,thisElement){
				if(thisElement.value!=""){
					stringsId+=thisElement.value+",";
				}
			});
			//去掉最后一个逗号
			stringsId = stringsId.substring(0,stringsId.length-1);
		   	//提交批量删除信息
			$.ajax({
				url : '${ctx}/agentUserController/deleteAgentUser.do',// 跳转到 action
				data : {
					ids : stringsId
				},
				type : 'POST',
				cache : false,
				dataType : 'json',
				success : function(data) {
					//先清空table中的数据
					var result = data;
					if(result){
						alert("删除会员关系成功！");
						//重新加载数据
						check1();
					}
					else{
						alert("删除会员关系失败！");
					}
				},
				error : function() {
						alert("异常！");
				}
			});
	   	}
	  }
	   
	   //全选和反选
	   function checkAll(obj){
	   		$("#userTable tr td [name =deleUser]:checkbox").each(function(i,thisElement){
			if($(obj).prop("checked")==true){
		      $(thisElement).prop("checked",'true');
			}else{
				$(thisElement).prop("checked",false);
			}
		});
	   }
	   
	   //添加(建立)操作员之间的代理关系
	   function addAgentUser(){
	   		//校验录入的业务操作员是否存在,判断的标准为登录名(account),若返回为true,则表示存在,添加他们之间代理关系,
	   		//若放回为false,则表示此业务操作员不存在,代理失败
	   		var account=$("#agentUserAccount").val();
	   		var userId=$("#userId").val();
	   		if(userId!="0"){
	   			if(account!=""&&account!=null){
		   			//验证此操作员是否存在
		   			$.ajax({
						url:'${ctx}/agentUserController/checkUser.do',
						dataType:'JSON',
						type:'POST',
						data:{"account":account},
						success:function(data){
							//返回为true,表示找到了此操作员,接下来就可以给他们进行建立代理关系
							if(data!=null&&data!=""){
								$("#agentUserId").val(data.id);
								$.ajax({
									url:'${ctx}/agentUserController/createAgentUser.do',
									type: 'POST',
					                dataType: 'json',
									data:$('#addAgentUser').serialize(),
									success:function(result){
									   	 //如果成功
									   	 if(result){
									   	 	alert("操作成功！"); 
									   	 	//返回当前代理人界面,并把前面选中的值带回去
									   	 	check1();
									   	 }
									   	 //如果失败
									   	 else {
									   	 	alert("添加失败,请重新录入!");
									   	 }
									}
								});
							}else{
								alert("请输入合法的代理操作员账号");
							}
							//返回值为false,则表示不存在,直接返回
						},
						error:function(){
							alert("操作异常！");
						}
					});	
					
		   		}else{
		   			alert("请输入需要代理操作员的账号");
		   		}
	   		}else{
	   			alert("请选择平台操作员");
	   		}
	   }
	   </script>
	   <title>客服管理</title>
	</head>
	
	<body>
		<div class="container-fluid" style="margin-top:15px;">
		    <div class="row-fluid">
		        <!-- col-sm-12 -->
		        <div class="col-sm-12 ">
		        	<div class="panel panel-default article-bj">
		                <div class="panel-heading box-shodm">
		                	客服管理
		                </div>
		                <form id="addAgentUser" method="post">
			        		<div class="row wrapper form-margin">
			       				<div class="col-md-4">
			        				<div class="input-group">
			       						<div class="input-group-btn">
			       							 <h5 class="h5-margin">客户服务人员:</h5>
			       						</div>
			       						<select name="userId" id="userId" tabindex="-1"class="form-control" onchange="check1()">
			       							 <option value="0">请选择</option>
			       							 <#if userList ??>
												<#list userList as user>
													<option value="${user.id!}">${user.name!}</option>
												</#list>
											</#if>
			                            </select>
			        				</div>	
			        			</div>
			                    <div class="col-md-4">
			                        <div class="input-group">
                                        <div class="input-group-btn">
                                            <h5 class="h5-margin">被代理用户账号:</h5>
                                        </div>
			                            <input type="text" class="form-control" name="agentUserAccount" id="agentUserAccount">
			                            <input type="hidden" id="agentUserId" name="agentUserId">
			                        </div>
			                    </div>
			       				<a class="btn btn-info btn-s-md btn-default float-right cx-btn-margin" href="#" onclick="addAgentUser()">新增</a>
			       			</div>
			       		</form>
		                <div class="table-responsive panel-table-body ">
		                    <table class="table table-striped table-hover" id="userTable">
		                        <thead>
		                            <tr>
		                                <th><input type="checkbox" onclick="checkAll(this)"></th>
		                                <th >已代理的操作员</th>
		                                <th >所属会员</th>
		                                <th >角色</th>
		                            </tr>
		                        </thead>
		                        <tbody>
		                            
		                        </tbody>
		                    </table>
		                </div> 
		                <footer class="panel-footer text-right bg-light lter">
		                    <button class="btn btn-warning btn-s-xs" onclick="deleAgentUser()">删除</button>
		                </footer>
		        	</div>
		        </div>
		    </div> 
		</div>
	</body>
</html>

