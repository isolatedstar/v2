<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="Content-Language" content="zh-CN">
<title>${mmbName}</title>
<link href="${rootUrl}/mall/css/mall.css" rel="stylesheet" type="text/css">
<link href="${rootUrl}/mall/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="${rootUrl}/mall/css/media2.css" rel="stylesheet" type="text/css">
<script src="${rootUrl}/mall/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="${rootUrl}/common/js/common.js"></script>
<script>
   
	
</script>
    <script>
    
    
    
        window.onload = function(){
            var box = document.getElementById('box');
            var list = document.getElementById('list');
            var buttons = document.getElementById('buttons').getElementsByTagName('span');
            var prev = document.getElementById('prev');
            var next = document.getElementById('next');
            var index = 1;
            var time;
            var animated = false;
            function showButtons(){
                for(var i=0; i<buttons.length;i++){
                    if(buttons[i].className == 'on'){
                      buttons[i].className = '';
                }
            }
                buttons[index-1].className = 'on';
            };
            for(var i=0; i<buttons.length; i++){
                if(this.className == 'on'){
                    return;
                }
                buttons[i].onclick = function(){
                    var myIndex = parseInt(this.getAttribute('index'));
                    var offset = -1280*(myIndex - index);
                    if(!animated){
                        animate(offset);
                    }

                    index = myIndex;
                    showButtons();
                };
            };
            function animate(offset){
                animated = true;
                var newLeft = parseInt(list.style.left) + offset;

                var time = 300; //位移总时间
                var interval = 10; //位移间隔
                var speed = offset/(time/interval); //每次移动多少距离
                var buttons = document.getElementById('buttons').getElementsByTagName('span');
                var num = buttons.length;
                
                function go(){
                    if( (speed < 0 && parseInt(list.style.left) > newLeft) || ( speed > 0 && parseInt(list.style.left) < newLeft) ){
                        list.style.left = parseInt(list.style.left) + speed + 'px';
                        setTimeout(go,interval)
                    }else {
                        animated = false;
                        list.style.left = newLeft + 'px';
                        if(newLeft > -1280){
                            list.style.left = -1280*num + 'px'
                        }
                        if(newLeft < -1280*num){
                            list.style.left = -1280 + 'px'
                        }
                    }
                }
                go();
            }
            next.onclick = function(){
            	var buttons = document.getElementById('buttons').getElementsByTagName('span');
                var num = buttons.length;
                if(index == num){
                    index = 1;
                }else {
                    index += 1;
                }
                if(!animated){
                    animate(-1280)
                }
                showButtons()
            };
            prev.onclick = function(){
            	var buttons = document.getElementById('buttons').getElementsByTagName('span');
                var num = buttons.length;
                if(index == 1){
                    index = num
                }else {
                    index -=1;
                }
                if(!animated){
                    animate(1280);
                }
                showButtons()
            };
            function play(){
                time = setInterval(function(){
                    next.onclick();
                },3000)
            }
            function stop(){
                clearInterval(time)
            }
            box.onmouseover = stop;
            box.onmouseout = play;
        };
        function addMmbId(){
        	var mmbId = $("#relaMmbId").val()
        	//windos.location = '${rootUrl}/mmbwesite/followMmbRedirectBefore.do?relaMmbId='+mmbId;
        	$.ajax({
				url:'${rootUrl}/mmbwesite/followMmbRedirectBefore.do?relaMmbId='+mmbId,
				type: "GET",
                dataType: "json",
				
				success:function(data){
				   	 data = eval(data);
				   	 //如果成功
				   	 alert(data);
				   	 if(data!=""){
				   	 	window.location = "http://www.baidu.com";
				   	 }
				}
			});
			
        }
        //会员商品页面
        function toGood(){
        	var mmbId = $("#relaMmbId").val();
        	//alert("${rootUrl}");
        	
        	//window.open = "${rootUrl}/GoodController/mmbGoodsAnonymously.do?mmbId="+mmbId;
        	
        	window.open("${rootUrl}/GoodController/mmbGoodsAnonymously.do?mmbId="+mmbId, "_blank");
        }
        
    </script>
    

</head>

<body>
<div class="h_member">
	<div class="h_header">
    	<p>中国农校对接服务网</p>
        <div class="h_header_right">
        	<a href="${rootUrl}/QuoteController/toshowShop1RedirectBefore.do" ><span>向我买</span></a>
            <a href="${rootUrl}/QuoteController/toshowShopRedirectBefore.do"><span>向我卖</span></a>
            <input type="hidden" id="relaMmbId" value="${mmbId}" />
        </div>
    </div>
    
    <!--banner start-->
    <div id="box">
       <#if homepage??>
        <div id="list" style="left:-1280px;">
        	 <#list homepage as text1>
	        	<#if !text1_has_next>
			      <div class="one">
                		<img src="${rootUrl}${text1.materialPath!}" alt="${homepage?size}" ><span>${text1.remark1!}</span>
            		</div>
			    </#if>
		    </#list>
           <#list homepage as text>
    				 <div class="one">
                		<img src="${rootUrl}${text.materialPath!}" alt="${text_index+1}" ><span>${text.remark1!}</span>
            		</div>
			</#list>
			<#list homepage as text2>
	        	<#if text2_index == 0>
			      <div class="one">
                		<img src="${rootUrl}${text2.materialPath!}" alt="1" ><span>${text2.remark1!}</span>
            		</div>
			    </#if>
		    </#list>
        </div>
        <div id="buttons">
            <#list homepage as text>
            	<#if text_index == 0>
            		<span index="1" class="on"></span>
            	</#if>
            	<#if text_index != 0>
            		<span index="${text_index+1}"></span>
            	</#if>
    				 
			</#list>
        </div>
        <a href="#" id="next" class="arrow">&gt;</a>
        <a href="#" id="prev" class="arrow">&lt;</a>
        </#if>
    </div>
    <!--banner over-->
	<div class="h_culture">
    	<h2>企业介绍</h2>
        <p>${mmbIntroduce}</p>
    </div>
    <div class="quality" id="showPic">
    <a href="#" class="h_culture_btn" onclick="toGood();">商品详情</a>
        <div class="clear"></div>
    <#if pic??>
    	<h2>资质展示</h2>
    	<#list pic as text>
    				
					<div class="quality_l">
			        	<img src="${rootUrl}${text.materialPath!}" style="width:337px;height:246px;"/>
			            <div>${text.remark2!}</div>
			        </div>
		</#list>
		<div class="clear"></div>
	</#if>
    </div>
    <div class="clear"></div>
    
    <div class="login">
    	<a href="${rootUrl}/QuoteController/toshowShopRedirectBefore.do" class="login_l"  target="_Blank" >登录商城</a>
		<a href="#" onclick="addMmbId();" class="login_r">关注我们</a>      
    </div>
    <div class="h_footer">
    	<p class="h_footer_l">欢迎关注<中国农校对接服务网>会员主页</p>
        <div class="h_footer_r">
            <img src="${rootUrl}${imgPath}" style="width:160px; height:160px;"/>  
            <p>官方二维码<p>
        </div> 
        
    </div>
    <div class="clear"></div>
    
    
    
    
</div>
</body>
</html>
