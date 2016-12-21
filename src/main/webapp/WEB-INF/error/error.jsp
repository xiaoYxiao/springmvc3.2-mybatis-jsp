<%@ include file="../common/tags.jsp"%>
<html>
	<head>
		<%@ include file="../common/meta_info.jsp"%>
		<%@ include file="../common/metas.jsp"%>
		<title>错误页面</title>
</head>
<body>
	<h1>出错了</h1>
	<%
		Exception e = (Exception) request.getAttribute("exception");
		out.print(e.getMessage());
	%>
</body>
</html>
