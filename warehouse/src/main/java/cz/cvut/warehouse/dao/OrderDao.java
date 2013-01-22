package cz.cvut.warehouse.dao;

import java.io.Serializable;
import java.util.List;
import cz.cvut.warehouse.model.Order;

public interface OrderDao extends GenericDao<Order, Serializable>{
	
	public List<Order> getOrders(String state);
}
