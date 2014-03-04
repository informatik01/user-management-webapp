<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<title>User update</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
	<div id="container">
		<s:include value="/WEB-INF/jspf/header.jsp"></s:include>
		<s:include value="/WEB-INF/jspf/notifications.jsp"></s:include>
		<div id="userForm">
			<s:form action="updateUser" method="post">
				<s:hidden name="user.id" />
				<s:textfield key="user.firstName" cssClass="userInput" />
				<s:textfield key="user.lastName" cssClass="userInput" />
				<s:textfield key="user.email" cssClass="userInput" />
				<s:date name="user.birthDate" format="%{getText('user.birthDate.format')}" var="birthDate" />
				<s:textfield key="user.birthDate" value="%{birthDate}" cssClass="userInput" />
				<s:submit value="%{getText('manage.user.update')}" />
			</s:form>
		</div>
	</div>
</body>
</html>