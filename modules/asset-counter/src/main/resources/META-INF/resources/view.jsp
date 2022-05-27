<%@ include file="/init.jsp"%>

<portlet:defineObjects />

<%
Integer count = (Integer) renderRequest.getAttribute("assetEntryCount");
if(count != null){
%>

<span class="pt-unparallel-asset-counter-value"><%=count %></span>

<% }%>