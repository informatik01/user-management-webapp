<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<title>Login page</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
	<div id="mainContainer">
		<div>
			<a href="${pageContext.request.contextPath}"><s:text name="general.gohome" /></a>
		</div>
		<div id="springLoginForm">
			<h1><s:text name="header.signin"/></h1>
			<form action="j_spring_security_check" method="post">
				<table>
					<s:if test="%{#parameters.loginError}">
						<tr>
							<td>&nbsp;</td>
							<td><span class="error"><s:text name="error.login" /></span></td>
						</tr>
					</s:if>
					<s:textfield label="%{getText('user.email')}" name="j_username" cssStyle="width: 200px;" />
					<s:password label="%{getText('user.password')}" name="j_password" cssStyle="width: 200px;" />
					<s:submit label="%{getText('header.signin')}" align="left" />
				</table>
			</form>
		</div>
	</div>
</body>
</html>