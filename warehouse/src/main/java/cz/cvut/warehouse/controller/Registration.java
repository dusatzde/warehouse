package cz.cvut.warehouse.controller;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.inject.Inject;
import javax.inject.Named;
import cz.cvut.warehouse.dao.UserDao;
import cz.cvut.warehouse.model.UserEntity;
import cz.cvut.warehouse.util.DigestUtils;
import cz.cvut.warehouse.util.RoleType;

@Named("registration")
@RequestScoped
public class Registration extends BaseController{

	private static final long serialVersionUID = 6633696202476835313L;
	
	@Inject
	private UserDao userManager;
	
	private UserEntity userEntity = new UserEntity();
	private UIComponent registerButton;
	
	
	public void registerCustomer(){
		userEntity.setRole(RoleType.CUSTOMER);
		encryptPassword();
		userManager.create(userEntity);
		initInfoMessage(registerButton, "OK", "Your account has been registered successfully. Please login.");
		userEntity = new UserEntity();
	}
	
	public void registerEmployee(){
		userEntity.setRole(RoleType.STOREKEEPER);
		encryptPassword();
		userManager.create(userEntity);
		initInfoMessage(registerButton, "OK", "An account has been registerer successfully.");
		userEntity = new UserEntity();
	}
	
	private void encryptPassword(){
		String password = userEntity.getPassword();
		password = DigestUtils.getHash(password);
		userEntity.setPassword(password);
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
