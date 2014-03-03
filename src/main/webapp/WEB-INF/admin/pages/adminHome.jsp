<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Admin success page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div id="container">
		<s:include value="/WEB-INF/jspf/header.jsp"></s:include>
		<s:if test="#session.SPRING_SECURITY_CONTEXT.authentication != null">
			<h1>
				<s:text name="admin.home"></s:text>
			</h1>
			<hr />
			<p>
				<a href="showAddUser">Add new user</a> | <a href="manageUsers">Manage registered users</a>
			</p>
		</s:if>
	</div>
</body>
</html>