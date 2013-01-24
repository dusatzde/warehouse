package cz.cvut.warehouse.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import cz.cvut.warehouse.dao.UserDao;
import cz.cvut.warehouse.model.UserEntity;
import cz.cvut.warehouse.util.RoleType;

@Named("employee")
@RequestScoped
public class Employee extends BaseController{

	private static final long serialVersionUID = 8747407780168462294L;
	
	private List<UserEntity> staff;
	
	@Inject
	private UserDao userManager;
	
	
	@PostConstruct
	@SuppressWarnings("unused")
	private void init(){
		staff = userManager.getUsers(RoleType.STOREKEEPER);
	}

	public List<UserEntity> getStaff() {
		return staff;
	}

	public void setStaff(List<UserEntity> staff) {
		this.staff = staff;
	}
	
	

}
