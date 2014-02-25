<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<title>Home page</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js?ver=1.11.0"></script>
	<script src="${pageContext.request.contextPath}/js/login.js"></script>
</head>
<body>
	<div class="mainContainer">
		<div class="headContainer">
			<div id="navContainer">
				<ul class="nav">
					<li><a href="?request_locale=en">Eng</a></li>
					<li><a href="?request_locale=ru">Ru</a></li>
					<li><a href="?request_locale=et">Est</a></li>
				</ul>
			</div>
			<div id="loginContainer">
				<a id="loginButton" href="#"><span>Sign in</span></a>
				<div style="clear:both"></div>
				<div id="loginBox">
					<form id="loginForm" method="post" action="j_spring_security_check">
						<fieldset id="loginBody">
							<fieldset>
								<label for="email">Email</label>
								<input id="email" name="j_username" type="text" />
								<s:if test="">
									<div class="error"></div>
								</s:if>
							</fieldset>
							<fieldset>
								<label for="password">Password</label>
								<input id="password" name="j_password" type="password" />
							</fieldset>
							<input id="submitButton" type="submit" value="Sign in" />
						</fieldset>
					</form>
				</div>
			</div>
			<div style="clear: both;"></div>
			<h3><s:text name="header.message" /></h3>
		</div>
		<h1><s:text name="greeting" /></h1>
	</div>
</body>
</html>