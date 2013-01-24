package cz.cvut.warehouse.controller;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.jboss.seam.international.status.builder.BundleKey;

abstract public class BaseController implements Serializable{

	private static final long serialVersionUID = 5931761146162041346L;
	
	public static final String BUNDLE = "text";

	protected String redirect(String view) {
		return view + "?faces-redirect=true";
	}

	protected FacesMessage facesMessage(String text) {
		return facesMessage("", text, FacesMessage.SEVERITY_INFO);
	}

	protected FacesMessage facesMessage(String title, String text, FacesMessage.Severity severity) {
		return facesMessage("growl", title, text, severity);
	}

	protected FacesMessage facesMessage(String id, String title, String text, FacesMessage.Severity severity) {
		FacesMessage message = new FacesMessage(severity, getBundleText(title), getBundleText(text));
		FacesContext.getCurrentInstance().addMessage(id, message);
		return message;
	}
	
	protected void initInfoMessage(UIComponent component, String msg, String description) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, description);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(component.getClientId(context), message);
    }

	protected String getBundleText(String key) {
		return key == null ? null : new BundleKey(BUNDLE, key).toString();
	}
	
	protected double roundTwoDecimals(double d) {
		int ix = (int)(d * 100.0); 
		 d = ((double)ix)/100.0;
        return d;
	}
}

