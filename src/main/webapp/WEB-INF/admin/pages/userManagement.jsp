<%@ taglib prefix="s" uri="/struts-tags"%>

<link rel="stylesheet" type="text/css" href="<s:url value="/css/jquery.tablesorter.css"/>">
<link rel="stylesheet" type="text/css" href="<s:url value="/css/jquery.tablesorter.pager.css"/>">
<script src="<s:url value="/js/jquery-latest.js"/>"></script>
<script src="<s:url value="/js/jquery.tablesorter.js"/>"></script>
<script src="<s:url value="/js/jquery.tablesorter.pager.js"/>"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("table").tablesorter({
			headers : {
				2 : {
					sorter : false
				}
			},
			sortList : [ [ 0, 0 ] ],
		}).tablesorterPager({
			container : $("#pager"),
			size : 10
		});
	});
</script>
<h1>
	<s:text name="manage.header" />
</h1>
<s:include value="/WEB-INF/admin/tiles/notifications.jsp"></s:include>
<s:if test="users == null || users.empty">
	<p style="font-size: 1.3em;">
		<s:text name="manage.nousers" />
	</p>
</s:if>
<s:else>
	<table class="userList tablesorter">
		<thead>
			<tr>
				<th><s:text name="user.firstName" /></th>
				<th><s:text name="user.lastName" /></th>
				<th><s:text name="manage.edit" /></th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="users">
				<s:url value="showUpdateUser" includeParams="all" escapeAmp="false" var="updateUser">
					<s:param name="userId" value="%{id}" />
				</s:url>
				<s:url value="deleteUser" includeParams="all" escapeAmp="false" var="deleteUser">
					<s:param name="userId" value="%{id}" />
				</s:url>
				<tr>
					<td><s:property value="firstName" /></td>
					<td><s:property value="lastName" /></td>
					<td class="edit"><a href="<s:property value="updateUser" />"><s:text name="manage.update" /></a> | <a
						href="<s:property value="deleteUser" />"><s:text name="manage.delete" /></a></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<div id="pager" class="pager">
		<form>
			<img src="<s:url value="/img/tablesorter/pager/first.png" />" class="first" /> <img src="<s:url value="/img/tablesorter/pager/prev.png" />"
				class="prev" /> <input type="text" class="pagedisplay" /> <img src="<s:url value="/img/tablesorter/pager/next.png" />" class="next" /> <img
				src="<s:url value="/img/tablesorter/pager/last.png" />" class="last" /> <select class="pagesize">
				<option selected="selected" value="10">10</option>
				<option value="50">50</option>
				<option value="100">100</option>
			</select>
		</form>
	</div>
</s:else>
