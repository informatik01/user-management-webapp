<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="header">
	<div id="navigation">
		<ul class="nav">
			<li><a href="?request_locale=en">Eng</a></li>
			<li><a href="?request_locale=ru">Ru</a></li>
			<li><a href="?request_locale=et">Est</a></li>
		</ul>
	</div>
	<div style="float: left">
		<a href="<s:url value="/admin/home" />"><s:text name="admin.gohome" /></a>
	</div>
	<div style="float: right; margin-right: 15px;">
		<s:text name="header.welcome" />
		,
		<s:property value="#session.SPRING_SECURITY_CONTEXT.authentication.principal.username" />
		| <a href="${pageContext.request.contextPath}/j_spring_security_logout"><s:text name="header.signout" /></a>
	</div>
	<br style="clear: both" />
</div>