<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />   <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
   <link href="${ctx}/mall/css/pulic.css" rel="stylesheet" type="text/css">
	<link href="${ctx}/mall/css/theme.css" rel="stylesheet" type="text/css">
	<link href="${ctx}/mall/css/bootstrap.min.css" rel="stylesheet" type="text/css">
  
  
	<script src="${ctx}/mall/js/jquery-1.11.1.min.js" type="text/javascript"></script>

	<!--over-->
   <script  type="text/javascript" src="${ctx}/mall/js/bootstrap.min.js"></script>
   <script  type="text/javascript" src="${ctx}/mall/js/bootstrap-treeview.js"></script>
   <script type="text/javascript" src="${ctx}/mall/js/extendPagination.js"></script>
   	<!-- 配置界面上的css -->
  	
	<script type="text/javascript" src="${ctx}/mall/js/swfupload/swfupload.js"></script>
	<script type="text/javascript" src="${ctx}/mall/js/swfupload/swfupload.queue.js"></script>
	<script type="text/javascript" src="${ctx}/mall/js/swfupload/handlers.js"></script>
	<script type="text/javascript" src="${ctx}/mall/js/swfupload/swfupload.js"></script>
	<script type="text/javascript" src="${ctx}/mall/js/swfupload/handlers.js"></script>
	<!--over-->
	 <script src="${ctx}/common/js/common.js"></script>
   <!-- 校验开始 -->

<script src="${ctx}/mall/js/serializeJson.js"></script>
<script type="text/javascript" src="${ctx}/common/jqueryValidation/js/jquery.validationEngine-zh_CN.js"></script>
	<script type="text/javascript" src="${ctx}/common/jqueryValidation/js/jquery.validationEngine.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/common/jqueryValidation/css/validationEngine.jquery.css" />
<!-- 校验结束 -->
  <script type="text/javascript">
			var swfu;
			window.onload = function () {
				swfu = new SWFUpload({
					upload_url: "${Request.basePath}material/swfupload.do",
					use_query_string: true,
					post_params: {	"showDivId" : $("#showDivId").val(),
									"imgType" : 1
								 },
					
					// File Upload Settings
					file_size_limit : "10 MB",	// 1000MB
					file_types : "*.*",
					file_types_description : "所有文件",
					file_upload_limit : "0",
									
					file_queue_error_handler : fileQueueError,
					file_dialog_complete_handler : fileDialogComplete,//选择好文件后提交
					file_queued_handler : fileQueued,
					upload_progress_handler : uploadProgress,
					upload_error_handler : uploadError,
					upload_success_handler : uploadSuccess,
					upload_complete_handler : uploadComplete,
	
					// Button Settings
					button_image_url : "${ctx}/mall/images/SmallSpyGlassWithTransperancy_17x18.png",
					button_placeholder_id : "spanButtonPlaceholder",
					button_width: 150,
					button_height: 18,
					button_text : '<span class="button">选择图片 <span class="buttonSmall">(10 MB Max)</span></span>',
					button_text_style : '.button { font-family: Helvetica, Arial, sans-serif; font-size: 12pt; } .buttonSmall { font-size: 10pt; }',
					button_text_top_padding: 0,
					button_text_left_padding: 18,
					button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
					button_cursor: SWFUpload.CURSOR.HAND,
					
					// Flash Settings
					flash_url : "${ctx}/mall/js/swfupload/swfupload.swf",
	
					custom_settings : {
						upload_target : "divFileProgressContainer"
					},
					// Debug Settings
					debug: false  //是否显示调试窗口
				});
			};
			function startUploadFile(){
			   var divId =  $("#showDivId").val();
			   var imgType = $("#imgType").val();
			    var imgdes = $("#imgdes").val();
			   //alert(imgType);
				var parm = {	"showDivId" :divId,
									"imgType" : imgType,
									   "imgdes":imgdes          
								 };
				swfu.setPostParams(parm);
				swfu.startUpload();
				//显示资源
				
			}
			
			function showExtShow(){
				win.show();
			}
		</script>
   <script language="JavaScript">  
	
	</script>
<title>资源库管理</title>


