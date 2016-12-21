<%@ include file="../common/tags.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
	<head>
		<%@ include file="../common/meta_info.jsp"%>
		<%@ include file="../common/metas.jsp"%>
		<title>ErrorPage</title>
		<script type="text/javascript">
			var url = "${pageContext.request.contextPath}/demo/user/listUser.do";
			var second = 3;
			var val;
			function changeTime(){
				val = window.setInterval(changeSeconds, 1000);
			}
			function changeSeconds(){
				second -= 1;
				document.getElementById('time').innerHTML = second;
				if(second == 0){
					window.clearInterval(val);
					window.location = url;
				}
			}
		</script>
	</head>
	<body onload="changeTime();">
		<h2>出错了，页面将在<span id="time">3</span>秒后返回用户列表！</h2>
	</body>
</html>