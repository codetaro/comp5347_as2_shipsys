<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" media="screen" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#submit").click(function(e) {
		var tracking_num = $("#tracking_num").val();
		$("#querySub").load("${pageContext.request.contextPath}/packages/query/" + tracking_num);
	});
});
</script>
<title>Check your packages</title>
</head>
<body>
<div id="queryMain">
<h2>Please input a tracking number</h2>
<input type="text" id="tracking_num"/>
<input type="button" value="Show Result" class="button" id="submit"/>
</div>
<div id="querySub"></div>
</body>
</html>