<script type="text/javascript">
	  $(function(){
	  
	  			//校验     
			 $("#ceateBase").validationEngine('attach',{
					scroll:false,
					autoHidePrompt:true,
					autoHideDelay:2500,
					promptPosition : "bottomLeft"
			 });
	         //获取选择的图片类型  根据标题还是轮播  触发单选多选效果
            	$("#showMalTable tr td [name=malCheckbox").click(function(){
            		 
						checkRedio(this);
					
				});	
				//显示资源库
				showBase();
        });
     //全选反选群组      name='malCheckbox'   showMalTable
	  function checkAllMal(obj){
		var changeType = $("#changeType").val();
        if(changeType==2){
				$("#showMalTable tr th [name =malCheckbox]:checkbox").each(function(i,thisElement){
					if($(obj).prop("checked")==true){
				      $(thisElement).prop("checked",'true');
					}else{
						$(thisElement).prop("checked",false);
					}
				});
		}
		
		
	}
	//多选框实现单选
	function checkRedio(obj){
	
		var changeType = $("#changeType").val();
        if(changeType==1){
				if($(obj).prop('checked')==true){
					$("#showMalTable tr td [name =malCheckbox]:checkbox").removeAttr('checked');
					$(obj).prop('checked','true');
				}			
		}
		
		
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
	
	  
   //显示资料库
   function showBase(){
		
		//alert("查询");
		var name = $("#materialName").val();
		var type = $("#materialType").val();
	  	//关闭目录大窗口
	  	$("#opendiv").modal("hide");
		$.ajax({
				url : '${ctx}/mbase/showBase.do',// 跳转到 action
				data : {
							materialName:name,
							materialType : type,
							
				},
				type : 'POST',
				cache : false,
				dataType : 'json',
				success : function(data) {
					data = eval(data);
					
					//先清空table中的数据
					$("#showBase  tr:not(:first)").remove();
					var m = 1;
					if(data.pub.length>0){
						for ( var i = 0; i < data.pub.length; i++) {
		
							var content = '';
							
							content += '<tr>';
							content +="<td>"+m+"</td>";
							content += "<td><a href='#' data-toggle='modal' data-target='#opendiv' onclick=showDiv('"+data.pub[i].id+"','"+data.pub[i].materialType+"')>"+data.pub[i].materialName+"</a></td>";
							content += "<td>共享</td>";
							content += "<td>"+data.pub[i].remark+"</td>";
							content += "<td><a href='#' >删除</a></td>";
							content += '</tr>';
							addTr('showBase', -1, content);
							m++;
						}	
					}
					if(data.baseList.length>0){
						
						for ( var i = 0; i < data.baseList.length; i++) {
		
							var content = '';
							
							content += '<tr>';
							content +="<td>"+m+"</td>";
							content += "<td><a href='#' data-toggle='modal' data-target='#opendiv' onclick=showDiv('"+data.baseList[i].id+"','"+data.baseList[i].materialType+"')>"+data.baseList[i].materialName+"</a></td>";
							content += "<td>个人</td>";
							content += "<td>"+data.baseList[i].remark+"</td>";
							content += "<td><a href='#'>删除</a></td>";
							content += '</tr>';
							addTr('showBase', -1, content);
							m++;
						}	
					}       
				},
				error : function() {
					alert("异常！");
				}
		});		
	};
	
	//显示目录
	function showDiv(id,type){
	    //alert("加载目录");
	    //关闭图片div
	    $("#imgShow").hide();  
	    $("#showBaseId").val(id);
	    $("#showBaseType").val(type);
	    //清空table
	    $("#showMalTable  tr:not(:first)").remove();
	    
	     //判断资源库类型  公共库0 不显示  
	    var type = $("#showBaseType").val();
	    //alert(type);
	    if(type==0){
	    	$("#unSee").hide();
	    }else{
	    	$("#unSee").show();
	    }
	    $.ajax({
				url : '${ctx}/mdiv/showDiv.do',// 跳转到 action
				data : {
							baseId:id,		
				},
				type : 'POST',
				cache : false,
				dataType : 'json',
				success : function(data) {
					data = eval(data);
					$("#tree").empty();
					if(data!=""&&data.length>0){
						$('#tree').treeview({
						  color: "#428bca",
						  enableLinks: true,
						  data: data,
						  showBorder: true,
						  expandIcon: 'glyphicon glyphicon-chevron-right',
						  collapseIcon: 'glyphicon glyphicon-chevron-down',
						  nodeIcon: 'glyphicon glyphicon-file',
						  showCheckbox:true, 
						  onNodeChecked: function(event, node) {
							//alert(node.id);
							//$("#showMalTable tr td [name =malCheckbox]:checkbox").removeAttr('checked');
							//$(obj).prop('checked','true');
							//alert(event);
							$("#selectDIvId").val(node.id);
							$("#editDivName").val(node.text);
						  },
						  onNodeUnchecked: function (event, node) {
							//alert('<p>' + node.text + ' was unchecked</p>');
						  }
						});
					}
					//点击事件
					 $('#tree').on('nodeSelected', function(event, data) {
							if(data.nodes==null||data.nodes==""){	
								//赋值
								//alert(data.id);
								$("#showDivId").val(data.id);
								showMal();	
							}
					});
				},
				error : function() {
					alert("异常！");
				}
		});	
	}
	//显示资源
	function showMal(){
		
		//打开图片div
		$("#imgShow").show();
		//获取选中的目录
		var divId = $("#showDivId").val();
		//alert(divId);
		$.ajax({
				url : '${ctx}/material/showPic.do',// 跳转到 action
				data : {
							divId:divId,
							pageNo : 1,
							pageSize : 3,
							materialName:$("#malName").val(),
							type:$("#malType").val(),
				},
				type : 'POST',
				cache : false,
				dataType : 'json',
				success : function(data) {
					data = eval(data);
					//先清空table中的数据
					$("#showMalTable  tr:not(:first)").remove();
					//获取类型  标题还是轮播
					var changeType = $("#changeType").val();
					
	    			
					if(data.picList.length>0){
						var m = 1;
						for ( var i = 0; i < data.picList.length; i++) {
						
							var content = '';
							
							content += '<tr>';
							
							
							
							
							content +="<td style='vertical-align:middle'>"+m+"</td>";
							var path = "${ctx}"+data.picList[i].picPath;
							content +="<td><img src='"+path+"' style='height:50px; width:50px;'></td>";
							content +="<td style='vertical-align:middle'>"+data.picList[i].materialName+"</td>";
							if(data.picList[i].type==1){
								content +="<td style='vertical-align:middle'>图片</td>";
							}
							if(data.picList[i].type==2){
								content +="<td style='vertical-align:middle'>视频</td>";
							}
							//获取显示的数据库类型  公共库无上传按钮  删除按钮
							var basetype = $("#showBaseType").val();
							content +="<td style='vertical-align:middle'>";
								content +="<a href='#' onclick=seeMal('"+data.picList[i].id+"') >预览</a>";
							if(basetype ==1){
							content +="&nbsp;&nbsp;<a  href='#' onclick=delMal('"+data.picList[i].id+"')  >删除</a>";
							}	
								
							content +="</td>";
							content += '</tr>';
							m++;
							addTr('showMalTable', -1, content);
							
						}
						var mm = '';
						
							
						mm += "<tr>";
						mm += "<td class='text-right' colspan='6'>";
						if(basetype ==1){
							mm += "<a class='btn btn-success' href='#' data-toggle='modal' data-target='#upload'>上传</a>";
						}	
                                                           
                        mm += "<a class='btn btn-warning' href='#' onclick='childCloseImg();'>关闭</a></td>";                                   
                        mm += '</tr>';                   					
                        addTr('showMalTable', -1, mm);                              
                    
						setPagination(1, 3, data.picCount);
					}else{
						alert("无资源！");
						var mm = '';
						//获取显示的数据库类型  公共库无上传按钮
						var basetype = $("#showBaseType").val();
							
						mm += "<tr>";
						mm += "<td class='text-right' colspan='6'>";
						if(basetype ==1){
							mm += "<a class='btn btn-success' href='#' data-toggle='modal' data-target='#upload'>上传</a>";
						}	
                                                          
                        mm += "<a class='btn btn-warning' href='#' onclick='childCloseImg();'>关闭</a></td>";                                   
                        mm += '</tr>';                   					
                        addTr('showMalTable', -1, mm);   
					}
					
				},
				error : function(){
					alert("异常！");
				}
		});		
	}
	function setPagination(curr, limit, totalCount) {
			//alert("分页");
			//打开图片div
			$("#imgShow").show();
			$('#callBackPager').extendPagination({
				totalCount : totalCount,
				showCount : limit,
				limit : limit,
				callback : function(curr, limit, totalCount) {
					
					$.ajax({
						url : '${ctx}/material/showPic.do',// 跳转到 action
						data : {
								divId:$("#showDivId").val(),
								pageNo : curr,
								pageSize : limit,
								materialName:$("#malName").val(),
								type:$("#malType").val(),
						},
						type : 'POST',
						cache : false,
						dataType : 'json',
						success : function(data) {
					data = eval(data);
					//先清空table中的数据
					$("#showMalTable  tr:not(:first)").remove();
					//判断类型
					var changeType = $("#changeType").val();
					if(data.picList.length>0){
						var m = 1;
						for ( var i = 0; i < data.picList.length; i++) {
						
							var content = '';
							
							content += '<tr>';
							
							content +="<td style='vertical-align:middle'>"+m+"</td>";
							var path = "${ctx}"+data.picList[i].picPath;
							content +="<td><img src='"+path+"' style='height:50px; width:50px;'></td>";
							content +="<td style='vertical-align:middle'>"+data.picList[i].materialName+"</td>";
							if(data.picList[i].type==1){
								content +="<td style='vertical-align:middle'>图片</td>";
							}
							if(data.picList[i].type==2){
								content +="<td style='vertical-align:middle'>视频</td>";
							}
							//获取显示的数据库类型  公共库无上传按钮  删除按钮
							var basetype = $("#showBaseType").val();
							content +="<td style='vertical-align:middle'>";
								content +="<a href='#' onclick=seeMal('"+data.picList[i].id+"') >预览</a>";
							if(basetype ==1){
							content +="&nbsp;&nbsp;<a  href='#' onclick=delMal('"+data.picList[i].id+"')  >删除</a>";
							}	
								
							content +="</td>";
							content += '</tr>';
							m++;
							addTr('showMalTable', -1, content);
						}
						var mm = '';
						
						mm += "<tr>";
						mm += "<td class='text-right' colspan='6'>";
						if(baseType ==1){
							mm += "<a class='btn btn-success' href='#' data-toggle='modal' data-target='#upload'>上传</a>";
						}	
                                                        
                        mm += "<a class='btn btn-warning' href='#' onclick='childCloseImg();'>关闭</a></td>";                                   
                        mm += '</tr>';                   					
                        addTr('showMalTable', -1, mm); 
					}else{
						alert("无资源");
						var mm = '';
						//获取显示的数据库类型  公共库无上传按钮
						var basetype = $("#showBaseType").val();
							
						mm += "<tr>";
						mm += "<td class='text-right' colspan='6'>";
						if(basetype ==1){
							mm += "<a class='btn btn-success' href='#' data-toggle='modal' data-target='#upload'>上传</a>";
						}	
                                                          
                        mm += "<a class='btn btn-warning' href='#' onclick='childCloseImg();'>关闭</a></td>";                                   
                        mm += '</tr>';                   					
                        addTr('showMalTable', -1, mm);   
					}
					
				},
			});
			}
		});
	}
	
	//新增目录
	function addDiv(){
		//获取输入的名字
		var name = $("#addDivName").val();
		if(name==""){
			alert("请输入名称");
			return false;
		}
		//获取选中的节点，在同级节点处添加 异步获取该节点的父节点
		var id=$("#selectDIvId").val();
		//id为空  添加根节点
		//alert(id);
		//资料库Id
		var baseId = $("#showBaseId").val();
		//alert(baseId);
		
		
		$.ajax({
			url : '${ctx}/mdiv/addDiv.do',// 跳转到 action
			data : {
					parentId:id,
					divName : name,
					baseId : baseId,
			},
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data){
				if(data==0){
					
					alert("目录新增成功");
					//清空数据
					$("#selectDIvId").val("");
					
					//清空数据
					$("#addDivName").val("");
					//关闭窗口
					$("#addCatalogue").modal("hide");
					
					//重新加载目录
					var type = $("#showBaseType").val();
					var nn = $("#showBaseId").val();
					showDiv(nn,type);
				}else{
					alert("目录新增失败");
				}
			},
		});
	};
	//新增 返回按钮
	function addDivClose(){
		
		//清空数据
		$("#addDivName").val("");
		//关闭窗口
		$("#addCatalogue").modal("hide");
	}
	//编辑目录
	function updateDiv(){
		//获取输入的名字
		var name = $("#editDivName").val();
		if(name==""){
			alert("请输入名称");
			return false;
		}
		//资料库Id
		var baseId = $("#showBaseId").val();
		//获取选中的节点
		var id=$("#selectDIvId").val();
		$.ajax({
			url : '${ctx}/mdiv/updateDiv.do',// 跳转到 action
			data : {
					
					divName : name,
					divId : id,
					
			},
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data) {
				if(data==0){
					//关闭窗口
					
					alert("目录修改成功");
					$("#editCatalogue").modal("hide");
					//清空数据
					$("#selectDIvId").val("");
					
					//清空数据
					$("#editDivName").val("");
					//重新加载目录
					var type = $("#showBaseType").val();
					var nn = $("#showBaseId").val();
					showDiv(nn,type);
				}else{
					alert("目录修改失败");
				}
			},
		});
	}
	//删除按钮
	function delDiv(){
		//获取选中的节点
		var id=$("#selectDIvId").val();
		//资料库Id
		var baseId = $("#showBaseId").val();
		$.ajax({
			url : '${ctx}/mdiv/delDiv.do',// 跳转到 action
			data : {
					divId : id,	
			},
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data) {
				if(data.successMsg!=""&&data.successMsg.length>0){
					//关闭窗口
					$("#deleteCatalogue").modal("hide");
					alert(data.successMsg);
					//重新加载目录
					var type = $("#showBaseType").val();
					var nn = $("#showBaseId").val();
					showDiv(nn,type);
				}else{
					alert(data.errorMsg);
				}
			},
		});
	}
	
	
	//资料库返回按钮
	function childClose(){
		//关闭显示目录div
		$("#opendiv").modal("hide");
		
		parent.$("#childPage1").modal('hide');	
	}
	
	//资源  确认按钮  向父页面传值     name='malCheckbox'   showMalTable
	function childSub(){
		
		//获取选中的多选框按钮的值
	 		
  		var checkedMal = $("#showMalTable tr td [name =malCheckbox]:checked");
		var stringsId = "";
		checkedMal.each(function(i,thisElement){
			if(thisElement.value!=""){
				stringsId+=thisElement.value+",";
			}
		});
		//去掉最后一个逗号
		stringsId = stringsId.substring(0,stringsId.length-1);
		if(stringsId.length==0){
			alert("请先选择商品");
			return false;
		}
		//分割字符串
		var ids = stringsId.split(",");
		// 判断类型    getType    商品新增  1  商品编辑为2   报价新增 为3  报价新增为4
		var type = $("#getType").val(); 
		//
		if(type!=""&&type.length>0){
			if(type==1){
				//给商品新增页面赋值
				//获取选中的id的缩略图路径  给img赋值
				parent.$("#imgShow").attr("src","");
				//向父页面传值  malId
				parent.S("#imgId").val();
			}
			if(type==2){
				//给商品修改页面赋值
				//获取选中的id的缩略图路径  给img赋值
				parent.$("#imgShow1").attr("src","");
				//向父页面传值  malId
				parent.S("#imgId1").val();
			}
			if(type==3){
				//给报价新增页面赋值
				//获取选中的id的缩略图路径  给img赋值
				parent.$("#addQuoteImg").attr("src","");
				//向父页面传值  malId
				parent.$("#titlePic1").val();
			}
			if(type==4){
				//给报价编辑页面赋值
				//获取选中的id的缩略图路径  给img赋值
				parent.$("#imageId").attr("src","");
				//向父页面传值  malId
				parent.S("#s").val();
			}
		}
		
	}
	
	//资源  关闭按钮  关闭ifream
	function childCloseImg(){
		//关闭显示目录div
		$("#opendiv").modal("hide");
		$("#tree").empty();
		
	}
	//取消新增资源库
	function closeBaseAdd(){
		$("#add").modal("hide");
		 $("#materialName1").val("");
		 $("#remark1").val("");
	}
	//新增资源库
	function addBase(){
		var materialName1 = $("#materialName1").val();
		var remark1 = $("#remark1").val();
		//alert(materialName1);
		if(materialName1==""){
			alert("资源库名称不能为空");
			return false;
		}
		$.ajax({
			url : '${ctx}/mbase/addMBase.do',// 跳转到 action
			data : {
					materialName1 : materialName1,	
					remark1 : remark1
			},
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data) {
				if(data=="0"){
					alert("创建成功");
					closeBaseAdd();
				}else{
					alert("创建失败");
				}
				
			},
		});
	}
	//预览
	function seeMal(id){
		//alert(id);
		$.ajax({
			url : '${ctx}/material/getPath.do',// 跳转到 action
			data : {
					malId : id,	
					type : 1
			},
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data) {
				if(data!=null&&data.length>0){
					if (data.substr(0,1)=='/'){
						data=data.substr(1);
					}
					
					var path = '${Request.basePath}'+data;
					//alert(path);
					//newwindow.location.href = path;
					window.open(path);
				}
				
			},
		});
	}
	//删除
	function delMal(id){
		//alert(id);
		$.ajax({
			url : '${ctx}/material/delMal.do',// 跳转到 action
			data : {
					malId : id
			},
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data) {
			    data = eval(data);
				if(data=="0"){
					//重新加载
					showMal();
				}	
			},
		});
	}
