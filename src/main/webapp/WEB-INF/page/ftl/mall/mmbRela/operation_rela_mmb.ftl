<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />   <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>无标题文档</title>
    <script src="${ctx}/mall/js/jquery.js"></script>
    <link href="${ctx}/mall/css/pulic.css" rel="stylesheet" type="text/css">
	<link href="${ctx}/mall/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<script src="${ctx}/mall/js/jquery-1.11.1.min.js" type="text/javascript"></script>
 	<script src="${ctx}/mall/js/bootstrap.min.js" type="text/javascript"></script>
<script>
$(document).ready(function(e) {
    $('.A_b_2_nav_left>li').click(function(){
		$('.A_b_2_subnav').slideUp()
		$(this).next('ul').stop().slideDown()
		});
	$('.A_b_2_subnav li a').click(function(){
		$('.A_b_2_subnav li a').css('color','#333')
		$(this).css('color','#4aa3df')
		$($(this).attr('href')).show()
		});
	
});


//动态查询合作类型会员
function queryBizTypeMmbs(type){
	var mmbId = $("#mmbId").val();

	if(type!=""&&mmbId!=""){
		$.ajax({
			url : '${ctx}/mmbRela/getMmbsByType.do',// 跳转到 action
			data : {
				mmbId : mmbId,
				type : type
			},
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data) {
				var content = "";
				if(data!=null&&data!=""&&data.length>0){
					for(var i=0;i<data.length;i++){
						content+="<li><a href='#' onclick=showDetail('"+data[i].memberId+"')>"+data[i].fname+"</a></li>"
					}
					//如果是等级一
					if(type!=""&&type=="0"){
						//清空买中的内容
						$("#buy").empty();
						$("#buy").append(content);
						$("#bizType").val(type);
					}
					else if(type!=""&&type=="1"){
						//清空卖中的内容
						$("#sell").empty();
						$("#sell").append(content);
						$("#bizType").val(type);
					}
					else if(type!=""&&type=="2"){
						//清空借中的内容
						$("#borrow").empty();
						$("#borrow").append(content);
						$("#bizType").val(type);
					}
					else if(type!=""&&type=="3"){
						//清空贷中的内容
						$("#loan").empty();
						$("#loan").append(content);
						$("#bizType").val(type);
					}
				}
			},
			error : function() {
				alert("异常！");
			}
		});
	}
}

//点击关注会员，动态查询其数据
function showDetail(mmid){
	var type = $("#bizType").val();

	var currentMmbId = $("#mmbId").val();//当前登录会员ID
	var relaMmbId = mmid;//点击的待查看详情会员ID

    $("#mmbId_").val(currentMmbId); //当前登录会员ID
    $("#relationMmbId").val(relaMmbId);//待查看详情会员ID

	var updateBizHtml = getUpdateBizHtml();

	if(mmid!=null&&mmid!=""){
		$.ajax({
			url : '${ctx}/mmb/queryMMBByMid.do',// 跳转到 action
			data : {
				mmbId : mmid,
				type :"1",
                operateType:type
			},
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data) {
				//清空div中信息
				$("#all").show();
				$("#showDetail").empty();
				var content = "";
				if(data!=""&&data!=null){

					$("#relationshipId").val(data.relaId);
					//卖的关系
					if(type !=null && type == 1){
                        $("#mmbId_").val(data.relaMmbId_);
                        $("#relationMmbId").val(data.mmbId_);
					}else{
                        $("#mmbId_").val(data.mmbId_);
                        $("#relationMmbId").val(data.relaMmbId_);
					}

                    if(data.mmbHomepage != null && data.mmbHomepage != ""){
                        content+="<li>"+data.mmbFname+"&nbsp;&nbsp;<a href='#' onclick =openHomePage('"+data.mmbHomepage+"')>主页</a></li>";
                    }else{
                        content+="<li>"+data.mmbFname+"&nbsp;&nbsp;<span><a href=javascript:alert('对方未配置主页!')>主页</a></span></li>";
                    }
                    content+="<li>注册地址:&nbsp;&nbsp;<lable>"+data.mmbAddress+"</lable></li>";

					content+= updateBizHtml;

					content+="<li style='margin-top:10px;'>";
					//content+="<input class='btn btn-primary' onclick='openUpdateBizModal()' style='margin-right:10px;' type='button' value='调整关系'>";
					content+='<input class="btn btn-primary" onclick="lowerToConcern()" type="button" value="降级为仅关注">&nbsp;&nbsp;';
					content+="<a href='#' onclick='toAddContract()'><input class='btn btn-primary' type='button' value='创建合作协议'></a>";
					content+="</li>";
				}
				$("#showDetail").append(content);
				$("#showDetail").show();
			},
			error : function() {
				alert("异常！");
			}
		});
	}
}

