<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<title>Login page</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
	<style type="text/css">
		#loginForm {
			/* Centering the login form */
			width: 40%;
			margin: 0 auto;
		}
	</style>
</head>
<body>
	<div id="mainContainer">
		<div>
			<a href="${pageContext.request.contextPath}"><s:text name="general.gohome" /></a>
		</div>
		<div id="loginForm">
			<h1>Please sign in</h1>
			<form action="j_spring_security_check" method="post">
				<table>
					<s:if test="%{#parameters.loginError}">
						<tr>
							<td>&nbsp;</td>
							<td><span class="error"><s:text name="error.login" /></span></td>
						</tr>
					</s:if>
					<s:textfield label="Email" name="j_username" cssStyle="width: 200px;" />
					<s:password label="Password" name="j_password" cssStyle="width: 200px;" />
					<s:submit label="Sign in" align="left" />
				</table>
			</form>
		</div>
	</div>
</body>
</html>