</script>
</head>

<body>
<div class="container-fluid" id="baseId11" >
    <div class="row-fluid" >
        <!-- col-sm-12 -->
        <div class="col-sm-12 ">
        	<div class="panel panel-default article-bj">
                <div class="panel-heading box-shodm">
                资源库管理
                </div>
        			<div class="row wrapper form-margin">
       					 <div class="col-md-4">
        					<div class="input-group">
       							 <div class="input-group-btn">
       							 	<h5 class="h5-margin">资源库名称:</h5>
       							 <!--   <button class="btn" type="button"></button> -->
       							 </div>
       							 <input type="text" placeholder="" class="form-control" name="materialName" id="materialName">
       							 <input type="hidden" id="showBaseId"/>
       							 <input type="hidden" id="getType"/>
       							 <input type="hidden" id="changeType"/>
       							 <input type="hidden" id="showBaseType"/>
       							
       							 <input type="hidden" id="selectDIvId"/>
        					</div>
        				</div>
                        <div class="col-md-4">
        					<div class="input-group">
       							 <div class="input-group-btn">
       							 	<h5 class="h5-margin">资源库类型:</h5>
       							 <!--   <button class="btn" type="button"></button> -->
       							 </div>
       							 <select name="selecter_basic" tabindex="-1"class="form-control" id="materialType" name="materialType">
                                    <option value="1">个人</option>
                                    <option value="0">共享</option>
                                 </select>
        					</div>
        				</div>
       					<a class="btn btn-info" href="#" onclick="showBase();">查询</a>
       				 </div>
                    <div class="table-responsive panel-table-body ">
                        <table class="table table-striped table-hover "  id="showBase">
                            <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>资源库名称</th>
                                    <th>资源库类别</th>
                                    <th>说明</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                               
                            </tbody>
                        </table>
                    </div> 
                    <footer class="panel-footer text-right bg-light lter">
                    <button type="button" class="btn btn-success "  data-toggle="modal" data-target="#add">新增</button>
                    <button type="button" class="btn btn-warning "  onclick="childClose();">返回</button>
                    </footer>
        		</div>
       		 </div>
        </div>
    </div> 