/**
 * 弹出用户 主页
 * @param homePage
 */
function openHomePage(homePage){
	window.open(homePage);
}


//动态拼接 停用、启用 业务关系
function getUpdateBizHtml(){
    //获取mmbId以及当前打开的relaMmbId,查询买卖业务关系是否存在
    var mmbId = $("#mmbId_").val();
    var relaMmbId = $("#relationMmbId").val();

	var bizHtml ="";
    if(mmbId!=""&&relaMmbId!=""){
        //ajax请求查询返回两种业务是否存在
        $.ajax({
            url : '${ctx}/mmbRela/toUpgradebizOperation.do',// 跳转到 action
            data : {
                mmbId : mmbId,
                relaMmbId :relaMmbId
            },
            type : 'POST',
            cache : false,
            async : false,//同步ajax
            dataType : 'json',
            success : function(data) {
                var content = "";
                if(data!=null&&data.buyBiz!=""){

                    //如果存在当前会员与打开的关注会员存在买业务
                    if(data.buyBiz=="0"){

                        //显示停用
                        content += "<label>我能采购</label><input type='button' onclick='stopBuyBiz(1)' class='btn btn-default' value='已启用，点击停用' style='width:130px; height:25px; line-height:12px; padding:5px 5px' /><br/>";

                    }
                    else{
                        //显示启用
                        content += "<label>我能采购</label><input type=button  onclick='openBuyBiz(1)' class='btn btn-default' value='已停用，点击启用' style='width:130px; height:25px; line-height:12px; padding:5px 5px' /><br/>";

                    }
                }
                if(data!=null&&data.sellBiz!=""){
                    //如果存在当前会员与打开的关注会员存在卖业务
                    if(data.sellBiz=="0"){
                        //显示停用
                        content += "<label>我能销售</label><input type='button' class='btn btn-default' onclick='stopBuyBiz(2)' value='已启用，点击停用' style='width:130px; height:25px; line-height:12px; padding:5px 5px' />";

                    }
                    else{
                        //显示启用
                        content += "<label>我能销售</label><input type=button class='btn btn-default'  onclick='openBuyBiz(2)' value='已停用，点击启用' style='width:130px; height:25px; line-height:12px; padding:5px 5px' />";

                    }
                }

                bizHtml = content;


            },
            error : function() {
                alert("异常！");
            }
        });
    }

    return bizHtml;
}

//打开调整关注模态框
function openUpdateBizModal(){
	//获取mmbId以及当前打开的relaMmbId,查询买卖业务关系是否存在
	var mmbId = $("#mmbId_").val();
	var relaMmbId = $("#relationMmbId").val();
	if(mmbId!=""&&relaMmbId!=""){
		//ajax请求查询返回两种业务是否存在
		$.ajax({
			url : '${ctx}/mmbRela/toUpgradebizOperation.do',// 跳转到 action
			data : {
				mmbId : mmbId,
				relaMmbId :relaMmbId
			},
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data) {
				if(data!=null&&data.buyBiz!=""){
					$("#buyDiv").empty();
					//如果存在当前会员与打开的关注会员存在买业务
					if(data.buyBiz=="0"){
						var content = "";
						//显示停用
						content += "<label>我能采购</label><input type='button' onclick='stopBuyBiz(1)' class='btn btn-danger' value='已启用，点击停用' style='width:130px; height:25px; line-height:12px; padding:5px 5px' />";
						$("#buyDiv").html(content);
					}
					else{
						var content = "";
						//显示启用
						content += "<label>我能采购</label><input type=button  onclick='openBuyBiz(1)' class='btn btn-success' value='已停用，点击启用' style='width:130px; height:25px; line-height:12px; padding:5px 5px' />";
						$("#buyDiv").html(content);
					}
				}
				if(data!=null&&data.sellBiz!=""){
					//如果存在当前会员与打开的关注会员存在卖业务
					$("#sellDiv").empty();
					if(data.sellBiz=="0"){
						//显示停用
						var content = "";
						content += "<label>我能销售</label><input type='button' class='btn btn-danger' onclick='stopBuyBiz(2)' value='已启用，点击停用' style='width:130px; height:25px; line-height:12px; padding:5px 5px' />";
						$("#sellDiv").html(content);
					}
					else{
						//显示启用
						var content = "";
						content += "<label>我能销售</label><input type=button class='btn btn-success'  onclick='openBuyBiz(2)' value='已停用，点击启用' style='width:130px; height:25px; line-height:12px; padding:5px 5px' />";
						$("#sellDiv").html(content);
					}
				}
				$("#tiaozhengguanxi").modal('show');
			},
			error : function() {
				alert("异常！");
			}
		});
	}
}

