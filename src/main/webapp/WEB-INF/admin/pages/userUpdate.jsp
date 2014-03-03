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
		<div id="userForm">
			<s:form action="updateUser" method="post">
				<s:hidden name="currentUser.id" />
				<s:textfield key="user.firstname" name="currentUser.firstName" cssClass="userInput" />
				<s:textfield key="user.lastname" name="currentUser.lastName" cssClass="userInput" />
				<s:textfield key="user.email" name="currentUser.email" cssClass="userInput" />
				<s:date name="currentUser.birthDate" format="dd/MM/YYYY" var="birthDate" />
				<s:textfield key="user.birthdate" name="currentUser.birthDate" value="%{birthDate}" cssClass="userInput" />
				<s:submit key="manage.user.update" />
			</s:form>
		</div>
	</div>
</body>
</html>