</div>
<!--新增弹出层-->
<div class="modal fade" id="add" role="dialog" aria-labelledby="gridSystemModalLabel">
	<div class="modal-dialog s-tanchu-box" role="document"> 
		
        <div class="container-fluid" >
        <form class="form-horizontal" role="form" id="createBase">
            <div class="row-fluid">
                <!-- col-sm-12 -->
                <div class="col-sm-12 ">
                    <div class="panel panel-default article-bj">
                        <div class="panel-heading box-shodm">
                        新增资源库
                        </div>
                            <div class="row wrapper form-margin">
                                 <div class="col-md-12">
                                    <div class="input-group">
                                         <div class="input-group-btn">
                                            <h5 class="h5-margin">资源库名称:</h5>
                                         <!--   <button class="btn" type="button"></button> -->
                                         </div>
                                         <input type="text" placeholder="" class=" form-control validate[required]"  id="materialName1" name="materialName1" />
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="input-group">
                                         <div class="input-group-btn">
                                            <h5 class="h5-margin">资源库类别:</h5>
                                         <!--   <button class="btn" type="button"></button> -->
                                         </div>
                                         <select name="selecter_basic" tabindex="-1"class="form-control">
                                            <option value="1">个人</option>
                                            
                                         </select>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="input-group">
                                         <div class="input-group-btn">
                                            <h5 class="h5-margin">资源库说明:</h5>
                                         <!--   <button class="btn" type="button"></button> -->
                                         </div>
                                         <textarea style="width:100%" id="remark1" name="remark1"></textarea>
                                    </div>
                                </div>
                            </div>
                            <footer class="panel-footer text-right bg-light lter">
                            <button type="button" class="btn btn-success " onclick="addBase();" >保存</button>
                    		<button type="button" class="btn btn-warning " onclick="closeBaseAdd();">返回</button>
                            </footer>
                        </div>
                     </div>        	
                </div>
            </div> 
              </form>
        </div>
      
	</div>
