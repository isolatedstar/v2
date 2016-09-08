<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />   <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>无标题文档</title>
   <script src="${ctx}/mall/js/jquery.js"></script>
   <script src="${ctx}/mall/js/jquery.min.js"></script>
   <script src="${ctx}/mall/js/bootstrap.min.js"></script>
   <script src="${ctx}/mall/js/bootstrap-treeview.js"></script>
   <script type="text/javascript" src="${ctx}/mall/js/extendPagination.js"></script>
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

//动态查询关注等级信息
function queryConcernMmbs(type){
	var mmbId = $("#mmbId").val();
	if(type!=""&&mmbId!=""){
		$.ajax({
			url : '${ctx}/mmbRela/getMmbsByGrade.do',// 跳转到 action
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
						content+="<li><a href='#' onclick=showDetail('"+data[i].relaMmbId+"')>"+data[i].fname+"</a></li>"
					}
					//如果是等级一
					if(type!=""&&type=="1"){
						//清空等级一中的内容
						$("#gradeOne").empty();
						$("#gradeOne").append(content);
					}
					else if(type!=""&&type=="2"){
						//清空等级一中的内容
						$("#gradeTwo").empty();
						$("#gradeTwo").append(content);
					}
					else if(type!=""&&type=="3"){
						//清空等级一中的内容
						$("#gradeThree").empty();
						$("#gradeThree").append(content);
					}
					else if(type!=""&&type=="4"){
						//清空等级一中的内容
						$("#gradeFour").empty();
						$("#gradeFour").append(content);
					}
					else if(type!=""&&type=="5"){
						//清空等级一中的内容
						$("#gradeFive").empty();
						$("#gradeFive").append(content);
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
	if(mmid!=null&&mmid!=""){
		$.ajax({
			url : '${ctx}/mmb/queryMMBByMid.do',// 跳转到 action
			data : {
				mmbId : mmid,
				type :"0"
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
					$("#mmbId_").val(data.mmbId_);
					$("#relationMmbId").val(data.relaMmbId_);

                    if(data.mmbHomepage != null && data.mmbHomepage != ""){
                        content+="<li>"+data.mmbFname+"&nbsp;&nbsp;<a href='#' onclick =openHomePage('"+data.mmbHomepage+"')>主页</a></li>";
                    }else{
                        content+="<li>"+data.mmbFname+"&nbsp;&nbsp;<span><a href=javascript:alert('对方未配置主页!')>主页</a></span></li>";
                    }
                    content+="<li>注册地址:&nbsp;&nbsp;<lable>"+data.mmbAddress+"</lable></li>";
					content+="<li>关注等级:&nbsp;&nbsp;"+
							"<img src='${ctx}/mall/images/Ab2_moble_bj_88_01.png' style='width:30px;' onclick='lowerGrade()'/>&nbsp;"+
							"<lable id='concernGradeDiv'>"+data.concernGrade+"</lable> &nbsp;&nbsp;&nbsp;"+
                            "<img src='${ctx}/mall/images/Ab2_moble_bj_88_003.png' style='width:30px;' onclick='uperGrade()'/></li>";
					//content+="<li>历史合作协议:<span>"+1+"</span></li>";
					//content+="<li><input class='btn btn-primary' onclick='openMyModel()' style='margin-left:20%;' type='button' value='调整关注度'></li>";
                    content+="<input class='btn btn-primary' onclick='openUpdateBizModal()'  type='button' value='升级到业务合作'>&nbsp;&nbsp;";
                    content+="<input class='btn btn-primary' onclick='cancelConcern()'  type='button' value='取消关注'>"
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

//打开调整关注模态框
function openMyModel(){
	//将关注等级中的值传入模态框中
	$("#concernGradeDiv").html($("#gradeSpan").text());
	$("#myModal").modal('show');
}

//打开升级到业务合作模态框
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
						content += "<label>我能采购</label><input type='button' onclick='stopBuyBiz(1)' class='btn btn-default' value='已启用，点击停用' style='width:130px; height:25px; line-height:12px; padding:5px 5px' />";
						$("#buyDiv").html(content);
					}
					else{
						var content = "";
						//显示启用
						content += "<label>我能采购</label><input type=button  onclick='openBuyBiz(1)' class='btn btn-default' value='已停用，点击启用' style='width:130px; height:25px; line-height:12px; padding:5px 5px' />";
						$("#buyDiv").html(content);
					}
				}
				if(data!=null&&data.sellBiz!=""){
					//如果存在当前会员与打开的关注会员存在买业务
					$("#sellDiv").empty();
					if(data.sellBiz=="0"){
						//显示停用
						var content = "";
						content += "<label>我能销售</label><input type='button' class='btn btn-default' onclick='stopBuyBiz(2)' value='已启用，点击停用' style='width:130px; height:25px; line-height:12px; padding:5px 5px' />";
						$("#sellDiv").html(content);
					}
					else{
						//显示启用
						var content = "";
						content += "<label>我能销售</label><input type=button class='btn btn-default'  onclick='openBuyBiz(2)' value='已停用，点击启用' style='width:130px; height:25px; line-height:12px; padding:5px 5px' />";
						$("#sellDiv").html(content);
					}
				}
				$("#shengjidaoyewuhezuo").modal('show');
			},
			error : function() {
				alert("异常！");
			}
		});
	}
}

//取消关注
function cancelConcern(){
    $.ajax({
        url : '${ctx}/mmbRela/cancelConcern.do',// 跳转到 action
        data : {
            id : $("#relationshipId").val()
        },
        type : 'POST',
        cache : false,
        dataType : 'json',
        success : function(data) {
            //返回结果,成功
            if(data!=""&&data=="0"){
                alert("取消关注成功！");
                //刷新整个页面
                window.document.location.reload();
            }
            else{
                alert("取消关注失败！");
            }


        },
        error : function() {
            alert("异常！");
        }
    });

}

//降低关注度
function lowerGrade(){
	//获取当前级别
	var grade = $("#concernGradeDiv").html();
	var oldGrade ; //原始等级

	if(grade!=""){
		grade = parseInt(grade);
        oldGrade = grade;
		grade = grade - 1;
		if(grade<1){
			alert("关注等级不能小于一！");
			return false;
		}
		$.ajax({
			url : '${ctx}/mmbRela/changeConcernGrade.do',// 跳转到 action
			data : {
				Id : $("#relationshipId").val(),
				grade :grade
			},
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data) {
				//返回结果,成功
				if(data!=""&&data=="0"){
					//reload页面
					$("#concernGradeDiv").html(grade);
					//document.location.reload();

					//原始等级个数-1 新等级个数+1
                    var oldNums = parseInt($("#nums"+oldGrade+"").html());
                    var newNums = parseInt($("#nums"+grade+"").html());

                    $("#nums"+oldGrade+"").html( oldNums - 1);
                    $("#nums"+grade+"").html(newNums + 1);

					//点击当前等级li
                    $("#grand"+grade+"").click();
				}
				else{
					alert("降级失败！");
				}
			},
			error : function() {
				alert("异常！");
			}
		});
	}
}

