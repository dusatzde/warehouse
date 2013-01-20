package cz.cvut.warehouse.controller;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named(value="logout")
@RequestScoped
public class Logout extends BaseController{

	private static final long serialVersionUID = 459890362069908131L;
	
	public String logout(){
		 FacesContext fc = FacesContext.getCurrentInstance();  
	     HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);  
	     session.invalidate(); 
		return redirect("private");
	}

}
