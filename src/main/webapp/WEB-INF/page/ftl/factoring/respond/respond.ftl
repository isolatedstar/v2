<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />   <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <title>直接融资</title>
   
     <link rel="stylesheet" href="${ctx}/css/bootstrap.min111.css"/>
  <link href="${ctx}/css/theme.css" rel="stylesheet">

  <script src="${ctx}/js/jquery.js"></script>
  <script src="${ctx}/js/bootstrap.min111.js"></script>

  <link href="${ctx}/css/theme.css" rel="stylesheet">
  <link href="${ctx}/css/font-awesome.min.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" media="all" href="${ctx}/css/daterangepicker-bs3.css" />
  <script type="text/javascript" src="${ctx}/js/moment.js"></script>
  <script type="text/javascript" src="${ctx}/js/daterangepicker.js"></script>
   <style>
   .modal-dialog{width:1000px}
   </style>
   
   <script>
	$(function(){
		document.getElementById("td_1").colSpan="9"; 
		document.getElementById("td_2").colSpan="15"; 
	})
	
	 $(document).ready(function() {
                  $('#reservationtime').daterangepicker({
                    timePicker: true,
                    timePickerIncrement: 30,
                    format: 'MM/DD/YYYY h:mm A'
                  }, function(start, end, label) {
                    console.log(start.toISOString(), end.toISOString(), label);
                  });
               });
                 $(document).ready(function() {
                  $('#reservationtimedate').daterangepicker({
                    timePicker: true,
                    timePickerIncrement: 30,
                    format: 'MM/DD/YYYY h:mm A'
                  }, function(start, end, label) {
                    console.log(start.toISOString(), end.toISOString(), label);
                  });
               });
	
	function XZAssure(){
		//删除行
		var len = parseInt(Assure_select.rows.length);
	    for(var j=1;j<len;len--){
			document.getElementById("Assure_select").deleteRow(j);
		}
	
		var groupId = "4028800846c650a60146cb8653a20083";
		$.ajax({
			url:'${ctx}/directFinancingController/assureResourcesNoFilter.do',
			dataType:"JSON",
			type:"POST",
			data:{"groupId":groupId},
			success:function(data){
			    data = eval("("+data+")")
			    
			    for(var i=0;i<parseInt(data.length);i++){
			    	var row1 =document.getElementById("Assure_select").insertRow(i+1);
			    	row1.insertCell(0).innerHTML='<td id="guaranteetId">'+data[i].guaranteetId+'</td>';
			    	row1.insertCell(1).innerHTML='<td id="guaranteeAmount">'+data[i].guaranteeAmount+'</td>';
			    	row1.insertCell(2).innerHTML='<td id="paymentAmount">'+data[i].paymentAmount+'</td>';
			    	row1.insertCell(3).innerHTML='<td id="nonPayAmount">'+data[i].nonPayAmount+'</td>';
			    	row1.insertCell(4).innerHTML='<td id="payerName">'+data[i].payerName+'</td>';
			    	row1.insertCell(5).innerHTML='<td id="guaranteeName">'+data[i].guaranteeName+'</td>';
			    	row1.insertCell(6).innerHTML='<td id="expireDate">'+data[i].expireDate+'</td>';
			    	row1.insertCell(7).innerHTML='<td id="bcsy_money"><input id="inp_txt" type="text" value="" style="width:70px; height:20px;"></td>';
			    }
			    $("#myModal1").modal('show');
			}
		})
	}
	
	function addAssure(){
	
		var ids;
		var BCSY_money;
		var jsonStr="[";
		
		$('#Assure_select').find("tr:not(first)").each(function(i,element){
			
			if($(element).find("[id=inp_txt]").val()!=null || $(element).find("[id=inp_txt]").val()!=undefined ){
				if($(element).find("[id=inp_txt]").val() == ""){
					$("#myModal1").modal('hide');
				}else{
					var guaranteetId= $(element).find("td:eq(0)").html();
					var guaranteeAmount= $(element).find("td:eq(1)").html();
					var nonPayAmount= $(element).find("td:eq(3)").html();
					var guaranteeName= $(element).find("td:eq(5)").html();
					var bcsy_money = $(element).find("[id=inp_txt]").val();
					
					var row2 = document.getElementById("Select_Assure").insertRow(Select_Assure.rows.length);
					row2.insertCell(0).innerHTML='<td id="checkbox"><input type="checkbox" /></td>';
					row2.insertCell(1).innerHTML='<td id="guaranteetId">'+guaranteetId+'</td>';
			    	row2.insertCell(2).innerHTML='<td id="guaranteeAmount">'+guaranteeAmount+'</td>';
			    	row2.insertCell(3).innerHTML='<td id="nonPayAmount">'+nonPayAmount+'</td>';
			    	row2.insertCell(4).innerHTML='<td id="guaranteeName">'+guaranteeName+'</td>';
			    	row2.insertCell(5).innerHTML='<td id="bcsy_money">'+bcsy_money+'</td>';
				}
			}
				$("#myModal1").modal('hide');	
		})
	}
	
	function checkAllBox(obj){
		$(obj).parent().parent().parent().find("[value = checkbox]:checkbox").each(function(i,thisElement){
			if($(obj).prop("checked")==true){
		      $(thisElement).prop("checked",'true');
			}else{
				$(thisElement).prop("checked",false);
			}
		})
	}
   </script>