//升级关注度
function uperGrade(){
	//获取当前级别
	var grade = $("#concernGradeDiv").html();
    var oldGrade ; //原始等级
	if(grade!=""){
		grade = parseInt(grade);
        oldGrade = grade;
		grade += 1;
		if(grade>5){
			alert("关注等级不能大于五！");
			return false;
		}
		$.ajax({
			url : '${ctx}/mmbRela/changeConcernGrade.do',// 跳转到 action
			data : {
				Id : $("#relationshipId").val(),
				grade :grade
			},
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data) {
				//返回结果,成功
				if(data!=""&&data=="0"){
					//reload页面
					$("#concernGradeDiv").html(grade);
					//document.location.reload();

                    //原始等级个数-1 新等级个数+1
					var oldNums = parseInt($("#nums"+oldGrade+"").html());
					var newNums = parseInt($("#nums"+grade+"").html());

                    $("#nums"+oldGrade+"").html( oldNums - 1);
                    $("#nums"+grade+"").html(newNums + 1);

                    //点击当前等级li
                    $("#grand"+grade+"").click();
				}
				else{
					alert("升级失败！");
				}
			},
			error : function() {
				alert("异常！");
			}
		});
	}
}

//模态框关闭后刷新下页面
$("#myModal").on('hidden.bs.modal', function () {
	document.location.reload();
});