</div>
<!--新增弹出层结束-->
<!--资源库目录-->
<div class="modal fade" id="opendiv" role="dialog" aria-labelledby="gridSystemModalLabel">
	<div class="modal-dialog tanchu-box" role="document" style="width:100%"> 
        <div class="container-fluid"  >
            <div class="row-fluid" >
                <!-- col-sm-12 -->
                <div class="col-sm-12 ">
                    <div class="panel panel-default article-bj">
                        <div class="panel-heading box-shodm">
                        资源库信息
                        </div>
                        
                        <div class="panel-body">
                           <!---------------------------------------nav start<ul id="tree" class="ztree"><ul id="tree1" class="ztree"></ul>--------------------------------------------------------------->
                            <div id="nav" style=" float:left;width:14%">
                                <div id="tree" style="float:left;  border:none; width:100%;"></div>
                                
                                <div id="unSee" style="margin-top:13px; margin-left:8px;">
                                <a class="btn btn-success btn-sm" href="#" data-toggle="modal" data-target="#addCatalogue">新增</a>
                                <a class="btn btn-info btn-sm" href="#" data-toggle="modal" data-target="#editCatalogue">修改</a>
                                <a class="btn btn-danger btn-sm" href="#" data-toggle="modal" data-target="#deleteCatalogue">删除</a>
                                </div>
                            </div>
                            <!---------------------------------------nav end--------------------------------------------------------------->
                            <!--商品信息-->
                            <div class="container-fluid" style="float:left; width:85%"  id="imgShow">
                            	<input type="hidden" id="showDivId"   />
                                <div class="row-fluid">
                                    <div class="col-sm-12 ">
                                        <div class="panel panel-default ">
                                            <div class="panel-heading">
                                                商品信息
                                            </div>
                                            <div class="row wrapper form-margin">
                                                <div class="col-md-4">
                                                    <div class="input-group">
                                                         <div class="input-group-btn">
                                                            <h5 class="h5-margin">资源名称:</h5>
                                                         <!--   <button class="btn" type="button"></button> -->
                                                         </div>
                                                         <input type="text" placeholder="" class="form-control" name="malName" id="malName">
                                                    </div>
                                                </div>
                                                <div class="col-md-4">
                                                    <div class="input-group">
                                                         <div class="input-group-btn">
                                                            <h5 class="h5-margin">资源类型:</h5>
                                                         <!--   <button class="btn" type="button"></button> -->
                                                         </div>
                                                         <select name="selecter_basic" tabindex="-1"class="form-control" id="malType" name="malType">
                                                            <option value="1">图片</option>
                                                            <option value="2">文字</option>
                                                         </select>
                                                    </div>
                                                </div>
                                                <a class="btn btn-info" href="#" onclick="showMal();">查询</a>
                                                <!-- <div id="fileQueue"></div>
												    <input type="file" name="uploadify" id="uploadify" />
												   
												      <a class="btn btn-info" href="javascript:$('#uploadify').uploadifyUpload()">上传</a>| 
												      <a class="btn btn-info" href="javascript:$('#uploadify').uploadifyClearQueue()">取消上传</a>-->
                                                 
											     <a class="btn btn-info" href="#" data-toggle="modal" data-target="#upload">上传</a>	    
                                            </div>
                                            <div class="table-responsive panel-table-body ">
                                                <table class="table table-striped table-hover" id="showMalTable">
                                                    <thead>
                                                        <tr>
                                                            <th>资源序号</th>
                                                            <th>缩略图</th>
                                                            <th>资源名称</th>
                                                            <th>资源类型</th>
                                                            <th>操作</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        
                                                    </tbody>
                                                </table>
                                            </div> 
                                            
                                            <footer class="panel-footer text-right bg-light lter">         
							            		<div id="callBackPager" class="text-center"></div>    
                                            </footer>
                                            
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--商品信息结束-->
                        </div>
                    </div>
                </div>
            </div>
        </div>
	</div>
