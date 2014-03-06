<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript">
	// TODO add onmousedown
	function clearInput() {
		$("#birthDate").val("").removeClass("inputHelp");
	}

	// TODO add onblur
	function showDateFormatHelp() {
		$("#birthDate").val("<s:text name='user.birthDate.format' />")
				.addClass("inputHelp");
	}
</script>
<div id="notificationContainer">
	<s:include value="/WEB-INF/admin/tiles/notifications.jsp"></s:include>
</div>
<div id="userForm">
	<s:form action="addUser" method="post">
		<s:textfield key="user.firstName" cssClass="userInput" />
		<s:textfield key="user.lastName" cssClass="userInput" />
		<s:textfield key="user.email" cssClass="userInput" />
		<s:textfield key="user.birthDate" 
					 cssClass="inputHelp userInput" onmousedown="clearInput()" />
		<tr><td style="padding: 0">(<i><s:text name="user.birthDate.format.hint" /></i>)</td></tr>
		<s:textfield label="%{getText('user.role')} 1" name="roles.name" cssClass="userInput" />
		<s:textfield label="%{getText('user.role')} 2" name="roles.name" cssClass="userInput"  />
		<s:textfield label="%{getText('user.role')} 3" name="roles.name" cssClass="userInput" />
		<s:submit value="%{getText('manage.user.add')}" />
	</s:form>
</div>