//停用业务
function stopBuyBiz(type){
	var mmbId = $("#mmbId_").val();
	var relaMmbId = $("#relationMmbId").val();
	var type = type.toString();
	if(type!=""&&mmbId!=""&&relaMmbId!=""){
		if(confirm("您确定要停用吗？")){
            $.ajax({
                url : '${ctx}/mmbRela/stopBizRelationShip.do',// 跳转到 action
                data : {
                    mmbId : mmbId,
                    relaMmbId :relaMmbId,
                    type :type
                },
                type : 'POST',
                cache : false,
                dataType : 'json',
                success : function(data) {
                    //返回结果,成功
                    if(data!=""&&data=="0"){
                        //如果是我能采购，将我能采购按钮改成启用
                        if(type=="1"){
                            $("#buyDiv").empty();
                            var content = "";
                            //显示启用
                            content += "<label>我能采购</label><input type=button  onclick='openBuyBiz(1)' class='btn btn-default' value='已停用，点击启用' style='width:130px; height:25px; line-height:12px; padding:5px 5px' />";
                            $("#buyDiv").html(content);
                        }
                        else if(type=="2"){
                            $("#sellDiv").empty();
                            //显示启用
                            var content = "";
                            content += "<label>我能销售</label><input type=button class='btn btn-default'  onclick='openBuyBiz(2)' value='已停用，点击启用' style='width:130px; height:25px; line-height:12px; padding:5px 5px' />";
                            $("#sellDiv").html(content);
                        }
                    }
                    else{
                        alert("停用失败！");
                    }
                },
                error : function() {
                    alert("异常！");
                }
            });
		}

	}
}

