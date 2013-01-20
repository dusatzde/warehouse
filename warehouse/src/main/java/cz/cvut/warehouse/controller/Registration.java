package cz.cvut.warehouse.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import cz.cvut.warehouse.model.UserEntity;

@Named("registration")
@RequestScoped
public class Registration extends BaseController{

	private static final long serialVersionUID = 6633696202476835313L;
	
	private UserEntity userEntity = new UserEntity();
	
	
	public String register(){
		return redirect("");
	}


	public UserEntity getUserEntity() {
		return userEntity;
	}


	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
}