</div>
<!--资源库目录-->
<!--新增目录弹出层开始-->
<div class="modal fade" id="addCatalogue" role="dialog" aria-labelledby="gridSystemModalLabel">
	<div class="modal-dialog xs-tanchu-box" role="document"> 
        <div class="container-fluid" >
            <div class="row-fluid">
                <!-- col-sm-12 -->
                <div class="col-sm-12 ">
                    <div class="panel panel-default article-bj">
                        <div class="panel-heading ">
                        新增目录
                        </div>
                            <div class="row wrapper form-margin">
                                 <div class="col-md-12">
                                    <div class="input-group">
                                         <div class="input-group-btn">
                                            <h5 class="h5-margin">图片目录名称:</h5>
                                         <!--   <button class="btn" type="button"></button> -->
                                         </div>
                                         <input type="text" placeholder="" class="form-control" name="addDivName" id="addDivName">
                                    </div>
                                </div>
                            </div>
                            <footer class="panel-footer text-right bg-light lter">
                            <button type="button" class="btn btn-success btn-sm" onclick="addDiv();" >保存</button>
                    		<button type="button" class="btn btn-warning btn-sm" onclick="addDivClose();">返回</button>
                            </footer>
                        </div>
                     </div>        	
                </div>
            </div> 
        </div>
	</div>
