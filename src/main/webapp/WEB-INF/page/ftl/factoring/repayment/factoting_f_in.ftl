<#assign sec=JspTaglibs["/WEB-INF/security.tld"] /> <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>还款登记</title>
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
</head>
<body>
  <div class="container-fluid">
    <div class="row-fluid">
      <form role="form" action="${ctx}/refundMgController/disposeGuaranteeRefund.do" method="post" class="login-form">
        	<div class="form-group">
        		<label class="sr-only" for="form-username">结款单号</label>
            	<input type="text" name="guaranteereId" placeholder="结款单..." class="form-username form-control" id="form-username">
            </div>
            <div class="form-group">
            	<label class="sr-only" for="form-password">金额</label>
            	<input type="text" name="refundAmount" placeholder="金额..." class="form-password form-control" id="form-password">
            </div>
            <button type="submit" class="btn">担保还款</button>
      </form>
    </div>
  </div>
</body>
</html>