//停用
function stopBuyBiz(type){
    var mmbId = $("#mmbId_").val();
    var relaMmbId = $("#relationMmbId").val();
	if(mmbId!=""&&relaMmbId!=""){
        if(confirm("您确定要停用吗？")){
            $.ajax({
                url:"${ctx}/mmbRela/stopBizRelationShip.do",
                data:{
                    "mmbId":mmbId,
                    "relaMmbId":relaMmbId,
                    "type":type
                },
                type:'POST',
                cache:false,
                dataType:'json',
                success: function (data) {
                    if(data!=null){
                        //成功
                        if(data=="0"){
                            alert("停用成功!");
                            $("#tiaozhengguanxi").modal('hide');
                            document.location.reload();
                        }else{
                            alert("停用失败！");
                        }

                    }
                },
                error:function(){
                    alert("异常！");
                }
            });
		}

	}


}

//启用
function openBuyBiz(type){
    var mmbId = $("#mmbId_").val();
    var relaMmbId = $("#relationMmbId").val();
    if(mmbId!=""&&relaMmbId!=""){
        if(confirm("您确定要启用吗？")){
            $.ajax({
                url:"${ctx}/mmbRela/openBizRelationShip.do",
                data:{
                    "mmbId":mmbId,
                    "relaMmbId":relaMmbId,
                    "type":type
                },
                type:'POST',
                cache:false,
                dataType:'json',
                success: function (data) {
                    if(data!=null){
                        //成功
                        if(data=="0"){
                            alert("启用成功!待对方审批通过后即可使用！");
                            $("#tiaozhengguanxi").modal('hide');
                            document.location.reload();
                        }else{
                            alert("启用失败！");
                        }

                    }
                },
                error:function(){
                    alert("异常！");
                }
            });
		}
    }

}

//降级为仅关注
function lowerToConcern(){
	var mmbId = $("#mmbId_").val();
	var relaMmbId = $("#relationMmbId").val();
	var bizType = $("#bizType").val();
	if(mmbId!=""&&relaMmbId!=""){
        if(confirm("您确认要降级为仅关注吗？")){
            //ajax请求删除两种业务是否存在，存在则删除
            $.ajax({
                url : '${ctx}/mmbRela/lowerToConcernOperation.do',// 跳转到 action
                data : {
                    mmbId : mmbId,
                    relaMmbId :relaMmbId,
                    bizType : bizType
                },
                type : 'POST',
                cache : false,
                dataType : 'json',
                success : function(data) {
                    if(data!=""){
                        //如果成功
                        if(data=="0"){
                            alert("降级成功！");
                            $("#tiaozhengguanxi").modal('hide');
                            document.location.reload();
                        }
                        else{
                            alert("降级失败！");
                        }
                    }
                },
                error : function() {
                    alert("异常！");
                }
            });
		}

	}
}

//进入添加协议页面
function toAddContract(){
    var mmbId = $("#mmbId_").val();
    var relammbId = $("#relationMmbId").val();
    var type = $("#bizType").val() == 0 ? 1:2;
    if(relammbId==null||relammbId==""){
        alert("请选择关系会员！");
        return false;
    }
    else if(type==null||type==""){
        alert("请选择关系会员！");
        return false;
    }
    else{

		window.location.href ="${ctx}/contract/toAddContractPage.do?mmbId="+mmbId+"&relaMmbId="+relammbId+"+&contractType="+type;
    }
}
</script>
</head>

<body>
<div class="panel panel-default" style="margin-top:10px; box-shadow:3px 3px 8px rgba(0,0,0,0.1); margin-right:1%; height:auto;">
<!---------------------------------------con top  start-------------------------------------------------------------->
    <div class="con_top">
        <p>业务合作会员目录
			<!--当前登录会员ID -->
			<input type="hidden" id="mmbId" value="${mmbId!}"/>
			<!--当前业务类型 -->
			<input type="hidden" id="bizType"/>
		</p>
    </div>
