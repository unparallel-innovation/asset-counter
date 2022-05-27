package pt.unparallel.asset_counter.portlet.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;

import pt.unparallel.asset_counter.constants.AssetCounterPortletKeys;


@Component(
		immediate=true,
		property = "javax.portlet.name=" + AssetCounterPortletKeys.ASSETCOUNTER,
		service= ConfigurationAction.class
		
		)
public class AssetCounterConfigurationAction extends DefaultConfigurationAction{
	@Override
	public String getJspPath(HttpServletRequest httpServletRequest) {

		return "/configuration.jsp";
		
	}
	

	
	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {
		Map<String, String[]> res = actionRequest.getParameterMap();
		List<String> categoryIdList = new ArrayList<String>();
		for ( String key : res.keySet() ) {
		    if(key.startsWith("categoryId")) {

		    	String[] categoryIds = ParamUtil.getStringValues(actionRequest,key);
		    	for(String categoryId: categoryIds) {
		    		categoryIdList.add(categoryId);
		    	}
		    }
		}

		String[] tagNames =  ParamUtil.getStringValues(actionRequest, "assetTagNames");
	
		setPreference(actionRequest,"categoryIds",categoryIdList.toArray(new String[0]));
		setPreference(actionRequest,"tagNames",tagNames);
		setPreference(
			actionRequest, "assetClassNameId",
			ParamUtil.getString(actionRequest, "assetClassNameId"));
		setPreference(
				actionRequest, "tagSelectionType",
				ParamUtil.getString(actionRequest, "tagSelectionType"));
		super.processAction(portletConfig, actionRequest, actionResponse);
	}
}
