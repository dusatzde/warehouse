package cz.cvut.warehouse.dao.jpa;

import java.io.Serializable;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.Clustered;
import cz.cvut.warehouse.dao.AddressDao;
import cz.cvut.warehouse.model.Address;
import cz.cvut.warehouse.util.RoleType;

@Stateless(name="addressDaoImpl")
@Clustered
@DeclareRoles({RoleType.CUSTOMER, RoleType.STOREKEEPER, RoleType.MANAGER})
public class AddressDaoImpl extends GenericDaoJPAImpl<Address, Serializable> implements AddressDao{

	@Override
	@RolesAllowed({RoleType.STOREKEEPER, RoleType.MANAGER})
	public Address getAddress(Serializable pk) {
		return (Address) em.createNamedQuery("Address.findByUser")
				.setParameter("user", pk)
				.getSingleResult();
	}

}
