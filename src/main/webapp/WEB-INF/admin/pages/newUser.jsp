<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<title>New user</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js?ver=2.1.0"></script>
</head>
<body>
	<script type="text/javascript">
	
		// TODO add onmousedown
		function clearInput() {
			$("#birthDate").val("").removeClass("inputHelp");
		}
		
		// TODO add onblur
		function showDateFormatHelp() {
			$("#birthDate").val("<s:text name='user.birthdate.format' />").addClass("inputHelp");
		}
	
	</script>
	<div id="container">
		<s:include value="/WEB-INF/jspf/header.jsp"></s:include>
		<div id="userForm">
			<s:form action="addUser" method="post">
				<s:textfield key="user.firstname" name="currentUser.firstName" cssClass="userInput" />
				<s:textfield key="user.lastname" name="currentUser.lastName" cssClass="userInput" />
				<s:textfield key="user.email" name="currentUser.email" cssClass="userInput" />
				<s:textfield id="birthDate" key="user.birthdate" format="%{getText('user.birthdate.format')}" name="currentUser.birthDate"
					cssClass="inputHelp userInput"  />
				<s:submit key="manage.user.add" />
			</s:form>
		</div>
	</div>
</body>
</html>