<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />   <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>融资管理</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/factoring_list.css"/>
<script type='text/javascript' src='${ctx}/js/jquery-1.4.2.min.js'></script>
<script type="text/javascript">
$(function(){
	$('.rz_cont:gt(0)').hide();
	var div_cont = $('.rz_div .rz_cont');
	$('.rz_ul_all li').each(function(index){
		$(this).click(function(){
			$(this).addClass('rz_ul_one').siblings().removeClass();
			div_cont.eq(index).show().siblings('.rz_cont').hide();
		});
	});
	$('.fac_table tr td a').click(function(){
		var i=$(this).attr('href')
		$('.inner_a_box').hide()
		$(i).show()
		})
	$('#b_10001_alert_btn').click(function(){
		$('.inner_a_box').hide()
		})
	$('#b_10002_alert_btn').click(function(){
		$('.inner_a_box').hide()
		})
	$('#b_10003_alert_btn').click(function(){
		$('.inner_a_box').hide()
		})
	$('#b_dbfsone_alert_btn').click(function(){
		$('.inner_a_box').hide()
		})
	$('#b_dbfstwo_alert_btn').click(function(){
		$('.inner_a_box').hide()
		})	
	$('#b_dbfsthree_alert_btn').click(function(){
		$('.inner_a_box').hide()
		})
	$('#cinxdbhrk').click(function(){
		$('.inner_a_box').hide()
		$('#c_in_inner_a_op').show()
		})
	$('#cininbtn_xdbhrk').click(function(){
		$('.inner_a_box').hide()
		})
});
</script>
</head>
<body>
<div class="rz_div">
	<!-- 选项菜单 -->
	<ul class="rz_ul_all">
		<li class="rz_ul_one">融资支付</li>
		<li>融资查询</li>
	</ul>
	<!-- 选项菜单 over-->
	<!-- 融资支付 -->
	<div class="rz_cont">
		<!-- 查询条件 -->
		<div class="rz_query_div">
			<ul class="rz_query">
				<li><label>选择保理方：</label>
					<select>
						<option>中联联合</option>
					</select>
				</li>
				<li><label>选择担保方式：</label>
					<select>
                    	<option>请选择</option>
						<option  id="cinxdbhrk">先担保后认可</option>
						<option>先认可有担保</option>
					</select>
				</li>
				<li style="padding-left:12px;"><label>选择银行：</label><input class="fac_input" type="text" value="中信银行"  style=" background:#ccc;"/></li>
				<li><label>预计还款日：</label><input class="fac_input" type="text" value="" /></li>
			</ul>
		</div>
		<!-- 查询条件 over-->
		<div class="clearfix"></div>
		<table class="fac_table " cellspacing="0"  cellpadding="5">
			<tr class="fac_tr_tab">
				<td width="3%"><input type="checkbox" value="checkbox" /></td>
				<td width="6%">订单行号</td>
				<td width="6%">订单金额</td>
				<td width="9%">建议融资金额</td>
				<td width="9%">本次融资金额</td>
				<td width="6%">自付金额</td>
				<td width="6%">担保金额</td>
				<td width="4%">利率</td>
				<td width="6%">预计利息</td>
				<td width="7%">预计服务费</td>
				<td width="10%">收款方账号</td>
				<td width="10%">付款方账号</td>
			</tr>
			<tr>
				<td><input type="checkbox" value="checkbox" /></td>
				<td>10001</td>
				<td>100</td>
				<td>80</td>
				<td>￥<input type="text" class="fac_input_min" /></td>
				<td>￥<input type="text" class="fac_input_min" /></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><div class="b_table_p"><p>一般户：</p><p>虚拟户：</p></div></td>
				<td><div class="b_table_p"><p>一般户：</p><p>虚拟户：</p></div></td>
			</tr>
			<tr>
				<td><input type="checkbox" value="checkbox" /></td>
				<td>10001</td>
				<td>100</td>
				<td>80</td>
				<td>￥<input type="text" class="fac_input_min" /></td>
				<td>￥<input type="text" class="fac_input_min" /></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><div class="b_table_p"><p>一般户：</p><p>虚拟户：</p></div></td>
				<td><div class="b_table_p"><p>一般户：</p><p>虚拟户：</p></div></td>
			</tr>
		</table>
		<div class="fac_fanh"><button class="fac_button">融资申请</button></div>
	</div>
	<!-- 融资支付 over -->
	<!-- 融资查询 -->
	<div class="rz_cont">
		<!-- 查询条件 -->
		<div class="rz_query_div" >
			<ul class="rz_query">
				<li><label>融资单号：</label><input class="fac_input" type="text" value="" /></li>
				<li><label>担保方式：</label>
					<select>
						<option>全部</option>
						<option>先担保后认可</option>
                        <option>先认可后担保</option>
					</select>
				</li>				
                <li><label style="margin-left:14px;">操作员：</label><input class="fac_input" type="text" value="" /></li>
				<li><label>预计还款日期：</label><input class="fac_input" type="text" value="" /></li>
			</ul>
			<div class="clearfix" style="margin:20px 0;"></div>
			<ul class="rz_query">
				<li><label style="margin-left:14px;">收款方：</label><input class="fac_input" type="text" value="" /></li>
				<li><label style="margin-left:14px;">付款方：</label><input class="fac_input" type="text" value="" /></li>

				<li><label>申请日期：</label><input class="fac_input" type="text" value="" /></li>
				<li><label style="margin-left:57px;">状态：</label>
					<select>
						<option>全部</option>
						<option>待确认</option>
						<option>已确认</option>
					</select>
				</li>
			</ul>
            <ul style=" float:right; margin:20px; " >
            	<li><button class="fac_button">查询</button></li
            ></ul>
		</div>
		<!-- 查询条件 over-->
      	<!--content-->
		<div class="clearfix" style="margin:20px 0;" id="b_in_in"></div>
		<table class="fac_table " cellspacing="0"  cellpadding="5">
			<tr class="fac_tr_tab">
				<td width="3%"><input type="checkbox" value="checkbox" /></td>
				<td width="6%">融资单号</td>
				<td width="7%">待付总金额</td>
				<td width="9%">担保方式</td>
				<td width="5%">利率</td>
				<td width="6%">预计利息</td>
				<td width="7%">预计服务费</td>
				<td width="10%">预计还款日期</td>
				<td width="5%">操作员</td>
				<td width="5%">收款方</td>
				<td width="5%">付款方</td>
				<td width="5%">担保方</td>
                <td width="6%">申请日期</td>
				<td width="5%">状态</td>
				<td width="8%">操作</td
			></tr>
			<tr>
				<td><input type="checkbox" value="checkbox" /></td>
				<td><a href="#b_10001_alert" style="text-decoration:underline;">10001</a></td>
				<td>100</td>
				<td><a href="#b_dbfsone_alert" style="text-decoration:underline;">先担保后认可</a></td>
				<td></td>
				<td></td>
				<td></td>
				<td>2015-12-12</td>
				<td>操作员1</td>
				<td>龙头1</td>
				<td>供应商1</td>
				<td>学校1</td>
                <td></td>
				<td>已确认</td>
                <td></td>
			</tr>
			<tr>
				<td><input type="checkbox" value="checkbox" /></td>
				<td><a href="#b_10002_alert" style="text-decoration:underline;">10002</a></td>
				<td>100</td>
				<td><a href="#b_dbfstwo_alert" style="text-decoration:underline;">先认可后担保</a></td>
				<td></td>
				<td></td>
				<td></td>
				<td>2015-12-12</td>
				<td>操作员1</td>
				<td>龙头2</td>
				<td>供应商1</td>
				<td></td>
				<td></td>
				<td>待确认</td>
				<td><input type="button" class="fac_button" value="融资认可" /></td>                
			</tr>
 			<tr>
				<td><input type="checkbox" value="checkbox" /></td>
				<td><a href="#b_10003_alert" style="text-decoration:underline;">10003</a></td>
				<td>100</td>
				<td><a href="#b_dbfsthree_alert" style="text-decoration:underline;">先认可后担保</a></td>
				<td></td>
				<td></td>
				<td></td>
				<td>2015-12-12</td>
				<td>操作员2</td>
				<td>龙头1</td>
				<td>供应商1</td>
				<td>学校1</td>
                <td></td>
				<td>已确认</td>
                <td></td>
			</tr>           
		</table>
		<div class="fac_fanh"><button class="fac_button">撤销</button></div>
	</div>
	<!-- 融资查询 over -->
