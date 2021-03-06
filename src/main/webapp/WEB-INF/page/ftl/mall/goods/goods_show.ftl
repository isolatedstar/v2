<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />   <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>商品管理</title>
<link href="${ctx}/mall/css/pulic.css" rel="stylesheet" type="text/css">
<link href="${ctx}/mall/css/theme.css" rel="stylesheet" type="text/css">
<link href="${ctx}/mall/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${ctx}/mall/css/SimpleTree.css" rel="stylesheet" type="text/css">
<link href="${ctx}/mall/css/mall.css" rel="stylesheet" type="text/css">
<link href="${ctx}/mall/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="${ctx}/mall/css/media.css" rel="stylesheet" type="text/css">
<script src="${ctx}/mall/js/jquery.js"></script>
<script src="${ctx}/mall/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="${ctx}/mall/js/bootstrap.min.js" type="text/javascript"></script>

<script src="${ctx}/mall/js/bootstrap-treeview.js"></script>
<script src="${ctx}/mall/js/SimpleTree.js" type="text/javascript"></script>
<script src="${ctx}/mall/js/extendPagination.js"></script>
<script src="${ctx}/mall/js/showGood.js"></script>
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
<script type="text/javascript">
        $(function(){
            $(".st_tree").SimpleTree({
                click:function(a){
                    if(!$(a).attr("hasChild"))
                        alert($(a).attr("ref"));
                }
            });
            //校验
            $("#createTime1").datetimepicker({
				locale: moment.locale('zh-cn'),
				showTodayButton: true,
				dayViewHeaderFormat: 'YYYY MM',
				format: 'YYYY-MM-DD'
			});
			$('#createTime1').datetimepicker();
			//
	        $("#createTime").datetimepicker({
	        	locale: moment.locale('zh-cn'),
				showTodayButton: true,
				dayViewHeaderFormat: 'YYYY MM',
				format: 'YYYY-MM-DD'
	        });
	        $('#createTime').datetimepicker();
            $("#createGood").validationEngine('attach',{
					scroll:false,
					autoHidePrompt:true,
					autoHideDelay:2500,
					promptPosition : "bottomLeft"
			 });
			  
			
			 $("#updateGood").validationEngine('attach',{
					scroll:false,
					autoHidePrompt:true,
					autoHideDelay:2500,
					promptPosition : "bottomLeft"
			 });
			
            
            //新增数量
	        $("#stockNum").on("input propertychange", function() {
				$(this).validateNumber();
				
			});
			//新增价格
			 $("#minPrice").on("input propertychange", function() {
				$(this).validateAmount();
				
			});
			 $("#maxPrice").on("input propertychange", function() {
				$(this).validateAmount();
				
			});
			//编辑
			 $("#stockNum1").on("input propertychange", function() {
				$(this).validateNumber();
				
			});
			//新增价格
			 $("#minPrice1").on("input propertychange", function() {
				$(this).validateAmount();
				
			});
			 $("#maxPrice1").on("input propertychange", function() {
				$(this).validateAmount();
				
			});
        });
     //商品展示页面
	var goodDetail;
	 $(function(){
            goodDetail =  new goodDetail("goodlook", '${ctx}','aa');
     });
	function seeGood(goodId){
			//商品
			goodDetail.showGoodDetail(goodId);
			$("#goodlook").modal("show");
	} 
	
	
 </script>
