<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="header">
	<div style="float: right; margin-right: 15px;">
		<s:text name="header.welcome" />, <s:property value="#session.SPRING_SECURITY_CONTEXT.authentication.principal.username" />
		| <a href="${pageContext.request.contextPath}/j_spring_security_logout">Log out</a>
	</div>
	<br style="clear: both" />
</div>