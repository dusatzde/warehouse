package cz.cvut.warehouse.view.jformbuilder;

import com.codingcrayons.jformbuilder.cache.ResourceCache;
import com.codingcrayons.jformbuilder.configuration.Context;
import com.codingcrayons.jformbuilder.ondemand.DefaultJFBGeneratorHandler;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.ComponentConfig;


public class AdaptiveJFBGeneratorHandler extends DefaultJFBGeneratorHandler {

	private static final String DEFAULT_CONFIG = "primeFaces";
	private static final int JFB_CACHE = 10;
	private Boolean _development = true;
	private Boolean _debug = false;

	public AdaptiveJFBGeneratorHandler(ComponentConfig config) {
		super(config, JFB_CACHE);
		FacesContext ctx = FacesContext.getCurrentInstance();
		Integer jfbCache = Integer.parseInt(ctx.getExternalContext().getInitParameter("JFB.CACHE"));
		if (jfbCache == null) {
			jfbCache = -1;
		}
		super.jfbCacheTime = jfbCache;
		_development = Boolean.parseBoolean(ctx.getExternalContext().getInitParameter("JFB.DEVELOPMENT"));
		if (_development == null) {
			_development = false;
		}
		_debug = Boolean.parseBoolean(ctx.getExternalContext().getInitParameter("JFB.DEBUG"));
		if (_debug == null) {
			_debug = false;
		}
	}

	private String applySettings(List<String> profiles) {
		String calcLayout = null;
		if (layout != null) {
			calcLayout = layout.getValue();
			calcLayout = (String) executeExpressionInElContext(FacesContext.getCurrentInstance()
					.getApplication().getExpressionFactory(), FacesContext.getCurrentInstance()
					.getELContext(), calcLayout);
		}
		return (calcLayout == null) ? null : ("../../layouts/" + calcLayout);
	}

    @Override
	protected InputStream hookCustomInputStream() {
		String role = "admin";
		String[] roles = {
				role
		};
		List<String> profiles = new ArrayList<String>();
		String appliedLayout = applySettings(profiles);
		InputStream outIS = generateAsIS(classesToInspect, getConfig(), roles, profiles, appliedLayout);
		return outIS;
	}
 
    @Override
	protected InputStream createInputStream(String s) {
		try {
			if (_development) {
				ResourceCache.getInstance().clear();
			}
			if (_debug) {
				System.out.println(s);
			}
			return new ByteArrayInputStream(s.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			return viewFragmentExceptionIS(e);
		}
	}

	protected void hookManagerContext(Context context) {
		context.getVariables().put("adm", "Admin");
	}

    @Override
	protected String getConfig() {
		if (configName == null || configName.getValue().isEmpty()) {
			return DEFAULT_CONFIG;
		}
		return configName.getValue();
	}
}

