package cz.cvut.warehouse.dao.jpa;

import java.io.Serializable;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.Clustered;
import cz.cvut.warehouse.dao.UserDao;
import cz.cvut.warehouse.model.UserEntity;
import cz.cvut.warehouse.util.RoleType;

@Stateless(name="userDaoImpl")
@Clustered
@DeclareRoles({RoleType.CUSTOMER, RoleType.STOREKEEPER, RoleType.MANAGER})
public class UserDaoImpl extends GenericDaoJPAImpl<UserEntity, Serializable> implements UserDao{

	@Override
	@RolesAllowed({RoleType.CUSTOMER, RoleType.STOREKEEPER, RoleType.MANAGER})
	public List<UserEntity> getUsers(String roleName) {
		List<UserEntity> result=  em.createNamedQuery("UserEntity.findByRole", UserEntity.class)
				.setParameter("role", roleName)
				.getResultList();
		return result;
	}

	@Override 
	@RolesAllowed({RoleType.CUSTOMER, RoleType.STOREKEEPER, RoleType.MANAGER})
	public UserEntity getUser(String username) {
		UserEntity result=  em.createNamedQuery("UserEntity.findByUsername", UserEntity.class)
				.setParameter("username", username)
				.getSingleResult();
		return result;
	}


}
