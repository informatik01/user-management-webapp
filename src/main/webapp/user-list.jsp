<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<title>Insert title here</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
	<script src="${pageContext.request.contextPath}/js/name.js"></script>
</head>
<body>
	<h1>User data:</h1>
	<s:if test="currentUser">
		<p>First name: <s:property value="currentUser.firstName" /></p>
		<p>Last name: <s:property value="currentUser.lastName" /></p>
		<p>Date of birth: <s:date name="currentUser.birthDate" format="dd MMM YYYY" /></p>
		<p>Email: <s:property value="currentUser.email" /></p>
	</s:if>
	<s:else>
		<s:actionerror cssStyle="color: #FF0000" />
	</s:else>
</body>
</html>