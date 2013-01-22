package cz.cvut.warehouse.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.inject.Inject;
import javax.inject.Named;

import cz.cvut.warehouse.dao.OrderDao;
import cz.cvut.warehouse.model.Order;
import cz.cvut.warehouse.util.OrderStateType;

@Named("order")
@RequestScoped
public class OrderController extends BaseController {

	
	private static final long serialVersionUID = -4011482258636832917L;
	
	private List<Order> newOrders;
	private List<Order> orders;
	private UIComponent confirmButton;
	
	@Inject
	private OrderDao orderManager;
	
	
	@PostConstruct
	@SuppressWarnings("unused")
	private void init(){
		newOrders = orderManager.getOrders(OrderStateType.NEW);
		orders = orderManager.getOrders(OrderStateType.CONFIRMED);
	}
	
	public void confirm(Order order){
		order.setState(OrderStateType.CONFIRMED);
		orderManager.update(order);
	}

	public List<Order> getNewOrders() {
		System.out.println("ORDER NEW SIZE: " + newOrders.size());
		return newOrders;
	}

	public void setNewOrders(List<Order> newOrders) {
		this.newOrders = newOrders;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public UIComponent getConfirmButton() {
		return confirmButton;
	}

	public void setConfirmButton(UIComponent confirmButton) {
		this.confirmButton = confirmButton;
	}
}
