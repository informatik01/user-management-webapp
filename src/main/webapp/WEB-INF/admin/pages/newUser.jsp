<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="notificationContainer">
	<s:include value="/WEB-INF/admin/tiles/notifications.jsp"></s:include>
</div>
<div id="userForm">
	<s:form action="addUser" method="post">
		<s:textfield key="user.firstName" cssClass="userInput" />
		<s:textfield key="user.lastName" cssClass="userInput" />
		<s:textfield key="user.email" cssClass="userInput" />
		<s:textfield key="user.birthDate" cssClass="userInput"/>
		<tr><td style="padding: 0">(<i><s:text name="user.birthDate.format.hint" /></i>)</td></tr>
		
		<s:textfield label="%{getText('user.role')} 1"
			name="roles.name" value="%{roles.size > 0 ? roles[0].name : ''}" cssClass="userInput" />
		<s:textfield label="%{getText('user.role')} 2"
			name="roles.name" value="%{roles.size > 1 ? roles[1].name : ''}" cssClass="userInput"  />
		<s:textfield label="%{getText('user.role')} 3"
			name="roles.name" value="%{roles.size > 2 ? roles[2].name : ''}" cssClass="userInput" />
		
		<s:textfield label="1 %{getText('car.name')} %{getText('car.make')}"
			name="user.cars[0].make" value="%{user.cars.size > 0 ? user.cars[0].make : ''}" cssClass="userInput" />
		<s:textfield label="1 %{getText('car.name')} %{getText('car.plate.number')}"
			name="user.cars[0].plateNumber" value="%{user.cars.size > 0 ? user.cars[0].plateNumber : ''}" cssClass="userInput" />
		
		<s:textfield label="2 %{getText('car.name')} %{getText('car.make')}"
			name="user.cars[1].make" value="%{user.cars.size > 1 ? user.cars[1].make : ''}" cssClass="userInput" />
		<s:textfield label="2 %{getText('car.name')} %{getText('car.plate.number')}"
			name="user.cars[1].plateNumber" value="%{user.cars.size > 1 ? user.cars[1].plateNumber : ''}" cssClass="userInput" />
		
		<s:submit value="%{getText('manage.user.add')}" />
	</s:form>
</div>
