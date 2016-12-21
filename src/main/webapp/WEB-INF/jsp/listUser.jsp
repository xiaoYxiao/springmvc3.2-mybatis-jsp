<%@ include file="../common/tags.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
	<head>
		<%@ include file="../common/meta_info.jsp"%>
		<%@ include file="../common/metas.jsp"%>
		<title>ListUser</title>
		<script type="text/javascript">
		function opcal(){
		    J.calendar.Show({ format:'yyyy-MM-dd HH:mm:ss' });
		}
		</script>
	</head>
	<body>
	<div>
		<h2>显示用户</h2>
		<form action="${pageContext.request.contextPath}/demo/user/listUser.do" id="testForm">
			<a href="${pageContext.request.contextPath}/demo/user/addUser.do">添加用户</a>
			<table>
				<tr align="center">
					<th>&nbsp;&nbsp;&nbsp;</th>
					<th>name：<input type="text" id="name" name="name"/>&nbsp;</th>
					<th>age：<input type="text" id="age" name="age"/>&nbsp;</th>
					<th>phone：<input type="text" id="phone" name="phone"/>&nbsp;</th>
					<th>time：<input  id="time" name="time" style="width:200px;" onclick="opcal();"/>&nbsp;</th>
					<th><input type="submit" value="查询"/></th>
				</tr>
			</table>
		</form>
	</div>
	<hr size="20" color="yellow"><!--filterable="false"默认是true，允许字段过滤 --><!--sortable="false" 默认是true，允许字段排序 -->
	<div>
		<ec:table
			view="compact"
			items="users" var="user"
			filterable="false"
  			sortable="true" 
  			retrieveRowsCallback="limit"  
		    filterRowsCallback="limit" 
			action="${pageContext.request.contextPath}/demo/user/listUser.do">
			<ec:exportPdf
				fileName="user.pdf"
				tooltip="导出PDF"
				headerColor="blue"
				headerBackgroundColor="red"
				headerTitle="用户列表"/>
			<ec:exportXls
				fileName="user.xls"
				tooltip="导出Excel"/>
			<ec:exportCsv
				fileName="user.csv"
				tooltip="导出CSV"
				delimiter=","/>
			<ec:row highlightRow="true">
				<ec:column property="name" title="姓名"/>
				<ec:column property="age" title="年龄"/>
				<ec:column property="phone" title="电话号"/>
				<ec:column property="uid" title="操作">
			 		<a href="${pageContext.request.contextPath}/demo/user/toUpdateUser.do?uid=${user.uid}">更新</a>|
					<a href="${pageContext.request.contextPath}/demo/user/deleteUser.do?uid=${user.uid}">删除</a>|
					<a href="${pageContext.request.contextPath}/demo/address/toAddNewAddress.do?uid=${user.uid}">添加新地址</a>
			 	</ec:column>
			</ec:row>
		</ec:table>
	</div>
	</body>
</html>