<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<title>Admin success page</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
	<div id="container">
		<s:include value="/WEB-INF/jspf/header.jsp"></s:include>
		<s:if test="#session.SPRING_SECURITY_CONTEXT.authentication != null">
			<h1>
				<s:text name="admin.info"></s:text>
			</h1>
			<hr />
			<p>
				<a href="showAddUser"><s:text name="manage.user.add" /></a>
				| <a href="manageUsers"><s:text name="manage.user.update" /></a>
			</p>
		</s:if>
	</div>
</body>
</html>