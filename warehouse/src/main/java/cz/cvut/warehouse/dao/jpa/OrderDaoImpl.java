package cz.cvut.warehouse.dao.jpa;

import java.io.Serializable;
import javax.ejb.Stateless;
import cz.cvut.warehouse.dao.OrderDao;
import cz.cvut.warehouse.model.Order;
import cz.cvut.warehouse.model.UserEntity;


@Stateless(name="orderDaoImpl")
public class OrderDaoImpl extends GenericDaoJPAImpl<Order, Serializable> implements OrderDao{

}
