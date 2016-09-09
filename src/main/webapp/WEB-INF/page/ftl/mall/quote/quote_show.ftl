<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />   <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>报价管理</title>
<link href="${ctx}/mall/css/pulic.css" rel="stylesheet" type="text/css">
<link href="${ctx}/mall/css/theme.css" rel="stylesheet" type="text/css">
<link href="${ctx}/mall/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${ctx}/mall/css/SimpleTree.css" rel="stylesheet" type="text/css">
<link href="${ctx}/mall/css/theme.css" rel="stylesheet">

<script src="${ctx}/mall/js/jquery.js"></script>
<script src="${ctx}/mall/js/jquery.min.js"></script>
<script src="${ctx}/mall/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="${ctx}/mall/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/mall/js/SimpleTree.js" type="text/javascript"></script>
<script src="${ctx}/mall/js/bootstrap-treeview.js"></script>

<script type="text/javascript" src="${ctx}/mall/js/extendPagination1.js"></script>
 <script src="${ctx}/common/js/common.js"></script>
<!-- 日期控件开始 -->

<link href="${ctx}/mall/css/bootstrap-datetimepicker.css" rel="stylesheet">
<script src="${ctx}/mall/js/moment-with-locales.js"></script>
<script src="${ctx}/mall/js/bootstrap-datetimepicker.js"></script>
<link href="${ctx}/mall/css/bootstrap-table.css" rel="stylesheet">

<!-- 日期控件结束 -->
<!-- 校验开始 -->

<script src="${ctx}/mall/js/serializeJson.js"></script>
<script type="text/javascript" src="${ctx}/common/jqueryValidation/js/jquery.validationEngine-zh_CN.js"></script>
	<script type="text/javascript" src="${ctx}/common/jqueryValidation/js/jquery.validationEngine.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/common/jqueryValidation/css/validationEngine.jquery.css" />
<!-- 校验结束 -->
<style>
.form-group label{text-align:right;}
.modal-content p{ font-size:16px; line-height:60px; text-align:center;}

</style>
<script type="text/javascript">
	//日期控件
	//查询发布时间
	$(function() {
			$("#createTime1").datetimepicker({
				locale: moment.locale('zh-cn'),
				showTodayButton: true,
				dayViewHeaderFormat: 'YYYY MM',
				format: 'YYYY-MM-DD'
			});
	        $("#createTime2").datetimepicker({
	        	locale: moment.locale('zh-cn'),
				showTodayButton: true,
				dayViewHeaderFormat: 'YYYY MM',
				format: 'YYYY-MM-DD'
	        });
	       
	        $("#createTime1").on("dp.change",function (e) {
	            $('#createTime2').data("DateTimePicker").minDate(e.date);
	        });
	        $("#createTime2").on("dp.change",function (e) {
	            $('#createTime1').data("DateTimePicker").maxDate(e.date);
	        });
			//查询有效期
			$("#startTime").datetimepicker({
				locale: moment.locale('zh-cn'),
				showTodayButton: true,
				dayViewHeaderFormat: 'YYYY MM',
				format: 'YYYY-MM-DD'
			});
	        $("#startEnd").datetimepicker({
	        	locale: moment.locale('zh-cn'),
				showTodayButton: true,
				dayViewHeaderFormat: 'YYYY MM',
				format: 'YYYY-MM-DD'
	        });
	        
	        $("#startTime").on("dp.change",function (e) {
	            $('#startEnd').data("DateTimePicker").minDate(e.date);
	        });
	        $("#startEnd").on("dp.change",function (e) {
	            $('#startTime').data("DateTimePicker").maxDate(e.date);
	        });
	        //编辑报价 有效期
	        $("#startTime2").datetimepicker({
				locale: moment.locale('zh-cn'),
				showTodayButton: true,
				dayViewHeaderFormat: 'YYYY MM',
				format: 'YYYY-MM-DD'
			});
	        $("#startEnd2").datetimepicker({
	        	locale: moment.locale('zh-cn'),
				showTodayButton: true,
				dayViewHeaderFormat: 'YYYY MM',
				format: 'YYYY-MM-DD'
	        });
	        
	        $("#startTime2").on("dp.change",function (e) {
	            $('#startEnd2').data("DateTimePicker").minDate(e.date);
	        });
	        $("#startEnd2").on("dp.change",function (e) {
	            $('#startTime2').data("DateTimePicker").maxDate(e.date);
	        });
	        //创建报价  有效期
	        $("#startTime1").datetimepicker({
				locale: moment.locale('zh-cn'),
				showTodayButton: true,
				dayViewHeaderFormat: 'YYYY MM',
				format: 'YYYY-MM-DD'
			});
	        $("#startEnd1").datetimepicker({
	        	locale: moment.locale('zh-cn'),
				showTodayButton: true,
				dayViewHeaderFormat: 'YYYY MM',
				format: 'YYYY-MM-DD'
	        });
	       
	        $("#startTime1").on("dp.change",function (e) {
	            $('#startEnd1').data("DateTimePicker").minDate(e.date);
	        });
	        $("#startEnd1").on("dp.change",function (e) {
	            $('#startTime1').data("DateTimePicker").maxDate(e.date);
	        });
	        //新增数量
	        $("#num1").on("input propertychange", function() {
				$(this).validateNumber();
				
			});
			//新增价格
			 $("#minPrice1").on("input propertychange", function() {
				$(this).validateAmount();
				
			});
			 $("#maxPrice1").on("input propertychange", function() {
				$(this).validateAmount();
				
			});
			//编辑
			$("#num2").on("input propertychange", function() {
				$(this).validateNumber();
				
			});
			//新增价格
			 $("#minPrice2").on("input propertychange", function() {
				$(this).validateAmount();
				
			});
			 $("#maxPrice2").on("input propertychange", function() {
				$(this).validateAmount();
				
			});
			//校验     
			 $("#updateQuote").validationEngine('attach',{
					scroll:false,
					autoHidePrompt:true,
					autoHideDelay:2500,
					promptPosition : "bottomLeft"
			 });
			 $("#createQuote").validationEngine('attach',{
					scroll:false,
					autoHidePrompt:true,
					autoHideDelay:2500,
					promptPosition : "bottomLeft"
			 });
			 
			 //默认查询
			 showQuote();
			
		});
	

