<#assign hasFieldErrors = parameters.name?? && fieldErrors?? && fieldErrors[parameters.name]??/>
<#if hasFieldErrors>
		<#list fieldErrors[parameters.name] as error>
			
				<td style="color: #FF0000; text-align: left; padding-left: 5px;" colspan="2"><#rt/>
					<span class="errorMessage">${error?html}</span><#t/>
				</td><#lt/>
			
		</#list>
	</#if>