package cz.cvut.warehouse.dao;

import java.io.Serializable;
import cz.cvut.warehouse.model.Address;

public interface AddressDao extends GenericDao<Address, Serializable> {

	public Address getAddress(Serializable pk);
}