<script type="text/javascript">
$(document).ready(function(e) {
    $('.A_b_2_nav_left>li').click(function(){
		$('.A_b_2_subnav').slideUp()
		$(this).next('ul').stop().slideDown()
		})
	$('.A_b_2_subnav li a').click(function(){
		$('.A_b_2_subnav li a').css('color','#333')
		$(this).css('color','#4aa3df')
		$($(this).attr('href')).show()
		})
});
	function cc(){
		$('#xuanzeshangpin2').modal('hide');
	}
	
	//****************************************上传**********************************************************
	function childCloseImg(){
		//关闭目录与资料路内容div
		//childBase.$("#treeview1").hide();
		childBase.$("#resourceInfo").hide();
			
	}
	//  嵌入资料库相关方法
	//新增页面点击上传按钮
	//给子页面getType赋值  表示为商品新增页面	
	function addImg(){
		//给子页面getType赋值  表示为新增页面
		childBase.$("#getType").val("1");
		//给子页面赋值  判断是添加标题图片还是添加轮播图  1标题图片  2轮播图
	   	childBase.$("#changeType").val("1");
		//alert(childBase.$("#getType").val()); 
		$("#childPage").modal('show');
	}
	//新增页面删除按钮  titlePic1
	function delImg(){
		//清除
		$("#imgId").val("");
		$("#imgPath").val("");
		$("#imgShow").attr("src","");
	}
	//修改页面点击上传按钮
	function updateImg(){
		//给子页面getType赋值  表示为修改页面
		childBase.$("#getType").val("2");
		//给子页面赋值  判断是添加标题图片还是添加轮播图  1标题图片  2轮播图
	   	childBase.$("#changeType").val("1");
		//alert(childBase.$("#getType").val()); 
		$("#edit").modal('show');
		$("#childPage").modal('show');
	};
	//修改页面删除按钮  titlePic2
	function delUpdateImg(){
		//清除
		$("#imgId1").val("");
		$("#imgPath1").val("");
		$("#imgShow1").attr("src","");
	};
	//新增页面轮播图  添加
	function addCarousel(){
		//给子页面getType赋值  表示为新增页面
		childBase.$("#getType").val("1");
		//给子页面赋值  判断是添加标题图片还是添加轮播图  1标题图片  2轮播图
	   	childBase.$("#changeType").val("2");
		//alert(childBase.$("#getType").val()); 
		$("#childPage").modal('show');
	}
	//编辑轮播图   添加
	function updateCarousel(){
		//给子页面getType赋值  表示为编辑页面
		childBase.$("#getType").val("2");
		//给子页面赋值  判断是添加标题图片还是添加轮播图  1标题图片  2轮播图
	   	childBase.$("#changeType").val("2");
		//alert(childBase.$("#getType").val());
		$("#edit").modal('show'); 
		$("#childPage").modal('show');
	}
	//新增清空轮播图
	function dellunbo1(){
		$("#lunbo1").empty();
		$("#lunbo2").empty();
		$("#imageIds").val("");
	}
	//编辑清空轮播图
	function dellunbo2(){
		$("#lunbo3").empty();
		$("#lunbo4").empty();
		$("#imageIds1").val("");
	}
	//新增轮播图显示方法
	function showCarousel(){
		$("#lunbo1").empty();
		$("#lunbo2").empty();
		$("#lunbo3").empty();
		$("#lunbo4").empty();
       //获取选中的轮播图
       var imageIds =  $("#imageIds").val();
       if(imageIds==""){
       		return false;
       }
       $.ajax({
					url : '${ctx}/material/showCarousel.do',// 跳转到 action
					data : {
							imageIds:imageIds,		
					},
					type : 'POST',
					cache : false,
					dataType : 'json',
					success : function(data) {
						if(data.path!=""){
							    var lunbo1 = document.getElementById("lunbo1");
								var lunbo2 =document.getElementById("lunbo2");
								for ( var i = 0; i < data.path.length; i++) {
										 var id = i+1;
										 var li= document.createElement("li");  
										 var path = "${ctx}"+  data.path[i] ;
									     var href_a = document.createElement("a");  
									            
									            
									            href_a.target="_blank"
									            href_a.innerHTML ="<img  src='"+path+"' >";  
									            li.id=id;
									            li.setAttribute("class", "slider-panel");   
									            li.appendChild(href_a);  
									            lunbo1.appendChild(li); 
									            
									    var li1= document.createElement("li");  
									            li1.id=id;  
									            li1.setAttribute("class", "slider-item");  
									            li1.innerHTML=id;  
									            lunbo2.appendChild(li1); 
										
								} 
							aa();
							
						}else{
							
						}			
					},
					error : function() {
						alert("异常！");
					}
		});
	}
	//编辑轮播图显示方法
	function showCarousel1(){
		$("#lunbo1").empty();
		$("#lunbo2").empty();
		$("#lunbo3").empty();
		$("#lunbo4").empty();
       //获取选中的轮播图
       var imageIds =  $("#imageIds1").val();
       if(imageIds==""){
       		return false;
       }
       $.ajax({
					url : '${ctx}/material/showCarousel.do',// 跳转到 action
					data : {
							imageIds:imageIds,		
					},
					type : 'POST',
					cache : false,
					dataType : 'json',
					success : function(data) {
						if(data.path!=""){
							    var lunbo3 = document.getElementById("lunbo3");
								var lunbo4 =document.getElementById("lunbo4");
								for ( var i = 0; i < data.path.length; i++) {
										 var id = i+1;
										 var li= document.createElement("li");  
										 var path = "${ctx}"+  data.path[i] ;
									     var href_a = document.createElement("a");  
									           
									            href_a.target="_blank"
									            href_a.innerHTML ="<img  src='"+path+"' >";  
									            li.id=id;
									            li.setAttribute("class", "slider-panel");  
									            
									            li.appendChild(href_a);  
									            lunbo3.appendChild(li); 
									            
									    var li1= document.createElement("li");  
									            li1.id=id;  
									            li1.setAttribute("class", "slider-item");  
									            li1.innerHTML=id;  
									            lunbo4.appendChild(li1); 
										
								} 
							aa();
							
						}else{
							
						}			
					},
					error : function() {
						alert("异常！");
					}
		});
	}
	//轮播图js
	 var length;
		 var  currentIndex = 0; 
		  var interval; 
		  var hasStarted = false; //是否已经开始轮播 
   		  var t = 3000; //轮播时间间隔 
		 function aa(){
		 		length = $('.slider-panel').length; 
				  
				  //将除了第一张图片隐藏 
				  $('.slider-panel:not(:first)').hide(); 
				  //将第一个slider-item设为激活状态 
				  $('.slider-item:first').addClass('slider-item-selected'); 
				  //隐藏向前、向后翻按钮 
				  $('.slider-page').hide(); 
				  //鼠标上悬时显示向前、向后翻按钮,停止滑动，鼠标离开时隐藏向前、向后翻按钮，开始滑动 
				  $('.slider-panel, .slider-pre, .slider-next').hover(function() { 
				   stop(); 
				   $('.slider-page').show(); 
				  }, function() { 
				   $('.slider-page').hide(); 
				   start(); 
				  }); 
				  $('.slider-item').hover(function(e) { 
				   stop(); 
				   var preIndex = $(".slider-item").filter(".slider-item-selected").index(); 
				   currentIndex = $(this).index(); 
				   play(preIndex, currentIndex); 
				  }, function() { 
				   start(); 
				  }); 
				  $('.slider-pre').unbind('click'); 
				  $('.slider-pre').bind('click', function() { 
				   pre(); 
				  }); 
				  $('.slider-next').unbind('click'); 
				  $('.slider-next').bind('click', function() { 
				   next(); 
				  }); 
		 }
		   /** 
		   * 向前翻页 
		   */
		  function pre() { 
		   var preIndex = currentIndex; 
		   currentIndex = (--currentIndex + length) % length; 
		   play(preIndex, currentIndex); 
		  } 
		  /** 
		   * 向后翻页 
		   */
		  function next() { 
		   var preIndex = currentIndex; 
		   currentIndex = ++currentIndex % length; 
		   play(preIndex, currentIndex); 
		  } 
		  /** 
		   * 从preIndex页翻到currentIndex页 
		   * preIndex 整数，翻页的起始页 
		   * currentIndex 整数，翻到的那页 
		   */
		  function play(preIndex, currentIndex) { 
		   $('.slider-panel').eq(preIndex).fadeOut(500) 
		    .parent().children().eq(currentIndex).fadeIn(500); 
		   $('.slider-item').removeClass('slider-item-selected'); 
		   $('.slider-item').eq(currentIndex).addClass('slider-item-selected'); 
		  }
		  /** 
		   * 开始轮播 
		   */
		  function start() { 
		   if(!hasStarted) { 
		    hasStarted = true; 
		    interval = setInterval(next, t); 
		   } 
		  } 
		  /** 
		   * 停止轮播 
		   */
		  function stop() { 
		   clearInterval(interval); 
		   hasStarted = false; 
		  } 
		  //开始轮播 
		  start();
		  
	function mm(){
	//$("#lunbo").empty();
       $("#showLB").empty();
       $.ajax({
					url : '${ctx}/material/showCarousel.do',// 跳转到 action
					data : {
							imageIds:imageIds,		
					},
					type : 'POST',
					cache : false,
					dataType : 'json',
					success : function(data) {
						if(data.path!=""){
							var lunbo = document.getElementById("lunbo");
							
							for ( var i = 0; i < data.path.length; i++) { 
							        var li= document.createElement("li");
						            li.setAttribute("data-target", "#myCarousel");
						            li.setAttribute("data-slide-to", i);
						            if(i==0){
						            	  li.setAttribute("class", "active");
						            } 
						            lunbo.appendChild(li); 
						            
								
								
								
							   var nn = '';
						       nn += "<div class='item active'>"; 
						       var imgpath = "${ctx}"+data.path[i] 
						       var bb = i+1;
						       nn += "<img src="+imgpath+" alt='First slide' style='height:450px; width:1200px;'>";
						       nn += "<div class='carousel-caption'>轮播图"+bb+"</div>";
						       nn += "</div>";
						     	$("showLB").append(nn);
							}
						}else{
							alert(data.error);
						}			
					},
					error : function() {
						alert("异常！");
					}
		});
	}
	//****************************************上传结束******************************************************
	//校验修改商品名称
	function updateName(){
		var name11 = $("#name1").val();
		var oldName = $("#oldName").val();
		var categoryId = $("#categoryId1").val();
		if(name11.length==0){
			
			return false;
		}
		if(name11.length>30){
			alert("商品名称过长")
			return false;
		}
		//获取品类Id
		var categoryId = $("#categoryId1").val();
		if(categoryId.length==0){
			alert("请选选择品类");
			return false;
		}
		//校验
		$.ajax({
					url : '${ctx}/GoodController/updateName.do',// 跳转到 action
					data : {
							oldName:oldName,
							categoryId:categoryId,
							goodName:name11
					},
					type : 'POST',
					cache : false,
					dataType : 'json',
					success : function(data) {
						data = eval(data);
						if(data=="1"){
							
							alert("该名字已存在");
							$("#name1").focus();
						}else{
							//alert("该名字可用");
						}				
					},
					error : function() {
						alert("异常！");
					}
		});
	}
	//校验新增商品名称
	function addName(){
		var name11 = $("#name").val();
		if(name11.length==0){
			
			return false;
		}
		if(name11.length>30){
			alert("商品名称过长")
			return false;
		}
		//获取品类Id
		var categoryId = $("#categoryId").val();

		if(categoryId.length==0){
			alert("请选选择品类");
			return false;
		}
		//校验
		$.ajax({
					url : '${ctx}/GoodController/addName.do',// 跳转到 action
					data : {
							categoryId:categoryId,
							goodName:name11
					},
					type : 'POST',
					cache : false,
					dataType : 'json',
					success : function(data) {
						data = eval(data);
						
						if(data=="1"){
							alert("该名字已存在");
							$("#name").focus();
						}else{
							//alert("该名字可用");
						
						}				
					},
					error : function() {
						alert("异常！");
					}
		});
	}
	//所有品类树  编辑xuanzeshangpin3
	function getAll1(){
		$.ajax({
					url : '${ctx}/GoodController/getAllCategory.do',// 跳转到 action
					type : 'POST',
					cache : false,
					dataType : 'json',
					success : function(data) {
						//alert(data.length);
						if(data!=""&&data.length>0){
							$('#st_tree1').treeview({
							  color: "#428bca",
							  enableLinks: true,
							  data: data,
							  showBorder: true,
							  expandIcon: 'glyphicon glyphicon-chevron-right',
							  collapseIcon: 'glyphicon glyphicon-chevron-down',
							  //nodeIcon: 'glyphicon glyphicon-file'
							});
							//折叠所有父节点
                   			 $('#st_tree1').treeview('collapseAll', { silent: true });
							//点击事件
							 $('#st_tree1').on('nodeSelected', function(event, data) {
          							if(data.nodes==null||data.nodes==""){	
          								//赋值
          								//alert(data.id);
          								$("#categoryId1").val(data.id);
          								$("#parentIds1").val(data.parentIds);
          								//alert("===="+data.parentIds);
          								$("#categoryName1").val(data.text);
 										$('#xuanzeshangpin3').modal('hide');
          							}
        					});
							
						}
					},
					error : function() {
						alert("异常！");
					}
		});
	}
	//所有品类树  新增xuanzeshangpin2
	function getAll(){
		$.ajax({
					url : '${ctx}/GoodController/getAllCategory.do',// 跳转到 action
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
							  showBorder: true,
							  expandIcon: 'glyphicon glyphicon-chevron-right',
							  collapseIcon: 'glyphicon glyphicon-chevron-down',
							  //nodeIcon: 'glyphicon glyphicon-file'
							});
							//折叠所有父节点
                   			 $('#st_tree').treeview('collapseAll', { silent: true });
							//点击事件
							 $('#st_tree').on('nodeSelected', function(event, data) {
          							if(data.nodes==null||data.nodes==""){	
          								//赋值
          								//alert(data.id);
          								$("#categoryId").val(data.id);
          								$("#parentIds").val(data.parentIds);
          								//alert("==="+data.parentIds);
          								$("#categoryName").val(data.text);
          								cc();	
          							}
        					});
							
						}
					},
					error : function() {
						alert("异常！");
					}
		});
	}
	//自动加载用户品类树
	$(function(){
		//alert("自动加载");
		getUser();
	});
	//用户品类树
	function getUser(){
		$.ajax({
					url : '${ctx}/GoodController/getUserCategory.do',// 跳转到 action
					type : 'POST',
					cache : false,
					dataType : 'json',
					success : function(data) {
						//alert(data.length);
						if(data!=""&&data.length>0){
							$('#tree').treeview({
							  color: "#428bca",
							  enableLinks: true,
							  data: data,
							  showBorder: false,
							  expandIcon: 'glyphicon glyphicon-chevron-right',
							  collapseIcon: 'glyphicon glyphicon-chevron-down',
							  //nodeIcon: 'glyphicon glyphicon-file'
							});
							//折叠所有父节点
                   			 $('#tree').treeview('collapseAll', { silent: true });
							//点击事件
							 $('#tree').on('nodeSelected', function(event, data) {
          							if(data.nodes==null||data.nodes==""){	
          								//赋值
          								//alert(data.id);
          								$("#categoryId2").val(data.id);
          								showGood();
          							}
        					});
							
						}
					},
					error : function() {
						alert("异常！");
					}
		});
	}
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
	//查询商品
	function showGood(){
		//alert("查询");
		$.ajax({
				url : '${ctx}/GoodController/serachGood.do',// 跳转到 action
				data : {
							categoryId:$("#categoryId2").val(),
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
					if(data.mtList.length>0){
						for ( var i = 0; i < data.mtList.length; i++) {
						
							var content = '';
							content += '<tr>';
							var path = "${ctx}"+data.mtList[i].imgPath;
							content += "<td><img src='"+path+"' style='height:50px; width:50px;'></td>";
							content += "<td style='vertical-align:middle'>"+data.mtList[i].name+"</td>";
							content += "<td>";
							if (data.mtList[i].status == 0) {
								content += "<a href='#'  onclick=toEditGood('"+data.mtList[i].goodsId+"'); data-toggle='modal' data-target='#edit' >编辑</a>&nbsp";
								content += "<a href='#' onclick=stopGood('"+data.mtList[i].goodsId+"');>停用</a>";
								content += "&nbsp<a href='#' onclick=seeGood('"+data.mtList[i].goodsId+"'); >详情</a>";
							
							}else{
								content += "<a href='#'  onclick=toEditGood('"+data.mtList[i].goodsId+"'); data-toggle='modal' data-target='#edit' >编辑</a>&nbsp";
								content +="<a href='#' onclick=startGood('"+data.mtList[i].goodsId+"');>启用</a>";
								content += "&nbsp<a href='#' onclick=seeGood('"+data.mtList[i].goodsId+"'); >详情</a>";
							}
							content += "</td>";
							content += '</tr>';
							addTr('showTable', -1, content);
							
						}
						//alert("数据条数="+data.mtCount);
						setPagination(1, 2, data.mtCount);
					}else{
						//alert("没有搜索的结果");
					}
					
				},
				error : function() {
					alert("异常！");
				}
		});		
	}
	function setPagination(curr, limit, totalCount) {
			//alert("分页");
			$('#callBackPager').extendPagination({
				totalCount : totalCount,
				showCount : limit,
				limit : limit,
				callback : function(curr, limit, totalCount) {
					
					$.ajax({
						url : '${ctx}/GoodController/serachGood.do',// 跳转到 action
						data : {
									categoryId:$("#categoryId2").val(),
									pageNo : curr,
									pageSize : limit
						},
						type : 'POST',
						cache : false,
						dataType : 'json',
						success : function(data) {
							data = eval(data);
							//先清空table中的数据
									$("#showTable  tr:not(:first)").remove();
									if(data.mtList.length>0){
											for ( var i = 0; i < data.mtList.length; i++) {
								
													var content = '';
												
													content += '<tr>';
													var path = "${ctx}"+data.mtList[i].imgPath;
													content += "<td><img src='"+path+"' style='height:50px; width:50px;'></td>";
													content += "<td style='vertical-align:middle'>"+data.mtList[i].name+"</td>";
													
													content += "<td>";
													if (data.mtList[i].status == 0) {
														content += "<a href='#'  onclick=toEditGood('"+data.mtList[i].goodsId+"'); data-toggle='modal' data-target='#edit' >编辑</a>&nbsp";
														content += "<a href='#' onclick=stopGood('"+data.mtList[i].goodsId+"');>停用</a>";
														
														content += "&nbsp<a href='#' onclick=seeGood('"+data.mtList[i].goodsId+"'); >详情</a>";
													}else{
														content += "<a href='#'  onclick=toEditGood('"+data.mtList[i].goodsId+"'); data-toggle='modal' data-target='#edit' >编辑</a>&nbsp";
														content +="<a href='#' onclick=startGood('"+data.mtList[i].goodsId+"');>启用</a>";
														content += "&nbsp<a href='#' onclick=seeGood('"+data.mtList[i].goodsId+"'); >详情</a>";
													}
													content += "</td>";
													content += '</tr>';
													addTr('showTable', -1, content);
										
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
	
	//停用商品状态
	function stopGood(goodsId){
		if(goodsId!=""){
			
			$.ajax({
				url:'${ctx}/GoodController/disabledGood.do',
				type: "POST",
	            dataType: "json",
				data:{"goodsId":goodsId},
				success:function(data){
				   	 data = eval(data);
				   	 //如果成功
				   	 if(data!=null&&data== "0"){
				   	 	//提示信息
				   	 	alert("停用商品成功！");
				   	 	//重新查询  categoryId
				   	 	$("#searchButt").click();
				   	 }
				   	 //失败
				   	 else{
				   	 	alert("停用失败！");
				   	 }
					}
				});
	    }
	}
	
	//启用商品
	
	function startGood(goodsId){
		if(goodsId!=""){
			
			$.ajax({
				url:'${ctx}/GoodController/startGood.do',
				type: "POST",
	            dataType: "json",
				data:{"goodsId":goodsId},
				success:function(data){
				   	data = eval(data);
				   	//alert(data);
				   	 //如果成功
				   	 if(data!=null&&data== "0"){
				   	 	//提示成功
				   	 	alert("启用商品成功！");
				   	 	//重新查询  categoryId
				   	 	
				   	 	$("#searchButt").click();
				   	 }
				   	 //失败
				   	 else{
				   	 	alert("启用失败！");
				   	 }
				   	}
				});
	    }
	}
	
	//打开添加
	function openAdd(){
		//alert("添加");
		$("#lunbo1").empty();
		$("#lunbo2").empty();
		$('#addGoodShow2').collapse('hide');
		$('#addGoodShow3').collapse('hide');
		//各个输入框赋清空
				   	 	$("#goodsId").val("");
				   	 	$("#categoryId").val("");
				   	 	$("#categoryName").val("");
				   	 	$("#name").val("");
				   	 	
				   	 	$("#described").val("");
				   	 	//标题图片
				   	 	$("#imgId").val("");
				   	 	$("#imgPath").val("");
				   
				   	 	$("#maxPrice").val("");
				   	 	$("#minPrice").val("");
				   	 	$("#productNum").val("");
				   	 	$("#productTime").val("");
				   	 	$("#factory").val("");
				   	 	$("#createAddress").val("");
				   	 	$("#specification").val("");
				   	 	$("#stockNum").val("");
				   	 	$("#brand").val("");
				   	 	$("#createTime").val("");
				   	 	//清空轮播
				   	 	$("#imageIds").val("");
	}
	//新增商品
	function createGood(){
		$('#collapseOne').collapse('show');
			$('#addGoodShow2').collapse('show');
		//校验新增是否正确
		if(!$("#createGood").validationEngine("validate")){
		
			
			return false;
		}
		
		if(eval($("#maxPrice").val())<eval($("#minPrice").val())){
			alert("请重新输入价格");
			$('#addGoodShow2').collapse('show');
			return false;
		}
	   	 	
		$.ajax({
				url:'${ctx}/GoodController/createGood.do',
				type: "POST",
                dataType: "json",
				data:{	
							createTime : $("#createTime").val(),
							parentIds : $("#parentIds").val(),
							categoryId : $("#categoryId").val(),
							name : $("#name").val(),
							described : $("#described").val(),
							imgId : $("#imgId").val(),
							imgPath : $("#imgPath").val(),
							maxPrice : $("#maxPrice").val(),
							minPrice : $("#minPrice").val(),
							productNum : $("#productNum").val(),
							productTime : $("#productTime").val(),
							factory : $("#factory").val(),
							createAddress : $("#createAddress").val(),
							specification : $("#specification").val(),
							unitSpecification: $("#unitSpecification").val(),
							stockNum : $("#stockNum").val(),
							brand : $("#brand").val(),
							unitPrice : $("#unitPrice").val(),
				            imageIds : $("#imageIds").val(),
				},
				success:function(data){
				   	 data = eval(data);
				   	
				   	 //如果成功
				   	 if(data!=null&&data.successMsg != ""){
				   	 	alert("创建商品成功");
				   	 	//隐藏模态框
				   	 	$("#add").modal('hide');
				   	 	//隐藏值清空
				   	 	//查询新的品类
				   	 	$("#categoryId2").val($("#categoryId").val());
				   	 	delImg();
				   	 	//重新生成品类树重新查询-
				   	 	getUser();
				   	 	$("#searchButt").click();
				   	 }else if(data!=null&&data.errorMsg != ""){
				   	 	 //如果失败
				   	 	alert(data.errorMsg);
				   	 }
				}
			});
			
	}
	//进入编辑商品信息
	function toEditGood(id){
		//alert("编辑商品==");
		$("#lunbo3").empty();
		$("#lunbo4").empty();
		$('#updateGoodShow2').collapse('hide');
		$('#updateGoodShow3').collapse('hide');
		//给编辑框的各个输入框赋清空
				   	 	$("#goodsId1").val("");
				   	 	$("#categoryId1").val("");
				   	 	$("#categoryName1").val("");
				   	 	$("#name1").val("");
				   	 	$("#oldName").val("");
				   	 	$("#described1").val("");
				   	 	//标题图片
				   	 	$("#imgId1").val("");
				   	 	$("#imgPath1").val("");
				   
				   	 	$("#maxPrice1").val("");
				   	 	$("#minPrice1").val("");
				   	 	$("#productNum1").val("");
				   	 	$("#productTime1").val("");
				   	 	$("#factory1").val("");
				   	 	$("#createAddress1").val("");
				   	 	$("#specification1").val("");
				   	 	$("#stockNum1").val("");
				   	 	$("#brand1").val("");
				   	 	$("#createTime1").val("");
				   	 	//清空轮播
				   	 	$("#imageIds1").val("");
	    if(id!=""){
	    	//alert(id);
			//暂时省略校验部分，直接提交
			$.ajax({
				url:'${ctx}/GoodController/toupdateGood.do',
				type: "POST",
	            dataType: "json",
				data:{"goodsId":id},
				success:function(data){
				   	 data = eval(data);
				   	 //alert(data);
				   	 //如果成功
				   	 if(data!=null){
				   	 	//给编辑框的各个输入框赋值
				   	 	$("#goodsId1").val(data.goodsId);
				   	 	$("#categoryId1").val(data.categoryId);
				   	 	$("#categoryName1").val(data.categoryName);
				   	 	$("#name1").val(data.name);
				   	 	$("#oldName").val(data.name);
				   	 	$("#described1").val(data.described);
				   	 	//标题图片
				   	 	$("#imgId1").val(data.imgId);
				   	 	$("#imgPath1").val(data.imgPath);
				   	 	var path = "${ctx}"+data.imgPath
				   	 	$("#imgShow1").attr("src",path);
				   	 	$("#maxPrice1").val(data.maxPrice);
				   	 	$("#minPrice1").val(data.minPrice);
				   	 	$("#productNum1").val(data.productNum);
				   	 	$("#productTime1").val(data.productTime);
				   	 	$("#factory1").val(data.factory);
				   	 	$("#createAddress1").val(data.createAddress);
				   	 	$("#specification1").val(data.specification);
				   	 	$("#stockNum1").val(data.stockNum);
				   	 	$("#brand1").val(data.brand); 
				   	 	$("#createTime1").val($.changeDate(data.createTime))
				   	 	//下拉列表，默认选中的单位
				   	 	$("#unitPrice1").children("option").each(function(){  
				              var temp_value = $(this).val();  
				              if(temp_value == data.unitPrice){  
				                    $(this).attr("selected","selected");  
				              }  
				        });
				        //规格单位 unitSpecification1
				        $("#unitSpecification1").children("option").each(function(){  
				              var temp_value = $(this).val();  
				              if(temp_value == data.unitSpecification1){  
				                    $(this).attr("selected","selected");  
				              }  
				        });
				   	 	//轮播图插入 获取图片Id的集合 赋值调用方法
				   	 	var imgIds = "";
				   	 	if(data.imglist!=null&&data.imglist.length>0){
				   	 		//alert(data.imglist.length);
				   	 		$("#lunbo1").empty();
							$("#lunbo2").empty();
							$("#lunbo3").empty();
							$("#lunbo4").empty();
				   	 		 var lunbo3 = document.getElementById("lunbo3");
							var lunbo4 =document.getElementById("lunbo4");
				   	 		for ( var i = 0; i < data.imglist.length; i++) {
										 var id = i+1;
										 var li= document.createElement("li");  
										 var path = "${ctx}"+  data.imglist[i].materialPath ;
									     var href_a = document.createElement("a");  
									             
									            href_a.target="_blank"
									            href_a.innerHTML ="<img  src='"+path+"' >";  
									            li.id=id;
									            li.setAttribute("class","slider-panel");  
									            li.appendChild(href_a);  
									            lunbo3.appendChild(li); 
									            
									    var li1= document.createElement("li");  
									            li1.id=id;  
									            li1.setAttribute("class", "slider-item");  
									            li1.innerHTML=id;  
									            lunbo4.appendChild(li1); 
										imgIds += data.imglist[i].id+",";
							} 
							//去掉最后一个,
							imgIds = imgIds.substring(0,imgIds.length-1);
							$("#imageIds1").val(imgIds);
							aa();
				   	 	}

				   	 	//打开编辑模态框
				   	 	$("#edit").modal('show');
				   	 }
				   	 else{
				   	 	//如果失败
				   	 	alert("系统错误！");
				   	 }
					}
				});
	    }
	}
	//编辑商品
	function updateGood(){
		$('#updateGoodShow1').collapse('show');
			$('#updateGoodShow2').collapse('show');
		//校验新增是否正确
		if(!$("#updateGood").validationEngine("validate")){

			return false;
		}

		if(eval($("#maxPrice1").val())<eval($("#minPrice1").val())){
			alert("请重新输入价格");
			$('#updateGoodShow2').collapse('show');
			return false;
		}
		
		$.ajax({
				url:'${ctx}/GoodController/updateGood.do',
				type: "POST",
                dataType: "json",
				data:{	
							
							goodsId1 : $("#goodsId1").val(),
							createTime1 : $("#createTime1").val(),
							parentIds1 : $("#parentIds1").val(),
							categoryId1 : $("#categoryId1").val(),
							name1 : $("#name1").val(),
							described1 : $("#described1").val(),
							imgId1 : $("#imgId1").val(),
							imgPath1 : $("#imgPath1").val(),
							maxPrice1 : $("#maxPrice1").val(),
							minPrice1 : $("#minPrice1").val(),
							productNum1 : $("#productNum1").val(),
							productTime1 : $("#productTime1").val(),
							factory1 : $("#factory1").val(),
							createAddress1 : $("#createAddress1").val(),
							specification1 : $("#specification1").val(),
							unitSpecification1: $("#unitSpecification1").val(),
							stockNum1 : $("#stockNum1").val(),
							brand1 : $("#brand1").val(),
							unitPrice1 : $("#unitPrice1").val(),
				            imageIds1 : $("#imageIds1").val(),
				},
				success:function(data){
				   	 data = eval(data);
				   	// alert(data);
				   	 //如果成功
				   	 if(data!=null&&data.successMsg != ""){
				   	 	alert(data.successMsg);
				   	 	//隐藏模态框
				   	 	$("#edit").modal('hide');
				   	 	//隐藏值清空
				   	 	delUpdateImg();
				   	 	//重新查询
				   	 	$("#searchButt").click();
				   	 }else if(data!=null&&data.errorMsg != ""){
				   	 	 //如果失败
				   	 	alert(data.errorMsg);
				   	 }
				}
			});
	}

</script>
</head>

<body style="padding-left:16px;">
<div class="panel panel-default" style="margin-top:10px; box-shadow:3px 3px 8px rgba(0,0,0,0.1); margin-right:1%; height:auto;">
<!---------------------------------------con top  start-------------------------------------------------------------->
    <div class="con_top">
        <p>商品管理</p>
    </div>
<!---------------------------------------con top  over--------------------------------------------------------------->
<!---------------------------------------nav start--------------------------------------------------------------->
<div>
<ul class="A_b_2_nav_left" style=" float:left;">
	<div id="tree"></div>
	
</ul>
<!--商品信息-->
<div class="container-fluid" style="float:left; width:80%">
	
    <div class="row-fluid">
        <!-- col-sm-12 -->
        <div class="col-sm-12 ">
        	<div class="panel panel-default article-bj">
                <div class="panel-heading">
                商品信息
                </div>
                    <div class="table-responsive panel-table-body ">
                        <table class="table table-striped table-hover" id="showTable">
                            <thead>
                                <tr>
                                    <th>图片</th>
                                    <th>商品名称</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<input type="hidden" id="searchButt" onclick="showGood();"/>
                            </tbody>
                        </table>
                    </div> 
                  
                    <footer class="panel-footer text-right bg-light lter">
                    
                    <input type="button" class="btn btn-warning btn-s-xs" data-toggle="modal" data-target="#add" value="添加" onclick="openAdd();" />
                    <!--资料库管理
                     <input type="button" class="btn btn-warning btn-s-xs"  value="资料库管理" onclick="openBase();" />
                     -->
                    <div id="callBackPager" float="right;"></div>
                   
                    </footer>
                    
                   
                     
        		</div>
       		 </div>
        </div>
    </div> 
</div>

<!-- 查询商品-->

<div class="clear"></div>
<!---------------------------------------nav  over--------------------------------------------------------------->
</div>
<!--编辑商品-->
<form id="updateGood" 　method="post">
		<input type="hidden"  name="categoryId2" id="categoryId2" "/>
		<input type="hidden"  name="categoryId1" id="categoryId1" "/>
		<input type="hidden"  name="parentIds1" id="parentIds1" "/>
		<input type="hidden" id="goodsId1" name="goodsId1"/>
		<input type="hidden" id="oldName" name="oldName"/>
		<input type="hidden" id="imgId1" />
		<input type="hidden" id="imgPath1" />
		<input type="hidden" id="imageIds1" />
<!--编辑商品-->
<div class="modal fade" id="edit"  role="dialog" aria-labelledby="gridSystemModalLabel" style="overflow-y: visible"; data-backdrop="static">
	<div class="modal-dialog tanchu-box" role="document" style="width:70%">  
        <div class="container-fluid container-margin">
            <div class="row-fluid">
                <!-- col-sm-12 -->
                <div class="col-sm-12 ">
                    <div class="panel panel-default article-bj">
                        <div class="panel-heading box-shodm modal-draggable">
                        编辑商品
                         <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        	<div class="row wrapper form-margin" style="margin:20px 40px;">
                            	
     						
     						
     						<div class="panel-group" id="accordion1">

                              <div class="panel panel-default" style="margin:7px 7px;">
                                
                                <div id="collapseOne1" >

                                  <div class="panel-body">
                                  
                                  
                                <div class="row wrapper">
                                    <div class="col-md-5">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 class="aa3-h5-margin">选择分类:</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             
                                             <input type="text" placeholder="" class="form-control validate[required]" name="categoryName1" id="categoryName1" readonly="readonly">
                                        </div>
                                    </div>
                                    <div class="col-md-2  ">
                                    	
                                        <button class="btn btn-warning btn-default  " type="button" data-toggle="modal" data-target="#xuanzeshangpin3" type="button" onclick="getAll1();">选择分类</button>
                                    </div>
                                </div>
                          
                                <div class="row wrapper" style="margin-top:20px;margin-bottom:20px;">
                                    <div class="col-md-12">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 class="aa3-h5-margin">标题图片:</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <img src="images/contentp.png" style="height:80px; width:80px;" id="imgShow1">
                                             
                                             <button class="btn btn-success btn-default" type="button"  onclick="updateImg();" style="margin-left:110px; margin-top:45px;">选择</button>
                                         </div>
                                    </div>
                                </div>
                                
                                
                                 </div>
                                </div>
                              </div>
                              
                              
                              <div class="panel panel-default" style="margin:7px 7px;">
                                <div class="panel-heading">
                                  <h5 class="panel-title" style="font-size:14px;">
                                    <a data-toggle="collapse" data-parent="#accordion" 
                                      href="#updateGoodShow2">
                                      商品详情
                                    </a>
                                  </h5>
                                </div>
                                <div id="updateGoodShow2" class="panel-collapse collapse">

                                  <div class="panel-body">
                                  
                                  
                                <div class="row wrapper form-margin">
                                    <div class="col-md-5">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 class="aa3-h5-margin">名称:</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <input type="text" placeholder="" class="form-control validate[required,maxSize[50]]" name="name1" id="name1" onblur="updateName();">
                                        </div>
                                    </div>
                                    <div class="col-md-5">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 class="aa3-h5-margin">生产日期:</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <input type="text" placeholder="" class="form-control validate[required,custom[date]]" name="createTime1" id="createTime1">
                                             
                                        </div>
                                    </div>
                                </div>
                                <div class="row wrapper form-margin">
                                    <div class="col-md-5">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 class="aa3-h5-margin">生产地址:</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <input type="text" placeholder="" class="form-control validate[maxSize[50]]" name="createAddress1" id="createAddress1">
                                        </div>
                                    </div>
                                    <div class="col-md-5">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 class="aa3-h5-margin">生产产家:</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <input type="text" placeholder="" class="form-control validate[maxSize[50]]" name="factory1" id="factory1">
                                        </div>
                                    </div>
                                </div> 
                                <div class="row wrapper form-margin">
                                    <div class="col-md-5">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 class="aa3-h5-margin">生产编号:</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <input type="text" placeholder="" class="form-control validate[maxSize[50]]" name="productNum1" id="productNum1">
                                        </div>
                                    </div>
                                    <div class="col-md-5">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 class="aa3-h5-margin">品牌:</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <input type="text" placeholder="" class="form-control validate[maxSize[50]]" name="brand1" id="brand1">
                                        </div>
                                    </div>
                                </div>
                                <div class="row wrapper form-margin">
                                    <div class="col-md-5">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 class="aa3-h5-margin">库存:</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <input type="text" placeholder=""  class="form-control validate[required,custom[integer]]" name="stockNum1" id="stockNum1">
                                        </div>
                                    </div>
                                    <div class="col-md-5">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 class="aa3-h5-margin">保质期:</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <input type="text" placeholder=""class="form-control validate[required,maxSize[50]]" name="productTime1" id="productTime1">
                                        </div>
                                    </div>
                                </div>
                                <div class="row wrapper form-margin">
                                    <div class="col-md-4">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 class="aa3-h5-margin">规格:</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <input type="text" placeholder="" class="form-control validate[maxSize[50]]"  name="specification1" id="specification1" >
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="input-group">
                                             <div class="input-group-btn">
                                              <h5 ></h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <select class="form-control" name="unitSpecification1" id="unitSpecification1" tabindex="-1">
                                                <option value="1">千克</option>
                                                <option value="2">克</option>
                                             </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row wrapper form-margin">
                                    <div class="col-md-4">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 class="aa3-h5-margin">价格:</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <input type="text" placeholder="" class="ui_timepicker form-control validate[required,custom[number]]"  name="minPrice1" id="minPrice1" >
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 style="margin-right:19px;">至</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <input type="text" placeholder="" class="ui_timepicker form-control validate[required,custom[number]]"  name="maxPrice1" id="maxPrice1">
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="input-group">
                                             <div class="input-group-btn">
                                              <h5 style="margin-right:10px;"></h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <select class="form-control" id="unitPrice1" name="unitPrice1"  tabindex="-1">
                                                <option value="￥">￥</option>
                                            	<option value="$">$</option>
                                             </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row wrapper form-margin">
                                    <div class="col-md-12" style="margin-top:15px;">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 class="aa3-h5-margin">描述:</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <textarea rows="3"  class="form-control validate[maxSize[200]]" style="width:80%" id="described1" name="described1">
                                             </textarea>
                                        </div>
                                    </div>
                                </div>
                                
                                
                                
                                </div>
                                </div>
                              </div>
                              
                              
                              <div class="panel panel-default" style="margin:7px 7px;">
                                <div class="panel-heading">
                                  <h5 class="panel-title" style="font-size:14px;">
                                    <a data-toggle="collapse" data-parent="#accordion" 
                                      href="#updateGoodShow3">
                                      轮播图片
                                    </a>
                                  </h5>
                                </div>
                                <div id="updateGoodShow3" class="panel-collapse collapse">
                                  <div class="panel-body">
                                  
                                  
                                  
                                
                             </div>
                            <div class="row wrapper form-margin "  >
                             <div class="slider" > 
						      <ul class="slider-main" id="lunbo3"> 
						        
						      </ul> 
						      <div class="slider-extra" style="margin: 0px auto; width:90%; "> 
						       <ul class="slider-nav" id="lunbo4"> 
						        
						       </ul> 
						         <div class="slider-page"> 
						        <a class="slider-pre" href="javascript:;;"><</a> 
						        <a class="slider-next" href="javascript:;;">></a> 
						       </div> 
						      </div> 
						     </div> 
						     </div> 
                            <div class="row wrapper" >
                                <div class="col-md-12" style="text-align: center; margin-bottom:20px;">
                                         <button class="btn btn-success btn-s-xs " type="button"  onclick="updateCarousel();">添加</button>
                                         <button class="btn btn-info btn-s-xs " type="button" onclick="dellunbo2();">清空</button>
                                </div>
                            </div>
                            
                            
                              </div>
                                </div>
                              </div>

                            </div>
                            
                            <footer class="panel-footer text-right bg-light lter">
                                 <button class="btn btn-warning btn-s-xs" type="button" onclick="updateGood();">保存</button>
                            </footer>
                        </div>
                     </div>
                </div>
            </div> 
        </div>
	</div> 
</div>
</form>
<!--增加商品-->
<form id="createGood" 　method="post">
	
	<input type="hidden" id = "categoryId" name="categoryId" />
	<input type="hidden" id = "parentIds" name="parentIds" />
	<input type="hidden" id = "imgId"  />
	<input type="hidden" id = "imgPath"  />
	<input type="hidden" id="imageIds" />
	<div class="modal fade" id="add"  role="dialog" aria-labelledby="gridSystemModalLabel" style="overflow-y: visible;" data-backdrop="static">
	<div class="modal-dialog tanchu-box" role="document" style="width:65%"> 
        <div class="container-fluid container-margin">
            <div class="row-fluid">
                <!-- col-sm-12 -->
                <div class="col-sm-12 ">
                    <div class="panel panel-default article-bj">
                        <div class="panel-heading box-shodm modal-draggable">
                        创建商品
                         <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        
                        
                        <div class="panel-group" id="accordion">
                        
                        
                        <div class="panel panel-default" style="margin:7px 7px;" >
                                
                                <div id="collapseOne" >

                                  <div class="panel-body">
                                  
                                  
                                  
                                  
                        	<div class="row wrapper form-margin" style="margin:20px 40px;">
                            	<div class="row wrapper">
                                    <div class="col-md-5">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 class="aa3-h5-margin">选择分类:</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             
                                             <input type="text" placeholder="" class="form-control validate[required]" name="categoryName" id="categoryName" readonly="readonly">
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                    	
                                        <button class="btn btn-warning btn-default  " type="button" data-toggle="modal" data-target="#xuanzeshangpin2" type="button" onclick="getAll();">选择分类</button>
                                    </div>
                                </div>
                                <div class="row wrapper" style="margin-top:20px;margin-bottom:20px;">
                                    <div class="col-md-12">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 class="aa3-h5-margin">标题图片:</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <img src="images/contentp.png" style="height:80px; width:80px;" id="imgShow">
                                             
                                             <button class="btn btn-success btn-default" type="button"  onclick="addImg();" style="margin-left:110px; margin-top:45px;">选择</button>
                                         </div>
                                    </div>
                                </div>
                                
                                  </div>
                                </div>
                              </div>
                             
                              
                              
                              <div class="panel panel-default" style="margin:7px 7px;"  >
                             
                                <div class="panel-heading">
                                  <h5 class="panel-title" style="font-size:14px;">
                                    <a data-toggle="collapse" data-parent="#accordion" 
                                      href="#addGoodShow2">
                                      商品详情                                                       
                                    </a>
                                  </h5>
                                </div>
                                <div id="addGoodShow2" class="panel-collapse collapse" >

                                  <div class="panel-body"  >
                                  
                                  
                                  
                              
                                <div class="row wrapper form-margin">
                                    <div class="col-md-5">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 class="aa3-h5-margin">名称:</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <input type="text" class="form-control validate[required,maxSize[50]]" name="name" id="name" placeholder="请输入简称" 
											data-bv-notempty data-bv-notempty-message="简称不能为空！" onblur="addName();">
                                        </div>
                                    </div>
                                    <div class="col-md-5">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 class="aa3-h5-margin">生产日期:</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <input type="text" placeholder="" class="form-control validate[required,custom[date]]" name="createTime" id="createTime">
                                             
                                        </div>
                                    </div>
                                </div>
                                <div class="row wrapper form-margin">
                                    <div class="col-md-5">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 class="aa3-h5-margin">生产地址:</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <input type="text" placeholder="" class="form-control validate[maxSize[50]]" name="createAddress" id="createAddress">
                                        </div>
                                    </div>
                                    <div class="col-md-5">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 class="aa3-h5-margin">生产产家:</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <input type="text" placeholder="" class="form-control validate[maxSize[50]]" name="factory" id="factory">
                                        </div>
                                    </div>
                                </div> 
                                <div class="row wrapper form-margin">
                                    <div class="col-md-5">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 class="aa3-h5-margin">生产编号:</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <input type="text" placeholder="" class="form-control validate[maxSize[50]]" name="productNum" id="productNum">
                                        </div>
                                    </div>
                                    <div class="col-md-5">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 class="aa3-h5-margin">品牌:</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <input type="text" placeholder="" class="form-control validate[maxSize[50]]" name="brand" id="brand">
                                        </div>
                                    </div>
                                </div>
                                <div class="row wrapper form-margin">
                                    <div class="col-md-5">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 class="aa3-h5-margin">库存:</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <input type="text" placeholder=""  class="form-control validate[required,custom[integer]]" name="stockNum" id="stockNum">
                                        </div>
                                    </div>
                                    <div class="col-md-5">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 class="aa3-h5-margin">保质期:</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <input type="text" placeholder=""class="form-control validate[required,maxSize[50]]" name="productTime" id="productTime">
                                        </div>
                                    </div>
                                </div>
                                <div class="row wrapper form-margin">
                                    <div class="col-md-4">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 class="aa3-h5-margin">规格:</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <input type="text" placeholder="" class="form-control validate[maxSize[50]]"  name="specification" id="specification" >
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="input-group">
                                             <div class="input-group-btn">
                                              <h5 ></h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <select class="form-control" name="unitSpecification" id="unitSpecification" tabindex="-1">
                                                <option value="1">千克</option>
                                                <option value="2">克</option>
                                             </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row wrapper form-margin">
                                    <div class="col-md-4">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 class="aa3-h5-margin">价格:</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <input type="text" placeholder="" class="ui_timepicker form-control validate[required,custom[number]]"  name="minPrice" id="minPrice" >
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 style="margin-right:19px;">至</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <input type="text" placeholder="" class="ui_timepicker form-control validate[required,custom[number]]"  name="maxPrice" id="maxPrice">
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="input-group">
                                             <div class="input-group-btn">
                                              <h5 style="margin-right:10px;"></h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <select class="form-control" id="unitPrice" name="unitPrice"  tabindex="-1">
                                                <option value="￥">￥</option>
                                            	<option value="$">$</option>
                                             </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row wrapper form-margin">
                                    <div class="col-md-12" style="margin-top:15px;">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                              <h5 class="aa3-h5-margin">描述:</h5>
                                              <!--   <button class="btn" type="button"></button> -->
                                             </div>
                                             <textarea rows="3" style="width:80%" id="described" class="form-control validate[maxSize[200]]"  name="described">
                                             </textarea>
                                        </div>
                                    </div>
                                </div>
                                
                                
                                 </div>
                                </div>
                              </div>
                            
                              
                              <div class="panel panel-default" style="margin:7px 7px;" >
                                <div class="panel-heading">
                                  <h5 class="panel-title" style="font-size:14px;">
                                    <a data-toggle="collapse" data-parent="#accordion" 
                                      href="#addGoodShow3">
                                      轮播图片
                                    </a>
                                  </h5>
                                </div>
                                <div id="addGoodShow3" class="panel-collapse collapse">
                                  <div class="panel-body">
                              
                              
                              
                                
                             </div>
                            <div class="row wrapper form-margin "  >
                             <div class="slider" > 
						      <ul class="slider-main" id="lunbo1"> 
						        
						      </ul> 
						      <div class="slider-extra" style="margin: 0px auto;width:90%; "> 
						       <ul class="slider-nav" id="lunbo2"> 
						        
						       </ul> 
						         <div class="slider-page"> 
						        <a class="slider-pre" href="javascript:;;"><</a> 
						        <a class="slider-next" href="javascript:;;">></a> 
						       </div> 
						      </div> 
						     </div> 
						     </div> 
                            <div class="row wrapper"  >
                                <div class="col-md-12" style="text-align: center; margin-bottom:20px;">
                                         <button class="btn btn-success btn-s-xs " type="button"  onclick="addCarousel();">添加</button>
                                         <button class="btn btn-info btn-s-xs " type="button" onclick="dellunbo1();">清空</button>
                                </div>
                            </div>
                            
                            
                             </div> 
						      </div> 
						     </div> 
						     
						     </div>
                            
                            <footer class="panel-footer text-right bg-light lter">
                                 <button class="btn btn-warning btn-s-xs" type="button" onclick="createGood();">保存</button>
                            </footer>
                        </div>
                     </div>
                     
                     
                      
                      
                </div>
            </div> 
        </div>
	</div> 
</div>
 </form>

<!--选择商品 start------>
<div class="modal fade" id="xuanzeshangpin2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">

    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header modal-draggable">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="panel-title">选择商品分类</h3>
            </div>
            <!--ztree start-->
            <div id="st_tree" style="float:left; margin-left:20px; border:none;"></div>
            
            <div class="clearfix"></div>
        </div>
    </div>
</div>

<div class="modal fade" id="xuanzeshangpin3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">

    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header modal-draggable">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="panel-title">选择商品分类</h3>
            </div>
            <!--ztree start-->
            <div id="st_tree1" style="float:left; margin-left:20px; border:none;"></div>
            
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<!------ 选择商品 over------>
<!--添加轮播图片-->
<div class="modal fade" id="addCarousel" role="dialog" aria-labelledby="gridSystemModalLabel" data-backdrop="static">
	<div class="modal-dialog tanchu-box" role="document" style="width:50%"> 
        <div class="container-fluid container-margin">
            <div class="row-fluid">
                <!-- col-sm-12 -->
                <div class="col-sm-12 ">
                    <div class="panel panel-default article-bj">
                        <div class="panel-heading box-shodm modal-draggable">
                        添加轮播图片
                        </div>
                        <div class="row wrapper form-margin" style="margin:20px 40px;">
                            <div class="col-md-4">
                                <img src="images/contentp.png" class="img-thumbnail">
                            </div>
                            <div class="col-md-4">
                                <img src="images/contentp.png" class="img-thumbnail" >
                            </div>
                            <div class="col-md-4">
                                <img src="images/contentp.png" class="img-thumbnail">
                            </div>
                            <div class="col-md-4">
                                <img src="images/contentp.png" class="img-thumbnail aa3_img_margin">
                            </div>
                            <div class="col-md-4">
                                <img src="images/contentp.png" class="img-thumbnail aa3_img_margin" >
                            </div>
                            <div class="col-md-4">
                                <img src="images/contentp.png" class="img-thumbnail aa3_img_margin">
                            </div>
                            <div class="col-md-4">
                                <img src="images/contentp.png" class="img-thumbnail aa3_img_margin">
                            </div>
                            <div class="col-md-4">
                                <img src="images/contentp.png" class="img-thumbnail aa3_img_margin">
                            </div>
                            <div class="col-md-4">
                                <img src="images/contentp.png" class="img-thumbnail aa3_img_margin">
                            </div>
                        </div>
                        <footer class="panel-footer text-right bg-light lter">
                             <button class="btn btn-warning btn-s-xs" type="submit" data-dismiss="modal">确定</button>
                        </footer>
                     </div>
                </div>
            </div> 
        </div>
	</div> 
</div>

<div class="modal fade" id="goodlook" role="dialog" aria-labelledby="gridSystemModalLabel" data-backdrop="static"></div>
<!--嵌入资源库管理页面
<script type="text/javascript">
	function openBase(){
	 
		$("#childPage1").modal('show');
	} 
	
	
</script>

<div class="modal fade" id="childPage1" role="dialog" aria-labelledby="gridSystemModalLabel" >
	<div class="modal-dialog tanchu-box" role="document" style="width:80%">
		<iframe  id="childBase1" name="childBase1"  src="${ctx}/mbase/toshowBase.do"  width="100%" height="100%" frameborder="0"  scrolling="auto"/>
	</div>
</div>
-->





<!--添加标题图片-->
<div class="modal fade" id="addTitlePic" role="dialog" aria-labelledby="gridSystemModalLabel" data-backdrop="static">
	<div class="modal-dialog s-tanchu-box" role="document" style="width:20%"> 
        <div class="container-fluid container-margin">
            <div class="row-fluid">
                <!-- col-sm-12 -->
                <div class="col-sm-12 ">
                    <div class="panel panel-default article-bj">
                        <div class="panel-heading box-shodm modal-draggable">
                        添加标题图片
                        </div>
                        <div class="row wrapper form-margin" style="margin:20px 20px;">
                            <div class="col-md-4">
                                <span  class="glyphicon glyphicon-leaf aa3_title_pic"></span>
                            </div>
                            <div class="col-md-4">
                                <span  class="glyphicon glyphicon-tint aa3_title_pic"></span>
                            </div>
                            <div class="col-md-4">
                                <span  class="glyphicon glyphicon-star-empty aa3_title_pic"></span>
                            </div>
                         </div>
                         <div class="row wrapper form-margin" style="margin:20px 20px;">
                            <div class="col-md-4">
                                <span  class="glyphicon glyphicon-star aa3_title_pic"></span>
                            </div>
                            <div class="col-md-4">
                                <span  class="glyphicon glyphicon-send aa3_title_pic"></span>
                            </div>
                            <div class="col-md-4">
                                <span  class="glyphicon glyphicon-shopping-cart aa3_title_pic"></span>
                            </div>
                        </div>
                        <div class="row wrapper form-margin" style="margin:20px 20px;">
                            <div class="col-md-4">
                                <span  class="glyphicon glyphicon-leaf aa3_title_pic"></span>
                            </div>
                            <div class="col-md-4">
                                <span  class="glyphicon glyphicon-tint aa3_title_pic"></span>
                            </div>
                            <div class="col-md-4">
                                <span  class="glyphicon glyphicon-star-empty aa3_title_pic"></span>
                            </div>
                        </div>
                        <footer class="panel-footer text-right bg-light lter">
                             <button class="btn btn-warning btn-s-xs" type="submit" data-dismiss="modal">确定</button>
                        </footer>
                     </div>
                </div>
            </div> 
        </div>
	</div> 
</div>

<!-- 嵌入资料库-->
<div class="modal fade " id="childPage" role="dialog" aria-labelledby="gridSystemModalLabel" style="width:1200px; height:750px;" data-backdrop="static" >
	
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
                         <iframe  id="childBase" name="childBase"  src="${ctx}/mbase/toshowBase1.do" width="100%" height="100%" frameborder="0"  class=" modal-draggable " />
                   	    </div>
                    </div>
                </div>
            </div>
        </div>
		
	
</div>
</body>
</html>
