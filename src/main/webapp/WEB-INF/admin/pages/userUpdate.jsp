<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="notificationContainer">
	<s:include value="/WEB-INF/admin/tiles/notifications.jsp"></s:include>
</div>
<div id="userForm">
	<s:form action="updateUser" method="post">
		<s:hidden name="user.id" />
		<s:textfield key="user.firstName" cssClass="userInput" />
		<s:textfield key="user.lastName" cssClass="userInput" />
		<s:textfield key="user.email" cssClass="userInput" />
		<s:textfield key="user.birthDate" cssClass="userInput" />
		<tr><td style="padding: 0">(<i><s:text name="user.birthDate.format.hint" /></i>)</td></tr>
		
		<s:iterator value="roles" status="status">
			<s:textfield label="%{getText('user.role')} %{#status.count}" name="roles.name" value="%{name}" cssClass="userInput" />
		</s:iterator>
		
		<s:iterator value="user.cars" status="status">
			<s:hidden name="user.cars[%{#status.index}].id"></s:hidden>
			<s:textfield label="%{getText('car.name')} %{#status.count} %{getText('car.make')}"
				name="user.cars[%{#status.index}].make" value="%{user.cars[#status.index].make}" cssClass="userInput" />
			<s:textfield label="%{getText('car.name')} %{#status.count} %{getText('car.plate.number')}"
				name="user.cars[%{#status.index}].plateNumber" value="%{user.cars[#status.index].plateNumber}" cssClass="userInput" />
		</s:iterator>
		
		<s:submit value="%{getText('manage.user.update')}" />
	</s:form>
</div>