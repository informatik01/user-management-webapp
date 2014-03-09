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
		
		<s:iterator value="roles" status="count">
			<s:textfield label="%{getText('user.role')} %{#count.index + 1}" name="roles.name" value="%{name}" cssClass="userInput" />
		</s:iterator>
		
		<s:iterator value="user.cars" status="count">
			<s:hidden name="user.cars[%{#count.index}].id"></s:hidden>
			<s:textfield label="%{getText('car.name')} %{#count.index + 1} %{getText('car.make')}"
				name="user.cars[%{#count.index}].make" value="%{user.cars[#count.index].make}" cssClass="userInput" />
			<s:textfield label="%{getText('car.name')} %{#count.index + 1} %{getText('car.plate.number')}"
				name="user.cars[%{#count.index}].plateNumber" value="%{user.cars[#count.index].plateNumber}" cssClass="userInput" />
		</s:iterator>
		
		<s:submit value="%{getText('manage.user.update')}" />
	</s:form>
</div>