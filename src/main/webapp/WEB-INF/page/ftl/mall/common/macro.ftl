
<#--输出的金额保留两位小数-->
<#macro money_format  money> 
#{money?number;m2}
</#macro> 

<#--金额以万为单位，保留2位小数-->
<#macro money_format6  money6> 
#{money6?number/10000;m2}
</#macro> 
  
<#--输出金额格式为000,000.000-->
<#macro money_format1  money1>
${money1?number?string(",###.000")}
</#macro>

<#--输出金额格式为000,000.000   金额保留3为小数   不够的用空格补足-->
<#macro money_format2  money2>
<#if money2?number?int = money2?number>
${money2?number?string(",###")}&nbsp;&nbsp;&nbsp;                   
<#elseif money2?number?string("000.0")?number = money2?number>
${money2?number?string(",###.0")}&nbsp;&nbsp;
<#elseif money2?number?string("000.00")?number = money2?number>
${money2?number?string(",###.00")}&nbsp;
<#else>
${money2?number?string(",###.000")}
</#if>
</#macro>

<#--输出数量保留3为小数   不够的用空格补足-->
<#macro number_format2  number2>
<#if number2?number?int = number2?number>
${number2?number?string}&nbsp;&nbsp;&nbsp;                   
<#elseif number2?number?string("#.0")?number = number2?number>
${number2?number?string("#.0")}&nbsp;&nbsp;
<#elseif number2?number?string("#.00")?number = number2?number>
${number2?number?string("#.00")}&nbsp;
<#else>
${number2?number?string("#.000")}
</#if>
</#macro>


<#--输出融资额度百分比格式为 -->
<#macro percentage  percentage>
${percentage?number*100}%
</#macro>
  
<#--输出年月日时分秒的格式为yyyy-MM-dd HH:mm:ss-->
<#macro date_format datenum>
${datenum?datetime?string("yyyy-MM-dd HH:mm:ss")}
</#macro>
  
<#-- 输出年月日时分秒的格式为yyyy-MM-dd HH:mm -->
<#macro date_format1 datenum1>
${datenum1?datetime?string("yyyy-MM-dd HH:mm")}
</#macro>
  
  
<#-- 输出年月日时分秒的格式为yyyy-MM-dd HH -->
<#macro date_format2 datenum2>
${datenum2?datetime?string("yyyy-MM-dd HH")}
</#macro>
  
<#-- 输出格式为yyyyMMddHHmmss  -->
<#macro date_format3 datenum3>
${datenum3?datetime?string("yyyyMMddHHmmss")}
</#macro>
  
<#-- 输出格式为yyyyMMdd  -->
<#macro date_format4 datenum4>
${datenum4?datetime?string("yyyyMMdd")}
</#macro>

<#-- 输出格式为YYYY-MM-DD  -->
<#macro date_format5 datenum5>
${datenum5?datetime?string("yyyy-MM-dd")}
</#macro>
  
<#-- 输出格式为yyyy:MM:dd--HH:mm:ss -->
<#macro date_format8 datenum8>
${datenum8?datetime?string("yyyy:MM:dd--HH:mm:ss")}
</#macro>
  
<#-- 输出格式为yyyyMMddHHmmss -->
<#macro date_format9 datenum9>
${datenum9?datetime?string("yyyyMMddHHmmss")}
</#macro>
  
  
<#--输出年月日时分秒的格式为yyyy'年'MM'月'dd'日'  HH'时'mm'分'ss'秒'-->
<#macro date2 datenum>
${datenum?datetime?string("yyyy'年'MM'月'dd'日'  HH'时'mm'分'ss'秒'")}
</#macro>




<#setting number_format="#.###">

<#--货币 格式化 000,000.00-->
<#macro money money>
	${moneyUnit!}${money?number?string(",##0.000")}
</#macro>
<#--质量 格式化 000,000.000-->
<#macro weight weight>
	${weight?number?string(",##0.###")}${weightUnit!}
