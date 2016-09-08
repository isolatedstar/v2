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
  <script type="text/javascript" src="${ctx}/common/js/json2.js"></script>
  <script type="text/javascript" src="${ctx}/factoring/js/jquery.page.js"></script>
  <script type="text/javascript">
  
  	function showDetail(){
	 	
	 	var financingId = $("#financingId").val();
		var refundCount = $("#refundCount").val();
		if(financingId==""){
			alert("请填写需查询的融资单号！");
			return;
		}
		if(refundCount==""){
			alert("请填还款金额！");
			return;
		}
	 	
	  	var len = parseInt(show_Detail.rows.length);
	    for(var j=1;j<len;len--){
			document.getElementById("show_Detail").deleteRow(j);
		}
		
	 	$.ajax({
			url:'${ctx}/refundMgController/selectRepaymenZhList.do',
			dataType:"JSON",
			type:"POST",
			data:{"financingId":financingId, "refundCount":refundCount},
			success:function(data){
			    data = eval('('+data+')');
			    var restr = data.restr;
			    var err = data.err;
			    var fin = data.fin;
			    $("#hidFinancingId").val(data.financingId);
			    $("#lockFinJson").val(JSON.stringify(data.lockFinJson));
			    $("#lockGuaJson").val(JSON.stringify(data.lockGuaJson));
			    $("#repaymentJson").val(JSON.stringify(data.repaymentJson));
			    if(typeof(err)!="undefined"){
			    	alert(err)
			    }else{
				    var messDetail = restr.split(",");
				    for(var i=0;i<parseInt(messDetail.length);i++){
				    	var row1 =document.getElementById("show_Detail").insertRow(i+1);
				    	row1.insertCell(0).innerHTML='<td id="messDetail">'+messDetail[i]+'</td>';
				    }
				    $("#changeChar").modal("show");
			    }
			}
		});
  	}
  	
  	function executeMessage(){
	 	$.ajax({
			url:'${ctx}/refundMgController/executeFinancingRefund.do',
			dataType:"JSON",
			type:"POST",
			data:{"financingId":$("#hidFinancingId").val(), "lockFinJson":$("#lockFinJson").val(), "lockGuaJson":$("#lockGuaJson").val(), "repaymentJson":$("#repaymentJson").val()},
			success:function(data){
				alert(data);
				$("#changeChar").modal("hide");
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
        <div class="panel-heading box-shodm">自还款</div>
        <div class="row wrapper form-margin">
          <form name="selectForm" id="selectForm" method="post">
            <input type="hidden" name="hidFinancingId" id="hidFinancingId"/>
            <input type="hidden" name="lockFinJson" id="lockFinJson"/>
            <input type="hidden" name="lockGuaJson" id="lockGuaJson"/>
            <input type="hidden" name="repaymentJson" id="repaymentJson"/>
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
                  <h5 class="h5-margin">还款金额:</h5>
                </div>
                <input type="text" placeholder="" class="form-control " name="refundCount" id="refundCount"></div>
              </div>
            </div>
          </form>
        </div>
        <footer class="panel-footer text-right bg-light lter">
          <div class="right-footer">
          	<button class="btn btn-success btn-s-xs" type="button" id="messBtn" onclick="return showDetail();">查看分款方案</button>
          </div>
          <div class="yema-page">
    			<ul class="pagination-page selectPage">
    				</br>
		        </ul>
     		</div>
        </footer>
      </div>
      <!-- 转账报文明细  -->
      <div class="modal fade" id="changeChar" role="dialog" aria-labelledby="gridSystemModalLabel" style="margin-left:225px;">
        <div class="modal-dialog tanchu-box" role="document">
          <div class="container-fluid">
            <div class="row-fluid">
              <div class="col-sm-12 ">
                <div class="panel panel-default">
                  <div class="panel-heading box-shodm">
                    	转账报文明细
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                  </div>
                  <div class="table-responsive panel-table-body ">
                    <table class="table table-striped table-hover fixed" id="show_Detail">
                        <tr>
                          	<th>
							</th>
                        </tr>
                    </table>
                    <footer class="panel-footer text-right bg-light lter">
			    	<div class="yema-page">
			    		<ul class="pagination-page detailPage" id="append_ul_li">
						 
						</ul>
					</div>
					<div class="right-footer">
			          	<button class="btn btn-success btn-s-xs" type="button" id="messBtn" onclick="return executeMessage();">审核</button>
			        </div>
			        <div class="yema-page">
		    			<ul class="pagination-page selectPage">
		    				</br>
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