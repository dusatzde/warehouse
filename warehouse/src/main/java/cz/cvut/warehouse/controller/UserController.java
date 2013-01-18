package cz.cvut.warehouse.controller;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value="user")
@SessionScoped
public class UserController extends BaseController {
	
	private static final long serialVersionUID = 2038618566164260484L;
	
	private String  username;
	private String  role;

	public String getUsername() {
		username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
		System.out.println("USERNAME USERNAME: " + username);
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		role = "" + FacesContext.getCurrentInstance().getExternalContext().isUserInRole("customer");
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	

}
