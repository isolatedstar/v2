<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/jsp/common/common.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>群组配置</title>
</head>

<body>
	<div class="container-fluid" style="margin-top:15px;">
	    <div class="row-fluid">
	        <!-- col-sm-12 -->
	        <div class="col-sm-12 ">
	        	<div class="panel panel-default article-bj">
	                <div class="panel-heading box-shodm">
	             	   群组配置
	                </div>
	        		<div class="row wrapper form-margin">
	       				<div class="col-md-4">
	        				<div class="input-group">
	       						<div class="input-group-btn">
	       							 <h5 class="h5-margin">群名称:</h5>
	       						</div>
	       						<input type="text" placeholder="" class="form-control" name="groupName" id="groupName">
	        				</div>
	        			</div>
	        			<input type="button" class="btn btn-info btn-s-md btn-default" onclick="queryGroupList()" value="查询"/>
	       			</div>
	                    <div class="table-responsive panel-table-body ">
	                        <table class="table table-striped table-hover" id="tb_groupCon">
	                          
	                        </table>
	                    </div> 
	        		</div>
	       		 </div>
	        </div>
	    </div> 
	</div>
	<!--管理群组关系-->
	<!--弹出层--> 
	<div class="modal fade" id="manageRelation" data-backdrop="static">
		<div class="modal-dialog m-tanchu-box" role="document"> 
	        <div class="container-fluid" style="margin-left:212px; margin-top:15px;" >
	            <div class="row-fluid">
	                <!-- col-sm-12 -->
	                <div class="col-sm-12 ">
	                    <div class="panel panel-default article-bj">
	                        <div class="panel-heading box-shodm modal-draggable">
	                        	群组关系管理
								<button class="close" aria-hidden="true" data-dismiss="modal" type="button">×</button>
	                        </div>
	                        	<div class="row wrapper form-margin">
	                                <div class="col-md-8">
	                                    <div class="input-group">
	                                         <div class="input-group-btn">
	                                            <h5 class="h5-margin">关系类型:</h5>
	                                         </div>
	                                         <select name="groupRealType" id="groupRealType" class="form-control" onchange="checkType()">
	                                            <option value="1" selected="true">买</option>
	                                            <option value="2">卖</option>
	                                        </select>
	                                    </div>
	                                </div>
	                             </div>
	                            <div class="table-responsive panel-table-body ">
	                                <table class="table table-striped table-hover " id="groupRealTable1">
	                                    <thead>
	                                        <tr>
	                                            <th><input type="checkbox" id="checkAll1" name="checkAll1"></th>
	                                            <th>群名称</th>
	                                        </tr>
	                                    </thead>
	                                    <tbody>
	                                    </tbody>
	                                </table>
	                            </div> 
	                            <input type="hidden" name="selGroupId" id="selGroupId" value=""/>
	                            <footer class="panel-footer text-right bg-light lter">
		                            <button class="btn btn-success btn-s-xs" onclick="toAddReal(1)">添加</button>
		                            <button class="btn btn-info btn-s-xs" onclick="deleReal()">删除</button>
		                            <button class="btn btn-warning btn-s-xs" type="button" data-dismiss="modal" onclick="exit_B()">退出</button>
	                            </footer>
	                        </div>
	                     </div>
	                </div>
	            </div> 
	        </div>
		</div> 
	</div>
	<!--管理关系-添加群组-->
	<!--弹出层--> 
	<div class="modal fade" id="addReGroup" role="dialog" aria-labelledby="gridSystemModalLabel" data-backdrop="static">
		<div class="modal-dialog m-tanchu-box" role="document"> 
	        <div class="container-fluid" style="margin-left:212px; margin-top:15px;">
	            <div class="row-fluid">
	                <!-- col-sm-12 -->
	                <div class="col-sm-12 ">
	                    <div class="panel panel-default article-bj">
	                        <div class="panel-heading box-shodm modal-draggable">
	                        	添加关系群组
								<button class="close" aria-hidden="true" data-dismiss="modal" type="button">×</button>
	                        </div>
	                        	<div class="row wrapper form-margin">
	                                <div class="col-md-8">
	                                    <div class="input-group">
	                                         <div class="input-group-btn">
	                                            <h5 class="h5-margin">名称:</h5>
	                                         <!--   <button class="btn" type="button"></button> -->
	                                         </div>
	                                         <input type="text" placeholder="群组名称" class="form-control" name="groupName2" id="groupName2">
	                                    </div>
	                                </div>
	                                <a class="btn btn-info btn-s-md btn-default float-right cx-btn-margin" href="javascript:void(0);" onclick="queryCon(1)">查询</a>	
	                             </div>
	                            <div class="table-responsive panel-table-body ">
	                                <table class="table table-striped table-hover " id="groupRealTable2">
	                                    <thead>
	                                        <tr>
	                                            <th><input type="checkbox" id="checkAll2"></th>
	                                            <th>名称</th>
	                                        </tr>
	                                    </thead>
	                                    <tbody>
	                                       
	                                    </tbody>
	                                </table>
	                            </div> 
	                            <footer class="panel-footer text-right bg-light lter">
	                            	<input class="btn btn-warning btn-s-xs" type="button" value="确定" onclick="addGroupReal()"/>
	                            	<button class="btn btn-warning btn-s-xs" type="button" onclick="exit()">退出</button>
	                            </footer>
	                        </div>
	                     </div>
	                </div>
	            </div> 
	        </div>
		</div> 
	</div>
