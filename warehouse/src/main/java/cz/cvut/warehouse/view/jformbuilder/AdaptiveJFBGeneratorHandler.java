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

/**
 * Dynamic JFB assembler to Facelets (adaptive to instance changes)
 * 
 * @author tomas
 */
public class AdaptiveJFBGeneratorHandler extends DefaultJFBGeneratorHandler {

	private static final String DEFAULT_CONFIG = "primeFaces";
	//DEVELOPMENT (10 - cache for 10 sec)
	//PRODUCTION (-1 cache forever -1)
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

	/*
	 * Applies Adaptive settings
	 */
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

	/*
	 * Template method that addes content (non-Javadoc)
	 * 
	 * @see com.jformbuilder.ondemand.GeneratorHandler#hookInputStream()
	 */

       @Override
	protected InputStream hookCustomInputStream() {
		//		Long time = System.currentTimeMillis();

		String role = "admin";
		String[] roles = {
				role
		};
		List<String> profiles = new ArrayList<String>();

		String appliedLayout = applySettings(profiles);

		InputStream outIS = generateAsIS(classesToInspect, getConfig(), roles, profiles, appliedLayout);

		//		Long timeNow = System.currentTimeMillis();
		//		Long timeItTook = timeNow - time;
		//		String out = String.format("%d:%02d:%02d.%03d", timeItTook / 60, timeItTook / 3600, (timeItTook % 3600) / 60,
		//				(timeItTook % 600));
		//		System.out.println("TIME (" + this.classesToInspect == null ? entityClassName
		//				: getFragmentName(classesToInspect) + ") " + out + " (" + timeItTook + "ms)");

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
		// Example how to add context variable
		context.getVariables().put("tom", "Tomas");
	}

        @Override
	protected String getConfig() {
		if (configName == null || configName.getValue().isEmpty()) {
			return DEFAULT_CONFIG;
		}
		return configName.getValue();
	}

}

