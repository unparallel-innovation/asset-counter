<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@
page import="com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil" %>
<%@
page import="com.liferay.portal.kernel.util.ListUtil" %>
<%@
page import="com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil" %>
<%@
page import="com.liferay.asset.kernel.model.AssetRendererFactory" %>

<%@
page import="com.liferay.asset.kernel.model.ClassType" %><%@
page import="com.liferay.asset.kernel.model.ClassTypeField" %><%@
page import="com.liferay.asset.kernel.model.ClassTypeReader" %>

<%@
page import="com.liferay.taglib.aui.AUIUtil" %>
<%@
page import="java.util.List" %>
<%@
page import="java.util.Map" %>
<%@
page import="java.util.Arrays" %>
<%@
page import="com.liferay.asset.kernel.model.AssetVocabularyConstants" %>
<%@
page import="com.liferay.asset.util.comparator.AssetRendererFactoryTypeNameComparator" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ taglib prefix="liferay-asset" uri="http://liferay.com/tld/asset" %>
<liferay-theme:defineObjects />

<portlet:defineObjects />
