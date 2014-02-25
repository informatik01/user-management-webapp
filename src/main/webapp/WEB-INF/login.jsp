<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
	<h1>Please sign in</h1>
	<form action="j_spring_security_check" method="post">
		<table>
			<s:if test="%{#parameters.loginError != 'null'}">
				<tr>
					<td>&nbsp;</td>
					<td><span class="error"><s:property value="%{#parameters.loginError}" /></span></td>
				</tr>
			</s:if >
			<s:textfield label="Email" name="j_username" cssStyle="width: 200px;" />
			<s:password label="Password" name="j_password" cssStyle="width: 200px;" />
			<s:submit label="Sign in" align="left"/>
		</table>
	</form>
</body>
</html>