</#macro>
<#--数量 格式化 -->
<#macro num num>
	${num?number?string(",##0.###")}${numUnit!}
</#macro>


<#--通用 select-->
<#--by zhanghu 2014.7.23-->
<#macro select id class name datas value="" key="" val="" style="" headerKey="" headerVal="">  
	<select id="${id}" name="${name}" class="${class}">  
		<#if headerVal!="">  
        <option value="${headerKey}">${headerVal}</option>  
		</#if>
        <#if datas?is_hash>  
			<#local keys=datas?keys/>  
	        <#list keys as key>  
				<#if key==value>  
					<option value="${key}" style="${style}" selected="selected">${datas[key]}</option>  
				<#else>
					<option value="${key}" style="${style}">${datas[key]}</option>  
				</#if>  
			</#list>
		<#else>
			<#list datas as data>  
				<#if key!="">  
					<#if value==data[key]?string>
						<option value="${data[key]}" selected>${data[val]}</option>  
					<#else>
						<option value="${data[key]}">${data[val]}</option>  
					</#if>
				<#else>  
					<#if value==data>  
						<option value="${data}" selected>${data}</option>  
					<#else>  
						<option value="${data}">${data}</option>  
					</#if>  
				</#if>
			</#list>    
		</#if>
	</select> 
</#macro>

<#--通用 radio-->
<#--by zhanghu 2014.7.24-->
<#macro radio class name datas value="">  
<#if datas?is_hash_ex>  
	<#local keys=datas?keys/>  
    <#list keys as key>  
		<#if key==value>  
		 	<input type="radio" class="${class}" name="${name}" value="${key}" checked="checked"/>${datas[key]}
		<#else>
		 	<input type="radio" class="${class}" name="${name}" value="${key}"/>${datas[key]}
		</#if>
		&nbsp;&nbsp;
	</#list>
<#else>
	目前只支持map类型的键值对!!
</#if>
</#macro>

<#--by qiujy-->
<#--update by zhanghu 2014.7.29-->
<#macro pager pageNo pageSize toURL recordCount paramId=''> 
  <#-- 定义局部变量pageCount保存总页数 -->
  <#assign pageCount=((recordCount + pageSize - 1) / pageSize)?int>  
	<#if recordCount==0><#return/></#if>