</div>
<!--新增目录弹出层结束-->
<!--修改目录弹出层开始-->
<div class="modal fade" id="editCatalogue" role="dialog" aria-labelledby="gridSystemModalLabel">
	<div class="modal-dialog xs-tanchu-box" role="document"> 
        <div class="container-fluid" >
            <div class="row-fluid">
                <!-- col-sm-12 -->
                <div class="col-sm-12 ">
                    <div class="panel panel-default article-bj">
                        <div class="panel-heading ">
                        修改目录
                        </div>
                            <div class="row wrapper form-margin">
                                 <div class="col-md-12">
                                    <div class="input-group">
                                         <div class="input-group-btn">
                                            <h5 class="h5-margin">图片目录名称:</h5>
                                         <!--   <button class="btn" type="button"></button> -->
                                         </div>
                                         <input type="text" placeholder="" class="form-control" name="editDivName" id="editDivName">
                                    </div>
                                </div>
                            </div>
                            <footer class="panel-footer text-right bg-light lter">
                            <button type="button" class="btn btn-success btn-sm" onclick="updateDiv();" >修改</button>
                    		<button type="button" class="btn btn-warning btn-sm" data-dismiss="modal">返回</button>
                            </footer>
                        </div>
                     </div>        	
                </div>
            </div> 
        </div>
	</div>
