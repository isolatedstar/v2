<#assign sec=JspTaglibs["/WEB-INF/security.tld"] /> <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>销账管理</title>
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
	 
  function writeOffAcc(){
        $("#writeOffAccBtn").attr({"disabled":"disabled"});
		var ids ="";
		$("#repay_tbody input:checked").each(function(i,element){
			if(i>0){ids += ","}
			ids += $(element).val();
		});
		
		$.ajax({
			url:'${ctx}/writeOffAccController/executeWriteOffAcc.do',
			dataType:"JSON",
			type:"POST",
			data:{"ids":ids},
			success:function(data){
			    $("#writeOffAccBtn").removeAttr("disabled");
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
        <div class="panel-heading box-shodm">销账管理</div>
        <div class="row wrapper form-margin">
          <form name="selectForm" id="selectForm" action="${ctx}/writeOffAccController/selectOutInterest.do" method="post">
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
                  <h5 class="h5-margin">担保单号:</h5>
                </div>
                <input type="text" placeholder="" class="form-control" name="guaranteeId" id="financingId"></div>
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
                <th>担保单号</th>
                <th>冻结金额</th>
                <th>给付金额</th>
                <th>剩余冻结金额</th>
                <th>剩余给付金额</th>
                <th>利息</th>
                <th>待销金额</th>
              </tr>
            </thead>
            <tbody id="repay_tbody">
              <#if finGuas??>
					<#list finGuas as fin>
	              <tr>
	                <td>
	                  <input type="checkbox" value="${fin.id!}" name="forLowerId" onclick="forLowerIdCheck(this)">
	                </td>
	                <td>${fin.financingId!}</td>
	                <td>${fin.guaranteeId!}</td>
	                <td>${fin.freezeAmount!}</td>
	                <td>${fin.paymentsAmount!}</td>
	                <td>${fin.surplusFreezeAmount!}</td>
	                <td>${fin.surplusPaymentsAmount!}</td>
	                <td>${fin.interest!}</td>
                	<td>${(fin.surplusPaymentsAmount+fin.interest)!}</td>
	              </tr>
              </#list>
              </#if>
            </tbody>
          </table>
        </div>
        <footer class="panel-footer text-right bg-light lter">
          <div class="right-footer">
          	<button class="btn btn-success btn-s-xs" type="button" id="writeOffAccBtn" onclick="return writeOffAcc();">销账</button>
          </div>
          <div class="yema-page">
    			<ul class="pagination-page selectPage">
		        </ul>
     		</div>
        </footer>
      </div>
    <!-- col-sm-12 --> 
  </div>
</body>
</html>