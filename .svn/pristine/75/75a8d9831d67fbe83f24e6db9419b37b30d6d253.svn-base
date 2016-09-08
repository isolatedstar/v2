<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/jsp/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>群组管理</title>
</head>

<body>
<div class="container-fluid" style="margin-top:15px;">
    <div class="row-fluid">
        <!-- col-sm-12 -->
        <div class="col-sm-12 ">
        	<div class="panel panel-default article-bj">
                <div class="panel-heading box-shodm">
             	   群组管理
                </div>
        			<div class="row wrapper form-margin">
       					 <div class="col-md-4">
        					<div class="input-group">
       							 <div class="input-group-btn">
       							 	<h5 class="h5-margin">群组名:</h5>
       							 <!--   <button class="btn" type="button"></button> -->
       							 </div>
       							 <input type="text" placeholder="群组名称" class="form-control" name="groupName" id="groupName" />
        					</div>
        				</div>
                        <input type="button" class="btn btn-info btn-s-md btn-default" value="查询" onclick="queryGroup()"/>
       				 </div>
       			</form> 
                  <div class="table-responsive panel-table-body ">
                        <table class="table table-striped table-hover " id="tb_groupMan">
                          
                        </table> 
                    </div> 
                    <div class="panel-footer text-right">
                    </div>
        		</div>
       		 </div>
        </div>
    </div> 
</div>
<!--确认加入群弹出框-->
<div class="modal fade" id="applyIn" role="dialog" aria-labelledby="gridSystemModalLabel" data-backdrop="static">
	<div class="modal-dialog s-tanchu-box" role="document" style="width:40%;"> 
        <div class="container-fluid" style="margin-left:212px; margin-top:15px;">
            <div class="row-fluid">
                <!-- col-sm-12 -->
                <div class="col-sm-12 ">
                    <div class="panel panel-default article-bj">
                        <div class="panel-heading box-shodm modal-draggable">
                        	消息提示
							<button class="close" aria-hidden="true" data-dismiss="modal" type="button">×</button>
                        </div>
                            <div class="row wrapper form-margin">
                                <div class="col-md-12">
                                    <div class="input-group">
                                         <div class="input-group-btn">
                                            <h5 >确定加入XXX群？</h5>
                                         <!--   <button class="btn" type="button"></button> -->
                                         </div>
                                    </div>
                                </div> 
                                <div class="col-md-12">
                                    <div class="input-group">
                                         <div class="input-group-btn">
                                            <h5 >时间：2016-03-25</h5>
                                         <!--   <button class="btn" type="button"></button> -->
                                         </div>
                                    </div>
                                </div>
                            </div>
                            <footer class="panel-footer text-right bg-light lter">
                            <button class="btn btn-info cx-btn-margin" type="submit"  data-dismiss="modal">确定</button>
                            </footer>
                        </div>
                     </div>        	
                </div>
            </div> 
        </div>
	</div>
</div>
<!--确认退出群弹出框-->
<div class="modal fade" id="applyOut" role="dialog" aria-labelledby="gridSystemModalLabel" data-backdrop="static">
	<div class="modal-dialog s-tanchu-box" role="document" style="width:40%;">
        <div class="container-fluid" style="margin-left:212px; margin-top:15px;">
            <div class="row-fluid">
                <!-- col-sm-12 -->
                <div class="col-sm-12 ">
                    <div class="panel panel-default article-bj">
                        <div class="panel-heading box-shodm modal-draggable">
                        	消息提示
							<button class="close" aria-hidden="true" data-dismiss="modal" type="button">×</button>
                        </div>
                            <div class="row wrapper form-margin">
                                <div class="col-md-12">
                                    <div class="input-group">
                                         <div class="input-group-btn">
                                            <h5 >确定退出XXX群？</h5>
                                         <!--   <button class="btn" type="button"></button> -->
                                         </div>
                                    </div>
                                </div> 
                                <div class="col-md-12">
                                    <div class="input-group">
                                         <div class="input-group-btn">
                                            <h5 >时间：2016-03-25</h5>
                                         <!--   <button class="btn" type="button"></button> -->
                                         </div>
                                    </div>
                                </div>
                            </div>
                            <footer class="panel-footer text-right bg-light lter">
                            <button class="btn btn-info cx-btn-margin" type="submit"  data-dismiss="modal">确定</button>
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
    	$(function() {
			var oTable = new TableInit();
			oTable.Init();


		});

		//初始化页面的表格数据
		var TableInit = function() {
			var oTableInit = new Object();
			oTableInit.Init = function() {
				$("#tb_groupMan").bootstrapTable({
					url : "${pageContext.request.contextPath}/group/queryGroupMan.do",
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
					sidePagination : "client",
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
								field : "groupStatus",
								title : "状态",
								align : "center",
								valign : "middle",
								sortable : false,
								formatter : function(value,row,index) {
									if(row.groupStatus==1){
										value = '<input class="btn btn-success cx-btn-margin btn-xs" type="button" value="申请加群" onclick="adduserforGroup(\''+row.id+'\')"/>';
									}else if(row.groupStatus==0){
										value = '<input class="btn btn-warning cx-btn-margin btn-xs" type="button" value="审核中" />';
									}else if(row.groupStatus==2){
										value = '<input class="btn btn-danger cx-btn-margin btn-xs" type="button" value="申请退群" onclick="deleteUserforGroup(\''+row.id+'\')"/>';
									}
									return value;
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
		
		//按名称查询群组
		function queryGroup(){
			var groupName=$("#groupName").val();
    		var pageSize = $("#tb_groupMan").bootstrapTable('getOptions').pageSize === $("#tb_groupMan").bootstrapTable('getOptions').formatAllRows() ? 
			$("#tb_groupMan").bootstrapTable('getOptions').totalRows : $("#tb_groupMan").bootstrapTable('getOptions').pageSize;
				
			$.ajax({
				url : '${pageContext.request.contextPath}/group/queryGroupMan.do?pageNo=0&pageSize='+pageSize,
				//参数必须一一对应
				data : {
					groupName : groupName
				},
				type : 'POST',
				cache : false,
				dataType : 'json',
				success : function(data) {
					$("#tb_groupMan").bootstrapTable('load', data.data);
				},
				error : function() {
					alert("异常！");
				}
			});
		}
    	
    	//申请入群
		function adduserforGroup(groupId){
			$.ajax({
				url:'${pageContext.request.contextPath }/group/adduserforGroup.do',
				data:{
					"groupId":groupId
				},
				type:"post",
				dataType:'json',
				success:function(data){
					if(data){
						window.location.href = "${pageContext.request.contextPath}/group/toGroupMan.do";
					}else{
						alert("执行失败");
					}	
				},
				error:function(){
					alert("异常");
				}
			});
		}
		
		//会员退群
		function deleteUserforGroup(groupId){
			if(confirm("确定退出该群吗?")){
				$.ajax({
					url:'${pageContext.request.contextPath }/group/deleteUserforGroup.do',
					data:{
						"groupId":groupId
					},
					type:"post",
					dataType:'json',
					success:function(data){
						if(data){
							window.location.href = "${pageContext.request.contextPath}/group/toGroupMan.do";
						}else{
							alert("执行失败");
						}
					},
					error:function(){
						alert("异常");
					}
				});
			}
			
		}
		
	</script>
</html>