<#-- 输出分页样式 -->
<style type="text/css">
.public_fy{heigh:20px;text-align:center;}
.pagination {padding: 5px; font-size:12px; margin:20px auto; }
.pagination a, .pagination a:link, .pagination a:visited {padding:2px 5px;margin:2px;border:1px solid #aaaadd;text-decoration:none;color:#006699;}
.pagination a:hover, .pagination a:active {border: 1px solid #d1cdcd;color: #000;text-decoration: none;}
.pagination span.current {padding: 2px 5px;margin: 2px;border: 1px solid #d1cdcd;font-weight: bold;background-color: #d6d2d2;color: #FFF;}
.pagination span.disabled {padding: 2px 5px;margin: 2px;border: 1px solid #eee; color: #ddd;}
#pagerDefine,#pagerSelect{width:45px;}
</style>
<#-- 页号越界处理 -->
<#if (pageNo > pageCount)><#assign pageNo=pageCount></#if>
<#if (pageNo < 1)><#assign pageNo=1></#if>

<#-- 输出分页表单 -->
<div class="clear"></div>
<div class="pagination">
<form id="qPagerForm" method="post" action="" name="qPagerForm">
<input type="hidden" id="pageNo" name="pageNo" value="${pageNo}"/>
<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}"/>
共${recordCount}条 &nbsp;&nbsp; 
每页&nbsp;<select id="pagerDefine">
	<option value="1" <#if 1==pageSize>selected="selected"</#if>>1</option>
	<option value="5" <#if 5==pageSize>selected="selected"</#if>>5</option>
	<option value="10" <#if 10==pageSize>selected="selected"</#if>>10</option>
	<option value="20" <#if 20==pageSize>selected="selected"</#if>>20</option>
	<option value="30" <#if 30==pageSize>selected="selected"</#if>>30</option>
	<option value="40" <#if 40==pageSize>selected="selected"</#if>>40</option>
	<option value="50" <#if 50==pageSize>selected="selected"</#if>>50</option>
</select>&nbsp;条&nbsp;
<#-- 上一页处理 -->
<#if (pageNo == 1)><span class="disabled">&laquo;&nbsp;上一页</span>
<#else><a href="javascript:void(0)" onclick="turnOverPage(${pageNo - 1})">&laquo;&nbsp;上一页</a></#if>

<#-- 如果前面页数过多,显示... -->
<#assign start=1>
<#if (pageNo > 4)>
	<#assign start=(pageNo - 1)>
	<a href="javascript:void(0)" onclick="turnOverPage(1)">1</a>
	<a href="javascript:void(0)" onclick="turnOverPage(2)">2</a>&hellip;
</#if>
<#-- 显示当前页号和它附近的页号 -->
<#assign end=(pageNo + 1)>
<#if (end > pageCount)>
	<#assign end=pageCount>
</#if>
<#list start..end as i>
    <#if (pageNo==i)><span class="current">${i}</span><#else>
	<a href="javascript:void(0)" onclick="turnOverPage(${i})">${i}</a></#if>
</#list>
  
<#-- 如果后面页数过多,显示... -->
  <#if (end < pageCount - 2)>&hellip;</#if>
  <#if (end < pageCount - 1)><a href="javascript:void(0)" onclick="turnOverPage(${pageCount - 1})">${pageCount-1}</a></#if>
  <#if (end < pageCount)><a href="javascript:void(0)" onclick="turnOverPage(${pageCount})">${pageCount}</a></#if>
  <#if (pageNo == pageCount)><span class="disabled">下一页&nbsp;&raquo;</span>
  <#else><a href="javascript:void(0)" onclick="turnOverPage(${pageNo + 1})">下一页&nbsp;&raquo;</a></#if>
  &nbsp;转到&nbsp;<select id="pagerSelect"><#list 1..pageCount as v>
   <option value="${v}" <#if v==pageNo>selected="selected"</#if>>${v}</option>
   </#list></select>&nbsp;页
</form>
<script language="javascript">
$(function(){
	$("#pagerSelect").change(function(){
		turnOverPage($("#pagerSelect").val());
	});
	$("#pagerDefine").change(function(){
		turnOverPage($("#pagerSelect").val(),true);
	});
    <#if paramId!=''>
    	$("#${paramId} :input").each(function(key,val){
			if(val.type=="text" || (val.type=="radio" && val.checked) || val.type=="hidden" || $(val).find("option").length>0){
				var value;
				if($(val).find("option").length>0){
					value=$(val).find("option:selected").val();
				}else{
					value=$(val).val();
				}
				if($("#"+val.name).length>0){
					$("#"+val.name).val(value);	
				}else{
					$("#qPagerForm").append("<input type='hidden' id='"+val.name+"' name='"+val.name+"' value='"+value+"'/>");
				}
	    	};
		});
    </#if>
});
  function turnOverPage(no,reload){
    var qForm=document.qPagerForm;
  	qForm.action = "${toURL}";
    $("#pageSize").val($("#pagerDefine").val());
    if(no>${pageCount}){no=${pageCount};}
    if(no<1){no=1;}
    if(!!reload) no=1;
    qForm.pageNo.value=no;
    qForm.submit();
  }
</script>
</div>
</#macro>  


<#--长度大于4后面的以...显示-->
<#macro PDreceive receiveManeyName>
  <#if receiveManeyName?length gt 4>
      ${receiveManeyName?string?substring(0,4)}...
  <#else>
      ${receiveManeyName}
  </#if>
</#macro>

