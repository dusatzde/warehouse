package cz.cvut.warehouse.controller;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value="user")
@SessionScoped
public class UserController extends BaseController {
	
	private static final long serialVersionUID = 2038618566164260484L;
	
	private String  username;
	private String  role;
	private boolean logged;

	

	public String getUsername() {
		username = getRemoteUser();
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		
		if(!role.equals("")){
			return role;
		}
		
		role =  getUserRole();
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isLogged() {
		logged = (getRemoteUser() == null)?false:true;
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}
	
	private String getRemoteUser(){
		return FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();	
	}
	
	private String getUserRole(){
		ExternalContext ec =  FacesContext.getCurrentInstance().getExternalContext();
		
		if(ec.isUserInRole("customer")){
			return "customer";
			
		}else if(ec.isUserInRole("storekeeper")){
			return "storekeeper";
			
		}else if(ec.isUserInRole("manager")){
			return "manager";
			
		}else{
			return "";
		}
	}
	
	
	
	
	
	

}
