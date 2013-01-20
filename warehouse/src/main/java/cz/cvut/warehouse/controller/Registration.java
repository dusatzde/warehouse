package cz.cvut.warehouse.controller;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import cz.cvut.warehouse.dao.UserDao;
import cz.cvut.warehouse.model.UserEntity;
import cz.cvut.warehouse.util.RoleType;

@Named("registration")
@RequestScoped
public class Registration extends BaseController{

	private static final long serialVersionUID = 6633696202476835313L;
	
	@Inject
	private UserDao userManager;
	
	private UserEntity userEntity = new UserEntity();
	private UIComponent registerButton;
	
	
	public String registerCustomer(){
		userEntity.setRole(RoleType.CUSTOMER);
		userManager.create(userEntity);
		initInfoMessage(registerButton, "OK", "Registration was successful. Please login.");
		userEntity = new UserEntity();
		return redirect("");
	}
	
	protected void initInfoMessage(UIComponent component, String msg, String description) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, description);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(component.getClientId(context), message);
    }


	public UserEntity getUserEntity() {
		return userEntity;
	}


	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public UIComponent getRegisterButton() {
		return registerButton;
	}

	public void setRegisterButton(UIComponent registerButton) {
		this.registerButton = registerButton;
	}
}
