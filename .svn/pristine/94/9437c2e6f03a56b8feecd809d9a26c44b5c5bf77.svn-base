<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />   <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>会员商品列表</title>


<link href="${ctx}/mall/css/mall.css" rel="stylesheet" type="text/css">
<link href="${ctx}/mall/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="${ctx}/mall/css/media_hy.css" rel="stylesheet" type="text/css">
<script src="${ctx}/mall/js/jquery.js"></script>
<script src="${ctx}/mall/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<style>
.m_menu i{display:none;}
	.m_menu{ font-size:32px; width:221px; height:64px; line-height:64px;}
	.content_box_warp{margin-top:40px;}
	.m_menu_ul{width:220px;}
	.m_menu_ul>li{width:220px; height:56px;}
	.m_menu_ul>li>a{font-size:28px; line-height:56px; background-image:none;}
	.m_subnav>li{width:220px; height:56px; }
	.m_subnav>li>a{font-size:28px; line-height:56px; }
	.m_subnav_2 li{width:220px; height:56px;}
	.m_subnav_2 li a{ font-size:28px; line-height:56px;}




</style>
<script>

	$(function(){
			//初始化
			getUser();
			//初始化显示所有商品
			showGood("");
			
	});
     //动态加载会员品类
    function getUser(){
    	 var mmbId = '${mmbId!}';
		$.ajax({
					url : '${ctx}/GoodController/getUserCategoryByMmbIdAnonymously.do',// 跳转到 action
					type : 'POST',
					cache : false,
					dataType : 'json',
					data : {
							relaMmbId : mmbId
							
					},
					success : function(data) {
						data = eval(data);
						var categoryId = "";
						if(data!=""&&data.length>0){
							var countent ='';
							for ( var i = 0; i < data.length; i++) {
								
								if(data[i].nodes!=null&&data[i].nodes!=""&&data[i].nodes.length>0){
									countent += "<li><a href='#'>"+data[i].text+"</a></li>";
									countent +="<ul class='m_subnav'>";
									for ( var m = 0; m < data[i].nodes.length; m++) {
										if(data[i].nodes[m].nodes!=null&&data[i].nodes[m].nodes!=""&&data[i].nodes[m].nodes.length>0){
											
											countent +="<li><a href='#'>"+data[i].nodes[m].text+"</a></li>"
											countent +="<ul class='m_subnav_2'>";
											
											for ( var n = 0; n < data[i].nodes[m].nodes.length; n++) {
												
                           						countent +="<li><a href='#' onclick=showGood('"+data[i].nodes[m].nodes[n].id+"') >"+data[i].nodes[m].nodes[n].text+"</a></li>";
											}
											countent +=" </ul>";
											
										}else{
											countent +="<li><a href='#'  onclick=showGood('"+data[i].nodes[m].id+"')  >"+data[i].nodes[m].text+"</a></li>";
										}
										
									}
									countent +=" </ul>";
									
								}else{
									countent += "<li><a href='#' onclick=showGood('"+data[i].id+"') >"+data[i].text+"</a></li>";
								}
								
							}
								
								$("#showCategory").append(countent);
								
							$('.m_menu').hover(function(){
								$('.m_subnav').hide()
								$('.m_menu_ul>li>a').css('background-position','right top')
								$('.m_menu_ul').show();
								},function(){
									})
							$('.m_menu_ul').hover(function(){
								},function(){
								$(this).hide()
									})
							$('.m_menu_2').hover(function(){
								$('.m_subnav').hide()
								$('.m_menu_ul>li>a').css('background-position','right top')
								$('.m_menu_ul').show();
								},function(){
									})
						    $('.m_menu_ul>li').click(function(){
								$('.m_subnav').stop().slideUp()
								$(this).next('.m_subnav').stop().slideToggle(100);
								$('.m_menu_ul>li>a').css('background-position','right top')
								$(this).find('a').css('background-position','right bottom')
								})
							$('.m_subnav li').click(function(){
								$('.m_subnav_2').stop().slideUp()
								$(this).next('.m_subnav_2').stop().slideToggle(100);
								})
							$('.content_div_txt a').onclick = function(){
								this.href = show();
							}
							
						}
				}
		});
	}
	//根据品类查询商品
	function showGood(categoryId){
		//alert(categoryId);
		 var mmbId = '${mmbId!}';
		 $("#showCategoryId").val(categoryId);
		
		$.ajax({
				url : '${ctx}/GoodController/serachGoodAnonymously.do',// 跳转到 action
				data : {
							mmbId:mmbId,
							categoryId:categoryId
							
				},
				type : 'POST',
				cache : false,
				dataType : 'json',
				success : function(data) {
					data = eval(data);
					//先清空table中的数据
					$("#showGood").empty();
					
					if(data.mtList.length>0){
						for ( var i = 0; i < data.mtList.length; i++) {
							var content = '';
							content += "<div class='content_div'>";
							var path = "${ctx}"+data.mtList[i].imgPath;
							content += "<div class='content_div_img'>";
							content += "<img src="+path+" style='width:240px; height:200px;'/></div>";
							content += "<div class='content_div_txt'>";
							
							content += "<p class='content_div_p1'>";
							content += "<span class='div_p4_span'>"+data.mtList[i].name+"</span>&nbsp;&nbsp;&nbsp;&nbsp;存货量："+data.mtList[i].stockNum+"</p>";
							content += "<a class='content_a' href='#' onclick=lookGood('"+data.mtList[i].goodsId+"')>查看详情</a>";
							
							
							content += "<div class='clear'></div>";
							content +=" </div>";
							 content+= "</div>";
							$("#showGood").append(content);
							
						}
						 var mm = '';
						mm += "<div class='clear'>";
						mm += "</div>";
						$("#showGood").append(mm);
						
						
					}else{
						
					}
					
				},
				error : function() {
					alert("异常！");
				}
		});	
	}
	function setPagination(curr, limit, totalCount) {
			var mmbId = '${mmbId!}';
			var categoryId = $("#showCategoryId").val();
			$('#callBackPager').extendPagination({
				totalCount : totalCount,
				showCount : limit,
				limit : limit,
				callback : function(curr, limit, totalCount) {
					
					$.ajax({
							url : '${ctx}/GoodController/serachGood.do',// 跳转到 action
							data : {
										mmbId:mmbId,
										categoryId:categoryId,
										pageNo : curr,
										pageSize : limit
							},
							type : 'POST',
							cache : false,
							dataType : 'json',
							success : function(data) {
								data = eval(data);
								//先清空table中的数据
								$("#showGood").empty();
								if(data.mtList.length>0){
									for ( var i = 0; i < data.mtList.length; i++) {
										var content = '';
										content += "<div class='content_div'>";
										var path = "${ctx}"+data.mtList[i].imgPath;
										content += "<div class='content_div_img'>";
										content += "<img src="+path+" style='width:240px; height:200px;'/></div>";
										content += "<div class='content_div_txt'>";
										
										content += "<p class='content_div_p1'>";
										content += "<span class='div_p4_span'>"+data.mtList[i].name+"</span>&nbsp;&nbsp;&nbsp;&nbsp;存货量："+data.mtList[i].stockNum+"</p>";
										content += "<a href='#' onclick=lookGood('"+data.mtList[i].goodsId+"')>查看详情</a>";
										
										
										content += "<div class='clear'></div>";
										content +=" </div>";
										content += "</div>";
										$("#showGood").append(content);
									}
									 var mm = '';
									mm += "<div class='clear'>";
									mm += "</div>";
									$("#showGood").append(mm);
									
								}else{
									alert("没有搜索的结果");
								}
								
							},
							error : function() {
								alert("异常！");
							}
					});	
				}
			});
		}
	//查看详情页面
	function lookGood(goodId){
		window.location = "${ctx}/GoodController/mmbGoodLookAnonymously.do?goodId="+goodId;
	}
	
	
	
	$(document).ready(function(e) {
    $('.content_div_txt a').onclick = function(){
		this.href = show();
		}
});
</script>
<script>
$(document).ready(function(e) {
	$('.m_menu').hover(function(){
		$('.m_subnav').hide()
		$('.m_menu_ul>li>a').css('background-position','right top')
		$('.m_menu_ul').show();
		},function(){
			})
	$('.m_menu_ul').hover(function(){
		},function(){
		$(this).hide()
			})
	$('.m_menu_2').hover(function(){
		$('.m_subnav').hide()
		$('.m_menu_ul>li>a').css('background-position','right top')
		$('.m_menu_ul').show();
		},function(){
			})
    $('.m_menu_ul>li').click(function(){
		$('.m_subnav').stop().slideUp()
		$(this).next('.m_subnav').stop().slideToggle(100);
		$('.m_menu_ul>li>a').css('background-position','right top')
		$(this).find('a').css('background-position','right bottom')
		})
	$('.m_subnav li').click(function(){
		$('.m_subnav_2').stop().slideUp()
		$(this).next('.m_subnav_2').stop().slideToggle(100);
		})
})
</script>
</head>

<body>
<div id="mallwarp" >
<!----------------------header start--------------------------->
	<div class="m_header">
    	<img src="${ctx}/mall/images/logo.png"/>
        <h2>农校对接</h2>
       
    </div>
<!----------------------header over--------------------------->
<div class="m_con_box">
		<!------------------------------------------nav start----------------------------------------------------------->
     <div class="m_nav">
        <div class="m_menu"><p>商品分类<i class="fa fa-qrcode"></i></p></div>
         <input type="hidden"  id="showCategoryId" />
        <div class="m_menu_2"><p><i class="fa fa-list"></i></p></div>
       
        <ul class="m_menu_ul" id="showCategory">
           
        </ul>
    </div>
</div>
<!------------------------------------------nav over------------------------------------------------------------>
<!------------------------------------------conter bot start------------------------------------------------------------>
<div class="content_box_warp" id="showGood">
    
  
    
     
   
   
<!------------------------------------------conter bot over------------------------------------------------------------>

</div>
<!-------
<footer class="panel-footer text-right bg-light lter">
                    
        <div id="callBackPager" float="right;"></div>
                   
</footer>
--->
</body>
</html>
