<#assign sec=JspTaglibs["/WEB-INF/security.tld"] /> <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>还款管理</title>
 <link rel="stylesheet" href="${ctx}/factoring/css/bootstrap.min111.css"/>
  <link href="${ctx}/factoring/css/theme.css" rel="stylesheet">

  <script src="${ctx}/factoring/js/jquery.js"></script>
  <script src="${ctx}/factoring/js/bootstrap.min111.js"></script>
  <script src="${ctx}/factoring/js/page/page.js"></script>

  <link href="${ctx}/factoring/css/font-awesome.min.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" media="all" href="${ctx}/factoring/css/daterangepicker-bs3.css" />
  <script type="text/javascript" src="${ctx}/factoring/js/moment.js"></script>
  <script type="text/javascript" src="${ctx}/common/js/common.js"></script>
  <script type="text/javascript" src="${ctx}/factoring/js/daterangepicker.js"></script>
  <script type="text/javascript" src="${ctx}/factoring/js/jquery.page.js"></script>
  <script type="text/javascript">
   $(document).ready(function() {
      $('#refundDate').daterangepicker({
      }, function(start, end, label) {
        console.log(start.toISOString(), end.toISOString(), label);
      });
   });
   $(document).ready(function() {
      $('#refundDate').daterangepicker({
        timePicker: true,
      }, function(start, end, label) {
        console.log(start.toISOString(), end.toISOString(), label);
      });
   });

   $(function(){
   		 //总页数
   		 var totalPage = parseInt("${totalPage!}");
   		 //当前页 
   		 var page = parseInt("${nowPage!}");
		 $('#register').click(function(){
		  	$('#selectForm').submit();
	  	 });
		 
		 $(".selectPage").createPage({
	        pageCount:totalPage,
	        current:page,
	        backFn:function(p){
	        	$("#nowPage").val(p);
				$('#selectForm').submit();
	        }
	     });
   });
	 
  function showDetailPage(){
	 var detailLi = $('.detailPage li');
	 var dePageNo =0 ;
	 dePageNo = getactiveIndex(dePageNo,detailLi);
	 //当点击的时候添加样式，获取
	 detailLi.click(function(){
	     var check = $(this).attr("class");
	     if(check != "prev disabled"){
		 	 var txt = $(this).find("a").html();
		 	 var index = $(this).index();
		 	 deNowPage = getPageNo(dePageNo,index,txt);
		 	 var financingId = $("#hidFinancingId").val();
		 	 showDetail(financingId, deNowPage);
		 }
	 });
   }
	 
   function showDetail(financingId, nowPage){
	  	
	  	$("#hidFinancingId").val(financingId);
	  	
	  	var len1 = $("#append_ul_li").find("li").length;
		for (var ii=0;ii<len1;ii++){
		   $("#append_ul_li").find("li").remove(ii);
		}
	  	
	  	var len = parseInt(show_Detail.rows.length);
	    for(var j=1;j<len;len--){
			document.getElementById("show_Detail").deleteRow(j);
		}
	        
	 	$.ajax({
			url:'${ctx}/refundMgController/findRepaymentByFinancingId.do',
			dataType:"JSON",
			type:"POST",
			data:{"financingId":financingId, "nowPage":nowPage},
			success:function(data){
			    data = eval(data);
			    var result = data.result;
			    var page = data.page;
			    var totalPage = data.totalPage;
			    var nowPage = data.nowPage;
			    for(var i=0;i<parseInt(result.length);i++){
			    	var row1 =document.getElementById("show_Detail").insertRow(i+1);
			    	row1.insertCell(0).innerHTML='<td id="refundOrderNum">'+result[i].refundOrderNum+'</td>';
			    	row1.insertCell(1).innerHTML='<td id="refundDate">'+getSmpFormatDate(new Date(result[i].refundDate), true)+'</td>';
			    	row1.insertCell(2).innerHTML='<td id="refundAmount">￥'+result[i].refundAmount+'</td>';
			    	row1.insertCell(3).innerHTML='<td id="refundSource">'+result[i].refundSource+'</td>';
			    }
			    if(totalPage!=0){
				    if(nowPage == 1){
				    	$("#append_ul_li").append("<li class='prev disabled'><a href='#'><<</a></li>");
				    }else{
				    	$("#append_ul_li").append("<li><a href='#' onclick='showDetailPage();'><<</a></li>");
				    }
				    for(var j=1;j<totalPage+1;j++){
				    	
				    	if(nowPage==j){
				    		$("#append_ul_li").append("<li class = 'active'><a href='#' onclick='showDetailPage();'>"+j+"</a></li>");
				    	}else{
				    		$("#append_ul_li").append("<li><a href='#' onclick='showDetailPage();'>"+j+"</a></li>");
				    	}
				    }
				    if(nowPage == totalPage){
				    	$("#append_ul_li").append("<li class='prev disabled'><a href='#'>>></a></li>");
				    }else{
				    	$("#append_ul_li").append("<li><a href='#' onclick='showDetailPage();'>>></a></li>");
				    }
			    }
			}
		});
  }
  
  function repayment(){
        $("#refundBtn").attr({"disabled":"disabled"});
		var financingIds = "";
		var repayJson = "{";
		var lockFinJson = "{";
		var lockGuaJson = "{";
		var a = $("#repay_tbody input:checked");
		if(a.length<=0){
			alert("请选要还款的融资单!");
			$("#refundBtn").removeAttr("disabled");
			return;
		}
		//提示信息
		var mess = "请正确填写单子:"; 
		var bool = false;
		var index = 0;
		$("#repay_tbody input:checked").each(function(i,element){
			if(i>0){
				financingIds += ","
				repayJson += ",";
				lockFinJson += ",";
			}
			
			if($(element).parent().parent().find("#repayCount").val()<=0||isNaN($(element).parent().parent().find("#repayCount").val())){
				bool = true;
				if(index>0){
					mess += "|"
				}
				mess += $(element).val();
			    index++;
			}
			financingIds += $(element).val();
			repayJson += " \""+ $(element).val().toString() + "\": \""+ $(element).parent().parent().find("#repayCount").val() +"\" ";
			lockFinJson += " \""+ $(element).val().toString() + "\": \""+ $(element).parent().find("#lockFin").val() +"\" ";
			var guaranteeId = $(element).parent().find("#guaranteeId").val();
			var guaLock = $(element).parent().find("#guaLock").val();
			var guaranteeIds = new Array();
			var guaLocks = new Array();
			guaranteeIds = guaranteeId.split(",");
			guaLocks = guaLock.split(",");
			for (i = 0; i < guaranteeIds.length; i++) {
				if(lockGuaJson.length>1){lockGuaJson += ",";}
				lockGuaJson += " \""+ guaranteeIds[i].toString() + "\": \""+ guaLocks[i].toString() +"\" ";
            }
		});
		repayJson += "}"
		lockFinJson += "}";
		lockGuaJson += "}";
		mess += "的本次还款金额!";
		if(bool){
			alert(mess);
			$("#refundBtn").removeAttr("disabled");
			return;
		}
		
		$.ajax({
			url:'${ctx}/refundMgController/batExecuteFinancingRefund.do',
			dataType:"JSON",
			type:"POST",
			data:{"financingIds":financingIds, "repaymentJson":repayJson, "lockFinJson":lockFinJson, "lockGuaJson":lockGuaJson},
			success:function(data){
			    $("#refundBtn").removeAttr("disabled");
			    alert(data);
			    $('#selectForm').submit();
			}
		});
  }
	 
  </script>

