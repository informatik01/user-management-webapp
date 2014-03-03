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
				<s:property value="#session.SPRING_SECURITY_CONTEXT.authentication.principal.username" />
				, you have successfully logged in to the admin area!
			</h1>
			<hr />
			<p>
				<a href="manageUsers">Manage registered users</a>
			</p>
		</s:if>
	</div>
</body>
</html>