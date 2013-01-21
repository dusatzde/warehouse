package cz.cvut.warehouse.dao;

import java.io.Serializable;
import java.util.List;

import cz.cvut.warehouse.model.Address;
import cz.cvut.warehouse.model.UserEntity;

public interface UserDao  extends GenericDao<UserEntity, Serializable>{
	
	public List<UserEntity> getUsers(String roleName) ;
}
