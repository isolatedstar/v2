<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />   <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>直接融资</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/factoring_list.css"/>
<script type='text/javascript' src='${ctx}/js/jquery-1.4.2.min.js'></script>
<script type="text/javascript">
$(document).ready(function(e) {
	$('#cinbtn a').click(function(){
		var cinbtn=$(this).attr('href')
		$(cinbtn).show()
		})
	$('#cininbtn').click(function(){
		var cininbtn=$('#c_in_inner_a_box')
		$(cininbtn).hide()
		})
	$('#cinbtnfhsyj_box a').click(function(){
		$(this).attr('href').show()
		})
});
</script>
</head>
<body>
<!--添加新融资页-->
<div class="fac_c_none_warp">
    <div>
        <ul class="rz_query">
            <li><label style="padding-left:12px;">融资申请人：</label>
                <input type="text" />
            </li>
            <li><label style="padding-left:27px;">融资金额：</label>
                <input type="text" />
            </li>
            <li><label>预计还款日期：</label><input type="text" value="" /></li>
        </ul>
    </div>
    <div class="clearfix" style="margin:20px 0;"></div>
    <div class="db_wrap">
        <table class="fac_table " cellspacing="0"  cellpadding="5">
            <tr class="fac_tr_tab">
                
                <td width="2%"><input type="checkbox" /></td>
                <td width="6%">担保单号</td>
                <td width="6%">担保金额</td>
                <td width="6%">担保可用金额</td>
                <td width="6%">担保登记员</td>
                <td width="6%">本次使用金额</td>
            </tr>
         </table>
    </div> 
     <div id="cinbtn"><a href="#c_in_inner_a_box"><input type="button" class="pay_button pay_button_3" value="选择担保资源" /> </a></div>
     <div class="fac_c_bot_bot" id="cinbtnfhsyj_box">
        <label>利息：￥100</label><label>服务费：￥100</label>
        <input type="button" class="pay_button pay_button_3" value="融资" onclick = "javaScript:history.back(-1);"/>
        <#--<a href="${ctx}/directFinancingController/directFinancingNoFilter.do"><input type="button" class="pay_button" id="cinbtnfhsyj" value="返回上一级" /></a>
     	-->
     </div> 

 <!--选择担保资源  inner-->
<div class="inner_a_box" id="c_in_inner_a_box" style="padding:10px 10px 0 10px; height:210px;">
	<div class="rz_query_div" style="height:45px;">
			<ul class="rz_query fac_top_sech" >
				<li><label>付款方：</label>
					<input type="text" />
				</li>
				<li><label>担保登记员：</label>
					<input type="text" />
				</li>
                <li style="float:right; margin-right:-100px;"><input type="button" class="pay_button" value="查询" /></li>
			</ul>
</div>
	<div class="inner_a_box_content" style="margin:0;">
        <table class="fac_table " cellspacing="0"  cellpadding="5">
            <tr class="fac_tr_tab">
                <td width="6%">担保单号</td>
                <td width="7%">担保单金额</td>
                <td width="7%">已支付金额</td>
                <td width="10%">可用担保金额</td>
                <td width="6%">付款方</td>
                <td width="7%">担保登记员</td>
                <td width="6%">到期时间</td>
                <td width="10%">本次使用金额</td>
            </tr>
            <tr>
                <td>10001</td>
                <td>￥100</td>
                <td>￥10</td>
                <td>￥90</td>
                <td>高校1</td>
                <td>asd</td>
                <td></td>
                <td><input type="text"/ style="width:70px; height:20px;"></td>             
            </tr>
            <tr>
                <td>10002</td>
                <td>￥100</td>
                <td>￥50</td>
                <td>￥50</td>
                <td>高校2</td>
                <td>111</td>
                <td></td>
                <td><input type="text"/ style="width:70px; height:20px;"></td>
            </tr>
        </table>
    </div>
    <input type="button" class="pay_button pay_button_a_in"  id="cininbtn" value="X"/>
    <input type="button" class="pay_button" value="添加"  style=" float:right; margin:20px 50px 20px 0;"/>
</div>
 <!--选择担保资源 inner  over-->    
</div>
</body>
</html>