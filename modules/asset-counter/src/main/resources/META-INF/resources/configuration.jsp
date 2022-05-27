<%@ include file="/init.jsp" %>
<%
List<AssetRendererFactory<?>> assetRendererFactories = AssetRendererFactoryRegistryUtil.getAssetRendererFactories(company.getCompanyId());




%>

<portlet:defineObjects />

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<%

	request.setAttribute("categoryId","39046");
	String[] tagNames = portletPreferences.getValues("tagNames",null);
	String tagNamesStr ="";
	if(tagNames!=null){
		for(String tagName:tagNames){
			System.out.println(tagName);
			tagNamesStr = tagNamesStr + tagName+ ",";
		}
	}



%>

<div class="container">
	<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	
		<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />
	
		<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />
		
		

		
		
		<div class="mt-3"></div>
		<aui:fieldset label="asset-class-name-id">
			<aui:select label=""  name="assetClassNameId" value='<%= (String)portletPreferences.getValue("assetClassNameId", "") %>'>
			<%
			for (AssetRendererFactory assetRendererFactory : assetRendererFactories) {
				%>
				
					<aui:option label="<%= assetRendererFactory.getTypeName(locale)  %>" value="<%=assetRendererFactory.getClassNameId()  %>" />
				<%
				}
			
			%>
				
			</aui:select>
		</aui:fieldset>
	
		<hr/>
		<!--  
		<aui:fieldset label="category-selection">
			<liferay-asset:asset-categories-selector categoryIds="39161,39129" singleSelect="<%= true %>"  hiddenInput="categoryId">
			
			</liferay-asset:asset-categories-selector>
		</aui:fieldset>
		<hr/>-->
		<aui:fieldset label="tag-selection">
			<liferay-asset:asset-tags-selector tagNames="<%=tagNamesStr %>"/>
	
			<aui:select label="tag-selection-type"  name="tagSelectionType" value="<%= (String)portletPreferences.getValue("tagSelectionType", "") %>">
				<aui:option label="any-option" value="any"/>
				<aui:option label="all-option" value="all"/>
			</aui:select>
		
		</aui:fieldset>
		<div style="height:61px"/>
		<aui:button-row>
			<aui:button type="submit" />
		</aui:button-row>
	</aui:form>

</div>