<!--10001内页-->
<div class="inner_a_box" id="b_10001_alert">
	<div class="inner_a_box_content">
        <table class="fac_table " cellspacing="0"  cellpadding="5">
            <tr class="fac_tr_tab">
                <td width="6%">订单号</td>
                <td width="7%">订单行号</td>
                <td width="6%">商品名</td>
                <td width="7%">订单行金额</td>
                <td width="7%">建议融资金额</td>
                <td width="8%">本次融资金额</td>
                <td width="10%">自付金额</td>
            </tr>
            <tr>
                <td>10001</td>
                <td>10002</td>
                <td>苹果</td>
                <td>100</td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>10001</td>
                <td>10003</td>
                <td>鸡蛋（斤）</td>
                <td>50</td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
        </table>
    </div>
    <input type="button" class="pay_button pay_button_a_in"  id="b_10001_alert_btn" value="X"/>
</div>
 <!--10001内页结束-->
 <!--10002内页-->
<div class="inner_a_box" id="b_10002_alert">
	<div class="inner_a_box_content">
        <table class="fac_table " cellspacing="0"  cellpadding="5">
            <tr class="fac_tr_tab">
                <td width="6%">订单号2</td>
                <td width="7%">订单行号</td>
                <td width="6%">商品名</td>
                <td width="7%">订单行金额</td>
                <td width="7%">建议融资金额</td>
                <td width="8%">本次融资金额</td>
                <td width="10%">自付金额</td>
            </tr>
            <tr>
                <td>10001</td>
                <td>10002</td>
                <td>苹果</td>
                <td>100</td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>10001</td>
                <td>10003</td>
                <td>鸡蛋（斤）</td>
                <td>50</td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
        </table>
    </div>
    <input type="button" class="pay_button pay_button_a_in"  id="b_10002_alert_btn" value="X"/>