<!---------------------------------------con top  over--------------------------------------------------------------->
<!---------------------------------------nav start--------------------------------------------------------------->
<div>
    <#--页面初始化时 动态拼接业务合作会员目录-->
<ul class="A_b_2_nav_left" style=" float:left;">

	<#if operationRelaList ??>
		<#list operationRelaList as operatrionRela>
			<#if operatrionRela.nums?? && operatrionRela.nums!=0 >
				<#if operatrionRela.buzType?? && operatrionRela.buzType == '买'>
                    <li onclick="queryBizTypeMmbs('0')">买(采购)-(${operatrionRela.nums!})</li>
                    <ul class="A_b_2_subnav"  id="buy">
                    </ul>
				<#elseif operatrionRela.buzType?? && operatrionRela.buzType == '卖'>
                    <li onclick="queryBizTypeMmbs('1')">卖(销售)-(${operatrionRela.nums!})</li>
                    <ul class="A_b_2_subnav" id="sell">
                    </ul>
				<#elseif operatrionRela.buzType?? && operatrionRela.buzType == '借'>
                    <li onclick="queryBizTypeMmbs('2')">借(贷款)-(${operatrionRela.nums!})</li>
                    <ul class="A_b_2_subnav" id="borrow">
                    </ul>
				<#elseif operatrionRela.buzType?? && operatrionRela.buzType == '贷'>
                    <li onclick="queryBizTypeMmbs('3')">贷(融资)-(${operatrionRela.nums!})</li>
                    <ul class="A_b_2_subnav" id="loan">
                    </ul>-->

				</#if>


			</#if>
		</#list>

	</#if>
	<#--<li onclick="queryBizTypeMmbs('0')">买(采购)</li>
    	<ul class="A_b_2_subnav"  id="buy">
        </ul>
	<li onclick="queryBizTypeMmbs('1')">卖(销售)</li>
    	<ul class="A_b_2_subnav" id="sell">
        </ul>
	<li onclick="queryBizTypeMmbs('2')">借(贷款)</li>
    	<ul class="A_b_2_subnav" id="borrow">
        </ul>
	<li onclick="queryBizTypeMmbs('3')">贷(融资)</li>
    	<ul class="A_b_2_subnav" id="loan">
        </ul>-->
</ul>
<!---------------------------------------nav  over--------------------------------------------------------------->
<!---------------------------------------right start--------------------------------------------------------------->
<div style="display:none;" id="all">
<ul class="A_b_2_right" style="height:280px;margin-left:20px;">
	  <div id="showDetail">
	      <li>福建省依个食品有限公司<span><a href="#">主页</a></span></li>
	      <li>合作方式：<span>采购</span></li>
	      <li>执行中合作协议:<span>1个</span></li>
	      <li>历史合作协议:<span>3个</span></li>
	      <li style="margin-top:10px;">
	        <input class="btn btn-primary" data-toggle="modal" data-target="#tiaozhengguanxi" style="margin-right:10px;" type="button" value="调整关系">
	        <a href="#" onclick="toAddContract()"><input class="btn btn-primary" type="button" value="创建合作协议"></a>
	      </li>
      </div>
</ul>
</div>
<div class="clear"></div>
            <!--升级到业务合作 start------>
            <div class="modal fade" id="tiaozhengguanxi" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
               <div class="modal-dialog">
                  <div class="modal-content">
                  	 <input id="relationshipId" type="hidden"/>
                     <input id="mmbId_" type="hidden"/>
                 	 <input id="relationMmbId" type="hidden"/>

                     <div class="modal-header" style="border-bottom:none;">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                     </div>
                     
                     <div class="Ab2_modal_top">
                        <p>配置业务合作类型</p>
                     </div>
                     
                     <div class="Ab2_modal_in_c">
                     	<div style="padding:10px 0;" id="buyDiv">
                        </div>
                     	<div style="padding:10px 0;" id="sellDiv">
                        </div>
                     </div>
                     
                     <div style="padding:20px 0 20px 230px;">
                     	<input class="btn btn-primary" onclick="lowerToConcern()" type="button" value="降级为仅关注">
                     </div>
                  </div>
                </div>
            </div>
            <!------ 升级到业务合作 over------>
          </div>
        </div>
    </div>
    <!------ 申请加群 over------>
<!---------------------------------------right  over--------------------------------------------------------------->
</div>
</body>
</html>
