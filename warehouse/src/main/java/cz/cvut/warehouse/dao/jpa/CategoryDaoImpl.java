package cz.cvut.warehouse.dao.jpa;

import java.io.Serializable;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.Clustered;
import cz.cvut.warehouse.dao.CategoryDao;
import cz.cvut.warehouse.model.Category;
import cz.cvut.warehouse.util.RoleType;

@Stateless(name="categoryDaoImpl")
@Clustered
@DeclareRoles({RoleType.CUSTOMER, RoleType.STOREKEEPER, RoleType.MANAGER})
@PermitAll
public class CategoryDaoImpl extends GenericDaoJPAImpl<Category, Serializable> implements CategoryDao{

	@Override
	public Category find(String name) {
		return (Category) em.createNamedQuery("Category.findByName")
							.setParameter("name", name)
							.getSingleResult();

	}

}