</head>
<body>
  <div class="container-fluid">
    <div class="row-fluid">
      <!-- col-sm-12 -->
      <div class="panel panel-default article-bj">
        <div class="panel-heading box-shodm">还款管理</div>
        <div class="row wrapper form-margin">
          <form name="selectForm" id="selectForm" action="${ctx}/refundMgController/selectFinancingRepaymen.do" method="post">
            <input type="hidden" name="nowPage" id="nowPage" value="1"/>
            <input type="hidden" name="hidFinancingId" id="hidFinancingId"/>
            <div class="row wrapper form-margin">
              <div class="col-md-3">
              <div class="input-group">
                <div class="input-group-btn">
                  <h5 class="h5-margin">融资单号:</h5>
                </div>
                <input type="text" placeholder="" class="form-control" name="financingId" id="financingId"></div>
              </div>
              <div class="col-md-3">
              <div class="input-group">
                <div class="input-group-btn">
                  <h5 class="h5-margin">还款日期:</h5>
                </div>
                <input type="text" placeholder="" class="form-control " name="refundDate" id="refundDate"></div>
              </div>
              <div class="col-md-3">
                <div class="input-group">
                  <div class="input-group-btn">
                    <h5 class="h5-margin">还款来源:</h5>
                  </div>
                  <select name="refundSource" tabindex="-1"class="form-control">
                   	<option value="-1">--请选择--</option>
                    <option value="1">商城</option>
                    <option value="2">保理</option>
                  </select>
                </div>
              </div>
              <div class="col-md-3">
                <div class="input-group">
                  <div class="input-group-btn">
                    <h5 class="h5-margin">状态:</h5>
                  </div>
                  <select name="status" tabindex="-1"class="form-control">
                    <option value="-1">--请选择--</option>
                    <option value="1">已还款</option>
                    <option value="2">待还款</option>

                  </select>
                </div>
              </div>
            </div>
            <a class="btn btn-info btn-s-md btn-default float-right cx-btn-margin" href="javascript:void(0);" name="register" id="register">查询</a>
          </form>
        </div>

        <div class="table-responsive panel-table-body ">
          <table class="table table-striped table-hover ">
            <thead>
              <tr>
                <th>
                  <input type="checkbox" onclick="checkAllBox(this)" name="checkAll">
                </th>
                <th>融资单号</th>
                <th>融资金额</th>
                <th>融资日期</th>
                <th>还款日期</th>
                <th>本次还款金额</th>
                <th>还款金额</th>
                <th>是否到期</th>
                <th>利息</th>
                <th>滞纳金</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody id="repay_tbody">
              <#if finList??>
					<#list finList as fin>
	              <tr>
	                <td>
	                  <input type="hidden" value="${fin.lock!}" name="lockFin" id="lockFin">
	                  <input type="hidden" value="${fin.guaranteeId!}" name="guaranteeId" id="guaranteeId">
	                  <input type="hidden" value="${fin.guaLock!}" name="guaLock" id="guaLock">
	                  <input type="checkbox" value="${fin.financingId!}" name="forLowerId" onclick="forLowerIdCheck(this)">
	                </td>
	                <td>${fin.financingId!}</td>
	                <td>${fin.waitPayTotal!}</td>
	                <td>${(fin.financingDate?string("yyyy-MM-dd HH:mm:ss"))!}</td>
	                <td>${(fin.refundDate?string("yyyy-MM-dd HH:mm:ss"))!}</td>
	                <td>
	                  	￥ <input type="text"class="input-size" name="repayCount" id="repayCount"></td>
	                <td>
	                  	已还:${fin.refundAmount!}<br>
	                  	剩余:${fin.surplusPaymentsAmount!}
	                </td>
	                <td>是</td>
	                <td></td>
	                <td></td>
	                <td>
	                  <a href="javascript:void(0);"  data-toggle="modal" data-target="#changeChar" onclick="showDetail(${fin.financingId!}, 1);">查看详情</a>
	                </td>
	              </tr>
              </#list>
              </#if>
            </tbody>
          </table>
        </div>
        <footer class="panel-footer text-right bg-light lter">
          <div class="right-footer">
          	<button class="btn btn-success btn-s-xs" type="button" id="refundBtn" onclick="return repayment();">还款</button>
          </div>
          <div class="yema-page">
    			<ul class="pagination-page selectPage">
		        </ul>
     		</div>
        </footer>
      </div>
      <!-- 查看详情表  -->
      <div class="modal fade" id="changeChar" role="dialog" aria-labelledby="gridSystemModalLabel" style="margin-left:225px;">
        <div class="modal-dialog tanchu-box" role="document">
          <div class="container-fluid">
            <div class="row-fluid">
              <div class="col-sm-12 ">
                <div class="panel panel-default">
                  <div class="panel-heading box-shodm">
                    	查看详情表
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                  </div>

                  <div class="row wrapper form-margin">
                    <form>
                      <div class="col-md-3">
                        <div class="input-group">
                          <div class="input-group-btn">
                            <h5 class="h5-margin">融资申请人:</h5>
                          </div>
                          <input type="text" placeholder="" class="form-control" name="input1-group3" id="input1-group3"></div>
                      </div>
                      <div class="col-md-3">
                        <div class="input-group">
                          <div class="input-group-btn">
                            <h5 class="h5-margin">融资金额:</h5>
                          </div>
                          <input type="text" placeholder="" class="form-control" name="input1-group3" id="input1-group3"></div>
                      </div>
                      <div class="col-md-5">
                        <div class="input-group">
                          <div class="input-group-btn">
                            <h5 class="h5-margin">预计还款日期:</h5>
                          </div>
                          <input type="text" placeholder="" class="form-control " name="reservation" id="reservationtimedate"></div>
                      </div>
                    </form>
                  </div>
                  <div class="table-responsive panel-table-body ">
                    <table class="table table-striped table-hover fixed" id="show_Detail">
                        <tr>
                          <th>还款单号</th>
                          <th>还款日期</th>
                          <th>还金额</th>
                          <th>还款来源</th>
                        </tr>
                    </table>
                    <footer class="panel-footer text-right bg-light lter">
			    	<div class="yema-page">
			    		<ul class="pagination-page detailPage" id="append_ul_li">
						 
						</ul>
					</div>
			        </footer>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!--  查看详情表 -->
    <!-- col-sm-12 --> 
  </div>
</body>
</html>