</body>
	<script type="text/javascript">
    	$(function(){
    		//复选框全选1
    		$("#checkAll1").click(function() {
    			$('[name=groupRealManage]:checkbox').prop("checked",this.checked);
    		});
    		
    		//复选框全选2
    		$("#checkAll2").click(function() {
    			$('[name=groupRealConManage]:checkbox').prop("checked",this.checked);
    		});

			//模态框关闭后
			$("#manageRelation").on("hide.bs.modal",function(){
				$("#groupRealType").val(1);
			});


    	});
    	
    	$(function() {
			//初始化table
			var oTable = new TableInit();
			oTable.Init();
		});

		//初始化页面的表格数据
		var TableInit = function() {
			var oTableInit = new Object();
			oTableInit.Init = function() {
				$("#tb_groupCon").bootstrapTable({
					url : "${pageContext.request.contextPath}/group/queryGroupByMmbCon.do",
					method : "post",
					dataType : "json",
					classes : "table table-no-bordered",
					contentType : "application/x-www-form-urlencoded",
					striped : true,
					cache : false,
					pagination : true,
					sortable : false,
					sortOrder : "asc",
					queryParams : oTableInit.queryParams,
					sidePagination : "server",
					pageNumber : 1,
					pageSize : 10,
					pageList : [ 10 ],
					search : false,
					strictSearch : false,
					showColumns : false,
					showRefresh : false,
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
								field : "groupName",
								title : "群组名称",
								align : "center",
								valign : "middle",
								sortable : false
							},
							{
								title : "操作",
								align : "center",
								valign : "middle",
								sortable : false,
								formatter : function(value,row, index) {
									return '<a href="#" data-toggle="modal" onclick="manageGroup(\''+row.groupId+'\',1,0);">管理相关群组关系</a>';
								}
							},]
					});
			};
			
			//得到查询的参数
			oTableInit.queryParams = function(params) {
				var temp = {
					pageNo : params.offset,
					pageSize : params.limit,
					groupName : $("#groupName").val()
				};
				return temp;
			};
			return oTableInit;
		};
    	
    	//按条件查询某一个群组
    	function queryGroupList(){
    		//用于判断是否为第一个
    		var groupName=$("#groupName").val();
    		var pageSize = $("#tb_groupCon").bootstrapTable('getOptions').pageSize === $("#tb_groupCon").bootstrapTable('getOptions').formatAllRows() ? 
			$("#tb_groupCon").bootstrapTable('getOptions').totalRows : $("#tb_groupCon").bootstrapTable('getOptions').pageSize;
				
			$.ajax({
				url : '${pageContext.request.contextPath}/group/queryGroupByMmbCon.do?pageNo=0&pageSize='+pageSize,
				//参数必须一一对应
				data : {
					groupName : groupName
				},
				type : 'POST',
				cache : false,
				dataType : 'json',
				success : function(data) {
					$("#tb_groupCon").bootstrapTable('load', data);
				},
				error : function() {
					alert("异常！");
				}
			});
    	}
    	
    	//停用与启用群组,
    	 function editGroupState(id,state){
			 $.ajax({
	            url : '${pageContext.request.contextPath }/group/editGroup.do',
	            data : {
	                "groupIds" : id,
	                "groupState":state
	            },
	            type : "POST",
	            dataType : 'json',
	            success : function(data) {
	                window.location.href = "${pageContext.request.contextPath}/group/toGroupManage.do";
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
		
		//加载管理群组页面
		function manageGroup(groupId,busType,queryStatus){
			//取得当前群组的id,由此作为查询的条件
			$.ajax({
				url:'${pageContext.request.contextPath }/group/queryGroupRelationshipsByCondition.do',
				data:{
					"groupId":groupId,
					"busType":busType,
					"queryStatus":queryStatus	
				},
				type:"post",
				dataType:'json',
				success: function(data){
					$("#groupRealTable1  tr:not(:first)").remove();
					if (data.groupList1.length > 0) {
						if(queryStatus<1){
							for ( var i = 0; i < data.groupList1.length; i++) {
								var content = '';
								content += '<tr>';
								content += "<td><input type=checkbox name=groupRealManage value='"+data.groupList1[i].id+"'></td>";
								content += "<td>"+data.groupList1[i].rpGroupName+"</td>";
								content += '</tr>';
								addTr('groupRealTable1', -1, content);
							}
						}else{
							for ( var i = 0; i < data.groupList1.length; i++) {
								var content = '';
								content += '<tr>';
								content += "<td><input type=checkbox name=groupRealManage value='"+data.groupList1[i].id+"'></td>";
								content += "<td>"+data.groupList1[i].groupName+"</td>";
								content += '</tr>';
								addTr('groupRealTable1', -1, content);
							}
						}
					}
					$("#selGroupId").val(groupId);
					$("#manageRelation").modal("show");
				},
				error: function(){
					alert("异常");
				}
			});
		}
		
		//转向添加页面
		function toAddReal(queryStatus){
			var selGroupId= $("#selGroupId").val();
			var busType=$("#groupRealType").val();
			$.ajax({
				url:'${pageContext.request.contextPath }/group/queryGroupRelationshipsByCondition.do',
				data:{
					"groupId":selGroupId,
					"queryStatus":queryStatus,
					"busType":busType
				},
				type:"post",
				dataType:'json',
				success:function(data){
					$("#groupRealTable2  tr:not(:first)").remove();
					if (data.groupList2.length > 0) {
						for ( var i = 0; i < data.groupList2.length; i++) {
							var content = '';
							content += '<tr>';
							content += "<td><input type=checkbox name=groupRealConManage value='"+data.groupList2[i].id+"'></td>";
							content += "<td>"+data.groupList2[i].groupName+"</td>";
							content += '</tr>';
							addTr('groupRealTable2', -1, content);
						}
					}

					$("#addReGroup").modal("show");
				},
				error:function(){
					alert("异常！");
				}
			});
		}
		
		//改变业务类型
		function checkType(){
			var selGroupId= $("#selGroupId").val();
			var busType=$("#groupRealType").val();
			manageGroup(selGroupId,busType,0);
		}
		
		//退出添加页面
		function exit(){
			var selGroupId= $("#selGroupId").val();
			var busType=$("#groupRealType").val();
			manageGroup(selGroupId,busType,0);
			$("#addReGroup").modal("hide");
		}
		
		//按群名称反向查询群组
		function queryCon(queryStatus){
			var selGroupId= $("#selGroupId").val();
			var busType=$("#groupRealType").val();
			var groupName2=$("#groupName2").val();
			$.ajax({
				url:'${pageContext.request.contextPath }/group/queryGroupRelationshipsByCondition.do',
				data:{
					"groupId":selGroupId,
					"queryStatus":queryStatus,
					"groupName":groupName2,
					"busType":busType
				},
				type:"post",
				dataType:'json',
				success:function(data){
					$("#groupRealTable2  tr:not(:first)").remove();
					if (data.groupList2.length > 0) {
						for ( var i = 0; i < data.groupList2.length; i++) {
							var content = '';
							content += '<tr>';
							content += "<td><input type=checkbox name=groupRealConManage value='"+data.groupList2[i].id+"'></td>";
							content += "<td>"+data.groupList2[i].groupName+"</td>";
							content += '</tr>';
							addTr('groupRealTable2', -1, content);
						}
					}
				},
				error:function(){
					alert("异常！");
				}
			});
		}
		
		//查询自己管理的群组
		function queryGroup(){
			var groupName=$("#groupName").val();
			$.ajax({
				url:'${pageContext.request.contextPath }/group/queryGroupList.do',
				data:{
					"groupName":groupName
				},
				dataType:'json',
				type:"post",
				success:function(data){
				},
				error:function(){
					alert("异常！");
				}
			});
		}
		
		//添加关系类型
		function addGroupReal(){
			//开始先设置深度序列化的值
    		$.ajaxSettings.traditional = true;
    		//获取当前选中的群组Id
    		var selGroupId= $("#selGroupId").val();
    		var busType=$("#groupRealType").val();
    		//获取选中的复选框信息
    		var realIds="";
    		$('input[name="groupRealConManage"]:checked').each(function(){
    			//拼接字符串
    			realIds += $(this).val() +","; 
    		});
    		if (realIds == 0) {
		        alert('请选择审批的信息！');
		        return;
		    }
		    realIds=realIds.substring(0,realIds.length-1);
		    $.ajax({
				url:'${pageContext.request.contextPath }/group/createGroupReal.do',
				data:{
					"rpGroupIds":realIds,
					"busType":busType,
					"groupId":selGroupId
				},
				dataType:'json',
				type:"post",
				success:function(data){
					if(data){
						toAddReal(1);
					}else{
						alert("操作失败!");
					}
				},
				error:function(){
					alert("异常！");
				}
			});
		}
		
		//退出群组
		function exit_B(){
			$("#groupRealType").val("1");
		}
		
		//删除群组关系
		function deleReal(){
			if(confirm("确定要删除此群组之间的关系?")){
				//开始先设置深度序列化的值
				$.ajaxSettings.traditional = true;
				var selGroupId= $("#selGroupId").val();
				var busType=$("#groupRealType").val();
				var realIds="";
	    		$('input[name="groupRealManage"]:checked').each(function(){
	    			//拼接字符串
	    			realIds += $(this).val() +","; 
	    		});
	    		if (realIds == 0) {
			        alert('请选择审批的信息！');
			        return;
			    }
			    realIds=realIds.substring(0,realIds.length-1);
			    $.ajax({
					url:'${pageContext.request.contextPath }/group/deleteGroupReal.do',
					data:{
						"ids":realIds
					},
					dataType:'json',
					type:"post",
					success:function(data){
						if(data){
							manageGroup(selGroupId,busType,0);
						}else{
							alert("操作失败!");
						}
					},
					error:function(){
						alert("异常！");
					}
				});
			}
		}
    </script>
</html>