//开启业务
function openBuyBiz(type){
	var mmbId = $("#mmbId_").val();
	var relaMmbId = $("#relationMmbId").val();
	var type = type.toString();
	if(type!=""&&mmbId!=""&&relaMmbId!=""){
        if(confirm("您确定要启用吗？")){
            $.ajax({
                url : '${ctx}/mmbRela/openBizRelationShip.do',// 跳转到 action
                data : {
                    mmbId : mmbId,
                    relaMmbId :relaMmbId,
                    type :type
                },
                type : 'POST',
                cache : false,
                dataType : 'json',
                success : function(data) {
                    //返回结果,成功
                    if(data!=""){
                        //成功
                        if(data=="0") {
                            alert("启用成功!待对方审批通过后即可使用！");
                            $("#tiaozhengguanxi").modal('hide');
                            document.location.reload();
                        }
                    }
                    else{
                        alert("启用失败！");
                    }
                },
                error : function() {
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
	var type = 1;//买关系
	if(mmbId!=""&&relaMmbId!=""){
		if(confirm("您确认要降级为仅关注吗？")){
            //ajax请求删除两种业务是否存在，存在则删除
            $.ajax({
                url : '${ctx}/mmbRela/lowerToConcernOperation.do',// 跳转到 action
                data : {
                    mmbId : mmbId,
                    relaMmbId :relaMmbId,
                    type : type
                },
                type : 'POST',
                cache : false,
                dataType : 'json',
                success : function(data) {
                    if(data!=""){
                        $("#buyDiv").empty();
                        //如果成功
                        if(data=="0"){
                            alert("降级成功！");
                            $("#shengjidaoyewuhezuo").modal('hide');
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
</script>
</head>

<body>
<div class="panel panel-default" style="margin-top:10px; box-shadow:3px 3px 8px rgba(0,0,0,0.1); margin-right:1%; height:auto;">
<!---------------------------------------con top  start-------------------------------------------------------------->
    <div class="con_top">
        <p>关注会员目录<input type="hidden" id="mmbId" value="${mmbId!}"/></p>
    </div>
<!---------------------------------------con top  over--------------------------------------------------------------->
<!---------------------------------------nav start--------------------------------------------------------------->
<div>
<ul class="A_b_2_nav_left" style=" float:left; margin-bottom:2%;">
	<li onclick="queryConcernMmbs('1')" id="grand1">一级&nbsp;(<span id="nums1">${levelNums[0]!}</span>)</li>
    	<ul class="A_b_2_subnav"  id="gradeOne">
        </ul>
	<li onclick="queryConcernMmbs('2')" id="grand2">二级&nbsp;(<span id="nums2">${levelNums[1]!}</span>)</li>
    	<ul class="A_b_2_subnav" id="gradeTwo">
        </ul>
	<li onclick="queryConcernMmbs('3')" id="grand3">三级&nbsp;(<span id="nums3">${levelNums[2]!}</span>)</li>
    	<ul class="A_b_2_subnav" id="gradeThree">
        </ul>
	<li onclick="queryConcernMmbs('4')" id="grand4">四级&nbsp;(<span id="nums4">${levelNums[3]!}</span>)</li>
    	<ul class="A_b_2_subnav" id="gradeFour">
        </ul>
	<li onclick="queryConcernMmbs('5')" id="grand5">五级&nbsp;(<span id="nums5">${levelNums[4]!}</span>)</li>
    	<ul class="A_b_2_subnav" id="gradeFive">
        </ul>
</ul>

<!---------------------------------------nav  over--------------------------------------------------------------->
<!---------------------------------------right start--------------------------------------------------------------->
<div style="display:none;" id="all">
<ul class="A_b_2_right" style="margin-left:20px;">
	  <div style="display:none;" id="showDetail">
      <li id="showNameLi">北京顺平信德商贸有限公司<span><a href="#">主页</a></span></li>
      <li>关注等级<span id="concernGradeDiv"></span></li>
      <li>历史合作协议:<span id="historyDiv"></span></li>
      <li><input class="btn btn-primary" data-toggle="modal" data-target="#myModal" style="margin-left:20%;" type="button" value="调整关注度"></li>
      </div>
            <!--调整关注度 start  已废除------>
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
               <div class="modal-dialog">
                  <div class="modal-content">
                     <div class="modal-header" style="border-bottom:none;">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                     </div>
                     <div class="Ab2_modal_top">
                        <p>配置关注度</p>
                     </div>
                     <div class="Ab2_modal_center">
                     	<input id="relationshipId" type="hidden"/>
                     	<input id="relationMmbId" type="hidden"/>
                     	<input id="mmbId_" type="hidden"/>
                        <div class="Ab2_modal_nav_l"><img src="${ctx}/mall/images/Ab2_moble_bj_88_01.png" onclick="lowerGrade()"/></div>
                        <div class="Ab2_modal_nav_c" id="concernGradeDiv">3</div>
                        <div class="Ab2_modal_nav_r"><img src="${ctx}/mall/images/Ab2_moble_bj_88_003.png" onclick="uperGrade()"/></div>
                        <div class="clear"></div>
                     </div>
                     <div class="Bbmt_content" style="padding:20px 150px;">
                        <input class="btn btn-primary" onclick="openUpdateBizModal()" style="margin-left:90px;" type="button" value="升级到业务合作">
                     </div>
                    </div>
                </div>
            </div>
            <!------ 调整关注度 over------>
                
                
            <!--升级到业务合作 start------>
            <div class="modal fade" id="shengjidaoyewuhezuo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
               <div class="modal-dialog">
                  <div class="modal-content">
                  
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

        </ul>
</div>
			<div class="clear"></div>
<!---------------------------------------right  over--------------------------------------------------------------->
</div>
</body>
</html>
