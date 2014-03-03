<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<title>Client success page</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div style="float: right; margin-right: 15px;">
		<a href="${pageContext.request.contextPath}/j_spring_security_logout">Log out</a>
	</div>
	<s:if test="#session.SPRING_SECURITY_CONTEXT.authentication != null">
		<h1><s:property value="#session.SPRING_SECURITY_CONTEXT.authentication.principal.username" />, you have successfully logged in to the client area!</h1>
	</s:if>
</body>
</html>