</div>
 <!--10002内页结束-->
 <!--10003内页-->
<div class="inner_a_box" id="b_10003_alert">
	<div class="inner_a_box_content">
        <table class="fac_table " cellspacing="0"  cellpadding="5">
            <tr class="fac_tr_tab">
                <td width="6%">订单号3</td>
                <td width="7%">订单行号</td>
                <td width="6%">商品名</td>
                <td width="7%">订单行金额</td>
                <td width="7%">建议融资金额</td>
                <td width="8%">本次融资金额</td>
                <td width="10%">自付金额</td>
            </tr>
            <tr>
                <td>10001</td>
                <td>10002</td>
                <td>苹果</td>
                <td>100</td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>10001</td>
                <td>10003</td>
                <td>鸡蛋（斤）</td>
                <td>50</td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
        </table>
    </div>
    <input type="button" class="pay_button pay_button_a_in"  id="b_10003_alert_btn" value="X"/>
</div>
 <!--10003内页结束-->  
 <!--担保方式 one-->
<div class="inner_a_box" id="b_dbfsone_alert">
	<div class="inner_a_box_content">
        <table class="fac_table " cellspacing="0"  cellpadding="5">
            <tr class="fac_tr_tab">
                <td width="10%">担保单号</td>
                <td width="10%">担保单金额</td>
                <td width="10%">已支付金额</td>
                <td width="11%">可用担保金额</td>
                <td width="8%">付款方</td>
                <td width="10%">担保登记员</td>
                <td width="10%">到期时间</td>
                <td width="11%">本次使用金额</td>
            </tr>
            <tr>
                <td>10001</td>
                <td>￥100</td>
                <td>￥10</td>
                <td>￥90</td>
                <td>高校1</td>
                <td>asd</td>
                <td></td>
                <td>30</td>
            </tr>
            <tr>
                <td>10002</td>
                <td>￥100</td>
                <td>￥50</td>
                <td>￥50</td>
                <td>高校2</td>
                <td>111</td>
                <td></td>
                <td>30</td>
            </tr>
        </table>
    </div>
    <input type="button" class="pay_button pay_button_a_in"  id="b_dbfsone_alert_btn" value="X"/>
