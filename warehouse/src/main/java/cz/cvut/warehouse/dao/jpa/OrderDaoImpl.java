package cz.cvut.warehouse.dao.jpa;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import cz.cvut.warehouse.dao.OrderDao;
import cz.cvut.warehouse.model.Order;



@Stateless(name="orderDaoImpl")
public class OrderDaoImpl extends GenericDaoJPAImpl<Order, Serializable> implements OrderDao{

	@Override
	public List<Order> getOrders(String state) {
		List<Order> result=  em.createNamedQuery("Order.findByState", Order.class)
				.setParameter("state", state)
				.getResultList();
		return result;
	}

}
