package cz.cvut.warehouse.dao;

import java.io.Serializable;
import cz.cvut.warehouse.model.Address;
import cz.cvut.warehouse.model.UserEntity;

public interface AddressDao extends GenericDao<Address, Serializable> {

	public Address getAddress(Serializable pk);
}
