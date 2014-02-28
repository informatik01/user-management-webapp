<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<title>Insert title here</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.tablesorter.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.tablesorter.pager.css">
	<script src="${pageContext.request.contextPath}/js/jquery-latest.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.tablesorter.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.tablesorter.pager.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("table").tablesorter({
				sortList : [ [ 0, 1 ] ]
			}).tablesorterPager({
				container : $("#pager"),
				size : 5
			});
		});
	</script>
</head>
<body>
	<div id="mainContainer">
		<div style="float: right; margin-right: 15px;">
			<a href="${pageContext.request.contextPath}/j_spring_security_logout">Log out</a>
		</div>
		<h1>Manage Users</h1>
		<s:if test="hasActionErrors()">
			<s:actionerror cssStyle="color: #FF0000" />
		</s:if>
		<s:elseif test="users == null || users.empty">
			<p><s:text name="manage.no_users" />
		</s:elseif>
		<s:else>
			<table class="userList tablesorter">
				<thead>
					<tr>
						<th><s:text name="user.firstname" /></th>
						<th><s:text name="user.lastname" /></th>
						<th><s:text name="user.birthdate" /></th>
						<th><s:text name="user.email" /></th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="users">
						<tr>
							<td><s:property value="firstName" /></td>
							<td><s:property value="lastName" /></td>
							<td><s:date name="birthDate" format="dd MMM YYYY" /></td>
							<td><s:property value="email" /></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<div id="pager" class="pager">
				<form>
					<img src="<s:url value="/img/tablesorter/pager/first.png" />" class="first" />
					<img src="<s:url value="/img/tablesorter/pager/prev.png" />" class="prev" />
					<input type="text" class="pagedisplay" />
					<img src="<s:url value="/img/tablesorter/pager/next.png" />" class="next" />
					<img src="<s:url value="/img/tablesorter/pager/last.png" />" class="last" />
					<select class="pagesize">
						<option selected="selected" value="5">5</option>
						<option value="10">10</option>
						<option value="30">30</option>
						<option value="50">50</option>
					</select>
				</form>
			</div>
		</s:else>
	</div>
</body>
</html>