</head>
<body>
  <div class="container-fluid">
    <div class="row-fluid">

      <!-- col-sm-12 -->

      <div class="panel panel-default article-bj">
        <div class="panel-heading box-shodm">意向回应管理表</div>

        <div class="row wrapper form-margin">
          <form>
            <div class="row wrapper form-margin">
              <form>
                <div class="col-md-3">
                  <div class="input-group">
                    <div class="input-group-btn">
                      <h5 class="h5-margin">意向单号:</h5>
                      <!--   <button class="btn" type="button"></button>
                    -->
                  </div>
                  <input type="text" placeholder="" class="form-control" name="input1-group3" id="input1-group3"></div>
              </div>
              <div class="col-md-3">
                <div class="input-group">
                  <div class="input-group-btn">
                    <h5 class="h5-margin">操作员:</h5>

                  </div>
                  <input type="text" placeholder="" class="form-control" name="input1-group3" id="input1-group3"></div>
              </div>
              <div class="col-md-3">
                <div class="input-group">
                  <div class="input-group-btn">
                    <h5 class="h5-margin">预计还款日期:</h5>

                  </div>
                  <input type="text" placeholder="" class="form-control " name="reservation" id="reservationtime">
                  <!--  name="input1-group3" --> </div>
              </div>
              <div class="col-md-3">
                <div class="input-group">
                  <div class="input-group-btn">
                    <h5 class="h5-margin">操作日期:</h5>

                  </div>
                  <input type="text" placeholder="" class="form-control "  name="reservation" id="reservationtimedate" ></div>
              </div>
              <div class="col-md-3">
                <div class="input-group">
                  <div class="input-group-btn">
                    <h5 class="h5-margin">意向状态:</h5>
                  </div>
                  <select name="selecter_basic" tabindex="-1"class="form-control">
                    <option value="1">全部</option>
                    <option value="2">未处理</option>
                    <option value="3">待通过</option>
                  </select>
                </div>
              </div>
              <a class="btn btn-info btn-s-md btn-default float-right cx-btn-margin" href="#">查询</a>
            </form>
          </div>
        </form>
      </div>

      <div class="table-responsive panel-table-body ">
        <table class="table table-striped table-hover">
          <thead>
            <tr>
              <th>
                <input type="checkbox"></th>

              <th>意向单号</th>
              <th>意向金额</th>
              <th>预计还款日</th>
              <th>本次担保金额</th>
              <th>预计利息</th>
              <th>预计服务费</th>
              <th>利率</th>
              <th>操作员</th>
              <th>是否意向达成</th>
              <th>操作时间</th>

            </tr>
          </thead>
          <tbody>
            <tr>
              <td>
                <input type="checkbox"></td>
              <td>100010</td>
              <td>100</td>
              <td>2015-12-12</td>
              <td>aaa</td>
              <td>100</td>
              <td>
                <input type="text"class="input-size"></td>
              <td>
                <input type="text"class="input-size"></td>
              <td>100</td>
              <td>已达成</td>
              <td>2016-2-29</td>
            </tr>
            <tr>
              <td>
                <input type="checkbox"></td>
              <td>100010</td>
              <td>100</td>
              <td>2015-12-12</td>
              <td>aaa</td>
              <td>100</td>
              <td>
                <input type="text"class="input-size"></td>
              <td>
                <input type="text"class="input-size"></td>
              <td>100</td>
              <td>已达成</td>
              <td>2016-2-29</td>
            </tr>
            <tr>
              <td>
                <input type="checkbox"></td>
              <td>100010</td>
              <td>100</td>
              <td>2015-12-12</td>
              <td>aaa</td>
              <td>100</td>
              <td>
                <input type="text"class="input-size"></td>
              <td>
                <input type="text"class="input-size"></td>
              <td>100</td>
              <td>已达成</td>
              <td>2016-2-29</td>
            </tr>
            <tr>
              <td>
                <input type="checkbox"></td>
              <td>100010</td>
              <td>100</td>
              <td>2015-12-12</td>
              <td>aaa</td>
              <td>100</td>
              <td>
                <input type="text"class="input-size"></td>
              <td>
                <input type="text"class="input-size"></td>
              <td>100</td>
              <td>已达成</td>
              <td>2016-2-29</td>
            </tr>
          </tbody>
        </table>

      </div>
      <footer class="panel-footer text-right bg-light lter">
        <div class="right-footer">
          <button class="btn btn-success btn-s-xs" type="submit"data-toggle="modal" data-target="#shenqingbiao">申请</button>
          <button class="btn btn-success btn-s-xs" type="submit">撤回</button>
        </div>
        <div class="yema-page">
          <ul class="pagination-page ">
            <li class="prev disabled">
              <a href="#">
                <<</a></li>
                <li class="active">
                  <a href="#">1</a>
                </li>
                <li>
                  <a href="#">2</a>
                </li>
                <li>
                  <a href="#">3</a>
                </li>
                <li>
                  <a href="#">4</a>
                </li>
                <li>
                  <a href="#">5</a>
                </li>
                <li class="next">
                  <a href="#">>></a>
                </li>
              </ul>
            </div>
          </footer>
        </div>

        <div class="modal fade" id="shenqingbiao" role="dialog" aria-labelledby="gridSystemModalLabel">
          <div class="modal-dialog tanchu-box" role="document">

            <div class="tanchu-box">
              <div class="panel panel-default tanchu-box">
                <div class="panel-heading box-shodm">
                  申请表
                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>

                <div class="row wrapper form-margin">
                  <form>
                    <div class="col-md-12">
                      <div class="input-group">
                        <div class="input-group-btn">
                          <h5 class="h5-margin">账户:</h5>

                        </div>
                        <input type="text" placeholder="" class="form-control" name="input1-group3" id="input1-group3"></div>
                    </div>
                    <div class="col-md-12">
                      <div class="input-group">
                        <div class="input-group-btn">
                          <h5 class="h5-margin">利率:</h5>

                        </div>
                        <input type="text" placeholder="" class="form-control" name="input1-group3" id="input1-group3"></div>
                    </div>

                    <a class="btn btn-info btn-s-md btn-default float-right cx-btn-margin" href="#">确定</a>
                  </form>
                </div>

              </div>

            </div>
          </div>

          <!-- col-sm-12 --> </div>
      </div>
    </div>
</body>
</html>