$(document).ready(function(e) {
	$('#form-group_in1').click(function(){
		$('#form-group_in2').removeClass('active')
		$('#form-group_in1').addClass('active')
		$('#form_con2').hide()
		$('#form_con1').show()
		})
	$('#form-group_in2').click(function(){
		$('#form-group_in1').removeClass('active')
		$('#form-group_in2').addClass('active')
		$('#form_con1').hide()
		$('#form_con2').show()
		})	
});


	function aa(){
		$("#xuanzehezuohuiyuan").modal('show');
		showMmb();
	}
	
	function bb(){
		$("#xuanzehezuohuiyuan").modal('hide');	
	}
	function cc(){
		$('#xuanzequnzu').modal('hide');
	}
	function dd(){
		$('#xuanzequnzu').modal('show');
		showGroup();
	}
	
	//**********************************************************************************
	//  嵌入资料库相关方法
	
	function childCloseImg(){
		//关闭目录与资料路内容div
		//childBase.$("#treeview1").hide();
		childBase.$("#resourceInfo").hide();
			
	}
	//新增页面点击上传按钮
	function addImg(){
		//给子页面getType赋值  表示为报价新增页面
		childBase.$("#getType").val("3");
		//给子页面赋值  判断是添加标题图片还是添加轮播图  1标题图片  2轮播图
	   	childBase.$("#changeType").val("1");
		//alert(childBase.$("#getType").val()); 
		$("#childPage").modal('show');
	}
	//新增页面删除按钮  titlePic1
	function delImg(){
		//清除
		$("#titlePic1").val("");
		$("#imgPath1").val("");
		$("#addQuoteImg").attr("src","");
	}
	//修改页面点击上传按钮
	function  updateImg(){
		//给子页面getType赋值  表示为报价新增页面 
		childBase.$("#getType").val("4");
		//给子页面赋值  判断是添加标题图片还是添加轮播图  1标题图片  2轮播图
	   	childBase.$("#changeType").val("1");
		
		$("#childPage").modal('show');
	};
	//修改页面删除按钮  titlePic2
	function delUpdateImg(){
		//清除
		$("#titlePic2").val("");
		$("#imgPath2").val("");
		$("#updateQuoteImg").attr("src","");
	};
	
	//******************************************************************
	//打开创建报价页面
	function openAddQuote(){
		//alert("打开");
		$("#chuangjianbaojia").modal('show');
	};
	
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
	 
	 	//选择商品  确认按钮
	 	function xuanGood(){
	 	
	 		
	 		//获取选中的多选框按钮的值
	 		
	  		var checkedGroups = $("#showGoods tr td [name =goodCheckbox]:checked");
			var stringsId = "";
			checkedGroups.each(function(i,thisElement){
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
			//赋值
			$("#goodsId1").val(ids[0]);
			//alert(ids[0]);
			$("#goodsName1").val(ids[1]);
	 		$('#xuanzeshangpin2').modal('hide');
	 		
	 	}
		//多选框实现单选
		function checkRedio(obj){
			if($(obj).prop('checked')==true){
				$("#showGoods tr td [name =goodCheckbox]:checkbox").removeAttr('checked');
				$(obj).prop('checked','true');
			}	
		}
		
		
	
	 //查询商品
	function showGood(categoryId){
		//alert("查询");
		$.ajax({
				url : '${ctx}/GoodController/serachGood.do',// 跳转到 action
				data : {
							categoryId:categoryId,
							goodStatus:0,
							pageNo : 1,
							pageSize : 3
				},
				type : 'POST',
				cache : false,
				dataType : 'json',
				success : function(data) {
					data = eval(data);
					//先清空table中的数据
					$("#showGoods  tr:not(:first)").remove();
					if(data.mtList.length>0){
						for ( var i = 0; i < data.mtList.length; i++) {
						
							var content = '';
							var id = data.mtList[i].goodsId+","+data.mtList[i].name;
							content += '<tr>';
							content +="<td><input type='checkbox' name='goodCheckbox' value='"+id+"' onclick='checkRedio(this);' /></td>";
							content += "<td >"+data.mtList[i].name+"</td>";
							content += "<td>"+data.mtList[i].stockNum+"</td>";
							content += "<td>"+data.mtList[i].minPrice+"--"+data.mtList[i].maxPrice+"</td>";
							content += '</tr>';
							addTr('showGoods', -1, content);
							
						}
						//alert("数据条数="+data.mtCount);
						setPagination5(1, 3, data.mtCount,categoryId);
					}else{
						//alert("没有搜索的结果");
					}
					
				},
				error : function() {
					alert("异常！");
				}
		});		
	}
	function setPagination5(curr, limit, totalCount,categoryId) {
			//alert("分页");
			$('#callBackPager5').extendPagination({
				totalCount : totalCount,
				showCount : limit,
				limit : limit,
				callback : function(curr, limit, totalCount) {
					
					$.ajax({
						url : '${ctx}/GoodController/serachGood.do',// 跳转到 action
						data : {
									categoryId:categoryId,
									goodStatus:0,
									pageNo : curr,
									pageSize : limit
						},
						type : 'POST',
						cache : false,
						dataType : 'json',
						success : function(data) {
							data = eval(data);
							//先清空table中的数据
									$("#showGoods  tr:not(:first)").remove();
									if(data.mtList.length>0){
										for ( var i = 0; i < data.mtList.length; i++) {
						
											var content = '';
											var id = data.mtList[i].goodsId+","+data.mtList[i].name;
											content += '<tr>';
											content +="<td><input type='checkbox' name='goodCheckbox' value='"+id+"' onclick='checkRedio(this);' /></td>";
											content += "<td >"+data.mtList[i].name+"</td>";
											content += "<td>"+data.mtList[i].stockNum+"</td>";
											content += "<td>"+data.mtList[i].minPrice+"--"+data.mtList[i].maxPrice+"</td>";
											content += '</tr>';
											addTr('showGoods', -1, content);
											
										}	
											//alert("赋值");
									}else{
											//alert("无结果");
									}
						},
						error : function() {
							alert("异常！");
						}
				});
				}
			});
	}
	//显示商品数以及商品
	function getUser(){
		//用户品类树
		$.ajax({
					url : '${ctx}/GoodController/getUserCategory.do',// 跳转到 action
					type : 'POST',
					cache : false,
					dataType : 'json',
					success : function(data) {
						//alert(data.length);
						if(data!=""&&data.length>0){
							$('#st_tree').treeview({
							  color: "#428bca",
							  enableLinks: true,
							  data: data,
							  showBorder: false,
							  expandIcon: 'glyphicon glyphicon-chevron-right',
							  collapseIcon: 'glyphicon glyphicon-chevron-down',
							  //nodeIcon: 'glyphicon glyphicon-file'
							});
							//点击事件
							 $('#st_tree').on('nodeSelected', function(event, data) {
          							if(data.nodes==null||data.nodes==""){
          								//alert(data.id);
          								//赋值
          								$("#categoryId1").val(data.id);
          								//查询
          								showGood(data.id);
          							}
        					});
							
						}
					},
					error : function() {
						alert("异常！");
					}
		});
	}
	  /**
	   * 全选
	   * allCkb 全选复选框的id
	   * items 复选框的name
	   */
	  function allCheck(allCkb, items){
	   $("#"+allCkb).click(function(){
	      $('[name='+items+']:checkbox').attr("checked", this.checked );
	   });
	  }
	  //删除按钮
	  function deleteScopeId(){
	  		var quoteId = $("#showQuoteId").val();
	  		//判断是否选中
	  		var boolCheck=$('input:radio[name="danxuan"]').is(":checked");
	  		if(boolCheck==false){
	  			alert("请先选择删除的对象");
	  			return false;
	  		}
	  		//获取单选按钮选中的值
	  		var scopeId = $('input[name="danxuan"]:checked').val();
	  		//alert(scopeId);
	  		//删除关系
			$.ajax({
				url : '${ctx}/QuoteController/deleteScopeId.do',// 跳转到 action
				data : {
							scopeId : scopeId,
							quoteId : quoteId,

				},
				type : 'POST',
				cache : false,
				dataType : 'json',
				success : function(data) {
					if(data=="0"){
						//删除成功
						alert("删除成功");
						//重新加载
						openRang();
					}else{
						//删除失败
						alert("删除失败");
					}
				},
				
			});	
	  }
	 
	 //全选反选群组      name='groupCheckbox'   groupTable
	  function checkAllGroups(obj){
		
		$("#groupTable tr th [name =groupCheckbox]:checkbox").each(function(i,thisElement){
			if($(obj).prop("checked")==true){
		      $(thisElement).prop("checked",'true');
			}else{
				$(thisElement).prop("checked",false);
			}
		});
		
	}
	//群组页面 确认按钮
	
	  function  subGroup(){
	  		//获取选中的多选框按钮的值
	  		var checkedGroups = $("#groupTable tr th [name =groupCheckbox]:checked");
			var stringsId = "";
			checkedGroups.each(function(i,thisElement){
				if(thisElement.value!=""){
					stringsId+=thisElement.value+",";
				}
			});
			//去掉最后一个逗号
			stringsId = stringsId.substring(0,stringsId.length-1);
			//alert("======="+stringsId);
			if(stringsId.length==0){
				alert("请先选择会员！");
				return false;
			}
			//var rangType =$("#showRangType").val();
			//if(rangType!=1){
				//if(rangType==0){
					//var a = confirm("是否将此公开报价改为指定报价");
					//if(a==false){
					 //  return false;
					//}
				//else{
					//alert("此报价将发布为指定报价");
				//}
			//}
			//添加关系
			$.ajax({
				url : '${ctx}/QuoteController/addGroupIds.do',// 跳转到 action
				data : {
							groupIds : stringsId,
							quoteId : $("#showQuoteId").val(),
							rangType:"1",
				},
				type : 'POST',
				cache : false,
				dataType : 'json',
				success : function(data) {
					data = eval(data);
					if(data!=null&&data!=""){
						if(data.successMsg!=null){
							alert(data.successMsg);
							//修改成功之后  将rangType赋值为1
							 //$("#showRangType").val(1);
							 $("#xuanzequnzu").modal('hide');
							 //点击指定报价页面
							 openRang();
						}else{
							alert(data.errorMsg);
						}
					}
				},
				
			});	
			
			
	  }
	   //全选反选会员      name='mmbCheckbox'   mmbTable
	  function checkAllMmbs(obj){
		
		$("#mmbTable tr th [name =mmbCheckbox]:checkbox").each(function(i,thisElement){
			if($(obj).prop("checked")==true){
		      $(thisElement).prop("checked",'true');
			}else{
				$(thisElement).prop("checked",false);
			}
		});
	}
	  //会员页面 确定按钮
	  function  subMmb(){
	  		//获取选中的多选框按钮的值
	  		var checkedMmbs = $("#mmbTable tr th [name =mmbCheckbox]:checked");
			
			var stringsId = "";
			checkedMmbs.each(function(i,thisElement){
				if(thisElement.value!=""){
					stringsId+=thisElement.value+",";
				}
			});
			//去掉最后一个逗号
			stringsId = stringsId.substring(0,stringsId.length-1);
			//alert("======="+stringsId);
			if(stringsId.length==0){
				alert("请先选择会员！");
				return false;
			}
			//var rangType =$("#showRangType").val();
			//if(rangType!=1){
			//	if(rangType==0){
			//		var a = confirm("是否将此公开报价改为指定报价");
			//		if(a==false){
			//		   return false;
			//		}
			//	}else{
			//		alert("此报价将发布为指定报价");
			//	}		
			//}
			//添加关系
			$.ajax({
				url : '${ctx}/QuoteController/addMmbIds.do',// 跳转到 action
				data : {
							mmbIds : stringsId,
							quoteId : $("#showQuoteId").val(),
							rangType:"1",
				},
				type : 'POST',
				cache : false,
				dataType : 'json',
				success : function(data) {
					data = eval(data);
					if(data!=null&&data!=""){
						if(data.successMsg!=null){
							alert(data.successMsg);
							//修改成功之后  将rangType赋值为1
							 //$("#showRangType").val(1);
							 $("#xuanzehezuohuiyuan").modal('hide');
							 //点击指定报价页面
							 openRang();
						}else{
							alert(data.errorMsg);
						}
					}
				},
				
			});	
			
			
	  }
	  //修改状态方法
	  function updateRangTypeByQuoteId(quoteId,rangType){
	  	if(quoteId!=""){
			
			$.ajax({
				url:'${ctx}/QuoteController/updateRangTypeByQuoteId.do',
				type: "POST",
	            dataType: "json",
				data : {
							rangType : rangType,
							quoteId :quoteId,
							
				},
				success:function(data){
					data = eval(data);
				   	 //如果成功
				   	 if(data== "0"){
				   	 	//提示信息
				   	 	alert("发布报价成功！");
				   	 	//修改成功之后  将rangType赋值为0
						 $("#showRangType").val(0);
				   	 	//关闭模块
						$("#myModal").modal('hide');
						$("#searchButt").click();
				   	 }else{
				   	 	 //失败
				   	 	alert("发布失败！");
				   	 }
				}	
				});
	    }else{
	    	alert("未选择报价");
	    }
	  }
	  //公开确定按钮
	  function subPub(){
	  		var rangType =$("#showRangType").val();
	  		var quoteId = $("#showQuoteId").val();
	  		if(rangType=="1"){
	  			//修改报价，将rangType改为0
	  			var a = confirm("是否将此指定指定报价改为公开报价");
	  			if(a==true){
	  			 	updateRangTypeByQuoteId(quoteId,"0")
	  			}
	  		}else if(rangType=="0"){
	  		}else{
	  			//修改报价，将rangType改为0
	  			var a = confirm("此报价将会发布为公开报价");
	  			if(a==true){
	  			 	updateRangTypeByQuoteId(quoteId,"0");
	  			}
	  		}
	  		
	  		$("#myModal").modal('hide');
	  		showQuote();
	  }
	   //指定 确定按钮
	  function subPub1(){
	  		var rangType =$("#showRangType").val();
	  		var quoteId = $("#showQuoteId").val();
	  		if(rangType=="0"){
	  			//修改报价，将rangType改为0
	  			var a = confirm("是否将此公开指定报价改为指定报价");
	  			if(a==true){
	  			 	updateRangTypeByQuoteId(quoteId,"1")
	  			}
	  		}else if(rangType=="1"){
	  		}else {
	  			var a = confirm("此报价将会发布为指定报价");
	  			if(a==true){
	  			 	updateRangTypeByQuoteId(quoteId,"1");
	  			}
	  		}
	  		
	  		$("#myModal").modal('hide');
	  		showQuote();
	  }
	  
	  //查询群组 通过会员Id，以及报价的type，查询出与会员相关的群组
	  function showGroup(){
		//alert("查询群组");
		$.ajax({
				url : '${ctx}/QuoteController/showGroup.do',// 跳转到 action
				data : {	
							groupName:$("#groupName").val(),
							mmbId : $("#showMmbId").val(),
							type : $("#showType").val(),
							pageNo : 1,
							pageSize : 2,
							
				},
				type : 'POST',
				cache : false,
				dataType : 'json',
				success : function(data) {
					data = eval(data);
					//先清空table中的数据
					$("#groupTable  tr:not(:first)").remove();
					var nn = $("#showType").val();
					//alert(nn);
					if(data!=null&&data!=""&&data.groupList.length>0){
						for ( var i = 0; i < data.groupList.length; i++) {
							 var content = '';
							 var n = i+1;
							 content += "<tr>";
							 content += "<th width='10%' style='padding-left:25px;'><input type='checkbox' name='groupCheckbox' value='"+data.groupList[i].id+"'></input></th>";
							 content +="<th width='10%'>"+n+"</th>";
							 content +="<th width='10%'>"+data.groupList[i].groupName+"</th>"
							if(nn==0){
								 content +="<th width='10%'>卖家</th>"
							}else{
								 content +="<th width='10%'>买家</th>"
							}
							content += '</tr>';
							addTr('groupTable', -1, content);
							
						}
						//alert("数据条数="+data.groupCount);
						setPagination2(1, 2, data.groupCount);
					}else{
						//alert("无数据！");
					}
					
				},
				error : function() {
					alert("异常！");
				}
		});		
	}
	function setPagination2(curr, limit, totalCount) {
			
			$('#callBackPager2').extendPagination({
				totalCount : totalCount,
				showCount : limit,
				limit : limit,
				callback : function(curr, limit, totalCount) {	
					$.ajax({
						url : '${ctx}/QuoteController/showGroup.do',// 跳转到 action
						data : {
							groupName:$("#groupName").val(),
							mmbId : $("#showMmbId").val(),
							type : $("#showType").val(),
							pageNo : curr,
							pageSize : limit
						},
						type : 'POST',
						cache : false,
						dataType : 'json',
						success : function(data) {
							data = eval(data);
							//先清空table中的数据
							$("#groupTable  tr:not(:first)").remove();
							var nn = $("#showType").val();
							if(data!=null&data!=""&&data.groupList.length>0){
								for ( var i = 0; i < data.groupList.length; i++) {
							
									var content = '';
									var n = i+1;
									 content += "<tr>";
									 content += "<th width='10%' style='padding-left:25px;'><input type='checkbox' name='groupCheckbox' value='"+data.groupList[i].id+"'></input></th>";
									 content +="<th width='10%'>"+n+"</th>";
									 content +="<th width='10%'>"+data.groupList[i].groupName+"</th>"
									if(nn==0){
										 content +="<th width='10%'>卖家</th>"
									}else{
										 content +="<th width='10%'>买家</th>"
									}
									content += '</tr>';
									addTr('groupTable', -1, content);
									
								}
								//alert("数据条数="+data.groupCount);
							}else{
										//alert("无数据！");
							}
					},
					error : function() {
						alert("异常！");
					}
				});
				}
			});
		}
	  //通过会员Id，以及报价的type，查询出与会员相关的会员  查询会员
	  function showMmb(){
		//alert("查询会员");
		$.ajax({
				url : '${ctx}/QuoteController/showMmb.do',// 跳转到 action
				data : {	
							mmbSname:$("#mmbSname").val(),
							mmbId : $("#showMmbId").val(),
							type : $("#showType").val(),
							pageNo : 1,
							pageSize : 2
				},
				type : 'POST',
				cache : false,
				dataType : 'json',
				success : function(data) {
					data = eval(data);
					//先清空table中的数据
					$("#mmbTable  tr:not(:first)").remove();
					var nn = $("#showType").val();
					if(data!=null&&data!=""&&data.mmbList.length>0){
						for ( var i = 0; i < data.mmbList.length; i++) {
					
							 var content = '';
							 var n = i+1;
							 content += "<tr>";
							 content += "<th width='10%' style='padding-left:25px;'><input type='checkbox' name='mmbCheckbox' value='"+data.mmbList[i].id+"'></input></th>";
							 content +="<th width='10%'>"+n+"</th>";
							 content +="<th width='10%'>"+data.mmbList[i].mmbSname+"</th>"
							if(nn==0){
								 content +="<th width='10%'>卖家</th>"
							}else{
								 content +="<th width='10%'>买家</th>"
							}
							content += '</tr>';
							addTr('mmbTable', -1, content);
							
						}
						//alert("数据条数="+data.mmbCount);
						setPagination1(1, 2, data.mmbCount);
					}else{
						//alert("无数据！");
					}
					
				},
				error : function() {
					alert("异常！");
				}
		});		
	}
	function setPagination1(curr, limit, totalCount) {
			
			$('#callBackPager1').extendPagination({
				totalCount : totalCount,
				showCount : limit,
				limit : limit,
				callback : function(curr, limit, totalCount) {
					
					$.ajax({
						url : '${ctx}/QuoteController/showMmb.do',// 跳转到 action
						data : {
							mmbSname:$("#mmbSname").val(),
							mmbId : $("#showMmbId").val(),
							type : $("#showType").val(),
							pageNo : curr,
							pageSize : limit
						},
						type : 'POST',
						cache : false,
						dataType : 'json',
						success : function(data) {
						data = eval(data);
							//先清空table中的数据
							$("#mmbTable  tr:not(:first)").remove();
							var nn = $("#showType").val();
							if(data!=null&&data!=""&&data.mmbList.length>0){
								for ( var i = 0; i < data.mmbList.length; i++) {
							
									var content = '';
									var n = i+1;
									 content += "<tr>";
									 
									 content += "<th width='10%' style='padding-left:25px;'><input type='checkbox' name='mmbCheckbox' value='"+data.mmbList[i].id+"'></input></th>";
									 content +="<th width='10%'>"+n+"</th>";
									 content +="<th width='10%'>"+data.mmbList[i].mmbSname+"</th>"
									if(nn==0){
										 content +="<th width='10%'>卖家</th>"
									}else{
										 content +="<th width='10%'>买家</th>"
									}
									content += '</tr>';
									addTr('mmbTable', -1, content);
									
								}
								//alert("数据条数="+data.mmbCount);
							}else{
										//alert("无数据！");
							}
					},
					error : function() {
						alert("异常！");
					}
				});
				}
			});
		}
	
	 //通过报价Id，查询出有报价相关的群组对象
	  function getGroup(){
	  		var quoteId = $("#showQuoteId").val();
	  		var rangType = $("#showRangType").val();
		  	$.ajax({
						url:'${ctx}/QuoteController/getGroup.do',
						type: "POST",
		                dataType: "json",
						data : {
								quoteId :quoteId,	
						},
						success:function(data){
						data = eval(data);
						   	 //如果成功
						   	 if(data!=null||data!=""||data.length>0){
						   	 	var content = ''; 
						   	 	//js动态添加
						   	 	for(var i=0;i<data.length;i++){
									content+="<li><input type='radio' name='danxuan' value='"+data[i].id+"'></input><a href='#' ref='yhgl'>"+data[i].groupName+"</a></li>";
								}
						   	 	
						   	 	//清空内容
								$("#searchGroup").empty();
								$("#searchGroup").append(content);
						   	 }else {
						   	 	//清空内容
								$("#searchGroup").empty();
						   	 	 //未发布范围
						   	 	//alert("未发布范围");
						   	 }
						}
			});
	  }
	 
	  //通过报价Id，查询出有报价相关的合作会员对象
	  function getMmb(){
	  		var quoteId = $("#showQuoteId").val();
	  		var rangType = $("#showRangType").val();
	  			//公开 显示公开报价
	 	   		$('#form_con2').hide();
				$('#form_con1').show();
		  	$.ajax({
						url:'${ctx}/QuoteController/getMmb.do',
						type: "POST",
		                dataType: "json",
						data : {
								quoteId :quoteId,	
						},
						success:function(data){
								data = eval(data);
						   	 //如果成功
						   	 if(data!=null&&data!=""&&data.length>0){
						   	 	var content = ""; 
						   	 	//js动态添加
						   	 	for(var i=0;i<data.length;i++){
									content+="<li><input type='radio' name='danxuan'  value='"+data[i].id+"'></input><a href='#' ref='yhgl'>"+data[i].mmbSname+"</a></li>";
								}
						   	 	//清空内容
								$("#searchMmb").empty();
								$("#searchMmb").append(content);
						   	 }else {
						   	 	//清空内容
								$("#searchMmb").empty();
						   	 	 //未发布范围
						   	 	//alert("未发布范围");
						   	 }
						}
			});
	  }
	  //点击指定按钮
	  function openRang(){
	  			//显示已有的关系树 
	 	   		 getMmb();
	 	   		 getGroup();
	 	   	  	//指定发布范围 显示选择发布范围
	 	   	  	$('#form_con1').hide()
				$('#form_con2').show()
				
	  }
	  //通过quoteId，查找到所属的地域的省份名称   点击公开按钮
	  function getAreaName(){
	  		//指定发布范围 显示公开范围
	 	   	$('#form_con2').hide()
		    $('#form_con1').show()
	        var quoteId = $("#showQuoteId").val();
	        //校验不为空
	        if(quoteId==null&&quoteId==""){
	        	alert("id不存在");
	        	return;
	        }
	     
	       // alert("===="+quoteId);
	  		$.ajax({
				url:'${ctx}/QuoteController/getAreaName.do',
				type: "POST",
                dataType: "json",
				data : {
						quoteId :quoteId,			
				},
				success:function(data){
				   	 //如果成功
				   	 if(data!=null&&data!=""){
				   	 	//alert(data);
				   	 	$('#addAreaName').html("=="+data)
				   	 }else {
				   	 	 //如果失败
				   	 	alert("会员地域不存在");
				   	 }
				}
			});
			
	  }
	   
	 //指定范围 data-toggle='modal' data-target='#myModal'
	 function updateRang(quoteId,mmbId,rangType,type){
	 	   //打开模态框 赋值
	 	   $("#showMmbId").val(mmbId);
	 	   $("#showType").val(type);
	 	   $("#showQuoteId").val(quoteId);
	 	   $("#showRangType").val(rangType);
	 	   //打开模态框
	 	   $("#myModal").modal('show');
	 	   if(rangType=="1"){
	 	   		//打开指定按钮
	 	   		 openRang();
	 	   		 
	 	   }else{
	 	   		//打开公开按钮
	 	   		getAreaName();
	 	   	    
	 	   }
	 	   
	 }
	 //编辑报价
	function updateQuote(){
		//校验新增是否正确
		if(!$("#updateQuote").validationEngine("validate")){
			return;
		}
		if(eval($("#maxPrice2").val())<eval($("#minPrice2").val())){
			alert("请重新核对价格");
			return false;
		}
		
		$.ajax({
				url:'${ctx}/QuoteController/editQuote.do',
				type: "POST",
                dataType: "json",
				data:$('#updateQuote').serialize(),
				success:function(data){
					 
				   	 //alert(data);
				   	 //如果成功
				   	  if(data!=null&&data== "0"){
				   	 	alert("报价编辑成功");
				   	 	//隐藏模态框
				   	 	$("#bianjibaojia").modal('hide');
				   	 	//重新查询
				   	 	$("#searchButt").click();
				   	 }else {
				   	 	 //如果失败
				   	 	alert("报价编辑失败");
				   	 }
				   	
				}
			});
	}
	//进入编辑报价页面
	function toEditQuote(id){
		//alert("编辑报价==");
	    if(id!=""){
	    	//alert(id);
			//暂时省略校验部分，直接提交
			$.ajax({
				url:'${ctx}/QuoteController/toPageEdit.do',
				type: "POST",
	            dataType: "json",
				data:{"quoteId":id},
				success:function(data){
				   	 data = eval(data);
				   	 //如果成功
				   	 if(data!=null){
				   	 	//给编辑框的各个输入框赋值
				   	 	$("#goodsId2").val(data.goodsId);
				   	 	$("#rangType2").val(data.rangType);
				   	 	$("#categoryId2").val(data.categoryId);
				   	 	$("#id2").val(data.id);
				   	 	$("#type3").val(data.type);
				   	 	//alert($.changeDate(data.startTime));
				   	 	$("#startTime2").val($.changeDate(data.startTime));
				   	 	$("#startEnd2").val($.changeDate(data.startEnd));
				   	 	$("#num2").val(data.num);
				   	 	$("#goodsName2").val(data.goodsName);
				   	 	$("#goodsName2").attr("disabled","disabled");
				   	 	//var mm =$("#goodsName2").val();
				   	 	//alert(mm);
				   	 	$("#minPrice2").val(data.minPrice);
				   	 	$("#maxPrice2").val(data.maxPrice);
				   	 	$("#explan2").val(data.explan);
				   	 	//报价状态判断
				   	 	$("#status2").val(data.Status);
				   	 	//报价类型判断
				   	 	$("#type2").children("option").each(function(){  
				              var temp_value = $(this).val();  
				              if(temp_value == data.type){  
				                    $(this).attr("selected","selected");  
				              }  
				         });
				   	 	//价格单位
				   	 	$("#unitPrice2").children("option").each(function(){  
				              var temp_value = $(this).val();  
				              if(temp_value == data.unitPrice){  
				                    $(this).attr("selected","selected");  
				              }  
				        });
				   	 	
				   	 	var num = $("#type2").val();
				   	 	//alert(type);
				   	 	//图片
				   	 	$("#titlePic2").val(data.titlePic);
						$("#imgPath2").val(data.imgPath);
						var path = "${ctx}"+data.imgPath;
						$("#updateQuoteImg").attr("src",path);
				   	 	
				   	 	
				   	 	//alert("打开编辑模态框");
				   	 	//打开编辑模态框
				   	 	$("#bianjibaojia").modal('show');
				   	 }
				   	 else{
				   	 	//如果失败
				   	 	alert("系统错误！");
				   	 }
					}
				});
	    }
	}
	
	//创建报价
	function createQuote(){
		//校验新增是否正确
		if(!$("#createQuote").validationEngine("validate")){
			return;
		}
		if(eval($("#maxPrice1").val())<eval($("#minPrice1").val())){
			alert("请重新核对价格");
			return false;
		}
		
		$.ajax({
				url:'${ctx}/QuoteController/createQuote.do',
				type: "POST",
                dataType: "json",
				data:$('#createQuote').serialize(),
				success:function(data){
				   	 //alert("123");
				   	 //如果成功
				   	 if(data!=null&&data== "0"){
				   	 	alert("报价创建成功");
				   	 	//隐藏模态框
				   	 	$("#chuangjianbaojia").modal('hide');
				   	 	//重新查询
				   	 	$("#searchButt").click();
				   	 }else {
				   	 	 //如果失败
				   	 	alert("报价创建失败");
				   	 }
				}
			});
	}
	//查询报价
	function showQuote(){
		
		$.ajax({
				url : '${ctx}/QuoteController/showQuote.do',// 跳转到 action
				data : {
							goodname : $("#goodname").val(),
							startTime : $("#startTime").val(),
							startEnd : $("#startEnd").val(),
							createTime1 : $("#createTime1").val(),
							createTime2 : $("#createTime2").val(),
							status : $("#status").val(),
							type : $("#type").val(),
							pageNo : 1,
							pageSize : 2
				},
				type : 'POST',
				cache : false,
				dataType : 'json',
				success : function(data) {
					data = eval(data);
					//先清空table中的数据
					$("#showTable  tr:not(:first)").remove();
					if(data.qtList.length>0){
					
						//获取当前系统时间
							var myDate = new Date();
							var dataNow = myDate.toLocaleDateString();     //获取当前日期
							
						for ( var i = 0; i < data.qtList.length; i++) {
					
							var content = '';
							
							content += "<tr style='border-bottom:1px #ddd solid;'>";
							content += "<td >"+data.qtList[i].id+"</td>";
							content += "<td>"+data.qtList[i].goodsName+"</td>";
							content += "<td>"+data.qtList[i].num+"</td>";
							//content += "<td>"+data.qtList[i].mmbName+"</td>";
							content += "<td>"+data.qtList[i].minPrice+"--"+data.qtList[i].maxPrice+"</td>";
							content += "<td>"+data.qtList[i].publishName+"</td>";
							//content += "<td>"+$.changeDate(data.qtList[i].createTime)+"</td>";
							content += "<td>"+$.changeDate(data.qtList[i].startTime)+"--"+$.changeDate(data.qtList[i].startEnd)+"</td>";
							if(data.qtList[i].rangType == 0){
								content += "<td>公开报价</td>";
							}else if(data.qtList[i].rangType == 1){
								content += "<td>指定报价</td>";
							}else{
								content += "<td>未发布</td>";
							};
							content += "<td><input class='btn btn-success'  style='height:25px; line-height:12px; padding:5px 5px' type='button'  value='指定发布范围' onclick=updateRang('"+data.qtList[i].id+"','"+data.qtList[i].mmbId+"','"+data.qtList[i].rangType+"','"+data.qtList[i].type+"')></input>&nbsp;";
							content += "<input class='btn btn-warning'  style=' height:25px; line-height:12px; padding:5px 5px' type='button'  value='编辑' onclick=toEditQuote('"+data.qtList[i].id+"');></input>&nbsp;";
							
							if($.changeDate(dataNow)<$.changeDate(data.qtList[i].startEnd)){
								if (data.qtList[i].status == 0) {
								
									content += "<input class='btn btn-success' style=' height:25px; line-height:12px; padding:5px 5px' type='button' value='禁用' onclick=stopQuote('"+data.qtList[i].id+"');></input></td>";
								
								} else if(data.qtList[i].status == 1) {
									
									content += "<input class='btn btn-success' style=' height:25px; line-height:12px; padding:5px 5px' type='button' value='启用' onclick=startQuote('"+data.qtList[i].id+"');></input></td>";
								} 
							}else{
								content += "<input class='btn btn-success' style=' height:25px; line-height:12px; padding:5px 5px' type='button' value='已过期' ;></input></td>";
								
							}
							content += '</tr>';
							addTr('showTable', -1, content);
							
						}
						//alert("数据条数="+data.qtCount);
					}else{
						//alert("无数据！");
					}
					setPagination(1, 2, data.qtCount);
				},
				error : function() {
					alert("异常！");
				}
		});		
	}
	function setPagination(curr, limit, totalCount) {
			
			$('#callBackPager').extendPagination({
				totalCount : totalCount,
				showCount : limit,
				limit : limit,
				callback : function(curr, limit, totalCount) {
					
					$.ajax({
						url : '${ctx}/QuoteController/showQuote.do',// 跳转到 action
						data : {
							goodname : $("#goodname").val(),
							startTime : $("#startTime").val(),
							startEnd : $("#startEnd").val(),
							createTime1 : $("#createTime1").val(),
							createTime2 : $("#createTime2").val(),
							status : $("#status").val(),
							type : $("#type").val(),
							pageNo : curr,
							pageSize : limit
						},
						type : 'POST',
						cache : false,
						dataType : 'json',
						success : function(data) {
							//先清空table中的数据
							data = eval(data);
							$("#showTable  tr:not(:first)").remove();
								if(data.qtList.length>0){
									//获取当前系统时间
									var myDate = new Date();
									var dataNow = myDate.toLocaleDateString();     //获取当前日期
									for ( var i = 0; i < data.qtList.length; i++) {
											var content = '';
											content += "<tr style='border-bottom:1px #ddd solid;'>";
											content += "<td >"+data.qtList[i].id+"</td>";
											content += "<td>"+data.qtList[i].goodsName+"</td>";
											content += "<td>"+data.qtList[i].num+"</td>";
											//content += "<td>"+data.qtList[i].mmbName+"</td>";
											content += "<td>"+data.qtList[i].minPrice+"--"+data.qtList[i].maxPrice+"</td>";
											content += "<td>"+data.qtList[i].publishName+"</td>";
											//content += "<td>"+$.changeDate(data.qtList[i].createTime)+"</td>";
											content += "<td>"+$.changeDate(data.qtList[i].startTime)+"--"+$.changeDate(data.qtList[i].startEnd)+"</td>";
											
											if(data.qtList[i].rangType == 0){
												content += "<td>公开报价</td>";
											}else if(data.qtList[i].rangType == 1){
												content += "<td>指定报价</td>";
											}else{
												content += "<td>未发布</td>";
											};
											content += "<td><input class='btn btn-success'  style='height:25px; line-height:12px; padding:5px 5px' type='button'  value='指定发布范围' onclick=updateRang('"+data.qtList[i].id+"','"+data.qtList[i].mmbId+"','"+data.qtList[i].rangType+"','"+data.qtList[i].type+"')></input>&nbsp;";
											content += "<input class='btn btn-warning'  style=' height:25px; line-height:12px; padding:5px 5px' type='button'  value='编辑' onclick=toEditQuote('"+data.qtList[i].id+"');></input>&nbsp;";
											
											if($.changeDate(dataNow)<$.changeDate(data.qtList[i].startEnd)){
												if (data.qtList[i].status == 0) {
												
													content += "<input class='btn btn-success' style=' height:25px; line-height:12px; padding:5px 5px' type='button' value='禁用' onclick=stopQuote('"+data.qtList[i].id+"');></input></td>";
												
												} else if(data.qtList[i].status == 1) {
													
													content += "<input class='btn btn-success' style=' height:25px; line-height:12px; padding:5px 5px' type='button' value='启用' onclick=startQuote('"+data.qtList[i].id+"');></input></td>";
												} 
											}else{
												content += "<input class='btn btn-success' style=' height:25px; line-height:12px; padding:5px 5px' type='button' value='已过期' ;></input></td>";
												
											}
											content += '</tr>';
											addTr('showTable', -1, content);
							
									}
									//alert("数据条数="+data.qtCount);
								}else{
										//alert("无数据！");
							}
					},
					error : function() {
					alert("异常！");
					}
				});
				}
			});
		}
//停用报价
	function stopQuote(quoteId){
		if(quoteId!=""){
			
			$.ajax({
				url:'${ctx}/QuoteController/paseQuote.do',
				type: "POST",
	            dataType: "json",
				data:{"quoteId":quoteId},
				success:function(data){
				   	 data = eval(data);
				   	 //如果成功
				   	 if(data!=null&&data== "0"){
				   	 	//提示信息
				   	 	alert("停用报价成功！");
				   
				   	 	$("#searchButt").click();
				   	 }else{
				   	 	 //失败
				   	 	alert("停用失败！");
				   	 }
				}	
				});
	    }
	}
	
	//启用报价
	
	function startQuote(quoteId){
		if(quoteId!=""){
			
			$.ajax({
				url:'${ctx}/QuoteController/enabledQuote.do',
				type: "POST",
	            dataType: "json",
				data:{"quoteId":quoteId},
				success:function(data){
				   	data = eval(data);
				   	//alert(data);
				   	 //如果成功
				   	 if(data!=null&&data== "0"){
				   	 	//提示成功
				   	 	alert("启用报价成功！");
				   	 	//重新查询  
				   	 	$("#searchButt").click();	
				   	 }else{
				   	 	 //失败
				   	 	alert("启用失败！");
				   	 }
				 }  	
				});
	    }
	}
</script>
</head>

<body>

<div class="container-fluid" >
    <div class="panel panel-default" style="margin-top:10px; box-shadow:3px 3px 8px rgba(0,0,0,0.1);">
       <div class="panel-heading">
          <h3 class="panel-title">
             报价管理
          </h3>
       </div>
       <div class="panel-body">
                <div class="row">
                      <div class="col-md-3 col-sm-12">
                           
                              <div class="form-group">
                                <label class="pull-left">报价类型</label>
                                <div class="col-sm-9">
                                    <select class="form-control " id="type" name="type">
											<option value="0">采购报价</option>
											<option value="1">销售报价</option>
									</select>
                                </div>
                              </div>
                                    
                      </div>
                      <div class="col-md-3 col-sm-12">
                           
                              <div class="form-group">
                                <label class="pull-left" style="padding-right:28px;">状态</label>
                                <div class="col-sm-9">
                                   <select class="form-control " id="status" name="status">
									 <option value="">全部</option>
									 <option value="0">使用中</option>
									 <option value="1">已过期</option>
									 <option value="2">已停用</option>
								  </select>
                                </div>
                              </div>
                                     
                      </div>
                      <div class="col-md-3 col-sm-12">
                            <form class="form-horizontal">
                              <div class="form-group">
                                <label class="pull-left">商品名称</label>
                                <div class="col-sm-9">
                                  <input type="text" class="form-control" id="goodname" name="goodname" />
                                </div>
                              </div>
                            </form>          
                      </div>
                </div>
                <div class="row">
                    <div class="col-md-3 col-sm-12">
                            <form class="form-horizontal">
                              <div class="form-group">
                                <label class="pull-left">发布时间</label>
                                <div class="col-sm-9">
                                  <input type="text" class="ui_timepicker form-control validate[required,custom[date]]"  id="createTime1" name="createTime1"/>
                                </div>
                              </div>
                            </form>          
                    </div>
                
                    <div class="col-md-3 col-sm-12">
                            <form class="form-horizontal">
                              <div class="form-group">
                                <label class="pull-left" style="padding-right:42px;" >至</label>
                                <div class="col-sm-9">
                                  <input type="text" class="ui_timepicker form-control validate[required,custom[date]]" id="createTime2" name="createTime2" />
                                </div>
                              </div>
                            </form>          
                    </div>
                    <div class="col-md-3 col-sm-12">
                            <form class="form-horizontal">
                              <div class="form-group">
                                <label class="pull-left" style="padding-right:14px;">有效期</label>
                                <div class="col-sm-9">
                                  <input type="text" class="ui_timepicker form-control validate[required,custom[date]]" id="startTime" name="startTime"/>
                                </div>
                              </div>
                            </form>          
                    </div>
                    <div class="col-md-3 col-sm-12">
                            <form class="form-horizontal">
                              <div class="form-group">
                                <label class="pull-left" style="padding-right:42px;" >至</label>
                                <div class="col-sm-9">
                                  <input type="text" class="ui_timepicker form-control validate[required,custom[date]]" id="startEnd" name="startEnd" />
                                </div>
                              </div>
                            </form>          
                    </div>
					<div class="row">
						
						<button type="button"  class="btn btn-primary col-md-1 pull-right" style="margin:0 60px 20px 0;" onclick="showQuote();" id="searchButt">查询<tton>
					</div>

                </div>
               <div class="clearfix"></div>
                <div class="table-responsive" >
                    <table class="table table-hover" id="showTable" method="post">
                      <thead>
                         <tr class="active" style=" border-top:1px solid #ddd;" >
                             <th width="10%">序号</th>
							<th width="10%">商品名称</th>
							<th width="10%">数量</th>
							<th width="10%">价格范围</th>
							<th width="10%">发布人</th>
							<th width="10%">有效期范围</th>
							<th width="10%">发布类型</th>
							<th width="20%">操作</th>
                        </tr>
                      </thead>
                      <tbody>
                 
                       
                      </tbody>
                   </table>
                </div>
                <!---------------------------------------page start--------------------------------------------------------------->
				
				<!---------------------------------------page  over--------------------------------------------------------------->
                <div class="row">
                    <button type="button" class="btn btn-primary col-md-1 pull-right"  style="margin:0 60px 20px 0;" onclick='openAddQuote();' >创建报价</button>
                </div>
    	</div>
		      
        <div >
        		<footer class="panel-footer text-right bg-light lter"> 
				        <div id="callBackPager" float="right;"></div>
				</footer>
        </div>
	</div>
</div>

<!--编辑报价 start------>
<!--弹出层--> 
<div class="modal fade" id="bianjibaojia" role="dialog" aria-labelledby="gridSystemModalLabel" data-backdrop="static">
	<div class="modal-dialog m-tanchu-box" role="document"> 
	 <form class="form-horizontal" role="form" id="updateQuote">
        		<input type="hidden" id="id2" name="id2" />
        		<input type="hidden" id="type3" name="type3" />
        		<input type="hidden" id="categoryId2" name="categoryId2" />
        		<input type="hidden" id="goodsId2" name="goodsId2" />
        		<input type="hidden" id="status2" name="status2" />
        		<input type="hidden" id="rangType2" name="rangType2" />
        		<input type="hidden" id="titlePic2" name="titlePic2" />
        		<input type="hidden" id="imgPath2" name="imgPath2" />
        <div class="container-fluid" style="margin-top:15px;">
        	 
            <div class="row-fluid">
                <!-- col-sm-12 -->
                
                <div class="col-sm-12 ">
                    <div class="panel panel-default article-bj">
                    
                        <div class="panel-heading box-shodm modal-draggable">
                        编辑报价
                         <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        
                            <div class="row wrapper form-margin">
                            	 <div class="row form-margin">
                                     <div class="col-md-6">
                                        <div class="input-group">
                                             <div class="input-group-btn">
                                                <h5 class="h5-margin">报价类型:</h5>
                                             <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <select  tabindex="-1"class="form-control" id="type2" name="type2">
                                                <option value="0">采购报价</option>
                            					 <option value="1">销售报价</option>
                                             </select>
                                        </div>
                                     </div>
                                  </div>
                                  <div class="row form-margin">
                                  	<div class="col-md-12"style="margin-top:20px;margin-bottom:20px;">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 class="h5-margin">标题图片:</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <img src="images/contentp.png" style="height:80px; width:80px;" id="updateQuoteImg">
                                            
                                        <button class="btn btn-warning  btn-sm " type="button" style="margin-left:100px; margin-top:45px;" onclick="updateImg();">上传</button>
                                        <button class="btn btn-success  btn-sm " style="margin-left:10px; margin-top:45px;" onclick="delUpdateImg();">删除</button>
                                 
                                        </div>
                                       
                                    </div>
                                    
                                   </div>
                                    <div class="row form-margin">
                                     <div class="col-md-5">
                                        <div class="input-group">
                                             <div class="input-group-btn">
                                                <h5 class="h5-margin">商品名称:</h5>
                                             <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <input type="text" class="ui_timepicker form-control validate[required]" id="goodsName2" name="goodsName2" readonly ="readonly"  />
                                        </div>
                                     </div>
                                    
                                    <div class="col-md-4">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 >数量：</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                            
                                             <input type="text" class="ui_timepicker form-control validate[required,custom[integer]]" id="num2" name="num2" />
                                        </div>
                                     </div>
                                  </div>
                                   
                                   <div class="row form-margin">
                                     <div class="col-md-5">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 class="h5-margin">单 &nbsp;&nbsp;&nbsp;&nbsp;价:</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                            
                                             <input type="text" class="ui_timepicker form-control validate[required,custom[number]]" id="minPrice2" name="minPrice2"/>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 style="margin-right:19px;">至</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             
                                              <input type="text" class="ui_timepicker form-control validate[required,custom[number]]" id="maxPrice2" name="maxPrice2" />
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="input-group">
                                             <div class="input-group-btn">
                                              <h5 style="margin-right:10px;"></h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <select class="form-control"  tabindex="-1" id="unitPrice2" name="unitPrice2">>
                                                <option value="￥">￥</option>
                                        		<option value="$">$</option>
                                             </select>
                                        </div>
                                    </div>
                                  </div>
                                  <div class="row form-margin">
                                    <div class="col-md-5">
                                        <div class="input-group">
                                             <div class="input-group-btn">
                                                <h5 class="h5-margin">有效时间:</h5>
                                             <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                            
                                             <input type="text" class=" form-control validate[required,custom[date]]" id="startTime2" name="startTime2"/>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 style="margin-right:19px;">至</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                            
                                              <input type="text" class="form-control validate[required,custom[date]]" id="startEnd2" name="startEnd2" />
                                        </div>
                                    </div>
                                   </div>
                                   <div class="row form-margin">
                                    <div class="col-md-11">
                                        <div class="input-group">
                                             <div class="input-group-btn">
                                                <h5 class="h5-margin">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注:</h5>
                                             <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <textarea rows="3" class="form-control validate[maxSize[200]]" style="width:100%" id="explan2" name="explan2">
                                         	 </textarea>
                                        </div>
                                    </div>
                                   </div>
                             </div>
                            <footer class="panel-footer text-right bg-light lter">
                            
                            <button class="btn btn-warning btn-s-xs" type="button" onclick="updateQuote();">确认</button>
                            </footer>
                        </div>
                     </div>
                </div>
            </div>
            </form> 
        </div>
	</div>
<!--弹出层结束-->
<!--编辑会员-->
          
  
   
<!--创建报价 start------>
<div class="modal fade" id="chuangjianbaojia" role="dialog" aria-labelledby="gridSystemModalLabel" data-backdrop="static">
	<div class="modal-dialog m-tanchu-box" role="document"> 
	 <form class="form-horizontal" role="form"  id="createQuote" method="post">
        		<input type="hidden" id="categoryId1" name="categoryId1" />
        		<input type="hidden" id="goodsId1" name="goodsId1" />
        		<input type="hidden" id="titlePic1" name="titlePic1" />
        		<input type="hidden" id="titlePic1" name="imgPath1" />
        <div class="container-fluid" style=" margin-top:15px;">
            <div class="row-fluid">
                <!-- col-sm-12 -->
                <div class="col-sm-12 ">
                	
                	
                    <div class="panel panel-default article-bj">
                        <div class="panel-heading box-shodm modal-draggable">
                        创建报价
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                            <div class="row wrapper form-margin">
                            	 <div class="row form-margin">
                                     <div class="col-md-6">
                                        <div class="input-group">
                                             <div class="input-group-btn">
                                                <h5 class="h5-margin">报价类型:</h5>
                                             <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <select  tabindex="-1"class="form-control" id="type1" name="type1">
                                                	<option value="0">采购报价</option>
                             						<option value="1">销售报价</option>
                                             </select>
                                        </div>
                                     </div>
                                  </div>
                                  <div class="row form-margin">
                                  	<div class="col-md-12"style="margin-top:20px;margin-bottom:20px;">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 class="h5-margin">标题图片:</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <img src="images/contentp.png" style="height:80px; width:80px;" id="addQuoteImg">
                                            
                                        <button class="btn btn-warning  btn-sm " type="button"  onclick="addImg();" style="margin-left:100px; margin-top:45px;">上传</button>
                                        <button class="btn btn-success  btn-sm " style="margin-left:10px; margin-top:45px;" type="button" onclick="delImg();">删除</button>
                                 
                                        </div>
                                    </div>
                                   </div>
                                   
                                   <div class="row form-margin">
                                     <div class="col-md-5">
                                        <div class="input-group">
                                             <div class="input-group-btn">
                                                <h5 class="h5-margin">商品名称:</h5>
                                             <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <input type="text" class="ui_timepicker form-control validate[required]" id="goodsName1" name="goodsName1" readonly ="readonly"  />
                                        </div>
                                     </div>
                                     <div class="col-md-2">
                                        <button class="btn btn-success btn-sm  " type="button" onclick="getUser();" data-toggle="modal" data-target="#xuanzeshangpin2" >选择商品</button>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 >数量：</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                            
                                             <input type="text" class="ui_timepicker form-control validate[required,custom[integer]]" id="num1" name="num1" />
                                        </div>
                                     </div>
                                  </div>
                                   
                                   <div class="row form-margin">
                                     <div class="col-md-5">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 class="h5-margin">单 &nbsp;&nbsp;&nbsp;&nbsp;价:</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             
                                             <input type="text" class="ui_timepicker form-control validate[required,custom[number]]" id="minPrice1" name="minPrice1"/>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 style="margin-right:19px;">至</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                            
                                             <input type="text" class="ui_timepicker form-control validate[required,custom[number]]" id="maxPrice1" name="maxPrice1" />
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="input-group">
                                             <div class="input-group-btn">
                                              <h5 style="margin-right:10px;"></h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <select class="form-control"  tabindex="-1" id="unitPrice1" name="unitPrice1">
                                               <option value="￥">￥</option>
                                        		<option value="$">$</option>
                                             </select>
                                        </div>
                                    </div>
                                  </div>
                                  <div class="row form-margin">
                                    <div class="col-md-5">
                                        <div class="input-group">
                                             <div class="input-group-btn">
                                                <h5 class="h5-margin">有效时间:</h5>
                                             <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             
                                             <input type="text" class="ui_timepicker form-control validate[required,custom[date]]" id="startTime1" name="startTime1"/>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 style="margin-right:19px;">至</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             
                                              <input type="text" class="ui_timepicker form-control validate[required,custom[date]]" id="startEnd1" name="startEnd1" />
                                        </div>
                                    </div>
                                   </div>
                                   <div class="row form-margin">
                                    <div class="col-md-11">
                                        <div class="input-group">
                                             <div class="input-group-btn">
                                                <h5 class="h5-margin">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注:</h5>
                                             <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <textarea rows="3" class="form-control validate[maxSize[200]]"  style="width:100%"  id="explan1" name="explan1">
                                         	 </textarea>
                                        </div>
                                    </div>
                                   </div>
                             </div>
                            <footer class="panel-footer text-right bg-light lter">
                            
                            <button class="btn btn-warning btn-s-xs" type="button"  onclick="createQuote();">确认</button>
                            </footer>
                        </div>
                     </div>
                </div>
                </form>
            </div> 
        </div>
	</div>
<!--创建报价 over----->
<!--选择商品 start------>
<div class="modal fade bs-example-modal-lg" id="xuanzeshangpin2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
   <div class="modal-dialog modal-lg">
      <div class="modal-content">
         <div class="modal-header modal-draggable">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h3 class="panel-title">选择商品</h3>
         </div>
    <!--ztree start-->
    <div id="st_tree" style="float:left; margin-left:20px; border:none;; width:20%"></div> 
    <div class="panel panel-default" style="width:75%; float:left; margin-top:6px;">
       <div class="panel-heading">
          <h3 class="panel-title">
             商品信息
          </h3>
       </div>
       <div class="panel-body">
          <table class="table table-hover" id="showGoods">
               <thead>
                  <tr>
                  	 <th>选择</th>
                     <th>名称</th>
                     <th>数量</th>
                     <th>价格</th>
                  </tr>
               </thead>
               <tbody>
                  
               </tbody>
            </table>
       </div>
	</div>
     <div class="clear"></div>
     	<footer class="panel-footer text-right bg-light lter">
                    
                    <div id="callBackPager5" float="right;"></div>
                   
        </footer>
         <div class="modal-footer">
            <input class="btn btn-primary" class="close"  aria-hidden="true" type="button" onclick="xuanGood();" value="确定">
         </div>
        </div>
    </div>
</div>

<!------ 选择商品 over------>

<!------ 创建报价 over------>
</div> 
<!---------------------------------------table over--------------------------------------------------------------->

    <!--指定范围 start------>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
       <div class="modal-dialog" style="transform:translate(0,150px)">
          <div class="modal-content">
          
             <div class="modal-header modal-draggable">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
             </div>
        <div class="form-group" style="margin:10px;">
            <button id="form-group_in1" type="button" class="btn btn-info col-md-3 active" style="margin-right:20px;" onclick="getAreaName();" >公开</button>  
            <button  id="xuanzeRang" type="button" class="btn btn-info col-md-3" onclick="openRang();">选择发布范围</button>
            <form id="rangTable" method="post">
            		<input id="showType" type="hidden" />
            		<input id="showMmbId" type="hidden"/>
            		<input id="showRangType" type="hidden"/> 
            		<input id="showQuoteId" type="hidden"/>  
            </form> 
        </div>
        <div class="clear"></div>
        
    <div id="form_con1" class="form_con_hide" style=" display:block;" >
        <p>公开报价将公开发布在本省范围内<span id="addAreaName" ></span></p>
		<div class="Bbmt_content">
		<input class="btn btn-primary col-md-2" class="close" aria-hidden="true"  style="margin:20px 0 20px 239px;" type="button" value="确定" onclick="subPub();">
	 </div>
	<div class="clear"></div>	 
    </div>
    <!--指定发布范围 start------>
    <div class="form_con_hide" id="form_con2">
        <!--ztree start-->
        <div class="st_tree" style="float:left; margin-left:20px; border:none;">
            <ul>
                <li><a href="#" ref="xtgl">发布范围</a></li>
                    <ul show="true">

                        <li><a href="#" ref="rzck">合作会员</a></li>
                    		<ul show="true" id="searchMmb">
                        		
                            </ul>
                        <li><a href="#" ref="rzck">群组</a></li>
                    		<ul show="true" id="searchGroup">
                        		<li><a href="#" ref="yhgl">XX群</a></li>
                            </ul>
                    </ul>
            </ul>
        </div>             
         <!--ztree over--> 
        <div style="float:right; margin-right:50px; margin-top:20px;" id="mmm">
            <p><button style="width:120px;" type="button" class="btn btn-info"  onclick="aa()">选择合作会员</button></p>  
            <p><button style="width:120px;" type="button" class="btn btn-info" onclick="dd()">选择群组</button></p>  
            <p><button style="width:120px;" type="button" class="btn btn-info" onclick="deleteScopeId()">删除</button></p>  
        </div>
         <div class="clear"></div> 
         <div class="Bbmt_content">
            <input class="btn btn-primary col-md-2" class="close" aria-hidden="true"  style="margin:20px 0 20px 239px;" type="button" value="确定" onclick="subPub1();">
         </div>
         <div class="clear"></div>
             
     </div>
    <!------ 指定发布范围 over------>           
    </div>  
</div>
</div>	
             <form>
           <!--选择合作会员 start------>
            <div class="modal fade" id="xuanzehezuohuiyuan" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
               <div class="modal-dialog" style="transform:translate(0,100px); width:770px;">
                  <div class="modal-content">
                     <div class="modal-header modal-draggable">
                        <button type="button" class="close" onclick="bb()">&times;</button>
                     </div>
                     <div class="modal-body">
        <!--------------------------------------- sech start--------------------------------------------------------------->
        <form class="form-horizontal" role="form">
            <div class="form-group">
              <label class="pull-left mylable">会员名称：</label>
              <div class="form-group col-lg-3 col-sm-3 col-xs-4">
                 <input type="text" class="form-control" id="mmbSname" name="mmbSname" />
              </div>
              <div class="form-group col-lg-2 col-xs-3">
                  <input class="btn btn-primary"  type="button" value="查询" onclick="showMmb();" style="margin-left:100px;">
              </div>
            </div>
        </form>
        </div>
        <!--------------------------------------- sech over--------------------------------------------------------------->
               <div class="clear"></div>
            <!---------------------------- table start--------------------------------------------------->
                <div class="table-responsive" style="width:100%;">
                   <table class="table table-hover" id="mmbTable" method="post">
                      <thead>
                         <tr class="active"  style=" border:1px solid #ddd;" >
                            <th width="10%" style="padding-left:25px;"><input type="checkbox" onclick="checkAllMmbs(this)"/></th>
                            <th width="10%">序列</th>
                            <th width="10%">组织名称</th>
                            <th width="10%">关联关系</th>
                          
                        </tr>
                      </thead>
                      <tbody>
                      </tbody>
                   </table>
                </div> 
            <!---------------------------- table over--------------------------------------------------->
            <footer class="pagination" style="margin-left:34%;">
                       <div id="callBackPager1" float="right;"></div>
			</footer>
            
             <div class="Bbmt_content" style="margin-bottom:50px;">
                <input class="btn btn-primary col-md-2"  aria-hidden="true" style="margin-left:196px;" type="button" value="添加" onclick="subMmb()">
                <input class="btn btn-primary col-md-2"  aria-hidden="true" style="margin-left:110px;" type="button" value="取消" onclick="bb()">
             </div>
             <div class="clear"></div>
                    </div>
                </div>
            </div>
            <!------ 选择合作会员 over------>

    
    <!--选择群组 start------>
    <div class="modal fade" id="xuanzequnzu" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
               <div class="modal-dialog" style="transform:translate(0,100px); width:770px;">
                  <div class="modal-content">
                     <div class="modal-header modal-draggable">
                        <button type="button" class="close" onclick="cc()">&times;</button>
                     </div>
        <div class="modal-body">
        <!--------------------------------------- sech start--------------------------------------------------------------->
        <form class="form-horizontal" role="form">
            <div class="form-group">
              <label class="pull-left">群组名称：</label>
              <div class="form-group col-lg-3 col-sm-3 col-xs-4">
                 <input type="text" class="form-control" id="groupName" name="groupName" />
              </div>       
              <div class="form-group col-lg-2 col-xs-3">
                  <input class="btn btn-primary"  type="button" value="查询" onclick="showGroup();" style="margin-left:100px;">
              </div>
            </div>
        </form>
        </div>
        <!--------------------------------------- sech over--------------------------------------------------------------->
               <div class="clear"></div>
            <!---------------------------- table start--------------------------------------------------->
                <div class="table-responsive" style="width:100%;">
                   <table class="table table-hover" id="groupTable" method="post">
                      <thead>
                         <tr class="active"  style=" border:1px solid #ddd;" >
                            <th width="10%" style="padding-left:25px;"><input type="checkbox" onclick="checkAllGroups(this)" /></th>
                            <th width="10%">序列</th>
                            <th width="10%">群组名称</th>
                            <th width="10%">群组关系</th>
                          
                        </tr>
                      </thead>
                      <tbody>
                      </tbody>
                   </table>
                </div> 
            <!---------------------------- table over--------------------------------------------------->
            <footer class="pagination" style="margin-left:34%;">
                       <div id="callBackPager2" float="right;"></div>
			</footer>
            
             <div class="Bbmt_content" style="margin-bottom:50px;">
                <input class="btn btn-primary col-md-2"  aria-hidden="true" style="margin-left:196px;" type="button" value="确定" onclick="subGroup()">
                <input class="btn btn-primary col-md-2"  aria-hidden="true" style="margin-left:110px;" type="button" value="取消" onclick="cc()">
             </div>
             <div class="clear"></div>
                    </div>
                </div>
            </div>
          </form>
    
<!------ 选择群组 over------>





</div>

<!-- 嵌入资料库-->
<div class="modal fade " id="childPage" role="dialog" aria-labelledby="gridSystemModalLabel" style="width:1200px; height:750px;" data-backdrop="static">
	
		<div class="container-fluid modal-draggable"  style="margin:0px; padding:0px;" >
            <div class="row-fluid" >
                <!-- col-sm-12 -->
                <div class="col-sm-12 " style="margin:0px; padding:0px;">
                    <div class="panel panel-default article-bj"  style="min-height:750px; height:100%; width:100%">
                        <div class="panel-heading box-shodm ">
                        资源库信息
                        <button type="button" class="close" 
                           data-dismiss="modal" aria-hidden="true" onclick="childCloseImg();" >
                              &times;
                        </button>
                        
                        </div>
                       
                        <div >
                         <iframe  id="childBase" name="childBase"  src="${ctx}/mbase/toshowBase1.do" width="100%" height="100%" frameborder="0"   class=" modal-draggable " />
                   	    </div>
                    </div>
                </div>
            </div>
        </div>
		
	
</div>
</body>
</html>
