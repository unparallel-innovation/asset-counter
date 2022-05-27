package pt.unparallel.asset_counter.portlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.asset.kernel.service.AssetTagLocalServiceUtil;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import pt.unparallel.asset_counter.constants.AssetCounterPortletKeys;

/**
 * @author antoniogoncalves
 */
@Component(immediate = true, property = { "com.liferay.fragment.entry.processor.portlet.alias=asset-counter",
		"com.liferay.portlet.display-category=category.sample", "com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true", "com.liferay.portlet.render-weight=0",
		"com.liferay.portlet.css-class-wrapper=pt-unparallel-asset-counter-portlet-wrapper",
		"javax.portlet.display-name=AssetCounter", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + AssetCounterPortletKeys.ASSETCOUNTER, 
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" ,
		"javax.portlet.supported-public-render-parameter=categoryId",
		"javax.portlet.supported-public-render-parameter=tag"
	
}, service = Portlet.class)
public class AssetCounterPortlet extends MVCPortlet {
	

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		// TODO Auto-generated method stub
		PortletPreferences prefs = renderRequest.getPreferences();
		String assetClassNameIdStr = prefs.getValue("assetClassNameId", null);
		String[] tagNames = prefs.getValues("tagNames", null);
		String tagSelectionType = prefs.getValue("tagSelectionType", null);
		if(assetClassNameIdStr!= null) {
			long assetClassNameId = Long.parseLong(assetClassNameIdStr);
				
			ThemeDisplay themeDisplay= (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			long portletGroupId= themeDisplay.getScopeGroupId();

			AssetEntryQuery assetEntryQuery = new AssetEntryQuery();
			assetEntryQuery.setClassNameIds(new long[] {assetClassNameId});
			assetEntryQuery.setGroupIds(new long[] {portletGroupId});
			assetEntryQuery.setEnablePermissions(true);
			if(tagNames!= null && tagNames.length > 0) {
				List<Long> tagIds = new ArrayList<Long>();
				for(String tagName: tagNames) {
					AssetTag assetTag = AssetTagLocalServiceUtil.fetchTag(portletGroupId, tagName);
					tagIds.add(assetTag.getTagId());  
				}
				long[] tagIdsArray = tagIds.stream().mapToLong(l -> l).toArray();
				if(tagSelectionType!= null && tagSelectionType.equals("all")) {
					assetEntryQuery.setAllTagIds(tagIdsArray);
				}else {
					assetEntryQuery.setAnyTagIds(tagIdsArray);
				}
				
			}
			int count = _assetEntryLocalService.getEntriesCount(assetEntryQuery);
			renderRequest.setAttribute("assetEntryCount", count);
		}else {
			include("/portlet_not_setup.jsp", renderRequest, renderResponse);
		}

		super.doView(renderRequest, renderResponse);

	}
	
	
	@Reference(unbind = "-")
	protected void setComponentChildLocalService(AssetEntryLocalService assetEntryLocalService) {

		_assetEntryLocalService = assetEntryLocalService;
	}
	
	
	
	private AssetEntryLocalService _assetEntryLocalService = null;
	
}