</div>
<!--修改目录弹出层结束-->
<!--删除目录弹出层开始-->
<div class="modal fade" id="deleteCatalogue" role="dialog" aria-labelledby="gridSystemModalLabel">
	<div class="modal-dialog xs-tanchu-box" role="document"> 
        <div class="container-fluid" >
            <div class="row-fluid">
                <!-- col-sm-12 -->
                <div class="col-sm-12 ">
                    <div class="panel panel-default article-bj">
                        <div class="panel-heading ">
                        删除目录
                        </div>
                            <div class="row wrapper form-margin">
                                 <div class="col-md-12">
                                    <div class="input-group">
                                         <div class="input-group-btn">
                                            <h5 >确定要删除该目录吗？</h5>
                                         <!--   <button class="btn" type="button"></button> -->
                                         </div>
                                    </div>
                                </div>
                            </div>
                            <footer class="panel-footer text-right bg-light lter">
                            <button type="button" class="btn btn-success btn-sm" onclick="delDiv();" >确定</button>
                    		<button type="button" class="btn btn-warning btn-sm" data-dismiss="modal">取消</button>
                            </footer>
                        </div>
                     </div>        	
                </div>
            </div> 
        </div>
	</div>
</div>
<!--删除目录弹出层结束-->
<!--上传弹出层开始-->

<div class="modal fade" id="upload" role="dialog" aria-labelledby="gridSystemModalLabel">
	<div class="modal-dialog s-tanchu-box" role="document"> 
        <div class="container-fluid" >
            <div class="row-fluid">
                <!-- col-sm-12 -->
                <div class="col-sm-12 ">
                    <div class="panel panel-default article-bj">
                        <div class="panel-heading box-shodm">
                        资源上传
                        </div>
                            <div class="row wrapper form-margin">
                                <div class="col-md-6">
                                    <div class="input-group">
                                         <div class="input-group-btn">
                                            <h5 class="h5-margin">描述:</h5>
                                         <!--   <button class="btn" type="button"></button> -->
                                         </div>
                                         <input type="text" placeholder="" class="form-control" name="imgdes" id="imgdes">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="input-group">
                                         <div class="input-group-btn">
                                            <h5 class="h5-margin">资源类型:</h5>
                                         <!--   <button class="btn" type="button"></button> -->
                                         </div>
                                         <select class="form-control" id="imgType">
                                            <option value="1">图片</option>
                                            <option value="2">视频</option>
                                         </select> 
                                    </div>
                            	</div>
                            	<div id="content" class="col-md-12">
								<form enctype="multipart/form-data" role="form">
									<div style="padding-top:10px; padding-left:40px;">
										<span id="spanButtonPlaceholder"></span>
										<button id="btnUpload" type="button"  class="btn btn-success btn-sm"
											onclick="startUploadFile();" >上传</button>
										<button id="btnCancel" type="button" class="btn btn-warning btn-sm"
											onclick="cancelUpload();" disabled="disabled">取消所有上传</button>
									</div>
								</form>
								<div id="divFileProgressContainer"></div>
								<div id="thumbnails">
									<table id="infoTable" border="0" width="530" style="display: inline; border: solid 1px #7FAAFF; background-color: #C5D9FF; padding: 2px;margin-top:8px;">
									</table>
								</div>
							</div>
                     </div>        	
                </div>
            </div> 
        </div>
	</div>
		
</div>
<!--上传弹出层结束-->
</body>
</html>