</div>
 <!--担保方式  one  over-->  
 <!--担保方式 two-->
<div class="inner_a_box" id="b_dbfstwo_alert">
	<div class="inner_a_box_content">
        <table class="fac_table " cellspacing="0"  cellpadding="5">
            <tr class="fac_tr_tab">
                <td width="10%">担保单号2</td>
                <td width="10%">担保单金额</td>
                <td width="10%">已支付金额</td>
                <td width="11%">可用担保金额</td>
                <td width="8%">付款方</td>
                <td width="10%">担保登记员</td>
                <td width="10%">到期时间</td>
                <td width="11%">本次使用金额</td>
            </tr>
            <tr>
                <td>10001</td>
                <td>￥100</td>
                <td>￥10</td>
                <td>￥90</td>
                <td>高校1</td>
                <td>asd</td>
                <td></td>
                <td>30</td>
            </tr>
            <tr>
                <td>10002</td>
                <td>￥100</td>
                <td>￥50</td>
                <td>￥50</td>
                <td>高校2</td>
                <td>111</td>
                <td></td>
                <td>30</td>
            </tr>
        </table>
    </div>
    <input type="button" class="pay_button pay_button_a_in"  id="b_dbfstwo_alert_btn" value="X"/>
</div>
 <!--担保方式  two  over-->    
  <!--担保方式 three-->
<div class="inner_a_box" id="b_dbfsthree_alert">
	<div class="inner_a_box_content">
        <table class="fac_table " cellspacing="0"  cellpadding="5">
            <tr class="fac_tr_tab">
                <td width="10%">担保单号3</td>
                <td width="10%">担保单金额</td>
                <td width="10%">已支付金额</td>
                <td width="11%">可用担保金额</td>
                <td width="8%">付款方</td>
                <td width="10%">担保登记员</td>
                <td width="10%">到期时间</td>
                <td width="11%">本次使用金额</td>
            </tr>
            <tr>
                <td>10001</td>
                <td>￥100</td>
                <td>￥10</td>
                <td>￥90</td>
                <td>高校1</td>
                <td>asd</td>
                <td></td>
                <td>30</td>
            </tr>
            <tr>
                <td>10002</td>
                <td>￥100</td>
                <td>￥50</td>
                <td>￥50</td>
                <td>高校2</td>
                <td>111</td>
                <td></td>
                <td>30</td>
            </tr>
        </table>
    </div>
    <input type="button" class="pay_button pay_button_a_in"  id="b_dbfsthree_alert_btn" value="X"/>
</div>
 <!--担保方式 three  over-->     
   <!--先担保后认可  inner-->
<div class="inner_a_box" id="c_in_inner_a_op" style="padding:10px 10px 0 10px; height:210px;">
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
    <input type="button" class="pay_button pay_button_a_in"  id="cininbtn_xdbhrk" value="X"/>
    <input type="button" class="pay_button" value="添加"  style=" float:right; margin:20px 50px 20px 0;"/>
</div>
 <!--先担保后认可 inner  over-->  
    
    
</div>
</body>
</html>