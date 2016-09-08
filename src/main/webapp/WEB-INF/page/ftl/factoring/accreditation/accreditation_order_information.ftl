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
$(function(){
	$('#d10001in_btn li a').click(function(){
		var d10001in_btn=$(this).attr('href')
		$(d10001in_btn).show()
		
		
		})
});
</script>
</head>
<body>
<p style="padding-left:20px; line-height:40px;">融资单号：10004</p>
<!--form  bot -->
<div class="db_wrap">
	<table class="fac_table " cellspacing="0"  cellpadding="5">
    	<tr>
			<td colspan="9" style=" background:#fff; text-align:left; padding-left:20px; line-height:32px;">担保单信息</td>
		</tr>
		<tr class="fac_tr_tab">
			<td width="3%"><input type="checkbox" value="checkbox" /></td>
			<td width="7%">担保单号</td>
			<td width="8%">担保单金额</td>
			<td width="6%">已支付金额</td>
			<td width="7%">可用担保金额</td>
			<td width="10%">付款方</td>
			<td width="7%">担保登记员</td>
			<td width="8%">到期时间</td>
			<td width="8%">本次使用金额</td>
		</tr>
		<tr>
			<td><input type="checkbox" value="checkbox" /></td>
			<td>10001</td>
			<td>￥100</td>
			<td>￥10</td>
			<td>￥90</td>
			<td>高校1</td>
			<td>asd</td>
			<td></td>
			<td>￥30</td>
		</tr>
		<tr>
			<td><input type="checkbox" value="checkbox" /></td>
			<td>10002</td>
			<td>￥100</td>
			<td>50</td>
			<td>￥50</td>
			<td>高校2</td>
			<td>111</td>
			<td></td>
			<td>￥30</td>
		</tr>
	</table>
</div>
<!--form bot over-->
<div>
<ul  id="d10001in_btn">
    <li><a onclick="javaScript:history.back(-1);"><input type="button" class="pay_button pay_button_d2" value="返回上一级"/></a></li>
</ul>
<div class="clear"></div>
</div>

</body>
</html>