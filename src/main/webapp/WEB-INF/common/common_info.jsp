<%@ include file="tags.jsp"%>
<html>
	<head>
		<%@ include file="../common/meta_info.jsp"%>
		<%@ include file="metas.jsp"%>
		<title>信息展示</title>
		<script type="text/javascript">
			function to_close(upload, active) {
				TB_remove();
				if (undefined !=upload && null != upload) {
					parent.location.reload();
					closewindow();
				} else if (undefined !=active && null != active) {
					parent.mbdif.location.reload();
					refreshpage();
					closewindow();
				} else {
					parent.mbdif.location.reload();
					closewindow();
				}
			}
		</script>
</head>
<body>
	<div class="main-body" id="main_body">
		<div class="div-pl">
			<c:if test="${successFlag==false}">
				<div class="use-wrong search-wrong">
				    <span class="ft-cl-n ft-sz-14 ft-wt-b"> <c:out value="${message}"/></span> <br /> 
				<span class="ft-cl-n ft-sz-14 ">很抱歉！出错啦，没有成功！</span>
			</c:if>
			<c:if test="${successFlag!=false}">
				<div class="use-sucess search-wrong">
				 <span class="ft-cl-n ft-sz-14 ft-wt-b"> <c:out value="${message}"/></span> <br /> 
				<span class="ft-cl-n ft-sz-14 ">恭喜，操作已成功！</span>
			</c:if>
			<div class="divH12"></div>
			<input type="button" class="btn-sh"
				<c:if test="${!empty redirectionUrl}
					onclick="window.location.href='${BasePath}'+'${redirectionUrl}'"
				</c:if>
				<c:if test="${empty redirectionUrl}
					onclick="to_close('<c:out value="${upload}"/>','<c:out value="${active}"/>');"
				</c:if>
				value="确 定" hidefocus="true" />
			</div>
		</div>
	</div>
</body>
</html>
