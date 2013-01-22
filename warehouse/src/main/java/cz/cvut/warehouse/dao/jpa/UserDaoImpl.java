package cz.cvut.warehouse.dao.jpa;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import cz.cvut.warehouse.dao.UserDao;
import cz.cvut.warehouse.model.UserEntity;

@Stateless(name="userDaoImpl")
public class UserDaoImpl extends GenericDaoJPAImpl<UserEntity, Serializable> implements UserDao{

	@Override
	public List<UserEntity> getUsers(String roleName) {
		List<UserEntity> result=  em.createNamedQuery("UserEntity.findByRole", UserEntity.class)
				.setParameter("role", roleName)
				.getResultList();
		return result;
	}

	@Override 
	public UserEntity getUser(String username) {
		UserEntity result=  em.createNamedQuery("UserEntity.findByUsername", UserEntity.class)
				.setParameter("username", username)
				.getSingleResult();
		return result;
	}


}
