package cz.cvut.warehouse.controller;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import cz.cvut.warehouse.controller.qualifiers.NewOrder;
import cz.cvut.warehouse.dao.OrderDao;
import cz.cvut.warehouse.dao.UserDao;
import cz.cvut.warehouse.model.Order;
import cz.cvut.warehouse.model.UserEntity;
import cz.cvut.warehouse.util.OrderStateType;

@Named(value="user")
@SessionScoped
public class UserController extends BaseController {
	
	private static final long serialVersionUID = 2038618566164260484L;
	
	private String  username;
	private String  role = "";
	private UserEntity user;
	
	@Inject
	private OrderDao orderManager;
	
	@Produces 
	@NewOrder
	private Order order;
	
	
	@PostConstruct
	@SuppressWarnings("unused")
	private void init(){
		order = new Order();
	}

	@Inject
	private UserDao userManager;

	public String send(){
		order.setState(OrderStateType.NEW);
		order.setUser(user);
		orderManager.update(order);
		return redirect("/public/intro/order");
	}
	
	public String clear(){
		order = new Order();
		return redirect("/public/intro/index");
	}
	
	public String getUsername() {
		
		if(user != null){
			username = user.getEmail();
			return username;
		}
		
		try {
			user = getRemoteUser();
			username = user.getEmail();
		} catch (AuthenticationException e) {
			username = "";
		}
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
	
	private UserEntity getRemoteUser() throws AuthenticationException{
		String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();	
		if(username != null){
			return userManager.getUser(username);
		}
		throw new AuthenticationException();
	}
	
	private String getUserRole(){
		if(user != null){
			return user.getRole();			
		}
		
		try {
			user = getRemoteUser();
			return user.getRole();
		} catch (AuthenticationException e) {
			return "";
		}
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	
	
	
	
	

}
