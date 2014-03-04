<%@ taglib prefix="s" uri="/struts-tags"%>

<s:url value="" includeParams="all" var="en">
	<s:param name="request_locale">en</s:param>
</s:url>
<s:url value="" includeParams="all" var="ru">
	<s:param name="request_locale">ru</s:param>
</s:url>
<s:url value="" includeParams="all" var="et">
	<s:param name="request_locale">et</s:param>
</s:url>

<div id="header">
	<div id="navigation">
		<ul class="nav">
			<li><a href="<s:property value="en"/>">Eng</a></li>
			<li><a href="<s:property value="ru"/>">Rus</a></li>
			<li><a href="<s:property value="et"/>">Est</a></li>
		</ul>
	</div>
	<div style="float: left">
		<a href="<s:url value="/admin/home" />"><s:text name="admin.gohome" /></a>
	</div>
	<div style="float: right; margin-right: 15px;">
		<s:text name="header.welcome" />,
		<s:property value="#session.SPRING_SECURITY_CONTEXT.authentication.principal.username" />
		| <a href="${pageContext.request.contextPath}/j_spring_security_logout"><s:text name="header.signout" /></a>
	</div>
	<br style="clear: both" />
</div>