package cz.cvut.warehouse.dao.jpa;

import java.io.Serializable;
import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.Clustered;
import cz.cvut.warehouse.dao.AddressDao;
import cz.cvut.warehouse.model.Address;

@Stateless(name="addressDaoImpl")
@Clustered
public class AddressDaoImpl extends GenericDaoJPAImpl<Address, Serializable> implements AddressDao{

	@Override
	public Address getAddress(Serializable pk) {
		return (Address) em.createNamedQuery("Address.findByUser")
				.setParameter("user", pk)
				.getSingleResult();
	}

}
