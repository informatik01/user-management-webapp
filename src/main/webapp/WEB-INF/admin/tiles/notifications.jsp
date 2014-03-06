<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="hasActionErrors()">
	<div id="errors">
		<s:actionerror cssClass="notification error" />
	</div>
</s:if>
<s:elseif test="hasActionMessages()">
	<div id="messages">
		<s:actionmessage cssClass="notification info" />
	</div>
</s:elseif>
