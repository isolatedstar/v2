<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />   <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
   <title>商城报价搜索</title>
<link href="${ctx}/mall/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${ctx}/mall/css/mall.css" rel="stylesheet" type="text/css">
<link href="${ctx}/mall/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="${ctx}/mall/css/media.css" rel="stylesheet" type="text/css">
<script src="${ctx}/mall/js/jquery.js"></script>
<script src="${ctx}/mall/js/jquery.min.js"></script>
<script src="${ctx}/mall/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="${ctx}/mall/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/mall/js/bootstrap-treeview.js"></script>
<script src="${ctx}/mall/js/serializeJson.js"></script>
<script type="text/javascript" src="${ctx}/mall/js/extendPagination.js"></script>
 <script src="${ctx}/common/js/common.js"></script>
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
});
</script>

<script>
	$(document).ready(function(e) {
	    $('.m_con_top_p2').click(function(){
			
			$(this).next('.content_2ul').slideToggle(100)
			
		})
	});
	

	//自动加载
	$(function(){
		//alert("自动加载");
		var type = '${type!}';
		//设置默认选中
		//报价类型判断
   	 	$("#showType").children("option").each(function(){  
              var temp_value = $(this).val();  
              if(temp_value == type){  
                    $(this).attr("selected","selected");  
              }  
         });
		//alert("====="+type);
		showShop();
		
		 
});
	//搜索隐藏
	function cloasSearch(){
		 
			$('.content_2ul').hide();
			//$('.m_con_top_p2').next('.content_2ul').slideToggle(100);
			
		
	}
	//显示
	function showShop(){
		cloasSearch();
		//获取选中的多选框按钮的值
  		var checked = $("[name =checkboxId]:checked");
  		
		var stringsId = "";
		checked.each(function(i,thisElement){
			if(thisElement.value!=""){
				stringsId+=thisElement.value+",";
			}
		});
		//去掉最后一个逗号
		stringsId = stringsId.substring(0,stringsId.length-1);
		//alert("=======++++++++++++++++++++++++++++++"+stringsId);
		if(stringsId==""){
			//alert("默认选中的除去公开报价之外的所有报价");
			stringsId = "1,2,3";
			 var arr = stringsId.split(",");

            var inputs = document.getElementsByName("checkboxId");

            for (var i = 0; i < arr.length; i++) {

                for (var j = 0; j < inputs.length; j++) {

                    if (arr[i] == inputs[j].value) {

                        inputs[j].checked = true;

                        break;

                    }

                }

            }
		}
		//alert("======="+stringsId);
		var name = $("#goodname").val();
		if(name=="请输入商品"){
			name="";
		}
		$.ajax({
				url : '${ctx}/QuoteController/serachQuote.do',// 跳转到 action
				data : {	
							categoryId:$("#checkCategoryId").val(),
							goodname:name,
							checkBoxId:stringsId,
							type : $("#showType").val(),
							pageNo : 1,
							pageSize : 9,
							
				},
				type : 'POST',
				cache : false,
				dataType : 'json',
				success : function(data) {
					data = eval(data);
					//清空div
					//alert("循环");
					$("#showQuote").empty();
							if(data.qtList.length>0){
								//alert("循环");
								for ( var i = 0; i < data.qtList.length; i++) {
										
										var content = '';
										var path = "${ctx}"+data.qtList[i].imgPath;
										content += "<div class='content_div'>";
										content += "<div class='content_div_img'><img src="+path+" style='width:240px; height:200px;'/></div>";
										content += "<div class='content_div_txt'>";
										content += "<p class='content_div_p1'><span class='div_p4_span'>"+data.qtList[i].goodsName+"</span>&nbsp;&nbsp;&nbsp;&nbsp;存货量："+data.qtList[i].num+"</p>";
										content += "<p class='content_div_p2'>价格："+data.qtList[i].minPrice+" ~ "+data.qtList[i].maxPrice+""+data.qtList[i].unitPrice+"&nbsp;&nbsp;&nbsp;&nbsp;<a href='#' onclick=quoteDetail('"+data.qtList[i].id+"')><span>查看明细</span></a></p>";
										content += " <p class='content_div_p3'>有效期："+$.changeDate(data.qtList[i].startTime)+"~ "+$.changeDate(data.qtList[i].startEnd)+" </p>";
										content += "<p class='content_div_p4'>发布人：<span onclick=priviewMmb('"+data.qtList[i].mmbId+"')  class='div_p4_span'>"+data.qtList[i].mmbName+"</span></p>";
										//
										content += "<p class='content_div_p5' onclick=javascript:addShpc('"+data.qtList[i].id+"'); >添加购物车</p>";
										content += "</div>";
										content += "</div>";
										
										 $("#showQuote").append(content);
						
								}
								
								 var mm = '';
								mm += "<div class='clear'>";
								mm += "</div>";
								 $("#showQuote").append(mm);
								setPagination(1, 9, data.qtCount);
								$("#callBackPager").show();
							}else{
									//alert("无数据！");
									$("#callBackPager").hide();
						}
					},
					error : function() {
						alert("异常！");
						
					}
			});	
	}
	
	function setPagination(curr, limit, totalCount) {
			cloasSearch();
			$('#callBackPager').extendPagination({
				totalCount : totalCount,
				showCount : limit,
				limit : limit,
				callback : function(curr, limit, totalCount) {
					var name = $("#goodname").val();
					if(name=="请输入商品"){
						name="";
					}
					//获取选中的多选框按钮的值
			  		var checked = $("[name =checkboxId]:checked");
			  		
					var stringsId = "";
					checked.each(function(i,thisElement){
						if(thisElement.value!=""){
							stringsId+=thisElement.value+",";
						}
					});
					//去掉最后一个逗号
					stringsId = stringsId.substring(0,stringsId.length-1);
					
					if(stringsId==""){
						//alert("默认选中的除去公开报价之外的所有报价");
						stringsId = "1,2,3";
					}
					$.ajax({
						url : '${ctx}/QuoteController/serachQuote.do',// 跳转到 action
						data : {
							categoryId:$("#checkCategoryId").val(),
							goodname:name,
							checkBoxId:stringsId,
							type : $("#showType").val(),
							pageNo : curr,
							pageSize : limit
						},
						type : 'POST',
						cache : false,
						dataType : 'json',
						success : function(data) {
							data = eval(data);
					//清空div
					$("#showQuote").empty();
							if(data.qtList.length>0){
								for ( var i = 0; i < data.qtList.length; i++) {
										
										var content = '';
										var path = "${ctx}"+data.qtList[i].imgPath;
										content += "<div class='content_div'>";
										content += "<div class='content_div_img'><img src="+path+" style='width:260px; height:200px;'/></div>";
										content += "<div class='content_div_txt'>";
										content += "<p class='content_div_p1'><span class='div_p4_span'>"+data.qtList[i].goodsName+"</span>&nbsp;&nbsp;&nbsp;&nbsp;存货量："+data.qtList[i].num+"</p>";
										content += "<p class='content_div_p2'>价格："+data.qtList[i].minPrice+" ~ "+data.qtList[i].maxPrice+""+data.qtList[i].unitPrice+"&nbsp;&nbsp;&nbsp;&nbsp;<a href='#' onclick=quoteDetail('"+data.qtList[i].id+"')><span>查看明细</span></a></p>";
										content += " <p class='content_div_p3'>有效期："+$.changeDate(data.qtList[i].startTime)+"~ "+$.changeDate(data.qtList[i].startEnd)+" </p>";
										content += "<p class='content_div_p4' >发布人：<span onclick=priviewMmb('"+data.qtList[i].mmbId+"')  class='div_p4_span'>"+data.qtList[i].mmbName+"</span></p>";
										content += "<button onclick=addShpc('"+data.qtList[i].id+"'); >添加购物车</button>";
										content += "</div>";
										content += "</div>";
										
										 $("#showQuote").append(content);
						
								}
								var mm = '';
								mm += "<div class='clear'>";
								mm += "</div>";
								 $("#showQuote").append(mm);
								
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
		//添加到购物车
		function addShpc(quoteId){
			$.ajax({
				url:'${ctx}/shpc/addQuoteToSHPC.do',
				type: "POST",
	            dataType: "json",
				data:{"quoteId":quoteId},
				success:function(data){
				   	data = eval(data);
				   	//alert(data);
				   	 //如果成功
				   	 if(data=="0"){
				   	 	//提示成功
				   	 	alert("添加购物车成功！");
				   	 }else if(data=="2"){
				   	 	alert("该报价已放入购物车");
				   	 }else if(data=="3"){
				   	 	alert("该商品已存在于购物车");
				   	 }else{
				   	 	 //失败
				   	 	alert("添加失败！");
				   	 }
				   	 
				 }
			});
		}
		//跳转购物车采购页面
		function showShpc(){
			var type = $("#showType").val();
			//type=0搜索采购 跳转销售  type=1搜索销售 跳转采购
			if(type=="0"){
				window.location.href="${ctx}/shpc/toshowShoc1.do";
			}
			if(type=="1"){
				window.location.href="${ctx}/shpc/toshowShoc0.do";
			}
			
		}
		//报价详情
		function quoteDetail(quoteId){
			window.open("${ctx}/QuoteController/quoteDetail.do?quoteId="+quoteId);
			//window.location.href="${ctx}/QuoteController/quoteDetail.do?quoteId='"+quoteId+"'";
		}
		//预览
		function priviewMmb(mmbId){
			
			$.ajax({
				url : '${ctx}/mmbwesite/viewMmbHtml.do',// 跳转到 action
				data : {
							mmbId : mmbId,
				},
				type : 'POST',
				cache : false,
				dataType : 'json',
				success : function(data) {
					//data = eval(data);
					if(data!=null&&data!=""){
						if (data.substr(0,1)=='/'){
							data=data.substr(1);
						}
						
						var path = '${Request.basePath}'+data;
						window.open(path);
					}else{
						alert("该会员没有配置主页");
					}
				},
				
			});	
		}
		//选择品类
		function checkCategory(id){
			
			$("#checkCategoryId").val(id);
			showShop();
			$("#checkCategoryId").val("");
			$('.m_menu_ul').hover(function(){},function(){
					$(this).hide()
			})
		}
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
	<div class="m_content_top">
    	<div class="m_con_top_p" >
        	  <label>报价类型：</label>
        	  <input type="hidden" id="checkCategoryId">
			<select id="showType">
				 <option value="0" >可销售</option>
            	<option value="1" >可采购</option>
               
            </select>
            <p class="m_con_top_p2">搜索范围</p>
             		<ul class="content_2ul" id="content_2ul2">
                        <li><input type="checkbox" name="checkboxId" value="2"   /><span>查业务合作会员发的报价</span></li>
                        <li><input type="checkbox" name="checkboxId" value="1"   /><span>查我关注会员发的报价</span></li>
                        <li><input type="checkbox" name="checkboxId" value="3"     /><span>查我所在群组收到的报价</span></li>
                        <li><input type="checkbox" name="checkboxId" value="0" /><span>查我所在省的公开报价</span></li>
                    </ul>

        </div>
        <form class="m_sech_form">
        	<input id="goodname" name="goodname" class="m_sech_text" type="text" value="请输入商品"/><input id="con_sh_btn" class="m_sech_btn" type="button" value="搜索商品" onclick="showShop();"/>
			<div class="con_shapping_box">
				<i class="fa fa-shopping-cart">
				<span style="padding-left:5px;" onclick="showShpc();">购物车</span>
				</i>
			</div> 
        </form>
	</div>

		<!------------------------------------------nav start----------------------------------------------------------->
     <div class="m_nav">
        <div class="m_menu"><p>商品分类<i class="fa fa-qrcode"></i></p></div>
        <div class="m_menu_2"><p><i class="fa fa-list"></i></p></div>
        <ul class="m_menu_ul">
              <li><a href='#'>粮油</a></li>
			  <ul class='m_subnav'>
			    <li><a href='#'>面粉</a></li>
			    <ul class='m_subnav_2'>
			      <li><a href='#' onclick=checkCategory('1001001001')>袋装面粉</a></li>
			      <li><a href='#' onclick=checkCategory('1001001002')>散装面粉</a></li>
			    </ul>
			    <li><a href='#'>大米</a></li>
			    <ul class='m_subnav_2'>
			      <li><a href='#' onclick=checkCategory('1001002001')>袋装大米</a></li>
			      <li><a href='#' onclick=checkCategory('1001002002')>散装大米</a></li>
			    </ul>
			    <li><a href='#' onclick=checkCategory('1001003')>杂粮</a></li>
			    <li><a href='#'>食用油</a></li>
			    <ul class='m_subnav_2'>
			      <li><a href='#' onclick=checkCategory('1001004001')>瓶装油</a></li>
			      <li><a href='#' onclick=checkCategory('1001004002')>散装油</a></li>
			    </ul>
			    <li><a href='#' onclick=checkCategory('1001005')>其他粮油</a></li>
			  </ul>
			<li><a href='#'>副食品</a></li>
			  <ul class='m_subnav'>
			    <li><a href='#'>蔬菜</a></li>
			    <ul class='m_subnav_2'>
			      <li><a href='#' onclick=checkCategory('1002001001')>叶花类菜</a></li>
			      <li><a href='#' onclick=checkCategory('1002001002')>瓜果类菜</a></li>
			      <li><a href='#' onclick=checkCategory('1002001003')>根茎类菜</a></li>
			      <li><a href='#' onclick=checkCategory('1002001004')>其他蔬菜</a></li>
			    </ul>
			    <li><a href='#'>果品</a></li>
			    <ul class='m_subnav_2'>
			      <li><a href='#' onclick=checkCategory('1002002001')>鲜果</a></li>
			      <li><a href='#' onclick=checkCategory('1002002002')>干果</a></li>
			      <li><a href='#' onclick=checkCategory('1002002003')>其他果品</a></li>
			    </ul>
			    <li><a href='#'>畜类</a></li>
			    <ul class='m_subnav_2'>
			      <li><a href='#' onclick=checkCategory('1002003001')>畜类鲜品</a></li>
			      <li><a href='#' onclick=checkCategory('1002003002')>畜类冻品</a></li>
			      <li><a href='#' onclick=checkCategory('1002003003')>畜类干品</a></li>
			    </ul>
			    <li><a href='#'>禽类</a></li>
			    <ul class='m_subnav_2'>
			      <li><a href='#' onclick=checkCategory('1002004001')>禽类鲜品</a></li>
			      <li><a href='#' onclick=checkCategory('1002004002')>禽类冻品</a></li>
			      <li><a href='#' onclick=checkCategory('1002004003')>禽类干品</a></li>
			    </ul>
			    <li><a href='#'>水产</a></li>
			    <ul class='m_subnav_2'>
			      <li><a href='#' onclick=checkCategory('1002005001')>水产鲜品</a></li>
			      <li><a href='#' onclick=checkCategory('1002005002')>水产冻品</a></li>
			      <li><a href='#' onclick=checkCategory('1002005003')>水产干品</a></li>
			    </ul>
			    <li><a href='#' onclick=checkCategory('1002006')>蛋类</a></li>
			    <li><a href='#' onclick=checkCategory('1002007')>其他副食</a></li>
			  </ul>
			<li><a href='#'>加工食品</a></li>
			  <ul class='m_subnav'>
			    <li><a href='#' onclick=checkCategory('1003001')>面制品</a></li>
			    <li><a href='#' onclick=checkCategory('1003002')>豆制品</a></li>
			    <li><a href='#' onclick=checkCategory('1003003')>乳制品</a></li>
			    <li><a href='#' onclick=checkCategory('1003004')>酒类</a></li>
			    <li><a href='#' onclick=checkCategory('1003005')>无酒精饮料</a></li>
			    <li><a href='#' onclick=checkCategory('1003006')>冲泡品</a></li>
			    <li><a href='#' onclick=checkCategory('1003007')>糖果糕点</a></li>
			    <li><a href='#' onclick=checkCategory('1003008')>干货炒货</a></li>
			    <li><a href='#' onclick=checkCategory('1003009')>其他加工食品</a></li>
			  </ul>
			<li><a href='#'>调味品</a></li>
			  <ul class='m_subnav'>
			    <li><a href='#' onclick=checkCategory('1004001')>基础调味品</a></li>
			    <li><a href='#' onclick=checkCategory('1004002')>酿造类调味品</a></li>
			    <li><a href='#' onclick=checkCategory('1004003')>腌菜类调味品</a></li>
			    <li><a href='#' onclick=checkCategory('1004004')>鲜菜类调味品</a></li>
			    <li><a href='#' onclick=checkCategory('1004005')>干货类调味品</a></li>
			    <li><a href='#' onclick=checkCategory('1004006')>水产类调味品</a></li>
			    <li><a href='#' onclick=checkCategory('1004007')>其他调味品</a></li>
			  </ul>
			<li><a href='#'>文具办公</a></li>
			  <ul class='m_subnav'>
			    <li><a href='#' onclick=checkCategory('1005001')>办公设备</a></li>
			    <li><a href='#' onclick=checkCategory('1005002')>办公耗材</a></li>
			    <li><a href='#' onclick=checkCategory('1005003')>教学用具</a></li>
			    <li><a href='#' onclick=checkCategory('1005004')>娱乐用品</a></li>
			    <li><a href='#' onclick=checkCategory('1005005')>其他文具</a></li>
			  </ul>
			<li><a href='#'>日用品</a></li>
			  <ul class='m_subnav'>
			    <li><a href='#' onclick=checkCategory('1006001')>家纺</a></li>
			    <li><a href='#' onclick=checkCategory('1006002')>家具</a></li>
			    <li><a href='#' onclick=checkCategory('1006003')>器皿</a></li>
			    <li><a href='#' onclick=checkCategory('1006004')>灯具</a></li>
			    <li><a href='#' onclick=checkCategory('1006005')>卫生用品</a></li>
			    <li><a href='#' onclick=checkCategory('1006006')>厨房用品</a></li>
			    <li><a href='#' onclick=checkCategory('1006007')>其他日用品</a></li>
			  </ul>
			<li><a href='#'>工具材料</a></li>
			  <ul class='m_subnav'>
			    <li><a href='#' onclick=checkCategory('1007001')>包装材料</a></li>
			    <li><a href='#' onclick=checkCategory('1007002')>电工工具</a></li>
			    <li><a href='#' onclick=checkCategory('1007003')>木工工具</a></li>
			    <li><a href='#' onclick=checkCategory('1007004')>园艺工具</a></li>
			    <li><a href='#' onclick=checkCategory('1007005')>水暖配件</a></li>
			    <li><a href='#' onclick=checkCategory('1007006')>墙面材料</a></li>
			    <li><a href='#' onclick=checkCategory('1007007')>装饰用品</a></li>
			    <li><a href='#' onclick=checkCategory('1007008')>其他工具材料</a></li>
			  </ul>

        </ul>
    </div>
</div>
<!------------------------------------------nav over------------------------------------------------------------>
<!------------------------------------------conter bot start------------------------------------------------------------>
<div class="content_box_warp" id="showQuote">
   
    
    
</div>
<footer class="panel-footer text-right bg-light lter">
                    
        <div id="callBackPager" float="right;"></div>
                   
</footer>
<!------------------------------------------conter bot over------------------------------------------------------------>

<div class="footer">

<div class="footer_in_bot">
        	<ul class="footer_in_ul">
            	<li><a href="http://www.nxdjfuw.org.cn/html/001.htm">中国教育后勤协会密函[2014]7号</a><span>|</span></li>
                <li><a href="http://www.nxdjfuw.org.cn/html/002.htm">关于我们<span>|</span></a></li>
                <li><a href="http://www.nxdjfuw.org.cn/html/003.htm">服务条款<span>|</span></a></li>
                <li><a href="http://www.nxdjfuw.org.cn/html/004.htm">友情链接<span>|</span></a></li>
                <li><a href="http://www.nxdjfuw.org.cn/html/005.htm">广告服务<span>|</span></a></li>
                <li><a href="http://www.nxdjfuw.org.cn/html/006.htm">法律声明<span>|</span></a></li>
                <li><a href="http://www.nxdjfuw.org.cn/html/007.htm">联系我们<span>|</span></a></li>
                <li><a href="http://www.nxdjfuw.org.cn/html/008.htm">帮助中心<span>|</span></a></li>
                <li><a href="http://www.bjzllh.com/join.php">诚聘精英</a></li>
            </ul>
            <div class="clear"></div>
        	<p>支持单位：教育部 农业部 商务部 中华全国供销合作总社 主办单位：中国教育后勤协会</p>
        	<p>地址：北京市海淀区上园村3号北京交通大学科技大厦1402室</p>
        	<p>中联联合投资控股集团有限公司版权所有©京ICP证：010026 海淀公安局网络备案编号：京公网安备 11010802010990</p>
        	<p><a href="http://www.nxdjfuw.org.cn/html/010.htm">电信与信息服务业务经营许可证 140335</a></p>
        	<p>电话：010-62513620 邮箱：zhouyi@nxdjfuw.org.cn</p>
        </div>
</div> 
</div>
</body>
</html>