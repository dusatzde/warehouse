package cz.cvut.warehouse.dao.jpa;

import java.io.Serializable;
import javax.ejb.Stateless;
import cz.cvut.warehouse.dao.AddressDao;
import cz.cvut.warehouse.model.Address;

@Stateless(name="addressDaoImpl")
public class AddressDaoImpl extends GenericDaoJPAImpl<Address, Serializable> implements AddressDao{

	@Override
	public Address getAddress(Serializable pk) {
		return (Address) em.createNamedQuery("Address.findByUser")
				.setParameter("user", pk)
				.getSingleResult();
	}

}
