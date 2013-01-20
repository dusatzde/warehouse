package cz.cvut.warehouse.dao;

import java.io.Serializable;
import javax.ejb.Stateless;

import cz.cvut.warehouse.model.Address;
import cz.cvut.warehouse.model.UserEntity;

@Stateless(name="userDaoImpl")
public class UserDaoImpl extends GenericDaoJPAImpl<UserEntity, Serializable> implements UserDao{


}
